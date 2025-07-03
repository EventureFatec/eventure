package com.github.eventure.controllers;

public class IdController {
	    private static Integer usuarioLogado = null;
	    private static Integer empresaLogada = null;

	    public static void setIdUser(int userId) {
	        usuarioLogado = userId;
	        empresaLogada = null; 
	    }

	    
	    public static Integer getIdUser() {
	        return usuarioLogado;
	    }

	    
	    public static void setIdEmpresa(int empresaId) {
	        empresaLogada = empresaId;
	        usuarioLogado = null; 
	    }

	    
	    public static Integer getIdEmpresa() {
	        return empresaLogada;
	    }

	    
	    public static void logoffUser() {
	        usuarioLogado = null;
	    }

	    
	    public static void logoffEmpresa() {
	        empresaLogada = null;
	    }

	    
	    public static boolean isAlguemLogado() {
	        return usuarioLogado != null || empresaLogada != null;
	    }

	    
	    public static boolean isUsuarioLogado() {
	        return usuarioLogado != null;
	    }

	    
	    public static boolean isEmpresaLogada() {
	        return empresaLogada != null;
	    }
	}
