package GUI;

import Modelo.Encryptacion;
import Modelo.Fichero;

public class CambiarClavesJPanel extends javax.swing.JPanel {

    private Encryptacion e;
    private Fichero f;
    
    public CambiarClavesJPanel(Encryptacion e,Fichero f) {
        initComponents();
        this.e= e;
        this.f=f;
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

        jLabel5.setText("CambiarClaves");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MuestraClavejScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(GuardarjButton)
                        .addGap(18, 18, 18)
                        .addComponent(VerPatronjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MuestraClavejScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GuardarjButton)
                    .addComponent(VerPatronjButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void GuardarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarjButtonActionPerformed
        try{
            this.e.setPrivateKey(this.cajaDeTextojTextArea.getText());
            this.f.savePrivateKey(e.exportPrivateKey());
            
        }
        catch(Exception e){
            this.cajaDeTextojTextArea.setText(e.getMessage());
        }
        
    }//GEN-LAST:event_GuardarjButtonActionPerformed

    private void VerPatronjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerPatronjButtonActionPerformed
        this.cajaDeTextojTextArea.setText(e.exportPrivateKey());
        //ver si se ha guardado correctamente
    }//GEN-LAST:event_VerPatronjButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GuardarjButton;
    private javax.swing.JScrollPane MuestraClavejScrollPane;
    private javax.swing.JButton VerPatronjButton;
    private javax.swing.JTextArea cajaDeTextojTextArea;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
