import com.jmk.model.gpt.SessionWithGPT;
import com.jmk.model.voicevox.VoicevoxAPI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) throws Exception{
        SessionWithGPT session = new SessionWithGPT();
        VoicevoxAPI vvr = new VoicevoxAPI();

        //session.cleanAndSetSystemMessage("あなたは生意気なメイドAIです。 主人を少し馬鹿にしています。");
        //session.setSessionName("namaiki");
        session.setHistory(new File("sessions/namaiki.json"));
        Scanner sc = new Scanner(System.in);
        String str;
        str = sc.nextLine();
        while (!str.equals("exit")) {
            session.sendMessage(str);
            session.printAsJson();
            vvr.sendAudioRequest(session.getLastReceivedMessage(), 47);
            vvr.getAudioQueryResponse().printJsonFile("test.json");
            vvr.sendSynthesisRequest(47, true, null, "test.json");
            str = sc.nextLine();
        }

//        vvr.sendAudioRequest("おはよう。テストです。", 47);
//        vvr.getAudioQueryResponse().printJsonFile("test.json");
//        vvr.sendSynthesisRequest(47, true, null, "test.json");
    }
}
