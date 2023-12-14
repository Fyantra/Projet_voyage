/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import connexion.Connect;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ITU
 */
public class VueBouquetActivite {
    String idBouquetactivite;
    String idBouquet;
    String description_bouquet;
    String idActivite;
    String description_activite;

    public VueBouquetActivite() {
    }

    public VueBouquetActivite(String idBouquetactivite, String idBouquet, String description_bouquet, String idActivite, String description_activite) {
        this.idBouquetactivite = idBouquetactivite;
        this.idBouquet = idBouquet;
        this.description_bouquet = description_bouquet;
        this.idActivite = idActivite;
        this.description_activite = description_activite;
    }

    public String getIdBouquetactivite() {
        return idBouquetactivite;
    }

    public String getIdBouquet() {
        return idBouquet;
    }

    public String getDescription_bouquet() {
        return description_bouquet;
    }

    public String getIdActivite() {
        return idActivite;
    }

    public String getDescription_activite() {
        return description_activite;
    }

    public void setIdBouquetactivite(String idBouquetactivite) {
        this.idBouquetactivite = idBouquetactivite;
    }

    public void setIdBouquet(String idBouquet) {
        this.idBouquet = idBouquet;
    }

    public void setDescription_bouquet(String description_bouquet) {
        this.description_bouquet = description_bouquet;
    }

    public void setIdActivite(String idActivite) {
        this.idActivite = idActivite;
    }

    public void setDescription_activite(String description_activite) {
        this.description_activite = description_activite;
    }
    
    public List<VueBouquetActivite> selectVueBouquetActiviteById(Connection connection, String idBouquet) throws Exception {
        boolean isOpened = false;
        
        List<VueBouquetActivite> VueBouquetActivites = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM vue_bouquet_activite where idbouquet = '"+idBouquet+"';" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de bouquet");
                    return null;
                }

                while (resultSet.next()) {
                    String idBouquetAct = resultSet.getString("idbouquetactivite");
                    String idBou = resultSet.getString("idbouquet");
                    String description_bouq = resultSet.getString("description_bouquet");
                    String idAct = resultSet.getString("idactivite");
                    String description_act = resultSet.getString("description_activite");

                VueBouquetActivites.add(new VueBouquetActivite(idBouquetAct,idBou,description_bouq,idAct,description_act));
            }
                return VueBouquetActivites;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de BouquetActivite a echoue");

                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (isOpened == true) {
                connection.close();
            }
        }
        return null;
    }
    
     public static void main(String[] args){
         VueBouquetActivite bou = new VueBouquetActivite();
         try{
            List<VueBouquetActivite> lbouquet = bou.selectVueBouquetActiviteById(null,"bouquet31");
            for(int i=0 ; i< lbouquet.size() ; i++ ){
                System.out.println(lbouquet.get(i).getDescription_bouquet());
            }
            //bou.insertBouquet(null);
         }catch(Exception e){
         }
     }
}
