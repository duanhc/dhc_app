//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.awt.*;
import java.awt.geom.RoundRectangle2D.Float;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    private static final int QRCODE_SIZE = 300;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public QRCodeUtil() {
    }

    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, Integer.valueOf(1));
        BitMatrix bitMatrix = (new MultiFormatWriter()).encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, 1);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                image.setRGB(x, y, bitMatrix.get(x, y)?-16777216:-1);
            }
        }

        if(imgPath != null && !"".equals(imgPath)) {
            insertImage(image, imgPath, needCompress);
            return image;
        } else {
            return image;
        }
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if(!file.exists()) {
            System.err.println(imgPath + "   该文件不存在！");
        } else {
            Object src = ImageIO.read(new File(imgPath));
            int width = ((Image)src).getWidth((ImageObserver)null);
            int height = ((Image)src).getHeight((ImageObserver)null);
            if(needCompress) {
                if(width > 60) {
                    width = 60;
                }

                if(height > 60) {
                    height = 60;
                }

                Image graph = ((Image)src).getScaledInstance(width, height, 4);
                BufferedImage x = new BufferedImage(width, height, 1);
                Graphics y = x.getGraphics();
                y.drawImage(graph, 0, 0, (ImageObserver)null);
                y.dispose();
                src = graph;
            }

            Graphics2D graph1 = source.createGraphics();
            int x1 = (300 - width) / 2;
            int y1 = (300 - height) / 2;
            graph1.drawImage((Image)src, x1, y1, width, height, (ImageObserver)null);
            Float shape = new Float((float)x1, (float)y1, (float)width, (float)width, 6.0F, 6.0F);
            graph1.setStroke(new BasicStroke(3.0F));
            graph1.draw(shape);
            graph1.dispose();
        }
    }

    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        String file = (new Random()).nextInt(99999999) + ".jpg";
        ImageIO.write(image, "JPG", new File(destPath + "/" + file));
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if(!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }

    }

    public static void encode(String content, String imgPath, String destPath) throws Exception {
        encode(content, imgPath, destPath, false);
    }

    public static void encode(String content, String destPath, boolean needCompress) throws Exception {
        encode(content, (String)null, (String)destPath, needCompress);
    }

    public static void encode(String content, String destPath) throws Exception {
        encode(content, (String)null, (String)destPath, false);
    }

    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = createImage(content, imgPath, needCompress);
        ImageIO.write(image, "JPG", output);
    }

    public static void encode(String content, OutputStream output) throws Exception {
        encode(content, (String)null, (OutputStream)output, false);
    }

    public static String decode(File file) throws Exception {
        BufferedImage image = ImageIO.read(file);
        if(image == null) {
            return null;
        } else {
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Hashtable hints = new Hashtable();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            Result result = (new MultiFormatReader()).decode(bitmap, hints);
            String resultStr = result.getText();
            return resultStr;
        }
    }

    public static String decode(String path) throws Exception {
        return decode(new File(path));
    }
}
