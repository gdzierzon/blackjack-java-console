package com.niantic.models.hands;

public class BlackJackHand extends FaceCardHand
{
    public boolean isBust()
    {
        return getValue() > 21;
    }
}
