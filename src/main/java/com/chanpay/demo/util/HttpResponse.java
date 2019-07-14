//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chanpay.demo.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

public class HttpResponse {
    private Header[] responseHeaders;
    private String stringResult;
    private byte[] byteResult;

    public HttpResponse() {
    }

    public Header[] getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public byte[] getByteResult() {
        return this.byteResult != null?this.byteResult:(this.stringResult != null?this.stringResult.getBytes():null);
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() throws UnsupportedEncodingException {
        return this.stringResult != null?this.stringResult:(this.byteResult != null?new String(this.byteResult, "UTF-8"):null);
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }
}
