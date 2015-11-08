package com.iwi.tests.world;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.iwi.vampus.Constants;
import com.iwi.vampus.world.Senses;
import com.iwi.vampus.world.World;

public class ShootingTests {

	private int map[][] = { { 0, 0, 0, 1 }, { 2, 3, 1, 0 }, { 0, 0, 0, 0 },
			{ 0, 0, 1, 0 } };

	@Test
	public void northTest() {
		Senses s = new Senses();
		s.setX(0);
		s.setY(3);
		s.setDirection(0);

		

		World w = new World(map, s);

		System.out.println(w);

		w = w.shoot();

		assertEquals(true, w.getCurrentSenses().isScream());
		assertEquals(map[1][0], Constants.FREE);

	}

}
