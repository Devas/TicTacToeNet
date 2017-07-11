package io.github.devas.game;

import io.github.devas.managers.ConfigurationManager;
import io.github.devas.managers.LocalizationManager;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.initAndRunGame();
    }

    private void initAndRunGame() {
        Scanner s = new Scanner(System.in);

        ConfigurationManager configManager = new ConfigurationManager();

        String locale = askForLocale(configManager);
        LocalizationManager localizationManager = new LocalizationManager(locale);
        localizationManager.loadLocalization();

        configManager.println(localizationManager.get("greeting"));

        HumanPlayer playerO = new HumanPlayer(configManager.get("playera"), Symbol.O.asString);
        HumanPlayer playerX = new HumanPlayer(configManager.get("playerb"), Symbol.X.asString);

        Position2D boardSize = askForBoardSize(configManager, localizationManager);

        int marksToWin = askForMarksToWin(configManager, localizationManager);

        TicTacToeGame game = new TicTacToeGame(playerO, playerX, boardSize.getX(), boardSize.getY(), marksToWin, configManager, localizationManager);

        configManager.print(playerO.getName() + localizationManager.get("whofirst"));
        if (s.next().equalsIgnoreCase("y")) {
            game.setInitialTurnPlayerA();
        } else {
            game.setInitialTurnPlayerB();
        }

        while (!game.isFinished()) {
            game.startGame();
        }
    }

    /**
     * Add new languages by adding new cases and .properties files
     */
    private String askForLocale(ConfigurationManager configManager) {
        configManager.println("Locale settings | 'E' English | 'P' Polish | Press key: ");
        Scanner s = new Scanner(System.in);
        String lang;

        do {
            lang = s.next();
        } while (!lang.matches("[A-Za-z]"));

        switch (lang.toUpperCase()) {
            case "E":
                return "ENG";
            case "P":
                return "PL";
            default:
                return "ENG";
        }
    }

    private Position2D askForBoardSize(ConfigurationManager configManager, LocalizationManager localizationManager) {
        final int minBoardSize = 1;
        Scanner s = new Scanner(System.in);
        int x = 0;
        int y = 0;
        do {
            configManager.print(localizationManager.get("xboardsize"));
            x = s.nextInt();
            configManager.print(localizationManager.get("yboardsize"));
            y = s.nextInt();
        } while (x < minBoardSize || y < minBoardSize);
        return new Position2D(x, y);
    }

    private int askForMarksToWin(ConfigurationManager configManager, LocalizationManager localizationManager) {
        final int minMarksToWin = 3;
        Scanner s = new Scanner(System.in);
        int marksToWin = 0;
        do {
            configManager.print(localizationManager.get("marks"));
            marksToWin = s.nextInt();
        } while (marksToWin < minMarksToWin);
        return marksToWin;
    }

}
