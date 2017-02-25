/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author patil
 */
public class MyMessageHandler implements SOAPHandler<SOAPMessageContext> {
    
    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        Boolean outboundProperty = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
         if (outboundProperty.booleanValue()) { 
            SOAPMessage message = messageContext.getMessage(); 
            try { 
                SOAPEnvelope envelope = messageContext.getMessage().getSOAPPart().getEnvelope();
                if (envelope.getHeader() != null) {
                    envelope.getHeader().detachNode();
                }
                SOAPHeader header = envelope.addHeader();
                SOAPElement usernameToken=header.addChildElement("UsernameToken","wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                SOAPElement username=usernameToken.addChildElement("Username","wsse");
                username.addTextNode("Harsh Patil");
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } else {
            try {                
                //This handler does nothing with the response from the Web Service so
                //we just print out the SOAP message.
                SOAPMessage message = messageContext.getMessage();
                //message.writeTo(System.out);
                System.out.println(""); 
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
        }  
        return outboundProperty;
    }
    
    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    @Override
    public void close(MessageContext context) {
    }
    
}
