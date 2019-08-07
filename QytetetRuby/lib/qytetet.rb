#encoding: utf-8
require "singleton"
require_relative "sorpresa"
require_relative "jugador"
require_relative "tipo_sorpresa"
require_relative "tipo_casilla"
require_relative "tablero"
require_relative "casilla"
require_relative "titulo_propiedad"
require_relative "metodo_salir_carcel"
require_relative "dado"

module ModeloQytetet
  class Qytetet
    include Singleton
    
    attr_accessor :cartaActual, :jugadorActual , :MAX_JUGADORES
    attr_accessor :MAX_CARTAS, :MAX_CASILLAS, :PRECIO_LIBERTAD, :SALDO_SALIDA
    attr_reader :jugadores, :tablero, :mazo
    
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 10
    @@MAX_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    
    def initialize
#      @jugadores = Array.new
#      @cartaActual = nil
#      @dado = Dado.instance
#      @tablero = Tablero.new
    end
    
    
    def aplicar_sorpresa
      tiene_propietario = false
      valor = @cartaActual.valor
      
      if @cartaActual.tipo == TipoSorpresa::PAGARCOBRAR then
        @jugadorActual.modificar_saldo(valor)
        
        if valor > 0 then
          puts("¡Has recibido dinero! --> " + valor.to_s)
        else
          puts("Has perdido dinero --> #{valor}")
        end
        
      elsif @cartaActual.tipo == TipoSorpresa::IRACASILLA then 
        es_carcel = @tablero.es_casilla_carcel(valor)
        
        if es_carcel then
          encarcelar_jugador
          puts("Vas a la cárcel")
        else
          nueva_casilla = @tablero.obtener_casilla_numero(valor, desplazamiento)
          tiene_propietario = @jugadorActual.actualizar_posicion(nueva_casilla)
          puts("Vas a la casilla #{valor}")
        end
      elsif @cartaActual.tipo == TipoSorpresa::PORCASAHOTEL then
        @jugadorActual.pagar_cobrar_por_casa_y_hotel(valor)
        
        if valor > 0 then
          puts("Ganas #{valor}")
        else
          puts("Pierdes #{valor}")
        end
      elsif @cartaActual.tipo == TipoSorpresa::PORJUGADOR then
        @jugadores.each do |jugador|
          if jugador != @jugadorActual then
            jugador.modificar_saldo(valor)
            @jugadorActual.modificar_saldo(-valor)
          end
        end
      elsif @cartaActual.tipo == TipoSorpresa::PORJUGADOR then
        @jugadorActual.convertirme(valor)
        
        if valor > 0 then
          puts("Ganas #{valor}")
        else
          puts("Pierdes #{valor}")
        end
      end
      
      if @cartaActual.tipo == TipoSorpresa::SALIRCARCEL then
        @jugadorActual.set_carta_libertad(@cartaActual)
        puts("Recibes una carta de libertad")
      else
        @mazo << @cartaActual
      end
      
      tiene_propietario
    end
    
    def cancelar_hipoteca(casilla)
      puedo_cancelar_hipoteca = @jugadorActual.puedo_pagar_hipoteca(casilla)
      
      puedo_cancelar_hipoteca
    end
    
    def comprar_titulo_propiedad
      puedo_comprar = @jugadorActual.comprar_titulo
    
      puedo_comprar
    end
    
    def edificar_casa(casilla)
      puedo_edificar = false
      coste_edificar_casa = 0
      
      if casilla.soy_edificable then
        se_puede_edificar = casilla.se_puede_edificar_casa(@jugadorActual.get_factor_especulador)
        
        if se_puede_edificar then
          puedo_edificar = @jugadorActual.puedo_edificar_casa(casilla)
          
          if puedo_edificar then
            coste_edificar_casa = casilla.edificar_casa
            @jugadorActual.modificar_saldo(-coste_edificar_casa)
          end
        end
      end
      
      puedo_edificar_casa = puedo_edificar
      
      puedo_edificar_casa
    end
    
    def edificar_hotel(casilla)
      puedo_edificar = false
      coste_edificar_hotel = 0
      
      if casilla.soy_edificable then
        se_puede_edificar = casilla.se_puede_edificar_hotel(@jugadorActual.get_factor_especulador)
        
        if se_puede_edificar then
          puedo_edificar = @jugadorActual.puedo_edificar_hotel(casilla)
          
          if puedo_edificar then
            coste_edificar_hotel = casilla.edificar_hotel
            @jugadorActual.modificar_saldo(-coste_edificar_hotel)
          end
        end
      end
      
      puedo_edificar_hotel = puedo_edificar
      
      puedo_edificar_hotel
    end
    
    def get_carta_actual
      @cartaActual
    end
    
    def get_jugador_actual
      @jugadorActual
    end
    
    def get_jugadores
      @jugadores
    end
        
    def hipotecar_propiedad(casilla)
      puedo_hipotecar = false
      
      if casilla.soy_edificable then
        se_puede_hipotecar = !casilla.esta_hipotecada
        
        if se_puede_hipotecar then
          puedo_hipotecar = @jugadorActual.puedo_hipotecar(casilla)
          
          if puedo_hipotecar then
            cantidad_recibida = casilla.hipotecar
            @jugadorActual.modificar_saldo(cantidad_recibida)
          end
        end
      end
      
      puedo_hipotecar_propiedad = puedo_hipotecar
      
      puedo_hipotecar_propiedad
    end
    
    def inicializar_juego(nombres)
      @jugadores = Array.new
      @cartaActual = nil
      @dado = Dado.instance
      @tablero = Tablero.new
      
      inicializar_jugadores(nombres)
      inicializar_cartas_sorpresa
      inicializar_tablero
      salida_jugadores
      @jugadorActual = @jugadores.at(0)
      @cartaActual = @jugadorActual
    end
    
    def intentar_salir_carcel(metodo)
      libre = false
      tengo_saldo = false
      valor_dado = 0
        
      if metodo == MetodoSalirCarcel::TIRANDODADO then
        valor_dado = @dado.tirar
        libre = valor_dado > 5
        puts("Valor del dado: #{valor_dado}")
      elsif metodo == MetodoSalirCarcel::PAGANDOLIBERTAD then
        tengo_saldo = @jugadorActual.pagar_libertad(200)
        libre = tengo_saldo
      end
      
      if libre then
        @jugadorActual.set_encarcelado(false)
        puts("Te vas de la cárcel.")
      else
        puts("Te quedas un rato más en la cárcel.")
      end
      
      libre
    end
    
    def jugar
      valor_dado = 0
      tiene_propietario = false
      
      valor_dado = @dado.tirar
      casilla_posicion = @jugadorActual.casillaActual
      nueva_casilla = @tablero.obtener_nueva_casilla(casilla_posicion, valor_dado)
      tiene_propietario = @jugadorActual.actualizar_posicion(nueva_casilla)
      
      puts("Valor del dado: #{valor_dado}")
      puts("Avanza hasta: #{nueva_casilla.to_s}")
      
      puts ("tiene_propietario = #{tiene_propietario}")
      
      if !nueva_casilla.soy_edificable then
        if nueva_casilla.tipo == TipoCasilla::JUEZ then
          encarcelar_jugador
        elsif nueva_casilla.tipo == TipoCasilla::SORPRESA then
          @cartaActual = @mazo.at(0)
          @mazo.delete(0)
        end
      end
      
      tiene_propietario
    end
    
    def obtener_ranking
      ranking = List.new
      capital = 0
      
      @jugadores.each { |jugador|
        capital = jugador.obtener_capital
        ranking << jugador.nombre
        ranking << capital
      }
      
      ranking
    end
    
    def propiedades_hipotecadas_jugador(hipotecadas)
      propiedades = Array.new
      casillas = Array.new
      propiedades = @jugadorActual.obtener_propiedades_hipotecadas(hipotecadas)
      
      propiedades.each { |t|  
        casillas << t.casilla
      }
      
      casillas
    end
    
    def siguiente_jugador
      indice = @jugadores.index(@jugadorActual)
      
      if indice == (@jugadores.length - 1) then
        indice = 0
      else
        indice = indice + 1
      end
      
      res = @jugadores.at(indice)
      
      @jugadorActual = res
      
      res
    end
    
    def vender_propiedad(casilla)
      puedo_vender = false
      
      if casilla.soy_edificable then
        puedo_vender = @jugadorActual.puedo_vender_propiedad(casilla)
        
        if puedo_vender then
          @jugadorActual.vender_propiedad(casilla)
        end
      end
      
      puedo_vender
    end
    
    def encarcelar_jugador
      if !@jugadorActual.tengo_carta_libertad then
        casilla_carcel = @tablero.get_carcel
        @jugadorActual.ir_a_carcel(casilla_carcel)
      
      else
        carta = @jugadorActual.devolver_carta_libertad
        @mazo << carta
      end
    end
    
    def inicializar_cartas_sorpresa
      @mazo = Array.new
      @mazo = []
      t = Tablero.new
      @mazo<< Sorpresa.new("Te hemos pillado con chanclas y calcetines, lo  sentimos, debes ir a la carcel", 9, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Pagas por la sorpresa",-1500, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Ganas por la sorpresa",3000, TipoSorpresa::PAGARCOBRAR)
      @mazo<< Sorpresa.new("Vas a la casilla porque...",5, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Vas a la carcel por corrupto", t.get_carcel.get_numero_casilla, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Pagas por tener tanto y ganar tan poco",2000, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Te conviertes en Especulador",5000, TipoSorpresa::CONVERTIRME)
      @mazo<< Sorpresa.new("A pagar impuestos",1000, TipoSorpresa::PORCASAHOTEL)
      @mazo<< Sorpresa.new("Tus amigos estan generosos",300, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Le debes pasta a tus colegas",-250, TipoSorpresa::PORJUGADOR)
      @mazo<< Sorpresa.new("Vas a la casilla porque...",15, TipoSorpresa::IRACASILLA)
      @mazo<< Sorpresa.new("Una pena que te vallas, sigue asi y volveras",0, TipoSorpresa::SALIRCARCEL)
      @mazo<< Sorpresa.new("Te conviertes en Especulador",3000, TipoSorpresa::CONVERTIRME)

      #puts("Inicialicé cartas sorpresa")
    end
        
    def inicializar_jugadores(nombres)
      #puts("Voy a inicializar jugadores")
      @jugadores = []
      
      i = 0
      while i < nombres.length do
        @jugadores << Jugador.new_jugador(nombres.at(i))
        i = i + 1
      end 
      
      #puts("Inicialicé jugadores")
    end
    
    def inicializar_tablero
      @tablero = Tablero.new
      #puts("Inicialicé el tablero")
    end
    
    def salida_jugadores
      @jugadores.each do |j| 
        j.set_casilla_actual(Casilla.new(0, TipoCasilla::SALIDA))
        
      end
      #puts("Jugadores en casilla de salida")
    end
    
    def get_jugadores
      @jugadores
    end
    
    def max_jugadores
      @@MAX_JUGADORES
    end
    
    private :encarcelar_jugador, :inicializar_cartas_sorpresa, :inicializar_jugadores, :inicializar_tablero, :salida_jugadores
  end
end
