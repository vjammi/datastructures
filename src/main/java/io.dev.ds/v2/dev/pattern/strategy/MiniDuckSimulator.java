package dev.vjammi.ds.v2.dev.pattern.strategy;

public class MiniDuckSimulator {
 
	public static void main(String[] args) {
 
		MallardDuck	mallard = new MallardDuck();

		FlyBehavior cantFly = () -> System.out.println("I can't fly");
		QuackBehavior squeak = () -> System.out.println("Squeak");
		Duck rubberDuckie = new RubberDuck(cantFly, squeak);

		Duck decoyDuck = new DecoyDuck();
		Duck modelDuck = new ModelDuck();


		mallard.performQuack();
		rubberDuckie.performQuack();
		decoyDuck.performQuack();
   
		modelDuck.performFly();
		modelDuck.setFlyBehavior(new FlyRocketPowered());
		modelDuck.performFly();
	}
}
