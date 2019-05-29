/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.control;


import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.ProdutoDao;
import br.com.senac.dao.ProdutoDaoImpl;
import br.com.senac.model.Produto;
import br.com.senac.model.Produto_;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */

@ManagedBean(name ="produtoC")
@ViewScoped
public class ProdutoControle  implements Serializable {
      private boolean mostrar_Toolbar;
      private String pesqProduto = "";
      private String pesqPorTipo = "";
      private String pesqPorMarca = "";
      private Session session;
      private ProdutoDao dao;
      private Produto produto;
      private List<Produto> produtos;
      private DataModel<Produto> modelProduto;
      
      private void abreSessao() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }
      public ProdutoControle() {
        dao = new ProdutoDaoImpl();
    }

    public void mostrar_Toolbar() {
        produto = new Produto();
        produtos = new ArrayList();
        pesqProduto = "";
        pesqPorTipo = "";
        pesqPorMarca = "";
        
        mostrar_Toolbar = !mostrar_Toolbar;
    }
    
     public void novo() {
        mostrar_Toolbar = !mostrar_Toolbar;
    }

    public void novaPesquisa() {
        mostrar_Toolbar = !mostrar_Toolbar;
    }
    
    public void pesquisar() {
        dao = new ProdutoDaoImpl();
        try {
            abreSessao();

            produtos = dao.listarPorTipo(pesqPorTipo, session);
            produtos = dao.listarPorMarca(pesqPorMarca, session);
            modelProduto = new ListDataModel(produtos);
            pesqPorMarca = null;
            pesqPorTipo = null;
            
        } catch (HibernateException ex) {
            System.err.println("Erro ao pesquisar produto:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        dao = new ProdutoDaoImpl();
        try {
            abreSessao();
            dao.salvarOuAlterar(produto, session);

            Mensagem.salvar("Equipamento ");
        } catch (Exception ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
            System.err.println("Erro ao pesquisar equipamento:\n" + ex.getMessage());
        } finally {
            produto = new Produto();

            session.close();
        }
    }

    public void alterarEquipamento() {
        mostrar_Toolbar = !mostrar_Toolbar;
        produto = modelProduto.getRowData();

    }
    
    public void carregarParaAlterar() {
        mostrar_Toolbar = !mostrar_Toolbar;
        produto = modelProduto.getRowData();
        
    }

    public void excluir() {
        produto = modelProduto.getRowData();
        dao = new ProdutoDaoImpl();
        try {
            abreSessao();
            dao.remover(produto, session);
            Mensagem.excluir("Equipamento ");
        } catch (Exception ex) {
            System.err.println("Erro ao excluir equipamento\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }
    public Produto getProduto() {
        if (produto == null) {
            produto = new Produto();

        }
        return produto;
    }

    public boolean isMostrarToolbar() {
        return mostrar_Toolbar;
    }

    public void setMostrarToolbar(boolean mostrarToolbar) {
        this.mostrar_Toolbar = mostrarToolbar;
    }

    public String getPesqProduto() {
        return pesqProduto;
    }

    public void setPesqProduto(String pesqProduto) {
        this.pesqProduto = pesqProduto;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ProdutoDao getDao() {
        return dao;
    }

    public void setDao(ProdutoDao dao) {
        this.dao = dao;
    }

    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public DataModel<Produto> getModelProduto() {
        return modelProduto;
    }

    public void setModelProduto(DataModel<Produto> modelProduto) {
        this.modelProduto = modelProduto;
    }
    
    
}
