
package com.fusibang.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SendSmsResult" type="{http://tempuri.org/}WsSendResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendSmsResult"
})
@XmlRootElement(name = "SendSmsResponse")
public class SendSmsResponse {

    @XmlElement(name = "SendSmsResult")
    protected WsSendResponse sendSmsResult;

    /**
     * 获取sendSmsResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WsSendResponse }
     *     
     */
    public WsSendResponse getSendSmsResult() {
        return sendSmsResult;
    }

    /**
     * 设置sendSmsResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WsSendResponse }
     *     
     */
    public void setSendSmsResult(WsSendResponse value) {
        this.sendSmsResult = value;
    }

}
