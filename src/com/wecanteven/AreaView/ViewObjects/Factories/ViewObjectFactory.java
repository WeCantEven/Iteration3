package com.wecanteven.AreaView.ViewObjects.Factories;

import com.sun.glass.ui.View;
import com.wecanteven.AreaView.AreaView;
import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.DynamicImages.StartableDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DestroyableViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.Equipment.EquipableViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.FeetViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HandViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.*;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Directional;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 3/31/2016.
 */
public abstract class ViewObjectFactory {
    private HexDrawingStrategy hexDrawingStrategy;
    private AreaView areaView;
    private DynamicImageFactory factory = DynamicImageFactory.getInstance();

    public ViewObjectFactory(AreaView areaView) {
        this.hexDrawingStrategy = new HexDrawingStrategy();
        this.hexDrawingStrategy.setCenterTarget(
                createSimpleViewObject(
                        new Position(0,0,0),
                        "null.xml"
                )
        );
        this.areaView = areaView;
    }

    public abstract ViewObject createGround(Position p);
    public abstract ViewObject createWater(Position p);



    public ViewObject createSneak(Position p, Direction d, Character subject) {
        EquipmentSlot chestSlot = subject.getItemStorage().getEquipped().getChest();
        EquipmentSlot weaponSlot = subject.getItemStorage().getEquipped().getWeapon();
        EquipmentSlot hatSlot = subject.getItemStorage().getEquipped().getHead();


        DirectionalViewObject body = createBody(p, subject, "Sneak");
        EquipableViewObject bodyArmor = createEquipable(body, createNullViewObject(), chestSlot, subject);
        EquipableViewObject hatArmor = createEquipable(bodyArmor, createNullViewObject(), hatSlot, subject);

        chestSlot.attach(bodyArmor);
        hatSlot.attach(hatArmor);


        MicroPositionableViewObject leftHand = new MicroPositionableViewObject(createLeftHand(p));
        MicroPositionableViewObject rightHand = new MicroPositionableViewObject(createRightHand(p));
        HandsViewObject hands = new HandsViewObject(leftHand, rightHand, d, p, weaponSlot);

        weaponSlot.attach(hands);

        MicroPositionableViewObject leftFoot = createLeftFoot(p, d, subject);
        MicroPositionableViewObject rightFoot = createRightFoot(p, d, subject);

        FeetViewObject feet = new FeetViewObject(d, leftFoot, rightFoot);
        HominidViewObject stationarySneak = new  HominidViewObject(p, d, subject, subject, hatArmor, hands, feet);

        subject.attach(stationarySneak);
        subject.attach(body);
        //TEMPORARY TESTING WORKAROUND
        //TODO: make better
        hexDrawingStrategy.setCenterTarget(stationarySneak);


        return createMovingViewObject(subject, stationarySneak);

    }

    public DestroyableViewObject createOneShotItem(Position position, OneShot oneShot) {
        StartableDynamicImage animation = factory.loadActiveDynamicImage("Items/" + oneShot.getName() + "/" + oneShot.getName() + ".xml");

        StartableViewObject internalVO = new StartableViewObject(position, animation, hexDrawingStrategy);
        DestroyableViewObject destroyableVO = new DestroyableViewObject(internalVO, oneShot);
        oneShot.attach(destroyableVO);
        return destroyableVO;
    }

    private MicroPositionableViewObject createLeftFoot(Position position, Direction direction, Entity entity) {
        DirectionalViewObject leftFootDirectional = createDirectional(position, entity, "Feet/Brown/Left/");
        entity.attach(leftFootDirectional);
        return new MicroPositionableViewObject(leftFootDirectional);
    }

    private MicroPositionableViewObject createRightFoot(Position position, Direction direction, Entity entity) {
        DirectionalViewObject rightFootDirectional = createDirectional(position, entity, "Feet/Brown/Right/");
        entity.attach(rightFootDirectional);
        return new MicroPositionableViewObject(rightFootDirectional);
    }

    private ViewObject createRightHand(Position position) {
        return new SimpleViewObject(position, factory.loadDynamicImage("Hands/Human/hand.xml"), hexDrawingStrategy);
    }

    private ViewObject createLeftHand(Position position) {
        return new SimpleViewObject(position, factory.loadDynamicImage("Hands/Human/hand.xml"), hexDrawingStrategy);
    }
//    private FeetViewObject createFeet(Position p, Direction d, String name) {
//        FootViewObject leftFoot = createFoot(p, d, name + "/Left");
//        FootViewObject rightFoot = createFoot(p, d, name + "/Right");
//        return new FeetViewObject(leftFoot, rightFoot, p, d);
//    }

    public ViewObject createInteractableItem(Position p, InteractiveItem interactiveItem) {
        ActivatableViewObject vo = new ActivatableViewObject(p,
                interactiveItem,
                factory.loadDynamicImage("Items/" + interactiveItem.getName() + "/Active.xml"),
                factory.loadDynamicImage("Items/" + interactiveItem.getName() + "/Inactive.xml"),
                hexDrawingStrategy);
        interactiveItem.attach(vo);
        return vo;
    }

    public DirectionalViewObject createEquipment(Position p, Directional d, String name ) {
        return createDirectional(p, d, "Equipment/" + name + "/");
    }

    private DirectionalViewObject createBody(Position p, Directional d, String entityName) {
        return createDirectional(p, d, "Entities/" +  entityName + "/");
    }


    private DirectionalViewObject createDirectional(Position p, Directional d, String path) {
        DynamicImage bodyNorth = DynamicImageFactory.getInstance().loadDynamicImage(path +  "north.xml");
        DynamicImage bodySouth = DynamicImageFactory.getInstance().loadDynamicImage(path +  "south.xml");
        DynamicImage bodyNorthEast = DynamicImageFactory.getInstance().loadDynamicImage(path +  "northeast.xml");
        DynamicImage bodyNorthWest = DynamicImageFactory.getInstance().loadDynamicImage(path +  "northwest.xml");
        DynamicImage bodySoutheast = DynamicImageFactory.getInstance().loadDynamicImage(path +  "southeast.xml");
        DynamicImage bodySouthWest = DynamicImageFactory.getInstance().loadDynamicImage(path +  "southwest.xml");
        return new DirectionalViewObject(p, d, hexDrawingStrategy, bodyNorth, bodySouth, bodyNorthEast, bodyNorthWest, bodySoutheast, bodySouthWest);
    }

    private MovingViewObject createMovingViewObject(Entity subject, ViewObject child) {
        MovingViewObject mvo = new  MovingViewObject(child, subject, areaView);
        subject.attach(mvo);
        return mvo;
    }

    public HexDrawingStrategy getDrawingStrategy() {
        return hexDrawingStrategy;
    }

    public DynamicImageFactory getDynamicImageFactory() {
        return factory;
    }

    public EquipableViewObject createEquipable(ViewObject child, ViewObject equipment, EquipmentSlot subject, Directional directional) {
        return new EquipableViewObject(child, equipment, subject, this, directional);
    }

    public NullViewObject createNullViewObject() {
        return new NullViewObject(new Position(0,0,0));
    }

    protected SimpleViewObject createSimpleViewObject(Position p, String path) {
        return new SimpleViewObject(p, factory.loadDynamicImage(path), hexDrawingStrategy);
    }
}
