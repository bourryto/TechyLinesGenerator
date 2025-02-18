import java.util.LinkedList;

public class Graph {
    int sizeX;
    int sizeY;
    LinkedList<Vertex> vertices;
    Vertex[][] vMap;
    LinkedList<Edge> edges;

    public Graph(int x, int y){
        sizeX = x;
        sizeY = y;
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        vMap = new Vertex[sizeY][sizeX];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                Vertex new_vertex = new Vertex(i, j);
                vertices.add(new_vertex);
                vMap[j][i] = new_vertex;
            }
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                Vertex v = vMap[j][i];
                LinkedList<Vertex> neighbours = getNeighbours(v);
                for (Vertex n: neighbours){
                    if (v.hasEdgeTo(n)){continue;}
                    Edge ne = new Edge(v, n);
                    if (ne.direction == 2){continue;}
                    v.addEdge(ne);
                    n.addEdge(ne);
                    edges.add(ne);
                }
            }
        }
        for(Edge e: edges){
            for (Edge o: edges){
                if (e == o){
                    continue;
                }
                if (e.isCrossing(o)){
                    if(e.crossingEdges.contains(o)){continue;}
                    e.addEdge(o);
                    o.addEdge(e);
                }
            }
        }
    }

    private boolean isValidPosition(int x, int y){
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }

    private LinkedList<Vertex> getNeighbours(Vertex vertex){
        LinkedList<Vertex> n = new LinkedList<>();
        int i = vertex.x; int j = vertex.y;
        if(isValidPosition(i, j-1)){n.add(vMap[j-1][i]);}   // n
        if(isValidPosition(i+1, j-1)){n.add(vMap[j-1][i+1]);}   // ne
        if(isValidPosition(i+1, j)){n.add(vMap[j][i+1]);}   // e
        if(isValidPosition(i+1, j+1)){n.add(vMap[j+1][i+1]);}   // se
        if(isValidPosition(i, j+1)){n.add(vMap[j+1][i]);}   // s
        if(isValidPosition(i-1, j+1)){n.add(vMap[j+1][i-1]);}   // sw
        if(isValidPosition(i-1, j)){n.add(vMap[j][i-1]);}   // w
        if(isValidPosition(i-1, j-1)){n.add(vMap[j-1][i-1]);}   // nw
        return n;
    }

    public Vertex getNeighbour(Vertex vertex, int dir) throws IndexOutOfBoundsException{
        int i = vertex.x; int j = vertex.y;
        switch (dir) {
            case 0:
                if (isValidPosition(i, j - 1)) {
                    return vMap[j - 1][i];
                }   // n
                break;
            case 1:
                if (isValidPosition(i + 1, j - 1)) {
                    return vMap[j - 1][i + 1];
                }   // ne
                break;
            case 2:
                if (isValidPosition(i + 1, j)) {
                    return vMap[j][i + 1];
                }   // e
                break;
            case 3:
                if (isValidPosition(i + 1, j + 1)) {
                    return vMap[j + 1][i + 1];
                }   // se
                break;
            case 4:
                if (isValidPosition(i, j + 1)) {
                    return vMap[j + 1][i];
                }   // s
                break;
            case 5:
                if (isValidPosition(i - 1, j + 1)) {
                    return vMap[j + 1][i - 1];
                }   // sw
                break;
            case 6:
                if (isValidPosition(i - 1, j)) {
                    return vMap[j][i - 1];
                }   // w
                break;
            case 7:
                if (isValidPosition(i - 1, j - 1)) {
                    return vMap[j - 1][i - 1];
                }   // nw
                break;
        }
        throw new IndexOutOfBoundsException("out of bounds");
    }


}
