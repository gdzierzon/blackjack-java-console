package com.niantic.application;

import com.niantic.models.cards.Card;
import com.niantic.models.cards.Deck;
import com.niantic.models.games.Uno;
import com.niantic.models.players.Player;
import com.niantic.ui.UserInput;
import com.niantic.ui.UserOutput;

public class UnoConsole extends Uno
{

    public UnoConsole(Deck deck)
    {
        super(deck);
    }

    public void play()
    {
        // welcome screen

        startGame();
    }

    public void startGame()
    {
        // restart game
        players.clear();

        // create players
        createPlayers();

        // deal
        dealPlayersIn();

        // play rounds
        playRounds();

        // declare winner
        printResults();

    }

    private void createPlayers()
    {
        // how many players
        int numberOfPlayers = UserInput.numberOfPlayers();

        // get player names
        for (int i = 0; i < numberOfPlayers; i++)
        {
            String name = UserInput.getPlayerName();
            Player player = new Player(name);
            join(player);
        }
    }

    private void playRounds()
    {
        while(true)
        {
            Player player = getNextPlayer();

            //add logic to play a round of uno
            Card card = player.getNextCard();
            player.discard(card);

            // since this game is reversible - call the
            if(card.getFaceValue().equalsIgnoreCase("reverse"))
            {
                reverse();
            }

            // break out of loop as soon as one player is out of card
            if(!player.hasCards())
            {
                break;
            }

        }

        endGame();
    }


    private void printResults()
    {
        UserOutput.gameOver();

        for(Player player : players)
        {
            UserOutput.showHand(player);
        }

        UserOutput.winner(getWinner());
        UserInput.waitForInput();
    }

}
