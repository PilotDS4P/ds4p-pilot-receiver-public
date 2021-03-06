/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */

package UtsSemNet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSemanticNetworkRelationLabelResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSemanticNetworkRelationLabelResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservice.uts.umls.nlm.nih.gov/}semanticNetworkRelationLabelDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSemanticNetworkRelationLabelResponse", propOrder = {
    "_return"
})
public class GetSemanticNetworkRelationLabelResponse {

    @XmlElement(name = "return")
    protected SemanticNetworkRelationLabelDTO _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link SemanticNetworkRelationLabelDTO }
     *     
     */
    public SemanticNetworkRelationLabelDTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link SemanticNetworkRelationLabelDTO }
     *     
     */
    public void setReturn(SemanticNetworkRelationLabelDTO value) {
        this._return = value;
    }

}
