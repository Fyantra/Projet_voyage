
<%@ include file = "header.html" %>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Activite</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Insertion d`activite</h3>
                        </div>
                        <hr>
                        <form action="InsererActivite" method="post">
                            <div class="form-group">
                                <label for="cc-payment" class="control-label mb-1">Description de l`activite</label>
                                <input id="cc-payment" name="description" type="text" class="form-control" required>
                            </div>

                              <div class="form-group">
                                <label for="cc-payment" class="control-label mb-1">Prix unitaire</label>
                                <input id="cc-payment" name="pu" type="number" class="form-control" required>
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