/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;


import java.util.*;
/**
 *
 * @author l
 */
public class CardDealer {


    private LinkedList<Monster> unusedMonsters;
    private LinkedList<Monster> usedMonsters;
    private LinkedList<Treasure> unusedTreasures;
    private LinkedList<Treasure> usedTreasures;
    
    private static final CardDealer instance = new CardDealer();
    
    public static CardDealer getInstance(){
        return instance;
    }
    
    private CardDealer(){
        unusedMonsters=new LinkedList();
        usedMonsters= new LinkedList();
        unusedTreasures= new LinkedList();
        usedTreasures= new LinkedList();
    
    }
    
    
    private void initTreasureCardDeck(){
        unusedTreasures.add(new Treasure("¡Sí mi amo!", 0, 4, 7, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Botas de investigación", 600, 3, 4, TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 500, 3, 5, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("A prueba de babas", 400, 2, 5, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 600, 4, 8, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 100, 1, 7, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 400, 3, 6, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Fez alópodo", 700, 3, 5, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Hacha prehistórica", 500, 2, 5, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Gaita", 500, 4, 5, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Insecticida", 300, 2, 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 700, 4, 6, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Garabato místico", 300, 2, 2, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("La fuerza de Mr. T", 1000, 0, 0, TreasureKind.NECKLACE));
        unusedTreasures.add(new Treasure("La rebeca metálica", 400, 2, 3, TreasureKind.ARMOR));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 200, 3, 4, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necroplayboycóm", 300, 3, 5, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Lanzallamas", 800, 4, 8, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrocomicón", 100, 1, 1, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necronomicón", 800, 5, 7, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 400, 3, 6, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Necrognomicón", 200, 2, 4, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Necrotelecom", 300, 2, 3, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Porra preternatural", 200, 2, 3, TreasureKind.ONEHAND));
        unusedTreasures.add(new Treasure("Tentáculo de pega", 200, 0, 1, TreasureKind.HELMET));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 500, 0, 1, TreasureKind.SHOE));
        unusedTreasures.add(new Treasure("Shogulador", 600, 1, 1, TreasureKind.BOTHHAND));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 400, 3, 4, TreasureKind.ONEHAND));

       
    }
    private void initMonsterCardDeck(){
        BadConsequence badConsequence;
        Prize prize;
        
        badConsequence= new BadConsequence("Mal rollo: Pierdes 2 niveles y 2 tesoros ocultos.", 2, 0, 2);
        prize= new Prize( 2, 1);
        unusedMonsters.add(new Monster("Semillas de Cthulhu", 4, badConsequence, prize));
        
        badConsequence =new BadConsequence("Mal rollo: Pierdes tu armadura visible y otra oculta.", 0,
                        new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        prize= new Prize(2,1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, badConsequence, prize));
        
        badConsequence =new BadConsequence("Mal rollo: El primordial bostezo contagioso. Pierdes el"
                + " calzado visible.", 0, new ArrayList(Arrays.asList(TreasureKind.SHOE)), new ArrayList());
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, badConsequence, prize));
        
        badConsequence= new BadConsequence("Mal rollo: Te atrapan para llevarte de fiesta y de dejan caer en mitad"
                        + " del vuelo. Descarta 1 mano visible y 1 mano oculta.", 0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                        new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize= new Prize(4,1);
        unusedMonsters.add(new Monster( "Ángeles de la noche Ibizeña", 14, badConsequence, prize));
        
     
        badConsequence= new BadConsequence("Mal rollo: Pierdes todos tus tesoros visibles", 0,  Integer.MAX_VALUE, 0);
        prize= new Prize(3,1);
        unusedMonsters.add(new Monster("El gorrón del umbral", 10, badConsequence, prize));
        
        badConsequence= new BadConsequence("Mal rollo: Pierdes la armadura visible.", 0 ,new ArrayList(Arrays.asList(TreasureKind.ARMOR)) ,
                        new ArrayList(Arrays.asList()));
        prize = new Prize(2,1);
        unusedMonsters.add(new Monster("H. P. Munchcraft", 6, badConsequence, prize));
        
        badConsequence= new BadConsequence("Mal rollo: Sientes bichos bajo la ropa. Descarta la armadura visible.", 0,
                        new ArrayList(Arrays.asList(TreasureKind.ARMOR)), new ArrayList());
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster("Bichgooth", 2, badConsequence, prize));
        
                
        badConsequence = new BadConsequence("Pierdes 5 niveles", 5, 0, 0);
        prize = new Prize(4,2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, badConsequence, prize));
        
        
        badConsequence= new BadConsequence("Mal rollo: Toses los pulmones y pierdes 2 niveles.", 2, 0,0);
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas.", 2, badConsequence, prize));
        
        
        
        badConsequence = new BadConsequence("Mal rollo:"
                        + " Embobados con el lindo primigenio te descartas de tu casco visible", 0,
                        new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Chibithulhu", 4, badConsequence, prize));
                
        
        
        Boolean muerto= true;
        prize= new Prize(2,1);
        badConsequence= new BadConsequence("Mal rollo: Estos mostruos resultan bastante superficiales y te aburren"
                        + " morltamente. Estas muerto.", muerto );
        unusedMonsters.add( new Monster("Los hondos", 8, badConsequence, prize));
        
        
        
        
        badConsequence= new BadConsequence("Mal rollo: Te intentas escaquear. Pierdes una mano visible.",
                        0, new ArrayList(Arrays.asList(TreasureKind.ONEHAND)), new ArrayList() );
        prize= new Prize(2,1);
        unusedMonsters.add(new Monster("Dameargo", 1, badConsequence, prize));
        
        badConsequence= new BadConsequence("Mal rollo: Da mucho asquito. Pierdes 3 niveles.", 3, 0, 0);
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster(" Pollipolipo volante", 3, badConsequence, prize));
        
        
        muerto=true;
        badConsequence= new BadConsequence("Mal rollo: No le hace gracia que pronuncien mal su nombre."
                        + "estas muerto. ",muerto);
        prize= new Prize(3,1);
        unusedMonsters.add(new Monster("YskhtihyssgGoth", 12, badConsequence, prize));
        
        
        badConsequence= new BadConsequence("Mal rollo: La familia te atrapa. Estas muerto.", true);
        prize=new Prize(4,1);
        unusedMonsters.add(new Monster("Familia feliz", 1, badConsequence, prize));
        
        
        badConsequence= new BadConsequence("Mal rollo: La quinta directiva primaria te obliga a perder 2 niveles "
                        + "y un tesoro 2 manos visible.", 2, new ArrayList(Arrays.asList(TreasureKind.BOTHHAND)), new ArrayList());
        prize= new Prize(2,1);
        unusedMonsters.add(new Monster("Roboggoth", 8 , badConsequence, prize));
        
        badConsequence= new BadConsequence("Mal rollo: Te asustas en la noche. Pierdes un casco visible.", 0,
                        new ArrayList(Arrays.asList(TreasureKind.HELMET)), new ArrayList());
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster("El espia", 5, badConsequence, prize));
        
        badConsequence= new BadConsequence("Mal rollo: Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.", 2, 5, 0);
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster("El lenguas", 20, badConsequence, prize));
        
        
        
        badConsequence= new BadConsequence("Mal rollo: Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles"
                        + " de las manos", 3, new ArrayList(Arrays.asList(TreasureKind.ONEHAND ,TreasureKind.ONEHAND,TreasureKind.BOTHHAND,TreasureKind.HELMET,TreasureKind.ARMOR,TreasureKind.SHOE)), new ArrayList());
        prize= new Prize(1,1);
        unusedMonsters.add(new Monster("Bicéfalo", 20, badConsequence, prize));
    }
    private void shuffleTreasures(){
        Collections.shuffle(unusedTreasures);
    }
    
    private void shuffleMonster(){
        Collections.shuffle(unusedMonsters);
    }
    
    public Treasure nextTreasure(){
        return unusedTreasures.removeFirst();
    }
    
    public Monster nextMonster(){
        return unusedMonsters.removeFirst();
    }
    
    public void giveTreasureBack( Treasure t){
        usedTreasures.add(t);
        unusedTreasures.remove(t);
    }
   
    public void giveMonsterBack(Monster m){
        usedMonsters.add(m);
        unusedMonsters.remove(m);
    }
    
    public void initCards(){
        this.initMonsterCardDeck();
        this.shuffleMonster();                           
        this.initTreasureCardDeck();
        this.shuffleTreasures();

    }
    
    
    
    
}
