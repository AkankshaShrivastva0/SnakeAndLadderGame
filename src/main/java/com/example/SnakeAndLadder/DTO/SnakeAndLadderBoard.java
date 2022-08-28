package com.example.SnakeAndLadder.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SnakeAndLadderBoard {
	 private int size;
	    private List<Snake> snakes; // The board contains snakes.
	    private List<Ladder> ladders;  // The board contains ladder.
	    private Map<String, Integer> playerPieces;  // The board contains players info.
	    
	    public SnakeAndLadderBoard() {}
	    public SnakeAndLadderBoard(int size) {
	        this.size = size;
	        this.snakes = new ArrayList<Snake>();
	        this.ladders = new ArrayList<Ladder>();
	        this.playerPieces = new HashMap<String, Integer>();
	    }

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public List<Snake> getSnakes() {
			return snakes;
		}

		public void setSnakes(List<Snake> snakes) {
			this.snakes = snakes;
		}

		public List<Ladder> getLadders() {
			return ladders;
		}

		public void setLadders(List<Ladder> ladders) {
			this.ladders = ladders;
		}

		public Map<String, Integer> getPlayerPieces() {
			return playerPieces;
		}

		public void setPlayerPieces(Map<String, Integer> playerPieces) {
			this.playerPieces = playerPieces;
		}
}
