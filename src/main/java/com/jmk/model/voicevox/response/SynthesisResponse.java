package com.jmk.model.voicevox.response;

import lombok.Data;

import java.util.List;

@Data
public class SynthesisResponse {
    public static class Mora {
        private String text;
        private String consonant;
        private Double consonant_length;
        private String vowel;
        private Double vowel_length;
        private Double pitch;
    }

    public static class AccentPhrase {
        private List<Mora> moras;
        private Integer accent;
        private Object pause_mora;
        private Boolean is_interrogative;
    }

    private List<AccentPhrase> accent_phrases;
    private Integer speedScale;
    private Integer pitchScale;
    private Integer intonationScale;
    private Integer volumeScale;
    private Double prePhonemeLength;
    private Double postPhonemeLength;
    private Integer outputSamplingRate;
    private Boolean outputStereo;
    private String kana;

    private String audioFilePath;
}