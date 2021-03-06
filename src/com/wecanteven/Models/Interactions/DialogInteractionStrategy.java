package com.wecanteven.Models.Interactions;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.NPC;

import java.util.ArrayList;
import java.util.Iterator;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

/**
 * Created by Joshua Kegley on 4/7/2016.
 */
public class DialogInteractionStrategy implements InteractionStrategy {

    private NPC owner;
    ArrayList<String> dialog;

    public DialogInteractionStrategy(ArrayList<String> dialog){
        this.dialog = dialog;
    }
    public void setOwner(NPC npc) {
        this.owner = npc;
    }

    @Deprecated
    public ArrayList<String> getDialog() { return dialog;}

    public Iterator<String> getIterator() {
        return dialog.iterator();
    }


    @Override
    public void interact(Character c) {
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createDialogView(owner, c, getIterator());
        },0);
    }

    public void interact(Avatar c) {
        ViewTime.getInstance().register(()->{
            UIViewFactory.getInstance().createDialogView(owner, c.getCharacter(), getIterator());
        },0);
    }
}
