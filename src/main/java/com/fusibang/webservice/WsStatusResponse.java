
package com.fusibang.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WsStatusResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WsStatusResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReturnStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StatusList" type="{http://tempuri.org/}ArrayOfWsStatusBox" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsStatusResponse", propOrder = {
    "returnStatus",
    "message",
    "statusList"
})
public class WsStatusResponse {

    @XmlElement(name = "ReturnStatus")
    protected int returnStatus;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "StatusList")
    protected ArrayOfWsStatusBox statusList;

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
     * 获取statusList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWsStatusBox }
     *     
     */
    public ArrayOfWsStatusBox getStatusList() {
        return statusList;
    }

    /**
     * 设置statusList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWsStatusBox }
     *     
     */
    public void setStatusList(ArrayOfWsStatusBox value) {
        this.statusList = value;
    }

}
