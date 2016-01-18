/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypta3.GUI;

import encrypta3.Clases.Encryptacion;

/**
 *
 * @author pe
 */
public class MensajesJPanel extends javax.swing.JPanel {

    private Encryptacion e;
    
    public MensajesJPanel(Encryptacion e) {
        initComponents();
        TAEntradaUsuarioj.setLineWrap(true);
        TASalida.setLineWrap(true);
        this.e= e;
    }
    
    public void mostrar(){
        this.TASalida.setText("");
        this.TAEntradaUsuarioj.setText("");
        this.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TASalida = new javax.swing.JTextArea();
        convertirJButton = new javax.swing.JButton();
        jScrollPane0 = new javax.swing.JScrollPane();
        TAEntradaUsuarioj = new javax.swing.JTextArea();
        BGuardarFichero = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(650, 450));
        setPreferredSize(new java.awt.Dimension(650, 480));

        TASalida.setColumns(20);
        TASalida.setRows(5);
        TASalida.setWrapStyleWord(true);
        TASalida.setMinimumSize(new java.awt.Dimension(5, 5));
        jScrollPane1.setViewportView(TASalida);

        convertirJButton.setText("Convertir");
        convertirJButton.setMinimumSize(new java.awt.Dimension(5, 5));
        convertirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertirJButtonActionPerformed(evt);
            }
        });

        TAEntradaUsuarioj.setColumns(100);
        TAEntradaUsuarioj.setRows(5);
        TAEntradaUsuarioj.setWrapStyleWord(true);
        TAEntradaUsuarioj.setMinimumSize(new java.awt.Dimension(5, 5));
        jScrollPane0.setViewportView(TAEntradaUsuarioj);

        BGuardarFichero.setText("Guarda Fichero");
        BGuardarFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarFicheroActionPerformed(evt);
            }
        });

        jButton1.setText("Cargar Fichero");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BGuardarFichero)
                .addContainerGap())
            .addComponent(jScrollPane0)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addComponent(convertirJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BGuardarFichero)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane0, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(convertirJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void convertirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertirJButtonActionPerformed
        if (e.isEncripy(this.TAEntradaUsuarioj.getText())) { //si esta encriptado lo desencripta
            try{
                String desEncriptado=e.desencripta(this.TAEntradaUsuarioj.getText());
                this.TASalida.setText(desEncriptado);
            }
            catch(Exception eo){
                this.TASalida.setText("El codigo no esta completo");
                eo.printStackTrace();
            }
        }
        else{ // si no lo esta, lo encripta
            String encriptado=e.encripta(this.TAEntradaUsuarioj.getText());
            this.TASalida.setText(encriptado);
            
        }
    }//GEN-LAST:event_convertirJButtonActionPerformed

    private void BGuardarFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarFicheroActionPerformed
        e.guardaFicheroEncriptado("FicheroDePrueva", this.TASalida.getText());
    }//GEN-LAST:event_BGuardarFicheroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BGuardarFichero;
    private javax.swing.JTextArea TAEntradaUsuarioj;
    private javax.swing.JTextArea TASalida;
    private javax.swing.JButton convertirJButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane0;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}