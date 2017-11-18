/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entidades.Login;
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
@WebService(serviceName = "LoginService")
public class LoginService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public Login logueo(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Login.class));
            Query q = em.createQuery(cq);
            List<Login> lisLogin = q.getResultList();
            return lisLogin.stream().filter((x) -> x.getUsername().equals(username) && x.getPassword().equals(password)).findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int loginAndroid(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Login.class));
            Query q = em.createQuery(cq);
            List<Login> lisLogin = q.getResultList();
            return lisLogin.stream().filter((x) -> x.getUsername().equals(username) && x.getPassword().equals(password)).findFirst().orElse(null) == null ? 0 : 1;
        } catch (Exception e) {
            System.err.println("Error en loginAndroid()");
            System.err.println(e.getMessage());
            return 0;
        }
    }

}
