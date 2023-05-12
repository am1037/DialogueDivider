package com.jmk.model.voicevox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Test {
    public static void main(String[] args) {
        VoicevoxAPI vvr = new VoicevoxAPI();
        vvr.sendAudioRequest("おはよう", 47);
        vvr.getAudioQueryResponse().printJsonFile("test2.json");
        vvr.sendSynthesisRequest(1, true, null, "test2.json");
    }
}
