package com.trading.card.game;

import com.trading.card.game.logic.GameLogic;
import com.trading.card.game.model.Player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TradingCardGameLogicTest {
	GameLogic game = new GameLogic();
	Player player;

	@Before
	public void player_should_create() {

	}

	@Test
	public void player_should_have_30_health_after_game_is_prepared() {
		player = game.preparePlayer();
		assertThat(player.getHealth(), is(equalTo(30)));

	}

	@Test
	public void player_should_have_20_deck_after_game_is_prepared() {
		player = game.preparePlayer();
		assertThat(player.numberOfDeck(), is(equalTo(20)));

	}

	@Test
	public void player_should_have_0_card_on_hand_when_game_is_preparing() {
		player = game.preparePlayer();
		assertThat(player.numberOfHand(), is(equalTo(0)));
	}

	@Test
	public void player_should_have_0_mana_and_manaSlot_when_game_is_preparing() {
		player = game.preparePlayer();
		assertThat(player.getMana(), is(equalTo(0)));
		assertThat(player.getManaSlot(), is(equalTo(0)));
	}

	@Test
	public void player_should_have_3_card_on_own_hand_when_game_is_started() {
		player = game.preparePlayer();
		player.initializePlayerHand();
		assertThat(player.numberOfHand(), is(equalTo(3)));
	}

	@Test
	public void player_should_have_17_deck_card_after_hand_init() {
		player = game.preparePlayer();
		player.initializePlayerHand();
		assertThat(player.numberOfDeck(), is(equalTo(17)));
	}

	@Test
	public void hand_shouldnt_change_when_player_has_5_card_own_hand() {
		player = game.withHand(1,2,3,4,5).preparePlayer();
		assertThat(player.numberOfHand(), is(equalTo(5)));
		assertThat(player.numberOfDeck(), is(equalTo(15)));
		// when
		player.drawCard();
		// then
		assertThat(player.numberOfHand(), is(equalTo(5)));
		assertThat(player.numberOfDeck(), is(equalTo(14)));

	}

	@Test
	public void health_demage_after_prepared() {
		player = game.preparePlayer();
		player.demageHealth(2);
		assertThat(player.getHealth(), is(equalTo(28)));
	}

	@Test
	public void health_should_demage_when_there_is_no_deck() {
		player = game.withNoDeck().preparePlayer();
		player.drawCard();
		assertThat(player.getHealth(), is(equalTo(29)));

	}
	
	@Test
	public void player_should_have_mana_after_game_prepared() {
		player = game.preparePlayer();
		player.increaseMana(1,3,5);
		assertThat(player.getMana(),is(equalTo(9)));
		assertThat(player.getManaSlot(), is(equalTo(3)));
	}
	
	@Test
	public void player_should_set_new_mana_after_draw_card() {
		player = game.preparePlayer();
		player.initializePlayerHand();
		player.drawCard();
		assertThat(player.getMana(),is(equalTo(12)));
		assertThat(player.getManaSlot(), is(equalTo(4)));
	}
	
	@Test
	public void game_shoul_have_2_players() {
		 player = game.preparePlayer();
		Player player2 = game.preparePlayer();
		game = game.gameWith2Players(player, player2);
		assertThat(game.getActivePlayer(), is(equalTo(player)));
		assertThat(game.getOpponentPlayer(), is(equalTo(player2)));
	}
	
	
	@Test
	public void players_should_have_switched_end_of_turn() {
		 player = game.preparePlayer();
		Player player2 = game.preparePlayer();
		game = game.gameWith2Players(player, player2);
		game.endOfTurn();
		assertThat(game.getActivePlayer(), is(equalTo(player2)));
		assertThat(game.getOpponentPlayer(), is(equalTo(player)));
	}
	
	
}
