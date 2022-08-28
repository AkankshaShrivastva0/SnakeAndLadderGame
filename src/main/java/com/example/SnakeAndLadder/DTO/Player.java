package com.example.SnakeAndLadder.DTO;

import java.util.UUID;

public class Player {
	 private String name;
	    private String id;

	    public Player() {}
	    public Player(String name) {
	        this.name = name;
	        this.id = UUID.randomUUID().toString();
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
}
