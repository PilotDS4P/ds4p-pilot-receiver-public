/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.samhsa.ds4ppilot.ws.client;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Duane DeCouteau
 */
public class XUAAssertionHandler implements SOAPHandler<SOAPMessageContext> {
    private static final String WSSE_NAMESPACE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private Element xuaAssertion;
    
    public XUAAssertionHandler(Element xuaAssertion) {
        this.xuaAssertion = xuaAssertion;
    }
    
    @Override
    public Set<QName> getHeaders() {
         return Collections.singleton(new QName(WSSE_NAMESPACE, "Security"));
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
                handleOutbound(context);
        }
        catch (SOAPException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
        return;
    }
    
    protected void handleOutbound(SOAPMessageContext ctx) throws SOAPException{
        //Element assertion = (Element) ctx.getMessage().getProperty("xuaAssertion");
        if(xuaAssertion == null)
            return;
        QName wsSecHeader = new QName(WSSE_NAMESPACE, "Security");
        SOAPHeaderElement elem = ctx.getMessage().getSOAPHeader().addHeaderElement(wsSecHeader);
        Node xuaNode = elem.getOwnerDocument().importNode(xuaAssertion, true);
        //elem.getOwnerDocument().adoptNode(xuaAssertion);
        elem.appendChild(xuaNode);
        dumpNodeToString(elem);
    }
    
   protected void handleInbound(SOAPMessageContext ctx) throws SOAPException{
        
    }    
   
    private void dumpNodeToString(Node node) {
        
        try {
            DOMSource domSource = new DOMSource(node);
            StreamResult result = new StreamResult(new StringWriter());
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer xform = null;
            xform = tf.newTransformer();
            xform.transform(domSource, result);
            String res = result.getWriter().toString();
            System.out.println("AND THE ELEMENT IS: "+res);
        }
        catch (Exception ex) {
            System.err.println("SAMLCallBackHandler:dumpNodeToString "+ex.getMessage());
        }
        
    }
   
    
}
