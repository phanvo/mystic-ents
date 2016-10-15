package model.piece;

public enum Team {
	BLUE(93, 203, 240), RED(242, 116, 116), YELLOW(255, 230, 64), GREEN(124, 247, 52);

	private Team(final Integer red, final Integer green, final Integer blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	private final Integer red, green, blue;
	private Boolean AI = false;
	private int undoNum = 1;

	public Boolean getAI() {
		return AI;
	}

	public void setAI(Boolean aI) {
		AI = aI;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public int getUndoNum() {
		return this.undoNum;
	}

	public void decreaseUndoNum() {
		this.undoNum--;
	}

}