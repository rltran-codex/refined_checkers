package refined_checkers;

import org.junit.Test;

import refined_checkers.game_logic.Board;
import refined_checkers.game_logic.BoardBuilder;
import refined_checkers.interfaces.PieceSubscriber;
import refined_checkers.models.CheckerPiece;
import refined_checkers.util.GameType;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

public class BoardTest {
  private BoardBuilder bBuilder;

  @Before
  public void setUp() {
    bBuilder = new BoardBuilder();
  }

  @After
  public void tearDown() {
    bBuilder = null;
  }

  @Test
  public void testBoard() {
    try {
      Board b = bBuilder.setGameType(GameType.CHECKERS)
          .setBoard()
          .build();

      int expected_x = 12;
      int expected_o = 12;
      int count_x = 0;
      int count_o = 0;

      PieceSubscriber[][] actualBoard = b.getBoard();
      for (PieceSubscriber[] row : actualBoard) {
        for (PieceSubscriber p : row) {
          if (p != null) {
            switch (p.getName()) {
              case "x":
                count_x++;
                break;
              case "o":
                count_o++;
                break;
              default:
                throw new Exception("Unexpected Piece found on board");
            }
          }
        }
      }

      assertEquals("X count is incorrect.", expected_x, count_x);
      assertEquals("O count is incorrect.", expected_o, count_o);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  @Test
  public void testPiecePlacements() {
    try {
      Board b = bBuilder.setGameType(GameType.CHECKERS)
        .setBoard()
        .build();

      PieceSubscriber[][] checkerBoard = b.getBoard();
      for (int c = 0; c < checkerBoard.length; c++) {
        if (c % 2 == 0) {
          assertEquals("Expected O piece.", "o", checkerBoard[1][c].getName());
          assertEquals("Expected X piece.", "x", checkerBoard[5][c].getName());
          assertEquals("Expected X piece.", "x", checkerBoard[7][c].getName());
        } else {
          assertEquals("Expected O piece.", "o", checkerBoard[0][c].getName());
          assertEquals("Expected O piece.", "o", checkerBoard[2][c].getName());
          assertEquals("Expected X piece.", "x", checkerBoard[6][c].getName());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  @Test
  public void testPieceCalculations() {
    try {
      Board b = bBuilder.setGameType(GameType.CHECKERS)
        .setBoard()
        .build();

      CheckerPiece p = (CheckerPiece) b.getBoard()[2][7];
      ArrayList<Integer[]> t = p.getPossibleMoves();
      assertEquals(1, t.size());
      assertEquals("o", p.getName());
      assertArrayEquals(new Integer[] {3, 6}, t.get(0));
    } catch (Exception e) {
      // TODO: handle exception
      fail();
    }
  }

  @Test
  public void testJumpCalculations() {
    Board b = new Board();
    CheckerPiece p1 = new CheckerPiece("o", 3, 2);
    CheckerPiece p2 = new CheckerPiece("x", 4, 1);
    CheckerPiece p3 = new CheckerPiece("x", 4, 3);

    CheckerPiece[][] testBoard = new CheckerPiece[8][8];
    testBoard[3][2] = p1;
    testBoard[4][1] = p2;
    testBoard[4][3] = p3;

    b.subscribePiece(p1);
    b.subscribePiece(p2);
    b.subscribePiece(p3);
    b.updateBoard(testBoard);

    assertEquals(2, p1.getPossibleJumps().size());
    assertEquals(1, p2.getPossibleJumps().size());
    assertEquals(1, p3.getPossibleJumps().size());

    ArrayList<Integer[]> expected_x = new ArrayList<>(){{
      add(new Integer[] { 2, 3 });
    }};

    assertArrayEquals(new Integer[] { 5, 0 }, p1.getPossibleJumps().get(0));
    assertArrayEquals(new Integer[] { 5, 4 }, p1.getPossibleJumps().get(1));
  }
}
