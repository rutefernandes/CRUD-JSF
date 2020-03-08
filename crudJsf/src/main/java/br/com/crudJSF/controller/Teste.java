package br.com.crudJSF.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Teste {
	  public String getHorario() {
		    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		    return "Atualizado em " + sdf.format(new Date());
	   }
}