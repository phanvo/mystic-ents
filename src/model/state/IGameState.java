package model.state;

import controller.ActionController;
import model.board.Square;

public interface IGameState {

   public abstract void startAction(ActionController a, Square s);
   
   public abstract void endAction(ActionController a, Square s);
   
   public abstract void updateAction(ActionController a);
	
}
