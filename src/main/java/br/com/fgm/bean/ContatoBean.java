package br.com.fgm.bean;

import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fgm.dao.ContatoDao;

@ManagedBean
@SessionScoped
public class ContatoBean {
    private String nome, email, endereco;
    private Calendar dataNascimento;
    private String nomeMask;

/*
public ContatoBean() {
    	//System.out.println("ContatoBean");
    }
*/
    
// para o data table do Primefaces
public ArrayList<ContatoBean> getMessages() {
	System.out.println("ContatoBean - getMessages");
	String mWhereNome = getNomeMask();
        return ContatoDao.getContato(mWhereNome);
    }

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getEndereco() {
	return endereco;
}

public void setEndereco(String endereco) {
	this.endereco = endereco;
}

public Calendar getDataNascimento() {
	return dataNascimento;
}

public void setDataNascimento(Calendar dataNascimento) {
	this.dataNascimento = dataNascimento;
}

public String getNomeMask() {
	return nomeMask;
}

public void setNomeMask(String nomeMask) {
	this.nomeMask = nomeMask;
}

public void geratabela() { 
	System.out.println("geratabela ---------------");
}


}