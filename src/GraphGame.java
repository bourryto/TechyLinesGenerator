import java.util.LinkedList;
import java.util.Random;

public class GraphGame {
    Graph graph;
    LinkedList<Vertex> path;

    public GraphGame(int x, int y){
        graph = new Graph(x, y);
        path = new LinkedList<>();
    }

    // TODO: for testpurposos, make this more modular, that we can also load a list of vertices
    public void generatePath(int maxLength){
        path.add(graph.vMap[0][2]);
        Random random = new Random();
        for (int i = 0; i < maxLength; i++) {
            int dir = random.nextInt(0, 8);
            try {
                Vertex o = path.getLast();
                Edge e = o.edges.get(random.nextInt(0, o.edges.size()));
                Vertex n = e.getOther(path.getLast());
                // Vertex n = graph.getNeighbour(path.getLast(), dir); // will be less efficient when we remove edges
                path.add(n);
                graph.edges.remove(e);
                o.edges.remove(e);
                n.edges.remove(e);
                LinkedList<Edge> crossedEdges = e.crossingEdges;
                for (Edge ce:crossedEdges){
                    ce.vs[0].edges.remove(ce);
                    ce.vs[1].edges.remove(ce);
                    graph.edges.remove(ce);
                    o.edges.remove(ce);
                    n.edges.remove(ce);
                    LinkedList<Edge> crossingThis = ce.crossingEdges;
                    for (Edge ct:crossingThis){
                        ct.crossingEdges.remove(ce);
                        ct.vs[0].edges.remove(ce);
                        ct.vs[1].edges.remove(ce);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Neighbour doesnt exist");
            }
        }
    }

    public String getPath(){
        if (path.isEmpty()){return "";}
        StringBuilder s = new StringBuilder();
        for(Vertex v: path){
            s.append(v.toString()).append(" >");
        }
        return s.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GraphGame:{\nVertices:\n");
        for(Vertex v: graph.vertices){
            sb.append(v.nestedString(1));
            sb.append("\n");
        }
        sb.append("Edges:\n");
        for(Edge e: graph.edges){
            sb.append(e.nestedString(1));
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();

    }
}
