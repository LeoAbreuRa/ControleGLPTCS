/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.model.Cliente;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author rossi
 */
public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> implements ClienteDao, Serializable {

    @Override
    public Cliente pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Cliente) session.get(Cliente.class, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Cliente").list(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Cliente c where c.nome like :nome");
        consulta.setParameter("nome", nome + "%");
        return consulta.list();
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente pesquisarCNPJ(String cnpj, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Cliente c where cnpj = :cnpj");
        consulta.setParameter("cnpj", cnpj);
        return (Cliente)consulta.uniqueResult();
    }

    @Override
    public List<Cliente> pesquisarNomeSocial(String nomeSocial, Session session) throws HibernateException {

        Query consulta = session.createQuery("from Cliente where nomeSocial like :nomeSocial");
        consulta.setParameter("nomeSocial","%" + nomeSocial + "%");
        return consulta.list();
    }
    
}
