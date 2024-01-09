<%-- 
    Document   : insertCategorieLieu
    Created on : 19 déc. 2023, 14:43:08
    Author     : ITU
--%>


<%@ include file = "header.html" %>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Categorie de lieu</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Insertion de categorie lieu</h3>
                        </div>
                        <hr>
                        <form action="InsererCategorieLieu" method="post">
                            <div class="form-group">
                                <label for="cc-payment" class="control-label mb-1">Libelle de categorie</label>
                                <input id="cc-payment" name="description" type="text" class="form-control" required>
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