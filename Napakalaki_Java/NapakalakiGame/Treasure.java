/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author l
 */
public class Treasure {

    
    private TreasureKind type;
    private String name;
    private int goldCoins;
    private int minBonus;
    private int maxBonus;
    
    public Treasure(String n, int g, int min, int max, TreasureKind t){
    
        this.name=n;
        this.goldCoins=g;
        this.minBonus=min;
        this.maxBonus=max;
        this.type=t;
        
    }
    
    public String getName(){
        return name;
    }
    
    public int getGoldCoins(){
        return goldCoins;
    }
    
    public int getMinBonus(){
        return minBonus;
    }
    
    public int getMaxBonus(){
        return maxBonus;
    }


    public TreasureKind getType(){
        return type;
    }
    
    public String toString(){
        
        return "name= " + name + " goldCoins= " +Integer.toString(goldCoins) +
                " minBonus= " +Integer.toString(minBonus) + " maxBonus= " + Integer.toString(maxBonus) + " type=" + type;
 
        
    }
    
}

