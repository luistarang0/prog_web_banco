
package com.mycompany.modulo_banco.controller;

import com.mycompany.modulo_banco.model.Usuario;
import com.mycompany.modulo_banco.model.UsuarioDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BancoServlet", urlPatterns = {"/modulo_banco/*"})
public class BancoServlet  extends HttpServlet{
    
    private UsuarioDAO usuarioDAO; 
    
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
}
