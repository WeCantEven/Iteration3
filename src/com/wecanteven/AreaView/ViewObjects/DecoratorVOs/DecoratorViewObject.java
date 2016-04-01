package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public abstract class DecoratorViewObject implements ViewObject{
    private ViewObject child;

    public DecoratorViewObject(ViewObject child) {
        this.child = child;
    }

    public final ViewObject getChild() {
        return child;
    }

    @Override
    public Position getPosition() {
        return child.getPosition();
    }

    @Override
    public void setPosition(Position p) {
        child.setPosition(p);
    }

    @Override
    public void draw(Graphics2D g) {
        child.draw(g);
    }
}