package com.mycompany.modulo_banco.controller;

import com.mycompany.modulo_banco.model.Cuenta;
import com.mycompany.modulo_banco.model.CuentaDAO;
import com.mycompany.modulo_banco.model.Movimiento;
import com.mycompany.modulo_banco.model.MovimientoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author luist
 */
@WebServlet(name = "dashboard", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    private CuentaDAO cuentaDAO = new CuentaDAO();
    private MovimientoDAO movimientoDAO = new MovimientoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        // 2. Obtener la cuenta del cliente de la sesión
        Cuenta cuenta = (Cuenta) session.getAttribute("cuentaCliente");
        if (cuenta == null) {
            // Esto no debería pasar si el login es correcto, pero es buena práctica
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se encontró la cuenta asociada.");
            return;
        }

  

        // 4. Obtener la lista de transacciones filtradas
        List<Movimiento> movimientos = movimientoDAO.obtenerMovimientos(
                cuenta.getIdCuenta()
        );

        // 6. Preparar datos para el JSP
        request.setAttribute("movimientos", movimientos);

        request.getRequestDispatcher("/views/dashboard_cliente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
