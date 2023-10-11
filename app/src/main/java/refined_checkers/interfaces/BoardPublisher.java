
package refined_checkers.interfaces;

/**
 * BoardPublisher Interface defines the methods
 * for a Observer behavioral design pattern.
 * 
 * References:
 * https://refactoring.guru/design-patterns/observer
 */
public interface BoardPublisher {

  public void updateBoard(PieceSubscriber[][] board);
  
  /**
   * notifyPieces method issues events of interest to other
   * objects and occur when a piece is moved on the board.
   */
  public void notifyPieces();

  /**
   * Method allows a piece to subscribe to BoardPublisher
   * @param p
   */
  public void subscribePiece(PieceSubscriber p);

  /**
   * Method allows a piece to unsubscribe to BoardPublisher
   * @param p
   */
  public void unsubscribePiece(PieceSubscriber p);
}