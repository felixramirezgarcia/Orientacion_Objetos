require_relative "tipo_casilla"
require_relative "casilla"
require_relative "titulo_propiedad"
require_relative "qytetet"

module ModeloQytetet
  class Jugador
    attr_accessor :nombre, :encarcelado, :saldo, :casillaActual, :propiedades,:cartaLibertad , :factorEspeculador
    
    @@factorEspeculador = 1
    def initialize(n,en,sal,cas,car,pro)
      @nombre = n
      @encarcelado = en
      @saldo = sal
      @casillaActual = cas
      @cartaLibertad = car
      @propiedades = pro
    end
    
    def self.new_jugador(n)
      self.new(n,false,7500,Casilla.new(0,TipoCasilla::SALIDA),nil,Array.new)
    end
    
    def self.new_especulador(jugador)
      self.new(jugador.nombre,jugador.encarcelado,jugador.saldo,jugador.casillaActual,jugador.cartaLibertad,jugador.propiedades)
    end
    
    def get_factor_especulador
      return @@factorEspeculador
    end

    def get_casilla_actual
      @casillaActual
    end

    def get_encarcelado
      @encarcelado
    end

    def tengo_propiedades
      @propiedades.length > 0
    end
    
    def convertirme(fianza)
       especulador = Especulador.new(self, fianza)
       return especulador;
    end

    def actualizar_posicion(casilla)
      if casilla.numeroCasilla < @casillaActual.numeroCasilla then
        modificar_saldo(1000)
      end
      
      tiene_propietario = false
      coste_alquiler = 0
      coste = 0
      
      set_casilla_actual(casilla)
      
      if casilla.soy_edificable then
        tiene_propietario = casilla.tengo_propietario
        
        if tiene_propietario then
          @encarcelado = casilla.propietario_encarcelado
          
          if !@encarcelado then
            coste_alquiler = casilla.cobrar_alquiler
            modificar_saldo(-coste_alquiler)
          end
        end
      else
        if casilla.tipo == TipoCasilla::IMPUESTO then
          coste = casilla.coste
          pagarImpuestos(coste)
          #modificar_saldo(-coste)
          puts("IMPUESTO --> Tu saldo se reduce en #{coste}")
        end
      end
    
      tiene_propietario
    end
    
    def pagarImpuestos(cantidad)  
         modificar_saldo(-cantidad)
    end

    def comprar_titulo
      puedo_comprar = false
      tengo_propietario = false
      coste_compra = 0
      titulo = TituloPropiedad.new(nil, nil, nil, nil, nil)
      
      if @casillaActual.soy_edificable then
        tengo_propietario = @casillaActual.tengo_propietario
        
        if !tengo_propietario then
          coste_compra = @casillaActual.coste
          
          if coste_compra <= @saldo then
            titulo = @casillaActual.asignar_propietario(self)
            @propiedades << titulo
            modificar_saldo(-coste_compra)
            puedo_comprar = true
          end
        end
      end
      
      puedo_comprar
    end

    def devolver_carta_libertad
      carta = @cartaLibertad
      @cartaLibertad = nil
      
      carta
    end

    def ir_a_carcel(casilla)
      set_casilla_actual(casilla)
      set_encarcelado(true)
    end

    def obtener_capital
      capital = 0
      valor_propiedades = 0
      
      @propiedades.each do |t|
        valor_propiedades = valor_propiedades + t.casilla.coste + cuantas_casas_hoteles_tengo * t.get_precio_edificar
        
        if t.hipotecada then
          valor_propiedades = valor_propiedades - t.hipotecaBase
        end
        
      end
      
      capital = @saldo + valor_propiedades
      
      capital
    end

    def obtener_propiedades_hipotecadas(hipotecada)
      hipotecadas = Array.new
      
      @propiedades.each do |t|
        if t.get_hipotecada then
          hipotecadas << t
        end
      end
      
      hipotecadas
    end

    def pagar_cobrar_por_casa_y_hotel(cantidad)
      numero_total = cuantas_casas_hoteles_tengo
      modificar_saldo(numero_total * cantidad)
    end

    def puedo_edificar_casa(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = false
      coste_edificar_casa = 0
      
      if es_mia and casilla.se_puede_edificar_casa(@@factorEspeculador) then
        coste_edificar_casa = casilla.titulo.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar_casa)
      end
      
      es_mia and tengo_saldo
    end
    
    def puedo_edificar_hotel(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = false
      coste_edificar_hotel = 0
      
      if es_mia and casilla.se_puede_edificar_hotel(@@factorEspeculador) then
        coste_edificar_hotel = casilla.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar_hotel)
      end
      
      es_mia and tengo_saldo
    end

    def puedo_pagar_hipoteca(casilla)
      tengo_saldo(casilla.calcular_valor_hipoteca)
    end
    
    def puedo_hipotecar(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      
      es_mia
    end
    
    def pagar_libertad(cantidad)
      tengo_saldo = tengo_saldo(cantidad)
      
      if tengo_saldo then
        modificar_saldo(-cantidad)
      end
      
      tengo_saldo
    end

    def puedo_vender_propiedad(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      hipotecada = casilla.esta_hipotecada
      
      es_mia and !hipotecada
    end

    def set_carta_libertad(carta)
      @cartaLibertad = carta
    end

    def set_casilla_actual(casilla)
      @casillaActual = casilla
    end

    def set_encarcelado(encarcelado)
      @encarcelado = encarcelado
    end

    def tengo_carta_libertad
      @cartaLibertad != nil
    end

    def vender_propiedad(casilla)
      precio_venta = casilla.vender_titulo
      modificar_saldo(precio_venta)
      eliminar_de_mis_propiedades(casilla)
    end

    def cuantas_casas_hoteles_tengo
      total = 0
      
      @propiedades.each do |t|
        total = total + t.casilla.numCasas + t.casilla.numHoteles
      end
      
      total
    end

    def eliminar_de_mis_propiedades(casilla)      
      @propiedades.each do |t|
        if t.nombre == casilla.titulo.nombre then
          @propiedades.delete(t)
        end
      end
      
    end

    def es_de_mi_propiedad(casilla)
      lo_es = false
      
      @propiedades.each do |t|
        if casilla.titulo.nombre.equal?(t.nombre) then #está bien usado 'equal?' ?
          lo_es = true
        end
      end
      
      lo_es
    end

    def tengo_saldo(cantidad)
      if(@saldo >= cantidad) then
        return true
      else 
        puts "No tienes dinero para salir de la carcel"
        return false
      end
    end
    
    def modificar_saldo(cantidad)
      @saldo += cantidad
    end

    def to_s
      "Jugador {nombre = #{@nombre}, saldo = #{@saldo}, encarcelado = #{@encarcelado} }"
    end
    
    private :cuantas_casas_hoteles_tengo, :eliminar_de_mis_propiedades, :es_de_mi_propiedad, :tengo_saldo
  end
end
