package org.example.Interface;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.example.MyWeightedEdge;
import org.example.Parser;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dimacs.DIMACSExporter;
import org.jgrapht.nio.dimacs.DIMACSImporter;
import org.jgrapht.nio.dot.DOTExporter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class JavaInterface extends JFrame implements ActionListener {


    private JButton recherche;
    private Container controlPanel;
    private Parser parser;
    private DirectedWeightedPseudograph<String, MyWeightedEdge>  graph;

    //SimpleWeightedGraph<String, DefaultWeightedEdge> graph;
    public JavaInterface() throws IOException {
        recherche = new JButton("Dossier");
        controlPanel = getContentPane();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        recherche.setAlignmentX(Component.CENTER_ALIGNMENT);
        recherche.addActionListener(this);
       // graph= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        graph = new DirectedWeightedPseudograph<>(MyWeightedEdge.class);
        JGraphXAdapter<String, MyWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, MyWeightedEdge>(graph);


        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        controlPanel.add(new mxGraphComponent(graphAdapter));;
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        //Create the exporter (without ID provider)
        DOTExporter<String, MyEdge> exporter=new DOTExporter<>();
        controlPanel.add(recherche);

    }
    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
    public static ListenableGraph<String, MyEdge> buildGraph() {
        ListenableDirectedWeightedGraph<String, MyEdge> g =
                new ListenableDirectedWeightedGraph<String, MyEdge>(MyEdge.class);

        String x1 = "x1";
        String x2 = "x2";
        String x3 = "x3";

        g.addVertex(x1);
        g.addVertex(x2);
        g.addVertex(x3);

        MyEdge e = g.addEdge(x1, x2);
        g.setEdgeWeight(e, 1);
        e = g.addEdge(x2, x3);
        g.setEdgeWeight(e, 2);

        e = g.addEdge(x3, x1);
        g.setEdgeWeight(e, 3);

        return g;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            LoadAST(ChosePath());
            exportGraph(graph);
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

    static public void exportGraph(DirectedWeightedPseudograph graph) throws IOException, ExecutionException, InterruptedException {
        /*
        DIMACSExporter<String, DefaultWeightedEdge> exporter = new DIMACSExporter<>();
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(graph, writer);
        createDotGraph(writer.toString());
                 */

        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultWeightedEdge>(graph);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, new Color(0f,0f,0f,.5f), true, null);
        File imgFile = new File("Graph.png");
        ImageIO.write(image, "PNG", imgFile);
    }
    public static void createDotGraph(String dotFormat) throws IOException, ExecutionException, InterruptedException {
        MutableGraph g = new guru.nidi.graphviz.parse.Parser().read(dotFormat);
        Graphviz gviz = Graphviz.fromGraph(g);
        gviz.render(Format.PNG).toFile(new File("Graph.png"));


    }
    public void LoadAST(String path) throws IOException {
        parser = new Parser(path);
        graph=  parser.getGraph();
        JGraphXAdapter<String, MyWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, MyWeightedEdge>(graph);


        mxIGraphLayout layout = new mxFastOrganicLayout(graphAdapter);

        layout.execute(graphAdapter.getDefaultParent());

        controlPanel.remove(0);
        controlPanel.add(new mxGraphComponent(graphAdapter),0);
        pack();
        setVisible(true);
    }
}
