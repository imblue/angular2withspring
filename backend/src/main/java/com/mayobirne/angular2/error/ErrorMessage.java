package com.mayobirne.angular2.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by Christian Schuhmacher  on 28.04.2016.
 */
public class ErrorMessage implements Serializable {

    public ErrorMessage(Integer status, int errorPrefix, int errorId, String message, String developerMessage, Throwable ex) {
        this.errorCode = addPrefix(errorId, errorPrefix);
        this.status = status;
        this.message = message;
        this.developerMessage = developerMessage;
        if (ex != null) this.stackTrace = ex.getMessage();
        this.moreInfo = "There will be more Info soon.."; // TODO
    }

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("developerMessage")
    private String developerMessage;

    @JsonProperty("stackTrace")
    private String stackTrace;

    @JsonProperty("moreInfo")
    private String moreInfo;


    private static String addPrefix(int id, int prefix) {
        DecimalFormat myFormatter = new DecimalFormat("0000");
        String output = myFormatter.format(id);
        return prefix + output;
    }
}
