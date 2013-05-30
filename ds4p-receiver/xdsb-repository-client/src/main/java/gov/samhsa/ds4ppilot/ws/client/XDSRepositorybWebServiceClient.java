/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
package gov.samhsa.ds4ppilot.ws.client;

import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequest;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequest;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponse;
import ihe.iti.xds_b._2007.XDSRepository;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponse;

import org.tempuri.DocumentRepositoryService;

/**
 * The Class XDSRepositorybWebServiceClient.
 */
public class XDSRepositorybWebServiceClient {

	/** The endpoint address. */
	private final String endpointAddress;

	/**
	 * Instantiates a new xDS repositoryb web service client.
	 * 
	 * @param endpointAddress
	 *            the endpoint address
	 */
	public XDSRepositorybWebServiceClient(String endpointAddress) {
		this.endpointAddress = endpointAddress;
	}

	/**
	 * Retrieve document set request.
	 * 
	 * @param retrieveDocumentSet
	 *            the retrieve document set
	 * @return the retrieve document set response
	 */
	public RetrieveDocumentSetResponse retrieveDocumentSetRequest(
			RetrieveDocumentSetRequest retrieveDocumentSet) {
		XDSRepository port = createPort();

		return port.retrieveDocumentSet(retrieveDocumentSet);
	}

	/**
	 * Provide and register document set reponse.
	 * 
	 * @param provideAndRegisterDocumentSet
	 *            the provide and register document set
	 * @return the registry response
	 */
	public RegistryResponse provideAndRegisterDocumentSetReponse(
			ProvideAndRegisterDocumentSetRequest provideAndRegisterDocumentSet) {
		XDSRepository port = createPort();
		return port
				.provideAndRegisterDocumentSet(provideAndRegisterDocumentSet);

	}

	/**
	 * Creates the port.
	 * 
	 * @return the xDS repository
	 */
	private XDSRepository createPort() {
		final URL WSDL_LOCATION = this.getClass().getClassLoader()
				.getResource("XDS.b_repository.net.wsdl");
		final QName SERVICE = new QName("http://tempuri.org/",
				"DocumentRepositoryService");

		XDSRepository port = new DocumentRepositoryService(WSDL_LOCATION,
				SERVICE).getXDSRepositoryHTTPEndpoint();


			BindingProvider bp = (BindingProvider) port;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);



		return port;
	}
}
