package com.niantic.application;

import com.niantic.ui.UserInput;
import com.niantic.ui.UserOutput;
import com.niantic.ui.enumerations.BlackJackPlay;
import com.niantic.models.cards.facecards.FaceCardDeck;
import com.niantic.models.games.BlackJack;
import com.niantic.models.players.BlackJackPlayer;
import com.niantic.models.players.Player;

public class BlackJackConsole extends BlackJack
{

    public BlackJackConsole(FaceCardDeck deck)
    {
        super(deck);
    }

    public void play()
    {
        // welcome screen
        UserOutput.printBlackJackSplashScreen();
        UserInput.waitForInput();

        // loop
        while(true)
        {
            BlackJackPlay option = UserInput.homeScreen();
            if (option == BlackJackPlay.Play)
            {
                startGame();
            }
            else if (option == BlackJackPlay.Quit)
            {
                UserOutput.thankYou();
                break;
            }
        }
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

        // save results
        saveGameResults();

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
            BlackJackPlayer player = new BlackJackPlayer(name);
            join(player);
        }
    }

    private void playRounds()
    {
        while(!allStayed())
        {
            BlackJackPlayer player = (BlackJackPlayer)getNextPlayer();

            // only play those players who have not stayed
            if(!player.hasStayed())
            {
                // show hand
                UserOutput.clear();
                UserOutput.showFaceCardHand(player);

                BlackJackPlay play = UserInput.hitOrStay();
                if(play == BlackJackPlay.Hit)
                {
                    hit(player);
                    if(player.showHand().getValue() > 21)
                    {
                        stay(player);
                        UserOutput.showFaceCardHand(player);
                        UserOutput.bust(player.showHand());
                        UserInput.waitForInput();
                    }
                }
                else
                {
                    stay(player);
                }
            }

        }

        endGame();
    }


    private void saveGameResults()
    {
    }

    private void printResults()
    {
        UserOutput.gameOver();

        for(Player player : players)
        {
            UserOutput.showFaceCardHand(player);
        }

        UserOutput.winner(getWinner());
        UserInput.waitForInput();
    }

}
