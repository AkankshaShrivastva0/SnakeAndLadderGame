package com.example.SnakeAndLadder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.SnakeAndLadder.DTO.Ladder;
import com.example.SnakeAndLadder.DTO.Player;
import com.example.SnakeAndLadder.DTO.Snake;
import com.example.SnakeAndLadder.Service.SnakeAndLadderServiceImpl;

@Controller
public class SnakeAndLadderController {

	@Autowired
	SnakeAndLadderServiceImpl snakeAndLadderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView snakeAndLadder(HttpServletRequest request) {
		
		ModelAndView winner = new ModelAndView("index");
		String winnerPlayer = null;
		List<Snake> snakes = new ArrayList<Snake>();
		snakes = Arrays.asList(new Snake(16, 6), new Snake(46, 25), new Snake(49, 11), new Snake(62, 19),
				new Snake(64, 60), new Snake(74, 53), new Snake(89, 68), new Snake(92, 88), new Snake(95, 75),
				new Snake(99, 80));
		
		List<Ladder> ladders = new ArrayList<Ladder>();
		ladders = Arrays.asList(new Ladder(2, 38), new Ladder(7, 14), new Ladder(8, 31), new Ladder(15, 26),
				new Ladder(21, 42), new Ladder(28, 84), new Ladder(36, 44), new Ladder(51, 67), new Ladder(71, 91),
				new Ladder(78, 98), new Ladder(87, 94));
		
		List<Player> players = new ArrayList<Player>();
		players = Arrays.asList(new Player("player1"), new Player("player2"), new Player("player3"),
				new Player("player4"));
		
		snakeAndLadderService.setPlayers(players);
		snakeAndLadderService.setSnakes(snakes);
		snakeAndLadderService.setLadders(ladders);
		winnerPlayer = snakeAndLadderService.startGame();
		winner.addObject("NoOfPlayers", players.size());
		winner.addObject("NoOfSnakes", snakes.size());
		winner.addObject("NoOfLadders", ladders.size());
		winner.addObject("totalPlayer", players);
		winner.addObject("snakeStartAndEnd", snakes);
		winner.addObject("LadderStartAndEnd", ladders);
		winner.addObject("playerWinner", winnerPlayer);
		return winner;

	}
}
