package view.mediator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.piece.Piece;
import utils.GameConfig;

/**
 * A component of ControlPanel: Display essential info for the selected piece
 * @author Phan Vo, Mark
 * 
 */
public class PieceInfoPanel extends JPanel{

	private JTextField[] tArray = new JTextField[4];
	
	public PieceInfoPanel() {
		// TODO Auto-generated constructor stub
		super();		
		
		JPanel pnContainer = new JPanel(new GridLayout(4, 1));
		pnContainer.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-20, GameConfig.getControlsWidth()-100));
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		TitledBorder titled = new TitledBorder("Selected piece");
		pnContainer.setBorder(titled);
			
		for(int i=0; i< tArray.length; i++) {			
			tArray[i] = new JTextField();
			tArray[i].setHorizontalAlignment(JTextField.CENTER);		
			tArray[i].setPreferredSize(new Dimension(GameConfig.getControlsWidth()-60, 15));
			tArray[i].setEditable(false);
			tArray[i].setBackground(Color.WHITE);
			tArray[i].setForeground(Color.WHITE);
			tArray[i].setFont(new Font("Sans-serif", Font.BOLD, 16));	
			pnContainer.add(tArray[i]);
		}
		
		tArray[3].setForeground(Color.BLACK);
	    this.add(pnContainer);
	    
	}
	
	
	/**
	 * Reset piece info value by default if no piece is selected/ change player turn
	 */
	public void resetPieceInformation() {	
		for(int i=0; i< tArray.length; i++) {
			tArray[i].setText("-");
			tArray[i].setBackground(Color.WHITE);
		}
	}
	
	/**
	 * Retrieve piece data from the Piece model
	 * @param pce
	 */
	public void updatePieceInformation(Piece pce) {
		
		String[] gtl = new String[3];		
		gtl[0] = "Health:  ";
		gtl[1] = "Attack:  ";
		gtl[2] = "Move:  ";
		
		int[] gtv = new int[3];		
		gtv[0] = pce.getTraitSet().getHealthTrait().getTraitValue();
		gtv[1] = pce.getTraitSet().getDamageTrait().getTraitValue();
		gtv[2] = pce.getTraitSet().getRangeTrait().getTraitValue();
		
		for(int i=0; i< gtl.length; i++) {			
			tArray[i].setText(gtl[i] + String.valueOf(gtv[i]));
			tArray[i].setBackground(setValueColor(gtv[i]));			
		}
		
		tArray[3].setText(pce.getSkillSet().getCurrentSkill().getName());
		
	}
	
	/**
	 * Set color of Value based on 'strength'
	 * @param i
	 */
	public Color setValueColor(int i) {
		
		if (i >= 4) {return Color.GREEN; }
		if (i >= 2 ) {return Color.ORANGE; }
		if (i < 2 ) {return Color.RED; }		
		return Color.BLACK;
		
	}	
	
}
