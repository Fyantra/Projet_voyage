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
public class Sejour {
    String idSejour;
    String description;
    int jourmin;
    int jourmax;

    public Sejour() {
    }

    public Sejour(String idSejour, String description, int jourmin, int jourmax) {
        this.idSejour = idSejour;
        this.description = description;
        this.jourmin = jourmin;
        this.jourmax = jourmax;
    }

    public Sejour(String description) {
        this.description = description;
    }

    public String getIdSejour() {
        return idSejour;
    }

    public String getDescription() {
        return description;
    }

    public int getJourmin() {
        return jourmin;
    }

    public int getJourmax() {
        return jourmax;
    }

    public void setIdSejour(String idSejour) {
        this.idSejour = idSejour;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJourmin(int jourmin) {
        this.jourmin = jourmin;
    }

    public void setJourmax(int jourmax) {
        this.jourmax = jourmax;
    }
    
    public List<Sejour> selectSejour(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<Sejour> Sejours = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM sejour;" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de bouquet");
                    return null;
                }

                while (resultSet.next()) {
                String idsej = resultSet.getString("idsejour");
                String description = resultSet.getString("description");
                int min =  resultSet.getInt("jourmin");
                int max =  resultSet.getInt("jourmax");
                
                Sejours.add(new Sejour(idsej,description, min, max));
            }
                return Sejours;

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
    
    public void insertSejour(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            //String emp = "EMP";
            String sql = "INSERT INTO sejour(description,jourmin,jourmax) VALUES ('"+ this.getDescription()+"', '"+this.getJourmin()+"', '" +this.getJourmax()+"');";
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
