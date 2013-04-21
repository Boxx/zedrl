/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zedrl.actors;

import asciiPanel.AsciiPanel;
import java.util.ArrayList;
import squidpony.squidcolor.SColor;
import zedrl.dungeon.Dungeon;

/**
 *
 * @author Boxx
 */
public class ActorBuilder {
    private Dungeon dungeon;
    private ItemBuilder ib;
    
    public ActorBuilder(Dungeon dungeon){
        this.dungeon = dungeon;
        this.ib = new ItemBuilder(dungeon);
    }
    
    public Actor newPlayer(ArrayList<String> messageQueue, FieldOfView FOV){
        Actor player = new Actor(dungeon, '@', SColor.WHITE, "Zedman", 30, 1, 1, 8);
        player.setLevel(1);
        player.setStr(10);
        player.setWis(10);
        player.setCon(10);
        player.setIntel(10);
        player.setDex(10);
        player.setCha(10);
        player.setModifiers();
        player.equip(ib.getNewDagger());
        player.equip(ib.getNewClothRobe());
        player.setDetails("The Zedman!");
        dungeon.addActor(player, 0);
        new PlayerAI(player, messageQueue, FOV);
        return player;
    }
    
    public Actor newFungus(int z){
        Actor fungus = new Actor(dungeon, 'f', SColor.GREEN, "fungus", 10, 0, 0, 0);
        fungus.setLevel(1);
        fungus.setStr(1);
        fungus.setWis(1);
        fungus.setCon(1);
        fungus.setIntel(1);
        fungus.setDex(1);
        fungus.setCha(5);
        fungus.setModifiers();
        fungus.setDetails("A putrid green fungus");
        dungeon.addActor(fungus, z);
        new FungusAI(fungus, this);
        return fungus;
    }
    
    public Actor newGoblin(int z, Actor player){
        
        Actor goblin = new Actor(dungeon, 'g', SColor.RED, "goblin", 15, 1, 1, 8);
        goblin.setLevel(1);
        goblin.setStr(8);
        goblin.setWis(4);
        goblin.setCon(8);
        goblin.setIntel(8);
        goblin.setDex(8);
        goblin.setCha(8);
        goblin.setModifiers();
        goblin.setDetails("A small reddish humanoid covered in dirt");
        goblin.equip(ib.getNewShortSword());
        dungeon.addActor(goblin, z);
        new GoblinAI(goblin, player);
        return goblin;
        
    }
    
    public Actor newKobold(int z, Actor player){
        
        Actor kobold = new Actor(dungeon, 'k', SColor.GREEN, "kobold", 8, 1, 1, 8);
        kobold.setLevel(1);
        kobold.setStr(9);
        kobold.setWis(9);
        kobold.setCon(10);
        kobold.setIntel(10);
        kobold.setDex(13);
        kobold.setCha(8);
        kobold.setModifiers();
        kobold.setDetails("A short reptilian humanoid with glowing red eyes");
        kobold.equip(ib.getNewLeatherArmor());
        kobold.equip(ib.getNewShortSword());
        dungeon.addActor(kobold, z);
        new GoblinAI(kobold, player);
        return kobold;
    }
    
    public Actor newOrc(int z, Actor player){
        
        Actor orc = new Actor(dungeon, 'o', SColor.GREEN_BAMBOO, "orc", 30, 1,1,8);
        orc.setLevel(1);
        orc.setStr(8);
        orc.setWis(4);
        orc.setCon(8);
        orc.setIntel(8);
        orc.setDex(8);
        orc.setCha(8);
        orc.equip(ib.getNewLeatherArmor());
        orc.equip(ib.getNewMace());
        orc.setDetails("A large green humanoid");
        dungeon.addActor(orc, z);
        new OrcAI(orc,player);
        return orc;
    }
    
    public Actor newOgre(int z, Actor player){
        Actor ogre = new Actor(dungeon, 'O', SColor.DARK_KHAKI, "ogre", 50, 1, 1, 5);
        ogre.setLevel(3);
        ogre.setStr(16);
        ogre.setWis(4);
        ogre.setCon(12);
        ogre.setIntel(4);
        ogre.setDex(4);
        ogre.setCha(4);
        ogre.equip(ib.getNewGreatClub());
        ogre.setDetails("A large green humanoid");
        dungeon.addActor(ogre, z);
        new OrcAI(ogre,player);
        return ogre;
    }
    
    public Actor newBat(int z){
        
        Actor bat = new Actor(dungeon, 'b', SColor.BROWNER, "bat", 5, 1, 1, 10);
        bat.setLevel(1);
        bat.setStr(1);
        bat.setWis(1);
        bat.setCon(1);
        bat.setIntel(1);
        bat.setDex(6);
        bat.setCha(1);
        bat.setModifiers();
        bat.setDetails("An extremely annoying flying rodent");
        dungeon.addActor(bat, z);
        new BatAI(bat);
        return bat;
        
        
    }
}
