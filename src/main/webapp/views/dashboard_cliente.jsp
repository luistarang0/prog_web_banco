

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Portal de Clientes - Halcón Capital</title>
    </head>
    <body bgcolor="#F0F9FF" style="font-family: Arial, sans-serif; margin: 0; padding: 0;">

        <!-- Header -->
        <table width="100%" cellpadding="10" cellspacing="0" border="0" bgcolor="#1E3A8A">
            <tr>
                <td>
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td width="200">
                                <div style="display: inline-block;">
                                    <span style="background-color: #F97316; color: white; padding: 8px 12px; border-radius: 5px; font-weight: bold; margin-right: 10px;">H</span>
                                    <span style="color: white; font-size: 18px; font-weight: bold;">Halcón Capital</span>
                                </div>
                            </td>
                            <td align="center">
                                <span style="color: white; font-size: 16px; font-weight: bold;">Portal de Clientes</span>
                            </td>
                            <td width="200" align="right">
                                <span style="color: #E5E7EB; font-size: 14px;">Cliente: ${sessionScope.nombreUsuario}</span>
                                <button 
                                    onclick="window.location.href = '<%= request.getContextPath()%>/views/login.jsp'"
                                    style="margin-left: 15px; padding: 5px 10px; background-color: #F97316; color: white; border: none; border-radius: 3px; cursor: pointer;">Salir</button>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        <!-- Navigation Menu -->
        <table width="100%" cellpadding="8" cellspacing="0" border="0" bgcolor="#4B5563">
            <tr>
                <td align="center">
                    <table cellpadding="8" cellspacing="0" border="0">
                        <tr>
                            <td><a href="dashboard_clientes.html" style="color: white; text-decoration: none; padding: 8px 15px; background-color: #F97316; border-radius: 3px;">Mi Cuenta</a></td>                        
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        <!-- Main Content -->
        <table width="100%" cellpadding="20" cellspacing="0" border="0">
            <tr>
                <td>

                    <!-- Account Summary -->
                    <table width="100%" cellpadding="15" cellspacing="0" border="0" bgcolor="#FFFFFF" style="border-radius: 5px; margin-bottom: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                        <tr>
                            <td>
                                <h3 style="color: #1E3A8A; margin: 0 0 15px 0;">Resumen de Cuenta</h3>

                                <table width="100%" cellpadding="10" cellspacing="10" border="0">
                                    <tr>
                                        <td width="50%" bgcolor="#F0F9FF" style="border-radius: 5px; padding: 20px; text-align: center;">
                                            <div style="font-size: 28px; font-weight: bold; color: #1E3A8A; margin-bottom: 5px;">$<fmt:formatNumber value="${sessionScope.cuentaCliente.saldo}" type="number" minFractionDigits="2" maxFractionDigits="2"/></div>
                                            <div style="color: #4B5563; font-size: 16px;">Saldo Disponible</div>
                                        </td>
                                        <td width="50%" style="padding-left: 20px;">
                                            <table width="100%" cellpadding="8" cellspacing="0" border="0">
                                                <tr>
                                                    <td style="color: #4B5563; font-size: 14px;">Cuenta Principal:</td>
                                                    <td style="color: #1E3A8A; font-weight: bold; font-size: 18px;">**** **** **** ${sessionScope.cuentaCliente.ultimosCuatroDigitos}</td>
                                                </tr>
                                                <tr>
                                                    <td style="color: #4B5563; font-size: 14px;">Último Movimiento:</td>
                                                    <td style="color: #1E3A8A; font-weight: bold; font-size: 14px;">22/09/2025</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                    <!-- Content Row -->
                    <table width="100%" cellpadding="0" cellspacing="10" border="0">
                        <tr>
                            <!-- Left Column -->
                            <td width="70%" valign="top">

                                <!-- Recent Movements -->
                                <table width="100%" cellpadding="15" cellspacing="0" border="0" bgcolor="#FFFFFF" style="border-radius: 5px; margin-bottom: 15px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                                    <tr>
                                        <td>
                                            <div style="margin-bottom: 15px;">
                                                <h3 style="color: #1E3A8A; margin: 0; display: inline-block;">Mis Movimientos Recientes</h3>

                                                <div style="clear: both;"></div>
                                            </div>

                                            <table class="tabla-movimientos">
                                                <thead>
                                                    <tr>
                                                        <th>Fecha</th>
                                                        <th>Descripción</th>
                                                        <th>Tipo</th>
                                                        <th>Monto</th>           
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="movimiento" items="${movimientos}">
                                                    <tr>
                                                        <td><fmt:formatDate value="${movimiento.fechaHora}" pattern="dd/MM/yyyy"/></td>

                                                        <td>${movimiento.descripcion}</td>

                                                        <td>
                                                            <span class="etiqueta-tipo ${movimiento.tipo == 'abono' ? 'abono' : 'cargo'}">
                                                                ${movimiento.tipo}
                                                            </span>
                                                        </td>

                                                        <td style="color: ${movimiento.tipo == 'Abono' ? '#10B981' : '#EF4444'}; font-weight: bold;">
                                                            ${movimiento.tipo == 'abono' ? '+' : '-'}
                                                            <fmt:formatNumber value="${movimiento.monto}" type="currency" currencySymbol="$"/>
                                                        </td>

                                                        <td>
                                                            <fmt:formatNumber value="${movimiento.saldoFinal}" type="currency" currencySymbol="$"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                <c:if test="${empty movimientos}">
                                                    <tr>
                                                        <td colspan="5" style="text-align: center;">No hay movimientos para el período seleccionado.</td>
                                                    </tr>
                                                </c:if>
                                                </tbody>
                                            </table>

                                            <div style="margin-top: 15px; text-align: center;">
                                                <button 
                                                    onclick="window.location.href = 'movimientos_clientes.html'" 
                                                    style="padding: 8px 20px; background-color: #1E3A8A; color: white; border: none; border-radius: 3px; cursor: pointer;">Ver Más Movimientos</button>
                                            </div>
                                        </td>
                                    </tr>
                                </table>

                            </td>

                            <!-- Right Column -->
                            <td width="30%" valign="top">

                                <!-- Monthly Summary -->
                                <table width="100%" cellpadding="15" cellspacing="0" border="0" bgcolor="#FFFFFF" style="border-radius: 5px; margin-bottom: 15px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                                    <tr>
                                        <td>
                                            <h3 style="color: #1E3A8A; margin: 0 0 15px 0;">Resumen del Mes</h3>

                                            <table width="100%" cellpadding="8" cellspacing="0" border="0">
                                                <tr>
                                                    <td style="font-size: 14px; color: #4B5563;">Total Ingresos:</td>
                                                    <td align="right" style="font-size: 14px; color: #10B981; font-weight: bold;">+$52,500</td>
                                                </tr>
                                                <tr>
                                                    <td style="font-size: 14px; color: #4B5563;">Total Egresos:</td>
                                                    <td align="right" style="font-size: 14px; color: #EF4444; font-weight: bold;">-$11,750</td>
                                                </tr>
                                                <tr style="border-top: 2px solid #E5E7EB;">
                                                    <td style="font-size: 16px; color: #1E3A8A; font-weight: bold; padding-top: 8px;">Balance:</td>
                                                    <td align="right" style="font-size: 16px; color: #1E3A8A; font-weight: bold; padding-top: 8px;">+$40,750</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                </td>
            </tr>
        </table>

        <script>

        </script>

    </body>
</html>
