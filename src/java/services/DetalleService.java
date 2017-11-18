/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.DetalleVenta;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author PC-Cristopher
 */
@WebService(serviceName = "DetalleVentaService")
public class DetalleService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<DetalleVenta> listarDetalleVentas() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(DetalleVenta.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public DetalleVenta buscarDetalleVentas(@WebParam(name = "id") int id) {
        try {
            return em.find(DetalleVenta.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int crearDetalleVentas(@WebParam(name = "idVenta") int idVenta, @WebParam(name = "idProducto") int idProducto, @WebParam(name = "cantidad") int cantidad,@WebParam(name = "promo") int promo,@WebParam(name = "combo") int combo) {
        try {
            DetalleVenta p = new DetalleVenta(Integer.parseInt("2"), idVenta, idProducto, cantidad);
            p.setIdCombo(new Integer(combo));
            p.setIdPromocion(new Integer(idProducto));
            utx.begin();
            em.persist(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("Error ");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @WebMethod
    public int modificarDetalleVentas(@WebParam(name = "id") int id, @WebParam(name = "idVenta") int idVenta, @WebParam(name = "idProducto") int idProducto, @WebParam(name = "cantidad") int cantidad) {
        try {

            DetalleVenta p = em.find(DetalleVenta.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(DetalleVenta.class));
//            Query q = em.createQuery(cq);
//            List<DetalleVenta> lista = q.getResultList();
//            DetalleVenta p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
            p.setIdVenta(idVenta);
            p.setIdProducto(idProducto);
            p.setCantidadProducto(cantidad);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarDetalleVentas -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
