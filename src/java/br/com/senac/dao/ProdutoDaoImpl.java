/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.model.Produto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class ProdutoDaoImpl extends BaseDaoImpl<Produto, Long> implements ProdutoDao, Serializable {

    @Override
    public Produto pesquisaEntidadeId(Long id, Session session) throws HibernateException {
         return (Produto) session.get(Produto.class, id);
    }

    @Override
    public List<Produto> listaTodos(Session session) throws HibernateException {
         return session.createQuery("from Produto").list();
    }

    @Override
    public List<Produto> pesquisaPorNome(String tipoProduto, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Produto where nome like :tipoProduto");
        consulta.setParameter("tipoProduto", "%" + tipoProduto + "%");
        return consulta.list();
    }

    @Override
    public List<Produto> listarPorTipo(String tipoProduto, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Produto where nome like :tipoProduto");
        consulta.setParameter("tipoProduto", "%" + tipoProduto + "%");
        return consulta.list();
    }

    @Override
    public List<Produto> listarPorMarca(String marcaProduto, Session session) throws HibernateException {
         Query consulta = session.createQuery("from Produto where nome like :marcaProduto");
        consulta.setParameter("marcaProduto", "%" + marcaProduto + "%");
        return consulta.list();
    }
   
    
}
