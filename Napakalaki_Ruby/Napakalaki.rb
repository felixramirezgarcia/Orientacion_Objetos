# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'Card_dealer.rb'
require_relative 'Bad_consequence.rb'
require_relative 'CombatResult.rb'
require_relative 'dice.rb'
require_relative 'monster.rb'
#require_relative 'napakalaki.rb'
require_relative 'player.rb'
require_relative 'prize.rb'
require_relative 'treasure.rb'
require_relative 'treasure_kind.rb'

module NapakalakiGame
require "singleton" 

class Napakalaki

  include Singleton
  
  attr_reader :currentPlayer, :currentMonster
  def initialize
    @currentPlayer=Player.new("")
    @players=Array.new
    @dealer=CardDealer.instance
    @currentMonster=nil
  end
  
 
  def getCurrentPlayer
    return @currentPlayer
  end
  
  def getCurrentMonster
    return @currentMonster
  end
  #EXAMEN
  def nextTurnExam
    
     extraTreasures=Array.new
     extraTreasures=@currentPlayer.getExtraTreasures
     @currentMonster=dealer.nextMonster
     @currentPlayer.receiveTreasures(extraTreasures)
     nextPlayer()
     
  end
  #FINEXAMEN
  def developCombat
    
    combatResult=@currentPlayer.combat(@currentMonster)
    @dealer.giveMonsterBack(@currentMonster)
    
    return combatResult
    
    
  end
  
  def discardVisibleTreasure( treasures )
    treasures.each{ |treasure| @currentPlayer.discardVisibleTreasure(treasure) 
                                @dealer.giveTreasureBack(treasure)
    }
  end
  
  def discardHiddenTreasure( treasures )
    treasures.each{ |treasure| @currentPlayer.discardHiddenTreasure(treasure) 
                                @dealer.giveTreasureBack(treasure)
    }    
  end
  
  def makeTreasuresVisible( treasures )
    
    puts treasures.size
    treasures.each{ |t| @currentPlayer.makeTreasureVisible(t)}
    
  end
  
  def buyLevels( visible, hidden)
    canI=@currentPlayer.buyLevels(visible, hidden)
    return canI
  end
  
  def initGame( names )
    initPlayers(names)
    @dealer.initCards
    nextTurn
  end
  
  
  def nextTurn
    
    stateOk=nextTurnAllowed
    stateOk=@currentPlayer.validState
    
    if stateOk
      @currentMonster=@dealer.nextMonster
      @currentPlayer=nextPlayer
      
      dead=@currentPlayer.isDead
      #si es su primer turno no tiene tesoros y su nivel es 1
      if dead 
        @currentPlayer.initTreasures
      end
    end
    
    return stateOk
    
  end
  
  def endOfGame( result )
    return result==CombatResult::WinAndWinGame
    
  end
  
  private
  
  def initPlayers (names)
    names.each{
      |x| new_player=Player.new(x)
      @players << new_player
    }
  end
  
  def nextTurnAllowed
    return @currentPlayer.validState()
  end
  
  def nextPlayer
    if @currentPlayer.getVisibleTreasures.empty? and @currentPlayer.getHiddenTreasures.empty?
      return @players[rand(@players.size)]
    
    elsif(@players.index(@currentPlayer)==@players.size-1) 
          return @players[0]
        else
          return @players[@players.index(@currentPlayer)+1]
        end
    
    end
    
    
  end
  


end
