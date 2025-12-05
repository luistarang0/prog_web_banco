package com.mycompany.modulo_banco.controller;

import com.mycompany.modulo_banco.model.Usuario;
import com.mycompany.modulo_banco.model.UsuarioDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import com.mycompany.modulo_banco.model.TipoUsuario;
import com.mycompany.modulo_banco.model.EstadoUsuario;

@WebServlet("/registro")
public class RegistServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String nombreCompleto = request.getParameter("nombre_completo");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String password = request.getParameter("password_hash");

            if (nombreCompleto == null || nombreCompleto.trim().isEmpty()
                    || correo == null || correo.trim().isEmpty()
                    || password == null || password.trim().isEmpty()) {

                request.setAttribute("error", "Todos los campos obligatorios deben ser completados");
                request.getRequestDispatcher("/views/register.jsp").forward(request, response);
                return;
            }

            if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("error", "El formato del correo electrónico no es válido");
                request.getRequestDispatcher("/views/register.jsp").forward(request, response);
                return;
            }


            /*if (usuarioDAO.existeUsuario(idUsuario)) {
                request.setAttribute("error", "El ID de usuario ya está registrado");
                request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
                return;
            }
            
            // 5. Verificar si el correo ya existe
            if (usuarioDAO.existeCorreo(correo)) {
                request.setAttribute("error", "El correo electrónico ya está registrado");
                request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
                return;
            }
             */
            //String passwordHash = hashPassword(password);
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombreCompleto);
            nuevoUsuario.setEmail(correo);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setPassword(password);

            nuevoUsuario.setTipo(TipoUsuario.cliente);
            nuevoUsuario.setEstado(EstadoUsuario.activo);
            nuevoUsuario.setFechaRegistro(LocalDateTime.now());

            boolean registrado = usuarioDAO.registrarUsuario(nuevoUsuario);

            if (registrado) {
                HttpSession session = request.getSession();
                session.setAttribute("mensajeExito", "Registro exitoso. Ya puedes iniciar sesión.");

                response.sendRedirect(request.getContextPath() + "/views/login.jsp");

            } else {
                request.setAttribute("error", "Error al registrar el usuario. Inténtalo de nuevo.");
                request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "El ID de usuario debe ser numérico");
            request.getRequestDispatcher("/views/registro.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("Error en RegistroServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error interno del servidor. Contacta al administrador.");
            request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
        }
    }



    /*private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear contraseña", e);
        }
    }*/
}
