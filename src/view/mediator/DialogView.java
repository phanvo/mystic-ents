package view.mediator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Create dialog view for displaying prompt in-game messages
 * @author Phan Vo
 *
 */
public class DialogView {
	private static DialogView instance;
	
	private DialogView(){}
	
	/**
	 * thread-safe for getting the only instance of the object
	 * reuse the DialogView to display in-game message
	 * @return
	 */
	public static synchronized DialogView getInstance() {
		if (instance == null) {
			instance = new DialogView();
		}
		return instance;
	}
	
	/**
	 * Display prompt message with a specific position
	 * @param info
	 * @param x
	 * @param y
	 */
	public void showInformation(String info, int x, int y) {
		final JOptionPane a = new JOptionPane(info, JOptionPane.INFORMATION_MESSAGE);
		final JDialog b = a.createDialog(null, "Information");
		b.setLocation(x, y);
		b.setVisible(true);
	}
	
	/**
	 * Display prompt message on default position (center)
	 * @param info
	 */
	public void showInformation(String info) {
		JOptionPane.showMessageDialog(null, info, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
