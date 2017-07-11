package io.github.devas.game;

import io.github.devas.util.Vector2i;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test
public class BoardTests {

    private SoftAssert softAssert = new SoftAssert();

    public void testCalculationOfBoardAreas() {
        Board board = new ConsoleBoard(new Vector2i(3, 3));
        softAssert.assertEquals(board.getArea(), 9);
        board = new ConsoleBoard(new Vector2i(3, 5));
        softAssert.assertEquals(board.getArea(), 15);
        board = new ConsoleBoard(new Vector2i(5, 4));
        softAssert.assertEquals(board.getArea(), 20);
        softAssert.assertAll();
    }

    public void testIsBoardFilledUp() {
        Board board = new ConsoleBoard(new Vector2i(3, 3));
        softAssert.assertTrue(board.isFilledUpWithDefaultString());
        board.setValueAt(1, 1, "X");
        softAssert.assertFalse(board.isFilledUpWithDefaultString());
        board.setValueAt(1, 1, "*");
        softAssert.assertTrue(board.isFilledUpWithDefaultString());
        softAssert.assertAll();
    }

    public void testIfBoardResetsItselfWithDefaultValue() {
        Board board = new ConsoleBoard(new Vector2i(3, 3));
        board.reset();
        softAssert.assertEquals(board.getValueAt(1, 2), "*");
        softAssert.assertEquals(board.getValueAt(0, 0), "*");
        softAssert.assertEquals(board.getValueAt(2, 2), "*");
        softAssert.assertAll();
    }

    public void testIfWholeBoardIsFilledWithSpecifiedString() {
        Board board = new ConsoleBoard(new Vector2i(3, 3));
        board.setAll("@");
        softAssert.assertEquals(board.getValueAt(2, 1), "@");
        softAssert.assertEquals(board.getValueAt(0, 0), "@");
        softAssert.assertEquals(board.getValueAt(1, 1), "@");
        softAssert.assertAll();
    }

    public void testBoardSetValueAndGetValueForPrimitives() {
        Board board = new ConsoleBoard(new Vector2i(3, 3));
        board.setValueAt(0, 0, "X");
        softAssert.assertEquals(board.getValueAt(0, 1), "*");
        softAssert.assertEquals(board.getValueAt(1, 0), "*");
        softAssert.assertEquals(board.getValueAt(0, 0), "X");
        softAssert.assertAll();
    }

    public void testBoardSetValueAndGetValueForPosition() {
        Board board = new ConsoleBoard(new Vector2i(3, 3));
        board.setValueAt(new Vector2i(0, 0), "X");
        softAssert.assertEquals(board.getValueAt(new Vector2i(0, 1)), "*");
        softAssert.assertEquals(board.getValueAt(new Vector2i(1, 0)), "*");
        softAssert.assertEquals(board.getValueAt(new Vector2i(0, 0)), "X");
        softAssert.assertAll();
    }

    public void testIsStringWithBoardRepresentationCorrect() {
        ConsoleBoard board = new ConsoleBoard(new Vector2i(3, 3));
        softAssert.assertEquals(board.draw(), "* * * \n* * * \n* * * \n");
        board = new ConsoleBoard(new Vector2i(3, 4));
        softAssert.assertEquals(board.draw(), "* * * \n* * * \n* * * \n* * * \n");
        board = new ConsoleBoard(new Vector2i(4, 3));
        softAssert.assertEquals(board.draw(), "* * * * \n* * * * \n* * * * \n");
        softAssert.assertAll();
    }

}
