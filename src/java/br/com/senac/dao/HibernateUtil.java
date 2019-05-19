    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.model.Caminhao;
import br.com.senac.model.Cliente;
import br.com.senac.model.Endereco;
import br.com.senac.model.Funcionario;
import br.com.senac.model.Pessoa;
import br.com.senac.model.Produto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Alunos
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static { //Singleton
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Pessoa.class);
            cfg.addAnnotatedClass(Funcionario.class);
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(Caminhao.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Produto.class);
            

            cfg.configure("/br/com/senac/dao/hibernate.cfg.xml");

            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder().
                    applySettings(cfg.getProperties()).build();

            sessionFactory = cfg.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Erro ao construir session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abreSessao() {
        return sessionFactory.openSession();
    }
    
}
