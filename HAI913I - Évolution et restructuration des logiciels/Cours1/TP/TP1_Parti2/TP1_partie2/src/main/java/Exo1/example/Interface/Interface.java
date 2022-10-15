package Exo1.example.Interface;
import Exo1.example.Parser;
import Exo1.example.Repository.JavaClass;
import Exo1.example.Repository.JavaMethode;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.LayoutStyle.ComponentPlacement.RELATED;

public class Interface extends JFrame implements ActionListener{
    private JButton recherche;


    private JLabel nbClasse;
    private JLabel nbligne;
    private JLabel nbMethode;
    private JLabel nbPackage;
    private JLabel nbMoyenMethode;
    private JLabel nbMoyenLigneMethode;
    private JLabel NbMoyenAttribut;
    private JLabel DixPmethode;
    private JLabel DixPAttribut;
    private JLabel DixPmethodeDixPAttribut;
    private JLabel Xmethode;
    private JLabel DixPLigne;
    private JLabel NbMaxParametre;
    private JPanel controlPanel;
    private JLabel resultnbligne;
    private JLabel resultnbClasse;
    private JLabel resultnbMethode;
    private JLabel resultnbPackage;
    private JLabel resultnbMoyenMethode;
    private JLabel resultnbMoyenLigneMethode;
    private JLabel resultNbMoyenAttribut;
    private JLabel resultNbMaxParametre;

    private JPanel resultDixPmethode = new JPanel(new GridLayout(0,1));
    private JPanel resultDixPAttribut = new JPanel(new GridLayout(0,1));
    private JPanel resultDixPmethodeDixPAttribut = new JPanel(new GridLayout(0,1));
    private JPanel resultXmethode = new JPanel(new GridLayout(0,1));
    private JPanel resultDixPLigne = new JPanel(new GridLayout(0,1));
    JTextField nbmethode;

    Parser parser ;



