package com.wecanteven.GameLaunching.LevelFactories;

import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;

/**
 * Created by alexs on 4/1/2016.
 */
public class DopeAssLevelFactory extends LevelFactory{

    @Override
    public Map createMap() {
        Map map = new Map(10,10,10);
        for (int i = 0; i<10; i++) {
            for (int j = 0; j<10; j++) {
                map.getTile(i, j, 0).setTerrain(
                        i + j < 15 ? new Ground() : new Water()
                );
            }
        }
        for (int i = 5; i < 9; i++) {
            map.getTile(i, 0, 1).setTerrain(new Ground());
            if (i >6) {
                map.getTile(i, 0, 2).setTerrain(new Ground());
                map.getTile(i, 0, 3).setTerrain(new Ground());
            }
            if (i >6) {
                map.getTile(i, 9, 1).setTerrain(new Water());
                map.getTile(i, 9, 2).setTerrain(new Water());
                map.getTile(i, 9, 3).setTerrain(new Water());
            }
                map.getTile(7,9,1).setTerrain(new Ground());
                map.getTile(7,9,2).setTerrain(new Ground());
                map.getTile(7,9,3).setTerrain(new Ground());

        }
        return map;
    }

    @Override
    public void populateMap() {

    }
}
