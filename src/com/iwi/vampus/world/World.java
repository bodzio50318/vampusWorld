package com.iwi.vampus.world;

public class World implements Accuators {

	private int map[][];

	private Senses currentSenses;

	public Senses getCurrentSenses() {
		return currentSenses;
	}

	public World(int map[][], Senses senses) {
		this.map = map;
		this.currentSenses = senses;

	}

	@Override
	public World turnRight() {
		int direction = currentSenses.getDirection();
		direction++;
		direction %= 4;
		currentSenses.setDirection(direction);

		return new World(map, currentSenses);
	}

	@Override
	public World turnLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World forward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World grab() {
		// TODO Auto-generated method stub
		return null;
	}

}
