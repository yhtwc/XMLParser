/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.util.ArrayList;

/**
 *
 * @author houyu
 */
public class Parser {
    private ArrayList<Element> elements;
    
    public Parser() {
        elements = new ArrayList<>();
    }
    
    public ArrayList<Element> getElements() {
        return elements;
    }
    
    public void addElement(Element element) {
        elements.add(element);
    }
}
