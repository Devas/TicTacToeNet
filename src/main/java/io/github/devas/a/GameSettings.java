package io.github.devas.a;

class GameSettings implements Settings {

    private int maxNumberOfPlayers;

    GameSettings(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    @Override
    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

}
