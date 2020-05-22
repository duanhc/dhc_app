
package com.fusibang.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WsSendResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WsSendResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReturnStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RemainPoint" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="TaskID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="SuccessCounts" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WsSendResponse", propOrder = {
    "returnStatus",
    "message",
    "remainPoint",
    "taskID",
    "successCounts"
})
public class WsSendResponse {

    @XmlElement(name = "ReturnStatus")
    protected String returnStatus;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "RemainPoint")
    protected int remainPoint;
    @XmlElement(name = "TaskID")
    protected int taskID;
    @XmlElement(name = "SuccessCounts")
    protected int successCounts;

    /**
     * 获取returnStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnStatus() {
        return returnStatus;
    }

    /**
     * 设置returnStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnStatus(String value) {
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
     * 获取remainPoint属性的值。
     * 
     */
    public int getRemainPoint() {
        return remainPoint;
    }

    /**
     * 设置remainPoint属性的值。
     * 
     */
    public void setRemainPoint(int value) {
        this.remainPoint = value;
    }

    /**
     * 获取taskID属性的值。
     * 
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * 设置taskID属性的值。
     * 
     */
    public void setTaskID(int value) {
        this.taskID = value;
    }

    /**
     * 获取successCounts属性的值。
     * 
     */
    public int getSuccessCounts() {
        return successCounts;
    }

    /**
     * 设置successCounts属性的值。
     * 
     */
    public void setSuccessCounts(int value) {
        this.successCounts = value;
    }

}
