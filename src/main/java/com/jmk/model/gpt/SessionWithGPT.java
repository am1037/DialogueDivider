package com.jmk.model.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SessionWithGPT {
    private String token = System.getenv("OPENAI_TOKEN");
    private OpenAiService service = new OpenAiService(token, Duration.ofSeconds(60));
    private List<ChatMessage> cmList = new ArrayList<>();
    private ChatMessage systemMessage = new ChatMessage("system", "あなたはお嬢さんAIです。 偉い人の言い方を使います。");
    private ChatCompletionRequest ccReq;
    private ChatCompletionResult ccRes;
    private String model = "gpt-3.5-turbo";
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
        ccRes = service.createChatCompletion(ccReq);
        cmList.add(ccRes.getChoices().get(0).getMessage());
        cmList.forEach(System.out::println);
    }

    public void cleanAndSetSystemMessage(String str) {
        ChatMessage nsm = new ChatMessage("system", str);
        this.systemMessage = nsm;
        cmList.clear();
        cmList.add(systemMessage);
    }

    public void printAsJson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("gptRequestTest"+sessionName+".json"), cmList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
