/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author houyu
 */
public class Loader {
    
    public static Parser load(File xmlCourseFile) throws Exception {
        Parser list = new Parser();
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                Element element = null;
                boolean valueFlag = false;
                HashMap<String, String> attr = null;

                @Override
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    System.out.println("Start Element :" + qName);
                    element = new Element();
                    attr = new HashMap();
                    
                    //System.out.println(attributes.getLength());
                    for(int i = 0; i < attributes.getLength(); i++)
                    {
                        System.out.println(attributes.getLocalName(i));
                        System.out.println(attributes.getValue(i));
                        attr.put(attributes.getLocalName(i), attributes.getValue(i));
                    }
                    element.setTag(qName);
                    element.setAttr(attr);
                    valueFlag = true;   
                    list.addElement(element);
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException { 
                    if(element != null){
                        element = null;
                        valueFlag = false;
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {   
                    if(valueFlag){
                        if(element != null)
                        {          
                            element.setValue(new String(ch, start, length));
                            System.out.println(new String(ch, start, length));
                        }
                    }
                }

            };

            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
         }catch (IOException | ParserConfigurationException | SAXException e) {
            throw e;
        }
        
        return list;
    }
    
}
