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
require "singleton"

class CardDealer
  include Singleton
  def initialize
    @usedMonsters=Array.new
    @unusedMonsters=Array.new
    @usedTreasures=Array.new
    @unusedTreasures=Array.new
  end
  
  private
    def initTreasureCardDeck
      @unusedTreasures << Treasure.new("Necrocomicón", 100, 1, 1, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("Necronomicón", 800, 5, 7, TreasureKind::BOTHHAND)
      @unusedTreasures << Treasure.new("Linterna a 2 manos", 400, 3, 6, TreasureKind::BOTHHAND)
      @unusedTreasures << Treasure.new("Necrognomicón", 200, 2, 4, TreasureKind::ONEHAND)
      @unusedTreasures << Treasure.new("¡Sí mi amo!", 0, 4, 7, TreasureKind::HELMET)
      @unusedTreasures <<Treasure.new("Botas de investigación", 600, 3, 4, TreasureKind::SHOE)
      @unusedTreasures <<Treasure.new("Capucha de Cthulhu", 500, 3, 5, TreasureKind::HELMET)
        @unusedTreasures << Treasure.new("A prueba de babas", 400, 2, 5, TreasureKind::ARMOR)
        @unusedTreasures << Treasure.new("Ametralladora Thompson", 600, 4, 8, TreasureKind::BOTHHAND)
        @unusedTreasures << Treasure.new("Camiseta de la UGR", 100, 1, 7, TreasureKind::ARMOR)
        @unusedTreasures << Treasure.new("Clavo de rail ferroviario", 400, 3, 6, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("Fez alópodo", 700, 3, 5, TreasureKind::HELMET)
        @unusedTreasures << Treasure.new("Hacha prehistórica", 500, 2, 5, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind::ARMOR)
        @unusedTreasures << Treasure.new("Gaita", 500, 4, 5, TreasureKind::BOTHHAND)
        @unusedTreasures << Treasure.new("Insecticida", 300, 2, 3, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("Escopeta de 3 cañones", 700, 4, 6, TreasureKind::BOTHHAND)
        @unusedTreasures << Treasure.new("Garabato místico", 300, 2, 2, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("La fuerza de Mr. T", 1000, 0, 0, TreasureKind::NECKLACE)
        @unusedTreasures << Treasure.new("La rebeca metálica", 400, 2, 3, TreasureKind::ARMOR)
        @unusedTreasures << Treasure.new("Mazo de los antiguos", 200, 3, 4, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("Necroplayboycóm", 300, 3, 5, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("Lanzallamas", 800, 4, 8, TreasureKind::BOTHHAND)
        @unusedTreasures << Treasure.new("Necrotelecom", 300, 2, 3, TreasureKind::HELMET)
        @unusedTreasures << Treasure.new("Porra preternatural", 200, 2, 3, TreasureKind::ONEHAND)
        @unusedTreasures << Treasure.new("Tentáculo de pega", 200, 0, 1, TreasureKind::HELMET)
        @unusedTreasures << Treasure.new("Zapato deja-amigos", 500, 0, 1, TreasureKind::SHOE)
        @unusedTreasures << Treasure.new("Shogulador", 600, 1, 1, TreasureKind::BOTHHAND)
        @unusedTreasures << Treasure.new("Varita de atizamiento", 400, 3, 4, TreasureKind::ONEHAND)
    end
    
    def initMonsterCardDeck
    bc=BadConsequence.newLevelNumberOfTreasures("Mal rollo: Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0)
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("El lenguas", 20, bc, price)
      
    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Pierdes tu armadura visible y otra oculta", 0, [TreasureKind::ARMOR], [TreasureKind::ARMOR] )
    price=Prize.new(2, 1)
    @unusedMonsters << Monster.new("3 Byakhees de bonanza", 8, bc, price)


    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Embobados con el lindo primigenio te descartas de tu casco visible", 0,
     [TreasureKind::HELMET], [])
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("Chibithulhu", 2, bc, price)

    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: El primordial bostezo contagioso. Pierdes el calzado visible.", 0,
    [TreasureKind::SHOE], [])
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("El sopor de Dunwich", 2, bc, price)

    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta", 0,
    [TreasureKind::ONEHAND], [[TreasureKind::ONEHAND]])
    price=Prize.new(4,1)
    @unusedMonsters << Monster.new("Ángeles de la noche ibicenca", 14, bc, price)

    bc=BadConsequence.newLevelNumberOfTreasures("Mal rollo: Pierdes todos tus tesoros visibles", 0, 9999999999, 0 )
    price=Prize.new(3,1)
    @unusedMonsters << Monster.new("El gorrón en el umbral", 10, bc, price)


    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Pierdes la armadura visible", 0, [TreasureKind::ARMOR], [])
    price=Prize.new(2,1)
    @unusedMonsters << Monster.new("H.P. Muchcraft", 6, bc, price)

    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Sientes bichos bajo la ropa. Descarta la armadura visible", 0, [TreasureKind::ARMOR], [])
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("Bichgooth", 2, bc, price)

    bc=BadConsequence.newLevelNumberOfTreasures("Mal rollo: Pierdes 5 niveles y 3 tesoros visibles.", 5, 3, 0)
    price=Prize.new(4,2)
    @unusedMonsters << Monster.new("El rey de rosa", 13, bc, price)

    bc=BadConsequence.newLevelNumberOfTreasures("Mal rollo: Toses los pulmones y pierdes 2 niveles", 2, 0, 0)
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("La que redacta en las tinieblas", 2, bc, price)

    bc=BadConsequence.newDeath("Mal rollo: Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estas muerto")
    price=Prize.new(2,1)
    @unusedMonsters << Monster.new("Los hondos", 8, bc, price)

    bc=BadConsequence.newLevelNumberOfTreasures("Mal rollo: Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2)
    price=Prize.new(2,1)
    @unusedMonsters << Monster.new("Semillas de Cthulhu", 4, bc, price)

    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Te intentas escaquear. Pierdes una mano visible", 0, [TreasureKind::ONEHAND], [])
    price=Prize.new(2,1)
    @unusedMonsters << Monster.new("Dameargo", 1, bc, price)

    bc=BadConsequence.newLevelNumberOfTreasures("Mal rollo: Da mucho asquito. Pierdes 3 niveles", 3, 0, 0)
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("Pollipólipo volante", 3, bc, price)

    bc=BadConsequence.newDeath("Mal rollo: No le hace gracia que pronuncien mal su nombre. Estas muerto")
    price=Prize.new(3,1)
    @unusedMonsters << Monster.new("Yskthtihyssg-Goth", 12, bc, price)

    bc=BadConsequence.newDeath("La familia te atrapa. Estás muerto")
    price=Prize.new(4,1)
    @unusedMonsters << Monster.new("Familia feliz", 1, bc, price)

    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: La quinta directiva te primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2, [TreasureKind::BOTHHAND], [])
    price=Prize.new(2,1)
    @unusedMonsters << Monster.new("Roboggoth", 8, bc, price)

    bc=BadConsequence.newLevelSpecificTreasures("Mal rollo: Te asusta en la noche. Pierdes un casco visible", 0, [TreasureKind::HELMET], [])
    price=Prize.new(1,1)
    @unusedMonsters << Monster.new("El espia", 5, bc, price)


    
    end
    
    def shuffleTreasures
      @unusedTreasures.shuffle!  
    end
    
    def shuffleMonsters
      @unusedMonsters.shuffle!      
    end
    
  public

    def nextTreasure
      t=@unusedTreasures.first
      @unusedTreasures.delete(@unusedTreasures.first)
      return t
    end
    
    def nextMonster
      t=@unusedMonsters.first
      @unusedMonsters.delete(@unusedMonsters.first)
      return t
    end
    
    def giveTreasureBack(t)
      @usedTreasures << t
      @unusedTreasures.delete(t)
    end
    
    def giveMonsterBack(m)
      @usedMonsters << m
      @unusedMonsters.delete(m)
    end
    
    def initCards
      initTreasureCardDeck
      initMonsterCardDeck
      shuffleTreasures
      shuffleMonsters
      
    end
    

    
end

end
