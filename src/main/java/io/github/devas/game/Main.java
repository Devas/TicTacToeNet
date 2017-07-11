package io.github.devas.game;

import io.github.devas.util.ConfigurationLoader;
import io.github.devas.util.LocalizationLoader;
import io.github.devas.util.Vector2i;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.initGame();
    }

    private void initGame() {
        ConfigurationLoader configurationLoader = new ConfigurationLoader();
        LocalizationLoader localizationLoader = new LocalizationLoader(askForLocale(configurationLoader));

        configurationLoader.println(localizationLoader.get("greeting"));

        TicTacToeSettings settings = new TicTacToeSettings();
        settings.setPlayerO(new HumanPlayer(configurationLoader.get("playerO"), Symbol.o.toString()));
        settings.setPlayerX(new HumanPlayer(configurationLoader.get("playerX"), Symbol.x.toString()));
        settings.setBoardSize(askForBoardSize(configurationLoader, localizationLoader));
        settings.setMarksToWin(askForMarksToWin(configurationLoader, localizationLoader));

        TicTacToeGame game = new TicTacToeGame(settings, configurationLoader, localizationLoader);

        configurationLoader.print(settings.getPlayerO().getName() + localizationLoader.get("whofirst"));
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
    private String askForLocale(ConfigurationLoader configManager) {
        Scanner scanner = new Scanner(System.in);
        int pickedLanguageId;
        do {
            configManager.print("Locale settings | '1' English | '2' Polish | Press key: ");
            pickedLanguageId = scanner.nextInt();
        } while (pickedLanguageId < 1 || pickedLanguageId > 2);

        Map<Integer, String> languages = new HashMap<>();
        languages.put(1, "ENG");
        languages.put(2, "PL");
        return languages.get(pickedLanguageId);
    }

    private Vector2i askForBoardSize(ConfigurationLoader configManager, LocalizationLoader localizationLoader) {
        final int MIN_BOARD_SIZE = 1;
        Scanner scanner = new Scanner(System.in);
        Vector2i boardSize = new Vector2i();
        do {
            configManager.print(localizationLoader.get("xboardsize"));
            boardSize.setX(scanner.nextInt());
            configManager.print(localizationLoader.get("yboardsize"));
            boardSize.setY(scanner.nextInt());
        } while (boardSize.getX() < MIN_BOARD_SIZE || boardSize.getY() < MIN_BOARD_SIZE);
        return boardSize;
    }

    private int askForMarksToWin(ConfigurationLoader configManager, LocalizationLoader localizationLoader) {
        final int MIN_MARKS_TO_WIN = 3;
        Scanner scanner = new Scanner(System.in);
        int marksToWin;
        do {
            configManager.print(localizationLoader.get("marks"));
            marksToWin = scanner.nextInt();
        } while (marksToWin < MIN_MARKS_TO_WIN);
        return marksToWin;
    }

}
