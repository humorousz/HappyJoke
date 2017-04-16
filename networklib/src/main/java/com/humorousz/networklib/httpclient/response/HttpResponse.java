package com.humorousz.networklib.httpclient.response;

/**
 * Created by zhangzhiquan on 2017/4/16.
 */

public class HttpResponse {
    private String body;
    private boolean error = false;

    HttpResponse(String body,boolean error){
        this.body = body;
        this.error = error;
    }
    public String getBody() {
        return body;
    }

    public boolean isError() {
        return error;
    }

    /**
     * Builder
     */
    public static class Builder{
        String body;
        boolean error;
        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setError(boolean error) {
            this.error = error;
            return this;
        }

        public HttpResponse build(){
            return new HttpResponse(this.body,this.error);
        }
    }
}
