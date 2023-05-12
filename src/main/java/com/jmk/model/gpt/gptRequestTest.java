package com.jmk.model.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.util.Scanner;

public class gptRequestTest {
    public static void main(String[] args) throws JsonProcessingException {
        SessionWithGPT session = new SessionWithGPT();
        Scanner sc = new Scanner(System.in);

//        while (true) {
//            session.test(sc.nextLine());
//            session.printAsJson();
//        }
        session.setHistory(new File("sessions/yankeesister.json"));
        session.getCmList().forEach(System.out::println);
        while (true) {
            session.test(sc.nextLine());
            session.printAsJson();
        }
    }
}
