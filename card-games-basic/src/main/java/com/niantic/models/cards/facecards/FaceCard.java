package com.niantic.models.cards.facecards;

import com.niantic.models.cards.Card;

public class FaceCard extends Card
{
    private final String suit;

    private static String getSuitColor(String suit)
    {
        return suit.equalsIgnoreCase("hearts") || suit.equalsIgnoreCase("diamonds")
                ? "Red"
                : "Black";
    }

    public FaceCard(String suit, int pointValue, String faceValue)
    {
        super(pointValue, getSuitColor(suit), faceValue);

        this.suit = suit;
    }

    public String getSuit()
    {
        return suit;
    }

}
