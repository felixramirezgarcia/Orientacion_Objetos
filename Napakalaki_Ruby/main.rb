#encoding: utf-8
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

def main(p)
  
  puts "Por si acaso" + p.getName
  
end
module NapakalakiGame

 
  
 


dealer=CardDealer.instance
dealer.initCards
  
p=Player.new("Luis")
main(p)


  
  
#  if(!p.hasVisibleTreasures())
#    puts "No tiene tesoros visibles"
#  end

#    d=CardDealer.instance
#    d.initCards
#    puts d.unusedMonsters[7].getLevelsGained
    
    

#bad=BadConsequence.newDeath("NUeva")
#if bad.isEmpty()
#  puts "Esta vacio"
#end
#
#  puts p.howManyVisibleTreasures(TreasureKind::NECKLACE)
#  puts p.howManyVisibleTreasures(TreasureKind::BOTHHAND)
#  puts p.howManyVisibleTreasures(TreasureKind::HELMET)  
#if p.validState()
#  puts("Hello World")
#end

  
  
  #
  #He cambiado como se inicializan los vectores al comienzo de la badConsequence
  #
  #
 #
end

