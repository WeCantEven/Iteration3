package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Items.Takeable.TakeableItem;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class TradeInteractionStrategy implements InteractionStrategy {

    private NPC owner;
    private Character other;
    public TradeInteractionStrategy(){
    }

    @Override
    public void setOwner(NPC npc) {
        this.owner = npc;
    }

    @Override
    public void interact(Character other) {
        this.other = other;
        //TODO: GET RID OF THIS LINE:
        other.addMoney(200);
        //TODO: When Character interacts with this Interactions OWNER
        //TODO: Call createTradeView(owner.getItemStorage(), other.getItemStorage(), other.getSkills.getBargainSkill())
        //UIViewFactory.getInstance().createInventoryView(avatar.getCharacter());
        System.out.println("Starting Trade");
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createTradeView(owner, other);
        },0);
    }

    public boolean buy(TakeableItem item){
        System.out.println("ShopKeeper has: " + owner.getItemStorage().getMoney().getValue() + " item: " + item.getName() + " cost: " + item.getValue());
        if(owner.buy(item.getValue())){
            other.addMoney(item.getValue());
            System.out.println("Shopkeeper bought the item");
            return true;
        }
        System.out.println("Shopkeeper did not buy the item");
        return false;
    }


    public boolean sell(TakeableItem item){
        System.out.println("Shopper has: " + other.getItemStorage().getMoney().getValue() + " item: " + item.getName() + " cost: " + item.getValue());
        if(other.buy(item.getValue())){
            owner.addMoney(item.getValue());
            System.out.println("Buyer bought the item");
            return true;
        }
        System.out.println("Buyer did not buy the item");
        return false;
    }
}
