package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

public class ImageUtil {
    public static BufferedImage getImage(String path, Object callingClass){
        BufferedImage image;
        try{
            image = ImageIO.read(Objects.requireNonNull(callingClass.getClass().getResourceAsStream("/player/playerFront01.png")));
            return image;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
