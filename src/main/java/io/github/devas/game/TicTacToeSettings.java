package io.github.devas.game;

import io.github.devas.util.ConfigurationManager;
import io.github.devas.util.LocalizationManager;
import io.github.devas.net.Settings;
import io.github.devas.util.Vector2i;

class TicTacToeSettings implements Settings {

    private Player playerO;
    private Player playerX;
    private Player firsPlayingPlayer;
    private Vector2i boardSize;
    private int marksToWin;
    private ConfigurationManager configurationManager;
    private LocalizationManager localizationManager;

    Player getPlayerO() {
        return playerO;
    }

    void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }

    Player getPlayerX() {
        return playerX;
    }

    void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    Player getFirsPlayingPlayer() {
        return firsPlayingPlayer;
    }

    void setFirsPlayingPlayer(Player firsPlayingPlayer) {
        this.firsPlayingPlayer = firsPlayingPlayer;
    }

    Vector2i getBoardSize() {
        return boardSize;
    }

    void setBoardSize(Vector2i boardSize) {
        this.boardSize = boardSize;
    }

    int getMarksToWin() {
        return marksToWin;
    }

    void setMarksToWin(int marksToWin) {
        this.marksToWin = marksToWin;
    }

    ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    LocalizationManager getLocalizationManager() {
        return localizationManager;
    }

    void setLocalizationManager(LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
    }

}
