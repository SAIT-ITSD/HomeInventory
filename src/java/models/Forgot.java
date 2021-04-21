/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 828200
 */
@Entity
@Table(name = "forgot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forgot.findAll", query = "SELECT f FROM Forgot f")
    , @NamedQuery(name = "Forgot.findByForgotId", query = "SELECT f FROM Forgot f WHERE f.forgotId = :forgotId")
    , @NamedQuery(name = "Forgot.findByForgotEmail", query = "SELECT f FROM Forgot f WHERE f.forgotEmail = :forgotEmail")})
public class Forgot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "forgot_id")
    private String forgotId;
    @Basic(optional = false)
    @Column(name = "forgot_email")
    private String forgotEmail;

    public Forgot() {
    }

    public Forgot(String forgotId) {
        this.forgotId = forgotId;
    }

    public Forgot(String forgotId, String forgotEmail) {
        this.forgotId = forgotId;
        this.forgotEmail = forgotEmail;
    }

    public String getForgotId() {
        return forgotId;
    }

    public void setForgotId(String forgotId) {
        this.forgotId = forgotId;
    }

    public String getForgotEmail() {
        return forgotEmail;
    }

    public void setForgotEmail(String forgotEmail) {
        this.forgotEmail = forgotEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (forgotId != null ? forgotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forgot)) {
            return false;
        }
        Forgot other = (Forgot) object;
        if ((this.forgotId == null && other.forgotId != null) || (this.forgotId != null && !this.forgotId.equals(other.forgotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Forgot[ forgotId=" + forgotId + " ]";
    }
    
}
