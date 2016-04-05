package com.wecanteven.SaveLoad.Save;

import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Map.*;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.SaveLoad.SaveVisitors.XMLSaveVisitor;
import com.wecanteven.SaveLoad.XMLProcessors.TileXMLProcessor;


/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class SaveToXMLFile implements SaveGame {

    private static Map currentMap;
    private static Avatar currentAvatar;
    private SaveFile saveFile;
    private Map map;
    private Avatar avatar;
    private XMLSaveVisitor saveVisitor;

    public SaveToXMLFile(String fileName) {
        //this.file = getFileFromRes(fileName);
        this.saveFile = new SaveFile(fileName);
        TileXMLProcessor.setCurrentSave(saveFile);
        this.map = currentMap;
        this.avatar = currentAvatar;
        this.saveVisitor = new XMLSaveVisitor(saveFile);

    }

    @Override
    public void saveGame() {
        System.out.println("Dispatching the SaveVisitor");
        avatar.accept(saveVisitor);
        map.accept(saveVisitor);
        saveFile.writeSaveFile();
    }

    public static void setMap(Map map) {
        currentMap = map;
    }

    public static void setAvatar(Avatar avatar) {
        currentAvatar = avatar;
    }



}