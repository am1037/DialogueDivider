package com.jmk.model.voicevox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmk.model.voicevox.request.AudioQueryRequest;
import com.jmk.model.voicevox.request.SynthesisRequest;
import com.jmk.model.voicevox.response.AudioQueryResponse;
import com.jmk.model.voicevox.response.SynthesisResponse;
import lombok.Data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
기본적으로 Request를 보내고
Response를 받아주는 역할

일단은 파라미터 다 수동으로 주고
나중에 일관성 있게 수정하기
 */
@Data
public class VoicevoxAPI {
    ObjectMapper om = new ObjectMapper();

    String defaultJsonPath = "test.json";

    //Request
    URL url;
    HttpURLConnection connection;
    String host = "http://127.0.0.1:50021/";

    //Response
    int responseCode;
    AudioQueryResponse audioQueryResponse;
    SynthesisResponse synthesisResponse;

    //input : txt
    //output : json
    public void sendAudioRequest(String text, int speaker) {

        AudioQueryRequest audioQuery = new AudioQueryRequest();
        audioQuery.setText(text);
        audioQuery.setSpeaker(speaker);

        try {
            url = new URL(host+audioQuery.toRequest());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            // Get the response code
            responseCode = connection.getResponseCode();
            //System.out.println("Response Code: " + responseCode);

            // Read the response from the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder stringBuilder = new StringBuilder();
//            while ( reader.ready() ) {
//                stringBuilder.append(reader.readLine());
//            }
//            System.out.println(stringBuilder.toString());
            audioQueryResponse = om.readValue(reader, AudioQueryResponse.class);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //input : json
    //output : wav
    public void sendSynthesisRequest(int speaker, Boolean enable_interrogative_upspeak, String core_version, String jsonPath) {

        SynthesisRequest synthesisRequest = new SynthesisRequest();
        synthesisRequest.setSpeaker(speaker);
        synthesisRequest.setEnable_interrogative_upspeak(enable_interrogative_upspeak);
        synthesisRequest.setCore_version(core_version);
        if (jsonPath == null) {
            jsonPath = defaultJsonPath;
        }
        synthesisRequest.setJsonPath(jsonPath);

        try {
            url = new URL(host+synthesisRequest.toRequest());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            //파일을 읽는다
            String jsonContent = new String(Files.readAllBytes(Paths.get(synthesisRequest.getJsonPath())));

            //파일을 보낸다? 붙인다? 쓴다? 여하튼 post한다
            //여기서의 Output은 내가 서버로 보내는 것을 말한다
            //connection에서 output을 받아들이도록 허용한다
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonContent.getBytes());
            outputStream.flush();
            outputStream.close();

            // Get the response code
            responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Save the response to an audio file
            // Open a stream to the data
            InputStream inputStream = connection.getInputStream();

            // Create a path for the output file
            Path outputPath = Paths.get("audio"+System.currentTimeMillis()+".wav");

            // Use the Files class to copy the data from the InputStream to the file.
            Files.copy(inputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Audio file saved: "+outputPath.toString());

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
