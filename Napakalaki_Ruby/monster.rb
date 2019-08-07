# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'Card_dealer.rb'
require_relative 'Bad_consequence.rb'
require_relative 'CombatResult.rb'
require_relative 'dice.rb'
require_relative 'monster.rb'
require_relative 'Napakalaki.rb'
require_relative 'player.rb'
require_relative 'prize.rb'
require_relative 'treasure.rb'
require_relative 'treasure_kind.rb'
module NapakalakiGame
class Monster
  attr_reader :name, :combatLevel, :badConsequence
  def initialize ( n, l,b,  p)
    @name=n
    @combatLevel=l
    @prize=p
    @badConsequence=b
  end
  
  
  def getLevelsGained
    return @prize.levels
  end
  
  def getName
    return @name
  end
  
  def getBadConsequence
    return @badConsequence
  end
  
  def getCombatLevel
    return @combatLevel
  end
  
  def getTreasuresGained
    return @prize.treasures
  end
  
  
  def kills
    return @badConsequence.myBadConsequenceIsDeath
  end
  
  def to_s
    return "name= #@name  combatLevel=#@combatLevel  BadConsequence= ( " +
    @badConsequence.to_s +
     " )  Prize= ( " +
    @prize.to_s  +
     " )"
  end
  
  
end

end