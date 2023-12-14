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
public class Activite {
    String idActivite;
    String description;

    public Activite() {
    }

    public Activite(String description) throws Exception{
        if ("".equals(description)) {
            throw new Exception("description vide");
        }
        this.description = description;
    }

    
    public Activite(String idActivite, String description) {
        this.idActivite = idActivite;
        this.description = description;
    }

    public String getIdActivite() {
        return idActivite;
    }

    public String getDescription() {
        return description;
    }

    public void setIdActivite(String idActivite) throws Exception{
        if ("".equals(idActivite)) {
            throw new Exception("activite vide");
        }
        this.idActivite = idActivite;
    }

    public void setDescription(String description) throws Exception{
        if ("".equals(description)) {
            throw new Exception("description vide");
        }
        this.description = description;
    }
    
    public List<Activite> selectActivite(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<Activite> Activites = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM activite" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de activite");
                    return null;
                }

                while (resultSet.next()) {
                String idActivite = resultSet.getString("idactivite");
                String description = resultSet.getString("description");
                

                Activites.add(new Activite(idActivite,description));
            }
                return Activites;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de Activite a echoue");

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
    
    public void insertActivite(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            //String emp = "EMP";
            String sql = "INSERT INTO activite(description) VALUES ('"+ this.getDescription()+"');";
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
         Activite bou = new Activite();
         try{
            List<Activite> lbouquet = bou.selectActivite(null);
            for(int i=0 ; i< lbouquet.size() ; i++ ){
                System.out.println(lbouquet.get(i).getDescription());
            }
            //bou.insertActivite(null);
         }catch(Exception e){
         }
     }
}
