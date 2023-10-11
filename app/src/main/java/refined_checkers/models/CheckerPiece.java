package refined_checkers.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

import refined_checkers.game_logic.Board;
import refined_checkers.interfaces.PieceSubscriber;

public class CheckerPiece implements PieceSubscriber, Serializable {
  private String name;
  private int row;
  private int col;
  private ArrayList<Integer[]> next_moves;
  private ArrayList<Integer[]> next_jumps;
  private boolean isKing;

  private PieceSubscriber[][] ref_board;

  public CheckerPiece() {
    this(null, -1, -1);
  }

  public CheckerPiece(String name, int row, int col) {
    this.name = name;
    this.row = row;
    this.col = col;
    this.next_moves = new ArrayList<>();
    this.next_jumps = new ArrayList<>();
    this.ref_board = null;
    this.isKing = false;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void calculateMoves() {
    if (ref_board == null) {
      throw new NullPointerException("Reference to board is missing...");
    }

    analyzingBoard(1, MoveType.SIMPLE);

    if (isKing) {
      // todo
    }
  }

  @Override
  public void calculateJumps() {
    // TODO Auto-generated method stub
    if (ref_board == null) {
      throw new NullPointerException("Reference to board is missing...");
    }

    analyzingBoard(2, MoveType.JUMP);

    if (isKing) {

    }
  }

  @Override
  public Board commitMove(Integer[] pos) {
    return null;
  }

  @Override
  public Integer[] getCoordinates() {
    return new Integer[] { this.row, this.col };
  }

  @Override
  public void update(PieceSubscriber[][] board) {
    this.ref_board = board;
    // reset jumps for next calculation
    this.next_moves = new ArrayList<>();
    this.next_jumps = new ArrayList<>();

    // calculate moves
    calculateMoves();
    // calculate jumps
    calculateJumps();
  }

  public ArrayList<Integer[]> getPossibleMoves() {
    return this.next_moves;
  }

  public ArrayList<Integer[]> getPossibleJumps() {
    return this.next_jumps;
  }

  private boolean validateMove(Integer[] initPos, Integer[] destPos) {
    if (initPos == null || destPos == null) {
      return false;
    }

    final int SIZE = 8;

    int rInit = initPos[0];
    int cInit = initPos[1];

    int rDest = destPos[0];
    int cDest = destPos[1];

    // If the value of the row or column is not in the range of the array
    // then retrun false
    if (rInit >= SIZE || rDest >= SIZE || rInit < 0 || rDest < 0) {
      return false;
    }
    if (cInit >= SIZE || cDest >= SIZE || cInit < 0 || cDest < 0) {
      return false;
    }

    // If move distance is 1 and space is available, then return true.
    if (Math.abs(cInit - cDest) == 1 && ref_board[rDest][cDest] == null) {
      return true;
    }

    // If move distance is 2, destination space is available, and space in 
    // between is occupied by opponent, then retrun true
    if (Math.abs(cInit - cDest) == 2 && ref_board[rDest][cDest] == null) {
      CheckerPiece p = (CheckerPiece) ref_board[(rInit + rDest) / 2][(cInit + cDest) / 2];
      if (p == null) {
        return false;
      }

      if (this.name.equals("o") && p.name.equals("x")) {
        return true;
      } else if (this.name.equals("x") && p.name.equals("o")) {
        return true;
      }
    }

    return false;
  }

  private void analyzingBoard(int n, MoveType type) {
    int x_row = -1;
    int x_col_left = col - n;
    int x_col_rght = col + n;

    if (this.name.equals("x")) {
      x_row = row - n;
    } else if (this.name.equals("o")) {
      x_row = row + n;
    } else {
      throw new NullPointerException("Checker piece is undetermined");
    }

    Integer[] curr_pos = getCoordinates();
    Integer[] move_fwd_left = new Integer[] { x_row, x_col_left };
    Integer[] move_fwd_rght = new Integer[] { x_row, x_col_rght };

    // If moving diagonal left is valid, add to available moves
    if (validateMove(curr_pos, move_fwd_left)) {
      if (type == MoveType.SIMPLE) {
        next_moves.add(move_fwd_left);
      } else if (type == MoveType.JUMP) {
        next_jumps.add(move_fwd_left);
      }
    }

    if (validateMove(curr_pos, move_fwd_rght)) {
      if (type == MoveType.SIMPLE) {
        next_moves.add(move_fwd_rght);
      } else if (type == MoveType.JUMP) {
        next_jumps.add(move_fwd_rght);
      }
    }
  }

  private enum MoveType {
    JUMP,
    SIMPLE
  }
}
