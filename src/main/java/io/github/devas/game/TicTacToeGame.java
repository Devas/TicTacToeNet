package io.github.devas.game;

import io.github.devas.util.ConfigurationManager;
import io.github.devas.util.LocalizationManager;
import io.github.devas.util.Vector2i;

import java.util.Scanner;

/**
 * If player wins the x or o is upper-cased to X or O
 */
class TicTacToeGame extends Game1vs1 {

    private ConsoleBoard board;
    private WinnerChecker winnerChecker;
    private ConfigurationManager configManager;
    private LocalizationManager localizationManager;
    private int turnNumber;

    TicTacToeGame(TicTacToeSettings settings) {
        super(settings.getPlayerO(), settings.getPlayerX());
        this.board = new ConsoleBoard(settings.getBoardSize());
        this.winnerChecker = new WinnerChecker(board, settings.getMarksToWin());
        this.configManager = settings.getConfigurationManager();
        this.localizationManager = settings.getLocalizationManager();
    }

    @Override
    public void mainGameLoop() {
        while (!isFinished()) {
            while (!isFinished()) {
                runSingleTurn();
            }
        }
    }

    private void runSingleTurn() {
        turnNumber++;
        configManager.println(localizationManager.get("turn") + turnNumber + " ***");
        configManager.println(getPlayerA() + " | " + getPlayerB());
        Vector2i position = handleInputCoords();
        BoardMove playersMove = new BoardMove(position);
        getCurrentPlayer().addMove(playersMove);
        board.setValueAt(playersMove.getPosition(), getCurrentPlayer().getNick());
        TurnStatus turnStatus = winnerChecker.checkAll(getCurrentPlayer().getNick());
        configManager.print(board.draw());
        if (turnStatus.equals(TurnStatus.WON)) {
            getCurrentPlayer().incrementGamesWon();
            getCurrentPlayer().getResult().increaseScore(3);
            configManager.println(localizationManager.get("wins") + getCurrentPlayer().getName() + " | " + getPlayerA() + " | " + getPlayerB());
            resolveEndOfGame();
        }
        if (turnNumber == board.getArea()) {
            getPlayerA().getResult().increaseScore(1);
            getPlayerB().getResult().increaseScore(1);
            configManager.println(localizationManager.get("draw") + getCurrentPlayer().getName() + " | " + getPlayerA() + " | " + getPlayerB());
            resolveEndOfGame();
        }
        nextTurn();
    }

    /**
     * Handles moves and asks to press input again if move is out of board or net move has been done already
     */
    private Vector2i handleInputCoords() {
        Scanner scanner = new Scanner(System.in);
        configManager.println(localizationManager.get("nowplays") + getCurrentPlayer().getName());
        Vector2i position = new Vector2i();
        Move move;
        do {
            configManager.print(localizationManager.get("xcoord"));
            position.setX(scanner.nextInt() - 1);
            configManager.print(localizationManager.get("ycoord"));
            position.setY(scanner.nextInt() - 1);
            move = new BoardMove(position);
        }
        while (position.getX() < 0 || position.getY() < 0 || position.getX() >= board.getSixeX() || position.getY() >= board.getSixeY() ||
                getPlayerA().hasMoved(move) || getPlayerB().hasMoved(move));
        return position;
    }

    /**
     * Game can end after bestOf or it can ask when end
     * Uncomment nextTurn() to always use the same initial player or comment to swap initial player in every game
     */
    private void resolveEndOfGame() {
        getPlayerA().resetMoves();
        getPlayerB().resetMoves();

        if (shouldGameEndBasedOnBestOf(3) || shouldGameEndBasedOnInput()) {
            configManager.println(localizationManager.get("whole") + getCurrentPlayer().getName() + " | " + getPlayerA() + " | " + getPlayerB());
            setFinished(true);
        } else {
            setFinished(false);
            board.reset();
            turnNumber = 0;
//            nextTurn()
        }
    }

    /**
     * Indicates game should end if player wins bestOf games
     */
    private boolean shouldGameEndBasedOnBestOf(int bestOf) {
        return (getPlayerA().getGamesWon() == bestOf || getPlayerB().getGamesWon() == bestOf);
    }

    /**
     * Indicates game should end if player wins bestOf games
     */
    private boolean shouldGameEndBasedOnInput() {
        configManager.print(localizationManager.get("again"));
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equalsIgnoreCase("n");
    }

}
