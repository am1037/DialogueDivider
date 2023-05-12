package com.jmk.model.voicevox;

public class Test4Voicevox {
    public static void main(String[] args) {
        VoicevoxAPI vvr = new VoicevoxAPI();
        vvr.sendAudioRequest("おはよう", 47);
        vvr.getAudioQueryResponse().printJsonFile("test.json");
        vvr.sendSynthesisRequest(1, true, null, "test.json");
    }
}
