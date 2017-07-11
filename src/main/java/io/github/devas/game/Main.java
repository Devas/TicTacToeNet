package io.github.devas.game;

import io.github.devas.util.ConfigurationManager;
import io.github.devas.util.LocalizationManager;
import io.github.devas.util.Vector2i;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.initGame();
    }

    private void initGame() {
        TicTacToeSettings settings = new TicTacToeSettings();

        ConfigurationManager configManager = new ConfigurationManager();
        settings.setConfigurationManager(configManager);

        LocalizationManager localizationManager = new LocalizationManager(askForLocale(configManager));
        settings.setLocalizationManager(localizationManager);

        configManager.println(localizationManager.get("greeting"));

        settings.setPlayerO(new HumanPlayer(configManager.get("playerO"), Symbol.o.toString()));
        settings.setPlayerX(new HumanPlayer(configManager.get("playerX"), Symbol.x.toString()));
        settings.setBoardSize(askForBoardSize(configManager, localizationManager));
        settings.setMarksToWin(askForMarksToWin(configManager, localizationManager));

        TicTacToeGame game = new TicTacToeGame(settings);

        configManager.print(settings.getPlayerO().getName() + localizationManager.get("whofirst"));
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equalsIgnoreCase("y")) {
            game.setInitialTurnPlayerA();
        } else {
            game.setInitialTurnPlayerB();
        }

        game.mainGameLoop();
    }

    /**
     * Add new languages by adding new cases and .properties files
     */
    private String askForLocale(ConfigurationManager configManager) {
        configManager.println("Locale settings | 'E' English | 'P' Polish | Press key: ");
        Scanner scanner = new Scanner(System.in);
        String lang;

        do {
            lang = scanner.next();
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

    private Vector2i askForBoardSize(ConfigurationManager configManager, LocalizationManager localizationManager) {
        final int MIN_BOARD_SIZE = 1;
        Scanner scanner = new Scanner(System.in);
        Vector2i boardSize = new Vector2i();
        do {
            configManager.print(localizationManager.get("xboardsize"));
            boardSize.setX(scanner.nextInt());
            configManager.print(localizationManager.get("yboardsize"));
            boardSize.setY(scanner.nextInt());
        } while (boardSize.getX() < MIN_BOARD_SIZE || boardSize.getY() < MIN_BOARD_SIZE);
        return boardSize;
    }

    private int askForMarksToWin(ConfigurationManager configManager, LocalizationManager localizationManager) {
        final int MIN_MARKS_TO_WIN = 3;
        Scanner scanner = new Scanner(System.in);
        int marksToWin;
        do {
            configManager.print(localizationManager.get("marks"));
            marksToWin = scanner.nextInt();
        } while (marksToWin < MIN_MARKS_TO_WIN);
        return marksToWin;
    }

}
