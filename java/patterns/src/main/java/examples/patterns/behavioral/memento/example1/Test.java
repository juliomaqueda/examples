package examples.patterns.behavioral.memento.example1;

/**
 * Memento
 * 
 * Intent:
 * 
 * - Without violating encapsulation, capture and externalize an object's internal state so that the object can be returned to this state later.
 * - A magic cookie that encapsulates a "check point" capability.
 * - Promote undo or rollback to full object status.
 * 
 * 
 * Discussion:
 * 
 * The client requests a Memento from the source object when it needs to checkpoint the source object's state. The source object initializes the Memento
 * with a characterization of its state. The client is the "care-taker" of the Memento, but only the source object can store and retrieve information
 * from the Memento (the Memento is "opaque" to the client and all other objects). If the client subsequently needs to "rollback" the source object's state,
 * it hands the Memento back to the source object for reinstatement.
 * 
 * An unlimited "undo" and "redo" capability can be readily implemented with a stack of Command objects and a stack of Memento objects.
 * 
 * The Memento design pattern defines three distinct roles:
 * 
 * - Originator - the object that knows how to save itself.
 * - Caretaker - the object that knows why and when the Originator needs to save and restore itself.
 * - Memento - the lock box that is written and read by the Originator, and shepherded by the Caretaker.
 */
public class Test {

	public static void main(String[] args) {
		Caretaker caretaker = new Caretaker();

		Originator originator = new Originator();

		originator.set("State1");
		originator.set("State2");

		caretaker.addMemento(originator.saveToMemento());

		originator.set("State3");

		caretaker.addMemento(originator.saveToMemento());

		originator.set("State4");

		originator.restoreFromMemento(caretaker.getMemento(1));
	}
}
