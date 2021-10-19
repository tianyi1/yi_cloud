package com.yi.yiserversecurity.config;

import com.yi.yiserversecurity.handler.MyAccessDeniedHandler;
import com.yi.yiserversecurity.handler.MyAuthenticationFailureHandler;
import com.yi.yiserversecurity.handler.MyAuthenticationSuccessHandler;
import com.yi.yiserversecurity.service.UserDetailServiveImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;


    @Autowired
    private UserDetailServiveImpl userDetailServive;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录认证（Authentication）
        http.formLogin()
                //定义入参，默认是username和password
                .usernameParameter("username1")
                .passwordParameter("password1")
                //自定义登录页面 登录页面必须post请求
                .loginPage("/xxxx/login.html")
                .loginProcessingUrl("/xxxx/login")
                //登录成功调转的页面
//                .successForwardUrl("/xxxx/toMain")
                //自定义路由调转，前后端分离
                .successHandler(new MyAuthenticationSuccessHandler("/xxxx/main.html"))
                .failureHandler(new MyAuthenticationFailureHandler("/xxxx/err.html"));

        //访问授权（Authorization）
        http.authorizeRequests()
                //所有的权限都基于access表达是来实现的
                //开发权限 ？匹配一个字符 * 匹配0个或多个字符 ** 匹配0个或多个目录
//                .antMatchers("/xxxx/err.html").permitAll()
                .antMatchers("/xxxx/login.html").permitAll()
                //基于权限进行控制
//                .antMatchers("/xxxx/role.html").hasAuthority("admin")
                //基于角色进行控制
//                .antMatchers("/xxxx/role.html").hasRole("abc")
                //基于ip地址进行控制
//                .antMatchers("/xxxx/role.html").hasIpAddress("127.0.0.1")
//                //开发静态资源权限
//                .antMatchers("/img/**").permitAll()
//                //正则表达式放行
//                .regexMatchers(".+[.].png").permitAll()
                //限制接口请求方式
//                .regexMatchers(HttpMethod.GET,"/xxxx/demo").permitAll()
                //mvc匹配 servletPath配置
//                .mvcMatchers("/demo").servletPath("/xxxx").permitAll()
                //1、拦截所有请求 从上往下执行 必须放在最后
                //2、所有请求必须先进行认证才能进行请求
                .anyRequest().authenticated();

        //rememberMe 记住我
        http.rememberMe()
//                数据源配置
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(60)//默认两周时间
                .userDetailsService(userDetailServive);
        //相关403异常处理
        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler("/xxxx/403.html"));
        //防火墙 关闭
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPw() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //必须添加mysql的驱动包不然无法使用
        tokenRepository.setDataSource(dataSource);
        //设置自动建表，第一次建表没有问题 第二次已经存在了将会报错，所以再启动的时候要屏蔽掉
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
