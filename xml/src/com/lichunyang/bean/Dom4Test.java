package com.lichunyang.bean;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;


import java.math.BigDecimal;
import java.util.List;

public class Dom4Test {
    @Test
    public void test() throws DocumentException {
        SAXReader sax = new SAXReader();
        Document document = sax.read("./xml/books.xml");
        System.out.println(document);
    }
    @Test
    public void test2() throws DocumentException {
        SAXReader sax = new SAXReader();
        Document document = sax.read("xml/books.xml");

        Element rootElement = document.getRootElement();

        List<Element> books = rootElement.elements("book");
//        books.forEach(book-> System.out.println(book.asXML()));
        for(Element book:books){
            Element nameElement = book.element("name");
            String name = nameElement.getText();
            String price = book.elementText("price");
            String author = book.elementText("author");
            String sn = book.attributeValue("sn");
            System.out.println(new Book(sn,name, BigDecimal.valueOf(Double.parseDouble(price)),author));
        }

    }

}
