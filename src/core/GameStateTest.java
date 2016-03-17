package core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameStateTest {

	GameState gs;

	@Before
	public void setUp() throws Exception {
		gs = new GameState();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayHole() {
		int[] deck = new int[] { 0, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4 };
		gs.playHole(0);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 0);
		assertEquals(gs.getPlayer2Score(), 0);
		assertEquals(gs.getLastMove(), 0);
		assertEquals(gs.getPlayerTurn(), 2);

		deck = new int[] { 1, 6, 6, 5, 5, 4, 4, 4, 4, 4, 4, 0 };
		gs.playHole(11);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 0);
		assertEquals(gs.getPlayer2Score(), 1);
		assertEquals(gs.getLastMove(), 11);
		assertEquals(gs.getPlayerTurn(), 1);

		deck = new int[] { 1, 6, 6, 5, 5, 0, 5, 5, 5, 4, 4, 0 };
		gs.playHole(5);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 1);
		assertEquals(gs.getPlayer2Score(), 1);
		assertEquals(gs.getLastMove(), 5);
		assertEquals(gs.getPlayerTurn(), 2);

		deck = new int[] { 1, 6, 6, 5, 5, 0, 5, 0, 6, 5, 5, 1 };
		gs.playHole(7);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 1);
		assertEquals(gs.getPlayer2Score(), 2);
		assertEquals(gs.getLastMove(), 7);
		assertEquals(gs.getPlayerTurn(), 2);

	}

	
	@Test
	public void testPlayHole4() {
		int[] deck = new int[] { 4, 0, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4 };
		gs.playHole(1);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 0);
		assertEquals(gs.getPlayer2Score(), 0);
		assertEquals(gs.getLastMove(), 1);
		assertEquals(gs.getPlayerTurn(), 2);

		deck = new int[] { 4, 0, 5, 5, 5, 5, 4, 4, 0, 5, 5, 5 };
		gs.playHole(8);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 0);
		assertEquals(gs.getPlayer2Score(), 1);
		assertEquals(gs.getLastMove(), 8);
		assertEquals(gs.getPlayerTurn(), 2);

		deck = new int[] { 5, 1, 5, 5, 5, 5, 4, 4, 0, 0, 6, 6 };
		gs.playHole(9);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 0);
		assertEquals(gs.getPlayer2Score(), 2);
		assertEquals(gs.getLastMove(), 9);
		assertEquals(gs.getPlayerTurn(), 1);
	}
	
	@Test
	public void testPlayHole2() {
		int[] deck = new int[] { 9, 5, 0, 1, 2, 1, 3, 4, 2, 1, 1, 0 };
		gs.setDeck(deck);
		gs.setPlayerTurn(2);
		gs.setPlayer1Score(0);
		gs.setPlayer2Score(0);
		gs.setFreeTurn(false);

		deck = new int[] { 0, 5, 0, 1, 2, 1, 3, 4, 2, 1, 0, 0 };
		gs.playHole(10);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 0);
		assertEquals(gs.getPlayer2Score(), 10);
		assertEquals(gs.getPlayerTurn(), 1);
	}

	@Test
	public void testPlayHole3() {
		int[] deck = new int[] { 9, 2, 0, 0, 2, 1, 3, 4, 2, 1, 1, 0 };
		gs.setDeck(deck);
		gs.setPlayerTurn(1);
		gs.setPlayer1Score(0);
		gs.setPlayer2Score(0);
		gs.setFreeTurn(false);

		deck = new int[] { 9, 0, 1, 0, 2, 1, 3, 4, 0, 1, 1, 0 };
		gs.playHole(1);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 3);
		assertEquals(gs.getPlayer2Score(), 0);
		assertEquals(gs.getPlayerTurn(), 2);
	}

	@Test
	public void testCheckWin() {
		int[] deck = new int[] { 0, 0, 0, 0, 0, 1, 3, 4, 2, 1, 1, 1 };
		gs.setDeck(deck);
		gs.setPlayerTurn(1);
		gs.setPlayer1Score(0);
		gs.setPlayer2Score(0);
		gs.setFreeTurn(false);

		deck = new int[] { 0, 0, 0, 0, 0, 0, 3, 4, 2, 1, 1, 1 };
		gs.playHole(5);
		assertArrayEquals(gs.getDeck(), deck);
		assertEquals(gs.getPlayer1Score(), 1);
		assertEquals(gs.getPlayer2Score(), 0);
		assertEquals(gs.getPlayerTurn(), 1);
		assertEquals(gs.getWinner(), 1);
	}

}
