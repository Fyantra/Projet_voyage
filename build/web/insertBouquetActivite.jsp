<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="objet.*" %>
<%
    Bouquet bouquet = new Bouquet();
    Activite activite = new Activite();
    CategorieLieu lieu = new CategorieLieu();
    Sejour sej = new Sejour();
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

                            <div class="form-group">
                                <label for="cc-payment" class="control-label mb-1"><b>Les activites</b></label>
                                <div class="col-10 col-md-15">
                                    <select name="activite" id="select" class="form-control" required>
                                        <option value="" disabled selected>Choisissez un activite</option>
                                        
                                        <% try {  
                                                List<Activite> lactivite= activite.selectActivite(null);
                                                //session.setAttribute("bouquet" , resultat);
                                                for(int i=0 ; i< lactivite.size() ; i++ ){
                                            %>
                                                <option value="<%= lactivite.get(i).getIdActivite() %>"> <%= lactivite.get(i).getDescription() %></option>
                                            
                                            <% } } catch(Exception e){
                                                out.print(e);
                                            }  %>
                                    </select>
                                </div>
                            </div>

                            <div id="formContainer">
                                <div class="row">
                                    <div class="col-6">
                                        <div class="form-group">
                                            <label for="cc-exp" class="control-label mb-1"><b>Sejour</b></label>
                                        <select name="sejour" id="select" class="form-control" required>
                                            <option value="" disabled selected>Choisissez un sejour</option>
                                            
                                            <% try {  
                                                    List<Sejour> lsej = sej.selectSejour(null);
                                                    //session.setAttribute("bouquet" , resultat);
                                                    for(int i=0 ; i< lsej.size() ; i++ ){
                                                %>
                                                    <option value="<%= lsej.get(i).getIdSejour() %>"> <%= lsej.get(i).getDescription() %></option>
                                                
                                                <% } } catch(Exception e){
                                                    out.print(e);
                                                }  %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <label for="x_card_code" class="control-label mb-1">Nombre d`activite</label>
                                        <div class="input-group">
                                            <input id="x_card_code" name="nbactivite" type="number" class="form-control cc-cvc" value="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                           
                            <button id="addFormButton" class="form-input-wide" style="margin-bottom: 37px;" onclick="addForm()">Ajouter un sejour</button>
                           
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

     <script>
        function addForm() {
            // Cloner le contenu du formulaire
            var formContainer = document.getElementById('formContainer');
            var clonedForm = formContainer.firstElementChild.cloneNode(true);

            // Effacer les valeurs des champs clonés (au cas où)
            var formInputs = clonedForm.querySelectorAll('input, select');
            formInputs.forEach(function(input) {
                input.value = '';
            });

            // Ajouter le formulaire cloné au conteneur
            formContainer.appendChild(clonedForm);
        }
    </script>
    

<%@ include file = "footer.html" %>
