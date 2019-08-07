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
module NapakalakiGame

  

class Player

  attr_reader :level,  :hiddenTreasures, :visibleTreasures, :pendingBadConsequence

  def initialize(name)
    @dead=true
    @name=name
    @level=1
    @hiddenTreasures=Array.new
    @visibleTreasures=Array.new
    @pendingBadConsequence= nil
  end
  
  def to_s
    return "#@name"
  end
  def getName
    return "#@name"
  end  
  
  def getLevels
    return @level
  end
  
  #EXAMEN
  def receiveTreasures(extraTreasures)
    extraTreasures.each{|x|
      
      @hiddenTreasures << x
    }
  end
  
  
  def getExtraTreasures
     extraTreasures=Array.new
     i=@hiddenTreasures.size
    while(i>4)
      extraTreasures << @hiddenTreasures[i-1]
      @hiddenTreasures.delete_at(i-1)
      i=i-1

    end
    
    return extraTreasures
    
  end
  
  #FINEXAMEN
  def getHiddenTreasures
    return @hiddenTreasures
  end
  
  def getVisibleTreasures
    return @visibleTreasures
  end
  
  private
    def bringToLife
      @dead=false  
    end
    
    def getCombatLevel()
      combatLevel=@level
      haveNecklace=false
      i=0
      while(i<@visibleTreasures.size() and !haveNecklace)
        if(@visibleTreasures[i].type==TreasureKind::NECKLACE)
          haveNecklace=true
        end 
        i=i+1
      end
      
      if(haveNecklace)
      @visibleTreasures.each{ |x| combatLevel=combatLevel+x.maxBonus }
      else
        @visibleTreasures.each{ |x| combatLevel= combatLevel+x.minBonus }
      end
      
      return combatLevel
    end
        
      
    
    
    
    def incrementLevels(l)

        puts "Mi nivel actual= #@level"
        @level+=l
        puts "Mi nivel tras incremento= #@level"

    end
    
    def decrementLevels(l)
      if(@level-l<=0)
        puts "Mi nivel actual= #@level"  
        @level=1
      else
        puts "Mi nivel actual= #@level"        
        @level-=l
        puts "Mi nivel tras decremento= #@level"
      end      
    end
    
    def setPendingBadConsequence(b)
      @pendingBadConsequence=b
    end
  
  
    def dieIfNoTreasures
      if(@hiddenTreasures.size()==0 and @visibleTreasures.size()==0)
        @dead=true
      end
      
    end
    
    def discardNecklaceIfVisible
      @visibleTreasures.each{ |x|
        if x.type==TreasureKind::NECKLACE
          dealer=CardDealer.instance
          dealer.giveTreasureBack(x)
          @visibleTreasures.delete(x)
        end
        
      }
      
    end
    
    def die
      
      @level=1
      dealer=CardDealer.instance
      @visibleTreasures.each{ |treasure| dealer.giveTreasureBack(treasure)}
      @visibleTreasures.clear
      
      @hiddenTreasures.each{ |treasure| dealer.giveTreasureBack(treasure)}
      @hiddenTreasures.clear
      
      dieIfNoTreasures
      
      
    end
    
    def computeGoldCoinsValue(t)
      num_gold_coins=0;
      num_levels=0
      t.each{ |x| num_gold_coins=num_gold_coins+x.goldCoins }
#      puts "Numero de monedas de oro "+ num_gold_coins.to_s
#     if(num_gold_coins<1000)
#        return 0
#     else
#       loop do
#          num_levels=num_levels+1
#          num_gold_coins=num_gold_coins-1000
#          break if num_gold_coins<1000
#        end   
#      end
#      puts "numero de niveles= " + num_levels.to_s
#      return num_levels
       return num_gold_coins
    end
    
    def canIBuyLevels(l)
        if(@level+l<10)
            return true;
        else return false;  
        end
    end
    
    def applyPrize(currentMonster)
      nLevels=currentMonster.getLevelsGained()
      incrementLevels(nLevels)
      nTreasures=currentMonster.getTreasuresGained()
      
      if(nTreasures>0)
        dealer=CardDealer.instance
        i=0
        while(i<nTreasures)
          treasure=dealer.nextTreasure()
          i=i+1
          @hiddenTreasures << treasure
        end
      end
    end
    
    def applyBadConsequence(bad)
      
      nLevels=bad.getLevels()
      decrementLevels(nLevels)
      pendingBad=bad.adjustToFitTreasureLists(@visibleTreasures, @hiddenTreasures)
      
      setPendingBadConsequence(pendingBad)
    end
  
    def canMakeTreasureVisible( t )
  
      if t.type==TreasureKind::ONEHAND
        cont=0
        @visibleTreasures.each{ |x|
          if (x.type==TreasureKind::ONEHAND)
            cont=cont+1

          end
          
          if cont==2
            
            return false
            
          end
        }
        return true
        
      end
      @visibleTreasures.each{ |x|
        if x.type==t.type
          return false
        end
      }
      
      return true
      
    end
  
    def howManyVisibleTreasures(tKind)
      nTreasures=0
      i=0
      while(i<@visibleTreasures.size())
        if(@visibleTreasures[i].type==tKind)
          nTreasures+=1
        end
        i+=1
      end
      return nTreasures
    end
    
    
