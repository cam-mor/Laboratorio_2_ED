/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import lab2_camilo_morales_sebastian_mercado_adalberto_vazques.Campo;
import lab2_camilo_morales_sebastian_mercado_adalberto_vazques.Jugador;
import java.util.List;
import lab2_camilo_morales_sebastian_mercado_adalberto_vazques.SimulacionFutbol;

/**
 *
 * @author ADALBERTO
 */
public class Cancha extends javax.swing.JFrame {
    
    private Campo campo;

    /**
     * Creates new form Cancha
     */
    public Cancha() {
        initComponents();
        campo = new Campo();
        cargarJugadores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtRegresar = new javax.swing.JButton();
        BtListaJugadores = new javax.swing.JButton();
        Jugador1TextField = new javax.swing.JTextField();
        EstrategiaTextField = new javax.swing.JTextField();
        BtIniciarSimulacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultadoTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GoBackButton.png"))); // NOI18N
        BtRegresar.setToolTipText("");
        BtRegresar.setBorderPainted(false);
        BtRegresar.setContentAreaFilled(false);
        BtRegresar.setFocusable(false);
        BtRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(BtRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, -1, -1));

        BtListaJugadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/JugadorFutbolIcono.png"))); // NOI18N
        BtListaJugadores.setBorderPainted(false);
        BtListaJugadores.setContentAreaFilled(false);
        BtListaJugadores.setFocusable(false);
        BtListaJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtListaJugadoresActionPerformed(evt);
            }
        });
        jPanel1.add(BtListaJugadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, -1, -1));

        Jugador1TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jugador1TextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Jugador1TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 150, 40));
        jPanel1.add(EstrategiaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 150, 40));

        BtIniciarSimulacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SimulacionlButton.png"))); // NOI18N
        BtIniciarSimulacion.setBorderPainted(false);
        BtIniciarSimulacion.setContentAreaFilled(false);
        BtIniciarSimulacion.setFocusable(false);
        BtIniciarSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtIniciarSimulacionActionPerformed(evt);
            }
        });
        jPanel1.add(BtIniciarSimulacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, 120));

        ResultadoTextArea.setColumns(20);
        ResultadoTextArea.setRows(5);
        jScrollPane1.setViewportView(ResultadoTextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CanchaFutbol1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtRegresarActionPerformed
        this.setVisible(false);

        Inicio BtInicio = new Inicio();
        BtInicio.setVisible(true);
    }//GEN-LAST:event_BtRegresarActionPerformed

    private void BtListaJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtListaJugadoresActionPerformed
        this.setVisible(false);

        Lista_Jugadores List = new Lista_Jugadores();
        List.setVisible(true);
    }//GEN-LAST:event_BtListaJugadoresActionPerformed

    private void Jugador1TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jugador1TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jugador1TextFieldActionPerformed

    private void BtIniciarSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtIniciarSimulacionActionPerformed
        String inicio = Jugador1TextField.getText();
        
        String estrategia = EstrategiaTextField.getText();

        if (campo.obtenerJugador(inicio) == null) {
            ResultadoTextArea.setText("Error: Jugador no encontrado");
            return;
        }

        if (!estrategia.equalsIgnoreCase("velocidad") &&
            !estrategia.equalsIgnoreCase("posesion") &&
            !estrategia.equalsIgnoreCase("remate")) {
            ResultadoTextArea.setText("Error: Estrategia no valida.");
            return;
        }

        // Llamar a la lógica de cálculo del camino hasta la portería   
        campo.establecerEstrategia(estrategia);
        List<Jugador> camino = campo.calcularCaminoOptimo(inicio, estrategia);

        if (camino.isEmpty()) {
            ResultadoTextArea.setText("No se encontro un camino.");
        } else {
            StringBuilder resultado = new StringBuilder("Camino optimo:\n");
            for (Jugador jugador : camino) {
                resultado.append(jugador.getNombre()).append("\n");
            }
            ResultadoTextArea.setText(resultado.toString());
        }
    }//GEN-LAST:event_BtIniciarSimulacionActionPerformed
    
    private void cargarJugadores(){
        SimulacionFutbol.cargarJugadores("jugadores.csv", campo);
        SimulacionFutbol.cargarMatrizDeAdyacencia("matriz.csv", campo);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cancha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cancha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cancha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cancha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cancha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtIniciarSimulacion;
    private javax.swing.JButton BtListaJugadores;
    private javax.swing.JButton BtRegresar;
    private javax.swing.JTextField EstrategiaTextField;
    private javax.swing.JTextField Jugador1TextField;
    private javax.swing.JTextArea ResultadoTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
