package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void createChessBoard() {
        Geometry chessBoard = (Geometry) assetManager.loadModel("assets/Models/Chessboard/12951_Stone_Chess_Board_v1_L3.obj");
        chessBoard.rotate((float) (-Math.PI / 2.0), 0, 0);
        rootNode.attachChild(chessBoard);
    }

    private void loadPawn() {
        Geometry pawn = (Geometry) assetManager.loadModel("assets/Models/Pawn/Pawn.obj");
        pawn.scale(100f);
        rootNode.attachChild(pawn);
    }

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0, 40, 50));
        cam.lookAt(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.2f));
        rootNode.addLight(al);

        Box b = new Box(0.5f, 0.5f, 0.5f);
        Geometry geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);

        loadPawn();

        createChessBoard();
    }
}