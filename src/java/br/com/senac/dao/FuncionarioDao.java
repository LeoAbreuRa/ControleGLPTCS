/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.model.Funcionario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 *
 * @author Alunos
 */
public interface FuncionarioDao extends BaseDao<Funcionario, Long>{
    
    List<Funcionario> listarPorUnidade(String unidade, Session session) throws HibernateException;
    
    Funcionario buscarPorCpf(String cpf, Session session) throws HibernateException;
    
    Funcionario buscarPorMatricula(String matricula, Session session) throws HibernateException;
    
    List<Funcionario> listarPorFuncao(String funcao, Session session) throws HibernateException;
}
