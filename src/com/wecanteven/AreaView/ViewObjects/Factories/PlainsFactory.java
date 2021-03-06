package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;

/**
 * Created by alexs on 4/12/2016.
 */
public class PlainsFactory implements BiomeFactory {
    private SimpleVOFactory factory;

    public PlainsFactory(SimpleVOFactory factory) {
        this.factory = factory;
    }

    @Override
    public SimpleViewObject createAboveGround(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Grass.xml");
    }

    @Override
    public SimpleViewObject createWater(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Water.xml");

    }

    @Override
    public SimpleViewObject createBelowGround(Position p) {
        return factory.createSimpleViewObject(p, "Terrain/Dirt.xml");

    }

}
