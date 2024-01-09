<%-- 
    Document   : listeBouquetActivite
    Created on : 14 déc. 2023, 01:49:01
    Author     : ITU
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="objet.*" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%
    Bouquet bouquet = new Bouquet();
    Activite activite = new Activite();
    List<Activite> lactivite= activite.selectActivite(null);
    List<VueBouquetActivite> vba = new ArrayList();

    Boolean formSubmitted = (Boolean) request.getAttribute("formSubmitted");

    if (formSubmitted != null && formSubmitted) {
        vba = (List<VueBouquetActivite>) request.getAttribute("VueBouquetActivite");
    }
    int counter = 1;
%>

<%@ include file = "header.html" %>

    <div class="col-lg-10">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Liste bouquet-activite</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Liste des activites pour un bouquet</h3>
                        </div>
                        <hr>
                        <form action="ListeBouquetActivite" method="post">
                            <div class="form-group">
                                <label for="cc-payment" class="control-label mb-1"><b>Description du Bouquet</b></label>
                                <div class="col-10 col-md-15">
                                    <select name="bouquet" id="select" class="form-control" required>
                                        <option value="" disabled selected>Choisissez un bouquet</option>
                                        
                                        <% try {  
                                                List<Bouquet> resultat= bouquet.selectBouquet(null);
                                                //session.setAttribute("bouquet" , resultat);
                                                for(int i=0 ; i< resultat.size() ; i++ ){
                                            %>
                                                <option value="<%= resultat.get(i).getIdBouquet() %>"> <%= resultat.get(i).getDescription() %></option>
                                            
                                            <% } } catch(Exception e){
                                                out.print(e);
                                            }  %>
                                    </select>
                                </div>
                            </div>
                            
                            <div>
                                <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block" style="width: 25%;margin-left: 29%;">
                                    <span id="payment-button-amount">Valider</span>
                                </button>
                            </div>
                        </form>

                        <%
                            // Vérifie si le formulaire a été soumis et que les résultats sont disponibles
                            if (formSubmitted != null && formSubmitted) {
                        %>
                            <div class="col-lg-10" style="margin-top: 5%;">
                                <div class="card">
                                    <div class="card-header">
                                        <strong class="card-title">Liste des activites pour le bouquet <%= vba.get(0).getDescription_bouquet() %></strong>
                                    </div>
                                    <div class="table-stats order-table ov-h">
                                        <table class="table ">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Description</th>
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                    // Boucle sur les résultats pour afficher les données dans le tableau
                                                    for (VueBouquetActivite v : vba) {
                                                %>
                                                        <tr>
                                                            <td><%= counter++ %></td>
                                                            <td><span class="name"><%= v.getDescription_activite() %></span></td>
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

