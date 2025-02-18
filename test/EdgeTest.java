import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EdgeTest {


    @Test
    void directions() {
        Edge verticalEdge1 = new Edge(new Vertex(0,0), new Vertex(0,1));    // hin
        Edge verticalEdge2 = new Edge(new Vertex(0,1), new Vertex(0,0));    // back
        Edge verticalEdge3 = new Edge(new Vertex(0,0), new Vertex(0,-1));   // negative numbers

        Edge upEdge1 = new Edge(new Vertex(0,1), new Vertex(1,0));          // hin
        Edge upEdge2 = new Edge(new Vertex(1,0), new Vertex(0,1));          // back
        Edge upEdge3 = new Edge(new Vertex(0,0), new Vertex(1, -1));        // negative numbers

        Edge horizontalEdge1 = new Edge(new Vertex(0,0), new Vertex(1,0));  // hin
        Edge horizontalEdge2 = new Edge(new Vertex(1,0), new Vertex(0,0));  // back
        Edge horizontalEdge3 = new Edge(new Vertex(0,0), new Vertex(-1,0)); // negative numbers

        Edge downEdge1 = new Edge(new Vertex(0,0), new Vertex(1,1));        // hin
        Edge downEdge2 = new Edge(new Vertex(1,1), new Vertex(0,0));        // back
        Edge downEdge3 = new Edge(new Vertex(0,0), new Vertex(-1,-1));      // negative numbers

        Assertions.assertEquals(0, verticalEdge1.direction);
        Assertions.assertEquals(0, verticalEdge2.direction);
        Assertions.assertEquals(0, verticalEdge3.direction);
        Assertions.assertEquals("|", verticalEdge1.directionAsString());
        Assertions.assertEquals("|", verticalEdge2.directionAsString());
        Assertions.assertEquals("|", verticalEdge3.directionAsString());
        Assertions.assertEquals(1, upEdge1.direction);
        Assertions.assertEquals(1, upEdge2.direction);
        Assertions.assertEquals(1, upEdge3.direction);
        Assertions.assertEquals("/", upEdge1.directionAsString());
        Assertions.assertEquals("/", upEdge2.directionAsString());
        Assertions.assertEquals("/", upEdge3.directionAsString());
        Assertions.assertEquals(2, horizontalEdge1.direction);
        Assertions.assertEquals(2, horizontalEdge2.direction);
        Assertions.assertEquals(2, horizontalEdge3.direction);
        Assertions.assertEquals("-", horizontalEdge1.directionAsString());
        Assertions.assertEquals("-", horizontalEdge2.directionAsString());
        Assertions.assertEquals("-", horizontalEdge3.directionAsString());
        Assertions.assertEquals(3, downEdge1.direction);
        Assertions.assertEquals(3, downEdge2.direction);
        Assertions.assertEquals(3, downEdge3.direction);
        Assertions.assertEquals("\\", downEdge1.directionAsString());
        Assertions.assertEquals("\\", downEdge2.directionAsString());
        Assertions.assertEquals("\\", downEdge3.directionAsString());
    }
}