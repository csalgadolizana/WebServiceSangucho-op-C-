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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC-Cristopher
 */
@Entity
@Table(name = "detalle_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d")
    , @NamedQuery(name = "DetalleVenta.findByIdDetalle", query = "SELECT d FROM DetalleVenta d WHERE d.idDetalle = :idDetalle")
    , @NamedQuery(name = "DetalleVenta.findByIdVenta", query = "SELECT d FROM DetalleVenta d WHERE d.idVenta = :idVenta")
    , @NamedQuery(name = "DetalleVenta.findByIdProducto", query = "SELECT d FROM DetalleVenta d WHERE d.idProducto = :idProducto")
    , @NamedQuery(name = "DetalleVenta.findByIdPromocion", query = "SELECT d FROM DetalleVenta d WHERE d.idPromocion = :idPromocion")
    , @NamedQuery(name = "DetalleVenta.findByIdCombo", query = "SELECT d FROM DetalleVenta d WHERE d.idCombo = :idCombo")
    , @NamedQuery(name = "DetalleVenta.findByCantidadProducto", query = "SELECT d FROM DetalleVenta d WHERE d.cantidadProducto = :cantidadProducto")})
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_venta")
    private int idVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;
    @Column(name = "id_promocion")
    private Integer idPromocion;
    @Column(name = "id_combo")
    private Integer idCombo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_producto")
    private int cantidadProducto;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public DetalleVenta(Integer idDetalle, int idVenta, int idProducto, int cantidadProducto) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Integer getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Integer idCombo) {
        this.idCombo = idCombo;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVenta)) {
            return false;
        }
        DetalleVenta other = (DetalleVenta) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleVenta[ idDetalle=" + idDetalle + " ]";
    }
    
}
