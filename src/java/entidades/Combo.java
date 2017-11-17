/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC-Cristopher
 */
@Entity
@Table(name = "combo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Combo.findAll", query = "SELECT c FROM Combo c")
    , @NamedQuery(name = "Combo.findByIdcombo", query = "SELECT c FROM Combo c WHERE c.idcombo = :idcombo")
    , @NamedQuery(name = "Combo.findByNombre", query = "SELECT c FROM Combo c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Combo.findByLleva", query = "SELECT c FROM Combo c WHERE c.lleva = :lleva")
    , @NamedQuery(name = "Combo.findByPaga", query = "SELECT c FROM Combo c WHERE c.paga = :paga")})
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcombo")
    private Integer idcombo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lleva")
    private int lleva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paga")
    private int paga;

    public Combo() {
    }

    public Combo(Integer idcombo) {
        this.idcombo = idcombo;
    }

    public Combo(Integer idcombo, String nombre, int lleva, int paga) {
        this.idcombo = idcombo;
        this.nombre = nombre;
        this.lleva = lleva;
        this.paga = paga;
    }

    public Integer getIdcombo() {
        return idcombo;
    }

    public void setIdcombo(Integer idcombo) {
        this.idcombo = idcombo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLleva() {
        return lleva;
    }

    public void setLleva(int lleva) {
        this.lleva = lleva;
    }

    public int getPaga() {
        return paga;
    }

    public void setPaga(int paga) {
        this.paga = paga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcombo != null ? idcombo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Combo)) {
            return false;
        }
        Combo other = (Combo) object;
        if ((this.idcombo == null && other.idcombo != null) || (this.idcombo != null && !this.idcombo.equals(other.idcombo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Combo[ idcombo=" + idcombo + " ]";
    }
    
}
