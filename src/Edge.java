import java.util.Arrays;
import java.util.LinkedList;

public class Edge {
    Vertex[] vs;
    LinkedList<Edge> crossingEdges;
    /* 0: vertical
       1: /
       2: horizontal
       3:  \
     */
    int direction;

    public Edge(Vertex v1, Vertex v2){
        vs = new Vertex[]{v1, v2};
        crossingEdges = new LinkedList<>();
        caluclateDirection();
    }

    public void addEdge(Edge edge){
        crossingEdges.add(edge);
    }

    // so far not checking if actually in there
    public Vertex getOther(Vertex v1){
        if (vs[0] == v1){return vs[1];}
        return vs[0];
    }

    public boolean isCrossing(Edge other){
        if(this.vs[0].equals(other.vs[0]) || this.vs[0].equals(other.vs[1]) || this.vs[1].equals(other.vs[0]) || this.vs[1].equals(other.vs[1])){
            return false; // if they share a vertex, straight lines can't cross
        }
        if (direction == 0 || direction == 2){
            return false; // non diagonal lines can't cross other lines (in our case)
        }

        Vertex other_upper;
        Vertex other_lower;
        if (other.vs[0].y < other.vs[1].y){
            other_upper = other.vs[1];
            other_lower = other.vs[0];
        }
        else{
            other_upper = other.vs[0];
            other_lower = other.vs[1];
        }
        Vertex this_upper;
        Vertex this_lower;
        if (this.vs[0].y < this.vs[1].y){
            this_upper = this.vs[1];
            this_lower = this.vs[0];
        }
        else{
            this_upper = this.vs[0];
            this_lower = this.vs[1];
        }
        // case me \ and other /
        if (    other_lower.x == this_lower.x -1 && other_lower.y == this_lower.y &&
                other_upper.x == this_upper.x +1 && other_upper.y == this_upper.y){
            return true;
        }
        // case me / and other \
        return other_lower.x == this_lower.x + 1 && other_lower.y == this_lower.y &&
                other_upper.x == this_upper.x - 1 && other_upper.y == this_upper.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()){ return false;}
        Edge other = (Edge)obj;
        return (this.vs[0].equals(other.vs[0]) || this.vs[0].equals(other.vs[1])) &&
                (this.vs[1].equals(other.vs[0]) || this.vs[1].equals(other.vs[1]));
    }

    public String shortString(){
        return "[" + vs[0] + " <> " + vs[1] + "]";
    }

    @Override
    public String toString() {
        return nestedString(0);
    }

    public String nestedString(int depth) {
        String d = "";
        for (int i = 0; i < depth; i++) {
            d += "\t";
        }
        String s = d + "Edge{" + directionAsString() +
                shortString() +
                ", Crossing Edges: [";
        for (Edge ce:crossingEdges){
            s += "\n" + d + "\t" + ce.shortString() + ", ";
        }
        s = crossingEdges.isEmpty() ? s : s.substring(0,s.length()-2);
        s += "]}";
        return s;
    }
    public String directionAsString(){
        return switch (direction) {
            case 0 -> "|";
            case 1 -> "/";
            case 2 -> "-";
            case 3 -> "\\";
            default -> "";
        };
    }


    /* 0: vertical
       1: /
       2: horizontal
       3:  \
     */
    private void caluclateDirection(){
        this.direction = vs[0].directionTo(vs[1])%4;
    }
}
