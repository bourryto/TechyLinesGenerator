import java.util.LinkedList;
import java.util.Random;

public class GraphGame {
    Graph graph;
    LinkedList<Vertex> path;

    public GraphGame(int x, int y){
        graph = new Graph(x, y);
        path = new LinkedList<>();
    }

    // todo: change so we only choose the next edge prom all actually viable edges, not all existing edges, to get a more consistent length
    public void generatePath(int maxLength){
        path.add(graph.vMap[0][2]);
        Random random = new Random();
        int lastTurnDirection = 4;
        for (int i = 0; i < maxLength; i++) {
            try {
                Vertex o = path.getLast();
                if (o.edges.isEmpty()){break;}                                  // stop if there are no more possible ways
                Edge e = o.edges.get(random.nextInt(0, o.edges.size()));
                Vertex n = e.getOther(path.getLast());
                boolean viableEdgesExist = false;
                for (Edge oneEdge: o.edges){
                    if(o.directionTo(oneEdge.getOther(o)) >= 3 && o.directionTo(oneEdge.getOther(o)) <= 5){viableEdgesExist = true;}
                }
                if (o.directionTo(n) < 3 || o.directionTo(n) > 5){continue;}    // only allowing downwards movement
                if (!viableEdgesExist){break;}  // stop checing if there are no viable edges
                if (lastTurnDirection == 3 && o.directionTo(n) == 5){continue;}
                if (lastTurnDirection == 5 && o.directionTo(n) == 3){continue;}

                lastTurnDirection = o.directionTo(n);
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
