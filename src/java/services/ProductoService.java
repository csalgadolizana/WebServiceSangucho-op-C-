/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.Producto;
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
@WebService(serviceName = "ProductoService")
public class ProductoService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Producto> listarProductos() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Producto.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Producto buscarProductos(@WebParam(name = "id") int id) {
        try {
            return em.find(Producto.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int crearProductos(@WebParam(name = "nombre") String nombre, @WebParam(name = "stock") int stock) {
        try {
            Producto p = new Producto();
            p.setIdproducto(Integer.parseInt("2"));
            p.setNombre(nombre);
            p.setFechaEntrada(new Date());
            p.setStock(stock);
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
    public int modificarProductos(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "stock") int stock) {
        try {

            Producto p = em.find(Producto.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Producto.class));
//            Query q = em.createQuery(cq);
//            List<Producto> lista = q.getResultList();
//            Producto p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
            p.setNombre(nombre);
            p.setFechaEntrada(new Date());
            p.setStock(stock);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarProductos -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
