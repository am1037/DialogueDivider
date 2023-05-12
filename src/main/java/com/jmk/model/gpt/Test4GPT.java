package com.jmk.model.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmk.model.voicevox.VoicevoxAPI;

import java.io.File;
import java.util.Scanner;

public class Test4GPT {
    public static void main(String[] args) throws JsonProcessingException {
        SessionWithGPT session = new SessionWithGPT();
        VoicevoxAPI vvr = new VoicevoxAPI();

        Scanner sc = new Scanner(System.in);

//        while (true) {
//            session.test(sc.nextLine());
//            session.printAsJson();
//        }
        session.setHistory(new File("sessions/yankeesister.json"));
        session.getCmList().forEach(System.out::println);
        while (true) {
            session.sendMessage(sc.nextLine());
            vvr.sendAudioRequest(session.getLastReceivedMessage(), 47);
            vvr.sendSynthesisRequest(47, true, null, "test2.json");
            session.printAsJson();
        }
    }
}
