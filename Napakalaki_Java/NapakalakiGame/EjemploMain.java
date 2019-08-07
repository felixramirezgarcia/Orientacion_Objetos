
package NapakalakiGame;

import java.util.*;
import GUI.*;
public class EjemploMain {

    public static void main(String[] args) {
      Napakalaki napakalakiModel = Napakalaki.getInstance();
      NapakalakiView napakalakiView= new NapakalakiView();
      Dice.createInstance(napakalakiView);
      
      ArrayList<String> names= new ArrayList();
      PlayerNamesCapture namesCapture= new PlayerNamesCapture(napakalakiView, true);
      names= namesCapture.getNames();
      napakalakiModel.initGame(names);
      
      napakalakiView.setNapakalaki(napakalakiModel);      
      napakalakiView.showView();
      

    }
}
