/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;


import br.com.senac.model.Cliente;
import br.com.senac.model.ItemVenda;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public class ItemVendaDaoImpl extends BaseDaoImpl<ItemVenda, Long> implements ItemVendaDao, Serializable{ 

    @Override
    public ItemVenda pesquisaEntidadeId(Long id, Session session) throws HibernateException {
         return (ItemVenda) session.get(ItemVenda.class, id);
    }

    @Override
    public List<ItemVenda> listaTodos(Session session) throws HibernateException {
         return session.createQuery("from ItemVenda").list();
    }

    
    public ItemVenda pesquisaPorId(Long id, Session session) throws HibernateException {
       return (ItemVenda) session.get(ItemVenda.class, id); 
    }

    @Override
    public List<ItemVenda> pesquisaPorNome(String nome, Session session) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
