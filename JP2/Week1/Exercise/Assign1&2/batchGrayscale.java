
/**
 * This is the source code of Week1 Assignment1 batchGrayscale.
 * 
 * @author liuyaqiu 
 * @version 2016-07-29
 */
import edu.duke.*;
import java.io.*;

public class batchGrayscale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void grayConvert() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            ImageResource newImage = makeGray(image);
            newImage.setFileName(newName);
            newImage.draw();
            newImage.save();
        }
    }
    
    public ImageResource makeNegative(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }
    
    public void negativeConvert() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "invert-" + fname;
            ImageResource newImage = makeNegative(image);
            newImage.setFileName(newName);
            newImage.draw();
            newImage.save();
        }
    }
}
