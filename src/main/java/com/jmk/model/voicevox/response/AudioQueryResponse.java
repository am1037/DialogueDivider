package com.jmk.model.voicevox.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class AudioQueryResponse {
    private List<AccentPhrase> accent_phrases;
    private float speedScale;
    private float pitchScale;
    private float intonationScale;
    private float volumeScale;
    private float prePhonemeLength;
    private float postPhonemeLength;
    private int outputSamplingRate;
    private boolean outputStereo;
    private String kana;

    public String toJsonString(){
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void printJsonFile(String path){
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(new File(path), this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

@Data
class AccentPhrase {
    private List<Mora> moras;
    private int accent;
    private Pause_mora pause_mora;
    @JsonProperty("is_interrogative")
    private boolean is_interrogative;
}

@Data
class Mora {
    private String text;
    private String consonant;
    private Float consonant_length;
    private String vowel;
    private Float vowel_length;
    private float pitch;
}

@Data
class Pause_mora {
    private String text;
    private String consonant;
    private Float consonant_length;
    private String vowel;
    private Float vowel_length;
    private Float pitch;
}