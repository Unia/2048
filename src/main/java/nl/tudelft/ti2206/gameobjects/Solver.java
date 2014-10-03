/**
 * 
 */
package nl.tudelft.ti2206.gameobjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import nl.tudelft.ti2206.game.TwentyFourtyGame.GameState;
import nl.tudelft.ti2206.gameobjects.Grid.Direction;
import nl.tudelft.ti2206.log.Logger;

public class Solver extends TimerTask {

	private static Logger logger = Logger.getInstance();

	private Grid original;

	private static int succesfulMoves = 0;

	// private int moves;
	//
	// private Timer timer;

	private static void print(String str) {
		System.out.println("[AUTSOLVE]: " + str);
	}

	public void setGrid(Grid grid) {
		this.original = grid;
	}

	// public void setMoves(int moves) {
	// this.moves = moves;
	// }
	//
	// public int getMoves() {
	// return moves;
	// }
	//
	// public static boolean leftColFull(Grid grid) {
	// Tile[] tiles = grid.getTiles();
	// for (int index = 0; index < 16; index += 4)
	// if (tiles[index].isEmpty())
	// return false;
	// return true;
	// }

	public static boolean lowerRowFull(Grid grid) {
		Tile[] tiles = grid.getTiles();
		for (int index = 12; index < 16; index += 1)
			if (tiles[index].isEmpty())
				return false;
		return true;
	}

	public static int tryMoves(Grid ogrid) {

		int highest = 0;

		for (Direction direction : Grid.Direction.values()) {

			// ignore up direction
			if (direction == Direction.UP)// && !leftColFull(ogrid))
				continue;

			// ignore right direction if lower row is not full
			if (direction == Direction.RIGHT && !lowerRowFull(ogrid))
				continue;

			Grid grid = ogrid.clone();
			grid.move(direction);

			// get highest score possible
			highest = Math.max(highest, grid.getScore());
		}
		return highest;
	}

	public static Direction selectDirectionComplex(Grid ogrid) {

		int score = ogrid.getScore();
		Direction selected = null;

		for (Direction direction : Grid.Direction.values()) {

			Grid grid = ogrid.clone();

			if (direction == Direction.UP)// && !leftColFull(ogrid))
				continue;

			if (direction == Direction.RIGHT && !lowerRowFull(ogrid))
				continue;

			// if move actually is possible
			if (grid.move(direction) != -1) {

				int pointsAfter = grid.getScore() + tryMoves(grid.clone());

				if (pointsAfter > score) {
					score = pointsAfter;
					selected = direction;
				}
			}
		}

		return selected;
	}

	public static Direction selectDirectionSimple(Grid grid) {
		Direction direction = Direction.LEFT;

		if (grid.clone().move(Direction.LEFT) != -1)
			direction = Direction.LEFT;
		else if (grid.clone().move(Direction.DOWN) != -1)
			direction = Direction.DOWN;
		else if (grid.clone().move(Direction.RIGHT) != -1)
			direction = Direction.RIGHT;
		else
			// if all else fails, move up :/
			direction = Direction.UP;

		return direction;
	}

	public static void autoMove(Grid grid) {

		Direction direction = selectDirectionComplex(grid.clone());

		if (direction == null) {

			print("smart direction selection failed! using 'dumb' movement selection");
			direction = selectDirectionSimple(grid.clone());
		}
		print("making selected move: " + direction);
		if (grid.move(direction) != -1)
			succesfulMoves += 1;
		if (direction == Direction.UP) {
			if (grid.move(Direction.DOWN) != -1)
				succesfulMoves += 1;
		}

	}

	public static Timer autoSolve(Grid grid, int delay) {
		print("Trying to solve grid automatically, making one move every "
				+ delay + "ms...");

		Solver solver = new Solver();
		solver.setGrid(grid);

		Timer timer = new Timer();
		timer.schedule(solver, 0, delay);

		return timer;
	}

	@Override
	public void run() {

		if (original.getPossibleMoves() == 0) {
			print("succesful moves made: " + succesfulMoves);
			print("Grid's full! Did I lose? :(");
			this.cancel();
		} else
			autoMove(original);
	}

}
