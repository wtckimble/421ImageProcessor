
/**
 * *************************************************************
 *
 * COMPS412F Simple Image Processor
 *
 * The application demonstrates how to perform Image Processing operations based
 * on Java API.
 *
 */
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class ImageOperator {

    private static short[] red = new short[256];
    private static short[] green = new short[256];
    private static short[] blue = new short[256];

    public static BufferedImage warm(BufferedImage image) {

        // fill in your code here
        for (int i = 0; i < 256; i++) {
            red[i] = (short) (i + 15);
            green[i] = (short) i;
            blue[i] = (short) i;
            if (red[i] > 255) {
                red[i] = 255;
            }
        }

        short[][] warm = new short[][]{red, green, blue};
        BufferedImageOp op = new LookupOp(new ShortLookupTable(0, warm), null);
        BufferedImage oimage = op.filter(image, null);
        return oimage;
    }

    public static BufferedImage chill(BufferedImage image) {

        // fill in your code here
        for (int i = 0; i < 256; i++) {
            red[i] = (short) i;
            green[i] = (short) i;
            blue[i] = (short) (i + 15);
            if (blue[i] > 255) {
                blue[i] = 255;
            }
        }

        short[][] chill = new short[][]{red, green, blue};
        BufferedImageOp op = new LookupOp(new ShortLookupTable(0, chill), null);
        BufferedImage oimage = op.filter(image, null);
        return oimage;
    }

    public static BufferedImage negation(BufferedImage image) {

        // fill in your code here
        for (int i = 0; i < 256; i++) {
            red[i] = (short) (255 - i);
            green[i] = (short) (255 - i);
            blue[i] = (short) (255 - i);
        }

        short[][] negation = new short[][]{red, green, blue};
        BufferedImageOp op = new LookupOp(new ShortLookupTable(0, negation), null);
        BufferedImage oimage = op.filter(image, null);
        return oimage;
    }

    public static BufferedImage posterization(BufferedImage image, int bits) {

        // fill in your code here
        for (int i = 0; i < 256; i++) {
            red[i] = (short) ((i >> bits) << bits);
            green[i] = (short) ((i >> bits) << bits);
            blue[i] = (short) ((i >> bits) << bits);
        }

        short[][] posterization = new short[][]{red, green, blue};
        BufferedImageOp op = new LookupOp(new ShortLookupTable(0, posterization), null);
        BufferedImage oimage = op.filter(image, null);
        return oimage;
    }

    public static BufferedImage blur(BufferedImage image) {

        // fill in your code here
        float ninth = 1.0f / 9.0f;
        float[] blurKernel = {
            ninth, ninth, ninth,
            ninth, ninth, ninth,
            ninth, ninth, ninth,};
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel));
        BufferedImage oimage = op.filter(image, null);

        return oimage;
    }

    public static BufferedImage edge(BufferedImage image) {

        // fill in your code here
        float[] edgeKernel = {
            0, -1, 0,
            -1, 4, -1,
            0, -1, 0,};
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, edgeKernel));
        BufferedImage oimage = op.filter(image, null);

        return oimage;
    }

    public static BufferedImage flipX(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double[] matrix = {
            -1, 0, 0, 1, width, 0
        };
        BufferedImageOp op = new AffineTransformOp(
                new AffineTransform(matrix),
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage mBufferedImage = op.filter(image, null);
        return mBufferedImage;

    }

    public static BufferedImage flipY(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double[] matrix = {
            1, 0, 0, -1, 0, height
        };
        BufferedImageOp op = new AffineTransformOp(
                new AffineTransform(matrix),
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage mBufferedImage = op.filter(image, null);
        return mBufferedImage;

    }

    public static BufferedImage rotate(BufferedImage image, double value) {
        int width = image.getWidth();
        int height = image.getHeight();
        value = toRadians(value);
        double[] matrix = {
            cos(value), sin(value), -sin(value), cos(value), width/2, -height/2
        };
        BufferedImageOp op = new AffineTransformOp(
                new AffineTransform(matrix),
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage mBufferedImage = op.filter(image, null);
        return mBufferedImage;
        
    }

    public static BufferedImage greyScale(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = inputImage.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

	// add your code here to modify the RGB pixel values
                double average = (red + green + blue)/3;
                red = (int) average;
                green = (int) average;
                blue = (int) average;
                pixel = (((((alpha << 8) + red) << 8) + green) << 8) + blue;
                inputImage.setRGB(x, y, pixel);
            }
        }
        return inputImage;
    }

}
