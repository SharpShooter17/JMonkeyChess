package frontend;

import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board {

    private final Vector4f defaultAmbient = new Vector4f(0.23333f, 0.23333f, 0.23333f, 1.0f);
    private final Vector4f selectedAmbient = new Vector4f(0.0f, 1.0f, 0.0f, 1.0f);

    private boolean isSelected = false;
    private int selectedPiece;
    private final Models models;
    private final List<Geometry> board = new ArrayList<>();

    public Board(Models models) {
        this.models = models;
        initializeBoard();
        this.selectedPiece = 0;
        selectNext();
    }

    public void selectNext() {
        setAmbient(board.get(selectedPiece), defaultAmbient);
        if (selectedPiece + 1 >= board.size()) {
            selectedPiece = -1;
        }
        setAmbient(board.get(++selectedPiece), selectedAmbient);
    }

    public void selectPrevious() {
        setAmbient(board.get(selectedPiece), defaultAmbient);
        if (selectedPiece - 1 < 0) {
            selectedPiece = board.size();
        }
        setAmbient(board.get(--selectedPiece), selectedAmbient);
    }

    private void setAmbient(Geometry geometry, Vector4f ambient) {
        geometry.getMaterial().getParam("Ambient").setValue(ambient);
    }

    private void initializePawns(float startX, final float z, Geometry pawn) {
        for (int i = 0; i < 8; i++) {
            float x = startX + i * 5.5f;
            Geometry clonedPawn = pawn.clone();
            clonedPawn.move(new Vector3f(x, 0.0f, z));
            board.add(clonedPawn);
        }
    }

    private void initializeDoubleFigure(final float startX, final float z, Geometry figure) {
        Geometry piece = figure.clone();
        piece.move(startX, 0, z);
        board.add(piece);
        figure.move(-startX, 0, z);
        board.add(figure);
    }

    private void initializeFigure(final float x, final float z, Geometry figure) {
        figure.move(x, 0, z);
        board.add(figure);
    }

    private void initializeBoard() {
        initializePawns(-19.0f, 13.5f, models.whitePawn());
        initializeDoubleFigure(19.0f, 19, models.whiteRook());
        initializeDoubleFigure(13.5f, 18.0f, models.whiteKnight());
        initializeDoubleFigure(8.0f, 19.0f, models.whiteBishop());
        initializeFigure(-2.7f, 19.0f, models.whiteQueen());
        initializeFigure(2.7f, 19.0f, models.whiteKing());

        initializePawns(-19.0f, -13.5f, models.blackPawn());
        initializeDoubleFigure(-19.0f, -19, models.blackRook());
        initializeDoubleFigure(-13.5f, -18.5f, models.blackKnight());
        initializeDoubleFigure(-8.0f, -19.0f, models.blackBishop());
        initializeFigure(-2.7f, -19.0f, models.blackQueen());
        initializeFigure(2.7f, -19.0f, models.blackKing());
    }

    public void moveRight() {
        move(1, 0);
    }

    public void moveLeft() {
        move(-1 ,0);
    }

    public void moveUp() {
        move(0, 1);
    }

    public void moveDown() {
        move(0, -1);
    }

    private void move(int x, int z) {
        board.get(selectedPiece).move(new Vector3f(x * 5.5f, 0.0f, z * 5.5f));
    }

    public void toggleSelect() {
        this.isSelected = !this.isSelected;
    }

    public Collection<Geometry> getBoard() {
        return board;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
