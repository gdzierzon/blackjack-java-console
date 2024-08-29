package com.niantic.models.games;

import com.niantic.models.cards.Card;
import com.niantic.models.cards.Deck;
import com.niantic.models.hands.Hand;
import com.niantic.models.players.Player;

public class Uno extends ReversibleGame
{
    public Uno(Deck deck)
    {
        super(deck);
    }

    @Override public void dealPlayersIn()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < players.size(); j++)
            {
                Player player = getNextPlayer();
                Card card = deck.getNextCard();

                player.deal(card);
            }
        }

    }

    @Override
    public void endGame()
    {
        winner = players.peek();
        for(Player player : players)
        {
            Hand hand = player.showHand();
            //lowest score wins
            if(hand.getValue() < winner.getHandValue())
            {
                winner = player;
            }
        }
    }

    public void play(){}
}
