package view.mediator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.UIMediator;
import utils.GameConfig;

/**
 * A component of ControlPanel: Display the current team color and available pieces
 * @author Phan Vo
 *
 */
public class TeamColorPanel extends JPanel{
	
	private JTextField tfColor;
	private JCheckBox AIButton;

	public TeamColorPanel() {
		// TODO Auto-generated constructor stub
		super();
		
		tfColor = new JTextField();
		tfColor.setHorizontalAlignment(JTextField.CENTER);		
		tfColor.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-80, 70));
		tfColor.setEditable(false);
		tfColor.setForeground(Color.WHITE);
		tfColor.setFont(new Font("Sans-serif", Font.BOLD, 60));
		
		JPanel pnContainer = new JPanel(new FlowLayout());
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		pnContainer.add(tfColor);
		
		AIButton = new JCheckBox("AI");
		AIButton.addActionListener(new AIListener());
		pnContainer.add(AIButton);
		
	    TitledBorder titled = new TitledBorder("Team");
	    pnContainer.setBorder(titled);

		this.add(pnContainer);

	}
	
	public void setTeamColor(Color c) {
		tfColor.setBackground(c);
	}
	
	public void setAvailablePieces(int num) {
		tfColor.setText(String.valueOf(num));
	}

	public void setAIButtonToggle(Boolean b) {
		AIButton.setSelected(b);
	}


	// ***************************************************************
	// The listener for the CheckBoxes
	// ***************************************************************
	public class AIListener implements ActionListener
	{		
		public AIListener () {}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			UIMediator.getInstance().setCurrentTeamAI();
		}
	}
	
	
}
