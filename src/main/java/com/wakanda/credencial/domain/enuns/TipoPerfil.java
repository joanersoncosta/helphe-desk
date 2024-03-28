package com.wakanda.credencial.domain.enuns;

public enum TipoPerfil {
	ADMIN("ROLE_CLIENTE"), CLIENTE("ROLE_CLIENTE"), TECNICO("ROLE_TECNICO");
	
	private String perfil;
	
	TipoPerfil(String  perfil){
		this.perfil = perfil;
	}
	
	public String getpPerfil() {
		return this.perfil;
	}
	

}
