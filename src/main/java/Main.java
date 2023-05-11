import com.jmk.model.text2json.Content;
import com.jmk.model.text2json.Text;

import java.io.File;
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
        System.out.println("Enter opener and closer: ");
        char dopen = '"';
        char dclose = '"';
        try{
            dopen = sc.next().charAt(0);
            dclose = sc.next().charAt(0);
        }
        catch (Exception e){
            System.out.println("No opener and closer entered. Using default.");
        }

        Text text = new Text(file, dopen, dclose);
        System.out.println(text.getTitle());
        for (Content content : text.getContents()) {
            System.out.println(content);
        }

        text.printJsonFile();
    }
}