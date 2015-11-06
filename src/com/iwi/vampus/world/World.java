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
		this.currentSenses = updateSenses(senses);
	

	}

	@Override
	public World turnRight() {
		int direction = currentSenses.getDirection();
		direction++;
		direction %= 4;
		currentSenses.setDirection(direction);

		return new World(map, updateSenses(currentSenses));
	}

	@Override
	public World turnLeft() {
		int direction = currentSenses.getDirection();
		direction--;
		if (direction == -1) {
			direction = 3;
		}

		currentSenses.setDirection(direction);

		return new World(map, updateSenses(currentSenses));
	}

	@Override
	public World forward() {
		Senses newSenses = new Senses();

		int direction = currentSenses.getDirection();
		switch (direction) {
		case Constants.NORTH:
			newSenses.setX(currentSenses.getX());
			newSenses.setY(currentSenses.getY() - 1);
			newSenses.setDirection(direction);
			break;
		case Constants.EAST:
			newSenses.setX(currentSenses.getX() + 1);
			newSenses.setY(currentSenses.getY());
			newSenses.setDirection(direction);
			break;
		case Constants.SOUTH:
			newSenses.setX(currentSenses.getX());
			newSenses.setY(currentSenses.getY() + 1);
			newSenses.setDirection(direction);
			break;
		case Constants.WEST:
			newSenses.setX(currentSenses.getX() - 1);
			newSenses.setY(currentSenses.getY());
			newSenses.setDirection(direction);
			break;
		}

		newSenses = detectBump(newSenses);
		return new World(map, updateSenses(newSenses));
	}

	private Senses detectBump(Senses newSenses) {
		if (newSenses.getX() < 0) {
			newSenses.setX(0);
			newSenses.setBump(true);

		}

		if (newSenses.getX() > 3) {
			newSenses.setX(3);
			newSenses.setBump(true);

		}

		if (newSenses.getY() < 0) {
			newSenses.setY(0);
			newSenses.setBump(true);

		}

		if (newSenses.getY() > 3) {
			newSenses.setY(3);
			newSenses.setBump(true);

		}

		return newSenses;
	}

	private Senses updateSenses(Senses newSenses) {

		newSenses = detectSmell(newSenses);
		newSenses = detectWind(newSenses);
		
		int x = newSenses.getX();
		int y = newSenses.getY();
		
		if(map[y][x]==Constants.GOLD){
			newSenses.setGlitter(true);
		}
		
		return newSenses;
	}

	private Senses detectWind(Senses newSenses) {
		int x = newSenses.getX();
		int y = newSenses.getY();

		int nx, ny;

		nx = x - 1;
		ny = y;

		if (nx >= 0) {
			if (map[ny][nx] == Constants.PIT) {
				newSenses.setBreezy(true);
			}
		}

		nx = x + 1;
		ny = y;

		if (nx <= 3) {
			if (map[ny][nx] == Constants.PIT) {
				newSenses.setBreezy(true);
			}
		}

		nx = x;
		ny = y - 1;

		if (ny >= 0) {
			if (map[ny][nx] == Constants.PIT) {
				newSenses.setBreezy(true);
			}
		}

		nx = x;
		ny = y + 1;

		if (ny <= 3) {
			if (map[ny][nx] == Constants.PIT) {
				newSenses.setBreezy(true);
			}
		}

		return newSenses;
	}

	private Senses detectSmell(Senses newSenses) {

		int x = newSenses.getX();
		int y = newSenses.getY();

		int nx, ny;

		nx = x - 1;
		ny = y;

		if (nx >= 0) {
			if (map[ny][nx] == Constants.VAMPUS) {
				newSenses.setSmelly(true);
			}
		}

		nx = x + 1;
		ny = y;

		if (nx <= 3) {
			if (map[ny][nx] == Constants.VAMPUS) {
				newSenses.setSmelly(true);
			}
		}

		nx = x;
		ny = y - 1;

		if (ny >= 0) {
			if (map[ny][nx] == Constants.VAMPUS) {
				newSenses.setSmelly(true);
			}
		}

		nx = x;
		ny = y + 1;

		if (ny <= 3) {
			if (map[ny][nx] == Constants.VAMPUS) {
				newSenses.setSmelly(true);
			}
		}

		return newSenses;
	}

	@Override
	public World grab() {
		// TODO Auto-generated method stub
		return null;
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
