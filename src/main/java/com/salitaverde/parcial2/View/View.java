package com.salitaverde.parcial2.View;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

import com.salitaverde.parcial2.Control.Controlador;
import static com.salitaverde.parcial2.Control.Controlador.mostrarVistaEditar;
import com.salitaverde.parcial2.EditView.EditView;

public class View extends javax.swing.JFrame {

    public static EditView editView = new EditView();
    
    public View() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		IngDni = new javax.swing.JTextField();
		IngPse = new javax.swing.JTextField();
		IngNom = new javax.swing.JTextField();
		Guardar = new javax.swing.JButton();
		Editar = new javax.swing.JButton();
		Limpiar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Agregar autor");
		setForeground(java.awt.Color.black);
		setResizable(false);

		jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Agregar Autor");

		jLabel2.setText("DNI");

		jLabel3.setText("Nombre");

		jLabel4.setText("Pseudonimo");

		Guardar.setText("Guardar");
		Guardar.addActionListener(this::GuardarActionPerformed);

		Editar.setText("Editar");
		Editar.addActionListener(this::EditarActionPerformed);

		Limpiar.setText("Limpiar");
		Limpiar.addActionListener(this::LimpiarActionPerformed);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
												.addGap(0, 0, Short.MAX_VALUE)
												.addComponent(Limpiar,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														115,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jLabel1,
												javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(Editar,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														115,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(
														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														170,
														Short.MAX_VALUE)
												.addComponent(Guardar,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														115,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												layout
														.createSequentialGroup()
														.addComponent(jLabel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																135,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(IngNom))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												layout
														.createSequentialGroup()
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		135,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel4,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		135,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(IngPse)
																.addComponent(IngDni))))
								.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										26,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(40, 40, 40)
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												28,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(IngNom))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2)
										.addComponent(IngDni,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(16, 16, 16)
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(IngPse,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(Limpiar)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										145,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(Guardar)
										.addComponent(Editar))
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_GuardarActionPerformed
        try {
            Controlador.guardar(IngDni, IngNom, IngPse);
            Controlador.limpiar(IngDni, IngNom, IngPse);
        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
        }

    }// GEN-LAST:event_GuardarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LimpiarActionPerformed
        Controlador.limpiar(IngDni, IngNom, IngPse);
    }// GEN-LAST:event_LimpiarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
//Se crea una nueva instancia de la ventana de edición (EditView)
        mostrarVistaEditar(this, editView);

    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton Editar;
	private javax.swing.JButton Guardar;
	private javax.swing.JTextField IngDni;
	private javax.swing.JTextField IngNom;
	private javax.swing.JTextField IngPse;
	private javax.swing.JButton Limpiar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	// End of variables declaration//GEN-END:variables

    public JTextField getIngDni() {
        return IngDni;
    }

    public JTextField getIngNom() {
        return IngNom;
    }

    public JTextField getIngPse() {
        return IngPse;
    }

}
