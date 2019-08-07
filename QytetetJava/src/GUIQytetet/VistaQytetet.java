/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUIQytetet;

import modeloqytetet.*;

/**
 *
 * @author felix
 */
public class VistaQytetet extends javax.swing.JPanel {

    /**
     * Creates new form VistaQytetet
     */
    public VistaQytetet() {
        initComponents();
    }
    
   void actualizar(Qytetet qytetet) {
       vistaCasilla1.actualizar(qytetet.getJugadorActual().getCasillaActual().toString());
       vistaJugador1.actualizar(qytetet.getJugadorActual().toString());
       if(qytetet.getCartaActual()==null)
            vistaCartaSorpresa2.actualizar("NO HAY CARTA");
       else
           vistaCartaSorpresa2.actualizar(qytetet.getCartaActual().toString());
       
       if(qytetet.getJugadorActual().getCasillaActual().getTipo() == TipoCasilla.SORPRESA){
           
       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaCartaSorpresa2 = new GUIQytetet.VistaCartaSorpresa();
        vistaCasilla1 = new GUIQytetet.VistaCasilla();
        vistaJugador1 = new GUIQytetet.VistaJugador();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(vistaCasilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vistaCartaSorpresa2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vistaJugador1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vistaCartaSorpresa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vistaCasilla1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vistaJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUIQytetet.VistaCartaSorpresa vistaCartaSorpresa2;
    private GUIQytetet.VistaCasilla vistaCasilla1;
    private GUIQytetet.VistaJugador vistaJugador1;
    // End of variables declaration//GEN-END:variables

    
}
