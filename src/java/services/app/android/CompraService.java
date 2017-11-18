/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.app.android;

import clases.app.android.catalogo.Compra;
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
@WebService(serviceName = "CompraService")
public class CompraService {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    @WebMethod
    public List<Compra> listarCompras() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Compra.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    @WebMethod
    public Compra buscarCompras(@WebParam(name = "id") int id) {
        try {
            return em.find(Compra.class, new Integer(id));
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod
    public int crearCompras(@WebParam(name = "idusuario") int idusuario, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "precio") int precio,@WebParam(name = "idcatalogo") int idcatalogo) {
        try {
            Compra p = new Compra();
            p.setId(Integer.parseInt("2"));
            p.setIdusuario(idusuario);
            p.setIdcatalogo(idcatalogo);
            p.setCantidad(cantidad);
            p.setPrecio(precio);
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
    public int modificarCompras(@WebParam(name = "id") int id,@WebParam(name = "idusuario") int idusuario, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "precio") int precio) {
        try {

            Compra p = em.find(Compra.class, new Integer(id));
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Compra.class));
//            Query q = em.createQuery(cq);
//            List<Compra> lista = q.getResultList();
//            Compra p = lista.stream().filter((x) -> x.getIdproducto().intValue() == id).findFirst().orElse(null);
//            p.setNombre(nombre);
//            p.setFechaEntrada(new Date());
//            p.setStock(stock);
            utx.begin();
            p = em.merge(p);
            utx.commit();
            return 1;
        } catch (Exception ex) {
            System.out.println("modificarCompras -> Error");
            System.out.println(ex.getMessage());
        }
        return 0;
    }
}
