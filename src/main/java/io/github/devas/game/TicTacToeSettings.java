package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;
import io.github.devas.net.Settings;

class TicTacToeSettings implements Settings {

    private Player playerA;
    private Player playerB;
    private int sizeX;
    private int sizeY;
    private int marksToWin;
    private ConfigurationManager conf;
    private LocalizationManager loc;

}
