package tek.tdd.utility;

public class DataGenerator {

    public static String genereteRandomEmail(String prefix) {
        int random = (int) (Math.random() * 100000);
        return prefix + random + "@gmail.com";
    }
}
