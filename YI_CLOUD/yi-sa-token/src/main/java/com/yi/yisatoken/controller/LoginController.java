package com.yi.yisatoken.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 官方文档 ：https://sa-token.dev33.cn/doc/index.html#/
 * 登录测试
 * 所谓登录认证，说白了就是限制某些API接口必须登录后才能访问（例：查询我的账号资料）
 * 那么如何判断一个会话是否登录？框架会在登录成功后给你做个标记，每次登录认证时校验这个标记，有标记者视为已登录，无标记者视为未登录！
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/doLogin")
    public SaResult doLogin(String name, String pwd) {
        // 测试登录  ---- http://localhost:3310/login/doLogin?name=zhang&pwd=123456
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:3310/login/isLogin
    @RequestMapping("/isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:3310/login/tokenInfo
    @RequestMapping("/tokenInfo")
    public SaResult tokenInfo() {
// 获取指定token对应的账号id，如果未登录，则返回 null
        Object ttt = StpUtil.getLoginIdByToken("10001");
// 获取当前`StpLogic`的token名称
        String tokenName = StpUtil.getTokenName();
// 获取当前会话的token值
        String tokenValue = StpUtil.getTokenValue();
        System.out.println(ttt + "|" + tokenName + "|" + tokenValue);
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:3310/login/logout
    @RequestMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
    // ---- http://localhost:3310/login/queryId
    @RequestMapping("/queryId")
    public SaResult queryId() {
        Object id = StpUtil.getLoginId();
        // 类似查询API还有：
        String loginIdAsString = StpUtil.getLoginIdAsString();// 获取当前会话账号id, 并转化为`String`类型
        int loginIdAsInt = StpUtil.getLoginIdAsInt();// 获取当前会话账号id, 并转化为`int`类型
        long loginIdAsLong = StpUtil.getLoginIdAsLong();// 获取当前会话账号id, 并转化为`long`类型
        System.out.println("信息是：" + id + "|" + loginIdAsString + "|" + loginIdAsInt + "|" + loginIdAsLong);
        Object loginIdDefaultNull = StpUtil.getLoginIdDefaultNull();
        System.out.println("未登录：" + loginIdDefaultNull);

        return SaResult.ok();
    }

    // 测试注销  ---- http://localhost:3310/login/queryCode
    @RequestMapping("/queryCode")
    public void queryCode(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
//        String syscode = request.getParameter("clientcode");
        String responseStr = "";
        String requestStr = request.getQueryString();
        System.out.println(requestStr);
        requestStr = requestStr.substring("METHOD=uniForward".length(), requestStr.length());
        String BOSSURL = "http://10.68.0.65:8080/OssWeb/core/interactive/usermanagement/UserManageAction.do?METHOD=WECHATBH" + requestStr;
        System.out.println(BOSSURL);
        PostMethod postMethod = new PostMethod(BOSSURL);
        postMethod.setRequestHeader("Connection", "close");
        postMethod.addParameter("version", request.getParameter("version"));
        postMethod.addParameter("clientcode", request.getParameter("clientcode"));
        postMethod.addParameter("clientpwd", request.getParameter("clientpwd"));
        postMethod.addParameter("servicecode", request.getParameter("servicecode"));
        postMethod.addParameter("requestid", request.getParameter("requestid"));
        postMethod.addParameter("requestContent", request.getParameter("requestContent"));
        postMethod.addParameter("citycode", request.getParameter("citycode"));
        try {
            HttpClient httpClient = new HttpClient();
            postMethod.addRequestHeader("Connection", "close");
            httpClient.executeMethod((HttpMethod)postMethod);
            responseStr = postMethod.getResponseBodyAsString().trim();
            System.out.println("======================BOSS)");
            System.out.println("responseStr=" + responseStr);
            if(!"".equals(responseStr) && responseStr!=null){
                try {
                    JSONObject dataInfo = JSONObject.fromObject(responseStr);
                    JSONObject dataOutput =  JSONObject.fromObject(dataInfo.getString("output"));
                    if("1".equals(dataOutput.getString("sendredirect"))){
                        response.sendRedirect(dataOutput.getString("thirdparty_ru"));
                    }else {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(responseStr);
                    }
                }catch(Exception e) {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(responseStr);
                }
            }else{
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(responseStr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }
        response.getWriter().flush();
        response.getWriter().close();
    }

}
