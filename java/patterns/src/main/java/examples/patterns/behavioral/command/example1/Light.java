package examples.patterns.behavioral.command.example1;

//Receiver
public class Light {

	private boolean on;

	public void switchOn() {
		on = true;
	}

	public void switchOff() {
		on = false;
	}
}
