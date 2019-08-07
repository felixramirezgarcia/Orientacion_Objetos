require_relative "qytetet"
require_relative "tablero"
require_relative "casilla"
require_relative "titulo_propiedad"
require_relative "jugador"

module ModeloQytetet
  class PruebaQytetetP2

    def mostrar_tablero
      t = Tablero.new    
      t.inicializar
      
      for i in (0...20)
        puts t.obtener_casilla_numero(i).get_titulo
      end
    end
    
    def self.main
      p = PruebaQytetetP2.new
#      q = Qytetet.new   <-- MAL
#      q = Qytetet.instance #<-- BIEN
      j1 = Jugador.new_jugador("Pepe")
      j2 = Jugador.new_jugador("Paco")
      j3 = Jugador.new_jugador("Maricarmen")
      
      nombres = Array.new
      mazo_prueba = Array.new
      
      nombres = ["PablaO", "Abelardo", "Pancracio", "Burocracio"]
#      puts "voy a mostrar cada casilla del tablero: "
#
#      puts p.mostrar_tablero
      
      puts "Muestro a los jugadores: "
      
      puts j1.to_s
      puts j2.to_s
      puts j3.to_s
      
      puts "\nMuestro cartas sorpresa: "
      
      q.inicializar_cartas_sorpresa
      mazo_prueba = q.mazo
      
      mazo_prueba.each { |s| 
      
        puts s.to_s
      }
#      q.mostrar_cartas_sorpresa     método inventado para hacer pruebas
      
    end

  end
  PruebaQytetetP2.main
end