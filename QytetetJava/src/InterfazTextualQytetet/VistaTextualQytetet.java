
package InterfazTextualQytetet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import modeloqytetet.Casilla;
import modeloqytetet.OtraCasilla;
import modeloqytetet.Calle;

public class VistaTextualQytetet {
    
    private static final Scanner in = new Scanner (System.in);
    
    private boolean comprobarOpcion(String lectura, int min, int max) {
        boolean valido=true;   
        int opcion;
        
        try {  
             opcion =Integer.parseInt(lectura);
             if (opcion<min || opcion>max) { // No es un entero entre los válidos
                  this.mostrar("el numero debe estar entre min y max");
                   valido = false;}

        } catch (NumberFormatException e) { // No se ha introducido un entero
                 this.mostrar("debes introducir un numero");
                 valido = false;  
        }
        if (!valido) {
           this.mostrar("\n\n Seleccion erronea. Intentalo de nuevo.\n\n");
        }
        
        return valido;
   }

    public void mostrar(String texto){ //método que muestra en pantalla el string que recibe como argumento

       System.out.println(texto);
    }
    
    public boolean elegirQuieroComprar() {
        String respuesta;
        boolean incorrecto = false;
        boolean elegir = false;
        
        System.out.println("¿Quieres comprar este título de propiedad?");
        System.out.println("Sí --> S");
        System.out.println("No --> N");
                
        do {
            respuesta = in.nextLine();
            incorrecto = false;
            
            if (respuesta.equals("S") || respuesta.equals("s"))
                elegir = true;
            else if (respuesta.equals("N") || respuesta.equals("n"))
                elegir = false;
            else {
                System.out.println("Respuesta no válida: eliga Sí (S) o No (N)");
                incorrecto = true;
            }
        } while (incorrecto);
        
        return elegir;
    }
    
    public int menuElegirPropiedad(List<String> listaPropiedades) { //numero y nombre de propiedades            
        Map<Integer, String> menuEP = new TreeMap();
        int numeroOpcion=0;
        
        for(String prop: listaPropiedades) {
            menuEP.put(numeroOpcion, prop); //opcion de menu, numero y nombre de propiedad
            numeroOpcion=numeroOpcion+1;
        }
        
        int salida=this.seleccionMenu(menuEP); // Método para controlar la elección correcta en el menú 
        
        return salida;
    }
    
    public int menuGestionInmobiliaria() {
         //ejemplo de menú
     
        this.mostrar("Elige la gestion inmobiliaria que deseas hacer");
        Map<Integer, String> menuGI = new TreeMap();
        menuGI.put(0, "Siguiente Jugador"); 
        menuGI.put(1, "Edificar casa");
        menuGI.put(2, "Edificar Hotel"); 
        menuGI.put(3, "Vender propiedad ");  	
        menuGI.put(4, "Hipotecar Propiedad"); 
        menuGI.put(5, "Cancelar Hipoteca");
        int salida=this.seleccionMenu(menuGI); // Método para controlar la elección correcta en el menú 
        
        return salida;
    }
    
    public int menuSalirCarcel() {
        int metodo = 0;
        boolean incorrecto = true;
        
        System.out.println("Estás encarcelado: selecciona un método para intentar salir: ");
        System.out.println("1 --> tirar dado.");
        System.out.println("2 --> pagar libertad");
        
        do {
            metodo = Integer.parseInt(in.nextLine());
            
            if (metodo == 1 || metodo == 2)
                incorrecto = false;
            
        } while (incorrecto);
        
        return metodo;
    }
        
    public ArrayList<String> obtenerNombreJugadores() {
        boolean valido = true; 
        String lectura;
        ArrayList<String> nombres = new ArrayList();
        do{ //repetir mientras que el usuario no escriba un número correcto 
            this.mostrar("Escribe el número de jugadores: (de 2 a 4):");
            lectura = in.nextLine();  //lectura de teclado
            valido=this.comprobarOpcion(lectura, 2, 4); //método para comprobar la elección correcta
        }while (!valido);

        for (int i = 1; i <= Integer.parseInt(lectura); i++) { //solicitud del nombre de cada jugador
          this.mostrar("Nombre del jugador " + i + ": ");
          nombres.add (in.nextLine());
        }
        
        return nombres;
    }
    
    private int seleccionMenu(Map<Integer, String> menu) {
        boolean valido = true; 
        int numero;
        String lectura;
        
        do { // Hasta que se hace una selección válida
          for(Map.Entry<Integer, String> fila : menu.entrySet()) {
                numero = fila.getKey();
                String texto = fila.getValue();
                this.mostrar(numero + " : " + texto);  // número de opción y texto
          }
          this.mostrar("\n Elige una opción: ");
          lectura = in.nextLine();  //lectura de teclado
          valido=this.comprobarOpcion(lectura, 0, menu.size()-1); //método para comprobar la elección correcta
        } while (!valido);
        
        return Integer.parseInt(lectura);
    }
    
}
