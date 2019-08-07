/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.*;
/**
 *
 * @author l
 */
public class Player {

    
    private ArrayList<Treasure> hiddenTreasures;
    public ArrayList<Treasure> visibleTreasures;
    private BadConsequence pendingBadConsequence;
    private boolean dead;
    private String name;
    private int level;
            
    
    Player(String name){
        this.name=name;
        this.level=1;
        this.dead=true;
        this.hiddenTreasures= new ArrayList();
        this.visibleTreasures= new ArrayList();
        this.pendingBadConsequence=null;
    }
    
    private void bringToLife(){
        dead=false;
        
    }

    public String getName(){
        return name;
    }

    private int getCombatLevel(){
        int combatLevel=level;
        boolean haveNecklace=false;
        for(int i=0; i<visibleTreasures.size() & !haveNecklace; i++){
            if(visibleTreasures.get(i).getType()==TreasureKind.NECKLACE)
                haveNecklace=true;
        }
        if(haveNecklace){
            for(Treasure t : visibleTreasures)
                combatLevel+=t.getMaxBonus();
        }
        else{
            for(Treasure t : visibleTreasures)
                combatLevel+=t.getMinBonus();            
        }
            
        return combatLevel;
        
    }

    private void incrementLevels(int l){

           // System.out.println("Nivel antes incremento= " + Integer.toString(level));
            level+=l;
           // System.out.println("Nivel tras incremento= " + Integer.toString(level));

    }

    private void decrementLevels(int l){
        if(level-l<=0){
            level=1;
            //System.out.println("Mi nivel es = " + Integer.toString(level));
        }
        else{//System.out.println("Nivel antes decremento= " + Integer.toString(level));
            level-=l;
            //System.out.println("Nivel tras decremento= " + Integer.toString(level));            
        }
        
    }

    private void setPendingBadConsequence(BadConsequence b){
        this.pendingBadConsequence=b;
       
    }
    

    private void dieIfNoTreasures(){
        if(hiddenTreasures.isEmpty() & visibleTreasures.isEmpty())
            dead=true;
        
    }


    private void discardNecklaceIfVisible(){
        for( Treasure aux: visibleTreasures)
            if( aux.getType()==TreasureKind.NECKLACE ){
                CardDealer dealer=CardDealer.getInstance();
                dealer.giveTreasureBack(aux);
                visibleTreasures.remove((Treasure)aux);
            }
            
        
    }

    
    private void die(){
        level=1;
        CardDealer dealer=CardDealer.getInstance();
        for(Treasure treasure : visibleTreasures)
            dealer.giveTreasureBack(treasure);
        visibleTreasures.clear();
        
        for(Treasure treasure : hiddenTreasures)
            dealer.giveTreasureBack(treasure);
        
        hiddenTreasures.clear();
        
        this.dieIfNoTreasures();
    }
    
    private int computeGoldCoinsValue(ArrayList<Treasure>  t){
       int numGoldCoins=0;
       int numLevels=0;
       
         
       for(Treasure aux: t){
           numGoldCoins+=aux.getGoldCoins();
       }

     
       return numGoldCoins;
    }
    
    private boolean canIBuyLevels(int l){
        if(level+l<10)
            return true;
        else return false;
        
    }
    
    
    
