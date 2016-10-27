package Trackme;

/**
 * Created by  jkeys on 10/13/2016.
 * Modified by jkeys on 10/13/2016.
 */

import java.util.*;

public class User {
    ArrayList<Graph> graphList;
    String userName; //name of the user

    public User(String n) {
        graphList = new ArrayList<Graph>();
        userName = n;
    }

    public void addGraph(Graph g) {
        graphList.add(g);
    }

    public void setName(String n) {
        userName = n;
    }

    public int numGraphs() {
        return graphList.size();
    }
    public String name()  {   return userName;  }

    public Graph getGraph(int i) {
        return graphList.get(i);
    }

    public void print() {
        System.out.println("User name:  " + userName);
        System.out.println("Num graphs: " + numGraphs());
        for (Graph g : graphList) g.print();
    }

}
