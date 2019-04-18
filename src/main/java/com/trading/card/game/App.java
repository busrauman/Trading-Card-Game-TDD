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
    	game.gameWith2Players(player, player);
    	player.withHand(5,6,8);
    	game.lungeStrategy();
    }
}
