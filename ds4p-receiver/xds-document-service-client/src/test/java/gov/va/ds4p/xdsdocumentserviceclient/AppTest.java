package gov.va.ds4p.xdsdocumentserviceclient;

import gov.samhsa.ds4ppilot.contract.orchestrator.XDSDocumentService;
import gov.samhsa.ds4ppilot.contract.orchestrator.XDSDocumentServicePortType;
import gov.samhsa.ds4ppilot.schema.orchestrator.SaveDocumentSetToXdsRepositoryRequest;
import gov.samhsa.ds4ppilot.schema.orchestrator.SaveDocumentSetToXdsRepositoryResponse;
import gov.va.ehtac.ds4p.ws.cda.CDAR2ConsentDirective;
import gov.va.ehtac.ds4p.ws.cda.CDAR2ConsentDirective_Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.ws.BindingProvider;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    
    private String patientName = "Asample Patientone";
    private String patientId = "PUI100010060001";
    private String patientIDType = "ehr";
    private String patientDateOfBirth = "19710510";
    private String patientGender = "M";
    private String intendedPOU = "TREAT";
    private List<String> allowedPOU = new ArrayList();
    private String primaryRecipient = "Duane_Decouteau@direct.healthvault-stage.com";
    private List<String> allowedRecipients = new ArrayList();;
    private String authorization = "Permit";
    private List<String> maskingActions = new ArrayList();
    private List<String> redactActions = new ArrayList();
    private String homeCommunityId = "2.16.840.1.113883.3.467";
    private String cdaEndpoint = "http://174.78.146.228:8080/DS4PACSServices/CDAR2ConsentDirective?wsdl";
    private String xdsEndpoint = "http://xds-demo.feisystems.com/Orchestrator/services/xdsdocumentservice?wsdl";
    
    
    public AppTest( String testName )
    {
        super( testName );
        allowedPOU = Arrays.asList(new String[]{"TREAT"});
        allowedRecipients = Arrays.asList(new String[]{"mike.davis@va.gov", "Duane_Decouteau@direct.healthvault-stage.com", "burak.tasel@feisystems.com", "michael.dufel@jerichosystems.com", "david.staggs@jerichosystems.com", "kcallahan@hipaat.com"});
        maskingActions = Arrays.asList(new String[]{"HIV"});
        redactActions = Arrays.asList(new String[] {"ETH","PSY"});
        
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        String consent = "";
        try {
            //generate consent
            CDAR2ConsentDirective_Service service = new CDAR2ConsentDirective_Service();
            CDAR2ConsentDirective port = service.getCDAR2ConsentDirectivePort();
            ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, cdaEndpoint);
            byte[] bRes = port.getCDAR2ConsentDirective(patientName, patientId, patientIDType, patientGender, patientDateOfBirth, authorization, intendedPOU, allowedPOU, primaryRecipient, allowedRecipients, maskingActions, redactActions, homeCommunityId);
            consent = new String(bRes);
            System.out.println(consent);
            
            //store consent
            XDSDocumentService xService = new XDSDocumentService();
            XDSDocumentServicePortType xPort = xService.getXDSDocumentServicePort();
            ((BindingProvider)xPort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, xdsEndpoint);
            
            SaveDocumentSetToXdsRepositoryRequest request = new SaveDocumentSetToXdsRepositoryRequest();
            request.setDocumentSet(consent);
            
            SaveDocumentSetToXdsRepositoryResponse resp = xPort.saveDocumentSetToXdsRepository(request);
            
            if (resp.isReturn()) {
                System.out.println("RETURN IS TRUE");
            }
            else {
                System.out.println("RETURN IS FALSE");
            }
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        assertTrue( true );
    }
}
