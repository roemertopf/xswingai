package de.fischer.xswingai;

import java.util.Random;

import de.fischer.xswingai.agents.RandomAgent;
import xswing.ai.AICommunicator;
import xswing.start.XSwingAI;

/**
 * This class starts XSwing and the AI that plays the game.
 * @author TobiasSebastian
 *
 */
public class XSwingStarter{
		
	private static class TestAgent implements Runnable{
		
		private AICommunicator game;
		private int counter;
		
		public TestAgent(AICommunicator game) {
			this.game = game;
		}

		@Override
		public void run() {
			while (counter < 10){
				synchronized (game) {
					try {
						game.wait();
					} catch (InterruptedException e) {
			            e.printStackTrace();
			        }
					// waiting is over
					if (counter % 2 == 0){
						game.setDropAt(counter-1);
					}
					else{
						game.setDropAt(counter);
					}
					counter++;
				}
				// sleeping so that the game can progress...
				try{
					Thread.sleep(250);
				}
				catch (InterruptedException ex){
					System.err.println("agent sleeping interrupted");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		AICommunicator comm = new AICommunicator();
		// setting up the test agent
		RandomAgent agent = new RandomAgent(comm);
//		TestAgent agent = new TestAgent(comm);
		Thread agentThread = new Thread(agent);
		// starting the game in ai mode
		Thread gameThread = new Thread(new XSwingAI(comm));
		gameThread.start();
		agentThread.start();
	}

}
