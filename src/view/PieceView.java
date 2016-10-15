package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.piece.Piece;
import model.piece.Team;
import utils.GameConfig;

/**
 * Representation of piece. Eg. Usurper, regularpiece
 * 
 * @author skh, ms
 *
 */
public class PieceView extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;

	// Represents team.
	private Color color;
	private Piece piece;
	private BufferedImage image;
//	private CFacade boardUtils;
	private int size;
//	private JLabel label;

	public PieceView(Piece pce) {
//		boardUtils = CFacade.getInstance();
		size = (int) ((GameConfig.getDefaultHeight() / GameConfig.getROW_COL()));
		this.piece = pce;
		
		//Get color details from Piece's Team class
		Team team = (Team) pce.getTeam();
		this.color = new Color(team.getRed(),team.getGreen(),team.getBlue());

		addPieceIcon();
	}

	private void addPieceIcon() {
    	String icon = piece.getIcon();
		try {                
			image = ImageIO.read(new File("./src/view/icons/"+icon));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("Piece icon not found");
		}		
	}

//	private String buildLabelString() {
//		StringBuilder str = new StringBuilder();
//		str.append(piece.getSkillSet().getCurrentSkill().getName().charAt(0));
//		return str.toString();
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(this.color);
		g2.fillRect(0, 0, size, size);

		int isize = 210;		
        Double scale = ((double) size) / isize;
        
//        AffineTransform oldXform = g2.getTransform(); 
        g2.scale(scale, scale); 
        g2.drawImage(image, 0, 0, null);   
	}


}
