import java.util.LinkedList;
import java.util.Objects;

public class Vertex {
    LinkedList<Edge> edges;
    int x;
    int y;
    
    public Vertex(int x, int y){
        this.x = x;
        this.y = y;
        this.edges = new LinkedList<>();
    }
    
    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public boolean hasEdgeTo(Vertex other){
        for(Edge edge: edges){
            if(edge.vs[0].equals(other) || edge.vs[1].equals(other)){return true;}
        }
        return false;
    }

    public int directionTo(Vertex other){
        int direction = -1;
        if(this.x == other.x){
            if(this.y < other.y){
                direction = 4;
            }
            else {
                direction = 0;
            }
        }
        else if(this.y == other.y){
            if(this.x < other.x){
                direction = 2;
            }
            else{
                direction = 6;
            }
        } else if (this.x < other.x && this.y > other.y) {
            direction = 1;
        } else if (this.x < other.x && this.y < other.y) {
            direction = 3;
        } else if (this.x > other.x && this.y < other.y) {
            direction = 5;
        } else if (this.x > other.x && this.y > other.y) {
            direction = 7;
        }
        return direction;
    }

    @Override
    public String toString() {
        return "[x:" + x + "|y:" + y +"]";
    }

    public String fullString(){
        StringBuilder sb = new StringBuilder("Vertex:{");
        sb.append(this.toString());
        for(Edge e: edges){
            sb.append(e);
        }
        sb.append("}");
        return sb.toString();
    }

    public String nestedString(int depth){
        String d = "";
        for (int i = 0; i < depth; i++) {
            d += "\t";
        }
        StringBuilder sb = new StringBuilder(d);
        sb.append("Vertex:{");
        sb.append(this.toString());
        sb.append("Edges:[");
        for(Edge e: edges){
            sb.append("\n");
            sb.append(e.nestedString(depth+1));
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return x == vertex.x && y == vertex.y;
    }

}
