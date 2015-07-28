package de.fischer.xswingai.agents;

import java.util.concurrent.ThreadLocalRandom;

import xswing.ai.AICommunicator;

public class RandomAgent implements Runnable {

	private AICommunicator game;
	private boolean running;
	
	public RandomAgent(AICommunicator game) {
		this.game = game;
		this.running = true;
	}

	@Override
	public void run() {
		// let agent run during game is running
		while (running){
			// synchronizing on monitor AIComminucator
			synchronized (game) {
				try {
					// maybe timeout because notify at ending may be lost...(?)
					game.wait(2500);
				} catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				// awaken..
				if (game.isGameEnded()){
					running = false;
				}
				else{
					// Random agent is stupid: he randomly chooses a column to drop the ball at
					int column = ThreadLocalRandom.current().nextInt(0,8);		
					game.setDropAt(column);
				}
			}
			
			// waiting for the game to go on and let the render draw the next screen etc..
			try{
				Thread.sleep(500);
			}
			catch (InterruptedException ex){
				System.err.println("agent sleeping interrupted");
			}
		}
		
	}

}
