/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personne;

import connexion.Connect;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import objet.VueBouquetActivite;
/**
 *
 * @author ITU
 */
public class Utilisateur {
    String idUtilisateur;
    String email;
    String mdp;

    public Utilisateur() {
    }

    public Utilisateur(String idUtilisateur, String email, String mdp) {
        this.idUtilisateur = idUtilisateur;
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public List<Utilisateur> login(Connection connection, String email, String mdp) throws Exception {
        boolean isOpened = false;
        
        List<Utilisateur> user = new ArrayList<>();

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            try {

                String requete = "SELECT * FROM utilisateur where email = '"+email+"' and mdp = '"+mdp+"';" ;

                //System.out.println(requete);
                ResultSet resultSet = stat.executeQuery(requete);

                if (resultSet == null) {
                    System.out.println("Il n`y a pas de utilisateur Utilisateur.java 87");
                    return null;
                }

                while (resultSet.next()) {
                    String mail = resultSet.getString("email");
                    String mdpp = resultSet.getString("mdp");

                     user.add(new Utilisateur(mail, mdpp));
            }
                return user;

            } catch (SQLException e) {

                connection.rollback();

                System.out.println("La recuperation de utilisateur a echoue : Utilisateur.java 103");

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
    
    public Boolean testLogin(String email,String mdp) throws Exception
    {
        Utilisateur user = new Utilisateur();
        List<Utilisateur> luser = user.login(null, email, mdp);
        if(luser.size()==0)
            return false;
        else
            return true;
    }
    
    public static void main(String[] args){
         Utilisateur user = new Utilisateur();
         try{
             System.out.println(user.testLogin("user1@gmail.com", "0000"));
         }catch(Exception e){
         }
     }
}
