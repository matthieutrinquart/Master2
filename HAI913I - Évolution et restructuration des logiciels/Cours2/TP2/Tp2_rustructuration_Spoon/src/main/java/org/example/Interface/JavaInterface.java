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
    private JTextField textField ;
    private Container controlPanel;
    private Parser parser;

    public JavaInterface() throws IOException {
        recherche = new JButton("Dossier");
        textField = new JTextField(5);
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
        jpanel.add(textField);

        controlPanel.add(jpanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            LoadAST(ChosePath());
            parser.exportGraph(parser.getGraph(),"Graph");
            parser.exportGraph(parser.getGraphcluster(),"Cluster");
            parser.exportGraph(parser.getDendogramme(),"Dendogramme");
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
        if(textField.getText() == "" || !textField.getText().matches("[+-]?\\d*(\\.\\d+)?")){
            parser = new Parser(path,-1);
        }else{
            parser = new Parser(path,Integer.parseInt(textField.getText()));
        }
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
