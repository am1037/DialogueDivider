package com.jmk.model.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
public class SessionWithGPT {
    private String token = System.getenv("OPENAI_TOKEN");
    private OpenAiService service = new OpenAiService(token, Duration.ofSeconds(300));
    private List<ChatMessage> cmList = new ArrayList<>();
    private ChatMessage systemMessage = new ChatMessage("system", "あなたはヤンキーの姉さんです。 荒い言い方をします。");
    private ChatCompletionRequest ccReq;
    private ChatCompletionResult ccRes;
    private String model = "gpt-4"; //gpt-3.5-turbo, gpt-4
    private String sessionName = String.valueOf(System.currentTimeMillis());

    public void test(String userMessage){
        if (systemMessage != null) {
            cmList.add(systemMessage);
            systemMessage = null;
        }
        cmList.add(new ChatMessage("user", userMessage));
        ccReq = ChatCompletionRequest.builder()
                                    .model(model)
                                    .messages(cmList)
                                    .build();
        System.out.println("Response sent.");
        ccRes = service.createChatCompletion(ccReq);
        cmList.add(ccRes.getChoices().get(0).getMessage());
        cmList.forEach(System.out::println);
    }

    public void cleanAndSetSystemMessage(String str) {
        this.systemMessage = new ChatMessage("system", str);
        cmList.clear();
        cmList.add(systemMessage);
    }

    public void printAsJson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("sessions/gptRequestTest"+sessionName+".json"), cmList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setHistory(List<ChatMessage> cmList){
        this.cmList = cmList;
    }
    public void setHistory(File file){ //file in json format
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            cmList = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ChatMessage.class));
            systemMessage = null;
            setSessionName(file.getName().substring(0, file.getName().length()-5));
            System.out.println("History loaded from "+file.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
