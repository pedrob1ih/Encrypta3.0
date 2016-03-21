package GUI;

import Modelo.Encryptacion;
import Modelo.Fichero;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MensajesJPanel extends javax.swing.JPanel {

    private Encryptacion e;
    private Fichero f;
    
    public MensajesJPanel(Encryptacion e,Fichero f) {
        initComponents();
        TAEntradaUsuarioj.setLineWrap(true);
        TASalida.setLineWrap(true);
        this.e= e;
        this.f=f;
    }
    
    public void mostrar(){
        try {
            this.TASalida.setText("");
            this.TAEntradaUsuarioj.setText("");
            this.setVisible(true);
            if(!(this.e.exportPrivateKey().equals(f.getPrivateKey())))
                e.importPrivateKey(f.getPrivateKey());
        } catch (Exception ex) {
            Logger.getLogger(MensajesJPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        copiarEntrada = new javax.swing.JButton();
        pegarEntrada = new javax.swing.JButton();
        cargarFichero = new javax.swing.JButton();
        bGuardarEnFichero = new javax.swing.JButton();
        jScrollPane0 = new javax.swing.JScrollPane();
        TAEntradaUsuarioj = new javax.swing.JTextArea();
        convertirJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TASalida = new javax.swing.JTextArea();
        copiarEntrada1 = new javax.swing.JButton();
        pegarEntrada1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(650, 480));

        copiarEntrada.setText("Copiar");
        copiarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarEntradaActionPerformed(evt);
            }
        });

        pegarEntrada.setText("Pegar");

        cargarFichero.setText("Cargar Fichero");

        bGuardarEnFichero.setText("Guardar En Fichero");
        bGuardarEnFichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarEnFicheroActionPerformed(evt);
            }
        });

        TAEntradaUsuarioj.setColumns(100);
        TAEntradaUsuarioj.setRows(5);
        TAEntradaUsuarioj.setWrapStyleWord(true);
        TAEntradaUsuarioj.setMinimumSize(new java.awt.Dimension(5, 5));
        jScrollPane0.setViewportView(TAEntradaUsuarioj);

        convertirJButton.setText("Convertir");
        convertirJButton.setMinimumSize(new java.awt.Dimension(5, 5));
        convertirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertirJButtonActionPerformed(evt);
            }
        });

        TASalida.setColumns(20);
        TASalida.setRows(5);
        TASalida.setWrapStyleWord(true);
        TASalida.setMinimumSize(new java.awt.Dimension(5, 5));
        jScrollPane1.setViewportView(TASalida);

        copiarEntrada1.setText("Copiar");

        pegarEntrada1.setText("Pegar");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(copiarEntrada)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pegarEntrada)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(cargarFichero)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(bGuardarEnFichero))
                    .add(layout.createSequentialGroup()
                        .add(copiarEntrada1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pegarEntrada1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(convertirJButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(bGuardarEnFichero)
                    .add(cargarFichero)
                    .add(copiarEntrada)
                    .add(pegarEntrada))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(convertirJButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .add(pegarEntrada1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(copiarEntrada1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 190, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void convertirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertirJButtonActionPerformed
        if(!(this.TAEntradaUsuarioj.getText().equals(""))){
            if (e.isEncripy(this.TAEntradaUsuarioj.getText())) { //si esta encriptado lo desencripta
            try{
                String desEncriptado=e.unEncripta(this.TAEntradaUsuarioj.getText());
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
        }
        
    }//GEN-LAST:event_convertirJButtonActionPerformed

    private void bGuardarEnFicheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarEnFicheroActionPerformed
        f.saveFile("FicheroDePrueva", this.TASalida.getText());
    }//GEN-LAST:event_bGuardarEnFicheroActionPerformed

    private void copiarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_copiarEntradaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TAEntradaUsuarioj;
    private javax.swing.JTextArea TASalida;
    private javax.swing.JButton bGuardarEnFichero;
    private javax.swing.JButton cargarFichero;
    private javax.swing.JButton convertirJButton;
    private javax.swing.JButton copiarEntrada;
    private javax.swing.JButton copiarEntrada1;
    private javax.swing.JScrollPane jScrollPane0;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pegarEntrada;
    private javax.swing.JButton pegarEntrada1;
    // End of variables declaration//GEN-END:variables
}