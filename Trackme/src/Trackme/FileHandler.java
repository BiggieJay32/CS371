package Trackme;
/**
 * Created by jkeys on 10/12/2016.
 */

import java.io.*;
import java.util.*;

/**
 * Created  by jkeys on 10/12/16.

 */

public class FileHandler {
    private Node readNode(BufferedReader r) throws Exception {
        String nodeNote = r.readLine();
        String xVal_s = r.readLine();
        String yVal_s = r.readLine();
        int xVal = Integer.parseInt(xVal_s);
        int yVal = Integer.parseInt(yVal_s);

        return new Node(xVal, yVal, nodeNote);
    }

    private void writeNode(PrintWriter w, Node n) throws Exception {
        String nodeNote = n.note();
        String xVal_s = String.valueOf(n.x());
        String yVal_s = String.valueOf(n.y());

        w.println(nodeNote);
        w.println(xVal_s);
        w.println(yVal_s);
    }

    private Graph readGraph(BufferedReader r) throws Exception {
        String graphName = r.readLine();
        String xLabel = r.readLine();
        String xMin_s = r.readLine();
        String xMax_s = r.readLine();
        String yLabel = r.readLine();
        String yMin_s = r.readLine();
        String yMax_s = r.readLine();

        int xMin = Integer.parseInt(xMin_s);
        int xMax = Integer.parseInt(xMax_s);
        int yMin = Integer.parseInt(yMin_s);
        int yMax = Integer.parseInt(yMax_s);

        return (new Graph(graphName, xLabel, yLabel, xMin, xMax, yMin, yMax));

    }

    private void writeGraph(PrintWriter w, Graph g) {
        String graphName = g.graphName();
        String xLabel = g.xLabel();
        String xMin_s = String.valueOf(g.xMin());
        String xMax_s = String.valueOf(g.xMax());
        String yLabel = g.yLabel();
        String yMin_s = String.valueOf(g.yMin());
        String yMax_s = String.valueOf(g.yMax());
        String numNodes_s = String.valueOf(g.numNodes());

        w.println(graphName);
        w.println(xLabel);
        w.println(xMin_s);
        w.println(xMax_s);
        w.println(yLabel);
        w.println(yMin_s);
        w.println(yMax_s);
        w.println(numNodes_s);
    }

    //reads the user file, and adds all nodes to each graph, and each graph to the user object
    private User readUserFile(String userFile) throws Exception {
        String userName;
        String numGraphs_s, numNodes_s;

        BufferedReader r = new BufferedReader(new FileReader(userFile));

        userName = r.readLine();
        numGraphs_s = r.readLine();
        int numGraphs = Integer.parseInt(numGraphs_s);

        User u = new User(userName);

        for (int i = 0; i != numGraphs; i++) {
            Graph g = readGraph(r);

            numNodes_s = r.readLine();

            int numNodes = Integer.parseInt(numNodes_s); //j many nodes per graph

            for (int j = 0; j != numNodes; j++) {
                Node n = readNode(r);
                g.addNode(n); //add the j'th node to Graph g's node list
            }

            u.addGraph(g); //add the i'th graph to the User object
        }//end for

        //should be no more input here
        return u;
    }


    public void writeUserFile(User u, String userFile) throws Exception {
        String userName = u.name();
        int numGraphs = u.numGraphs();

        PrintWriter w = new PrintWriter(userFile);

        w.println(userName);
        w.println(String.valueOf(numGraphs));

        for (int i = 0; i != u.numGraphs(); i++) {
            Graph g = u.getGraph(i);
            writeGraph(w, g);

            for (int j = 0; j != g.numNodes(); j++) {
                Node n = g.getNode(j);
                writeNode(w, n);
            }
        }

        w.close();
    }

    public static void main(String[] args) throws Exception {
        FileHandler f = new FileHandler();

        User u = f.readUserFile("user_file.txt");
        u.print();
        f.writeUserFile(u, "user_file_out.txt");
    }

}
