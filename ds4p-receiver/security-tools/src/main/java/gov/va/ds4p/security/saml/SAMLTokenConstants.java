/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ds4p.security.saml;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Duane DeCouteau
 */
public class SAMLTokenConstants {
    private static String SUBJECT_ID;
    private static String SUBJECT_NPI;
    private static String SUBJECT_LOCALITY;
    private static List<String> SUBJECT_PERMISSIONS;
    private static String SUBJECT_STRUCTURED_ROLE;
    private static String SUBJECT_FUNCTIONAL_ROLE;
    private static String SUBJECT_PURPOSE_OF_USE;
    private static String RESOURCE_NAME;
    private static String RESOURCE_ID;
    private static String RESOURCE_TYPE;
    private static String RESOURCE_ACTION_ID;
    private static String USER_NAME;
    private static String USER_ORGANIZATION;
    private static String USER_ORGANIZATION_UNIT;
    private static String USER_CITY;
    private static String USER_STATE;
    private static String USER_COUNTRY;
    private static String RECENT_REQUEST;
    private static String MESSAGE_ID;


    public static String getSUBJECT_ID() {
        return SUBJECT_ID;
    }

    public static void setSUBJECT_ID(String aSUBJECT_ID) {
        SUBJECT_ID = aSUBJECT_ID;
    }

    public static String getSUBJECT_NPI() {
        return SUBJECT_NPI;
    }

    public static void setSUBJECT_NPI(String aSUBJECT_NPI) {
        SUBJECT_NPI = aSUBJECT_NPI;
    }

    public static String getSUBJECT_LOCALITY() {
        return SUBJECT_LOCALITY;
    }

    public static void setSUBJECT_LOCALITY(String aSUBJECT_LOCALITY) {
        SUBJECT_LOCALITY = aSUBJECT_LOCALITY;
    }

    public static List<String> getSUBJECT_PERMISSIONS() {
        return SUBJECT_PERMISSIONS;
    }

    public static void setSUBJECT_PERMISSIONS(List<String> aSUBJECT_PERMISSIONS) {
        SUBJECT_PERMISSIONS = aSUBJECT_PERMISSIONS;
    }

    public static String getSUBJECT_STRUCTURED_ROLE() {
        return SUBJECT_STRUCTURED_ROLE;
    }

    public static void setSUBJECT_STRUCTURED_ROLE(String aSUBJECT_STRUCTURED_ROLE) {
        SUBJECT_STRUCTURED_ROLE = aSUBJECT_STRUCTURED_ROLE;
    }

    public static String getSUBJECT_FUNCTIONAL_ROLE() {
        return SUBJECT_FUNCTIONAL_ROLE;
    }

    public static void setSUBJECT_FUNCTIONAL_ROLE(String aSUBJECT_FUNCTIONAL_ROLE) {
        SUBJECT_FUNCTIONAL_ROLE = aSUBJECT_FUNCTIONAL_ROLE;
    }

    public static String getSUBJECT_PURPOSE_OF_USE() {
        return SUBJECT_PURPOSE_OF_USE;
    }

    public static void setSUBJECT_PURPOSE_OF_USE(String aSUBJECT_PURPOSE_OF_USE) {
        SUBJECT_PURPOSE_OF_USE = aSUBJECT_PURPOSE_OF_USE;
    }

    public static String getRESOURCE_ID() {
        return RESOURCE_ID;
    }

    public static void setRESOURCE_ID(String aRESOURCE_ID) {
        RESOURCE_ID = aRESOURCE_ID;
    }

    public static String getRESOURCE_TYPE() {
        return RESOURCE_TYPE;
    }

    public static void setRESOURCE_TYPE(String aRESOURCE_TYPE) {
        RESOURCE_TYPE = aRESOURCE_TYPE;
    }


    /**
     * @return the RESOURCE_ACTION_ID
     */
    public static String getRESOURCE_ACTION_ID() {
        return RESOURCE_ACTION_ID;
    }

    /**
     * @param aRESOURCE_ACTION_ID the RESOURCE_ACTION_ID to set
     */
    public static void setRESOURCE_ACTION_ID(String aRESOURCE_ACTION_ID) {
        RESOURCE_ACTION_ID = aRESOURCE_ACTION_ID;
    }


    /**
     * @return the RESOURCE_NAME
     */
    public static String getRESOURCE_NAME() {
        return RESOURCE_NAME;
    }

    /**
     * @param aRESOURCE_NAME the RESOURCE_NAME to set
     */
    public static void setRESOURCE_NAME(String aRESOURCE_NAME) {
        RESOURCE_NAME = aRESOURCE_NAME;
    }

    /**
     * @return the USER_NAME
     */
    public static String getUSER_NAME() {
        return USER_NAME;
    }

    /**
     * @param aUSER_NAME the USER_NAME to set
     */
    public static void setUSER_NAME(String aUSER_NAME) {
        USER_NAME = aUSER_NAME;
    }

    /**
     * @return the USER_ORGANIZATION
     */
    public static String getUSER_ORGANIZATION() {
        return USER_ORGANIZATION;
    }

    /**
     * @param aUSER_ORGANIZATION the USER_ORGANIZATION to set
     */
    public static void setUSER_ORGANIZATION(String aUSER_ORGANIZATION) {
        USER_ORGANIZATION = aUSER_ORGANIZATION;
    }

    /**
     * @return the USER_ORGANIZATION_UNIT
     */
    public static String getUSER_ORGANIZATION_UNIT() {
        return USER_ORGANIZATION_UNIT;
    }

    /**
     * @param aUSER_ORGANIZATION_UNIT the USER_ORGANIZATION_UNIT to set
     */
    public static void setUSER_ORGANIZATION_UNIT(String aUSER_ORGANIZATION_UNIT) {
        USER_ORGANIZATION_UNIT = aUSER_ORGANIZATION_UNIT;
    }

    /**
     * @return the USER_CITY
     */
    public static String getUSER_CITY() {
        return USER_CITY;
    }

    /**
     * @param aUSER_CITY the USER_CITY to set
     */
    public static void setUSER_CITY(String aUSER_CITY) {
        USER_CITY = aUSER_CITY;
    }

    /**
     * @return the USER_STATE
     */
    public static String getUSER_STATE() {
        return USER_STATE;
    }

    /**
     * @param aUSER_STATE the USER_STATE to set
     */
    public static void setUSER_STATE(String aUSER_STATE) {
        USER_STATE = aUSER_STATE;
    }

    /**
     * @return the USER_COUNTRY
     */
    public static String getUSER_COUNTRY() {
        return USER_COUNTRY;
    }

    /**
     * @param aUSER_COUNTRY the USER_COUNTRY to set
     */
    public static void setUSER_COUNTRY(String aUSER_COUNTRY) {
        USER_COUNTRY = aUSER_COUNTRY;
    }

    /**
     * @return the RECENT_REQUEST
     */
    public static String getRECENT_REQUEST() {
        return RECENT_REQUEST;
    }

    /**
     * @param aRECENT_REQUEST the RECENT_REQUEST to set
     */
    public static void setRECENT_REQUEST(String aRECENT_REQUEST) {
        RECENT_REQUEST = aRECENT_REQUEST;
    }

    /**
     * @return the MESSAGE_ID
     */
    public static String getMESSAGE_ID() {
        return MESSAGE_ID;
    }

    /**
     * @param aMESSAGE_ID the MESSAGE_ID to set
     */
    public static void setMESSAGE_ID(String aMESSAGE_ID) {
        MESSAGE_ID = aMESSAGE_ID;
    }
    
    
    
}
