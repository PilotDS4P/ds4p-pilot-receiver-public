/**
 * This software is being provided per FARS 52.227-14 Rights in Data - General.
 * Any redistribution or request for copyright requires written consent by the
 * Department of Veterans Affairs.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.ehtac.ds4p.jpa;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "user_auth_profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAuthProfile.findAll", query = "SELECT a FROM UserAuthProfile a"),
    @NamedQuery(name = "UserAuthProfile.findByIduserauthprofile", query = "SELECT a FROM UserAuthProfile a WHERE a.iduserauthprofile = :iduserauthprofile"),
    @NamedQuery(name = "UserAuthProfile.findByUserid", query = "SELECT a FROM UserAuthProfile a WHERE a.userid = :userid"),
    @NamedQuery(name = "UserAuthProfile.findByUserpass", query = "SELECT a FROM UserAuthProfile a WHERE a.userpass = :userpass"),
    @NamedQuery(name = "UserAuthProfile.findByProviderid", query = "SELECT a FROM UserAuthProfile a WHERE a.providerid = :providerid"),
    @NamedQuery(name = "UserAuthProfile.findByUseridAndUserpass", query = "SELECT a FROM UserAuthProfile a WHERE a.userid = :userid AND a.userpass = :userpass")}) 
public class UserAuthProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "iduserauthprofile")
    private Integer iduserauthprofile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "userid")
    private String userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "userpass")
    private String userpass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "providerid")
    private String providerid;
    @Basic(optional = false)
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "profile")
    private String profile;
    
    public UserAuthProfile() {
        
    }
    
    public UserAuthProfile(Integer iduserauthprofile) {
        this.iduserauthprofile = iduserauthprofile;
    }
    
    public UserAuthProfile(Integer iduserauthprofile, String userid, String userpass, String providerid, String profile) {
        this.iduserauthprofile = iduserauthprofile;
        this.userid = userid;
        this.userpass = userpass;
        this.providerid = providerid;
        this.profile = profile;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIduserauthprofile() != null ? getIduserauthprofile().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAuthProfile)) {
            return false;
        }
        UserAuthProfile other = (UserAuthProfile) object;
        if ((this.getIduserauthprofile() == null && other.getIduserauthprofile() != null) || (this.getIduserauthprofile() != null && !this.iduserauthprofile.equals(other.iduserauthprofile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.va.ehtac.ds4p.jpa.UserAuthProfile[ iduserAuthProfile=" + getIduserauthprofile() + " ]";
    }

    /**
     * @return the iduserauthprofile
     */
    public Integer getIduserauthprofile() {
        return iduserauthprofile;
    }

    /**
     * @param iduserauthprofile the iduserauthprofile to set
     */
    public void setIduserauthprofile(Integer iduserauthprofile) {
        this.iduserauthprofile = iduserauthprofile;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the userpass
     */
    public String getUserpass() {
        return userpass;
    }

    /**
     * @param userpass the userpass to set
     */
    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    /**
     * @return the providerid
     */
    public String getProviderid() {
        return providerid;
    }

    /**
     * @param providerid the providerid to set
     */
    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }
    
}
    