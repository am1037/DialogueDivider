import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        BufferedReader reader = null;
        File[] texts = new File("texts\\").listFiles();
        try {
            map.put("Title", texts[0].getName());
            reader = new BufferedReader(new FileReader(texts[0]));
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c=reader.read()) != -1) {
                sb.append((char) c);
            }
            map.put("Content", sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}