# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module NapakalakiGame
class Treasure
  attr_reader :name, :goldCoins, :minBonus, :maxBonus, :type
  def initialize (n, g, min ,max,t)
    @name=n
    @goldCoins=g
    @minBonus=min
    @maxBonus=max
    @type=t
  end
  
  def to_s
    return "name= #@name  goldCoins=#@goldCoins minBonus= #@minBonus " +
           "maxBonus= #@maxBonus  type=#@type"
  end  
  
  
  def getName
    return @name
  end
  
  def getGoldCoins
    return @goldCoins
  end
  
  def getMinBonus
    return @minBonus
  end
  
  def getMaxBonux
    return @maxBonus
  end
  
  def getType
    return @type
  end
end

end
