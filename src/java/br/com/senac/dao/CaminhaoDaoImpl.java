/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.model.Caminhao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author rossi
 */
public class CaminhaoDaoImpl extends BaseDaoImpl<Caminhao, Long> implements CaminhaoDao, Serializable {

    @Override
    public Caminhao pesquisaEntidadeId(Long id, Session session) throws HibernateException {
   
        return (Caminhao)session.get(Caminhao.class,id);
    }

    @Override
    public List<Caminhao> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Caminhao").list();
    }

    @Override
    public List<Caminhao> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Caminhao c where c.nome like :nome");
        consulta.setParameter("nome", nome +"%");
        return consulta.list();
    }
    
}
