package com.trading.card.game.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.trading.card.game.model.Card;
import com.trading.card.game.model.Player;

public class GameLogic {
//	int rand = (int)(Math.random() * range) + min; 
	private int health = 30;

	private String playerName = "PLAYER_ " ;
	private List<Integer> deck = Card.list(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8); 
	private List<Integer> hand = new ArrayList<Integer>();
	private int mana = 0;
	private int manaSlot = 0;
	private static int playerId = 1;
	
	private Player activePlayer;
	
	private Player opponentPlayer;

	public Player preparePlayer() {
		playerName = "PLAYER_ " + playerId++;
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
	  Integer dropCard = lungeStrategy();
	  System.out.println(this.opponentPlayer.getName());
	  
		if(dropCard != -1) {
			this.activePlayer.getHand().remove(this.activePlayer.getHand().indexOf(dropCard));
			this.activePlayer.increaseMana((-1)*dropCard);
			this.opponentPlayer.demageHealth(dropCard);
		}
		endOfTurn();
	}
	/**
	
	oyun başlarken mana 0 3 kart alınır ve mana / max mana 1/ 1 olur 
	yeterli mana varsa elinde onu bırakır ve bırakılan kart kadar oyuncunun canı düşer
	eğer mana slots 10 olursa kart çekemezsin

	*/
	public Integer lungeStrategy() {
		
		System.out.println(activePlayer.getMana());
		System.out.println(activePlayer.getHand());
		activePlayer.setMana(8);
		
			Optional<Integer> dragable = this.activePlayer.getHand().stream().filter(x -> x <= this.activePlayer.getMana()).
					collect(Collectors.toList()).parallelStream().max(Comparator.comparing(Integer::valueOf));
			if(dragable.isPresent()) 
				return dragable.get();
			else {
				// lack of mana cost
				
			//TODO	0,0,4,4,6 -> zeros are annoying
 
				return -1;
				
			}
			

	}
	
}
