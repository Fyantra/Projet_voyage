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
public class CategorieLieu {
    String idCategorielieu;
    String description;

    public CategorieLieu() {
    }

    public CategorieLieu(String description) {
        this.description = description;
    }

    public CategorieLieu(String idCategorielieu, String description) {
        this.idCategorielieu = idCategorielieu;
        this.description = description;
    }

    public String getIdCategorielieu() {
        return idCategorielieu;
    }

    public String getDescription() {
        return description;
    }

    public void setIdCategorielieu(String idCategorielieu) throws Exception{
        if ("".equals(idCategorielieu)) {
            throw new Exception("idcategorie vide");
        }
        this.idCategorielieu = idCategorielieu;
    }

    public void setDescription(String description) throws Exception{
        if ("".equals(description)) {
            throw new Exception("description categorie vide");
        }
        this.description = description;
    }
    
    public List<CategorieLieu> selectCategorieLieu(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<CategorieLieu> CategorieLieus = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM categorielieu;" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de bouquet");
                    return null;
                }

                while (resultSet.next()) {
                String idcategorielieu = resultSet.getString("idcategorielieu");
                String description = resultSet.getString("description");
                

                CategorieLieus.add(new CategorieLieu(idcategorielieu,description));
            }
                return CategorieLieus;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de categorieLieu a echoue");

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
    
    public void insertCategorieLieu(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            //String emp = "EMP";
            String sql = "INSERT INTO categorielieu(description) VALUES ('"+ this.getDescription()+"');";
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
}
