package frontend;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

import java.util.ArrayList;
import java.util.Collection;

public class Board {

    private final Models models;
    private final Collection<Geometry> board = new ArrayList<>();

    public Board(Models models) {
        this.models = models;
        initializeBoard();
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
        initializePawns(-19.0f, -13.5f, models.blackPawn());
        initializePawns(-19.0f, 13.5f, models.whitePawn());

        initializeDoubleFigure(-19.0f, -19, models.blackRook());
        initializeDoubleFigure(19.0f, 19, models.whiteRook());

        initializeDoubleFigure(-13.5f, -18.0f, models.blackKnight());
        initializeDoubleFigure(13.5f, 18.0f, models.whiteKnight());

        initializeDoubleFigure(-8.0f, -19.0f, models.blackBishop());
        initializeDoubleFigure(8.0f, 19.0f, models.whiteBishop());

        initializeFigure(-2.7f, -19.0f, models.blackQueen());
        initializeFigure(-2.7f, 19.0f, models.whiteQueen());

        initializeFigure(2.7f, -19.0f, models.blackKing());
        initializeFigure(2.7f, 19.0f, models.whiteKing());
    }

    public Collection<Geometry> getBoard() {
        return board;
    }
}
