package com.example.SnakeAndLadder.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SnakeAndLadder.DTO.Ladder;
import com.example.SnakeAndLadder.DTO.Player;
import com.example.SnakeAndLadder.DTO.Snake;
import com.example.SnakeAndLadder.DTO.SnakeAndLadderBoard;

@Service
public class SnakeAndLadderServiceImpl implements SnakeAndLadderService {
	@Autowired
	DiceServiceImpl DiceService;

	private SnakeAndLadderBoard snakeAndLadderBoard;
	private int initialNumberOfPlayers;
	private Queue<Player> players; // Keeping players in this service as they are specific to this game
									// and not the board.
	//private boolean isGameCompleted;

	private int noOfDices; 
	private static final int DEFAULT_BOARD_SIZE = 100; // The board will have 100 cells numbered from 1 to 100.
	private static final int DEFAULT_NO_OF_DICES = 2; //Number of dice is 2.

	public SnakeAndLadderServiceImpl() {
		this(SnakeAndLadderServiceImpl.DEFAULT_BOARD_SIZE);
	}

	public SnakeAndLadderServiceImpl(int boardSize) {
		this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
		this.players = new LinkedList<Player>();
		this.noOfDices = SnakeAndLadderServiceImpl.DEFAULT_NO_OF_DICES;
	}

	/**
	 * ====Setters for making the game extensible ====
	 */
	
	
	/**
	 * Sets number of dice
	 */
	@Override
	public void setNoOfDices(int noOfDices) {
		this.noOfDices = noOfDices;
	}

	// ==================Initialize board==================
	 
	 
	/**
	 * Sets players on board
	 */
	@Override
	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
		this.initialNumberOfPlayers = players.size();
		Map<String, Integer> playerPieces = new HashMap<String, Integer>();
		for (Player player : players) {
			this.players.add(player);
			playerPieces.put(player.getName(), 0); // Each player has a piece which is initially starting from position 0.
		}
		snakeAndLadderBoard.setPlayerPieces(playerPieces); // Adding player pieces to the game board.
	}

	/**
	 * Sets Snakes on board
	 */
	@Override
	public void setSnakes(List<Snake> snakes) {
		snakeAndLadderBoard.setSnakes(snakes); // Adding snakes to the game board
	}

	
	/**
	 * Sets ladder on board
	 */
	@Override
	public void setLadders(List<Ladder> ladders) {
		snakeAndLadderBoard.setLadders(ladders); // Adding ladders to the game board
	}

	
	 // ==========Core logic for the snake and ladder game==========
	 

	/**
	 * Getting new position of player after and checking snake and ladder position on the board
	 */
	@Override
	public int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) // 5
	{
		int previousPosition;

		do {
			previousPosition = newPosition;
			for (Snake snake : snakeAndLadderBoard.getSnakes()) {
				if (snake.getStart() == newPosition) {
					newPosition = snake.getEnd(); // Whenever a player's position ends up at a position with the head of the snake,
													// the piece should go down to the position of the tail of that
													// snake.
				}
			}

			for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
				if (ladder.getStart() == newPosition) {
					newPosition = ladder.getEnd(); // Whenever a player's position ends up at a position with the start of the
													// ladder, the piece should go up to the position of the end of that
													// ladder.
				}
			}
		} while (newPosition != previousPosition); // There could be another snake/ladder at the tail of the snake or
													// the end position of the ladder and the piece should go up/down
													// accordingly.
		return newPosition;
	}

	/**
	 * moving the player and assigning new position
	 */
	@Override
	public void movePlayer(Player player, int positions) {
		int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getName());
		int newPosition = oldPosition + positions; // Based on the dice value, the player moves their position forward to
													// number of cells.
		int boardSize = snakeAndLadderBoard.getSize();
		/*
		 * Second Extra rule 
		 * i.e., if overshoot the 100th square player move back to
		 * number of square that are still left on the dices.
		 */
		if (newPosition > boardSize) {
			newPosition = 100 - (newPosition % 100);
			newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
		} else {
			newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
		}
		snakeAndLadderBoard.getPlayerPieces().put(player.getName(), newPosition);
	}

	/**
	 * Get total value after both dice roll 
	 *   and also play again, if throws double(Rule 1 given in exercise) 
	 */
	@Override
	public int getTotalValueAfterDiceRolls() {
		return DiceService.roll();
	}

	/**
	 * checking winner player
	 */
	@Override
	public boolean hasPlayerWon(Player player) {
		int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getName());
		int winningPosition = snakeAndLadderBoard.getSize();
		return playerPosition == winningPosition; // A player wins if it exactly reaches the position 100 and the game
													// ends there by getting 1 winner among 4.
	}

	/**
	 * Checking game completed or not
	 */
	@Override
	public boolean isGameCompleted() {
		int currentNumberOfPlayers = players.size();
		return currentNumberOfPlayers < initialNumberOfPlayers;
	}

	/**
	 * Start game logic
	 * return winner
	 */
	@Override
	public String startGame() {
		String playerWon = null;
		while (!isGameCompleted()) {
			//Getting dice value after roll
			int totalDiceValue = getTotalValueAfterDiceRolls(); // Each player rolls the dice when their turn comes.
			Player currentPlayer = players.poll();
			//move player to new position
			movePlayer(currentPlayer, totalDiceValue);
			if (hasPlayerWon(currentPlayer)) {    // checking winning player. 
				playerWon = currentPlayer.getName();
				snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
			} else {
				players.add(currentPlayer);
			}
		}
		return playerWon;
	}

}
