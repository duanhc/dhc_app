
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
 *         &lt;element name="QueryStatusResult" type="{http://tempuri.org/}WsStatusResponse" minOccurs="0"/&gt;
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
    "queryStatusResult"
})
@XmlRootElement(name = "QueryStatusResponse")
public class QueryStatusResponse {

    @XmlElement(name = "QueryStatusResult")
    protected WsStatusResponse queryStatusResult;

    /**
     * 获取queryStatusResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WsStatusResponse }
     *     
     */
    public WsStatusResponse getQueryStatusResult() {
        return queryStatusResult;
    }

    /**
     * 设置queryStatusResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WsStatusResponse }
     *     
     */
    public void setQueryStatusResult(WsStatusResponse value) {
        this.queryStatusResult = value;
    }

}
