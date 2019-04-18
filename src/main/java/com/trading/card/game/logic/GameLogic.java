package com.trading.card.game.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.trading.card.game.model.Card;
import com.trading.card.game.model.Player;

public class GameLogic {
//	int rand = (int)(Math.random() * range) + min; 
	private int health = 30;
	private static int playerId = 1;
	private String playerName = "PLAYER_ " + playerId++;
	private List<Integer> deck = Card.list(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8); 
	private List<Integer> hand = new ArrayList<Integer>();
	private int mana = 0;
	private int manaSlot = 0;
	
	private Player activePlayer;
	
	private Player opponentPlayer;

	public Player preparePlayer() {
		return new Player(playerName, health, deck, hand, mana, manaSlot);
	}

	public GameLogic withNoDeck() {
		this.deck = new ArrayList<Integer>();
		return this;
	}

	public GameLogic withHand(int... cards) {
		this.hand = IntStream.of(cards).boxed().collect(Collectors.toList());
		this.hand.stream().filter(han -> this.deck.contains(han)).forEach(x -> {
			this.deck.remove(this.deck.indexOf(x));
		});
		return this;
	}
	
	public GameLogic gameWith2Players(Player activePlayer, Player opponentPlayer) {
		this.activePlayer = activePlayer;
		this.opponentPlayer = opponentPlayer;
		return this;
	}
	
	public void endOfTurn() {
		System.out.println(activePlayer.getName() + " complated own turn ");
		switchPlayers();
	}
	
	private void switchPlayers() {
		Player swapPlayer = this.activePlayer;
		this.activePlayer = this.opponentPlayer;
		this.opponentPlayer = swapPlayer;
	}
	
	public Player getActivePlayer() {
		return this.activePlayer;
	}
	
	public Player getOpponentPlayer() {
		return this.opponentPlayer;
	}
	
	
	public void dropCard() {
		
		
	}
	/**
	
	oyun başlarken mana 0 3 kart alınır ve mana / max mana 1/ 1 olur 
	yeterli mana varsa elinde onu bırakır ve bırakılan kard kadar oyuncunun canı düşer
	eğer mana slots 10 olursa kart çekemezsin
	
	
	*/
	public void lungeStrategy() {
		System.out.println(activePlayer.getMana());
		activePlayer.setMana(6);
		List<Integer> dragable = this.activePlayer.getHand().stream().filter(x -> x <= this.activePlayer.getMana()).
				collect(Collectors.toList());	
		System.out.println(dragable);
	}
	

	
}
