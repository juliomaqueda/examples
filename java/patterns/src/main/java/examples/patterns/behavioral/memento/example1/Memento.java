package examples.patterns.behavioral.memento.example1;

public class Memento {

	private String state;

	public Memento(String stateToSave) {
		state = stateToSave;
	}

	public String getSavedState() {
		return state;
	}
}
