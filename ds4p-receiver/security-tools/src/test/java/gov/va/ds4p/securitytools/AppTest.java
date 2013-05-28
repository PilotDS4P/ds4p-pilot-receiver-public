package gov.va.ds4p.securitytools;

import gov.va.ds4p.policy.reference.ActInformationSensitivityPolicy;
import gov.va.ds4p.policy.reference.ApplicableSensitivityCodes;
import gov.va.ds4p.security.vocab.VocabularyProvider;
import java.util.Iterator;
import java.util.List;
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
    public AppTest( String testName )
    {
        super( testName );
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
        VocabularyProvider v = new VocabularyProvider();
        ApplicableSensitivityCodes codes = v.getDataSegmentationObligations();
        List<ActInformationSensitivityPolicy> codeList = codes.getActInformationSensitivityPolicy();
        Iterator iter = codeList.iterator();
        while (iter.hasNext()) {
            ActInformationSensitivityPolicy p = (ActInformationSensitivityPolicy)iter.next();
            System.out.println(p.getCode());
        }
        
        assertTrue( true );
    }
}
