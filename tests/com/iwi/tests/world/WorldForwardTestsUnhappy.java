package com.iwi.tests.world;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.iwi.vampus.Constants;
import com.iwi.vampus.world.Senses;
import com.iwi.vampus.world.World;

@RunWith(Parameterized.class)
public class WorldForwardTestsUnhappy {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 3, 0, 0, 2, 0, false },
				{ 3, 0, 1, 3, 1, false }, { 3, 0, 2, 3, 0, true },
				{ 3, 0, 3, 3, 0, true }, { 0, 0, 0, 0, 0, true },
				{ 0, 3, 0, 0, 3, true }, { 0, 3, 1, 0, 3, true },
				{ 3, 3, 1, 3, 3, true }, { 3, 3, 2, 3, 3, true },
				{ 3, 3, 3, 3, 2, false } });
	}

	private int inputDirrection;

	private int expectedX;
	private int expectedY;
	private int startingX;
	private int startingY;
	private boolean bump;
	private int map[][] = { { 0, 0, 0, 1 }, { 2, 3, 1, 0 }, { 0, 0, 0, 0 },
			{ 0, 0, 1, 0 } };

	public WorldForwardTestsUnhappy(int startingY, int startingX, int input,
			int expectedY, int expectedX, boolean bump) {

		inputDirrection = input;
		this.expectedX = expectedX;
		this.expectedY = expectedY;
		this.startingX = startingX;
		this.startingY = startingY;
		this.bump = bump;
	}

	@Test
	public void test() {
		Senses s = new Senses();
		s.setDirection(inputDirrection);
		s.setX(startingX);
		s.setY(startingY);

		World w = new World(map, s);
		World newW = w.forward();
		assertEquals(inputDirrection, newW.getCurrentSenses().getDirection());
		assertEquals(bump, newW.getCurrentSenses().isBump());
		assertEquals(expectedX, newW.getCurrentSenses().getX());
		assertEquals(expectedY, newW.getCurrentSenses().getY());

	}
}