    private void applyPrize(Monster currentMonster){
        
        int nLevels=currentMonster.getLevelsGained();
        this.incrementLevels(nLevels);
        int nTreasures=currentMonster.getTreasuresGained();
        
        if(nTreasures>0){
            CardDealer dealer=CardDealer.getInstance();
            Treasure treasure;
            for(int i=0; i<nTreasures; i++){
                treasure=dealer.nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }
    }
    
    
    private void applyBadConsequence(BadConsequence bad ){
        int nLevels=bad.getLevels();
        this.decrementLevels(nLevels);
        
        BadConsequence pendingBad=bad.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        this.setPendingBadConsequence(pendingBad);
       
    }
    
    private boolean canMakeTreasureVisible( Treasure t){

        if(t.getType()==TreasureKind.BOTHHAND){
            
            for( Treasure aux: visibleTreasures){
                if(aux.getType() == TreasureKind.ONEHAND)
                    return false;
            }
            
        }
        
        if(t.getType()==TreasureKind.ONEHAND){
            int cont=0;
            for(Treasure aux: visibleTreasures){
                if (aux.getType() == TreasureKind.ONEHAND)
                    cont ++;
                if (cont==2 || aux.getType()==TreasureKind.BOTHHAND)
                    return false;
            }
            return true;
        }
        for( Treasure aux: visibleTreasures){
            if(aux.getType()==t.getType())
                return false;
        }
        
        return true;
       
    }


    private int howManyVisibleTreasures(TreasureKind tKind){
        int nTreasures=0;
        for(Treasure t: visibleTreasures)
            if(t.getType()==tKind)
                nTreasures++;
     return nTreasures;
                
    }
    
    public BadConsequence getPendingBadConsequence(){
        return pendingBadConsequence;
    }
    

    public boolean isDead(){
        if (dead)
            return true;
        else return false;
    }

    
    public ArrayList<Treasure> getHiddenTreasures(){
    
        return this.hiddenTreasures;
    }

    public ArrayList<Treasure> getVisibleTreasures(){
        
        return this.visibleTreasures;
    }    
    
    public CombatResult combat( Monster m){
    
        CombatResult combatResult;
        int myLevel=this.getCombatLevel();
        int monsterLevel=m.getCombatLevel();
    //    System.out.println("Mi Nivel= " + Integer.toString(myLevel) + "  monsterLevel=" + Integer.toString(monsterLevel));
        if(myLevel>monsterLevel){
            
            this.applyPrize(m);
            
            if(level>=10)
                combatResult=CombatResult.WinAndWinGame;
            else 
                combatResult=CombatResult.Win;
           
        }
        else {
            Dice dice=Dice.getInstance();
            int escape=dice.nextNumber();
            if(escape<5){  
                boolean amIDead=m.kills();
                if(amIDead){
                    this.die();
                    combatResult=CombatResult.LoseAndDie;
                }
                else{
                    BadConsequence bad= m.getBadConsequence();
                    combatResult=CombatResult.Lose;
                    this.applyBadConsequence(bad);
                    
                }
                    
                
            }
            else combatResult=CombatResult.LoseAndEscape;
        }
        
        this.discardNecklaceIfVisible();
        return combatResult;
    }
    
    public void makeTreasureVisible(Treasure t){
    
        boolean canI= this.canMakeTreasureVisible(t);
        
        if(canI){
            visibleTreasures.add(t);
            hiddenTreasures.remove(t);
        }
            
    }
    
    public void discardVisibleTreasure(Treasure t){
        visibleTreasures.remove(t);
        if((pendingBadConsequence!=null) && (!pendingBadConsequence.isEmpty()) )
            pendingBadConsequence.substractVisibleTreasure(t);
        this.dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t){
        hiddenTreasures.remove(t);
        if((pendingBadConsequence!=null) && (!pendingBadConsequence.isEmpty()) )
            pendingBadConsequence.substractHiddenTreasure(t);
        this.dieIfNoTreasures();        
        
    }
    
    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden){
        
        boolean canI;       
        float levelsMayBought=this.computeGoldCoinsValue(visible);
        levelsMayBought+=this.computeGoldCoinsValue(hidden);
        int levels=(int) levelsMayBought/1000;          
        canI=this.canIBuyLevels(levels);
        
        if(canI)
            incrementLevels(levels);
        
        visibleTreasures.removeAll(visible);
        hiddenTreasures.removeAll(hidden);
     
        CardDealer dealer=CardDealer.getInstance();
        
        for(Treasure treasure : visible)
            dealer.giveTreasureBack(treasure);
        
        
        for(Treasure treasure : hidden)
            dealer.giveTreasureBack(treasure);
        
            return canI;
    }
    
    public boolean validState(){
        if(pendingBadConsequence==null || (pendingBadConsequence.isEmpty() && hiddenTreasures.size()<=4))
            if(hiddenTreasures.size()>4){
                return false;}
            else return true;
        else return false;
    }
    

    public void initTreasures(){
        CardDealer dealer=CardDealer.getInstance();
        Dice dice=Dice.getInstance();
        this.bringToLife();
        Treasure treasure= dealer.nextTreasure();
        hiddenTreasures.add(treasure);
        int number= dice.nextNumber();
    //    System.out.println("El dado a sacado= ");
    //    System.out.println(Integer.toString(number));
        
        if(number > 1){
            treasure=dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
        if(number ==6){
            treasure=dealer.nextTreasure();
            hiddenTreasures.add(treasure);
        }
    }


    public boolean hasVisibleTreasures(){
        if(visibleTreasures.size()>0)
            return true;
        else return false;
        
        
    }

    public int getLevels(){
        return level;
    }
    
    public String toString(){
        
             
        return name;
    }


}
