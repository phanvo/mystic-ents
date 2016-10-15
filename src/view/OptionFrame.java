package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.CFacade;
import utils.GameConfig;

/**
 * Option component within MainMenuFrame
 * @author Phan Vo
 *
 */

public class OptionFrame extends JFrame{
	
	public OptionFrame() {
		super();
		buildFrame();
		buildUI();		
	}
	
	/**
	 * Adding and configuring properties of the frame
	 */
	private void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// adjust size using Dimension.
		this.getContentPane().setPreferredSize(new Dimension(300, 100));

		this.setResizable(false);
		this.setVisible(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, 
				dim.height/2-this.getSize().height);
	}

	/**
	 * Creating the option UI
	 */
	private void buildUI() {
		JPanel optionPanel = new JPanel(new GridLayout(0, 2));
		int margin = 10;
		optionPanel.setBorder(new EmptyBorder(margin, margin, margin, margin));
		
		JLabel lbSelect = new JLabel("Select map: ");
		final JComboBox<String> cbMapList = new JComboBox<String>();
		
		for (String item : CFacade.getInstance().getAllGameMaps()) {
			cbMapList.addItem(item);
		}
		
		// set the selected map (or default map)
		cbMapList.setSelectedItem(GameConfig.getFileMapName());
		
		JButton btOK = new JButton("OK");
		btOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// set the game map by selection
				GameConfig.setFileMapName(cbMapList.getSelectedItem().toString());
				System.out.println(cbMapList.getSelectedItem() + " loaded...");
				doCloseFrame();
			}
		});
		
		JButton btCancel = new JButton("Cancel");
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doCloseFrame();
			}
		});
		
		optionPanel.add(lbSelect);
		optionPanel.add(cbMapList);
		optionPanel.add(btOK);
		optionPanel.add(btCancel);
		
		this.add(optionPanel);
		this.pack();
	}
	
	private void doCloseFrame(){
		this.dispose();
	}
}
