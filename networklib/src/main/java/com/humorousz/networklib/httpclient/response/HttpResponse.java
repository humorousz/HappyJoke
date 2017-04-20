package com.humorousz.networklib.httpclient.response;

/**
 * Created by zhangzhiquan on 2017/4/16.
 */

public class HttpResponse {
    private String body;
    private boolean error = false;
    private String requestUrl;

    HttpResponse(String body,boolean error,String requestUrl){
        this.body = body;
        this.error = error;
        this.requestUrl = requestUrl;
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
        String body;
        boolean error;
        String requestUrl;
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

        public HttpResponse build(){
            return new HttpResponse(this.body,this.error,this.requestUrl);
        }
    }
}
