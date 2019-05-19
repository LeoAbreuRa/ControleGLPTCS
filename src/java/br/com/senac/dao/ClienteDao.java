/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.model.Cliente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author rossi
 */
public interface ClienteDao extends BaseDao<Cliente, Long> {
    
   Cliente pesquisarCNPJ( String cnpj, Session session)throws HibernateException;
    
   List<Cliente> pesquisarNomeSocial(String nomeSocial, Session session)throws HibernateException;
    


    
}
