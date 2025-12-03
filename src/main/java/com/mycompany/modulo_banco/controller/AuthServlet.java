
package com.mycompany.modulo_banco.controller;

import com.mycompany.modulo_banco.model.Usuario;
import com.mycompany.modulo_banco.model.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet{
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Usuario usuario = usuarioDAO.validarLogin(username, password);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            
            session.setAttribute("usuarioLogueado", usuario);
            session.setAttribute("usuarioID", usuario.getId());
            session.setAttribute("nombreUsuario", usuario.getNombre());
            
            response.sendRedirect(request.getContextPath() + "/views/dashboard.jsp");
        } else {
            request.setAttribute("error", "Numero de cuenta o contraseña incorrectos");
            
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
    
    
}
