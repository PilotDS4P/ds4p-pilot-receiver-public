/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.samhsa.ds4ppilot.ws.client;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Element;
import com.sun.xml.wss.saml.Assertion;
import com.sun.xml.wss.saml.Conditions;
import com.sun.xml.wss.saml.NameID;
import com.sun.xml.wss.saml.SAMLAssertionFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

/**
 *
 * @author Duane DeCouteau
 */
public class SAMLAssertionHelper {
    //public static final String senderVouchesConfirmation_saml20 = "urn:oasis:names:tc:SAML:2.0:cm:sender-vouches";
    private static final String holderOfKeyConfirmation_saml20 = "urn:oasis:names:tc:SAML:2.0:cm:sender-vouches";
    
    private static final String nameIdFormat = "urn:oasis:names:tc:SAML:2.0:nameid-format:X509SubjectName";
    
    private static final String SAML_2_NS = "urn:oasis:names:tc:SAML:2.0:assertion";
    private static final String XML_ENC_NS = "http://www.w3.org/2001/04/xmlenc#";
    private static final String XML_EXC_NS = "http://www.w3.org/2001/10/xml-exc-c14n#";
    private static final String XS_NS = "http://www.w3.org/2001/XMLSchema"; 
    private static final String DS_NS = "http://www.w3.org/2000/09/xmldsig#";
    private static final String VERSION = "2.0";
    
    public Element createSAMLAssertion() {
        try {
            Assertion assertion = null;
            // create the assertion id
            String aID = GSAMLTokenConstants.getMESSAGE_ID();                       
            
            Calendar bCal = Calendar.getInstance();
            bCal.add(Calendar.DATE, -1);
            Calendar aCal = Calendar.getInstance();
            aCal.add(Calendar.DATE, +5);
            
            GregorianCalendar c = new GregorianCalendar();
            long beforeTime = bCal.getTimeInMillis();
            // roll the time by one hour
            //long offsetHours = 60*60*1000;
            c.setTimeInMillis(beforeTime);
            GregorianCalendar before= (GregorianCalendar)c.clone();
            
            c = new GregorianCalendar();
            long afterTime = aCal.getTimeInMillis();
            c.setTimeInMillis(afterTime);
            GregorianCalendar after = (GregorianCalendar)c.clone();
            
            GregorianCalendar issueInstant = new GregorianCalendar();
            

            
            // statements
            List statements = new LinkedList();

            SAMLAssertionFactory factory = SAMLAssertionFactory.newInstance(SAMLAssertionFactory.SAML2_0);
            
            String compliantName = "CN="+ GSAMLTokenConstants.getUSER_NAME()+","+
                                   "OU="+ GSAMLTokenConstants.getUSER_ORGANIZATION_UNIT() +","+
                                   "O="+ GSAMLTokenConstants.getUSER_ORGANIZATION() +","+
                                   "L="+ GSAMLTokenConstants.getUSER_CITY() +","+
                                   "ST="+ GSAMLTokenConstants.getUSER_STATE() +","+
                                   "C="+ GSAMLTokenConstants.getUSER_COUNTRY(); 
            
            System.out.println("Compliant NAME "+compliantName);
            

            NameID nmId = factory.createNameID(compliantName, null, nameIdFormat);
            
            
            
            List attributes = getAttributeList(factory);
            
            statements.add(factory.createAttributeStatement(attributes));

            
            Conditions conditions = factory.createConditions(before, after, null, null, null, null);

            
            assertion = factory.createAssertion(aID, nmId, issueInstant, conditions, null, null, statements);  
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            
 
            return assertion.toElement(doc);
            
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
            
        }
        
    }

