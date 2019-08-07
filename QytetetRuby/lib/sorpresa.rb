#encoding: utf-8
module ModeloQytetet
  class Sorpresa
    attr_accessor :texto, :valor, :tipo

    def initialize(texto, valor, tipo)
      @texto = texto
      @valor = valor
      @tipo = tipo
    end

    def get_texto
      @texto
    end

    def get_valor
      @valor
    end

    def get_tipo
      @tipo
    end

    def to_s
      "Texto: #{@texto} \n Valor: #{@valor} \n Tipo: #{@tipo}"
    end

  end
end
