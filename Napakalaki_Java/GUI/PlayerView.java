/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import NapakalakiGame.*;
import java.awt.Component;
import java.util.*;
import javax.swing.JPanel;
/**
 *
 * @author l
 */
public class PlayerView extends javax.swing.JPanel {

    
    Player playerModel;
    private Napakalaki napakalakiModel;
    
    public PlayerView() {
        initComponents();
    }

    public void setPlayer(Player p){
        
        playerModel=p;
        name.setText(playerModel.getName());
        level.setText(Integer.toString(playerModel.getLevels()));
        badConsequenceView1.setBadConsequence(playerModel.getPendingBadConsequence());
        death.setText(Boolean.toString(playerModel.isDead()));
        fillTreasurePanel(visibleTreasures, playerModel.getVisibleTreasures());
        fillTreasurePanel(hiddenTreasures, playerModel.getHiddenTreasures());
        repaint();
        revalidate();
    }
    
    public void setNapakalki(Napakalaki n){
        napakalakiModel=n;
    }
    
    public ArrayList<Treasure> getSelectedTreasures(JPanel aPanel){
        
        TreasureView tv;
        ArrayList<Treasure> output= new ArrayList();
        for(Component c : aPanel.getComponents()){
            tv=(TreasureView) c;
            if( tv.isSelected() )
                output.add( tv.getTreasure());
        }
        return output;
    }
    
    public void fillTreasurePanel(JPanel aPanel, ArrayList<Treasure> aList){
        
        aPanel.removeAll();
        
        for(Treasure t: aList){
            
            TreasureView aTreasureView = new TreasureView();
            aTreasureView.setTreasure(t);
            aTreasureView.setVisible(true);
            aPanel.add(aTreasureView);
            
        }
        
        aPanel.repaint();
        aPanel.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        deathLabel = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        level = new javax.swing.JLabel();
        death = new javax.swing.JLabel();
        visibleTreasures = new javax.swing.JPanel();
        hiddenTreasures = new javax.swing.JPanel();
        buyLevels = new javax.swing.JButton();
        makeVisible = new javax.swing.JButton();
        discardTreasures = new javax.swing.JButton();
        badConsequenceView1 = new GUI.BadConsequenceView();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        nameLabel.setText("Name:");

        levelLabel.setText("Level:");

        deathLabel.setText("death?");

        visibleTreasures.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        hiddenTreasures.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        buyLevels.setText("Buy Levels");
        buyLevels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyLevelsActionPerformed(evt);
            }
        });

        makeVisible.setText("Make visible");
        makeVisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeVisibleActionPerformed(evt);
            }
        });

        discardTreasures.setText("Discard treasures");
        discardTreasures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardTreasuresActionPerformed(evt);
            }
        });

        jLabel1.setText("PendingBadConsequence:");

        jLabel2.setText("Visible Treasures:");

        jLabel3.setText("Hidden Treasures:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(makeVisible)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buyLevels)
                            .addGap(249, 249, 249))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(levelLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(level, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(deathLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(death, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addComponent(discardTreasures)))
                            .addGap(339, 339, 339)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(92, 92, 92))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(badConsequenceView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(visibleTreasures, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(hiddenTreasures, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(visibleTreasures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hiddenTreasures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(level, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(death, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deathLabel))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(badConsequenceView1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyLevels)
                    .addComponent(makeVisible))
                .addGap(18, 18, 18)
                .addComponent(discardTreasures)
                .addContainerGap(124, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void makeVisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeVisibleActionPerformed
        
        ArrayList<Treasure> setHidden = getSelectedTreasures(hiddenTreasures);
        napakalakiModel.makeTreasuresVisible(setHidden);
        setPlayer(napakalakiModel.getCurrentPlayer());
    }//GEN-LAST:event_makeVisibleActionPerformed


    
    private void buyLevelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyLevelsActionPerformed
        ArrayList<Treasure> setHidden = getSelectedTreasures(hiddenTreasures);
        ArrayList<Treasure> setVisible = getSelectedTreasures(visibleTreasures);             
        playerModel.buyLevels(setVisible, setHidden);
        setPlayer(napakalakiModel.getCurrentPlayer());
    }//GEN-LAST:event_buyLevelsActionPerformed

    private void discardTreasuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardTreasuresActionPerformed
    ArrayList<Treasure> setHidden = getSelectedTreasures(hiddenTreasures);
    ArrayList<Treasure> setVisible = getSelectedTreasures(visibleTreasures);
    for(Treasure t : setVisible)
        playerModel.discardVisibleTreasure(t);
    for(Treasure t: setHidden)
        playerModel.discardHiddenTreasure(t);
    setPlayer(napakalakiModel.getCurrentPlayer());        
        
        
    }//GEN-LAST:event_discardTreasuresActionPerformed

    public void setMakeVisible(boolean b){
        makeVisible.setEnabled(b);
//        makeVisible.setVisible(b);
    }
    
    public void setBuyLevels(boolean b){
        buyLevels.setEnabled(b);
//        buyLevels.setVisible(b);
    }
    public void setDiscardTreasures(boolean b){
        discardTreasures.setEnabled(b);
//        buyLevels.setVisible(b);
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.BadConsequenceView badConsequenceView1;
    private javax.swing.JButton buyLevels;
    private javax.swing.JLabel death;
    private javax.swing.JLabel deathLabel;
    private javax.swing.JButton discardTreasures;
    private javax.swing.JPanel hiddenTreasures;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel level;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JButton makeVisible;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel visibleTreasures;
    // End of variables declaration//GEN-END:variables
}
