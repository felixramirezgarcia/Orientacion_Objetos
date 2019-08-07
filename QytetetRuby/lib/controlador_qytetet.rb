require_relative "metodo_salir_carcel"
require_relative "qytetet"
require_relative "jugador"
require_relative "casilla"
require_relative "titulo_propiedad"
require_relative "vista_textual_qytetet"

module InterfazTextualQytetet
  class ControladorQytetet
    include ModeloQytetet
    
    def initialize
      @vista = VistaTextualQytetet.new
      @juego = Qytetet.instance
      #@jugador = @juego.jugadorActual
      #@casilla = Casilla.new_no_calle(0, TipoCasilla::SALIDA)
      #@casilla = @jugador.casilla_actual #¿POR QUÉ DA ERROR AQUÍ?
    end
    
    def desarrollo_juego
      bancarrota = false
      encarcelado = false
      elegir_quiero_comprar = false
      tengo_propiedades = false
      metodo_salir = nil
      #propiedades_jugador = @jugador.propiedades
      casilla_propiedades = Array.new
      
      @vista.mostrar("Turno de #{@jugador.nombre}")
      @vista.mostrar("Posicion: #{@casilla.to_s}")
      @vista.mostrar("Cantidad de propiedades: #{@jugador.propiedades}")
      
      while !bancarrota do
        encarcelado = @jugador.encarcelado
        #propiedades_jugador = @jugador.propiedades
        
        if encarcelado then
          metodo = @vista.menu_salir_carcel
          
          if metodo == 0 then
            metodo_salir = MetodoSalirCarcel::TIRANDODADO
          elsif metodo == 1 then
            metodo_salir = MetodoSalirCarcel::PAGANDOLIBERTAD
          end
          
          libre = @juego.intentar_salir_carcel(metodo_salir)
          
          encarcelado = !libre
        end
        
        if !encarcelado then
          no_tiene_propietario = !@juego.jugar
          @casilla = @jugador.casillaActual
          bancarrota = @jugador.saldo <= 0

          if !bancarrota then
            encarcelado = @jugador.encarcelado
            if !encarcelado then
              if @casilla.tipo == TipoCasilla::SORPRESA then
                no_tiene_propietario = @juego.aplicar_sorpresa
                encarcelado = @jugador.encarcelado
                if !encarcelado then
                  bancarrota = @jugador.saldo <= 0
                  if !bancarrota then
                    if @casilla.tipo == TipoCasilla::CALLE then
                      puts("no_tiene_propietario = #{no_tiene_propietario}")
                      if no_tiene_propietario then
                        elegir_quiero_comprar = @vista.elegir_quiero_comprar
                        if elegir_quiero_comprar then
                          @juego.comprar_titulo_propiedad
                        end
                      end
                    end
                  end
                end
              elsif @casilla.tipo == TipoCasilla::CALLE then
                puts("no_tiene_propietario = #{no_tiene_propietario}")
                if no_tiene_propietario then
                  elegir_quiero_comprar = @vista.elegir_quiero_comprar
                  if elegir_quiero_comprar then
                    @juego.comprar_titulo_propiedad
                  end
                end
              end
              
              tengo_propiedades = @jugador.tengo_propiedades
              @jugador = @juego.jugadorActual
              if !encarcelado and !bancarrota and tengo_propiedades then
                @jugador.propiedades.each do |t|
                  casilla_propiedades << t.casilla
                  #puts("numeroCasilla: #{t.casilla.numeroCasilla}")
                end

                opcion = @vista.menu_gestion_inmobiliaria
                
                if opcion > 0 then
                  @casilla = elegir_propiedad(casilla_propiedades)

                  if opcion == 1 then
                    @juego.edificar_casa(@casilla)
                  elsif opcion == 2 then
                    @juego.edificar_hotel(@casilla)
                  elsif opcion == 3 then
                    @juego.vender_propiedad(@casilla)
                  elsif opcion == 4 then
                    @juego.hipotecar_propiedad(@casilla)
                  elsif opcion == 5 then
                    @juego.cancelar_hipoteca(@casilla)
                  end
                end
                casilla_propiedades.clear

              end
            end
          end
        end

        bancarrota = @jugador.saldo <= 0

        if !bancarrota then
          @vista.mostrar("Termina #{@jugador.nombre}")

          @jugador = @juego.siguiente_jugador
          @casilla = @jugador.casillaActual

          @vista.mostrar("\n\nTurno de #{@jugador.nombre}")
          @vista.mostrar(@jugador.to_s)
          @vista.mostrar("Posicion: #{@casilla.to_s}")
          @vista.mostrar("Cantidad propiedades: #{@jugador.propiedades.length}")

          gets.chomp #pausa
        else
          @juego.obtener_ranking
          break
        end
      end
      
    end
    
    def inicializacion_juego
#      @juego = Qytetet.instance
      
      puts("Introduce los nombres de los jugadores\n")
      
      @juego.inicializar_juego(@vista.obtener_nombre_jugadores)
      
      @jugador = @juego.jugadorActual
      @casilla = @jugador.casillaActual
      
      puts("Vista del tablero: \n")
      @vista.mostrar(@juego.tablero.to_s)
      @vista.mostrar(@juego.mazo.to_s)
      @vista.mostrar(@jugador.to_s)
      @vista.mostrar(@casilla.to_s)
      
    end
    
    def elegir_propiedad(propiedades) # lista de propiedades a elegir
      @vista.mostrar("\tCasilla\tTitulo");

      lista_propiedades= Array.new
      
      for prop in propiedades  # crea una lista de strings con numeros y nombres de propiedades
        prop_string= prop.numeroCasilla.to_s+' '+prop.titulo.nombre; 
              lista_propiedades<<prop_string
      end
#      i = 0
#      while i < propiedades.length do
#        prop_string = propiedades.at(i).numeroCasilla+' '+propiedades.at(i).titulo.nombre
#        lista_propiedades << prop_string
#        i = i+1
#      end
      
      seleccion=@vista.menu_elegir_propiedad(lista_propiedades)  # elige de esa lista del menu
      
      propiedades.at(seleccion)
    end
    
    def self.main
      c = ControladorQytetet.new
      
      c.inicializacion_juego
      c.desarrollo_juego
    end
    
    private :elegir_propiedad
  end
  
  ControladorQytetet.main
end
