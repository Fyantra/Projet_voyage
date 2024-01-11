<%-- 
    Document   : filtreActivite
    Created on : 9 janv. 2024, 14:37:22
    Author     : ITU
--%>


<%@ include file = "header.html" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="objet.*" %>
<%@page import="javax.servlet.http.HttpSession" %>

<%
    List<VoyageDetail> vd = new ArrayList();

    Boolean formSubmitted = (Boolean) request.getAttribute("formSubmitted");

    if (formSubmitted != null && formSubmitted) {
        vd = (List<VoyageDetail>) request.getAttribute("listeVoyage");
    }
    int counter = 1;
%>

    <div class="col-lg-10">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Liste voyage</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Entrer les deux montants</h3>
                        </div>
                        <hr>
                        <form action="DetailVoyage" method="post">
                            <div class="row" style="margin-left: 14%;">
                                <div class="form-group" style="margin-right: 20px;">
                                    <label for="cc-payment" class="control-label mb-1">Montant 1</label>
                                    <input id="cc-payment" name="montant1" type="number" class="form-control" style="width: 311px;" required>
                                </div>

                                <div class="form-group">
                                    <label for="cc-payment" class="control-label mb-1">Montant 2</label>
                                    <input id="cc-payment" name="montant2" type="number" class="form-control" style="width: 311px;" required>
                                </div>
                            </div>
                            <div>
                                <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                    <span id="payment-button-amount">Valider</span>
                                </button>
                            </div>
                        </form>
                        
                        <%
                            // V�rifie si le formulaire a �t� soumis et que les r�sultats sont disponibles
                            if (formSubmitted != null && formSubmitted) {
                        %>
                            <div class="col-lg-10" style="margin-top: 5%;">
                                <div class="card">
                                    <div class="card-header">
                                        <strong class="card-title">Liste des voyages</strong>
                                    </div>
                                    <div class="table-stats order-table ov-h">
                                        <table class="table ">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Bouquet</th>
                                                    <th>Categorie lieu</th>
                                                    <th>Sejour</th>
                                                    <th>Somme activite</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                    // Boucle sur les r�sultats pour afficher les donn�es dans le tableau
                                                    for (VoyageDetail v : vd) {
                                                %>
                                                        <tr>
                                                            <td><%= counter++ %></td>
                                                            <td><span class="name"><%= v.getBouquet() %></span></td>
                                                             <td><span class="name"><%= v.getCategorielieu() %></span></td>
                                                             <td><span class="name"><%= v.getSejour() %></span></td>
                                                             <td><span class="name"><%= v.getSommeActivite()%></span></td>
                                                        </tr>
                                                <% 
                                                    }
                                                %>
                                                
                                            </tbody>
                                        </table>
                                    </div> <!-- /.table-stats -->
                                </div>
                            </div>
                        <%
                            }
                        %>
                    </div>
                </div>

            </div>
        </div> <!-- .card -->
    </div>

<%@ include file = "footer.html" %>