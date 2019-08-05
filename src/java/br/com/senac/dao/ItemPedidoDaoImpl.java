/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;


import br.com.senac.model.ItemPedido;
import static br.com.senac.model.Produto_.marcaProduto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public class ItemPedidoDaoImpl extends BaseDaoImpl<ItemPedido, Long> implements ItemPedidoDao, Serializable{ 

    @Override
    public ItemPedido pesquisaEntidadeId(Long id, Session session) throws HibernateException {
         return (ItemPedido) session.get(ItemPedido.class, id);
    }

    @Override
    public List<ItemPedido> listaTodos(Session session) throws HibernateException {
         return session.createQuery("from ItemPedido").list();
    }

    
    public ItemPedido pesquisaPorId(Long id, Session session) throws HibernateException {
       return (ItemPedido) session.get(ItemPedido.class, id); 
    }

    @Override
    public List<ItemPedido> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Produto p where p.marcaProduto like :marcaProduto");
        consulta.setParameter("marcaProduto", "%" + marcaProduto + "%");
        return consulta.list();
    }

}
