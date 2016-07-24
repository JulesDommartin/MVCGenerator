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
public class Menu {
    
    private ArrayList<Page> lesPages;
    private ArrayList<Link> lesLinks;
    
    public Menu() {
        this.lesPages = new ArrayList<>();
        this.lesLinks = new ArrayList<>();
    }

    public ArrayList<Page> getLesPages() {
        return lesPages;
    }

    public void setLesPages(ArrayList<Page> lesPages) {
        this.lesPages = lesPages;
    }
    
    public void addPage(Page s) {
        this.lesPages.add(s);
    }
    
    public Page getPage(String s) {
        Page p = null;
        for (Page page : lesPages) {
            if (page.getNom().equals(s)) {
                p = page;
            }
        }
        return p;
    }
    
    public void supprimerPage(String s) {
        lesPages.remove(getPage(s));
    }

    public void updatePage(String oldNomPage, String nomPage) {
        getPage(oldNomPage).setNom(nomPage);
    }

    public ArrayList<Link> getLesLinks() {
        return lesLinks;
    }

    public void setLesLinks(ArrayList<Link> lesLinks) {
        this.lesLinks = lesLinks;
    }
    
    public void addLink(Link l) {
        this.lesLinks.add(l);
    }
    
    public Link getLink(String s) {
        Link link = null;
        for (Link l : lesLinks) {
            if (l.getNom().equals(s)) {
                link = l;
            }
        }
        return link;
    }
    
    public void removeLink(String s) {
        lesLinks.remove(getLink(s));
    }
    
    public void updateLink(String oldLink, String newLink, String type) {
        getLink(oldLink).setType(type);
        getLink(oldLink).setNom(newLink);
    }

    public String getMenuInc() {
        String menuInc = "<div id='div-menu'>\n";
        for (Page p : lesPages) {
            menuInc += "\t<a href='../" + p.getNom().toLowerCase() + "/'>" + p.getNom() + "</a>\n";
        }
        menuInc += "</div>\n\n";
        return menuInc;
    }

    public String getHeadInc(String nomProjet) {
        String headInc = "<title>" + nomProjet + "</title>\n";
        for (Link l : lesLinks) {
            switch (l.getType()) {
                case "Css" :
                    headInc += "<link rel='stylesheet' href='" + l.getNom() + "' />\n";
                    break;
                case "Script" :
                    headInc += "<script src='" + l.getNom() + "'></script>\n";
                    break;
            }
        }
        return headInc;
    }
    
}
