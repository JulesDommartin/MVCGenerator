/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import modele.Link;
import modele.Menu;
import modele.PHPClass;
import modele.PHPControleur;
import modele.Page;
import vue.FenetrePrincipal;

/**
 *
 * @author Jules
 */
public class Controleur {
    
    private String nomProjet;
    private String nomSite;
    private Menu menu;
    private PHPControleur phpControleur;
    private Scanner scanner;
    private FenetrePrincipal fenetrePrincipal;
    
    public Controleur() throws IOException {
        this.nomProjet = "";
        this.nomSite = "";
        this.menu = new Menu();
        this.phpControleur = new PHPControleur(nomProjet);
        scanner = new Scanner(System.in);
        fenetrePrincipal = new FenetrePrincipal(this);
        fenetrePrincipal.setLocationRelativeTo(null);
        fenetrePrincipal.setVisible(true);
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }
    
    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public PHPControleur getPhpControleur() {
        return phpControleur;
    }

    public void setPhpControleur(PHPControleur phpControleur) {
        this.phpControleur = phpControleur;
    }
    
//    private void demanderNomProjet() {
//        System.out.println("Choisissez un nom de projet : ");
//        this.nomProjet = scanner.nextLine();
//    }

//    private void afficherMenu() throws IOException {
//        System.out.println("-------------------------");
//        System.out.println("----#     MENU     #-----");
//        System.out.println("-------------------------");
//        System.out.println("                         ");
//        System.out.println("      " + this.nomProjet + "       ");
//        System.out.println("                         ");
//        System.out.println("1 : Afficher classes     ");
//        System.out.println("2 : Afficher menu        ");
//        System.out.println("3 : Ajouter classe       ");
//        System.out.println("4 : Ajouter page menu    ");
//        System.out.println("5 : Récupérer dossier    ");
//        System.out.println("6 : Quitter              ");
//        
//        String input = scanner.next();
//        System.out.println("");
//        switch (input) {
//            case "1":
//                afficherClasses();
//                break;
//            case "2":
//                afficherPagesMenu();
//                break;
//            case "3":
//                ajouterClasse();
//                break;
//            case "4":
//                ajouterPageMenu();
//                break;
//            case "5":
//                recupererDossier();
//                break;
//            case "6":
//                quitter();
//                break;
//            default :
//                afficherMenu();
//                break;
//        }
//    }

//    private void afficherClasses() throws IOException {
//        System.out.println("Liste des classes : ");
//        if (!phpControleur.getLesClasses().isEmpty()) {
//            for (PHPClass classe : phpControleur.getLesClasses()) {
//                System.out.println("\tClasse : " + classe.getNom());
//                for (String attribut : classe.getLesAttributs()) {
//                    System.out.println("\t\t$" + attribut);
//                }
//            }   
//        } else {
//            System.out.println("\tAucune classe pour le moment");
//        }
//        String s = scanner.nextLine();
//        System.out.println("");
//        afficherMenu();
//    }
//
//    private void afficherPagesMenu() throws IOException {
//        System.out.println("Liste des pages : ");
//        if (!menu.getLesPages().isEmpty()) {
//            for (Page p : menu.getLesPages()) {
//                System.out.println("\t" + p.getNom());
//            }   
//        } else {
//            System.out.println("\tAucune page pour l'instant");
//        }
//        String s = scanner.nextLine();
//        System.out.println("");
//        afficherMenu();
//    }
//
//    private void ajouterClasse() throws IOException {
//        System.out.println("Nouvelle classe : ");
//        System.out.println("\tNom de la classe : ");
//        String nomClasse = scanner.next();
//        PHPClass classe = new PHPClass(nomClasse.replaceFirst(".",(nomClasse.toLowerCase().charAt(0)+"").toUpperCase()));
//        System.out.println("\tNombre d'attributs : ");
//        int nbAttributs = scanner.nextInt();
//        scanner = new Scanner(System.in);
//        classe.addAttribut("id");
//        for (int i = 0; i < nbAttributs; i++) {
//            System.out.println("Nom attribut : ");
//            String nomAttribut = scanner.nextLine();
//            classe.addAttribut(nomAttribut.toLowerCase());
//        }
//        phpControleur.addClasse(classe);
//        System.out.println("Classe ajoutée");
//        String s = scanner.nextLine();
//        System.out.println("");
//        afficherMenu();
//    }
//
//    private void ajouterPageMenu() throws IOException {
//        System.out.println("Nouvelle page : ");
//        System.out.println("\tNom de la page : ");
//        String nomPage = scanner.next();
//        menu.addPage(new Page(nomPage.toLowerCase()));
//        System.out.println("Page ajoutée");
//        String s = scanner.nextLine();
//        System.out.println("");
//        afficherMenu();
//    }
//    
    private void recupererDossier(String path) throws IOException {
        String entirePath = path + "\\" + nomProjet;
        System.out.println(entirePath);
        System.out.println("Récupération du dossier..");
        File dir = new File(entirePath);
        dir.mkdir();
        createDirs(entirePath);
        createFiles(entirePath);
        System.out.println("Dossier récupéré");
    }

