require 'singleton'

class Dado
  def initialize
  
  end
  include Singleton

  def tirar
    numero = rand(6) + 1

    numero
  end
end
