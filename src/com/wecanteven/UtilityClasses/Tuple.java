package com.wecanteven.UtilityClasses;

/**
 * Created by Alex on 3/31/2016.
 */
public class Tuple<X, Y> {
    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;

    }

    public Tuple clone(){
        return new Tuple(this.x, this.y);
    }
}