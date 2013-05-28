/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */

package UtsMetathesaurusContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSourceDescriptorSourceDescriptorRelations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSourceDescriptorSourceDescriptorRelations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceDescriptorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psf" type="{http://webservice.uts.umls.nlm.nih.gov/}psf" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSourceDescriptorSourceDescriptorRelations", propOrder = {
    "ticket",
    "version",
    "sourceDescriptorId",
    "rootSource",
    "psf"
})
public class GetSourceDescriptorSourceDescriptorRelations {

    protected String ticket;
    protected String version;
    protected String sourceDescriptorId;
    protected String rootSource;
    protected Psf psf;

    /**
     * Gets the value of the ticket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * Sets the value of the ticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicket(String value) {
        this.ticket = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the sourceDescriptorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDescriptorId() {
        return sourceDescriptorId;
    }

    /**
     * Sets the value of the sourceDescriptorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDescriptorId(String value) {
        this.sourceDescriptorId = value;
    }

    /**
     * Gets the value of the rootSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootSource() {
        return rootSource;
    }

    /**
     * Sets the value of the rootSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootSource(String value) {
        this.rootSource = value;
    }

    /**
     * Gets the value of the psf property.
     * 
     * @return
     *     possible object is
     *     {@link Psf }
     *     
     */
    public Psf getPsf() {
        return psf;
    }

    /**
     * Sets the value of the psf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Psf }
     *     
     */
    public void setPsf(Psf value) {
        this.psf = value;
    }

}
