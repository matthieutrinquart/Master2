package org.example.Interface;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.example.MyWeightedEdge;
import org.example.Parser;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class JavaInterface extends JFrame implements ActionListener {


    private JButton recherche;

    private JLabel labelEtape ;
    private JTextField textFieldEtape ;
    private JLabel LabelCP ;
    private JTextField textFieldCP ;
    private Container controlPanel;
    private Parser parser;

    public JavaInterface() throws IOException {
        recherche = new JButton("Dossier");
        labelEtape = new JLabel("Etape regroupement clustering");
        textFieldEtape = new JTextField(5);
        LabelCP = new JLabel("Valeur CP = ");
        textFieldCP = new JTextField(5);
        controlPanel = getContentPane();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        recherche.setAlignmentX(Component.CENTER_ALIGNMENT);
        recherche.addActionListener(this);
        DirectedWeightedPseudograph<String, MyWeightedEdge> graph = new DirectedWeightedPseudograph<>(MyWeightedEdge.class);
        JGraphXAdapter<String, MyWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, MyWeightedEdge>(graph);


        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        controlPanel.add(new mxGraphComponent(graphAdapter));;
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);

        JPanel jpanel = new JPanel();
        FlowLayout experimentLayout = new FlowLayout();
        jpanel.setLayout(experimentLayout);
        jpanel.add(recherche);
        jpanel.add(labelEtape);
        jpanel.add(textFieldEtape);
        jpanel.add(LabelCP);
        jpanel.add(textFieldCP);

        controlPanel.add(jpanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            LoadAST(ChosePath());
            parser.exportGraph(parser.getGraph(),"image/Graph");
            parser.exportGraph(parser.getGraphmodule(),"image/Module");
            parser.exportGraph(parser.getGraphcluster(),"image/Cluster");
            parser.exportGraph(parser.getDendogramme(),"image/Dendogramme");
        } catch (IOException | ExecutionException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }

    public String ChosePath() throws IOException {
        JFileChooser f = new JFileChooser();
        String currentPath = new File(".").getCanonicalPath();
        File theDirectory = new File(currentPath);
        f.setCurrentDirectory(theDirectory);
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        return f.getSelectedFile().getPath();
    }

    public void LoadAST(String path) throws IOException, ExecutionException, InterruptedException {
        int etape = -1;
        float CP =1;
        if(textFieldEtape.getText().equals("") || !textFieldEtape.getText().matches("[+-]?\\d*(\\.\\d+)?")){
            etape = -1;
        }else{
            etape = Integer.parseInt(textFieldEtape.getText());
        }
        if(textFieldCP.getText().equals("") || !textFieldCP.getText().matches("[-+]?[0-9]*\\.?[0-9]+")){
            CP = 0;
        }else{
            CP = Float.parseFloat(textFieldCP.getText());
        }
        System.out.println("CP = " + CP );

        parser = new Parser(path,etape,CP);
        JGraphXAdapter<String, MyWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, MyWeightedEdge>(parser.getGraph());
        mxIGraphLayout layout = new mxFastOrganicLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        controlPanel.remove(0);
        controlPanel.add(new mxGraphComponent(graphAdapter),0);
        pack();
        setVisible(true);
    }
}
