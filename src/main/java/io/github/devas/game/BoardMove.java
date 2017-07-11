package io.github.devas.game;

import io.github.devas.util.Vector2i;

/**
 * Represents a single move on board.
 * Additionally we can define here other activities connected with a single move.
 */
class BoardMove implements Move {

    private Vector2i position;

    BoardMove(Vector2i position) {
        this.position = position;
    }

    Vector2i getPosition() {
        return position;
    }

    void setPosition(Vector2i position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardMove boardMove = (BoardMove) o;

        return position != null ? position.equals(boardMove.position) : boardMove.position == null;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }

}
