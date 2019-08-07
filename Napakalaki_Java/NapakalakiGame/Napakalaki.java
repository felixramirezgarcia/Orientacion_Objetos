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
public class Napakalaki {

    Player currentPlayer;
    ArrayList<Player> players;
    CardDealer dealer;
    Monster currentMonster;
    int currentPlayerIndex;
 
    private static final Napakalaki instance = new Napakalaki();
    private Napakalaki(){
        currentPlayer=new Player("");
        players=new ArrayList();
        dealer=CardDealer.getInstance();
        currentMonster=null;
    };
    
    
    public static Napakalaki getInstance(){
        return instance;
    }
    
    private void initPlayers(ArrayList<String> names){
        for(String aux : names){
            
            Player player= new Player(aux);
            players.add(player);
        }
        

    }
    
    private Player nextPlayer(){
        if( currentPlayer.getHiddenTreasures().isEmpty() && currentPlayer.getVisibleTreasures().isEmpty()){
            Random rnum = new Random();
            return  players.get(rnum.nextInt(players.size()));
        }
        else{ if(players.indexOf(currentPlayer)==players.size()-1)
                return players.get(0);
              else return players.get(players.indexOf(currentPlayer)+1);
            }
            
    }
    
    private boolean nextTurnAllowed(){
        if(currentPlayer.validState())
            return true;
        else return false;
    }
    
    public static Napakalaki getInstace(){
        return instance;
    }
    
    public CombatResult developCombat(){
    
        CombatResult combatResult=currentPlayer.combat(currentMonster);
        
        dealer.giveMonsterBack(currentMonster);
        
        return combatResult;
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        
        
        for(Treasure treasure : treasures){
            currentPlayer.discardVisibleTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){

        for(Treasure treasure : treasures){
            currentPlayer.discardHiddenTreasure(treasure);
            dealer.giveTreasureBack(treasure);
        }
    }    
    
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
    
        for(Treasure t: treasures){
            currentPlayer.makeTreasureVisible(t);
        }
    }
    
    public boolean buyLevels( ArrayList<Treasure> visible, ArrayList<Treasure> hidden){
    
        boolean canI=currentPlayer.buyLevels(visible, hidden);
        return canI;
    }
    
    public void initGame( ArrayList<String> players){
        this.initPlayers(players);
        dealer.initCards();
        this.nextTurn();
        
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
        
    }
    
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    
    public boolean nextTurn(){
      boolean stateOk=this.nextTurnAllowed();
      if(stateOk){
          dealer=CardDealer.getInstance();
          currentMonster=dealer.nextMonster();
          currentPlayer=this.nextPlayer();
          
          boolean dead=currentPlayer.isDead();
          
          if(dead)
          {
              currentPlayer.initTreasures();
          }
          
      }
                return stateOk;
    }
    
    public boolean endOfTheGame(CombatResult result){
        if(result==CombatResult.WinAndWinGame)
            return true;
        else return false;
    }









}