    public Interface(){
        parser = new Parser();
        recherche = new JButton("Dossier");
        GridLayout experimentLayout = new GridLayout(0,3);
        controlPanel = (JPanel) getContentPane();
        controlPanel.setLayout(experimentLayout);

        nbmethode = new JTextField(5);
        loadJPanel();
        GroupLayout(nbClasse,resultnbClasse);
        GroupLayout(nbligne,resultnbligne);
        GroupLayout(nbMethode,resultnbMethode);
        GroupLayout(nbPackage,resultnbPackage);
        GroupLayout(nbMoyenMethode,resultnbMoyenMethode);
        GroupLayout(nbMoyenLigneMethode,resultnbMoyenLigneMethode);
        GroupLayout(NbMoyenAttribut,resultNbMoyenAttribut);


        GroupLayoutScroll(DixPmethode,resultDixPmethode);
        GroupLayoutScroll(DixPAttribut,resultDixPAttribut);
        GroupLayoutScroll(DixPmethodeDixPAttribut,resultDixPmethodeDixPAttribut);
        GroupLayoutScrollX(Xmethode,resultXmethode);
        //GroupLayoutScroll(Xmethode);
        GroupLayoutScroll(DixPLigne,resultDixPLigne);




        GroupLayout(NbMaxParametre,resultNbMaxParametre);
        controlPanel.add(recherche);
        setSize(1400,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recherche.addActionListener(this);
        nbmethode.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                if(nbmethode.getText().matches("[+-]?\\d*(\\.\\d+)?") && !nbmethode.getText().equals("")) {
                    resultXmethode.removeAll();
                    ArrayList<JavaClass> p4 = parser.Xmethodes(Integer.parseInt(nbmethode.getText()));
                    for (JavaClass j : p4) {
                        resultXmethode.add(new JLabel(j.getName()));
                        resultXmethode.updateUI();

                    }
                }
            }
        });

    }




    public void loadJPanel(){
        nbClasse = new JLabel("Nombre de classes = ");
        nbligne = new JLabel("Nombre de lignes = ");
        nbMethode = new JLabel("Nombre de méthodes = ");
        nbPackage = new JLabel("Nombre de package = ");
        nbMoyenMethode = new JLabel("Nombre moyen méthodes = ");
        nbMoyenLigneMethode = new JLabel("Nombre moyen ligne = ");
        NbMoyenAttribut = new JLabel("Nombre moyen attribut = ");


        DixPmethode = new JLabel("10% des classes qui ont le plus de méthodes ");
        DixPAttribut = new JLabel("10% des classes qui contiennent le plus d'attributs ");
        DixPmethodeDixPAttribut = new JLabel("Classes qui correspond au 2 catégories précédentes ");


        Xmethode = new JLabel("Classes qui possède plus de X méthodes ");

        DixPLigne = new JLabel("10% des méthodes les plus grandes ");


        NbMaxParametre = new JLabel("Nombre max paramètre = ");

        resultnbligne = new JLabel();
        resultnbClasse = new JLabel();
        resultnbMethode = new JLabel();
        resultnbPackage = new JLabel();
        resultnbMoyenMethode = new JLabel();
        resultnbMoyenLigneMethode = new JLabel();
        resultNbMoyenAttribut = new JLabel();
        resultNbMaxParametre = new JLabel();




    }
    private void GroupLayoutScroll(JLabel Text, JPanel jpanel){
        JPanel pane2 = new JPanel();
        JScrollPane scrollFrame = new JScrollPane(jpanel);


        scrollFrame.setPreferredSize(new Dimension( 100,100));
        var gl = new GroupLayout(pane2);
        pane2.setLayout(gl);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
        sg.addComponent(Text).addPreferredGap(RELATED).addComponent(scrollFrame,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        gl.setHorizontalGroup(sg);
        GroupLayout.ParallelGroup pg = gl.createParallelGroup(
                LEADING, false);
        pg.addComponent(Text).addComponent(scrollFrame);
        gl.setVerticalGroup(pg);

        gl.setAutoCreateContainerGaps(true);

        pack();
        controlPanel.add(pane2);
    }

    private void GroupLayout(JLabel Text,JLabel result){
        JPanel pane2 = new JPanel();
        var gl = new GroupLayout(pane2);
        pane2.setLayout(gl);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
        sg.addComponent(Text).addPreferredGap(RELATED).addComponent(result,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        gl.setHorizontalGroup(sg);
        GroupLayout.ParallelGroup pg = gl.createParallelGroup(
                LEADING, false);
        pg.addComponent(Text).addComponent(result);
        gl.setVerticalGroup(pg);

        gl.setAutoCreateContainerGaps(true);

        pack();
        controlPanel.add(pane2);
    }
    private void GroupLayoutScrollX(JLabel Text, JPanel jpanel){
        JPanel pane2 = new JPanel();
        JScrollPane scrollFrame = new JScrollPane(jpanel);

        scrollFrame.setPreferredSize(new Dimension( 100,100));
        var gl = new GroupLayout(pane2);
        pane2.setLayout(gl);
        GroupLayout.SequentialGroup sg = gl.createSequentialGroup();
        sg.addComponent(Text).addPreferredGap(RELATED).addComponent(nbmethode).addComponent(scrollFrame,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        gl.setHorizontalGroup(sg);
        GroupLayout.ParallelGroup pg = gl.createParallelGroup(
                LEADING, false);
        pg.addComponent(Text).addComponent(nbmethode).addComponent(scrollFrame);
        gl.setVerticalGroup(pg);

        gl.setAutoCreateContainerGaps(true);

        pack();
        controlPanel.add(pane2);
    }


    public String ChosePath() throws IOException {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String currentPath = new java.io.File(".").getCanonicalPath();
        File theDirectory = new File(currentPath);
        f.setCurrentDirectory(theDirectory);
        f.showSaveDialog(null);
        return f.getSelectedFile().getPath();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            loadAST(ChosePath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void loadAST(String path) throws IOException {
        resultDixPmethode.removeAll();
        resultDixPAttribut.removeAll();
        resultDixPmethodeDixPAttribut.removeAll();
        resultDixPLigne.removeAll();


        parser.AnalyseAST(path);
        resultnbClasse.setText(Integer.toString(parser.nbClasses()));
        resultnbligne.setText(Integer.toString(parser.nbLigneApplication()));
        resultnbMethode.setText(Integer.toString(parser.nbMethode()));
        resultnbPackage.setText(Integer.toString(parser.nbPackage()));
        resultnbMoyenMethode.setText(Float.toString(parser.nbMoyenMetode()));
        resultnbMoyenLigneMethode.setText(Float.toString(parser.nbMoyenLigneMethode()));
        resultNbMoyenAttribut.setText(Float.toString(parser.MoyenAttribut()));

        ArrayList<JavaClass> p1 = parser.Classe10methode();
        for (JavaClass j:p1) {
            resultDixPmethode.add(new JLabel(j.getName()));
        }

        ArrayList<JavaClass> p2 = parser.Classe10Attribut();
        for (JavaClass j:p2) {
            resultDixPAttribut.add(new JLabel(j.getName()));
        }

        ArrayList<JavaClass> p3 = parser.Classe10Attribut10methode();
        for (JavaClass j:p3) {
            resultDixPmethodeDixPAttribut.add(new JLabel(j.getName()));
        }


        ArrayList<JavaMethode> p4 = parser.methode10ligne();
        for (JavaMethode j:p4) {
            resultDixPLigne.add(new JLabel(j.getName_methode()));
        }

        resultNbMaxParametre.setText(Integer.toString(parser.ParametreMax()));


    }
}
