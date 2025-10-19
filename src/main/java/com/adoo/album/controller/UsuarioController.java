package com.adoo.album.controller;

import com.adoo.album.service.IUsuarioService;

public class UsuarioController {

    private IUsuarioService usuarioService;

    public IUsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    

}
