/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculationWS;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.NodeList;

/**
 *
 * @author patil
 */
public class NewMessageHandler implements SOAPHandler<SOAPMessageContext> {
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        String outProperty = SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY;
        boolean outgoing = (Boolean) messageContext.get(outProperty);
        SOAPMessage msg  = messageContext.getMessage();
        String username = "";
        try {
            if (outgoing)
                msg.writeTo(new FileOutputStream("E:\\OUTPUT\\responseMessage.txt"));
            else {
                SOAPHeader header = msg.getSOAPHeader();
                Iterator it = header.examineAllHeaderElements();
                while (it.hasNext()) {
                    SOAPHeaderElement e = (SOAPHeaderElement)it.next();
                    NodeList nl = e.getElementsByTagName("wsse:Username");
                    for (int i = 0; i < nl.getLength(); i++) {
                        username += nl.item(i).getTextContent();
                    }
//                    SOAPBody body = msg.getSOAPBody();
//                    String operationName = body.getChildNodes().item(0).getLocalName();
//                    System.out.println(operationName);
                    
                }
                FileOutputStream f = new FileOutputStream("E:\\OUTPUT\\username.txt");
                byte data[] = username.getBytes();
                f.write(data);
                msg.writeTo(new FileOutputStream("E:\\OUTPUT\\inputMessage.txt"));
            }
        } catch (IOException e) {
            System.out.println("IO  Error!!!!");
            throw new RuntimeException(e);
        } catch (SOAPException e) {
            System.out.println("SOAP IO Error!!!!");
            throw new RuntimeException(e);
        } 
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
