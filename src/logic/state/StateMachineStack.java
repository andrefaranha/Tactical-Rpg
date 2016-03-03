package logic.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachineStack {
	
	private Map<String, IState> mStates = new HashMap<String, IState>();
	private List<IState> mStack = new ArrayList<IState>();
	
	public void update(float elapsedTime) {
		IState top = mStack.get(0);
		top.update(elapsedTime);
	}
	
	public void render(Graphics2D g) {
		IState top = mStack.get(0);
		top.render(g);
	}
	
	public void push(String name) {
		IState state = mStates.get(name);
		mStack.add(0, state);
	}
	
	public IState pop() {
		return mStack.remove(0);
	}
	
	public void add(String name, IState state) {
		mStates.put(name, state);
	}
}
