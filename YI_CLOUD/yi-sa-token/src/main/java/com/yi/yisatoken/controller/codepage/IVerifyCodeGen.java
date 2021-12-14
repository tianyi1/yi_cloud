package com.yi.yisatoken.controller.codepage;

import java.io.IOException;
import java.io.OutputStream;

/**
 * ��֤�����ɽӿ�
 */
public interface IVerifyCodeGen {

    /**
     * ������֤�벢����code����ͼƬд��os��
     *
     * @param width
     * @param height
     * @param os
     * @return
     * @throws IOException
     */
    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * ������֤�����
     *
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    VerifyCode generate(int width, int height) throws IOException;
}