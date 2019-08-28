package org.light.collect.treemappingmobile.naxa.network;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("data")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
