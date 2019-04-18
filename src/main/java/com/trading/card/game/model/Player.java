package com.trading.card.game.model;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
	private int health;
	private String name;
	private List<Integer> deck;
	private List<Integer> hand;
	private int mana;
	private int manaSlot;

	public Player(String name, int health, List<Integer> deck, List<Integer> hand, int mana, int manaSlot) {
		this.health = health;
		this.name = name;
		this.deck = deck;
		this.hand = hand;
		this.mana = mana;
		this.manaSlot = manaSlot;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDeck() {
		return deck;
	}

	public void setDeck(List<Integer> deck) {
		this.deck = deck;
	}

	public List<Integer> getHand() {
		return hand;
	}

	public void setHand(List<Integer> hand) {
		this.hand = hand;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getManaSlot() {
		return manaSlot;
	}

	public void setManaSlot(int manaSlot) {
		this.manaSlot = manaSlot;
	}

	public int numberOfDeck() {
		return this.deck.size();
	}

	public int numberOfHand() {
		return this.hand.size();
	}

	public void withHand(int... cards) {
		this.hand = IntStream.of(cards).boxed().collect(Collectors.toList());
		this.hand.stream().filter(han -> this.deck.contains(han)).forEach(x -> {
			this.deck.remove(this.deck.indexOf(x));
		});
	}

	public void initializePlayerHand() {
		
		this.hand = Card.list(1, 3, 5);
		this.hand.stream().filter(han -> this.deck.contains(han)).forEach(x -> {
			this.deck.remove(this.deck.indexOf(x));
			increaseMana(x);
		});
	}

	public void drawCard() {
		if (numberOfDeck() == 0) {
			demageHealth(1);
		} else {
			pickCard();
			overloaded();
		}
	}

	public void overloaded() {
		if (numberOfHand() > 5) {
			removeFromHand(numberOfHand() - 1);
		}
	}

	public void demageHealth(int demage) {
		this.health -= demage;
	}

	public void increaseMana(int... manaValues) {
		IntStream.of(manaValues).forEach(x -> {
			this.mana += x;
			this.manaSlot++;
		});
	}

	public void pickCard() {
		// TODO randomly
		Integer picked = this.deck.stream().filter(x -> x==3).findFirst().get();
		System.out.println("picked ->"+picked);
		this.hand.add(picked);
		increaseMana(picked);
		this.deck.remove(this.deck.indexOf(picked));
		
	}

	public void removeFromDeck() {
		this.hand.stream().filter(han -> this.deck.contains(han)).forEach(x -> {
			this.deck.remove(this.deck.indexOf(x));
		});
	}

	public void removeFromHand(int card) {
		this.hand.remove(card);
	}

}
