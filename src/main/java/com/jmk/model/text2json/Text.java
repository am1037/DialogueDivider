package com.jmk.model.text2json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Text {
    String title;
    List<Content> contents = new ArrayList<>();
    int dOpen = '“';
    int dClose = '”';

    public void setdOpen(int dOpen) {
        this.dOpen = dOpen;
    }

    public void setdClose(int dClose) {
        this.dClose = dClose;
    }

    public enum Type {
        NARRATION, DIALOGUE, crlf
    }

    public Text() {
    }

    public Text(File file) {
        setByFile(file);
    }

    public Text(File file, int dOpen, int dClose) {
        this.dOpen = dOpen;
        this.dClose = dClose;
        setByFile(file);
    }

    public void setByFile(File file){
            BufferedReader reader = null;
            Text text = this;
            try {
                String nameWithoutExtension = file.getName();
                int extensionIndex = nameWithoutExtension.lastIndexOf(".");
                if (extensionIndex != -1) {
                    nameWithoutExtension = nameWithoutExtension.substring(0, extensionIndex);
                }
                text.setTitle(nameWithoutExtension);
                reader = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c=reader.read()) != -1) {
                    if (c == dOpen) {
                        String temp = sb.toString().trim();
                        if (temp.length()>0) {
                            String[] strs = temp.split("\n");
                            for (String str : strs) {
                                if(str.trim().length()>0) {
                                    text.addContent(Text.Type.NARRATION, "NARRATOR", str.trim());
                                }else {
                                    text.addContent(Type.crlf, "");
                                }
                            }
                        }
                        sb = new StringBuilder();
                        //sb.append((char) c);
                        while ((c=reader.read()) != dClose) {
                            //System.out.println((char) c+ " " + c);
                            sb.append((char) c);
                        }
                        //sb.append((char) c);
                        text.addContent(Text.Type.DIALOGUE, sb.toString().trim());
                        sb = new StringBuilder();
                    }else {
                        sb.append((char) c);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Content> getContents() {
        return contents;
    }
    public void addContent(Type tt, String str) {
        if (tt == Type.crlf) this.contents.add(new Content(tt, "", str));
        else this.contents.add(new Content(tt,  str));
    }

    public void addContent(Type tt, String sp, String str) {
        this.contents.add(new Content(tt, sp, str));
    }

    @Override
    public String toString() {
        return "com.jmk.text2json.Text{" +
                "title='" + title + '\'' +
                ", contents=" + contents +
                '}';
    }

    public void printJsonFile(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(this);
            mapper.writeValue(new File("outputs\\"+this.getTitle()+".json"), this);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

