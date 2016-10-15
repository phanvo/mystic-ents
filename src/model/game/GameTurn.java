package model.game;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import utils.GameConfig;

import java.awt.Toolkit;

public class GameTurn extends Observable {
	
	private Timer gameTimer;    
    private Boolean isRunning;
    private int keepTime;

	private int count = 1;
	
	private int time;

    public GameTurn() {
    	//toolkit = Toolkit.getDefaultToolkit();
    }
    
    public void buildTimer() {
    	isRunning = false;
    	time = GameConfig.getMaxSeconds();
    	setGameTime(GameConfig.getMaxSeconds());
    	//keepTime = GameConfig.getMaxSeconds();
    }
    
    public void stop() {
    	gameTimer.cancel();
    }    
    
    public void startTimer() {
    	isRunning = true;
    	gameTimer = new Timer();
    	gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				setChanged();
			    notifyObservers();
			    if (time-- == 0)
				{
					setGameTime(GameConfig.getMaxSeconds());
				}
			}
    	}, GameConfig.getStartDelay() , GameConfig.getTimerPeriod());
    }

    public void reset() {
    	this.time = GameConfig.getMaxSeconds();
    }    

    public int getGameTime() {
    	return time;
    }
    
    public void setGameTime(int time) {
    	this.time = time;
    }
    public int getKeepTime() {
    	return keepTime;
    }
    
    public void setKeepTime(int keepTime) {
    	this.keepTime = keepTime;
    }
    
    public int getCount() {
		return count;
	}
    
    public void setCount(int c) {
		count = c;
	} 
    
    public Boolean getIsRunning() {
    	return isRunning;
    }
    
    public void setIsRunning(Boolean isRunning) {
    	this.isRunning = isRunning;
    }
    
}