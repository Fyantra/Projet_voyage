<%-- 
    Document   : insertVoyage
    Created on : 19 déc. 2023, 18:01:14
    Author     : ITU
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="objet.*" %>
<%
    Bouquet bouquet = new Bouquet();
    Activite activite = new Activite();
    CategorieLieu lieu = new CategorieLieu();
    Sejour sej = new Sejour();
    List<Sejour> lsejour= sej.selectSejour(null);
%>

<%@ include file = "header.html" %>

    <div class="col-lg-10">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Voyage</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Insertion de detail du voyage</h3>
                        </div>
                        <hr>
                        <form action="InsertVoyage" method="post">
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

                            <div class="form-group">
                                <label for="cc-payment" class="control-label mb-1"><b>Description de categorie de lieu</b></label>
                                <div class="col-10 col-md-15">
                                    <select name="categorielieu" id="select" class="form-control" required>
                                        <option value="" disabled selected>Choisissez un categorie de lieu</option>
                                        
                                        <% try {  
                                                List<CategorieLieu> lcateg = lieu.selectCategorieLieu(null);
                                                //session.setAttribute("bouquet" , resultat);
                                                for(int i=0 ; i< lcateg.size() ; i++ ){
                                            %>
                                                <option value="<%= lcateg.get(i).getIdCategorielieu() %>"> <%= lcateg.get(i).getDescription() %></option>
                                            
                                            <% } } catch(Exception e){
                                                out.print(e);
                                            }  %>
                                    </select>
                                </div>
                            </div>

                            <div class="row form-group" style="margin-top: 30px;"">
                                <div class="col col-md-3"><label class=" form-control-label"><b>Quel type de sejour</b></label></div>
                                <div class="col col-md-9">
                                    <div class="form-check">
                                        <% for (Sejour sejour : lsejour) { %>
                                            <div class="checkbox" style="margin-bottom: 10px;">
                                                <label for="checkbox<%= sejour.getIdSejour() %>" class="form-check-label">
                                                    <input type="radio" id="checkbox<%= sejour.getIdSejour() %>"
                                                        name="sejour" value="<%= sejour.getIdSejour() %>"
                                                        class="form-check-input">
                                                    <%= sejour.getDescription() %>
                                                </label>
                                            </div>
                                        <% } %>
                                        
                                    </div>
                                </div>
                            </div>
                            
                            <div>
                                <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                    <span id="payment-button-amount">Valider</span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div> <!-- .card -->
    </div>

<%@ include file = "footer.html" %>
