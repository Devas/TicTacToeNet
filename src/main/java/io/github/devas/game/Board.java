package io.github.devas.game;

import io.github.devas.util.Vector2i;

abstract class Board {

    final int sizeX;
    final int sizeY;
    final String[][] board;
    private final String FILL_UP_STRING = "*";

    Board(Vector2i boardSize) {
        this.sizeX = boardSize.getX();
        this.sizeY = boardSize.getY();
        this.board = new String[sizeX][sizeY];
        reset();
    }

    int getSizeX() {
        return sizeX;
    }

    int getSizeY() {
        return sizeY;
    }

    int getArea() {
        return sizeX * sizeY;
    }

    String[][] getBoard() {
        return board;
    }

    String getValueAt(int x, int y) {
        return board[x][y];
    }

    String getValueAt(Vector2i position) {
        return board[position.getX()][position.getY()];
    }

    void setValueAt(int x, int y, String value) {
        board[x][y] = value;
    }

    void setValueAt(Vector2i position, String value) {
        board[position.getX()][position.getY()] = value;
    }

    /**
     * Fills up the board with specified String.
     *
     * @param value String used to fill up the board
     */
    void setAll(String value) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                board[x][y] = value;
            }
        }
    }

    /**
     * Fills up the board with default filling up String.
     */
    void reset() {
        setAll(FILL_UP_STRING);
    }

    /**
     * Returns whether board contains any String different from the default filling up String.
     */
    boolean isFilledUpWithDefaultString() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (!board[x][y].equals(FILL_UP_STRING)) {
                    return false;
                }
            }
        }
        return true;
    }

}
