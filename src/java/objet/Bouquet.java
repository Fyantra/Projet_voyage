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
public class Bouquet {
    String idBouquet;
    String description;

    public Bouquet() {
    }

    public Bouquet(String description) throws Exception{
        if ("".equals(description)) {
            throw new Exception("description vide");
        }
        this.description = description;
    }
    
    

    public Bouquet(String idBouquet, String description) {
        this.idBouquet = idBouquet;
        this.description = description;
    }

    public String getIdBouquet() {
        return idBouquet;
    }

    public String getDescription() {
        return description;
    }

    public void setIdBouquet(String idBouquet) throws Exception{
        if ("".equals(idBouquet)) {
            throw new Exception("bouquet vide");
        }
        this.idBouquet = idBouquet;
    }

    public void setDescription(String description) throws Exception{
        if ("".equals(description)) {
            throw new Exception("description vide");
        }
        this.description = description;
    }
    
    public List<Bouquet> selectBouquet(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<Bouquet> Bouquets = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM bouquet;" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de bouquet");
                    return null;
                }

                while (resultSet.next()) {
                String idBouquet = resultSet.getString("idbouquet");
                String description = resultSet.getString("Description");
                

                Bouquets.add(new Bouquet(idBouquet,description));
            }
                return Bouquets;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de Bouquet a echoue");

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
    
    public void insertBouquet(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            //String emp = "EMP";
            String sql = "INSERT INTO bouquet(description) VALUES ('"+ this.getDescription()+"');";
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
    
    public static void main(String[] args){
         Bouquet bou = new Bouquet();
         try{
            List<Bouquet> lbouquet = bou.selectBouquet(null);
            for(int i=0 ; i< lbouquet.size() ; i++ ){
                System.out.println(lbouquet.get(i).getIdBouquet());
            }
            //bou.insertBouquet(null);
         }catch(Exception e){
         }
     }

}
