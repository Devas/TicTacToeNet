package io.github.devas.game;

import io.github.devas.util.Vector2i;

abstract class Board implements World {

    final int sixeX;
    final int sixeY;
    final String[][] board;
    private final String FILL_UP_STRING = "*";

    Board(Vector2i boardSize) {
        this.sixeX = boardSize.getX();
        this.sixeY = boardSize.getY();
        this.board = new String[sixeX][sixeY];
        setAll(FILL_UP_STRING);
    }

    int getSixeX() {
        return sixeX;
    }

    int getSixeY() {
        return sixeY;
    }

    int getArea() {
        return sixeX * sixeY;
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
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
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
        for (int y = 0; y < sixeY; y++) {
            for (int x = 0; x < sixeX; x++) {
                if (!board[x][y].equals(FILL_UP_STRING)) {
                    return false;
                }
            }
        }
        return true;
    }

}
