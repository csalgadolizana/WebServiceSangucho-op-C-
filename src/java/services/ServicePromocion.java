/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.Promocion;
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
@WebService(serviceName = "ServicePromocion")
public class ServicePromocion {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Promocion> listarPromocions() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Promocion.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Promocion buscarPromociones(@WebParam(name = "id") int id) {
        try {
            return em.find(Promocion.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int crearPromociones(@WebParam(name = "nombre") String nombre, @WebParam(name = "porcenDesc") int porcenDesc) {
        try {
//            Promocion p = new Promocion();
            Promocion p = new Promocion(Integer.parseInt("2"), nombre, porcenDesc);
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
    public int modificarPromociones(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "porcenDesc") int porcenDesc) {
        try {

            Promocion p = em.find(Promocion.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Promocion.class));
//            Query q = em.createQuery(cq);
//            List<Promocion> lista = q.getResultList();
//            Promocion p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
            p.setNombre(nombre);
            p.setPorceDescuento(porcenDesc);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarPromocions -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }
}
