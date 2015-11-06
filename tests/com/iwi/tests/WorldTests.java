package com.iwi.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.iwi.tests.world.WorldForwardTestsHappyScenarios;
import com.iwi.tests.world.WorldForwardTestsUnhappy;
import com.iwi.tests.world.WorldTestsRightTurn;
import com.iwi.tests.world.WorldTurnLeftTests;

@RunWith(Suite.class)
@SuiteClasses({ WorldForwardTestsHappyScenarios.class,
		WorldTestsRightTurn.class, WorldTurnLeftTests.class,
		WorldForwardTestsUnhappy.class })
public class WorldTests {

}
