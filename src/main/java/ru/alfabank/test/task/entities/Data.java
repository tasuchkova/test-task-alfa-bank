package ru.alfabank.test.task.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    @JsonProperty("embed_url")
    private String embedUrl;

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

}
