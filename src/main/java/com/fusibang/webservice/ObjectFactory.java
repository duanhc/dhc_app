
package com.fusibang.webservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fusibang.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fusibang.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendSms }
     * 
     */
    public SendSms createSendSms() {
        return new SendSms();
    }

    /**
     * Create an instance of {@link SendSmsResponse }
     * 
     */
    public SendSmsResponse createSendSmsResponse() {
        return new SendSmsResponse();
    }

    /**
     * Create an instance of {@link WsSendResponse }
     * 
     */
    public WsSendResponse createWsSendResponse() {
        return new WsSendResponse();
    }

    /**
     * Create an instance of {@link QueryOverage }
     * 
     */
    public QueryOverage createQueryOverage() {
        return new QueryOverage();
    }

    /**
     * Create an instance of {@link QueryOverageResponse }
     * 
     */
    public QueryOverageResponse createQueryOverageResponse() {
        return new QueryOverageResponse();
    }

    /**
     * Create an instance of {@link WsOverageResponse }
     * 
     */
    public WsOverageResponse createWsOverageResponse() {
        return new WsOverageResponse();
    }

    /**
     * Create an instance of {@link QueryStatus }
     * 
     */
    public QueryStatus createQueryStatus() {
        return new QueryStatus();
    }

    /**
     * Create an instance of {@link QueryStatusResponse }
     * 
     */
    public QueryStatusResponse createQueryStatusResponse() {
        return new QueryStatusResponse();
    }

    /**
     * Create an instance of {@link WsStatusResponse }
     * 
     */
    public WsStatusResponse createWsStatusResponse() {
        return new WsStatusResponse();
    }

    /**
     * Create an instance of {@link QueryCall }
     * 
     */
    public QueryCall createQueryCall() {
        return new QueryCall();
    }

    /**
     * Create an instance of {@link QueryCallResponse }
     * 
     */
    public QueryCallResponse createQueryCallResponse() {
        return new QueryCallResponse();
    }

    /**
     * Create an instance of {@link WsCallResponse }
     * 
     */
    public WsCallResponse createWsCallResponse() {
        return new WsCallResponse();
    }

    /**
     * Create an instance of {@link ArrayOfWsStatusBox }
     * 
     */
    public ArrayOfWsStatusBox createArrayOfWsStatusBox() {
        return new ArrayOfWsStatusBox();
    }

    /**
     * Create an instance of {@link WsStatusBox }
     * 
     */
    public WsStatusBox createWsStatusBox() {
        return new WsStatusBox();
    }

    /**
     * Create an instance of {@link ArrayOfWsCallBox }
     * 
     */
    public ArrayOfWsCallBox createArrayOfWsCallBox() {
        return new ArrayOfWsCallBox();
    }

    /**
     * Create an instance of {@link WsCallBox }
     * 
     */
    public WsCallBox createWsCallBox() {
        return new WsCallBox();
    }

}
