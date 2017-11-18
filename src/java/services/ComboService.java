/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.Combo;
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
@WebService(serviceName = "ComboService")
public class ComboService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Combo> listarCombos() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Combo.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Combo buscarCombos(@WebParam(name = "id") int id) {
        try {
            return em.find(Combo.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int crearCombos(@WebParam(name = "nombre") String nombre, @WebParam(name = "lleva") int lleva, @WebParam(name = "paga") int paga) {
        try {
//            Combo p = new Combo();
            Combo p = new Combo(Integer.parseInt("2"), nombre, lleva, paga);
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
    public int modificarCombos(@WebParam(name = "id") int id, @WebParam(name = "nombre") String nombre, @WebParam(name = "lleva") int lleva, @WebParam(name = "paga") int paga) {
        try {

            Combo p = em.find(Combo.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Combo.class));
//            Query q = em.createQuery(cq);
//            List<Combo> lista = q.getResultList();
//            Combo p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
            p.setNombre(nombre);
            p.setLleva(lleva);
            p.setPaga(paga);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarCombos -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
