/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */

package gov.samhsa.schema.ds4p.xdsbregistry.message;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.samhsa.schema.ds4p.xdsbregistry.message package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.samhsa.schema.ds4p.xdsbregistry.message
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistryRequest }
     * 
     */
    public RegistryRequest createRegistryRequest() {
        return new RegistryRequest();
    }

    /**
     * Create an instance of {@link MessageBody }
     * 
     */
    public MessageBody createMessageBody() {
        return new MessageBody();
    }

}