    private static List getAttributeList(SAMLAssertionFactory factory) {
            List attributes = new LinkedList();

            List attributeValues = new LinkedList();

            try {
                //add subject id
                attributeValues = new LinkedList();
                attributeValues.add(GSAMLTokenConstants.getSUBJECT_ID());
                attributes.add(factory.createAttribute(DS4PConstants.SUBJECT_ID_NS, attributeValues));


                //add purpose of use
                
                attributeValues = new LinkedList();
                attributeValues.add(getPOUElement());
                attributes.add(factory.createAttribute(DS4PConstants.SUBJECT_PURPOSE_OF_USE_NS, attributeValues));
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("####### Attributes Created");
            return attributes;
    }
    
    private static Element getPOUElement() {
        Element mRoot = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            mRoot = doc.createElement("myroot");
            
            doc.appendChild(mRoot);
            
            Element pou = doc.createElementNS("urn:hl7-org:v3", "PurposeOfUse");
            mRoot.appendChild(pou);
            
            Attr attr = doc.createAttribute("code");
            attr.setValue(GSAMLTokenConstants.getSUBJECT_PURPOSE_OF_USE());
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("codeSystem");
            attr.setValue("2.16.840.1.113883.5.8");
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("codeSystemName");
            attr.setValue("hl7-org:v3");
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("displayName");
            attr.setValue("Treatment");
            pou.setAttributeNode(attr);  
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return mRoot;
    }
    
    public Element createSAML2Assertion() {
        Element el = null;
        try {
            
            Calendar bCal = Calendar.getInstance();
            bCal.add(Calendar.DATE, -1);
            Calendar aCal = Calendar.getInstance();
            aCal.add(Calendar.DATE, +5);
            
            Date before = bCal.getTime();
            Date after = aCal.getTime();
            
            Date issueInstant = new Date();
            
            
            String compliantName = "CN="+ GSAMLTokenConstants.getUSER_NAME()+","+
                                   "OU="+ GSAMLTokenConstants.getUSER_ORGANIZATION_UNIT() +","+
                                   "O="+ GSAMLTokenConstants.getUSER_ORGANIZATION() +","+
                                   "L="+ GSAMLTokenConstants.getUSER_CITY() +","+
                                   "ST="+ GSAMLTokenConstants.getUSER_STATE() +","+
                                   "C="+ GSAMLTokenConstants.getUSER_COUNTRY(); 
            
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setNamespaceAware(false);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element mRoot = doc.createElement("myroot");
            
            doc.appendChild(mRoot);

            QName saml2 = new QName(SAML_2_NS, "saml2");
            QName xenc = new QName(XML_ENC_NS, "xenc");
            QName exc14 = new QName(XML_EXC_NS, "exc14");
            QName xs = new QName(XS_NS, "xs");
            QName ds = new QName(DS_NS, "ds");
            
            el = doc.createElementNS(SAML_2_NS, "Assertion");
            //el.setPrefix("saml2");
            
            mRoot.appendChild(el);
            
            
            Attr attr = doc.createAttribute("IssueInstant");
            attr.setValue(getDateString(issueInstant));
            el.setAttributeNode(attr);
            
            attr = doc.createAttribute("Version");
            attr.setValue(VERSION);
            el.setAttributeNode(attr);
            
            Element issuer = doc.createElement("Issuer");
            attr = doc.createAttribute("Format");
            attr.setValue(nameIdFormat);
            issuer.setAttributeNode(attr);
            issuer.setNodeValue(compliantName);  
            el.appendChild(issuer);
            
            Element conditions = doc.createElement("Conditions");

            attr = doc.createAttribute("NotBefore");
            attr.setValue(getDateString(before));
            conditions.setAttributeNode(attr);
            attr = doc.createAttribute("NotOnOrAfter");
            attr.setValue(getDateString(after));
            conditions.setAttributeNode(attr);
            el.appendChild(conditions);
            
            Element attributestatement = doc.createElement("AttributeStatement");

            el.appendChild(attributestatement);
            
            Element attribute1 = doc.createElement("Attribute");

            attr = doc.createAttribute("Name");
            attr.setValue(DS4PConstants.SUBJECT_ID_NS);
            attribute1.setAttributeNode(attr);
            attributestatement.appendChild(attribute1);
            
            Element attributeV1 = doc.createElement("AttributeValue");
            attr = doc.createAttribute("type");
            attr.setNodeValue("http://www.w3.org/2001/XMLSchema#string");
            attributeV1.setAttributeNode(attr);
            Text testValueAttr = doc.createTextNode(GSAMLTokenConstants.getSUBJECT_ID());
            attributeV1.appendChild(testValueAttr);
            attribute1.appendChild(attributeV1);
            
            
            Element attribute2 = doc.createElement("Attribute");
            attr = doc.createAttribute("Name");
            attr.setValue(DS4PConstants.SUBJECT_PURPOSE_OF_USE_NS);
            attribute2.setAttributeNode(attr);
            attributestatement.appendChild(attribute2);
            
            Element attributeV2 = doc.createElement("AttributeValue");

            Element pou = doc.createElementNS("urn:hl7-org:v3", "PurposeOfUse");
            attr = doc.createAttribute("code");
            attr.setValue(GSAMLTokenConstants.getSUBJECT_PURPOSE_OF_USE());
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("codeSystem");
            attr.setValue("2.16.840.1.113883.5.8");
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("codeSystemName");
            attr.setValue("hl7-org:v3");
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("displayName");
            attr.setValue("Treatment");
            pou.setAttributeNode(attr);
            
            attr = doc.createAttribute("type");
            attr.setValue("urn:hl7-org:v3#CE");
            pou.setAttributeNode(attr);
            
            attributeV2.appendChild(pou);
            attribute2.appendChild(attributeV2);
   
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return el;
    }
    
    private String getDateString(Date dt) {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        String res = sdt.format(dt);
        
        return res;
    }
}
