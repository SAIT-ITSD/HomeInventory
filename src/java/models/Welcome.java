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
@Table(name = "welcome")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Welcome.findAll", query = "SELECT w FROM Welcome w")
    , @NamedQuery(name = "Welcome.findByWelcomeId", query = "SELECT w FROM Welcome w WHERE w.welcomeId = :welcomeId")
    , @NamedQuery(name = "Welcome.findByWelcomeEmail", query = "SELECT w FROM Welcome w WHERE w.welcomeEmail = :welcomeEmail")})
public class Welcome implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "welcome_id")
    private String welcomeId;
    @Basic(optional = false)
    @Column(name = "welcome_email")
    private String welcomeEmail;

    public Welcome() {
    }

    public Welcome(String welcomeId) {
        this.welcomeId = welcomeId;
    }

    public Welcome(String welcomeId, String welcomeEmail) {
        this.welcomeId = welcomeId;
        this.welcomeEmail = welcomeEmail;
    }

    public String getWelcomeId() {
        return welcomeId;
    }

    public void setWelcomeId(String welcomeId) {
        this.welcomeId = welcomeId;
    }

    public String getWelcomeEmail() {
        return welcomeEmail;
    }

    public void setWelcomeEmail(String welcomeEmail) {
        this.welcomeEmail = welcomeEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (welcomeId != null ? welcomeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Welcome)) {
            return false;
        }
        Welcome other = (Welcome) object;
        if ((this.welcomeId == null && other.welcomeId != null) || (this.welcomeId != null && !this.welcomeId.equals(other.welcomeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Welcome[ welcomeId=" + welcomeId + " ]";
    }
    
}
