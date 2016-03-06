package logic.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.input.KeyHandler;

public class StateMachineStack {
	
	private Map<String, State> mStates = new HashMap<String, State>();
	private List<State> mStack = new ArrayList<State>();
	
	private void onEnter() {
		State top = mStack.get(0);
		top.onEnter();
	}
	
	private void onExit() {
		State top = mStack.get(0);
		top.onExit();
	}
	
	public void processInput(KeyHandler keyHandler) {
		State top = mStack.get(0);
		top.processInput(keyHandler);
	}
	
	public void update(float elapsedTime) {
		State top = mStack.get(0);
		top.update(elapsedTime);
	}
	
	public void render(Graphics2D g) {
		State top = mStack.get(0);
		top.render(g);
	}
	
	public void push(String name) {
		State state = mStates.get(name);
		mStack.add(0, state);
		onEnter();
	}
	
	public State pop() {
		onExit();
		return mStack.remove(0);
	}
	
	public void add(String name, State state) {
		mStates.put(name, state);
	}
}
