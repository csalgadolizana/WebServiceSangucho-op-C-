/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.app.android;

import clases.app.android.catalogo.Catalogo;
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
@WebService(serviceName = "CatalogoService")
public class CatalogoService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Catalogo> listarCatalogo() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Catalogo.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Catalogo buscarCatalogo(@WebParam(name = "id") int id) {
        try {
            return em.find(Catalogo.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

}
