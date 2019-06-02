/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.control;


import br.com.senac.dao.ClienteDao;
import br.com.senac.dao.ClienteDaoImpl;
import br.com.senac.dao.EnderecoDao;
import br.com.senac.dao.HibernateUtil;
import br.com.senac.model.Caminhao;
import br.com.senac.model.Cliente;
import br.com.senac.model.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.swing.ListModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author rossi
 */
@ManagedBean(name ="clienteC")
@ViewScoped
public class ClienteControle implements Serializable {
  
    private Cliente cliente;
    private ClienteDao clienteDao;
    private List<Endereco> enderecos;
    private Endereco endereco;
    private Session session;
    private DataModel<Cliente> modelClientes;
    private List<Cliente> clientes;
    private Caminhao caminhao;
    private ClienteDao dao;
    private EnderecoDao enderecoDao;
    private boolean mostrar_toolbar;
    
   
    private void abreSessao(){
        if (session == null || !session.isOpen()){
            session = HibernateUtil.abreSessao();
       } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }
 public void novo() {
        mostrar_toolbar = !mostrar_toolbar;
    }
    
    public void novaPesquisa() {
        mostrar_toolbar = !mostrar_toolbar;
    }

    public void preparaAlterar() {
        mostrar_toolbar = !mostrar_toolbar;
    }

    
    public void pesquisar(){
        dao = new ClienteDaoImpl();
        try{
            abreSessao();
            clientes = clienteDao.pesquisaPorNome(cliente.getNome(), session);
            modelClientes = new ListDataModel(clientes);
            cliente.setNome(null);
        }catch (Exception e){
            System.out.println("erro ao pesquisar o cliente: " + e.getMessage());
        }finally {
            session.close();
        }            
    }

public void limpar(){
    cliente = new Cliente();
}

public void carregarParaAlterar(){
    mostrar_toolbar = !mostrar_toolbar;
    cliente = modelClientes.getRowData();
    endereco = cliente.getEndereco();
}

    public void excluir(){
        cliente = modelClientes.getRowData();
        dao = new ClienteDaoImpl();
        abreSessao();
        try {
            dao.remover(cliente, session);
            clientes.remove(cliente);
            modelClientes = new ListDataModel(clientes);
            Mensagem.excluir("Cliente" + cliente.getNome());
           limpar();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir cliente\n" + ex.getMessage());
        }finally{
            session.close();
        }
}
    
    public void salvar(){
    dao = new ClienteDaoImpl();
    abreSessao();
    try {
        abreSessao();
        cliente.setEndereco(endereco);
        endereco.setPessoa(cliente);
        dao.salvarOuAlterar(cliente, session);
        Mensagem.salvar("Cliente " + cliente.getNome());
        cliente = null;
        endereco = null;
        
    } catch (HibernateException ex) {
        Mensagem.mensagemError("Erro ao salvar\nTente novamente");
        System.err.println("Erro ao pesquisar cliente:\n" + ex.getMessage());
    }catch (Exception e) {
        System.out.println("Erro no salvar clienteDao Controle" + e.getMessage());
    }finally{
       // cliente = new Cliente();
        session.close();
    }
    }
    public void limparTela(){
        limpar();
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
    }
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public DataModel<Cliente> getModelClientes() {
        return modelClientes;
    }

    public void setModelClientes(DataModel<Cliente> modelClientes) {
        this.modelClientes = modelClientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public ClienteDao getDao() {
        return dao;
    }

    public void setDao(ClienteDao dao) {
        this.dao = dao;
    }

    public EnderecoDao getEnderecoDao() {
        return enderecoDao;
    }

    public void setEnderecoDao(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public boolean isMostrar_toolbar() {
        return mostrar_toolbar;
    }

    public void setMostrar_toolbar(boolean mostrar_toolbar) {
        this.mostrar_toolbar = mostrar_toolbar;
    }
    
}

    