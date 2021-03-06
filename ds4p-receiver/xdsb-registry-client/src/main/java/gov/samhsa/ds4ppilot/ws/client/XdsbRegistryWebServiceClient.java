/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
package gov.samhsa.ds4ppilot.ws.client;

import samhsa.ds4p.xdsbregistry.DocumentRegistryService;
import iti.xds_b._2007.XDSRegistry;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ResponseOptionType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;

import hl7.v3.PRPAIN201301UV02;
import hl7.v3.PRPAIN201302UV;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The Class XdsbRegistryWebServiceClient.
 */
public class XdsbRegistryWebServiceClient {

	/** The endpoint address. */
	private final String endpointAddress;

	/** The wsdl url. */
	final URL wsdlURL = this.getClass().getClassLoader()
			.getResource("XDS.b_registry.net.wsdl");

	/** The service name. */
	final QName serviceName = new QName("http://samhsa.gov/ds4p/XDSbRegistry/",
			"DocumentRegistryService");

	/** The port name. */
	final QName portName = new QName("http://samhsa.gov/ds4p/XDSbRegistry/",
			"XDSRegistry_HTTP_Endpoint");
        
        //XUA test data
        private static String userName = "DrDuane";
        private static String providerId = "Duane_Decouteau@direct.healthvault-stage.com";
        private static String organization = "Dept. of Veterans Affairs";
        private static String organizationUnit = "HIG";
        private static String state = "MT";
        private static String city = "Missoula";
        private static String country = "US";
        private static String providerHomeCommunityId = "2.16.840.1.113883.4.349";
        private static String purposeOfUse = "TREAT";
        
        private SAMLAssertionHelper samlHelper = new SAMLAssertionHelper();
	/**
	 * Instantiates a new xdsb registry web service client.
	 * 
	 * @param endpointAddress
	 *            the endpoint address
	 */
        
        public XdsbRegistryWebServiceClient(String endpointAddress) {
            this.endpointAddress = endpointAddress;
        }
        
 	/**
	 * Instantiates a new xdsb registry web service client.
	 * 
	 * @param endpointAddress, providerId, purposeOfUse, organization, organizationUnit, city, state, country, providerHomeCommunityId 
	 *            for XUA authentication and authorization assertion
	 */
        
	public XdsbRegistryWebServiceClient(String endpointAddress, String userName, String providerId, String purposeOfUse, String organization, String organizationUnit, 
                                            String city, String state, String country, String providerHomeCommunityId) {
		this.endpointAddress = endpointAddress;
                this.userName = userName;
                this.providerId = providerId;
                this.purposeOfUse = purposeOfUse;
                this.organization = organization;
                this.organizationUnit = organizationUnit;
                this.city = city;
                this.state = state;
                this.country = country;
                this.providerHomeCommunityId = providerHomeCommunityId;
                
                GSAMLTokenConstants.setSUBJECT_ID(providerId);
                GSAMLTokenConstants.setSUBJECT_PURPOSE_OF_USE(purposeOfUse);
                GSAMLTokenConstants.setUSER_NAME(userName);
                GSAMLTokenConstants.setUSER_CITY(city);
                GSAMLTokenConstants.setUSER_COUNTRY(country);
                GSAMLTokenConstants.setUSER_STATE(state);
                GSAMLTokenConstants.setUSER_ORGANIZATION(organization);
                GSAMLTokenConstants.setUSER_ORGANIZATION_UNIT(organizationUnit);
                GSAMLTokenConstants.setSUBJECT_LOCALITY(providerHomeCommunityId);
                 
                
	}

	/**
	 * Registry stored query.
	 * 
	 * @param registryStoredQuery
	 *            the registry stored query
	 * @return the adhoc query response
	 */
	public AdhocQueryResponse registryStoredQuery(
			AdhocQueryRequest registryStoredQuery) {
		XDSRegistry port = createPort();

		return port.registryStoredQuery(registryStoredQuery);
	}

	/**
	 * Adds the patient registry record.
	 * 
	 * @param input
	 *            the input
	 * @return the string
	 * @throws Throwable
	 *             the throwable
	 */
	public String addPatientRegistryRecord(PRPAIN201301UV02 input)
			throws Throwable {
		String justPayloadXml = marshall(input);

		final QName operationName = new QName("urn:ihe:iti:xds-b:2007",
				"PatientRegistryRecordAdded");

		String responsePayload = getRawResponsePayload(operationName,
				justPayloadXml);

		return responsePayload;
	}

	/**
	 * Revise patient registry record.
	 * 
	 * @param input
	 *            the input
	 * @return the string
	 * @throws Throwable
	 *             the throwable
	 */
	public String revisePatientRegistryRecord(PRPAIN201302UV input)
			throws Throwable {
		String justPayloadXml = marshall(input);

		final QName operationName = new QName("urn:ihe:iti:xds-b:2007",
				"PatientRegistryRecordRevised");

		String responsePayload = getRawResponsePayload(operationName,
				justPayloadXml);

		return responsePayload;
	}

