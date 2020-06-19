package com.walter.api.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	USUARIO(2, "ROLE_USUARIO");	
	
	private int cod;
	private String nome;
	
	private Perfil(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}
	
	public int getCod() {
		return cod;
	}

	public String getNome() {
		return nome;
	}
	
	public static Perfil toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for(Perfil tp : Perfil.values()) {
			if(id.equals(tp.getCod())) {
				return tp;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + id);
	}

}
