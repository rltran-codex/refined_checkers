package refined_checkers.interfaces;

import java.util.ArrayList;

import refined_checkers.game_logic.Board;

/**
 * PieceSubscriber Interface defines the methods
 * for a Observer behavioral design pattern.
 * 
 * References:
 * https://refactoring.guru/design-patterns/observer
 */
public interface PieceSubscriber {
  
  /**
   * calculateMoves method checks the available moves current piece
   * can make. Updates possible_moves attribute.
   */
  public void calculateMoves();
  
  /**
   * calculateJumps method checks if the current piece can take
   * another piece by jumping it.
   * @param board
   */
  public void calculateJumps();
  
  /**
   * commitMove method updates piece's current position.
   * 
   * @param pos - position on the board of choice.
   * @return board - new updated board
   */
  public Board commitMove(Integer[] pos);
  
  /**
   * Method returns the current piece's coordinates on the board.
   * 
   * @return Array of 2 Integers [row, col]
   */
  public Integer[] getCoordinates();
  
  /**
   * Update method accepts a Board object as contextual information
   * to handle update correctly.
   * 
   * @param board
   */
  public void update(PieceSubscriber[][] board);
  
  public String getName();
  public ArrayList<Integer[]> getPossibleMoves();
  public ArrayList<Integer[]> getPossibleJumps();
}