    public void quitter() {
        System.exit(0);
    }

    private void createDirs(String entirePath) {
        for (Page p : menu.getLesPages()) {
            File f = new File(entirePath + "/" + p.getNom().toLowerCase());
            f.mkdir();
        }
        
        File data = new File(entirePath + "/data");
        File dataJs = new File(entirePath + "/data/js");
        File dataCss = new File(entirePath + "/data/css");
        File dataFonts = new File(entirePath + "/data/fonts");
        File dataImg = new File(entirePath + "/data/img");
        File modele = new File(entirePath + "/modele");
        File scripts = new File(entirePath + "/scripts");
        File vues = new File(entirePath + "/vues");
        
        data.mkdir();
        dataJs.mkdir();
        dataCss.mkdir();
        dataFonts.mkdir();
        dataImg.mkdir();
        modele.mkdir();
        scripts.mkdir();
        vues.mkdir();
    }

    private void createFiles(String entirePath) throws IOException {
        
        // Création de menu.inc et head.inc
        File menuInc = new File(entirePath + "/menu.inc");
        FileWriter fw = new FileWriter(menuInc);
        fw.write(menu.getMenuInc());
        fw.close();
        
        File headInc = new File(entirePath + "/head.inc");
        fw = new FileWriter(headInc);
        fw.write(menu.getHeadInc(nomSite));
        fw.close();
        
        // Création des index.php
        for (Page p : menu.getLesPages()) {
            File f = new File(entirePath + "/" + p.getNom().toLowerCase() + "/index.php");
            fw = new FileWriter(f);
            fw.write(p.getIndexPHP());
            fw.close();
        }
        
        //Création des pages.php dans la dossier vues
        for (Page p : menu.getLesPages()) {
            File f = new File(entirePath + "/vues/" + p.getNom() + ".php");
            fw = new FileWriter(f);
            fw.write(p.getPagePHP(nomSite));
            fw.close();
        }
        
        //Création des classes.php dans le dossier modele et des scripts associés dans le dossier scripts
        for (PHPClass classe : phpControleur.getLesClasses()) {
            File f = new File(entirePath + "/modele/" + classe.getNom() + ".php");
            fw = new FileWriter(f);
            fw.write(classe.getStringClassePHP());
            fw.close();
            // Script ajout
            File add_script = new File(entirePath + "/scripts/add_" + classe.getNom().toLowerCase() + ".php");
            fw = new FileWriter(add_script);
            fw.write(classe.getAddPhpFile());
            fw.close();
            // Script suppression
            File remove_script = new File(entirePath + "/scripts/remove_" + classe.getNom().toLowerCase() + ".php");
            fw = new FileWriter(remove_script);
            fw.write(classe.getRemovePhpFile());
            fw.close();
            // Script get all normal
            File get_all = new File(entirePath + "/scripts/get_" + classe.getNom().toLowerCase() + "s.php");
            fw = new FileWriter(get_all);
            fw.write(classe.getGetAllPhpFile());
            fw.close();
            // Script get all json
            File get_all_json = new File(entirePath + "/scripts/get_" + classe.getNom().toLowerCase() + "s_json.php");
            fw = new FileWriter(get_all_json);
            fw.write(classe.getGetAllJsonPhpFile());
            fw.close();
        }
        
        //Création du controleur dans le dossier modele
        File controleur = new File(entirePath + "/modele/Controleur.php");
        fw = new FileWriter(controleur);
        fw.write(phpControleur.getControleurPHP());
        fw.close();
    }

    public void newProject() {
        fenetrePrincipal.demanderNomProjet();
        this.menu = new Menu();
        this.phpControleur = new PHPControleur(nomSite);
        fenetrePrincipal.initAll();
    }

    public boolean existLien(String nomLien) {
        boolean b = false;
        Link l = menu.getLink(nomLien);
        if (l != null) {
            b = true;
        }
        return b;
    }

    public void afficherEtat() {
        System.out.println("ETAT DU PROJET : \n");
        System.out.println("Les classes : ");
        for (PHPClass classe : phpControleur.getLesClasses()) {
            System.out.println("\tNom : " + classe.getNom());
            System.out.println("\t\tAttributs : ");
            for (String attribut : classe.getLesAttributs()) {
                System.out.println("\t\t\t- " + attribut);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Les pages : ");
        for (Page p : menu.getLesPages()) {
            System.out.println("\t- " + p.getNom());
        }
        System.out.println("");
        System.out.println("Les liens : ");
        for (Link l : menu.getLesLinks()) {
            System.out.println("\t- " + l.getNom());
        }
    }

    public void exporterProjet(File selectedFile) throws IOException {
        recupererDossier(selectedFile.getAbsolutePath());
    }
    
}
