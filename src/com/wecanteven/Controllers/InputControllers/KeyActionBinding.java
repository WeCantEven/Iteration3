package com.wecanteven.Controllers.InputControllers;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by John on 3/31/2016.
 */
public class KeyActionBinding implements KeyListener{

    private Integer key;
    private KeyInteractionCommand action;

    public KeyActionBinding(Integer key, KeyInteractionCommand action, JFrame jFrame){
        this.key = key;
        this.action = action;
        jFrame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == key){
            action.execute();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}