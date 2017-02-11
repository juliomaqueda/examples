package examples.patterns.behavioral.chainOfResponsibility.example1;

public class Test {

	public static void main(String[] args) {

		Handler chainRoot = new Handler();

		chainRoot.add(new Handler());
		chainRoot.add(new Handler());
		chainRoot.add(new Handler());

		chainRoot.wrapAround(chainRoot);

		for (int i = 1; i < 10; i++)
			chainRoot.handle(i);
	}
}
