import java.util.Random;

public class Utility {
    //Generate a integer between min and max inclusive
    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
