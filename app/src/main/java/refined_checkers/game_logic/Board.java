package refined_checkers.game_logic;

import java.util.ArrayList;
import java.util.Arrays;

import refined_checkers.interfaces.BoardPublisher;
import refined_checkers.interfaces.PieceSubscriber;
import refined_checkers.util.GameType;

/**
 * Board Class is a 8x8 checkered board
 * The board's layout is based on Algebraic notation such that the rows are
 * numbered from 1-8 and columns are a-h. <br>
 * Resource: https://en.wikipedia.org/wiki/Algebraic_notation_(chess) <br>
 *
 * @author Richard L. Tran
 * @version 2.0 Last Updated: 10/06/2023
 */
public class Board implements BoardPublisher {
  private static final int SIZE = 8;
  private PieceSubscriber[][] mainBoard;
  private boolean[][] validSquares;

  private ArrayList<PieceSubscriber> subscribers;

  /**
   * Constructor initializes a 8x8 matrix board
   * and sets all squares to be valid.
   */
  public Board() {
    this.mainBoard = new PieceSubscriber[SIZE][SIZE];
    for (PieceSubscriber[] squares : mainBoard) {
      Arrays.fill(squares, null);
    }
    this.validSquares = new boolean[SIZE][SIZE];
    subscribers = new ArrayList<>();
  }

  public boolean[][] initValidSpaces(GameType type) {
    boolean[][] board = new boolean[SIZE][SIZE];

    switch (type) {
      case CHECKERS:
        for (int r = 0; r < board.length; r++) {
          if (r % 2 == 0) {
            board[r][1] = true;
            board[r][3] = true;
            board[r][5] = true;
            board[r][7] = true;
          } else {
            board[r][0] = true;
            board[r][2] = true;
            board[r][4] = true;
            board[r][6] = true;
          }
        }
        break;

      default:
        for (boolean[] squares : board) {
          Arrays.fill(squares, true);
        }
        break;
    }

    return board;
  }

  public void toggleSquare(int row, int col) {
    try {
      this.validSquares[row][col] = !this.validSquares[row][col];
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
  }

  public PieceSubscriber[][] getBoard() {
    return this.mainBoard;
  }

  @Override
  public void updateBoard(PieceSubscriber[][] board) {
    this.subscribers = new ArrayList<>();
    for (int r = 0; r < SIZE; r++) {
      for (int c = 0; c < SIZE; c++) {
        PieceSubscriber p = board[r][c];
        this.mainBoard[r][c] = p;

        if (p != null) {
          this.subscribePiece(p);
        }
      }
    }

    notifyPieces();
  }

  @Override
  public void notifyPieces() {
    subscribers.forEach(p -> {
      PieceSubscriber[][] boardCopy = new PieceSubscriber[SIZE][SIZE];
      for (int r = 0; r < SIZE; r++) {
        for (int c = 0; c < SIZE; c++) {
          boardCopy[r][c] = this.mainBoard[r][c];
        }
      }

      p.update(boardCopy);
    });
  }

  @Override
  public void subscribePiece(PieceSubscriber p) {
    subscribers.add(p);
  }

  @Override
  public void unsubscribePiece(PieceSubscriber p) {
    subscribers.remove(p);
  }
}
