package logic.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {

//	private static KeyHandler instance;

	public final static int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3, ACTION = 4, CANCEL = 5, EXIT = 6, NUM_KEYS = 7;

	private boolean[] keystate = new boolean[NUM_KEYS], prevKeystate = new boolean[NUM_KEYS];

//	private KeyHandler() {
//	}

//	public static KeyHandler getInstance() {
//		if (instance == null)
//			instance = new KeyHandler();
//		return instance;
//	}

	private void setKey(int i, boolean b) {
		if (i == KeyEvent.VK_UP)
			keystate[UP] = b;
		else if (i == KeyEvent.VK_LEFT)
			keystate[LEFT] = b;
		else if (i == KeyEvent.VK_DOWN)
			keystate[DOWN] = b;
		else if (i == KeyEvent.VK_RIGHT)
			keystate[RIGHT] = b;
		else if (i == KeyEvent.VK_SPACE || i == KeyEvent.VK_ENTER)
			keystate[ACTION] = b;
		else if (i == KeyEvent.VK_ESCAPE)
			keystate[CANCEL] = b;
		else if (i == KeyEvent.VK_BACK_SPACE)
			keystate[EXIT] = b;
	}

	private String mapKeyCodeToString(int keycode) {
		Map<Integer, String> keyCodeMap = new HashMap<Integer, String>();
		keyCodeMap.put(UP, "UP");
		keyCodeMap.put(LEFT, "LEFT");
		keyCodeMap.put(DOWN, "DOWN");
		keyCodeMap.put(RIGHT, "RIGHT");
		keyCodeMap.put(ACTION, "ACTION");
		keyCodeMap.put(CANCEL, "CANCEL");
		keyCodeMap.put(EXIT, "EXIT");

		return keyCodeMap.get(keycode);
	}

	private void printKeystates() {
		String keystateRepresentation = "{";
		for (int i = 0; i < keystate.length; i++) {
			keystateRepresentation += mapKeyCodeToString(i) + ": " + keystate[i] + ", ";
		}
		keystateRepresentation.substring(0, keystateRepresentation.length() - 2);
		keystateRepresentation += "}";
		System.out.println(keystateRepresentation);
	}

	public void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeystate[i] = keystate[i];
		}
		
	}

	public boolean isPressed(int i) {
		return keystate[i] && !prevKeystate[i];
	}

	public boolean isDown(int i) {
		return keystate[i];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setKey(e.getKeyCode(), true);
//		printKeystates();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		setKey(e.getKeyCode(), false);
//		printKeystates();
	}
}
