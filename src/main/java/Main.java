import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File[] texts = new File("texts\\").listFiles();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < texts.length; i++) {
            System.out.println(i + " " + texts[i].getName());
        }
        System.out.println("Enter the index of the file you want to read: ");
        int index = sc.nextInt();
        File file = texts[index];

        Text text = new Text(file, '『', '』');
        System.out.println(text.getTitle());
        for (Content content : text.getContents()) {
            System.out.println(content);
        }

        text.printJsonFile();
    }
}