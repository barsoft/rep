package com.barsoft.java_labs2.lab7.entities;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Room {
	private ArrayList<User> users = new ArrayList<>();
	private int capacity = 2;
	private long id;
	private String name;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
	private Game game;
	private boolean gameStarted;

	public Room(Room r) {
		id = r.getId();
		setName("room" + getId());
	}

	public Room() {
		id = ID_GENERATOR.getAndIncrement();
		setName("room" + getId());
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName() + " (" + users.size() + "/" + getCapacity() + ") ("
				+ (getGame() != null ? "playing" : "pending") + ")";
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}