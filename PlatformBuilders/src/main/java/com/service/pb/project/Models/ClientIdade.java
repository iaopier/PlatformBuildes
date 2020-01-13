package com.service.pb.project.Models;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class ClientIdade extends Client{
	private float idade;
	
	public ClientIdade() {
		super();
	}
	
	public float getIdade() {
		return idade;
	}

	public void setIdade(Date date) {
		this.idade = TimeUnit.DAYS.convert(date.getTime() - this.getDataNascimento().getTime(),TimeUnit.MILLISECONDS) / 365;
	}
	
}
