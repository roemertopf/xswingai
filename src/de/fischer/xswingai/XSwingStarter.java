package de.fischer.xswingai;

import xswing.ai.AIListener;
import xswing.ai.AISignal;
import xswing.ai.AgentInterface;
import xswing.start.*;

/**
 * This class starts XSwing and the AI that plays the game.
 * @author TobiasSebastian
 *
 */
public class XSwingStarter{
		
	private static class TestAgent implements AIListener, Runnable{
		
		private AgentInterface game;
		private AISignal signal;

		@Override
		public void gameStarted(AgentInterface game) {
			this.game = game;
			synchronized (signal) {
				if ()
			}
			game.dropBall(0);
			try{
				Thread.sleep(750);
			}
			catch (InterruptedException e){
				System.err.println("Interrupted during dropBall waiting");
			}
			game.dropBall(2);
			try{
				Thread.sleep(750);
			}
			catch (InterruptedException e){
				System.err.println("Interrupted during dropBall waiting");
			}
			game.dropBall(2);
//			game.dropBall(2);
//			game.dropBall(1);
		}

		@Override
		public void run() {
			synchronized (signal) {
				try {
					wait();
				} catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			}

		}
		
	}
	
	public static void main(String[] args) {
		// setting up the test agent
		TestAgent agent = new TestAgent();
		Thread agentThread = new Thread(agent);
		// starting the game in ai mode
		Thread gameThread = new Thread(new XSwingAI(agent));
		gameThread.start();
		agentThread.start();
	}

}
