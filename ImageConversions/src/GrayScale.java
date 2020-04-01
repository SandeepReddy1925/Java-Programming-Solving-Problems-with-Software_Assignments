import edu.duke.*;

import java.io.File;

//method to convert image to grayscale

public class GrayScale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);

        }

        return outImage;
    }

    /*
     * Selecting Multiple Images and converting to GrayScale and saving them with
     * a new file name
     */

    public void selectMultipleFilesToConvertAndSave() {

        DirectoryResource directoryResource = new DirectoryResource();

        for (File file : directoryResource.selectedFiles()) {

            ImageResource image = new ImageResource(file);
            ImageResource gray = makeGray(image);

            String fileName = image.getFileName();
            String newFileName = "gray-" + fileName;

            gray.setFileName(newFileName);

            gray.save();

        }
    }


    public static void main(String args[])
    {
        GrayScale grayimage=new GrayScale();

        grayimage.selectMultipleFilesToConvertAndSave();
    }
}
