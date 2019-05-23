package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/* A basic Image class written to manipulate images.
The class is a kind of wrapper over the BufferdImage class.
*/

public class Image {

    /*
    The class holds a refference to a bufferd image object
    */
    BufferedImage img;

    /*
    A constructor to initialize the img object.
    The constructor takes the path to the image and reads it using ImageIO
    */
    public Image(String path) {
        try {
            img = ImageIO.read(new File(path));
            System.out.println("Image read successfully in sample.Image");
        } catch(IOException e) {
            System.out.println("Cannot read image");
        }
    }

    /*
    A simple constructor that intialises img with null. Was written for debugging purposes.
    */
    public Image() {
        img = null;
    }

    /*
    A method to get the height, using the getHeight method implemented in BufferedImage
    */
    private int height() {
        return img.getHeight();
    }

    /*
    A method to get the width, using the getWidth method implemented in BufferdImage
    */
    private int width() {
        return img.getWidth();
    }

    /*
    A method to create the transpose of an image, that is an image T,
    having the pixel at position (i,j) equal to the pixel (j,i) of the original image.
    This method will be used in the rotation algorithm.
    It uses the getRGB(int, int) method of the BufferedImage class
    */
    private Image transpose() {
        int height = height(), width = width();
        BufferedImage t = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                //System.out.println(i+" "+j);
                int color  = img.getRGB(i,j);
                t.setRGB(j,i,color);
            }
        }
        img = t;
        return this;
    }
    /*
    Rotates the image to the right with 90 degrees. The algorithm is the following:
    1. Let A be the original image, with dimensions NxM.
    2. Transpose the original image A.
    3. Swap first column with column M, second column with column M - 1, and so on
    This provides an in place rotation of the image, not needing any additional memory.
    Complexity: time O(N*M), space O(N*M)
    */
    private Image rotateRight90() {
        transpose();
        int height = height(), width = width();
        for(int i = 0; i < width/2; i++) {

            for (int j = 0; j < height; j++) {
                int color1 = img.getRGB(i, j);
                int color2 = img.getRGB(width - i - 1, j);
                img.setRGB(i, j, color2);
                img.setRGB(width - i - 1, j, color1);
            }
        }
        return this;
    }

    /*
    Rotates the image to the right with 90 degrees. The algorithm is the following:
    1. Let A be the original image, with dimensions NxM.
    2. Transpose the original image A.
    3. Swap first line with line N, second line with line N - 1, and so on
    This provides an in place rotation of the image, not needing any additional memory.
    Complexity: time O(N*M), space O(N*M)
    */
    private Image rotateLeft90() {
        transpose();
        int height = height(), width = width();
        for(int j = 0; j < height/2; j++) {

            for(int i = 0; i < width; i++) {
                int color1 = img.getRGB(i,j);
                int color2 = img.getRGB(i,height-j-1);
                img.setRGB(i,j,color2);
                img.setRGB(i,height-j-1,color1);
            }
        }
        return this;
    }

    /*
    To rotate an image to left with 180 degrees, do two 90 degrees rotation to the left.
    */
    private Image rotateLeft180() {
        rotateLeft90();
        rotateLeft90();
        return this;
    }
    /*
    To rotate an image to right with 180 degrees, do two 90 degrees rotation to the right.
    */
    private Image rotateRight180() {
        rotateRight90();
        rotateRight90();
        return this;
    }

    /*
    Method to save the rotated image in a new filename
    */
    public void save(String filename) {
        File out = new File(filename);
        try {
            ImageIO.write(img, "bmp", out);
            System.out.println("Image saved successfully");
        } catch(IOException e) {
            System.out.println("image cannot be saved");
        }
    }

    /*
    This method hides functionalites from the user.
    It recieves an integer and does one of the four operations available.
    */
    public void rotate(int userOption) {
        switch(userOption) {
            case 1: this.rotateLeft90();
                    break;
            case 2: this.rotateLeft180();
                    break;
            case 3: this.rotateRight90();
                    break;
            case 4: this.rotateLeft180();
                    break;
        }
        System.out.println("Image rotated successfully.");
    }
}
