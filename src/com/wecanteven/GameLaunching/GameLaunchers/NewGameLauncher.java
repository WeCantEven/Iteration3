package com.wecanteven.GameLaunching.GameLaunchers;

import com.wecanteven.Controllers.InputControllers.MainController;
import com.wecanteven.GameLaunching.LevelFactories.DemoLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.TSMBlowsLevelFactory;
import com.wecanteven.ModelEngine;
import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.Equipable.ChestEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.DualWieldMeleeWeapon;
import com.wecanteven.Models.Items.Takeable.Equipable.HeadEquipableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.OneHandedMeleeWeapon;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.ViewEngine;

/**
 * Created by alexs on 4/1/2016.
 */
public class NewGameLauncher extends GameLauncher {

    private LevelFactory levelFactory = new DemoLevelFactory();
    //private LevelFactory levelFactory = new DopeAssLevelFactory();

    public NewGameLauncher(MainController controller, ModelEngine modelEngine, ViewEngine viewEngine){
        super(controller, modelEngine, viewEngine);
        setLevelFactory(levelFactory);
    }

    /*
        In order for this to work, on the Avatar creation screen that
        creates the NewGameLauncher and then calls createAvatar, passing
        the Occupation
     */

    @Override
    public void launch(){
        System.out.println("launching game");
        createMap();
        createAvatar("test");
        populateMap(getMap());
        initializeAreaView();
        initializeUIView();
    }

    @Override
    protected void createMap(){
        setMap(getLevelFactory().createMap());
    }

    @Override
    protected void createAvatar(String occupation){
        Character player = new Character(getMap(), Direction.SOUTH);

        player.pickup(new HeadEquipableItem("Top Hat", 2, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        player.pickup(new HeadEquipableItem("THE GAME CRASHER", 1, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        //player.pickup(new OneHandedMeleeWeapon("Katar", 4, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        //player.pickup(new DualWieldMeleeWeapon("Katar", 5, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        //player.getItemStorage().equip(new ChestEquipableItem("Mediocre Top", 3, new StatsAddable(1,1,1,1,1,1,1,1,1)));
        setAvatar(new Avatar(player, getMap()));
        getMap().add(player, new Location(7,9,2));
    }

    @Override
    protected void populateMap(Map map){
        getLevelFactory().populateMap(map);
    }

}
