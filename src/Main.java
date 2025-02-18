import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {

    int xStart = 0;
    int yStart = 0;
    int xEnd = 0;
    int yEnd = 0;
    int element_width = 50;

    GraphGame graphGame;

    public void settings() { // diffrent from setup() here i can call size(), in setup i need to call surface.size()
    }

    public void setup(){
        int x = 10;
        int y = 17;
        surface.setSize((x)*element_width, (y)*element_width);
        surface.setLocation(100,100);
        surface.setResizable(true);
        graphGame = new GraphGame(x,y);
        graphGame.generatePath(20);
        System.out.println(graphGame.toString());
        System.out.println(graphGame.getPath());
    }



    public void draw() {
        background(249, 195, 220); // pastel pink

        stroke(180); // darkish gray
        for (Edge e: graphGame.graph.edges){
            line(corrPos(e.vs[0].x), corrPos(e.vs[0].y), corrPos(e.vs[1].x), corrPos(e.vs[1].y));
        }
        stroke(255); // white
        fill(255); // white
        for (Vertex v: graphGame.graph.vertices){
            ellipse(corrPos(v.x), corrPos(v.y), 10,10);
        }
        int i = 0;
        int j = 1;
        while(j < graphGame.path.size()){
            stroke(201, 0, 118); // flyking roaster purple
            line(   corrPos(graphGame.path.get(i).x), corrPos(graphGame.path.get(i).y),
                    corrPos(graphGame.path.get(j).x), corrPos(graphGame.path.get(j).y));
            i ++;
            j ++;
        }
    }

    // corecting position into the larger window
    private Integer corrPos(int a){
        return a*element_width + element_width/2;
    }



    public static void main(String[] args) {
        PApplet.main("Main"); // !has to be the class name!

    }
}