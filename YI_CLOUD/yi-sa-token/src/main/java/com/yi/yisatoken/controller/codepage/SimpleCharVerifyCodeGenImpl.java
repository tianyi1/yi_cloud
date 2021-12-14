package com.yi.yisatoken.controller.codepage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * ��֤��ʵ����
 */
public class SimpleCharVerifyCodeGenImpl implements IVerifyCodeGen {

    private static final String[] FONT_TYPES = { "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };

    private static final int VALICATE_CODE_LENGTH = 6;

    /**
     * ���ñ�����ɫ����С��������
     *
     * @param graphics
     * @param width
     * @param height
     */
    private static void fillBackground(Graphics graphics, int width, int height) {
        // ��䱳��
        graphics.setColor(Color.WHITE);
        //���þ�������x y Ϊ0
        graphics.fillRect(0, 0, width, height);

        // �����������
        for (int i = 0; i < 20; i++) {
            //���������ɫ�㷨����
            graphics.setColor(RandomUtils.randomColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            graphics.drawLine(x, y, x1, y1);
        }
    }

    /**
     * ��������ַ�
     *
     * @param width
     * @param height
     * @param os
     * @return
     * @throws IOException
     */
    @Override
    public String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomUtils.randomString(VALICATE_CODE_LENGTH);
        createCharacter(graphics, randomStr);
        graphics.dispose();
        //����JPEG��ʽ
        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }

    /**
     * ��֤������
     *
     * @param width
     * @param height
     * @return
     */
    @Override
    public VerifyCode generate(int width, int height) {
        VerifyCode verifyCode = null;
        try (
                //�����ĳ�ʼ���ŵ�����Ͳ���Ҫ�ֶ��ر���
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            String code = generate(width, height, baos);
            verifyCode = new VerifyCode();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(baos.toByteArray());
        } catch (IOException e) {
            verifyCode = null;
        }
        return verifyCode;
    }

    /**
     * �����ַ���ɫ��С
     *
     * @param g
     * @param randomStr
     */
    private void createCharacter(Graphics g, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //����RGB��ɫ�㷨����
            g.setColor(new Color(50 + RandomUtils.nextInt(100),
                    50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100)));
            //���������С������
            g.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(FONT_TYPES.length)], Font.BOLD, 26));
            //����x y ����
            g.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(8));
        }
    }
}