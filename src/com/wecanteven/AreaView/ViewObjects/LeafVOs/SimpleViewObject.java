package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class SimpleViewObject extends LeafViewObject {
    private DynamicImage dImage;
    private DynamicImageDrawingStrategy painter;

    public SimpleViewObject(Position position, DynamicImage dImage, DynamicImageDrawingStrategy painter) {
        super(position);
        this.dImage = dImage;
        this.painter = painter;
    }

    @Override
    public void draw(Graphics2D g) {
        painter.draw(g, dImage, getPosition());
    }
}