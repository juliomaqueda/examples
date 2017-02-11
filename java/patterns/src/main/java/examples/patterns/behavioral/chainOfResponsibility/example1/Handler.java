package examples.patterns.behavioral.chainOfResponsibility.example1;

public class Handler {

	private static java.util.Random random = new java.util.Random();

	private static int nextId = 1;
	private int id = nextId++;

	private Handler nextHandler;

	public void add(Handler next) {
		if (nextHandler == null)
			nextHandler = next;
		else
			nextHandler.add(next);
	}

	public void wrapAround(Handler root) {
		if (nextHandler == null)
			nextHandler = root;
		else
			nextHandler.wrapAround(root);
	}

	public void handle(int num) {
		if (random.nextInt(4) != 0) {
			System.out.print(id + "-busy  ");
			nextHandler.handle(num);
		}
		else
			System.out.println(id + "-handled-" + num);
	}
}
