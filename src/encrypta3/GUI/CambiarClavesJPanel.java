/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypta3.GUI;

import encrypta3.Clases.Encryptacion;
import encrypta3.Clases.Fichero;

/**
 *
 * @author pe
 */
public class CambiarClavesJPanel extends javax.swing.JPanel {

    private Encryptacion e;
    public CambiarClavesJPanel(Encryptacion e) {
        initComponents();
        this.e= e;
        cajaDeTextojTextArea.setLineWrap(true);
    }
    
    public void mostrar(){
        this.cajaDeTextojTextArea.setText("");
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

        MuestraClavejScrollPane = new javax.swing.JScrollPane();
        cajaDeTextojTextArea = new javax.swing.JTextArea();
        GuardarjButton = new javax.swing.JButton();
        VerPatronjButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(650, 480));
        setPreferredSize(new java.awt.Dimension(650, 480));

        MuestraClavejScrollPane.setMinimumSize(new java.awt.Dimension(5, 5));

        cajaDeTextojTextArea.setColumns(20);
        cajaDeTextojTextArea.setRows(5);
        MuestraClavejScrollPane.setViewportView(cajaDeTextojTextArea);

        GuardarjButton.setText("Guardar");
        GuardarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarjButtonActionPerformed(evt);
            }
        });

        VerPatronjButton.setText("verPatron");
        VerPatronjButton.setMaximumSize(new java.awt.Dimension(71, 23));
        VerPatronjButton.setMinimumSize(new java.awt.Dimension(71, 23));
        VerPatronjButton.setPreferredSize(new java.awt.Dimension(100, 23));
        VerPatronjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerPatronjButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("* La clave mostrada puede ser modificada si lo quiere");

        jLabel3.setText("* insertarPatrones guarda un nuevo patron");

        jLabel4.setText("* verPatron muestra por la interfaz los patrones que se siguen");

        jLabel5.setText("CambiarClaves");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(GuardarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(VerPatronjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(423, 423, 423))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(MuestraClavejScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GuardarjButton)
                    .addComponent(VerPatronjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MuestraClavejScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void GuardarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarjButtonActionPerformed
        try{
            this.e.generaClaves(this.cajaDeTextojTextArea.getText());
            e.guardarClavePublica();
        }
        catch(Exception e){
            this.cajaDeTextojTextArea.setText(e.getMessage());
        }
        
    }//GEN-LAST:event_GuardarjButtonActionPerformed

    private void VerPatronjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerPatronjButtonActionPerformed
        this.e.leerFichero();
        int eNumEm[][]=e.getaPatrones();
        String fichero="";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 16; j++) {
                fichero+=eNumEm[i][j]+"-";
            }
            fichero+=".\n";
        }
        this.cajaDeTextojTextArea.setText(fichero);
    }//GEN-LAST:event_VerPatronjButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GuardarjButton;
    private javax.swing.JScrollPane MuestraClavejScrollPane;
    private javax.swing.JButton VerPatronjButton;
    private javax.swing.JTextArea cajaDeTextojTextArea;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
