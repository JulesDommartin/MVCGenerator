/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Jules
 */
public class Page {
    
    private String nom;
    
    public Page(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIndexPHP() {
        String indexPHP = "<?php\n"
                + "\n"
                + "\trequire_once('../modele/Controleur.php');\n"
                + "\n"
                + "\tinclude('../vues/" + this.nom + ".php');\n"
                + "\n"
                + "?>";
        return indexPHP;
    }

    public String getPagePHP(String nomProjet) {
        String pagePHP = "<!DOCTYPE html>\n"
                + "<html lang='fr'>\n"
                + "\t<head>\n"
                + "\t\t<?php\n"
                + "\n"
                + "\n\t\t\tinclude('../head.inc');"
                + "\n"
                + "?>\n"
                + "\t</head>\n"
                + "\t<body>\n"
                + "\t\t<?php\n"
                + "\n"
                + "\t\t\tinclude('../menu.inc');\n"
                + "\n"
                + "\t\t?>\n"
                + "\t</body>\n"
                + "</html>";
        return pagePHP;
    }
    
}
