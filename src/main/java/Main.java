import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File[] texts = new File("texts\\").listFiles();
        File file = texts[0];

        Text text = new Text(file);
        System.out.println(text.getTitle());
        for (Content content : text.getContents()) {
            System.out.println(content);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(text);
            mapper.writeValue(new File("outputs\\"+text.getTitle()+".json"), text);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}