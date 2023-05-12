package com.jmk.model.voicevox.request;

import lombok.Data;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/*
지금은 대충 만들었지만
나중에 json으로도 받도록 수정해야?
20230512
 */


@Data
public class AudioQueryRequest {
    String text; //required, UTF-8로 인코딩 된 일본어 문장
    int speaker; //required, 47~50 너스쨩이 귀엽다
    String core_version;

    //추후 String이 아니라 connection 형식으로 수정??
    public String toRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append("audio_query");
        sb.append("?text=");
        sb.append(URLEncoder.encode(text, StandardCharsets.UTF_8));
        sb.append("&speaker=");
        sb.append(speaker);
        if (core_version != null) {
            sb.append("&core_version=");
            sb.append(core_version);
        }
        return sb.toString();
    }
}
