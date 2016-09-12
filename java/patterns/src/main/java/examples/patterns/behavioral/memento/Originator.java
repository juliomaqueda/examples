package examples.patterns.behavioral.memento;

public class Originator {

	/* lots of memory consumptive private data that is not necessary to define the 
	 * state and should thus not be saved. Hence the small memento object. */
	private String state;

	public void set(String state) { 
		System.out.println("Originator: Setting state to " + state);
		this.state = state; 
	}

	public Memento saveToMemento() { 
		System.out.println("Originator: Saving to Memento.");
		return new Memento(state); 
	}

	public void restoreFromMemento(Memento m) {
		state = m.getSavedState(); 
		System.out.println("Originator: State after restoring from Memento: " + state);
	}
}
