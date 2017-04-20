package com.humorousz.networklib.httpclient.response;

/**
 * Created by zhangzhiquan on 2017/4/16.
 */

public class HttpResponse {
    private int code;
    private String message;
    private String body;
    private boolean error = false;
    private String requestUrl;

    HttpResponse(Builder builder){
        this.code = builder.code;
        this.message = builder.message;
        this.body = builder.body;
        this.error = builder.error;
        this.requestUrl = builder.requestUrl;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getBody() {
        return body;
    }

    public boolean isError() {
        return error;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * Builder
     */
    public static class Builder{
        int code = -1;
        String message ="";
        String body="";
        boolean error=false;
        String requestUrl="";

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setError(boolean error) {
            this.error = error;
            return this;
        }

        public Builder setRequestUrl(String requestUrl) {
            this.requestUrl = requestUrl;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }
    }
}
