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
    
    private boolean mostrarToolbar = false;
    private String pesqNomeCliente = "";
    private String pesqCnpj;
    private String pesqNomeSoc;
    
    private Session session;
    private ClienteDao dao;
    private Cliente cliente;
    private List<Cliente> clientes;
    private DataModel<Cliente> modelClientes;
    private Endereco endereco;
    private Caminhao caminhao;
    
   private EnderecoDao enderecoDao;
    
    private void abreSessao(){
        if (session == null || !session.isOpen()){
            session = HibernateUtil.abreSessao();
        }
    }
    
    private void mudarToolbar(){
        cliente = new Cliente();
        clientes = new ArrayList();
        pesqNomeCliente ="";
        mostrarToolbar = !mostrarToolbar;
    }
    
    public void pesquisar(){
        dao = new ClienteDaoImpl();
        try{
            abreSessao();
          
            if (!pesqNomeCliente.equals("")){
                clientes = dao.pesquisaPorNome(pesqNomeCliente, session);
            }else if(!pesqCnpj.equals("")) {
             cliente = dao.pesquisarCNPJ(pesqCnpj, session);
            }else if(!pesqNomeSoc.equals("")){
                cliente = (Cliente) dao.pesquisarNomeSocial(pesqNomeSoc, session);
                
            }else{
                 clientes = dao.listaTodos(session);
            }     
        
            modelClientes = new ListDataModel(clientes);
            pesqNomeCliente = null;
            pesqCnpj = null;
            pesqNomeSoc = null;
            
   
    }catch (HibernateException ex){
            System.err.println("Erro ao pesquisar cliente:\n" + ex.getMessage());
    }finally {
           session.close();
        }
}


public void salvar(){
    dao = new ClienteDaoImpl();
    
    try {
        abreSessao();
        cliente.setEnderecos((List<Endereco>) endereco);
        endereco.setPessoa(cliente);
        dao.salvarOuAlterar(cliente, session);
        Mensagem.salvar("Cliente " + cliente.getNome());
        
    } catch (Exception ex) {
        Mensagem.mensagemError("Erro ao salvar\nTente novamente");
        System.err.println("Erro ao pesquisar cliente:\n" + ex.getMessage());
               
    }finally{
        cliente = new Cliente();
        session.close();
    }
}
public void alterarCliente(){
    mostrarToolbar = !mostrarToolbar;
    cliente = modelClientes.getRowData();
 //   endereco = (Endereco) cliente.getEnderecos();
}
    public void excluir(){
        cliente = modelClientes.getRowData();
        dao = new ClienteDaoImpl();
        try {
            abreSessao();
            dao.remover(cliente, session);
            cliente = null;
            modelClientes = null;
            Mensagem.excluir("Cliente" + cliente.getNome());
            cliente = new Cliente();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir cliente\n" + ex.getMessage());
        }finally{
            session.close();
        }
}
    
    

    public boolean isMostrarToolbar() {
        return mostrarToolbar;
    }

    public void setMostrarToolbar(boolean mostrarToolbar) {
        this.mostrarToolbar = mostrarToolbar;
    }

    public String getPesqNomeCliente() {
        return pesqNomeCliente;
    }

    public void setPesqNomeCliente(String pesqNomeCliente) {
        this.pesqNomeCliente = pesqNomeCliente;
    }

    public String getPesqCnpj() {
        return pesqCnpj;
    }

    public void setPesqCnpj(String pesqCnpj) {
        this.pesqCnpj = pesqCnpj;
    }

    public String getPesqNomeSoc() {
        return pesqNomeSoc;
    }

    public void setPesqNomeSoc(String pesqNomeSoc) {
        this.pesqNomeSoc = pesqNomeSoc;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ClienteDao getDao() {
        return dao;
    }

    public void setDao(ClienteDao dao) {
        this.dao = dao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public DataModel<Cliente> getModelClientes() {
        return modelClientes;
    }

    public void setModelClientes(DataModel<Cliente> modelClientes) {
        this.modelClientes = modelClientes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

}