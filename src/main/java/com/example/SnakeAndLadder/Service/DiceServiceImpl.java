package com.example.SnakeAndLadder.Service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class DiceServiceImpl implements DiceService {
	int firstRoll, secondRoll;
	/**
	 * logic for Dice roll return total of both dice.
	 *   if a player throws doubles, than the player can throw again. 
	 */
	@Override
	public int roll() {
		int total = 0;
		firstRoll = 1 + (int) (Math.random() * 6);
		secondRoll = 1 + (int) (Math.random() * 6);
		/*
		 * Extra rule 1 i.e.,
		 * if a player throws doubles, than the player can throw again
		 */
		if (firstRoll == secondRoll) {
			this.roll();
			total = firstRoll + secondRoll;
		} else {
			total = firstRoll + secondRoll;
		}
		return total;
	}

}
