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
@Table(name = "cheat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cheat.findAll", query = "SELECT c FROM Cheat c")
    , @NamedQuery(name = "Cheat.findByCheatId", query = "SELECT c FROM Cheat c WHERE c.cheatId = :cheatId")
    , @NamedQuery(name = "Cheat.findByCheatEmail", query = "SELECT c FROM Cheat c WHERE c.cheatEmail = :cheatEmail")
    , @NamedQuery(name = "Cheat.findByCheatPassword", query = "SELECT c FROM Cheat c WHERE c.cheatPassword = :cheatPassword")})
public class Cheat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cheat_id")
    private String cheatId;
    @Basic(optional = false)
    @Column(name = "cheat_email")
    private String cheatEmail;
    @Basic(optional = false)
    @Column(name = "cheat_password")
    private String cheatPassword;

    public Cheat() {
    }

    public Cheat(String cheatId) {
        this.cheatId = cheatId;
    }

    public Cheat(String cheatId, String cheatEmail, String cheatPassword) {
        this.cheatId = cheatId;
        this.cheatEmail = cheatEmail;
        this.cheatPassword = cheatPassword;
    }

    public String getCheatId() {
        return cheatId;
    }

    public void setCheatId(String cheatId) {
        this.cheatId = cheatId;
    }

    public String getCheatEmail() {
        return cheatEmail;
    }

    public void setCheatEmail(String cheatEmail) {
        this.cheatEmail = cheatEmail;
    }

    public String getCheatPassword() {
        return cheatPassword;
    }

    public void setCheatPassword(String cheatPassword) {
        this.cheatPassword = cheatPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cheatId != null ? cheatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheat)) {
            return false;
        }
        Cheat other = (Cheat) object;
        if ((this.cheatId == null && other.cheatId != null) || (this.cheatId != null && !this.cheatId.equals(other.cheatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Cheat[ cheatId=" + cheatId + " ]";
    }
    
}
