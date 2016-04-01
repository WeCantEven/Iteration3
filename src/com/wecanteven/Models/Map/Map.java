package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.MapVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class Map implements MapVisitable {

    private Column[][] columns;

    public Map(int rSize, int sSize){
        columns = new Column[rSize][sSize];
    }

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitMap(this);
    }

    public boolean add(Entity entity, Location loc){
        return columns[loc.getR()][loc.getS()].add(entity, loc.getZ());
    }
    public boolean add(OneShot oneShot, Location loc){
        return columns[loc.getR()][loc.getS()].add(oneShot, loc.getZ());
    }
    public boolean add(TakeableItem takeableItem, Location loc){
        return columns[loc.getR()][loc.getS()].add(takeableItem, loc.getZ());
    }
    public boolean add(Obstacle obstacle, Location loc){
        return columns[loc.getR()][loc.getS()].add(obstacle, loc.getZ());
    }
    public boolean add(InteractiveItem interactiveItem, Location loc){
        return columns[loc.getR()][loc.getS()].add(interactiveItem, loc.getZ());
    }

    public boolean remove(Entity entity, Location loc){
        return columns[loc.getR()][loc.getS()].remove(entity, loc.getZ());
    }
    public boolean remove(OneShot oneShot, Location loc){
        return columns[loc.getR()][loc.getS()].remove(oneShot, loc.getZ());
    }
    public boolean remove(TakeableItem takeableItem, Location loc){
        return columns[loc.getR()][loc.getS()].remove(takeableItem, loc.getZ());
    }
    public boolean remove(Obstacle obstacle, Location loc){
        return columns[loc.getR()][loc.getS()].remove(obstacle, loc.getZ());
    }
    public boolean remove(InteractiveItem interactiveItem, Location loc){
        return columns[loc.getR()][loc.getS()].remove(interactiveItem, loc.getZ());
    }
}
