/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Jules
 */
public class PHPControleur {
    
    private String nom;
    private ArrayList<PHPClass> lesClasses;

    public PHPControleur(String nom) {
        this.nom = nom;
        this.lesClasses = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<PHPClass> getLesClasses() {
        return lesClasses;
    }

    public void setLesClasses(ArrayList<PHPClass> lesClasses) {
        this.lesClasses = lesClasses;
    }
    
    public void addClasse(PHPClass classe) {
        this.lesClasses.add(classe);
    }
    
    public PHPClass getClasse(String nomClasse) {
        PHPClass c = null;
        for (PHPClass classe : lesClasses) {
            if (classe.getNom().equals(nomClasse)) {
                c = classe;
            }
        }
        return c;
    }
    
    public void updateClasse(String oldNomClasse, String nomClasse) {
        getClasse(oldNomClasse).setNom(nomClasse);
    }
    
    public void removeClasse(String nomClasse) {
        this.lesClasses.remove(getClasse(nomClasse));
    }

    public String getControleurPHP() {
        String controleurPHP = "<?php\n"
                + "\n"
                + getRequireOnce()
                + "\n"
                + "\tclass Controleur {\n"
                + "\n"
                + "\t\tprivate $dsn = 'mysql:dbname= ;host= ';\n"
                + "\t\tprivate $user = '';\n"
                + "\t\tprivate $mdp = '';\n"
                + "\t\tprivate $dbh;\n"
                + "\n"
                + "\t\tfunction __construct() {\n"
                + "\t\t\ttry {\n"
                + "\t\t\t\t$dbh = new PDO($dsn, $user, $mdp);\n"
                + "\t\t\t\t$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);\n"
                + "\t\t\t} catch(PDOException $e) {\n"
                + "\t\t\t\techo 'Echec lors de la connexion : ' . $e->getMessage();\n"
                + "\t\t\t}\n"
                + "\t\t}\n\n\n\n";
        for (PHPClass classe : this.lesClasses) {
            controleurPHP += classe.getStringSQL();
        }    
        controleurPHP += "\n"
                + "\t}\n"
                + "?>";
        return controleurPHP;
    }

    private String getRequireOnce() {
        String requireOnce = "";
        for (PHPClass classe : getLesClasses()) {
            requireOnce += "\trequire_once('" + classe.getNom() + ".php');\n";
        }
        return requireOnce;
    }

    public void updateAttribut(String nomClasse, String oldNomAttribut, String nomAttribut) {
        getClasse(nomClasse).getLesAttributs().remove(oldNomAttribut);
        getClasse(nomClasse).getLesAttributs().add(nomAttribut);
    }

    public void removeAttribut(String nomClasse, String nomAttribut) {
        getClasse(nomClasse).getLesAttributs().remove(nomAttribut);
    }
}
