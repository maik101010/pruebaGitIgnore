/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import consultasTablas.CiudadConsultar;
import consultasTablas.PersonaConsultar;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.Conexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import tablas.Ciudad;
import tablas.Persona;

/**
 *
 * @author Michael García A
 */
public class PersonaView extends javax.swing.JFrame {

    /**
     * Creates new form PersonaView
     */
    JScrollPane miBarra;
    DefaultComboBoxModel value = null;
    DefaultTableModel modeloTabla = null;
    PersonaConsultar personaConsultada = new PersonaConsultar();
    Persona miPersona = new Persona();

    public PersonaView() {
        initComponents();
        llenarComboBox();
        llenarTabla();

    }

    private void llenarComboBox() {

        CiudadConsultar c = new CiudadConsultar();
        value = new DefaultComboBoxModel();
        jcbCiudad.setModel(value);
        try {
            List<Ciudad> lista = c.seleccionarCiudades();
            for (Ciudad ciudadLista : lista) {
                value.addElement(new Ciudad(ciudadLista.getId(), ciudadLista.getNombre()));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void llenarTabla() {
        
        try {
            List<Persona> ad = personaConsultada.seleccionarPersonas();
            DefaultTableModel miJTable = (DefaultTableModel) jTablePersona.getModel();
            miJTable.setNumRows(0);
            Object dato[] = new Object[7];

            for (int i = 0; i < ad.size(); i++) {
                dato[0] = ad.get(i).getId();
                dato[1] = ad.get(i).getNombre();
                dato[2] = ad.get(i).getApellido();
                dato[3] = ad.get(i).getTelefeno();
                dato[4] = ad.get(i).getEdad();
                dato[5] = ad.get(i).getCiudad();
                if (ad.get(i).getEdad()<18) {
                    dato[6] = "Menor";
                }else{
                    dato[6] = "Mayor";
                }
                
                

                miJTable.addRow(dato);
            }
            //jTablePersona.setModel(miJTable);
        } catch (SQLException ex) {
            Logger.getLogger(PersonaView.class.getName()).log(Level.SEVERE, null, ex);
        }

        //modeloTabla = new DefaultTableModel();
        //modeloTabla = (DefaultTableModel)jTablePersona.getModel();
        //
//        try {
//            Object[] filas = new Object[6];
//            List<Persona> lista = personaConsultada.seleccionarPersonas();
//
//            for (int i = 0; i < lista.size(); i++) {
//                filas[0] = lista.get(i).getId();
//                filas[1] = lista.get(i).getNombre();
//                filas[2] = lista.get(i).getApellido();
//                filas[3] = lista.get(i).getTelefeno();
//                filas[4] = lista.get(i).getEdad();
//                filas[5] = lista.get(i).getCiudad().getNombre();
//
//                modeloTabla.addRow(filas);
//
//            }
    }

//    private void llenarTabla() {
//        modeloTable = new DefaultTableModel();
//        try {
//
//            List<Persona> lista = o.seleccionarPersonas();
//            modeloTable = (DefaultTableModel) jTablePersonas.getModel();
//            modeloTable.setNumRows(0);
//            Object[] fila = null;// new Object[6];
//
//            for (int i = 0; i < lista.size(); i++) {
//                modeloTable.addRow(fila);
//                modeloTable.setValueAt(lista.get(i).getId(), i, 0);
//                modeloTable.setValueAt(lista.get(i).getNombre(), i, 1);
//                modeloTable.setValueAt(lista.get(i).getApellido(), i, 2);
//                modeloTable.setValueAt(lista.get(i).getTelefeno(), i, 3);
//                modeloTable.setValueAt(lista.get(i).getEdad(), i, 4);
//                modeloTable.setValueAt(lista.get(i).getCiudad().getNombre(), i, 5);
////                fila[0] = lista.get(i).getId();
////                fila[1] = lista.get(i).getNombre();
////                fila[2] = lista.get(i).getCiudad().getNombre();
////                fila[3] = lista.get(i).getEdad();
////                fila[4] = lista.get(i).getTelefeno();
////                fila[5] = lista.get(i).getApellido();
//
//                //modeloTable.addRow(fila);
//
//            }
//            jTablePersonas.setModel(modeloTable);
//        } catch (Exception e) {
//            System.out.println("Excepcion " + e);
//            //System.out.println("Excepcion "+ e.printStackTrace());
//        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTxtId = new javax.swing.JTextField();
        jTxtApellido = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jTxtTelefono = new javax.swing.JTextField();
        jTxtEdad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jcbCiudad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePersona = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("REGISTRO DE PERSONAS");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Identificación:");

        jLabel5.setText("Apellido:");

        jLabel6.setText("Teléfono:");

        jLabel7.setText("Edad:");

        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Seleccionar Ciudad:");

        jTablePersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "Telefono", "Edad", "Ciudad", "Estado"
            }
        ));
        jTablePersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePersonaMouseClicked(evt);
            }
        });
        jTablePersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablePersonaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePersona);

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbCiudad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(58, 58, 58)
                                        .addComponent(jTxtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtTelefono)
                                    .addComponent(jTxtApellido)
                                    .addComponent(jTxtNombre))))))
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addGap(31, 31, 31)
                .addComponent(Eliminar)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        //Ciudad ciudad = new Ciudad();
        Ciudad ciudad = (Ciudad) jcbCiudad.getSelectedItem();
        //ciudad.getId();

        miPersona.setId(Integer.parseInt(jTxtId.getText()));
        miPersona.setNombre(jTxtNombre.getText());
        miPersona.setApellido(jTxtApellido.getText());
        miPersona.setEdad(Integer.parseInt(jTxtEdad.getText()));
        miPersona.setTelefeno(jTxtTelefono.getText());
        miPersona.setCiudad(ciudad);

        //lblId.setText(id);
        int respuesta;

        try {
            respuesta = personaConsultada.insertarPersonaProcedure(miPersona);
            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "Persona insertada en la base de datos");
                limpiarCamposTexto();
                llenarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el registro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaView.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTablePersonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePersonaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTablePersonaKeyPressed

    private void jTablePersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonaMouseClicked
        // TODO add your handling code here:
        int fila = jTablePersona.rowAtPoint(evt.getPoint());
        jTxtNombre.setText(jTablePersona.getValueAt(fila, 1).toString());
        jTxtApellido.setText(jTablePersona.getValueAt(fila, 2).toString());
        jTxtTelefono.setText(jTablePersona.getValueAt(fila, 3).toString());
        jTxtEdad.setText(jTablePersona.getValueAt(fila, 4).toString());
        jTxtId.setText(jTablePersona.getValueAt(fila, 0).toString());
        inhabilitaId();

