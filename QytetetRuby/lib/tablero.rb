#encoding: utf-8
require_relative "casilla"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "calle"

module ModeloQytetet
  attr_reader :carcel
  class Tablero
    def initialize
      @casillas = Array.new
      inicializar
    end
    
    def es_casilla_carcel(numeroCasilla)
      @carcel.get_numero_casilla == numeroCasilla
    end
    
    def get_carcel
      @carcel
    end
    
    def obtener_casilla_numero(numeroCasilla)
      res = Casilla.new(0, nil)
      
      @casillas.each do |c|
        if c.get_numero_casilla == numeroCasilla then
          res = c
        end
      end
      
      res
    end
    
    def obtener_nueva_casilla(casilla, desplazamiento)
      num = casilla.get_numero_casilla + desplazamiento
      res = Casilla.new(0, nil)
      if num > 19 then
        num = num - 20
      end
      
      @casillas.each do |c| 
        if c.get_numero_casilla == num then
          res = c
        end
        
      end
      
      res
      
    end
    
    def inicializar
      @casillas<<(Casilla.new(0, TipoCasilla::SALIDA))
      @casillas<<(Calle.new(1, 200, TipoCasilla::CALLE, TituloPropiedad.new("El perro andaluz", 50, 10, 150, 250)))
      @casillas<<(Calle.new(2, 250,TipoCasilla::CALLE, TituloPropiedad.new("La guarida del lobo",55,11,200,270)))
      @casillas<<(Casilla.new(3, TipoCasilla::IMPUESTO))
      @casillas<<(Calle.new(4, 300,TipoCasilla::CALLE, TituloPropiedad.new("Sawarma Boabdil",60,12,300,290)))
      @casillas<<(Casilla.new(5, TipoCasilla::PARKING))
      @casillas<<(Calle.new(6, 300, TipoCasilla::CALLE, TituloPropiedad.new("Alimentacion Estudiante",65,13,350,310)))
      @casillas<<(Casilla.new(7, TipoCasilla::SORPRESA))
      @casillas<<(Calle.new(8, 350, TipoCasilla::CALLE, TituloPropiedad.new("Sawarma Asador Real",65,14,450,350)))
      @casillas<<(Casilla.new(9, TipoCasilla::CARCEL))
      @casillas<<(Calle.new(10, 400, TipoCasilla::CALLE, TituloPropiedad.new("Pub la rocka",70,15,550,400)))
      @casillas<<(Calle.new(11, 450, TipoCasilla::CALLE, TituloPropiedad.new("Bazar china city",75,16,650,430)))
      @casillas<<(Casilla.new(12, TipoCasilla::SORPRESA))
      @casillas<<(Calle.new(13, 450, TipoCasilla::CALLE, TituloPropiedad.new("Sawarma poco loco",80,17,750,460)))
      @casillas<<(Calle.new(14, 500, TipoCasilla::CALLE, TituloPropiedad.new("Alimentacion Pekin",85,18,850,500)))
      @casillas<<(Casilla.new(15, TipoCasilla::JUEZ))
      @casillas<<(Calle.new(16, 550, TipoCasilla::CALLE, TituloPropiedad.new("The sawarma king",90,19,950,580)))
      @casillas<<(Calle.new(17, 600, TipoCasilla::CALLE, TituloPropiedad.new("Pub Lscandalo",95,20,1000,650)))
      @casillas<<(Casilla.new(18, TipoCasilla::SORPRESA))
      @casillas<<(Calle.new(19, 700, TipoCasilla::CALLE, TituloPropiedad.new("Chupiteria 69",100,20,1000,750)))
      
      @carcel = @casillas[9]
      
      @casillas.each do |c| 
        if c.tipo == TipoCasilla::CALLE then
           c.asignar_titulo_propiedad
        end
      end
    end
    
    def get_carcel
      @carcel
    end
    
    def es_casilla_carcel(numero_casilla)
      @carcel.get_numero_casilla == numero_casilla
    end
    
    def to_s
#      "Tablero{ casillas= #{@casillas.to_s}, \n carcel= #{@carcel} }\n"
      @casillas.each do |c|  
        puts c.to_s
      end
    end
    
    private :inicializar
  end
end
