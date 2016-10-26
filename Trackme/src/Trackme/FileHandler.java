package Trackme;
/**
 * Created by jkeys on 10/12/2016.
 */

import java.io.*;
import java.util.*;

/**
 * Created  by jkeys on 10/12/16.
 * Modified by jkeys on 10/25/16.
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

        System.err.println("Finished reading beginning graph info, graphName: " + graphName);

        int xMin = Integer.parseInt(xMin_s);
        int xMax = Integer.parseInt(xMax_s);
        int yMin = Integer.parseInt(yMin_s);
        int yMax = Integer.parseInt(yMax_s);

        return new Graph(graphName, xLabel, yLabel, xMin, xMax, yMin, yMax);

    }

    private void writeGraph(PrintWriter w, Graph g) throws Exception {
        w.println(g.graphName());
        w.println(g.xLabel());
        w.println(String.valueOf(g.xMin()));
        w.println(String.valueOf(g.xMax()));
        w.println(g.yLabel());
        w.println(String.valueOf(g.yMin()));
        w.println(String.valueOf(g.yMax()));
        w.println(String.valueOf(g.numNodes()));
    }

    //reads the user file, and adds all nodes to each graph, and each graph to the user object
    private User readUserFile(String userFile) throws Exception {
        String userName, numGraphs_s;

        BufferedReader r = new BufferedReader(new FileReader(userFile));

        userName = r.readLine();
        numGraphs_s = r.readLine();

        User u = new User(userName);

        int numGraphs = Integer.parseInt(numGraphs_s);

        Graph g;

        for (int i = 0; i != numGraphs; i++) {
            g = readGraph(r);                         //read the graph info
            String numNodes_s = r.readLine();
            int numNodes = Integer.parseInt(numNodes_s);

            for (int j = 0; j != numNodes; j++) {
                Node n = readNode(r);
                g.addNode(n);                               //add the j'th node to the user's i'th graph
            }

            u.addGraph(g);                                  //add the i'th graph to the User object
        }//end for

        //should be no more input here
        r.close();

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

        String path = System.getProperty("user.dir") + System.getProperty("file.separator");
        System.out.println(path + "user_file.txt");

        System.out.println(new File(".").getAbsoluteFile());

        User u = f.readUserFile(path + "user_file.txt");
        u.print();
        f.writeUserFile(u, "user_file_out.txt");

    }

}
