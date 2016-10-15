package view.mediator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import utils.GameConfig;

/**
 * A component of ControlPanel: Log all player/AI actions
 * @author Mark, Phan Vo
 *
 */

public class MoveInfoPanel extends JPanel{
	
	private JTextArea tfInfo;
	private JScrollPane scrollbar = new JScrollPane();
	
	public MoveInfoPanel() {
		buildPanel();
	}
	
	private void buildPanel() {
		tfInfo = new JTextArea();

		scrollbar.setBorder(BorderFactory.createEmptyBorder());
		scrollbar.getViewport().setBackground(Color.WHITE);
		scrollbar.setViewportView(tfInfo);
		scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollbar.getVerticalScrollBar().setUnitIncrement(50);
		scrollbar.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-30, 100));
		
		tfInfo.setEditable(false);
		tfInfo.setForeground(Color.BLACK);
		tfInfo.setBackground(Color.WHITE);
		tfInfo.setFont(new Font("Sans-serif", Font.BOLD, 12));
		tfInfo.setLineWrap(true);
		tfInfo.setWrapStyleWord(true);

		JPanel pnContainer = new JPanel(new FlowLayout());
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		pnContainer.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-20, 130));
		pnContainer.add(scrollbar);
		
		DefaultCaret caret2 = (DefaultCaret)tfInfo.getCaret();
		caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
	    TitledBorder titled = new TitledBorder("Piece log");
	    pnContainer.setBorder(titled);

		this.add(pnContainer);

	}
	
	public void setMessage(String msg) {
		tfInfo.append(msg+"\n");
	}

}
