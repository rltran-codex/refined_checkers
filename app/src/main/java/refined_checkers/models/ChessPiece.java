package refined_checkers.models;

import java.util.ArrayList;

import refined_checkers.game_logic.Board;
import refined_checkers.interfaces.PieceSubscriber;

public class ChessPiece implements PieceSubscriber {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getName'");
  }

  @Override
  public void calculateMoves() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateMoves'");
  }

  @Override
  public void calculateJumps() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateJumps'");
  }

  @Override
  public Board commitMove(Integer[] pos) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'commitMove'");
  }

  @Override
  public Integer[] getCoordinates() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCoordinates'");
  }

  @Override
  public void update(PieceSubscriber[][] board) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public ArrayList<Integer[]> getPossibleMoves() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPossibleMoves'");
  }

  @Override
  public ArrayList<Integer[]> getPossibleJumps() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPossibleJumps'");
  }
  
}