//id_persona = Integer.parseInt(jTablePersona.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_jTablePersonaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Persona miPersona = new Persona();
//        Ciudad ciudad = new Ciudad();
        Ciudad ciudad = (Ciudad) jcbCiudad.getSelectedItem();
        //ciudad.getId();

        miPersona.setId(Integer.parseInt(jTxtId.getText()));
        miPersona.setNombre(jTxtNombre.getText());
        miPersona.setApellido(jTxtApellido.getText());
        miPersona.setEdad(Integer.parseInt(jTxtEdad.getText()));
        miPersona.setTelefeno(jTxtTelefono.getText());
        miPersona.setCiudad(ciudad);

        //lblId.setText(id);
        int respuesta;

        try {
            respuesta = personaConsultada.actualizarPersona(miPersona);
            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "Persona actualizada");
                llenarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el registro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        try {
            // TODO add your handling code here:
            int id = Integer.parseInt(jTxtId.getText());
            int resultado = personaConsultada.eliminarPersona(id);

            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Persona eliminada");
                limpiarCamposTexto();

                llenarTabla();

            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarActionPerformed

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
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePersona;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtEdad;
    private javax.swing.JTextField jTxtId;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JTextField jTxtTelefono;
    private javax.swing.JComboBox<String> jcbCiudad;
    // End of variables declaration//GEN-END:variables

    private void inhabilitaId() {
        jTxtId.setEditable(false);
    }

    private void limpiarCamposTexto() {
        jTxtNombre.setText("");
        jTxtApellido.setText("");
        jTxtEdad.setText("");
        jTxtTelefono.setText("");
        jTxtId.setText("");
    }
}
