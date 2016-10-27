package Trackme;
/**
 * Created by jkeys on 10/13/2016.
 * Modified by jkeys on 10/26/2016.
 */
public class Node {
    private int x, y; //x and y coordinates
    private String note;

    public String note() {
        return note;
    }

    public int x() { return x; }
    public int y() { return y; }

    public void setNote(String n) { note = n; }
    public void setX(int n) { x = n; }
    public void setY(int n) { y = n; }

    public Node(int xNew, int yNew, String n) {
        x = xNew;
        y = yNew;
        note = n;
    }

    public void print() {
        System.out.println("Node note: " + note);
        System.out.println("x value: " + String.valueOf(x));
        System.out.println("y value: " + String.valueOf(y));
    }

}
