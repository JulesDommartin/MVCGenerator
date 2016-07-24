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
public class PHPClass {
    
    private String nom;
    private ArrayList<String> lesAttributs;
    
    public PHPClass(String nom) {
        this.nom = nom;
        this.lesAttributs = new ArrayList<>();
        this.addAttribut("id");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<String> getLesAttributs() {
        return lesAttributs;
    }

    public void setLesAttributs(ArrayList<String> lesAttributs) {
        this.lesAttributs = lesAttributs;
    }
    
    public void addAttribut(String attribut) {
        this.lesAttributs.add(attribut);
    }
    
    public String getStringClassePHP() {
        String s = "<?php\n"
                + "\n"
                + "\tclass " + nom + " {\n\n"
                + "";
        for (String attribut : lesAttributs) {
            s += "\t\tpublic $" + attribut + ";\n";
        }
        s += "\n";
        s += "\t\tfunction __construct(";
        for (int i = 0; i < lesAttributs.size(); i++) {
            if (i < lesAttributs.size() - 1) {
                s += "$" + lesAttributs.get(i) + ",";
            } else {
                s += lesAttributs.get(i);
            }
        }
        s += ") {\n";
        for (String attribut : lesAttributs) {
            s+= "\t\t\t$this->" + attribut + " = $" + attribut + ";\n";
        }
        s += "\t\t}\n";
        s += "\t}\n\n"
                + "?>";
        return s;
    }
    
    public String getStringSQL() {
        String sql = "\t\t//----------------------------------\n"
                + "\t\t//  Functions on class " + this.nom + "\n"
                + "\t\t//----------------------------------\n"
                + "\t\t\n";
        sql += getAllSql();
        sql += getOneSql();
        sql += getAddSql();
        sql += getUpdateSql();
        sql += getRemoveSql();
        sql += getNextIndexSql();
        sql += "\n\n";
        return sql;
    }
    
    private String getAllSql() {
        String sql = "\t\tfunction getAll" + this.nom + "s() {\n"
                + "\t\t\t$sql='SELECT * FROM " + this.nom + "';\n"
                + "\t\t\t$les" + this.nom + "s = null;\n"
                + "\t\t\ttry {\n"
                + "\t\t\t\tforeach  ($this->dbh->query($sql) as $row) {\n"
                + "\t\t\t\t\t$" + this.nom.toLowerCase().charAt(0) + " = new " + this.nom + "(" + getConstructorByRows() + ");\n"
                + "\t\t\t\t\t$les" + this.nom + "s[] = $" + this.nom.toLowerCase().charAt(0) + ";\n"
                + "\t\t\t\t}\n"
                + "\t\t\t} catch (PDOException $e) {\n"
                + "\t\t\t\techo 'Problem in getAll" + this.nom + "s() : \\n' . $e->getMessage();\n"
                + "\t\t\t}\n"
                + "\t\t\treturn $les" + this.nom + "s;\n"
                + "\t\t}\n\n";
        return sql;
    }
    
    private String getOneSql() {
        String sql = "\t\tfunction getOne" + this.nom + "($id) {\n"
                + "\t\t\t$sql='SELECT * FROM " + this.nom + " WHERE id=' . $id;\n"
                + "\t\t\t$" + this.nom.toLowerCase().charAt(0) + " = null;\n"
                + "\t\t\ttry {\n"
                + "\t\t\t\tforeach  ($this->dbh->query($sql) as $row) {\n"
                + "\t\t\t\t\t$" + this.nom.toLowerCase().charAt(0) + " = new " + this.nom + "(" + getConstructorByRows() + ");\n"
                + "\t\t\t\t}\n"
                + "\t\t\t} catch (PDOException $e) {\n"
                + "\t\t\t\techo 'Problem in getOne" + this.nom + "s() : \\n' . $e->getMessage();\n"
                + "\t\t\t}\n"
                + "\t\t\treturn $" + this.nom.toLowerCase().charAt(0) + ";\n"
                + "\t\t}\n\n";
        return sql;
    }
    
    private String getAddSql() {
        String sql = "\t\tfunction add" + this.nom + "(" + getParametersList() + ") {\n"
                + "\t\t\t$sql='INSERT INTO " + this.nom + " VALUES (id = $id, " + getParametersQuoteSql() + ")';\n"
                + "\t\t\ttry {\n"
                + "\t\t\t\t$this->dbh->exec($sql);\n"
                + "\t\t\t\t}\n"
                + "\t\t\t} catch (PDOException $e) {\n"
                + "\t\t\t\techo 'Problem in add" + this.nom + "() : \\n' . $e->getMessage();\n"
                + "\t\t\t}\n"
                + "\t\t}\n\n";
        return sql;
    }
    
    private String getUpdateSql() {
        String sql = "\t\tfunction update" + this.nom + "(" + getParametersList() + ") {\n"
                + "\t\t\t$sql='UPDATE " + this.nom + " SET " + getParametersUpdateSql() + " WHERE id=' . $id;\n"
                + "\t\t\ttry {\n"
                + "\t\t\t\t$this->dbh->exec($sql);\n"
                + "\t\t\t\t}\n"
                + "\t\t\t} catch (PDOException $e) {\n"
                + "\t\t\t\techo 'Problem in update" + this.nom + "() : \\n' . $e->getMessage();\n"
                + "\t\t\t}\n"
                + "\t\t}\n\n";
        return sql;
    }
    
    private String getRemoveSql() {
        String sql = "\t\tfunction remove" + this.nom + "($id) {\n"
                + "\t\t\t$sql='DELETE FROM " + this.nom + " WHERE id=' . $this->dbh->quote($id) . ')';\n"
                + "\t\t\ttry {\n"
                + "\t\t\t\t$this->dbh->exec($sql);\n"
                + "\t\t\t\t}\n"
                + "\t\t\t} catch (PDOException $e) {\n"
                + "\t\t\t\techo 'Problem in remove" + this.nom + "() : \\n' . $e->getMessage();\n"
                + "\t\t\t}\n"
                + "\t\t}\n\n";
        return sql;
    }
    
    private String getNextIndexSql() {
        String sql = "\t\tfunction getNextIndexOf" + this.nom + "() {\n" 
                + "\t\t\t$les" + this.nom + "s = $this->getAll" + this.nom + "s();\n"
                + "\t\t\t$indexMax = 0;\n"
                + "\t\t\tif ($les" + this.nom + "s != null) {\n"
                + "\t\t\t\tforeach ($les" + this.nom + " as $" + this.nom + ")\n"
                + "\t\t\t\t\tif (intval($" + this.nom + "->id) > $indexMax) {\n"
                + "\t\t\t\t\t\t$indexMax = intval($" + this.nom + "->id);\n"
                + "\t\t\t\t\t}\n"
                + "\t\t\t\t}\n"
                + "\t\t\t}\n"
                + "\t\t\treturn $indexMax + 1;\n"
                + "\t\t}\n\n";
        return sql;
    }
     
    private String getConstructorByRows() {
        String constructorByRows = "";
        
        for (int i = 0; i < lesAttributs.size(); i++) {
            constructorByRows += "$row['" + lesAttributs.get(i) + "']";
            if (i < lesAttributs.size() - 1) {
                constructorByRows += ", ";
            }
        }
        
        return constructorByRows;
    }
    
    private String getParametersList() {
        String parametersList = "";
        for (int i = 0; i < lesAttributs.size(); i++) {
            parametersList += "$" + lesAttributs.get(i);
            if (i < lesAttributs.size() - 1) {
                parametersList += ", ";
            }
        }
        return parametersList;
    }
    
    private String getParametersQuoteSql() {
        String parametersQuoteSql = "";
        for (int i = 1; i < lesAttributs.size(); i++) {
            parametersQuoteSql += "' . $this->dbh->quote($" + lesAttributs.get(i) + ") . '";
            if (i < lesAttributs.size() - 1) {
                parametersQuoteSql += ", ";
            }
        }
        return parametersQuoteSql;
    }
    
    private String getParametersUpdateSql() {
        String parametersUpdateSql = "";
        for (int i = 1; i < lesAttributs.size(); i++) {
            parametersUpdateSql += lesAttributs.get(i) + "=' . $this->dbh->quote($" + lesAttributs.get(i) + ") . '";
            if (i < lesAttributs.size() - 1) {
                parametersUpdateSql += ", ";
            }
        }
        return parametersUpdateSql;
    }
    
    private String getIsset(String attribut) {
        String isset = "\tif (isset($_POST['" + attribut + "']) && $_POST['" + attribut + "'] != '') {\n"
                + "\t\t$" + attribut + " = $_POST['" + attribut + "'];\n"
                + "\t} else {\n"
                + "\t\t$erreur .= '" + attribut + " manquant / ';\n"
                + "\t\t$" + attribut + " = '';\n"
                + "\t}\n\n";
        return isset;
    }
    
    private String getAllIssets() {
        String isset = "";
        for (String attribut : lesAttributs) {
            isset += this.getIsset(attribut);
        }
        return isset;
    }
    
    private String getAttributeDifferentFromEmpty() {
        String diff = "";
        for (String attribut : lesAttributs) {
            diff += "$" + attribut + " != ''";
            if (lesAttributs.indexOf(attribut) < lesAttributs.size() - 1) {
                diff += " && ";
            }
        }
        return diff;
    }
    
    private String getAddCodeFromControleur() {
        String addCode = "\tif (" + this.getAttributeDifferentFromEmpty() + ") {\n"
                + "\t\t$id = $controleur->getNextIndex" + this.nom + "();\n"
                + "\t\tif ($controleur->add" + this.nom + "(" + getParametersList() +")) {\n"
                + "\t\t\techo '" + this.nom + " ajouté';\n"
                + "\t\t} else {\n"
                + "\t\t\t$erreur = $controleur->add" + this.nom + "(" + getParametersList() +");\n"
                + "\t\t}\n"
                + "\t}\n\n";
        return addCode;
    }
    
    private String getRemoveCodeFromControleur() {
        String removeCode = "\tif ($id != '') {\n"
                + "\t\tif ($controleur->remove" + this.nom + "($id)) {\n"
                + "\t\t\techo '" + this.nom + " supprimé';\n"
                + "\t\t} else {\n"
                + "\t\t\t$erreur = $controleur->remove" + this.nom + "($id);\n"
                + "\t\t}\n"
                + "\t}\n\n";
        return removeCode;
    }
    
    public String getAddPhpFile() {
        String phpString = "<?php\n"
                + "\n"
                + "\trequire_once('../modele/Controleur.php');\n"
                + "\n"
                + "\t$erreur = '';\n"
                + "\n"
                + getAllIssets()
                + getAddCodeFromControleur()
                + "\tif ($erreur != '') {\n"
                + "\t\tvar_dump($erreur);\n"
                + "\t}\n\n"
                + "?>";
        return phpString;
    }

    public String getRemovePhpFile() {
        String phpString = "<?php\n"
                + "\n"
                + "\trequire_once('../modele/Controleur.php');\n"
                + "\n"
                + "\t$erreur = '';\n"
                + "\n"
                + this.getIsset("id")
                + this.getRemoveCodeFromControleur()
                + "\n"
                + "?>";
        return phpString;
    }

    public String getGetAllPhpFile() {
        String phpString = "<?php\n"
                + "\n"
                + "\trequire_once('../modele/Controleur.php');\n"
                + "\n"
                + "\t$" + this.nom + "s = $controleur->getAll" + this.nom + "s();\n"
                + "\n"
                + "\techo $" + this.nom + "s;\n"
                + "\n"
                + "?>";
        return phpString;
    }
    
    public String getGetAllJsonPhpFile() {
        String phpString = "<?php\n"
                + "\n"
                + "\trequire_once('../modele/Controleur.php');\n"
                + "\n"
                + "\t$" + this.nom + "s = $controleur->getAll" + this.nom + "s();\n"
                + "\n"
                + "\techo json_encode((array)$" + this.nom + "s);\n"
                + "\n"
                + "?>";
        return phpString;
    }
    
    private String getAllJSFunction() {
        String js = "\t\t$.ajax({\n"
                + "\t\t\turl: '../scripts/get_" + this.nom.toLowerCase() + "s_json',\n"
                + "\t\t\ttype: 'GET',\n"
                + "\t\t\tsuccess: function(data) {\n"
                + "\t\t\t\tvm." + this.nom.toLowerCase() + "s = JSON.parse(data);\n"
                + "\t\t\t},\n"
                + "\t\t\terror: function(xhr, ajaxOptions, thrownError) {\n"
                + "\t\t\t\tconsole.log(xhr);\n"
                + "\t\t\t\tconsole.log(thrownError);\n"
                + "\t\t\t}\n"
                + "\t\t});\n";
        return js;
    }
    
    private String getAllAttributesValJS() {
        String js = "";
        for (String attribut : lesAttributs) {
            js += "\t\tvar " + attribut + " = $('#" + attribut + "').val();\n";
        }
        return js;
    }
    
    private String getVerifiyJSNotEmpty() {
        String js = "";
        for (String attribut : lesAttributs) {
            js += attribut + " === ''";
            if (lesAttributs.indexOf(attribut) < lesAttributs.size() -1) {
                js += " || ";
            }
        }
        return js;
    }
    
    private String addJSFunction() {
        String js = "\t$('#add-'" + this.nom.toLowerCase() + "-form').on('submit', function(e) {\n"
                + "\t\te.preventDefault();\n"
                + "\n"
                + "\tvar $this = $(this);"
                + "\n"
                + getAllAttributesValJS() + "\n"
                + "\n"
                + "\t\tif (" + getVerifiyJSNotEmpty() + ") {\n"
                + "\t\t\talert('Champs manquants');\n"
                + "\t\t} else {\n"
                + "\t\t\t"
                + "\t\t}"
                + "\n"
                + "\t});\n\n";
        return js;
    }
    
    public String getJSFile() {
        String js = "$(document).ready(function () {\n"
                + "\n"
                + "\t var vm = this;"
                + "\n"
                + "\t// Get All " + this.nom + "s function\n"
                + "\tthis.get" + this.nom + "s = function() {\n"
                + getAllJSFunction()
                + "\t};\n"
                + "\n"
                + "});";
        return js;
    }
    
}
