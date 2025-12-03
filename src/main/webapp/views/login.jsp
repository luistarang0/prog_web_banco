<%@page import="com.mycompany.modulo_banco.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - Halcón Capital</title>
    </head>
    <body bgcolor="#F0F9FF" style="font-family: Arial, sans-serif; margin: 0; padding: 20px;">

    <center>
        <table width="400" cellpadding="20" cellspacing="0" border="0" bgcolor="#FFFFFF" style="margin-top: 50px; border-radius: 10px; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">

            <!-- Header -->
            <tr>
                <td align="center" bgcolor="#1E3A8A" style="border-radius: 10px 10px 0 0; padding: 30px;">
                    <div style="width: 60px; height: 60px; background-color: #F97316; border-radius: 10px; display: inline-block; line-height: 60px; color: white; font-size: 24px; font-weight: bold;">H</div>
                    <h1 style="color: white; margin: 15px 0 5px 0; font-size: 24px;">Halcón Capital</h1>
                    <p style="color: #E5E7EB; margin: 0; font-size: 14px;">Sistema Bancario</p>
                </td>
            </tr>

            <!-- Form -->
            <tr>
                <td style="padding: 30px;">
                    <h2 style="color: #1E3A8A; margin: 0 0 20px 0; font-size: 20px; text-align: center;">Iniciar Sesión</h2>

                    <form action="<%= request.getContextPath() %>/auth" method="post">
                        <table width="100%" cellpadding="5" cellspacing="0" border="0">
                            <tr>
                                <td>
                                    <label style="color: #4B5563; font-size: 14px; font-weight: bold;">Usuario:</label>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom: 15px;">
                                    <input type="text" 
                                           name="username" 
                                           placeholder="Ingresa tu usuario"
                                           style="width: 100%; padding: 10px; border: 2px solid #E5E7EB; border-radius: 5px; font-size: 14px; box-sizing: border-box;"
                                           required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label style="color: #4B5563; font-size: 14px; font-weight: bold;">Contraseña:</label>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom: 20px;">
                                    <input type="password" 
                                           name="password" 
                                           placeholder="Ingresa tu contraseña"
                                           style="width: 100%; padding: 10px; border: 2px solid #E5E7EB; border-radius: 5px; font-size: 14px; box-sizing: border-box;"
                                           required>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    <button type="submit" 
                                            style="width: 100%; padding: 12px; background-color: #F97316; color: white; border: none; border-radius: 5px; font-size: 16px; font-weight: bold; cursor: pointer;">
                                        Iniciar Sesión
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </form>
                        
                        <% if (request.getAttribute("error") != null) { %>
                        <p style="color: #EF4444; text-align: center; margin-top: 15px; font-weight: bold;">
                            <%= request.getAttribute("error") %>
                        </p>
                    <% } %>
                </td>
            </tr>                   

        </table>
    </center>
</body>
</html>
