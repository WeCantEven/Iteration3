package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class ConsumeableItem extends UseableItem {
    public ConsumeableItem(String name) {
        super(name);
    }

    @Override
    public void use(Character character) {

    }

    /**
     * Visitation Rights
     * */

    public void visit(ItemVisitor visitor) {
        visitor.visitConsumableItem(this);
    }
}