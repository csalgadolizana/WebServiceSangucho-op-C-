/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.Venta;
import java.util.Date;
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
@WebService(serviceName = "VentaService")
public class VentaService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Venta> listarVentas() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Venta.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Venta buscarVenta(@WebParam(name = "id") int id) {
        try {
            return em.find(Venta.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int crearVenta(@WebParam(name = "valor") int valor) {
        try {
            Venta p = new Venta(Integer.parseInt("2"), new Date(), valor);
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
    public int modificarVenta(@WebParam(name = "id") int id, @WebParam(name = "fechaVenta") Date fechaVenta, @WebParam(name = "valor") int valor) {
        try {

            Venta p = em.find(Venta.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Venta.class));
//            Query q = em.createQuery(cq);
//            List<Venta> lista = q.getResultList();
//            Venta p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
            p.setFechaVenta(fechaVenta);
            p.setValor(valor);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarVentas -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }
}
