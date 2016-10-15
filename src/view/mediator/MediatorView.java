package view.mediator;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import controller.UIMediator;

import utils.GameConfig;

/**
 * Display board game info: timer, team color in play, remaining pieces
 * 							selected piece, end turn mechanism
 * @author Phan Vo, Mark (Mediator pattern)
 *
 */
public class MediatorView extends JPanel {
	
	public MediatorView() {
		
		super();		
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(GameConfig.getControlsWidth(), GameConfig.getDefaultHeight()));
	    
		TimePanel pnTime = new TimePanel();	    
		TeamColorPanel pnTeamColor = new TeamColorPanel();
		PieceInfoPanel pnPieceInfo = new PieceInfoPanel();
		UndoPanel pnUndo = new UndoPanel();
		SaveGamePanel pnSaveGame = new SaveGamePanel();
		EndTurnPanel pnEndTurn = new EndTurnPanel();
		MoveInfoPanel pnMoveInfo = new MoveInfoPanel();
	    
	    this.add(pnTime);
	    this.add(pnTeamColor);
	    this.add(pnPieceInfo);	    
	    this.add(pnMoveInfo);
	    this.add(pnUndo);	    
	    this.add(pnSaveGame);	    
	    this.add(pnEndTurn);	    
	    
	    UIMediator.getInstance().registerColleagues(
	    		pnTime, 
	    		pnTeamColor,
	    		pnPieceInfo, 
	    		pnUndo, 
	    		pnSaveGame,
	    		pnEndTurn,
	    		pnMoveInfo
	    );	    
	    
	}
	
}
