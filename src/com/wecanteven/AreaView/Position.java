package com.wecanteven.AreaView;

import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import javafx.geometry.Pos;

/**
 * Created by alexs on 3/29/2016.
 */
public class Position {
    private double r;
    private double s;
    private double z;

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Position(double r, double s, double z) {

        this.r = r;
        this.s = s;
        this.z = z;
    }

    public Position copy(){
        return new Position(r,s,z);
    }

    public Location getLocation() {
        return new Location((int)r,(int)s,(int)z);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            return  p.getR() == getR() &&
                    p.getS() == getS() &&
                    p.getZ() == getZ();

        }
        return false;
    }

    public Position add(Position other) {
        return new Position(
                getR() + other.getR(),
                getS() + other.getS(),
                getZ() + other.getZ());
    }

    public Position multiply(double factor) {
        return  new Position(
                getR()*factor,
                getS()*factor,
                getZ()*factor);
    }

    @Override
    public String toString() {
        return r + ", " + s + ", " + z;
    }
}
