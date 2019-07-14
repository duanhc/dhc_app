//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chanpay.demo.util;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

public class HttpProtocolHandler {
    private static String DEFAULT_CHARSET = "UTF-8";
    private int defaultConnectionTimeout = 8000;
    private int defaultSoTimeout = 30000;
    private int defaultIdleConnTimeout = '\uea60';
    private int defaultMaxConnPerHost = 30;
    private int defaultMaxTotalConn = 80;
    private static final long defaultHttpConnectionManagerTimeout = 3000L;
    private HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
    private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();

    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }

    private HttpProtocolHandler() {
        this.connectionManager.getParams().setDefaultMaxConnectionsPerHost(this.defaultMaxConnPerHost);
        this.connectionManager.getParams().setMaxTotalConnections(this.defaultMaxTotalConn);
        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(this.connectionManager);
        ict.setConnectionTimeout((long)this.defaultIdleConnTimeout);
        ict.start();
    }

    public HttpResponse execute(HttpRequest request, String strParaFileName, String strFilePath) throws HttpException, IOException {
        HttpClient httpclient = new HttpClient(this.connectionManager);
        int connectionTimeout = this.defaultConnectionTimeout;
        if(request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }

        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        int soTimeout = this.defaultSoTimeout;
        if(request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }

        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
        httpclient.getParams().setConnectionManagerTimeout(3000L);
        String charset = request.getCharset();
        charset = charset == null?DEFAULT_CHARSET:charset;
        Object method = null;
        if(request.getMethod().equals("GET")) {
            method = new GetMethod(request.getUrl());
            ((HttpMethod)method).getParams().setCredentialCharset(charset);
            ((HttpMethod)method).setQueryString(request.getQueryString());
        } else if(("".equals(strParaFileName) || strParaFileName == null) && ("".equals(strFilePath) || strFilePath == null)) {
            method = new PostMethod(request.getUrl());
            ((PostMethod)method).addParameters(request.getParameters());
            ((HttpMethod)method).addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
            ((HttpMethod)method).addRequestHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.0.3; zh-CN; HTC T328w Build/IML74K) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/9.7.6.428 U3/0.8.0 Mobile Safari/533.1");
        } else {
            method = new PostMethod(request.getUrl());
            ArrayList response = new ArrayList();

            for(int ex = 0; ex < request.getParameters().length; ++ex) {
                response.add(new StringPart(request.getParameters()[ex].getName(), request.getParameters()[ex].getValue(), charset));
            }

            response.add(new FilePart(strParaFileName, new FilePartSource(new File(strFilePath))));
            ((PostMethod)method).setRequestEntity(new MultipartRequestEntity((Part[])response.toArray(new Part[0]), new HttpMethodParams()));
        }

        ((HttpMethod)method).addRequestHeader("User-Agent", "Mozilla/4.0");
        HttpResponse var20 = new HttpResponse();

        try {
            httpclient.executeMethod((HttpMethod)method);
            if(request.getResultType().equals(HttpResultType.STRING)) {
                var20.setStringResult(((HttpMethod)method).getResponseBodyAsString());
            } else if(request.getResultType().equals(HttpResultType.BYTES)) {
                var20.setByteResult(((HttpMethod)method).getResponseBody());
            }

            var20.setResponseHeaders(((HttpMethod)method).getResponseHeaders());
            return var20;
        } catch (UnknownHostException var16) {
            ;
        } catch (IOException var17) {
            return null;
        } catch (Exception var18) {
            return null;
        } finally {
            ((HttpMethod)method).releaseConnection();
        }

        return null;
    }

    public String toString(NameValuePair[] nameValues) {
        if(nameValues != null && nameValues.length != 0) {
            StringBuffer buffer = new StringBuffer();

            for(int i = 0; i < nameValues.length; ++i) {
                NameValuePair nameValue = nameValues[i];
                if(i == 0) {
                    buffer.append(nameValue.getName() + "=" + nameValue.getValue());
                } else {
                    buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
                }
            }

            return buffer.toString();
        } else {
            return "null";
        }
    }
}
