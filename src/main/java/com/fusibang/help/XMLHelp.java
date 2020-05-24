package com.fusibang.help;

import com.alibaba.fastjson.JSONObject;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @description: xml解析
 * @author: Alex
 * @date: 2020-05-24 13:08
 */
public class XMLHelp {

    /**
     * 用Element方式
     * @param list
     */
    public static void element(NodeList list){
        for (int i = 0; i <list.getLength() ; i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int j = 0; j <childNodes.getLength() ; j++) {
                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
                    //获取节点
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    //获取节点值
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
    }

    /**
     * 用Node方式
     * @param list
     */
    public static JSONObject node(NodeList list){
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i <list.getLength() ; i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j <childNodes.getLength() ; j++) {
                if (childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
                    jsonObject.put(childNodes.item(j).getNodeName(),childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        //1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document d = builder.parse("src/main/resources/applicationContext.xml");
            Document d = builder.parse(XMLHelp.class.getResourceAsStream("/applicationContext.xml"));
            NodeList sList = d.getElementsByTagName("bean");
            //element(sList);
            node(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}