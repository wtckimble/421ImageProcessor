
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author student
 */
public class ImageLabel extends JComponent {

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        repaint();
        revalidate();

    }

    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(image, null, 0, 0);
    }

    public static void main(String[] args) throws Exception {

        ImageLabel displayer = new ImageLabel();
        BufferedImage image = ImageIO.read(new File("ClearWaterBay.jpg"));
        displayer.setImage(image);

        JFrame frame = new JFrame();
        frame.getContentPane().add(displayer);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}
