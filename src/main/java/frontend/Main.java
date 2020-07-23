package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class Main extends SimpleApplication {

    private Geometry pawn;
    private Geometry rook;
    private Geometry knight;
    private Geometry bishop;
    private Geometry king;
    private Geometry queen;
    private Geometry chessBoard;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void loadChessBoard() {
        chessBoard = (Geometry) assetManager.loadModel("assets/Models/Chessboard/12951_Stone_Chess_Board_v1_L3.obj");
        chessBoard.rotate((float) (-Math.PI / 2.0), 0, 0);
    }

    private void loadPawn() {
        pawn = (Geometry) assetManager.loadModel("assets/Models/Pawn/Pawn.obj");
        pawn.scale(100f);
    }

    private void loadRook() {
        rook = (Geometry) assetManager.loadModel("assets/Models/Rook/Rook.obj");
        rook.scale(100f);
    }

    private void loadKnight() {
        knight = (Geometry) assetManager.loadModel("assets/Models/Knight/Knight.obj");
        knight.scale(100f);
    }

    private void loadBishop() {
        bishop = (Geometry) assetManager.loadModel("assets/Models/Bishop/Bishop.obj");
        bishop.scale(100f);
    }

    private void loadKing() {
        king = (Geometry) assetManager.loadModel("assets/Models/King/King.obj");
        king.scale(100f);
    }

    private void loadQueen() {
        queen = (Geometry) assetManager.loadModel("assets/Models/Queen/Queen.obj");
        queen.scale(100f);
    }

    private void loadModels() {
        loadChessBoard();
        loadPawn();
        loadRook();
        loadKnight();
        loadBishop();
        loadKing();
        loadQueen();
    }

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0, 0, 30));
        cam.lookAt(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.2f));
        rootNode.addLight(al);

        loadModels();

        rootNode.attachChild(chessBoard);
        rootNode.attachChild(king);
    }
}