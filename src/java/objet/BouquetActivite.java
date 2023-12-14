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

    public BouquetActivite() {
    }

    public BouquetActivite(String idBouquet, String idActivite) {
        this.idBouquet = idBouquet;
        this.idActivite = idActivite;
    }
    
    public BouquetActivite(String idBouquetActivite, String idBouquet, String idActivite) {
        this.idBouquetActivite = idBouquetActivite;
        this.idBouquet = idBouquet;
        this.idActivite = idActivite;
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
    
    public void insertBouquetActivite(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            String sql = "INSERT INTO bouquet_activite(idbouquet,idactivite) VALUES ('"+ this.getIdBouquet()+"','"+this.getIdActivite()+"');";
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
    
    public void insertBouquetActivite(String idBouquet, List<String> idActivitesList) throws Exception {
        try {
            
            for (String idActivite : idActivitesList) {
                BouquetActivite insertion = new BouquetActivite();
                insertion.setIdBouquet(idBouquet);
                insertion.setIdActivite(idActivite);

                insertion.insertBouquetActivite(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
         BouquetActivite bou = new BouquetActivite("bouquet1","activite1");
         try{
//            List<Bouquet> lbouquet = bou.selectBouquet(null);
//            for(int i=0 ; i< lbouquet.size() ; i++ ){
//                System.out.println(lbouquet.get(i).getIdBouquet());
//            }
            bou.insertBouquetActivite(null);
         }catch(Exception e){
         }
     }
}
