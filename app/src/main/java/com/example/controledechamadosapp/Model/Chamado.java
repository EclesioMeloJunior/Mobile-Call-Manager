package com.example.controledechamadosapp.Model;

import java.io.Serializable;
import java.util.Date;

public class Chamado implements Serializable {
    public int id;
    public String assunto;
    public String descricao;
    public Date data_criacao;
    public ChamadoStatus status;

    public Usuario usuarioDestino;
    public Usuario usuarioLancamento;

    public Chamado() {
    }

    public Chamado(int id, String assunto, String descricao, Date data_criacao, ChamadoStatus status) {
        this.id = id;
        this.assunto = assunto;
        this.descricao = descricao;
        this.data_criacao = data_criacao;
        this.status = status;
    }

    public Chamado(String assunto, String descricao) {
        this.assunto = assunto;
        this.descricao = descricao;
        this.status = ChamadoStatus.ABERTO;
        this.data_criacao = new Date();
    }

    @Override
    public String toString() {
        return this.assunto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public ChamadoStatus getStatus() {
        return status;
    }

    public void setStatus(ChamadoStatus status) {
        this.status = status;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public Usuario getUsuarioLancamento() {
        return usuarioLancamento;
    }

    public void setUsuarioLancamento(Usuario usuarioLancamento) {
        this.usuarioLancamento = usuarioLancamento;
    }
}