	/**
	 * Creates the port.
	 * 
	 * @return the xDS registry
	 */
	private XDSRegistry createPort() {
		
                XDSRegistry port = new DocumentRegistryService(wsdlURL, serviceName)
				.getXDSRegistryHTTPEndpoint();
                Element xuaAssertion = samlHelper.createSAML2Assertion();
                ((BindingProvider)port).getRequestContext().put("xuaAssertion", xuaAssertion);
                XUAAssertionHandler handler = new XUAAssertionHandler(xuaAssertion);
                ((BindingProvider)port).getBinding().setHandlerChain(Arrays.asList((Handler)handler));
                

                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);

		return port;
	}

	/**
	 * Gets the raw response payload.
	 * 
	 * @param operationName
	 *            the operation name
	 * @param justPayloadXml
	 *            the just payload xml
	 * @return the raw response payload
	 * @throws Throwable
	 *             the throwable
	 */
	private String getRawResponsePayload(QName operationName,
			String justPayloadXml) throws Throwable {
		Service jaxwsService = Service.create(wsdlURL, serviceName);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);

		InputStream is = new ByteArrayInputStream(
				justPayloadXml.getBytes("UTF-8"));
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document newDoc = builder.parse(is);

		DOMSource request = new DOMSource(newDoc);

		Dispatch<Source> disp = jaxwsService.createDispatch(portName,
				Source.class, Service.Mode.PAYLOAD);

		BindingProvider bp = disp;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				endpointAddress);

		bp.getRequestContext()
				.put(MessageContext.WSDL_OPERATION, operationName);

		Source responsePayloadSource = disp.invoke(request);

		Transformer trans = TransformerFactory.newInstance().newTransformer();
		StreamResult streamResult = new StreamResult();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		streamResult.setOutputStream(out);
		trans.transform(responsePayloadSource, streamResult);

		String responsePayload = new String(out.toByteArray());

		return responsePayload;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Throwable
	 *             the throwable
	 */
	public static void main(String[] args) throws Throwable {
		final String demoEndpoint = "http://75.145.119.97/JerichoExchangeServices/XDSRegistry";
                //final String demoEndpoint = "http://98.143.106.135:8080/axis2/services/xdsregistryb";
		//final String javaVmEndpoint = "http://192.168.223.134:8080/axis2/services/xdsregistryb";
		//final String dotnetVmEndpoint = "http://192.168.223.138:8080/xdsservice/xdsregistry";
                
		XdsbRegistryWebServiceClient xdsService = new XdsbRegistryWebServiceClient(
				demoEndpoint);

                GSAMLTokenConstants.setSUBJECT_ID(providerId);
                GSAMLTokenConstants.setSUBJECT_PURPOSE_OF_USE(purposeOfUse);
                GSAMLTokenConstants.setUSER_CITY(city);
                GSAMLTokenConstants.setUSER_COUNTRY(country);
                GSAMLTokenConstants.setUSER_STATE(state);
                GSAMLTokenConstants.setUSER_ORGANIZATION(organization);
                GSAMLTokenConstants.setUSER_ORGANIZATION_UNIT(organizationUnit);
                GSAMLTokenConstants.setSUBJECT_LOCALITY(providerHomeCommunityId);
                
		System.out.println("Run registryStoredQuery");

		AdhocQueryRequest registryStoredQuery = new AdhocQueryRequest();

		ResponseOptionType responseOptionType = new ResponseOptionType();
		responseOptionType.setReturnComposedObjects(true);
		responseOptionType.setReturnType("LeafClass");
		registryStoredQuery.setResponseOption(responseOptionType);

		AdhocQueryType adhocQueryType = new AdhocQueryType();
		adhocQueryType.setId("urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d"); // FindDocuments
																				// by
																				// patientId
		registryStoredQuery.setAdhocQuery(adhocQueryType);

		SlotType1 patientIdSlotType = new SlotType1();
		patientIdSlotType.setName("$XDSDocumentEntryPatientId");
		ValueListType patientIdValueListType = new ValueListType();
		
                //jericho patient id
                patientIdValueListType.getValue().add(
				"6^^^&1.2.3.4.5.6.7.8&ISO"); // PatientId
                //patientIdValueListType.getValue().add("'110-0234^^^&1.3.6.1.4.1.21367.2005.3.7&ISO'"); // PatientId

		patientIdSlotType.setValueList(patientIdValueListType);
		adhocQueryType.getSlot().add(patientIdSlotType);

		SlotType1 statusSlotType = new SlotType1();
		statusSlotType.setName("$XDSDocumentEntryStatus");
		ValueListType statusValueListType = new ValueListType();
		statusValueListType.getValue().add(
				"('urn:oasis:names:tc:ebxml-regrep:StatusType:Approved')");
		statusSlotType.setValueList(statusValueListType);
		adhocQueryType.getSlot().add(statusSlotType);

		System.out.println(marshall(registryStoredQuery));

		Object result = xdsService.registryStoredQuery(registryStoredQuery);

		System.out.println(marshall(result));

//		System.out.println("Run patientRegistryRecordAdded");
//
//		// PatientPerson
//		PatientPerson patientPerson = new PatientPerson();
//		Name name = new Name();
//		name.setFamily("Family");
//		name.setGiven("Given");
//		patientPerson.setName(name);
//
//		BirthTime birthTime = new BirthTime();
//		birthTime.setValue("19570323");
//		patientPerson.setBirthTime(birthTime);
//
//		Addr addr = new Addr();
//		addr.setStreetAddressLine("3443 South Beach Avenue");
//		addr.setCity("Columbia");
//		addr.setState("MD");
//		patientPerson.getAddr().add(addr);
//
//		// Patient
//		Patient patient = new Patient();
//		Id patientId = new Id();
//		patientId.setRoot("1.2.840.114350.1.13.99998.8734"); // Domain Id
//		patientId.setExtension("10"); // PatientId in the domain
//		patient.setId(patientId);
//		patient.setPatientPerson(patientPerson);
//
//		// Subject 1
//		Subject1 subject1 = new Subject1();
//		subject1.setPatient(patient);
//
//		// RegistrationEvent
//		RegistrationEvent registrationEvent = new RegistrationEvent();
//		registrationEvent.setSubject1(subject1);
//
//		// Subject
//		Subject subject = new Subject();
//		subject.setRegistrationEvent(registrationEvent);
//
//		// ControlActProcess
//		ControlActProcess controlActProcess = new ControlActProcess();
//		controlActProcess.setSubject(subject);
//
//		// PRPAIN201301UV02
//		PRPAIN201301UV02 prpain201301uv02 = new PRPAIN201301UV02();
//		prpain201301uv02.setControlActProcess(controlActProcess);
//
//		Id PRPAIN201302UVId = new Id();
//		PRPAIN201302UVId.setRoot("cdc0d3fa-4467-11dc-a6be-3603d686610257");
//		prpain201301uv02.setId(PRPAIN201302UVId);
//
//		Receiver receiver = new Receiver();
//		receiver.setTypeCode("RCV");
//		Device receiverDevice = new Device();
//		receiverDevice.setDeterminerCode("INSTANCE");
//		Id receiverDeviceId = new Id();
//		receiverDeviceId.setRoot("1.2.840.114350.1.13.99999.4567");
//		receiverDevice.setId(receiverDeviceId);
//		receiver.setDevice(receiverDevice);
//		prpain201301uv02.setReceiver(receiver);
//
//		Sender sender = new Sender();
//		sender.setTypeCode("SND");
//		Device senderDevice = new Device();
//		senderDevice.setDeterminerCode("INSTANCE");
//		Id senderDeviceId = new Id();
//		senderDeviceId.setRoot("1.2.840.114350.1.13.99998.8734");
//		senderDevice.setId(senderDeviceId);
//		sender.setDevice(senderDevice);
//		prpain201301uv02.setSender(sender);
//
//		System.out.println(xdsService
//				.addPatientRegistryRecord(prpain201301uv02));
//
//		System.out.println("Run patientRegistryRecordRevised");
//
//		// PRPAIN201302UV
//		PRPAIN201302UV prpain201302uv = new PRPAIN201302UV();
//
//		prpain201301uv02.setControlActProcess(controlActProcess);
//
//		prpain201302uv.setId(PRPAIN201302UVId);
//
//		prpain201302uv.setReceiver(receiver);
//
//		prpain201302uv.setSender(sender);
//
//		addr.setCity("DC");
//		System.out.println(xdsService
//				.revisePatientRegistryRecord(prpain201302uv));
	}

	/**
	 * Marshall.
	 * 
	 * @param obj
	 *            the obj
	 * @return the string
	 * @throws Throwable
	 *             the throwable
	 */
	private static String marshall(Object obj) throws Throwable {
		final JAXBContext context = JAXBContext.newInstance(obj.getClass());

		// Create the marshaller, this is the nifty little thing that will
		// actually transform the object into XML
		final Marshaller marshaller = context.createMarshaller();

		// Create a stringWriter to hold the XML
		final StringWriter stringWriter = new StringWriter();

		// Marshal the javaObject and write the XML to the stringWriter
		marshaller.marshal(obj, stringWriter);

		return stringWriter.toString();
	}
}
