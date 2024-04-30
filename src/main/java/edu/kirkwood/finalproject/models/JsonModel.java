package edu.kirkwood.finalproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonModel {
    @JsonProperty("email")
    public String email;

    @JsonProperty("autocorrect")
    public String autocorrect;
    @JsonProperty("deliverability")
    public String deliverability;
    @JsonProperty("quality_score")
    public String quality_score;
}
