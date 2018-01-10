package edu.pw.platon.annonymous.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Response {

    @JsonIgnore
    public static final String SUCCESS_MESSAGE = "Success";

    protected String description;
}
