package Business;

import Business.Tree.RTree.Circle;
import Business.Tree.RTree.R_Tree;
import Persistence.CircleCSVDAO;
import Presentation.CanvasView;
import Presentation.MenuOptions;
import Presentation.Messages;

import java.io.FileNotFoundException;

public class UIControllerCanvas {

    private R_Tree r_tree;
    private static CanvasView canvasView;

    public UIControllerCanvas() {
        CircleCSVDAO csvdao = new CircleCSVDAO();
        try {
            r_tree = (R_Tree) csvdao.readData("data/rtreeXXS.paed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(r_tree);
        //r_tree.paint();
        canvasView = new CanvasView();
    }

    /**
     * The canvas menu
     */
    public void start(){
        MenuOptions choice = MenuOptions.ADD_CIRCLE;
        while (true){
            choice = canvasView.canvasMenu();
            switch (choice){
                case ADD_CIRCLE -> addCircle();
                case REMOVE_CIRCLE -> removeCircle();
                case VISUALIZE_CIRCLE -> visualizeCircle();
                case SEARCH_AREA_CIRCLE -> searchAreaCircle();
                case SEARCH_SPEC_CIRCLE -> searchSpecCircle();
                case GO_BACK -> {return;}
            }
        }

    }

    /**
     * The first option for the canvas menu
     */
    private void addCircle() {
        float xCoord = canvasView.grabXCoordinate();
        float yCoord = canvasView.grabYCoordinate();
        float rad = canvasView.grabCircleRadius();
        String col = canvasView.grabCircleColor();
        Circle newCircle = new Circle(xCoord,yCoord,rad,col);
        long startTime = System.nanoTime();
        //Shape circleToPoint = new Business.Tree.RTree.Point(newCircle);
        r_tree.insert(newCircle);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        canvasView.showMessage(Messages.ADD_CIRCLE);
    }

    /**
     * The second option for the canvas menu
     */
    private void removeCircle(){
        float xCoord = canvasView.grabXCoordinate();
        float yCoord = canvasView.grabYCoordinate();
        long startTime = System.nanoTime();
        if (r_tree.delete(xCoord, yCoord))
            canvasView.showMessage(Messages.DELETE_CIRCLE);
        else
            System.out.println("NODE NOT FOUND!");
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
    }

    /**
     * The third option for the canvas menu
     */
    private void visualizeCircle(){
        long startTime = System.nanoTime();
        canvasView.showMessage(Messages.VISUAL_CIRCLE);
        r_tree.paint();
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
    }

    /**
     * The fourth option for the canvas menu
     */
    private void searchAreaCircle(){
        float[] points = canvasView.grabRectanglePoints();
        long startTime = System.nanoTime();
        r_tree.searchArea(points[3],points[1],points[0],points[2]);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
    }

    /**
     * The last option for the canvas menu
     */
    private void searchSpecCircle(){
        float xCoord = canvasView.grabXCoordinateSearch();
        float yCoord = canvasView.grabYCoordinateSearch();
        float rad = canvasView.grabCircleRadiusSearch();
        String col = canvasView.grabCircleColorSearch();
        long startTime = System.nanoTime();
        canvasView.showMessage(Messages.NEARBY_CIRCLE);
        r_tree.specialSearch(xCoord,yCoord,rad,col);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
    }


}
