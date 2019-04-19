package com.trading.card.game;

import com.trading.card.game.logic.GameLogic;
import com.trading.card.game.model.Player;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	GameLogic game = new GameLogic();
    	Player player = game.preparePlayer();
    	Player player2 = game.preparePlayer();
    	game.gameWith2Players(player, player2);
    	player.withHand(5,6,8);
    	player2.withHand(0,4,5);
    	game.dropCard();
    }
}
