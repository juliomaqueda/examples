package examples.patterns.behavioral.command.example1;

public class Test {

	public static void main(String[] args) {
		RemoteControl control = new RemoteControl();

		Light light = new Light();

		Command lightsOn = new LightOnCommand(light);
		Command lightsOff = new LightOffCommand(light);

		//switch on
		control.setCommand(lightsOn);
		control.pressButton();

		//switch off
		control.setCommand(lightsOff);
		control.pressButton();
	}
}
