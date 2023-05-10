import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main2 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        File[] texts = new File("texts\\").listFiles();
        File file = texts[0];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                //System.out.println(stringBuilder.toString());
                int charCode = reader.read();
                if(charCode != 13){
                    //System.out.println("charCode is : " + charCode + " " + (char)charCode);
                    stringBuilder.append((char)charCode);
                }else {
                    //System.out.println("charCode is 13: " + charCode);
                    charCode = reader.read();
                    //System.out.println("charCode is 10: " + charCode);
                    charCode = reader.read();
                    //System.out.println("charCode is 32?: " + charCode + (char)charCode);
                    if(charCode == 32){
                        stringBuilder.append("\n");
                    }else {
                        stringBuilder.append((char)charCode);
                    }
                }
            }
            System.out.println(stringBuilder.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
//        Text text = new Text(file);
//        System.out.println(text.getTitle());
//        for (Content content : text.getContents()) {
//            System.out.println(content);
//        }

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String json = mapper.writeValueAsString(text);
//            mapper.writeValue(new File("outputs\\"+text.getTitle()+".json"), text);
//            System.out.println(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}