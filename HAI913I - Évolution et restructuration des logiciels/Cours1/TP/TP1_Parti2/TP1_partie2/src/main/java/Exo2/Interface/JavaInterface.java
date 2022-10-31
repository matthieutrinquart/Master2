package Exo2.Interface;

import Exo2.Parser;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import guru.nidi.graphviz.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

public class JavaInterface extends JFrame implements ActionListener {


    private JButton recherche;
    private Container controlPanel;
    private Parser parser;
    Graph<String, DefaultEdge> graph;
    public JavaInterface() throws IOException {
        recherche = new JButton("Dossier");
        controlPanel = getContentPane();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        recherche.setAlignmentX(Component.CENTER_ALIGNMENT);
        recherche.addActionListener(this);
        graph= new SimpleGraph<>(DefaultEdge.class);
        JGraphXAdapter<String, DefaultEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultEdge>(graph);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        controlPanel.add(new mxGraphComponent(graphAdapter));;
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        //Create the exporter (without ID provider)
        DOTExporter<String, DefaultEdge> exporter=new DOTExporter<>();
        controlPanel.add(recherche);

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
        String currentPath = new java.io.File(".").getCanonicalPath();
        File theDirectory = new File(currentPath);
        f.setCurrentDirectory(theDirectory);
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        return f.getSelectedFile().getPath();
    }

    static public void exportGraph(Graph graph) throws IOException, ExecutionException, InterruptedException {
        DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>();
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(graph, writer);
        System.out.println(writer.toString());
        createDotGraph(writer.toString());
    }
    public static void createDotGraph(String dotFormat) throws IOException, ExecutionException, InterruptedException {

        MutableGraph g = new guru.nidi.graphviz.parse.Parser().read(dotFormat);
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("Graph.png"));
    }
    public void LoadAST(String path) throws IOException {
        parser = new Parser(path);
        graph=  parser.getGraph();
        JGraphXAdapter<String, DefaultEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultEdge>(graph);


        mxIGraphLayout layout = new mxFastOrganicLayout(graphAdapter);

        layout.execute(graphAdapter.getDefaultParent());

        controlPanel.remove(0);
        controlPanel.add(new mxGraphComponent(graphAdapter),0);
        pack();
        setVisible(true);
    }
}
