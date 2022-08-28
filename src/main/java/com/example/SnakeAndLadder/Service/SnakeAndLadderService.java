package com.example.SnakeAndLadder.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SnakeAndLadder.DTO.Ladder;
import com.example.SnakeAndLadder.DTO.Player;
import com.example.SnakeAndLadder.DTO.Snake;

@Service
public interface SnakeAndLadderService {

	void setNoOfDices(int noOfDices);

	void setPlayers(List<Player> players);
	void setSnakes(List<Snake> snakes);
	void setLadders(List<Ladder> ladders);

	// Core business logic
	int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition);

	void movePlayer(Player player, int positions);

	int getTotalValueAfterDiceRolls();

	boolean hasPlayerWon(Player player);

	boolean isGameCompleted();

	// Start Game method
	String startGame();

}
