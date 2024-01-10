/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import connexion.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ITU
 */
public class VoyageDetail {
    String idVoyage;
    String bouquet;
    String categorielieu;
    String sejour;
    double sommeActivite;

    public VoyageDetail() {
    }

    public VoyageDetail(String idVoyage, String bouquet, String categorielieu, String sejour, double sommeActivite) {
        this.idVoyage = idVoyage;
        this.bouquet = bouquet;
        this.categorielieu = categorielieu;
        this.sejour = sejour;
        this.sommeActivite = sommeActivite;
    }

    public String getIdVoyage() {
        return idVoyage;
    }

    public String getBouquet() {
        return bouquet;
    }

    public String getCategorielieu() {
        return categorielieu;
    }

    public String getSejour() {
        return sejour;
    }

    public double getSommeActivite() {
        return sommeActivite;
    }

    public void setIdVoyage(String idVoyage) {
        this.idVoyage = idVoyage;
    }

    public void setBouquet(String bouquet) {
        this.bouquet = bouquet;
    }

    public void setCategorielieu(String categorielieu) {
        this.categorielieu = categorielieu;
    }

    public void setSejour(String sejour) {
        this.sejour = sejour;
    }

    public void setSommeActivite(double sommeActivite) {
        this.sommeActivite = sommeActivite;
    }

   
    
    public List<VoyageDetail> selectVoyageDetail(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<VoyageDetail> VoyageDetails = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM vue_voyage_detail" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de vue_voyage_detail");
                    return null;
                }

                while (resultSet.next()) {
                String idVoyage = resultSet.getString("idvoyage");
                String bouquet = resultSet.getString("bouquet_description");
                String lieu = resultSet.getString("categorielieu_description");
                String activite = resultSet.getString("activite_description");
                double prix = resultSet.getDouble("prixunitaire");

                VoyageDetails.add(new VoyageDetail(idVoyage, bouquet, lieu, activite, prix));
            }
                return VoyageDetails;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de VoyageDetail a echoue");

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
    
    public List<VoyageDetail> selectVoyageBetween2PU(Connection connection, double pu1, double pu2) throws Exception {
        boolean isOpened = false;
        
        List<VoyageDetail> VoyageDetails = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

               String requete = "SELECT\n" +
                    "    idvoyage,bouquet,categorielieu,sejour,\n" +
                    "    SUM(prixUnitaire * nombreactivite) AS somme_activites\n" +
                    "FROM\n" +
                    "    vue_voyage_detail\n" +
                    "GROUP BY\n" +
                    "    idvoyage,bouquet,categorielieu,sejour\n" +  
                    "HAVING\n" +
                    "    SUM(prixUnitaire * nombreactivite) BETWEEN " + pu1 + " AND " + pu2;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de vue_voyage_detail");
                    return null;
                }

                while (resultSet.next()) {
                String idVoyage = resultSet.getString("idvoyage");
                String bouquet = resultSet.getString("bouquet");
                String lieu = resultSet.getString("categorielieu");
                String sejour = resultSet.getString("sejour");
               double somme = resultSet.getDouble("somme_activites");

                VoyageDetails.add(new VoyageDetail(idVoyage, bouquet, lieu,sejour, somme));
            }
                return VoyageDetails;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de VoyageDetail a echoue");

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
         VoyageDetail bou = new VoyageDetail();
         try{
            List<VoyageDetail> lbouquet = bou.selectVoyageBetween2PU(null, 200, 300000);
            for(int i=0 ; i< lbouquet.size() ; i++ ){
                System.out.println(lbouquet.get(i).getSommeActivite());
            }
            //bou.insertBouquet(null);
         }catch(Exception e){
         }
     }

}
