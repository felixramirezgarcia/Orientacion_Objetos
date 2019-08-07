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



public class Monster {


    Prize prize;
    BadConsequence badConsequence;
    private String name;
    private int combatLevel;
    
    Monster(String n, int l, BadConsequence b, Prize p){
        this.name=n;
        this.combatLevel=l;
        this.prize=p;
        this.badConsequence=b;
        
    }
    
    public String getName(){
        return name;
    }
    
    public int getCombatLevel(){
        return combatLevel;
    }
    
    public BadConsequence getBadConsequence(){
        return badConsequence;
    }
    
    public Prize getPrize(){
        return prize;
    }
    public int getLevelsGained(){
        return prize.getLevels();
    }
    
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    
    public boolean kills(){
        if(badConsequence.myBadConsequenceIsDeath())
            return true;
        else return false;
    }
    
    public String toString(){
        

        return "name= " + name + " Prize= ( " + prize.toString() + " ) combatLevel = " +
                Integer.toString(combatLevel) + " BadConsequence= ( " + badConsequence.toString() + " )";
 
        
    }
    
}
