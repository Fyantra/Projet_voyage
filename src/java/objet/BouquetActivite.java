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
public class BouquetActivite {
    String idBouquetActivite;
    String idBouquet;
    String idActivite;
    String idCategorieLieu;

    public BouquetActivite() {
    }

    public BouquetActivite(String idBouquet, String idActivite, String idCategorieLieu) {
        this.idBouquet = idBouquet;
        this.idActivite = idActivite;
        this.idCategorieLieu = idCategorieLieu;
    }
    
    public BouquetActivite(String idBouquetActivite, String idBouquet, String idActivite, String idCategorieLieu) {
        this.idBouquetActivite = idBouquetActivite;
        this.idBouquet = idBouquet;
        this.idActivite = idActivite;
         this.idCategorieLieu = idCategorieLieu;
    }

    public String getIdBouquetActivite() {
        return idBouquetActivite;
    }

    public String getIdBouquet() {
        return idBouquet;
    }

    public String getIdActivite() {
        return idActivite;
    }

    public String getIdCategorieLieu() {
        return idCategorieLieu;
    }

    public void setIdBouquetActivite(String idBouquetActivite) throws Exception{
         if ("".equals(idBouquetActivite)) {
            throw new Exception("BouquetActivite vide");
        }
        this.idBouquetActivite = idBouquetActivite;
    }

    public void setIdBouquet(String idBouquet) throws Exception{
         if ("".equals(idBouquet)) {
            throw new Exception("Bouquet vide");
        }
        this.idBouquet = idBouquet;
    }

    public void setIdActivite(String idActivite) throws Exception{
         if ("".equals(idActivite)) {
            throw new Exception("Activite vide");
        }
        this.idActivite = idActivite;
    }

    public void setIdCategorieLieu(String idCategorieLieu) {
        this.idCategorieLieu = idCategorieLieu;
    }
    
    public void insertBouquetActivite(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            String sql = "INSERT INTO bouquet_activite(idbouquet,idactivite,idcategorielieu) VALUES ('"+ this.getIdBouquet()+"','"+this.getIdActivite()+"','"+this.getIdCategorieLieu()+"');";
            //(nextval('Employerseq')

            statement = connection.createStatement();

            System.out.println(sql);
            statement.executeUpdate(sql);
            connection.commit();
        } catch (Exception e) { 
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }
    
    public void insertBouquetActivite(String idBouquet, String idcategorielieu, List<String> idActivitesList) throws Exception {
        try {
            
            for (String idActivite : idActivitesList) {
                BouquetActivite insertion = new BouquetActivite();
                insertion.setIdBouquet(idBouquet);
                insertion.setIdActivite(idActivite);
                insertion.setIdCategorieLieu(idcategorielieu);
                
                insertion.insertBouquetActivite(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<BouquetActivite> selectBouquetActivite(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<BouquetActivite> BouquetActivites = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM bouquet_activite" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de bouquet_activite");
                    return null;
                }

                while (resultSet.next()) {
                String idbouquetActivite = resultSet.getString("idbouquetActivite");
                String idbouquet = resultSet.getString("idbouquet");
                String idactivite = resultSet.getString("idactivite");
                String idcategorielieu = resultSet.getString("idcategorielieu");

                BouquetActivites.add(new BouquetActivite(idbouquetActivite,idbouquet,idactivite, idcategorielieu ));
            }
                return BouquetActivites;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de BouquetActivites a echoue");

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
    
     public static BouquetActivite getLastBouquetActivite() throws Exception {   //maka ny id farany
         BouquetActivite ba=new BouquetActivite();
        
        List<BouquetActivite> listeba=ba.selectBouquetActivite(null);
        int taille=listeba.size()-1;
        BouquetActivite lb= listeba.get(taille);
        
        return lb;
    }
    
    public static void main(String[] args){
         BouquetActivite bou = new BouquetActivite();
         try{
             
//            List<Bouquet> lbouquet = bou.selectBouquet(null);
//            for(int i=0 ; i< lbouquet.size() ; i++ ){
               System.out.println(bou.getLastBouquetActivite().getIdBouquetActivite());
//            }
            //bou.insertBouquetActivite(null);
         }catch(Exception e){
         }
     }
}
