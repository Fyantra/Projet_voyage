<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="objet.*" %>
<%
    Bouquet bouquet = new Bouquet();
    Activite activite = new Activite();
    List<Activite> lactivite= activite.selectActivite(null);
%>

<%@ include file = "header.html" %>

    <div class="col-lg-10">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Activite-Bouquet</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Insertion d`activite pour un bouquet</h3>
                        </div>
                        <hr>
                        <form action="InsertBouquetActivite" method="post">
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

                            <div class="row form-group" style="margin-top: 30px;"">
                                <div class="col col-md-2"><label class=" form-control-label"><b>Les activites</b></label></div>
                                <div class="col col-md-9">
                                    <div class="form-check">
                                        <% for (Activite act : lactivite) { %>
                                            <div class="checkbox" style="margin-bottom: 10px;">
                                                <label for="checkbox<%= act.getIdActivite() %>" class="form-check-label">
                                                    <input type="checkbox" id="checkbox<%= act.getIdActivite() %>"
                                                        name="activites" value="<%= act.getIdActivite() %>"
                                                        class="form-check-input">
                                                    <%= act.getDescription() %>
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
