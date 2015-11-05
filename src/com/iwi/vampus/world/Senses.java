package com.iwi.vampus.world;

public class Senses {
	private boolean smelly;
	private boolean breezy;
	private boolean glitter;
	private boolean bump;
	private boolean scream;

	private int direction;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isSmelly() {
		return smelly;
	}

	public void setSmelly(boolean smelly) {
		this.smelly = smelly;
	}

	public boolean isBreezy() {
		return breezy;
	}

	public void setBreezy(boolean breezy) {
		this.breezy = breezy;
	}

	public boolean isGlitter() {
		return glitter;
	}

	public void setGlitter(boolean glitter) {
		this.glitter = glitter;
	}

	public boolean isBump() {
		return bump;
	}

	public void setBump(boolean bump) {
		this.bump = bump;
	}

	public boolean isScream() {
		return scream;
	}

	public void setScream(boolean scream) {
		this.scream = scream;
	}

}
