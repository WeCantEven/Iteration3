package com.wecanteven.AreaView.ViewObjects.Hominid;


import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

public abstract class HandState {
    HandViewObject leftHand;
    HandViewObject rightHand;


    protected HandState(HandViewObject leftHand, HandViewObject rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public void drawForeground(Graphics2D graphic) {
        //TODO
    }

    public void drawBackground(Graphics2D graphic) {
        //TODO
    }

    public void draw(Graphics2D graphic) {
        leftHand.draw(graphic);
        rightHand.draw(graphic);
    }

    public void move(Graphics2D graphic) {
        //TODO
    }

    public void changeDirection(Direction direction) {
        //TODO
    }

    public void setLocation(Location location) {
        //TODO
    }

    public Location getLocation() {
        return null;
    }

    public void attack(long durationOfAttack) {
        //TODO
    }

    public void equip(/*add weapon param model doesnt exist*/) {

    }
}