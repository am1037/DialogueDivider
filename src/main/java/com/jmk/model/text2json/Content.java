package com.jmk.model.text2json;

public class Content {
    Text.Type type;
    String speaker = "ACTOR";
    String text;

    public Content(Text.Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Content(Text.Type type, String speaker, String text) {
        this.type = type;
        this.speaker = speaker;
        this.text = text;
    }

    @Override
    public String toString() {
        return "com.jmk.text2json.Content{" +
                "type=" + type +
                ", speaker='" + speaker + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public Text.Type getType() {
        return type;
    }

    public void setType(Text.Type type) {
        this.type = type;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
