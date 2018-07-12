package util;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.logging.Logger;

public class VerifyCodeFactory {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 38;
    private static final String PARAM = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";

    public String getImageVerifyCode(OutputStream out) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        initGraphics(g);
        String val = write((Graphics2D) g);
        try {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }

    public String getDigitVerifyCode() {
        int ans = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 4; i++) {
            ans *= 10;
            ans += (int) (Math.random() * 10);
        }
        return String.valueOf(ans);
    }

    private String write(Graphics2D g) {
        int x = 5;
        StringBuffer sb = new StringBuffer();
        String ch = "";
        for (int i = 0; i < 4; i++) {
            ch = PARAM.charAt(new Random().nextInt(PARAM.length())) + "";
            sb.append(ch);
            int degree = new Random().nextInt() % 30;
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }

    private void initGraphics(Graphics g) {
        //设置图片的背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //设置图片的边框
        g.setColor(Color.BLUE);
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);

        //在图片上画随机线条
        g.setColor(Color.GREEN);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            int y1 = random.nextInt(HEIGHT);
            int x1 = random.nextInt(WIDTH);
            g.drawLine(x1, y1, x2, y2);
        }

        // 设置字体颜色
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 20));
    }


}