public

    def isDead
      if @dead
        return true
      else
        return false
      end
    end
  
    def combat( m )

    
      myLevel=getCombatLevel()
      monsterLevel=m.getCombatLevel()
      puts "Mi nivel= "+ myLevel.to_s + "  Monstruo=" + monsterLevel.to_s
        if(myLevel>monsterLevel) 
            applyPrize(m)
            
            if(@level>=10)
                combatResult=CombatResult::WinAndWinGame
            else 
                combatResult=CombatResult::Win
            end
           
        
        else 
            dice=Dice.instance
            escape=dice.nextNumber()
            if(escape<5)  
                amIDead=m.kills()
                if(amIDead)
                    die()
                    combatResult=CombatResult::LoseAndDie
                
                else
                    bad= m.getBadConsequence()
                    combatResult=CombatResult::Lose
                    applyBadConsequence(bad)
                    
                end
                    
                
            
            else combatResult=CombatResult::LoseAndEscape
            end
        
        end
        
        discardNecklaceIfVisible()
        return combatResult
    end
      
      
    
  
    def makeTreasureVisible( t )
      puts t.to_s   
      canI=canMakeTreasureVisible(t)
      if canI     
        @visibleTreasures << t
        @hiddenTreasures.delete(t)
        
      end
      
    end
  
    def discardVisibleTreasure( t )
      
      @visibleTreasures.delete(t)
      if((@pendingBadConsequence!=nil) and (!@pendingBadConsequence.isEmpty()) )
            @pendingBadConsequence.substractVisibleTreasure(t);      
      end
        dieIfNoTreasures();
      
    end
  
    def discardHiddenTreasure( t )
      @hiddenTreasures.delete(t)
      if((@pendingBadConsequence!=nil) and (!@pendingBadConsequence.isEmpty()) )          
            @pendingBadConsequence.substractHiddenTreasure(t);
      end
        dieIfNoTreasures();
      
    end
  
    def buyLevels( visible, hidden)
      
      levelsMayBought=computeGoldCoinsValue(visible)
      levelsMayBought=levelsMayBought+computeGoldCoinsValue(hidden);
      levels=levelsMayBought/1000
      canI=canIBuyLevels(levels)
        
      if(canI)
          incrementLevels(levels)
      end
      
      visible.each{ |x| @visibleTreasures.delete(x)}
      hidden.each{ |x| @hiddenTreasures.delete(x) }
        
      dealer=CardDealer.instance();
      visible.each{ |treasure| dealer.giveTreasureBack(treasure)}
      hidden.each{ |treasure| dealer.giveTreasureBack(treasure)}
      
      return canI
      
    end
    
    def validState
      if ( @pendingBadConsequence==nil or (@pendingBadConsequence.isEmpty() and @hiddenTreasures.size()<=4))
          if(@hiddenTreasures.size()>4)
            return false
          end
        return true
      else return false
      end
    end 
  
    def initTreasures
      
      dealer=CardDealer.instance
      dice=Dice.instance
      bringToLife
      treasure=dealer.nextTreasure
      hiddenTreasures << treasure
      number=dice.nextNumber
      puts "El dado a sacado= " + number.to_s
      if(number>1)
        treasure=dealer.nextTreasure
        hiddenTreasures << treasure
      end
      if(number==6)
        treasure=dealer.nextTreasure
        hiddenTreasures << treasure
      end

    end
  
    def hasVisibleTreasures
      if @visibleTreasures.size()>0
        return true
      else return false
      end
      
    end


  
  

  
end

  

end