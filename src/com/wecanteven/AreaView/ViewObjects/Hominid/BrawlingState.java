package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.HandWalkingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.LimbStrategy;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;


public class BrawlingState extends HandState {

    private final double tangent = 0d;
    private final double radius = 0.5d;
    private final double leftAngle = Math.PI/2;
    private final double rightAngle = -leftAngle;
    private final double height = 2;

    private LimbStrategy walkingStrategy;

    public BrawlingState(Direction direction, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand) {
        super(leftHand, rightHand);
        walkingStrategy = new HandWalkingStrategy(0.3, leftHand, rightHand);
        changeDirection(direction);
        leftHand.setRadius(radius);
        leftHand.setOffsetAngle(leftAngle);
        leftHand.setTangent(tangent);
        leftHand.setHeight(height);
        rightHand.setRadius(radius);
        rightHand.setOffsetAngle(rightAngle);
        rightHand.setTangent(tangent);
        rightHand.setHeight(height);
    }

    @Override
    public void move(long duration) {
        walkingStrategy.execute(duration);
    }

    public void changeDirection(Direction direction) {
        leftHand.setDirection(direction);
        rightHand.setDirection(direction);
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

    //swapping states could be here
}
