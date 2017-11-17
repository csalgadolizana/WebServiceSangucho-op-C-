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
@Table(name = "promocion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promocion.findAll", query = "SELECT p FROM Promocion p")
    , @NamedQuery(name = "Promocion.findByIdpromocion", query = "SELECT p FROM Promocion p WHERE p.idpromocion = :idpromocion")
    , @NamedQuery(name = "Promocion.findByNombre", query = "SELECT p FROM Promocion p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Promocion.findByPorceDescuento", query = "SELECT p FROM Promocion p WHERE p.porceDescuento = :porceDescuento")})
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpromocion")
    private Integer idpromocion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porce_descuento")
    private int porceDescuento;

    public Promocion() {
    }

    public Promocion(Integer idpromocion) {
        this.idpromocion = idpromocion;
    }

    public Promocion(Integer idpromocion, String nombre, int porceDescuento) {
        this.idpromocion = idpromocion;
        this.nombre = nombre;
        this.porceDescuento = porceDescuento;
    }

    public Integer getIdpromocion() {
        return idpromocion;
    }

    public void setIdpromocion(Integer idpromocion) {
        this.idpromocion = idpromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorceDescuento() {
        return porceDescuento;
    }

    public void setPorceDescuento(int porceDescuento) {
        this.porceDescuento = porceDescuento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpromocion != null ? idpromocion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocion)) {
            return false;
        }
        Promocion other = (Promocion) object;
        if ((this.idpromocion == null && other.idpromocion != null) || (this.idpromocion != null && !this.idpromocion.equals(other.idpromocion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Promocion[ idpromocion=" + idpromocion + " ]";
    }
    
}
