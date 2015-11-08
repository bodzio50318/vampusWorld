package com.iwi.vampus.world;

import java.util.Arrays;

import com.iwi.vampus.Constants;

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

		updateSenses();
		return new World(map, currentSenses);
	}

	public void startUp() {
		passiveSensesUpdate();
	}

	@Override
	public World turnLeft() {
		int direction = currentSenses.getDirection();
		direction--;
		if (direction == -1) {
			direction = 3;
		}

		currentSenses.setDirection(direction);
		updateSenses();
		return new World(map, currentSenses);
	}

	@Override
	public World forward() {

		int direction = currentSenses.getDirection();
		switch (direction) {
		case Constants.NORTH:
			currentSenses.setX(currentSenses.getX());
			currentSenses.setY(currentSenses.getY() - 1);
			currentSenses.setDirection(direction);
			break;
		case Constants.EAST:
			currentSenses.setX(currentSenses.getX() + 1);
			currentSenses.setY(currentSenses.getY());
			currentSenses.setDirection(direction);
			break;
		case Constants.SOUTH:
			currentSenses.setX(currentSenses.getX());
			currentSenses.setY(currentSenses.getY() + 1);
			currentSenses.setDirection(direction);
			break;
		case Constants.WEST:
			currentSenses.setX(currentSenses.getX() - 1);
			currentSenses.setY(currentSenses.getY());
			currentSenses.setDirection(direction);
			break;
		}

		detectBump();
		updateSenses();
		return new World(map, currentSenses);
	}

	private void detectBump() {
		if (currentSenses.getX() < 0) {
			currentSenses.setX(0);
			currentSenses.setBump(true);

		}

		if (currentSenses.getX() > 3) {
			currentSenses.setX(3);
			currentSenses.setBump(true);

		}

		if (currentSenses.getY() < 0) {
			currentSenses.setY(0);
			currentSenses.setBump(true);

		}

		if (currentSenses.getY() > 3) {
			currentSenses.setY(3);
			currentSenses.setBump(true);

		}

	}

	private Senses updateSenses() {

		passiveSensesUpdate();

		currentSenses.setPoints(currentSenses.getPoints()
				- Constants.ACTION_COST);

		checkIfEndOfGame();

		return currentSenses;
	}

	private void passiveSensesUpdate() {
		currentSenses = detectSmell(currentSenses);
		currentSenses = detectWind(currentSenses);

		int x = currentSenses.getX();
		int y = currentSenses.getY();

		if (map[y][x] == Constants.GOLD) {
			currentSenses.setGlitter(true);
		} else {
			currentSenses.setGlitter(false);
		}

	}

	private void checkIfEndOfGame() {
		int x = currentSenses.getX();
		int y = currentSenses.getY();

		if (map[y][x] == Constants.VAMPUS || map[y][x] == Constants.PIT) {
			currentSenses.setPoints(currentSenses.getPoints()
					- Constants.DEATH_COST);
			currentSenses.setAlive(false);
		}
		if (y == Constants.START_Y && x == Constants.START_X
				&& currentSenses.hasGold()) {
			currentSenses.setPoints(currentSenses.getPoints()
					- Constants.GOLD_RETURN_COST);
			currentSenses.setAlive(false);
		}

	}

	private Senses detectWind(Senses currentSenses) {
		int x = currentSenses.getX();
		int y = currentSenses.getY();

		int nx, ny;

		nx = x - 1;
		ny = y;

		if (nx >= 0) {
			if (map[ny][nx] == Constants.PIT) {
				currentSenses.setBreezy(true);
			}
		}

		nx = x + 1;
		ny = y;

		if (nx <= 3) {
			if (map[ny][nx] == Constants.PIT) {
				currentSenses.setBreezy(true);
			}
		}

		nx = x;
		ny = y - 1;

		if (ny >= 0) {
			if (map[ny][nx] == Constants.PIT) {
				currentSenses.setBreezy(true);
			}
		}

		nx = x;
		ny = y + 1;

		if (ny <= 3) {
			if (map[ny][nx] == Constants.PIT) {
				currentSenses.setBreezy(true);
			}
		}

		return currentSenses;
	}

	private Senses detectSmell(Senses currentSenses) {

		int x = currentSenses.getX();
		int y = currentSenses.getY();

		int nx, ny;

		nx = x - 1;
		ny = y;

		if (nx >= 0) {
			if (map[ny][nx] == Constants.VAMPUS) {
				currentSenses.setSmelly(true);
			}
		}

		nx = x + 1;
		ny = y;

		if (nx <= 3) {
			if (map[ny][nx] == Constants.VAMPUS) {
				currentSenses.setSmelly(true);
			}
		}

		nx = x;
		ny = y - 1;

		if (ny >= 0) {
			if (map[ny][nx] == Constants.VAMPUS) {
				currentSenses.setSmelly(true);
			}
		}

		nx = x;
		ny = y + 1;

		if (ny <= 3) {
			if (map[ny][nx] == Constants.VAMPUS) {
				currentSenses.setSmelly(true);
			}
		}

		return currentSenses;
	}

	@Override
	public World grab() {
		int x = currentSenses.getX();
		int y = currentSenses.getY();
		
		if (map[y][x] == Constants.GOLD){
			currentSenses.setHasGold(true);
			map[y][x] = Constants.FREE;
		}

		updateSenses();
		return new World(map, currentSenses);
	}

	@Override
	public String toString() {

		String out = "";
		for (int j = 0; j < Constants.DIM; j++) {
			for (int i = 0; i < Constants.DIM; i++) {
				if (j == currentSenses.getY() && i == currentSenses.getX()) {
					out = out + map[j][i] + "*| ";
				} else {
					out = out + map[j][i] + " | ";
				}

			}
			out += "\n";
		}
		switch (currentSenses.getDirection()) {
		case Constants.NORTH:
			out += "^ " + currentSenses.toString();
			break;
		case Constants.EAST:
			out += "> " + currentSenses.toString();
			break;
		case Constants.SOUTH:
			out += "\\/ " + currentSenses.toString();
			break;
		case Constants.WEST:
			out += "< " + currentSenses.toString();
			break;

		}

		return out;
	}

}
