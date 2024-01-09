/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import connexion.Connect;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author ITU
 */
public class DetailBouquetActivite {
    String idDetailbouquetactivite;
    String idBouquetActivite;
    String idSejour;
    int nombreActivite;

    public DetailBouquetActivite() {
    }

    public DetailBouquetActivite(String idDetailbouquetactivite, String idBouquetActivite, String idSejour, int nombreActivite) {
        this.idDetailbouquetactivite = idDetailbouquetactivite;
        this.idBouquetActivite = idBouquetActivite;
        this.idSejour = idSejour;
        this.nombreActivite = nombreActivite;
    }

    public DetailBouquetActivite(String idBouquetActivite, String idSejour, int nombreActivite) {
        this.idBouquetActivite = idBouquetActivite;
        this.idSejour = idSejour;
        this.nombreActivite = nombreActivite;
    }
    
    

    public String getIdDetailbouquetactivite() {
        return idDetailbouquetactivite;
    }

    public String getIdBouquetActivite() {
        return idBouquetActivite;
    }

    public String getIdSejour() {
        return idSejour;
    }

    public int getNombreActivite() {
        return nombreActivite;
    }

    public void setIdDetailbouquetactivite(String idDetailbouquetactivite) {
        this.idDetailbouquetactivite = idDetailbouquetactivite;
    }

    public void setIdBouquetActivite(String idBouquetActivite) {
        this.idBouquetActivite = idBouquetActivite;
    }

    public void setIdSejour(String idSejour) {
        this.idSejour = idSejour;
    }

    public void setNombreActivite(int nombreActivite) {
        this.nombreActivite = nombreActivite;
    }
    
    public void insertDetailBouquetActivite(Connection connection) throws Exception {
        //Connection connection = null;
        Statement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            //String emp = "EMP";
            String sql = "INSERT INTO detailbouquet_activite(idbouquetactivite, idsejour, nombreactivite) VALUES ('"+ this.getIdBouquetActivite()+"','"+ this.getIdSejour()+"','"+ this.getNombreActivite()+"');";
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
