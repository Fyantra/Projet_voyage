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
public class Voyage {
    String idVoyage;
    String idCategorieLieu;
    String idBouquet;
    String idSejour;

    public Voyage() {
    }

    public Voyage(String idVoyage, String idCategorieLieu, String idBouquet, String idSejour) {
        this.idVoyage = idVoyage;
        this.idCategorieLieu = idCategorieLieu;
        this.idBouquet = idBouquet;
        this.idSejour = idSejour;
    }

    public Voyage(String idCategorieLieu, String idBouquet, String idSejour) {
        this.idVoyage = idVoyage;
        this.idCategorieLieu = idCategorieLieu;
        this.idBouquet = idBouquet;
        this.idSejour = idSejour;
    }

    public String getIdVoyage() {
        return idVoyage;
    }

    public String getIdCategorieLieu() {
        return idCategorieLieu;
    }

    public String getIdBouquet() {
        return idBouquet;
    }

    public String getIdSejour() {
        return idSejour;
    }

    public void setIdVoyage(String idVoyage) {
        this.idVoyage = idVoyage;
    }

    public void setIdCategorieLieu(String idCategorieLieu) {
        this.idCategorieLieu = idCategorieLieu;
    }

    public void setIdBouquet(String idBouquet) {
        this.idBouquet = idBouquet;
    }

    public void setIdSejour(String idSejour) {
        this.idSejour = idSejour;
    }
    
    public List<Voyage> selectVoyage(Connection connection) throws Exception {
        boolean isOpened = false;
        
        List<Voyage> Voyages = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM voyage;" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de voyage");
                    return null;
                }

                while (resultSet.next()) {
                String idVoy = resultSet.getString("idvoyage");
                String idcatego = resultSet.getString("idcategorielieu");
                String idbou = resultSet.getString("idbouquet");
                String idse = resultSet.getString("idsejour");
                

                Voyages.add(new Voyage(idVoy,idcatego, idbou, idse));
            }
                return Voyages;

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
    
    public void insertVoyage(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            //String emp = "EMP";
            String sql = "INSERT INTO voyage(idcategorielieu,idbouquet, idsejour) VALUES ('"+ this.getIdCategorieLieu()+"','"+ this.getIdBouquet()+"','"+ this.getIdSejour()+"');";
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
