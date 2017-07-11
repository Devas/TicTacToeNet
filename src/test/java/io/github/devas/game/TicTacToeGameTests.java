package io.github.devas.game;

import io.github.devas.util.ConfigurationLoader;
import io.github.devas.util.LocalizationLoader;
import io.github.devas.util.Vector2i;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Test
public class TicTacToeGameTests {

    private TicTacToeSettings settings;
    private TicTacToeGame game;

    private SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() throws Exception {
        settings = new TicTacToeSettings();
        settings.setPlayerO(new HumanPlayer("John", Symbol.o.toString()));
        settings.setPlayerX(new HumanPlayer("David", Symbol.x.toString()));
        settings.setBoardSize(new Vector2i(3, 3));
        settings.setMarksToWin(3);
        game = new TicTacToeGame(settings, new ConfigurationLoader(), new LocalizationLoader("ENG"));
    }

    public void testIsGameInitiallyNotFinished() {
        assertEquals(game.isFinished(), false);
    }

    public void testIsGameFinished() {
        game.setFinished(true);
        assertEquals(game.isFinished(), true);
    }

    public void testIsCurrentPlayerCorrect() {
        game.setInitialTurnPlayerA();
        softAssert.assertEquals(game.getCurrentPlayer(), settings.getPlayerO());
        game.setInitialTurnPlayerB();
        softAssert.assertEquals(game.getCurrentPlayer(), settings.getPlayerX());
        softAssert.assertAll();
    }

    public void testIsCurrentPlayerCorrectAfterSeveralTurns() {
        game.setInitialTurnPlayerA();
        softAssert.assertEquals(game.getCurrentPlayer(), settings.getPlayerO());
        game.nextTurn();
        softAssert.assertEquals(game.getCurrentPlayer(), settings.getPlayerX());
        game.nextTurn();
        softAssert.assertEquals(game.getCurrentPlayer(), settings.getPlayerO());
        softAssert.assertAll();
    }

}
