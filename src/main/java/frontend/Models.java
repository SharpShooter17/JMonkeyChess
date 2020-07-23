package frontend;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Geometry;

public class Models {

    private Geometry pawn;
    private Geometry rook;
    private Geometry knight;
    private Geometry bishop;
    private Geometry king;
    private Geometry queen;
    public Geometry chessBoard;
    private final AssetManager assetManager;

    public Models(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Geometry blackPawn() {
        return makeBlack(pawn);
    }

    public Geometry whitePawn() {
        return makeWhite(pawn);
    }

    public Geometry blackRook() {
        return makeBlack(rook);
    }

    public Geometry whiteRook() {
        return makeWhite(rook);
    }

    public Geometry blackKnight() {
        return makeBlack(knight);
    }

    public Geometry whiteKnight() {
        return makeWhite(knight);
    }

    public Geometry blackBishop() {
        return makeBlack(bishop);
    }

    public Geometry whiteBishop() {
        return makeWhite(bishop);
    }

    public Geometry blackKing() {
        return makeBlack(king);
    }

    public Geometry whiteKing() {
        return makeWhite(king);
    }

    public Geometry blackQueen() {
        return makeBlack(queen);
    }

    public Geometry whiteQueen() {
        return makeWhite(queen);
    }

    private Geometry makeBlack(Geometry piece) {
        Geometry black = piece.clone();
        setDiffuse(black, new Vector4f(0, 0, 0, 1));
        return black;
    }

    private Geometry makeWhite(Geometry piece) {
        Geometry white = piece.clone();
        setDiffuse(piece, new Vector4f(1, 1, 1, 1));
        return white;
    }

    private void setDiffuse(Geometry geometry, Vector4f color) {
        geometry.getMaterial().getParam("Diffuse").setValue(color);
    }


    private void loadChessBoard() {
        chessBoard = (Geometry) assetManager.loadModel("assets/Models/Chessboard/12951_Stone_Chess_Board_v1_L3.obj");
        chessBoard.rotate((float) (-Math.PI / 2.0), 0, 0);
    }

    private void loadPawn() {
        pawn = (Geometry) assetManager.loadModel("assets/Models/Pawn/Pawn.obj");
        pawn.move(new Vector3f(0.0f, -0.8f, 0.0f));
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

    public void loadModels() {
        loadChessBoard();
        loadPawn();
        loadRook();
        loadKnight();
        loadBishop();
        loadKing();
        loadQueen();
    }

}
