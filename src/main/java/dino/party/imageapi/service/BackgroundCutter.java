package dino.party.imageapi.service;

import marvin.color.MarvinColorModelConverter;
import marvin.image.MarvinImage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static marvinplugins.MarvinPluginCollection.alphaBoundary;

public class BackgroundCutter {

    public byte[] ChromaToTransparency(byte[] bImage, String format) throws IOException {
        MarvinImage image = new MarvinImage(ImageIO.read(new ByteArrayInputStream(bImage)));
        MarvinImage imageOut = new MarvinImage(image.getWidth(), image.getHeight());
        // Convert green to transparency
        greenToTransparency(image, imageOut);
        // Reduce extra green pixels
        reduceGreen(imageOut);
        // Smoothing angles
        alphaBoundary(imageOut, 7);
        imageOut.update();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imageOut.getBufferedImage(), format, baos);;
        return  baos.toByteArray();
    }

    private void greenToTransparency(MarvinImage imageIn, MarvinImage imageOut){
        for(int y=0; y<imageIn.getHeight(); y++){
            for(int x=0; x<imageIn.getWidth(); x++){

                int color = imageIn.getIntColor(x, y);

                double[] hsv = MarvinColorModelConverter.rgbToHsv(new int[]{color});

                if(hsv[0] >= 50 && hsv[0] <= 160 && hsv[1] >= 0.4 && hsv[2] >= 0.3){
                    imageOut.setIntColor(x, y, 0, 127, 127, 127);
                }
                else{
                    imageOut.setIntColor(x, y, color);
                }

            }
        }
    }

    private void reduceGreen(MarvinImage image){
        for(int y=0; y<image.getHeight(); y++){
            for(int x=0; x<image.getWidth(); x++){
                int r = image.getIntComponent0(x, y);
                int g = image.getIntComponent1(x, y);
                int b = image.getIntComponent2(x, y);
                int color = image.getIntColor(x, y);
                double[] hsv = MarvinColorModelConverter.rgbToHsv(new int[]{color});

                if(hsv[0] >= 60 && hsv[0] <= 160 && hsv[1] >= 0.15 && hsv[2] >= 0.15){
                    if((r*b) !=0 && (g*g) / (r*b) > 1.5){
                        image.setIntColor(x, y, 255, (int)(r*1.4), (int)g, (int)(b*1.4));
                    } else{
                        image.setIntColor(x, y, 255, (int)(r*1.2), g, (int)(b*1.2));
                    }
                }
            }
        }
    }

}