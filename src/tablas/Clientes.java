/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael García A
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByCodcliente", query = "SELECT c FROM Clientes c WHERE c.codcliente = :codcliente")
    , @NamedQuery(name = "Clientes.findByCedcliente", query = "SELECT c FROM Clientes c WHERE c.cedcliente = :cedcliente")
    , @NamedQuery(name = "Clientes.findByNomcliente", query = "SELECT c FROM Clientes c WHERE c.nomcliente = :nomcliente")
    , @NamedQuery(name = "Clientes.findByTlfcliente", query = "SELECT c FROM Clientes c WHERE c.tlfcliente = :tlfcliente")
    , @NamedQuery(name = "Clientes.findByEmailcliente", query = "SELECT c FROM Clientes c WHERE c.emailcliente = :emailcliente")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codcliente")
    private Integer codcliente;
    @Basic(optional = false)
    @Column(name = "cedcliente")
    private String cedcliente;
    @Basic(optional = false)
    @Column(name = "nomcliente")
    private String nomcliente;
    @Basic(optional = false)
    @Lob
    @Column(name = "direccliente")
    private String direccliente;
    @Basic(optional = false)
    @Column(name = "tlfcliente")
    private String tlfcliente;
    @Basic(optional = false)
    @Column(name = "emailcliente")
    private String emailcliente;

    public Clientes() {
    }

    public Clientes(Integer codcliente) {
        this.codcliente = codcliente;
    }

    public Clientes(Integer codcliente, String cedcliente, String nomcliente, String direccliente, String tlfcliente, String emailcliente) {
        this.codcliente = codcliente;
        this.cedcliente = cedcliente;
        this.nomcliente = nomcliente;
        this.direccliente = direccliente;
        this.tlfcliente = tlfcliente;
        this.emailcliente = emailcliente;
    }

    public Integer getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Integer codcliente) {
        this.codcliente = codcliente;
    }

    public String getCedcliente() {
        return cedcliente;
    }

    public void setCedcliente(String cedcliente) {
        this.cedcliente = cedcliente;
    }

    public String getNomcliente() {
        return nomcliente;
    }

    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    public String getDireccliente() {
        return direccliente;
    }

    public void setDireccliente(String direccliente) {
        this.direccliente = direccliente;
    }

    public String getTlfcliente() {
        return tlfcliente;
    }

    public void setTlfcliente(String tlfcliente) {
        this.tlfcliente = tlfcliente;
    }

    public String getEmailcliente() {
        return emailcliente;
    }

    public void setEmailcliente(String emailcliente) {
        this.emailcliente = emailcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcliente != null ? codcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.codcliente == null && other.codcliente != null) || (this.codcliente != null && !this.codcliente.equals(other.codcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tablas.Clientes[ codcliente=" + codcliente + " ]";
    }
    
}
