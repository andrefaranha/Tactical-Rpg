package tacticalrpg;

import java.awt.EventQueue;

import logic.game.Game;

public class TacticalRPG {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Game("Tactical RPG", 640, 640);
			}
		});
	}
}
