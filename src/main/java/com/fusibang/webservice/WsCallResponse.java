
package com.fusibang.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WsCallResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WsCallResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReturnStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CallList" type="{http://tempuri.org/}ArrayOfWsCallBox" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsCallResponse", propOrder = {
    "returnStatus",
    "message",
    "callList"
})
public class WsCallResponse {

    @XmlElement(name = "ReturnStatus")
    protected int returnStatus;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "CallList")
    protected ArrayOfWsCallBox callList;

    /**
     * 获取returnStatus属性的值。
     * 
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * 设置returnStatus属性的值。
     * 
     */
    public void setReturnStatus(int value) {
        this.returnStatus = value;
    }

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获取callList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWsCallBox }
     *     
     */
    public ArrayOfWsCallBox getCallList() {
        return callList;
    }

    /**
     * 设置callList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWsCallBox }
     *     
     */
    public void setCallList(ArrayOfWsCallBox value) {
        this.callList = value;
    }

}
