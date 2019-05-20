package br.com.senac.control;


import br.com.senac.dao.FuncionarioDao;
import br.com.senac.dao.FuncionarioDaoImpl;
import br.com.senac.dao.HibernateUtil;
import br.com.senac.model.Endereco;
import br.com.senac.model.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Leo
 */
@ManagedBean(name = "funcionarioC")
@ViewScoped
public class FuncionarioControl implements Serializable{
    
    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao;
    private List<Endereco> enderecos;
    private Endereco endereco;
    private Session session;
    private DataModel<Funcionario> modelFuncionarios;
    private List<Funcionario> funcionarios;
    private boolean mostrar_toolbar;
    
    private void abreSessao(){
        if(session == null){
            session = HibernateUtil.abreSessao();
        }else if(!session.isOpen()){
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
    
    public void pesquisar() {
        funcionarioDao = new FuncionarioDaoImpl();
        try {
            abreSessao();
            funcionarios = funcionarioDao.pesquisaPorNome(funcionario.getNome(), session);
            modelFuncionarios = new ListDataModel(funcionarios);
            funcionario.setNome(null);
        } catch (Exception e) {
            System.out.println("erro ao pesquisar funcionario por nome: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public void limpar(){
        funcionario = new Funcionario();
    }
    
    public void carregarParaAlterar(){
        mostrar_toolbar = !mostrar_toolbar;
        funcionario = modelFuncionarios.getRowData();
    }
    
    public void excluir(){
        funcionario = modelFuncionarios.getRowData();
        funcionarioDao = new FuncionarioDaoImpl();
        abreSessao();
        try {
            funcionarioDao.remover(funcionario, session);
            funcionarios.remove(funcionario);
            modelFuncionarios = new ListDataModel(funcionarios);
            Mensagem.excluir("Funcionario");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir: " + e.getMessage());
        }finally{
            session.close();
        }
    }
    
    public void salvar(){
        funcionarioDao = new FuncionarioDaoImpl();
        abreSessao();
        try {
            funcionario.setEndereco(endereco);
            endereco.setPessoa(funcionario);
            funcionarioDao.salvarOuAlterar(funcionario, session);
            Mensagem.salvar("Funcionario: " + funcionario.getNome());
            funcionario = null;
        } catch (HibernateException e) {
            System.out.println("Erro ao salvar funcionario" + e.getMessage());
        
        }finally{
            session.close();
        }
    }
    
    public void limparTela(){
        limpar();
    }

    public Funcionario getFuncionario() {
        if(funcionario == null){
            funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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

    public DataModel<Funcionario> getModelFuncionarios() {
        return modelFuncionarios;
    }

    public void setModelFuncionarios(DataModel<Funcionario> modelFuncionarios) {
        this.modelFuncionarios = modelFuncionarios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public boolean isMostrar_toolbar() {
        return mostrar_toolbar;
    }

    public void setMostrar_toolbar(boolean mostrar_toolbar) {
        this.mostrar_toolbar = mostrar_toolbar;
    }
    
    
    
}
