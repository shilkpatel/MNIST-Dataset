import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.IOException;

public class Representation
{
    int num_images;
    JFrame display = new JFrame();

    JPanel panel=new JPanel();
    JLabel[] image_canvases;
    JButton iteration = new JButton();

    public Representation(int num)
    {
        num_images = num;
        image_canvases= new JLabel[num_images];
        display.add(panel);
        display.setSize(100,100);

    }
    public void display_images(double[][] images)
    {

        for(int i=0;i<num_images;i++)
        {

            BufferedImage out = format_image(images[i]);
            image_canvases[i] = new JLabel(new ImageIcon(out));
            panel.add(image_canvases[i]);
            panel.setVisible(true);
        }

        display.setVisible(true);

    }

    public BufferedImage format_image(double[] image)
    {
        BufferedImage img = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);

        for(int i=0;i<28;i++)
        {
            for(int j=0;j<28;j++)
            {
                int index = (i*28)+j;
                img.setRGB(j,i,(int)image[index]);
            }
        }
        return resizeImage(img,56,56);// doubles the image
    }
//https://www.baeldung.com/java-resize-image
// love for bro if works
    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
    {

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

}
