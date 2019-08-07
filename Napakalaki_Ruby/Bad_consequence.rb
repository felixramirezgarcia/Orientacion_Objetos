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
  

class BadConsequence
  
  attr_reader :levels, :nVisibleTreasures, :nHiddenTreasures, :specificHiddenTreasures, :specificVisibleTreasures
  private_class_method :new
  

  
  def initialize( aText, someLevels, someVisibleTreasures, someHiddenTreasures, someSpecificVisibleTreasures,
                  someSpecificHiddenTreasures, death)
    @text=aText
    @levels=someLevels
    @nVisibleTreasures=someVisibleTreasures
    @nHiddenTreasures=someHiddenTreasures
    @specificVisibleTreasures=someSpecificVisibleTreasures
    @specificHiddenTreasures=someSpecificHiddenTreasures
    @death=death
  end
  
  def  getLevels
    return @levels
  end
  
  def getNVisibleTreasures
    return @nVisibleTreasures
  end
  
  def getNHiddenTreasures
    return @nHiddenTreasures
  end
  
  def getSpecificHiddenTreasures
    return @specificHiddenTreasures
  end
  
  def getSpecificVisibleTreasures
    return @specificVisibleTreasures
  end
  
  def self.newLevelNumberOfTreasures(aText,someLevels,someVisibleTreasures,someHiddenTreasures)
    new(aText, someLevels, someVisibleTreasures, someHiddenTreasures, Array.new, Array.new, false)
  end

  def self.newLevelSpecificTreasures(aText, someLevels, someSpecificVisibleTreasures, someSpecificHiddenTreasures)
    new(aText, someLevels, -1, -1, someSpecificVisibleTreasures, someSpecificHiddenTreasures, false) 
  end

  def self.newDeath(aText)
    new(aText, -1, -1, -1, Array.new , Array.new, true)
  end
  
  def isEmpty
    return ((@nVisibleTreasures<1 and @nHiddenTreasures<1) and ( @specificVisibleTreasures.empty? and @specificHiddenTreasures.empty?))  
  end
  
  
  def substractVisibleTreasure (t)
      if(@nVisibleTreasures<0)
        if t.getType!=TreasureKind::ONEHAND        
        @specificVisibleTreasures.delete(t.getType())
        else
        i=0
        while(@specificVisibleTreasures.at(i)!=TreasureKind::ONEHAND and i<@specificVisibleTreasures.size)
        i=i+1
        end
        if(i<@specificVisibleTreasures.size)
          @specificVisibleTreasures.delete_at(i)
        end
        end
      else
      @nVisibleTreasures=@nVisibleTreasures-1
      end
    
  end

  def substractHiddenTreasure (t)
      if(@nHiddenTreasures<0)
        if t.getType!=TreasureKind::ONEHAND
        @specificHiddenTreasures.delete(t.getType())
        else
        i=0
        while(@specificHiddenTreasures.at(i)!=TreasureKind::ONEHAND and i<@specificHiddenTreasures.size)
        i=i+1
        end
        if(i<@specificHiddenTreasures.size)
          @specificHiddenTreasures.delete_at(i)
        end
        end
      else
      @nHiddenTreasures=@nHiddenTreasures-1
      end    
    
  end
  
  def adjustToFitTreasureLists( v, h)        
        aux_visible=Array.new
        aux_hidden=Array.new
        if(!@specificVisibleTreasures.empty? or !@specificHiddenTreasures.empty?)
        v.each{|treasure|
            @specificVisibleTreasures.each{|specificTreasure|
                if(treasure.getType()==specificTreasure)
                    aux_visible << specificTreasure

                end
            }
        }
        h.each{ |treasure|
            @specificHiddenTreasures.each{|specificTreasure|
                if(treasure.getType()==specificTreasure)
                    aux_hidden << specificTreasure
                end
            }
        }
        elsif (@nVisibleTreasures>0 or @nHiddenTreasures>0)
          size=v.size()
          i=0
          while(i<size and i<@nVisibleTreasures)
            aux_visible << v[i].getType()
            i=i+1
          end
          size=h.size()
          i=0
          while(i<size and i<@nHiddenTreasures)
            aux_hidden << h[i].getType()
            i=i+1
          end          
          
        end
        

        
        bad=BadConsequence.newLevelSpecificTreasures("",0,aux_visible,aux_hidden);
        return bad;
  end


    
  
  def myBadConsequenceIsDeath
    if(@death==true)
      return true
    else return false
    end
  end
  
  def to_s
    return "Text= #@text  Levels= #@levels  nVisibleTreasures=#@nVisibleTreasures  nHiddenTreasures=#@nHiddenTreasures  " +
     "death=#@death specificHiddenTreasures=#{@specificHiddenTreasures}  " +
     "specificVisibleTreasures=#{@specificVisibleTreasures}"
  end
  



end


end
