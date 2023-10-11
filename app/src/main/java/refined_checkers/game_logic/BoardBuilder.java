package refined_checkers.game_logic;

import refined_checkers.models.CheckerPiece;
import refined_checkers.util.GameType;

public class BoardBuilder {
  private Board game;
  private GameType type;

  public BoardBuilder() {
    reset();
  }

  public BoardBuilder(GameType type) {
    this.game = new Board();
    this.type = type;
  }

  public BoardBuilder reset() {
    this.game = new Board();
    this.type = null;
    return this;
  }

  public BoardBuilder setBoard() throws Exception {
    CheckerPiece[][] checkerBoard = new CheckerPiece[8][8];
    game.initValidSpaces(type);

    if (type == GameType.CHECKERS) {
      // Put all checker pieces in proper initial positions
      for (int c = 0; c < checkerBoard.length; c++) {
        if (c % 2 == 0) {
          CheckerPiece p1 = new CheckerPiece("o", 1, c);
          CheckerPiece p2 = new CheckerPiece("x", 5, c);
          CheckerPiece p3 = new CheckerPiece("x", 7, c);

          checkerBoard[1][c] = p1;
          checkerBoard[5][c] = p2;
          checkerBoard[7][c] = p3;
        } else {
          CheckerPiece p1 = new CheckerPiece("o", 0, c);
          CheckerPiece p2 = new CheckerPiece("o", 2, c);
          CheckerPiece p3 = new CheckerPiece("x", 6, c);

          checkerBoard[0][c] = p1;
          checkerBoard[2][c] = p2;
          checkerBoard[6][c] = p3;
        }
      }
      game.updateBoard(checkerBoard);
    } else if (type == GameType.CHESS) {
      throw new UnsupportedOperationException("Unimplemented system mode 'CHESS'");
    } else {
      throw new Exception("Game type not set");
    }

    return this;
  }

  public BoardBuilder setGameType(GameType type) {
    this.type = type;
    return this;
  }

  public Board build() {
    return game;
  }
}
