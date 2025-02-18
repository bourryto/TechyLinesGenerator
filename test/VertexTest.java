import com.sun.jdi.request.VMDeathRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    @Test
    void directionTo() {
        Vertex v = new Vertex(1,1);
        Vertex[] others = {
                new Vertex(1,0),
                new Vertex(2,0),
                new Vertex(2,1),
                new Vertex(2,2),
                new Vertex(1,2),
                new Vertex(0,2),
                new Vertex(0,1),
                new Vertex(0,0)
        };

        Assertions.assertEquals(0, v.directionTo(others[0]));
        Assertions.assertEquals(1, v.directionTo(others[1]));
        Assertions.assertEquals(2, v.directionTo(others[2]));
        Assertions.assertEquals(3, v.directionTo(others[3]));
        Assertions.assertEquals(4, v.directionTo(others[4]));
        Assertions.assertEquals(5, v.directionTo(others[5]));
        Assertions.assertEquals(6, v.directionTo(others[6]));
        Assertions.assertEquals(7, v.directionTo(others[7]));
    }
}