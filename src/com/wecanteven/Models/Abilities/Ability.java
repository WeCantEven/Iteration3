package com.wecanteven.Models.Abilities;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Models.Occupation.Skill;
import com.wecanteven.Visitors.AbilityVisitor;

import java.util.Random;


/**
 * Created by simonnea on 4/4/16.
 */
public class Ability {
    private String name;
    private int windUpTicks,cooldownTicks;
    private int failChance;
    private boolean cast;
    private Skill skill;
    private HitBoxGenerator hitBoxGenerator;
    private Character caster;
    private int manaCost;


    public Ability(String abilityName, Character caster,HitBoxGenerator hitBoxGenerator,Skill skill,int manaCost){
        this.name = abilityName;
        this.caster = caster;
        this.hitBoxGenerator = hitBoxGenerator;
        this.skill = skill;
        cast = false;
        failChance = 0;
        this.manaCost = manaCost;
    }
    public void cast(){
        if (caster.getStats().getMana()<manaCost) {
            ModelTime.getInstance().registerAlertable(()->{
                UIViewFactory.getInstance().createToast(2,"Insufficient Mana");
            },1);
            return;
        }
        caster.getStats().consumeMana(manaCost);
        if (caster.isActive()) {
            return;
        }
        calculateFailChance(getSkillLevel());
        Random randomGenerator = new Random();
        if (cast || getFailChance()<=randomGenerator.nextInt(100)) {
            caster.updateWindUpTicks(getWindUpTicks());
            caster.updateCoolDownTicks(getCooldownTicks());

            activateAbility();
            return;
        }
        //the ability has failed
        caster.updateCoolDownTicks(cooldownTicks);
        caster.updateWindUpTicks(0);
        ViewTime.getInstance().register(()-> {
                    UIViewFactory.getInstance().createToast(2, getName() + " has failed.");
                },0);


    }
    private void activateAbility(){
        ModelTime.getInstance().registerAlertable(() -> {

            hitBoxGenerator.generate();
        },caster.getWindUpTicks());
    }

    public String getName() {
        return name;
    }
    public int getCooldownTicks(){
        return cooldownTicks;
    }
    public int getWindUpTicks(){
        return windUpTicks;
    }
    public int getFailChance(){
        return failChance;
    }
    public Skill getSkill(){
        return skill;
    }
    public int getSkillLevel(){
        return caster.getSkillPoints(getSkill());
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCooldownTicks(int ticks){
        cooldownTicks = ticks;
    }
    public  void setWindUpTicks(int ticks){
        windUpTicks = ticks;
    }
    public void setCast(boolean success){
        cast = success;
    }


    private void calculateFailChance(int skillLevel){
        failChance = 90;                //default chance of 10%
        failChance -= 10*skillLevel;
    }

    //public void configure(Skill skill)  {
    //    this.skill = skill.getSkillPoints();
    //}

    public void accept(AbilityVisitor visitor) {
        visitor.visitAbility(this);
    }
}
