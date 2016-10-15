package view.mediator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.board.BoardData;
import utils.CFacade;
import utils.GameConfig;

/**
 * A component of Control Panel: display save game button to save the current game
 * @author Phan Vo
 *
 */
public class SaveGamePanel extends JPanel implements Serializable {
	private JButton btnSaveGame;
	
	private BoardData boardData;
	
	public SaveGamePanel() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnSaveGame = new JButton("Save game");
		btnSaveGame.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-20, 40));
		btnSaveGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO call the end-turn method from the controller
				doSave();
			}
		});		
		this.add(btnSaveGame);
	}
	
	public JButton getSaveButton(){
		return btnSaveGame;
	}
	
	/**
	 * perform save feature
	 */
	private void doSave(){
		boardData = BoardData.getInstance();		
		if (CFacade.getInstance().saveGameData(boardData)) {
			DialogView.getInstance().showInformation("Save game successfully!");			
		};

	}
}
