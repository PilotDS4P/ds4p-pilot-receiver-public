/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ds4p.security.xacml;

import gov.va.ds4p.cdaconsentserviceclient.CDAR2ConsentDirectiveGenerator;
import gov.va.ehtac.ds4p.ws.cda.CDAR2ConsentDirective;
import gov.va.ehtac.ds4p.ws.cda.CDAR2ConsentDirective_Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Duane DeCouteau
 */
public class XACMLTestData {
    private String patientName = "Asample Patientone";
    private String patientId = "PUI100010060001";
    private String patientIDType = "ehr";
    private String patientDateOfBirth = "19960101";
    private String patientGender = "F";
    private String intendedPOU = "TREAT";
    private List<String> allowedPOU = new ArrayList();
    private String primaryRecipient = "Duane_Decouteau@direct.healthvault-stage.com";
    private List<String> allowedRecipients = new ArrayList();;
    private String authorization = "Permit";
    private List<String> maskingActions = new ArrayList();
    private List<String> redactActions = new ArrayList();
    private String homeCommunityId = "2.16.840.1.113883.3.467";
    
    public XACMLTestData() {
        allowedPOU = Arrays.asList(new String[]{"TREAT", "ETREAT"});
        allowedRecipients = Arrays.asList(new String[]{"Duane_Decouteau@direct.healthvault-stage.com", "dr.taylor@aero.org"});
        maskingActions = Arrays.asList(new String[]{"HIV"});
        redactActions = Arrays.asList(new String[] {"ETH","PSY"});
        
    }
    
    public String getPatientConsent() {
        String res = "";
        try {
            CDAR2ConsentDirective_Service service = new CDAR2ConsentDirective_Service();
            CDAR2ConsentDirective port = service.getCDAR2ConsentDirectivePort();
            ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/DS4PACSServices/CDAR2ConsentDirective?wsdl");  
            
            byte[] bString = port.getCDAR2ConsentDirective(patientName, patientId, patientIDType, patientGender, patientDateOfBirth, authorization, intendedPOU, allowedPOU, primaryRecipient, allowedRecipients, maskingActions, redactActions, homeCommunityId);          
            res = new String(bString);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
}
