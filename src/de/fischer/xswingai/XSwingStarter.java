package de.fischer.xswingai;

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
			while (counter < 5){
				synchronized (game) {
					try {
						game.wait();
					} catch (InterruptedException e) {
			            e.printStackTrace();
			        }
				}
				game.setDropAt(counter);
				counter++;
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
		TestAgent agent = new TestAgent(comm);
		Thread agentThread = new Thread(agent);
		// starting the game in ai mode
		Thread gameThread = new Thread(new XSwingAI(comm));
		gameThread.start();
		agentThread.start();
	}

}
