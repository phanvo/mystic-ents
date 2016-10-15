package model.piece;
import java.io.Serializable;
/**
 * Abstract class forms part of Piece Decorator pattern
 * Declare and implements constructor for taking in decorated piece and aggregating it
 * @author Daniel
 *
 */
public abstract class PieceDecorator implements Piece, Serializable{
	
	protected Piece decoratedPiece;
	
	public PieceDecorator (Piece decoratedPiece){
		this.decoratedPiece = decoratedPiece;
	}
	
}
