import edu.duke.*;

import java.io.File;

/* method to invert a image
    Write a program to create new images that are photographic negatives (or inverted images) of selected images
     *  In inverting an image, a pixel’s red, blue, and green components are modified to be the
     *  exact opposite within the 0 to 255 range. That is, if a pixel’s red, blue, and green values are (34, 198, 240),
     *  then that same pixel in the inverted image would have the red, blue and green values of (221, 57, 15).
     *  Note that 255 - 34 is 221, 255 - 198 is 57, and 255 - 240 is 15.
 */
public class ImageInversion {

    public ImageResource inverImage(ImageResource inImage) {

        ImageResource invertedImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel : invertedImage.pixels()) {

            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            pixel.setRed(255-inPixel.getRed() );
            pixel.setBlue(255-inPixel.getBlue());
            pixel.setGreen(255-inPixel.getGreen());

        }

        return invertedImage;
    }

//Save these new images with filenames that are related to the original images,
// such as adding “inverted-” in front of the old filename.

    public void selectMultipleFilesToInvertAndSave() {

        DirectoryResource directoryResource = new DirectoryResource();

        for (File file : directoryResource.selectedFiles()) {

            ImageResource image = new ImageResource(file);
            ImageResource gray = inverImage(image);

            String fileName = image.getFileName();
            String newFileName = "gray-" + fileName;

            gray.setFileName(newFileName);

            gray.save();

        }

    }

    public static void main(String args[])
    {
        ImageInversion image=new ImageInversion();
        image.selectMultipleFilesToInvertAndSave();
    }


}
