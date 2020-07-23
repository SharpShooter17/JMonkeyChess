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

    private void initializeBoard() {
        initializePawns(-19.0f, -13.5f, models.blackPawn());
        initializePawns(-19.0f, 13.5f, models.whitePawn());
    }

    public Collection<Geometry> getBoard() {
        return board;
    }
}
