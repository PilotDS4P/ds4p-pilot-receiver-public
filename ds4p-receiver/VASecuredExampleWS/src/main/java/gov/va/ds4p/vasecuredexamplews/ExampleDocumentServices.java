/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ds4p.vasecuredexamplews;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;



/**
 *
 * @author Duane DeCouteau
 */
@WebService(serviceName = "ExampleDocumentServices")
public class ExampleDocumentServices {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "documentQuery")
    public String documentQuery(@WebParam(name = "patientId") String patientId, @WebParam(name = "messageId") String messageId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "documentRetrieve")
    public String documentRetrieve(@WebParam(name = "documentId") String documentId, @WebParam(name = "messageId") String messageId) {
        //TODO write your implementation code here:
        return null;
    }

    
}
