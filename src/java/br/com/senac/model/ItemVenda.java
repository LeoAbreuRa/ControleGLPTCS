/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Pedrão Master
 */

@Entity
@Table(name = "ItemVenda")
@PrimaryKeyJoinColumn(name = "idCliente")
public class ItemVenda implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;
    

    public ItemVenda(Long id, Cliente cliente, Funcionario funcionario, Produto produto) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produto = produto;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemVenda{" + "id=" + id + ", cliente=" + cliente + ", funcionario=" + funcionario + ", produto=" + produto + '}';
    }
    
    
    
    
}
