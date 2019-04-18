package com.trading.card.game.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Card {
	
	public static List<Integer> list(int...manaCost){
		return IntStream.of(manaCost).boxed().collect(Collectors.toList());
	}

}
