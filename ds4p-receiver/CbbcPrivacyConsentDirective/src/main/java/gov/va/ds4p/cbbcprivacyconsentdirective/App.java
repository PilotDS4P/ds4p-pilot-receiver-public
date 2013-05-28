package gov.va.ds4p.cbbcprivacyconsentdirective;

import org.hl7.cbcc.privacy.consentdirective.CONSENTDIRECTIVEFactory;
import org.hl7.cbcc.privacy.consentdirective.ComputablePolicy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        createTestConsentDirective();
    }
    
    private static void createTestConsentDirective() {
        CONSENTDIRECTIVEFactory factory = CONSENTDIRECTIVEFactory.eINSTANCE;
        
        ComputablePolicy policy = factory.createComputablePolicy();
        policy.init();
        policy.
    }
}
