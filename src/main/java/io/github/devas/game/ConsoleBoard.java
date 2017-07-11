package io.github.devas.game;

import io.github.devas.util.Vector2i;

class ConsoleBoard extends Board implements Drawable {

    ConsoleBoard(Vector2i boardSize) {
        super(boardSize);
    }

    @Override
    public String draw() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                stringBuilder.append(board[x][y]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
