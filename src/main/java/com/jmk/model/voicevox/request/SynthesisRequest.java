package com.jmk.model.voicevox.request;

import lombok.Data;

import java.io.File;

@Data
public class SynthesisRequest {
    int speaker;
    boolean enable_interrogative_upspeak;
    String core_version;
    String jsonPath;

    public String toRequest(){
        StringBuilder sb = new StringBuilder();
        sb.append("synthesis");
        sb.append("?speaker=");
        sb.append(speaker);
        sb.append("&enable_interrogative_upspeak=");
        sb.append(enable_interrogative_upspeak);
        if(core_version != null){
            sb.append("&core_version=");
            sb.append(core_version);
        }
        return sb.toString();
    }
}
