/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.Personal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author PC-Cristopher
 */
@WebService(serviceName = "PersonalService")
public class PersonalService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Personal> listarPersonas() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Personal.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Personal buscarPersonal(@WebParam(name = "id") int id) {
        try {
            return em.find(Personal.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int eliminarPersonal(@WebParam(name = "id") int id) {
        try {
            utx.begin();
            Personal personal;
            personal = em.getReference(Personal.class, id);
            personal.getIdpersonal();
            em.remove(personal);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("Error ");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @WebMethod
    public int crearPersonal(@WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "fechaNacimiento") Date fechaNacimeinto
    ) {
        try {
            Personal p = new Personal(Integer.parseInt("2"), nombre, apellido, fechaNacimeinto);
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
    public int modificarPersonal(@WebParam(name = "id") int id,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "cantidad") String apellido,
            @WebParam(name = "fechaNacimiento") Date fechaNacimeinto
    ) {
        try {

            Personal p = em.find(Personal.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Personal.class));
//            Query q = em.createQuery(cq);
//            List<Personal> lista = q.getResultList();
//            Personal p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setFechaNacimiento(fechaNacimeinto);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarPersonals -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
