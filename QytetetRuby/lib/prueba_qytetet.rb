#encoding: utf-8
require_relative "sorpresa"
require_relative "tipo_sorpresa"
require_relative "tablero"
require_relative "casilla"

module ModeloQytetet
  class PruebaQytetet
    @@mazo = Array.new
    
    
    def sorpresas_mayor0
      res = Array.new;
      
      @@mazo.each { |s| 
        if s.valor > 0 then
          res << s
        end
        
      }
      return res
    end
    
    def ir_a_casilla
      res = Array.new
        
      @@mazo.each { |s|
        if s.tipo == [TipoSorpresa::IRACASILLA] then
          res << s
        end
      }

      return res
    end
    
    def tipo_sorpresa(t)
      res = Array.new
        
      @@mazo.each { |s|  
        if s.tipo == t then
          res << s
        end
      }
  
      return res
    end
    
    def inicializar_sorpresas
      @@mazo = []
      t = Tablero.new
      @@mazo<< Sorpresa.new("Te hemos pillado con chanclas y calcetines, lo  sentimos, debes ir a la carcel", 9, [TipoSorpresa::IRACASILLA])
      @@mazo<< Sorpresa.new("Pagas por la sorpresa",-1500, [TipoSorpresa::PAGARCOBRAR])
      @@mazo<< Sorpresa.new("Ganas por la sorpresa",3000, [TipoSorpresa::PAGARCOBRAR])
      @@mazo<< Sorpresa.new("Vas a la casilla porque...",5, [TipoSorpresa::IRACASILLA])
      @@mazo<< Sorpresa.new("Vas a la carcel por corrupto", t.get_carcel.get_numero_casilla, [TipoSorpresa::IRACASILLA])
      @@mazo<< Sorpresa.new("Pagas por tener tanto y ganar tan poco",2000, [TipoSorpresa::PORCASAHOTEL])
      @@mazo<< Sorpresa.new("A pagar impuestos",1000, [TipoSorpresa::PORCASAHOTEL])
      @@mazo<< Sorpresa.new("Tus amigos estan generosos",300, [TipoSorpresa::PORJUGADOR])
      @@mazo<< Sorpresa.new("Le debes pasta a tus colegas",-250, [TipoSorpresa::PORJUGADOR])
      @@mazo<< Sorpresa.new("Vas a la casilla porque...",15, [TipoSorpresa::IRACASILLA])
      @@mazo<< Sorpresa.new("Una pena que te vallas, sigue asi y volveras",0, [TipoSorpresa::SALIRCARCEL])
    end
  
  
  def self.main
    prueba = PruebaQytetet.new
    
    prueba.inicializar_sorpresas
    
    sorpresas_casilla = prueba.ir_a_casilla
    mayor0 = prueba.sorpresas_mayor0
    salir_carcel = prueba.tipo_sorpresa([TipoSorpresa::SALIRCARCEL])
    
#    puts "ir a casilla"
#    puts sorpresas_casilla
#    puts "mayor que 0"
#    puts mayor0
    #puts "salir de la carcel"
    #puts salir_carcel
    
    t = Tablero.new
    t.inicializar
    
    #muestro las casillas de todo el tablero
    #puts t.to_s
    
    v = Array.new
    v << 3
    v << 5
    v << 9
    
    puts "accedo al contenido en la posición 2:"
    
    puts v[2]
    
    puts "Accedo a la posición de la cárcel:"
    
    puts t.get_carcel.get_numero_casilla
  end
end

  PruebaQytetet.main
end
  

