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
public class BadConsequence {
    

    String text;
    int levels;
    int nVisibleTreasures;
    int nHiddenTreasures;
    ArrayList<TreasureKind> specificVisibleTreasures= new ArrayList();
    ArrayList<TreasureKind> specificHiddenTreasures= new ArrayList();
    boolean death;
    
    
    public BadConsequence(String t, int l, ArrayList<TreasureKind> v,
                ArrayList<TreasureKind> h){
        this.text=t;
        this.levels=l;
        this.specificVisibleTreasures=v;
        this.specificHiddenTreasures=h;
        this.nVisibleTreasures=-1;
        this.nHiddenTreasures=-1;

    }
    
    public BadConsequence(String t, int l, int nVisible, int nHidden){
        this.text=t;
        this.levels=l;
        this.nVisibleTreasures=nVisible;
        this.nHiddenTreasures=nHidden;
        this.specificVisibleTreasures=new ArrayList();
        this.specificHiddenTreasures=new ArrayList();
    }
    
    public BadConsequence(String text, boolean death){
        this.text=text;
        this.death=death;
        this.nVisibleTreasures=-1;
        this.nHiddenTreasures=-1;
        this.specificVisibleTreasures=null;
        this.specificHiddenTreasures=null;        
    }   
    
    
    public boolean isEmpty(){
        if((nVisibleTreasures<1 && nHiddenTreasures<1) && (specificVisibleTreasures.isEmpty() && specificHiddenTreasures.isEmpty())){
             return true;
        }
        else return false;
    }  
    
     
    public int getLevels(){
        return levels;
        
    }
    
    public String getText(){
        return text;
    }
    
    public int getNVisibleTreasures(){
        return nVisibleTreasures;
    }
    
    public int getNHiddenTreasures(){
        return nHiddenTreasures;
    }


    public ArrayList<TreasureKind>  getSpecificHiddenTreasures (){
        return specificHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificVisibleTreasures(){
        return specificVisibleTreasures;
    }

    public void substractVisibleTreasure( Treasure t){ //contador
        if(nVisibleTreasures<0)
          specificVisibleTreasures.remove(t.getType());
        else nVisibleTreasures--;
        
    }
    
    public void substractHiddenTreasure( Treasure t){
        if(nHiddenTreasures<0)
          specificHiddenTreasures.remove(t.getType());
        else nHiddenTreasures--;
    }
    
    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, ArrayList<Treasure> h ){
        
        ArrayList<TreasureKind> aux_visible=new ArrayList();
        ArrayList<TreasureKind> aux_hidden=new ArrayList();
        
        if(!specificVisibleTreasures.isEmpty() || !specificHiddenTreasures.isEmpty()){
        for(Treasure treasure : v){
            for(TreasureKind specificTreasure : specificVisibleTreasures)
                if(treasure.getType()==specificTreasure)
                    aux_visible.add(specificTreasure);
        }
        for(Treasure treasure : h){
            for(TreasureKind specificTreasure : specificHiddenTreasures)
                if(treasure.getType()==specificTreasure)
                    aux_hidden.add(specificTreasure);
        }        
        
        }
        else if(nVisibleTreasures>0 || nHiddenTreasures>0){
         
            for(int i=0; i<nVisibleTreasures && i<v.size(); i++)
                aux_visible.add(v.get(i).getType());
            for(int i=0; i<nHiddenTreasures && i<h.size(); i++)
                aux_hidden.add(h.get(i).getType());
        }
        BadConsequence bad=new BadConsequence(" ",0,aux_visible,aux_hidden);
        return bad;
    }

    public boolean myBadConsequenceIsDeath(){
        if (this.death)
            return true;
        else 
            return false;
                   
    }
    
    public String toString(){
        
        List list_hidden=Arrays.asList(specificHiddenTreasures);
        List list_visible=Arrays.asList(specificVisibleTreasures);
        
    return "Text = " + text + " levels = " + Integer.toString(levels) +
            " nVisibleTreasure= " + Integer.toString(nVisibleTreasures) +
            " nHiddenTreasure= " + Integer.toString(nHiddenTreasures) + " death= " +
            Boolean.toString(death) + " specificHiddenTreasures= " + list_hidden
            + " specificVisibleTreasures= " + list_visible ;

           
        }
    
    
}











