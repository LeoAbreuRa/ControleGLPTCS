/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rossi
 */
@Entity
@Table(name = "caminhao")
public class Caminhao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    private String nomeMotorista;
    private String placaCaminhao;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Caminhao() {
    }

    public Caminhao(Long id, String nomeMotorista, String placaCaminhao) {
        this.id = id;
        this.nomeMotorista = nomeMotorista;
        this.placaCaminhao = placaCaminhao;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getPlacaCaminhao() {
        return placaCaminhao;
    }

    public void setPlacaCaminhao(String placaCaminhao) {
        this.placaCaminhao = placaCaminhao;
    }

    
}    
    
    
    
    

