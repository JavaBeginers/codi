package edu.uoc.tdp.pac4.stock;

import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.exceptions.DuplicatedRequestException;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.beans.Recurso;
import edu.uoc.tdp.pac4.exceptions.TooManyRequestsException;
import edu.uoc.tdp.pac4.remote.GestorEstoc;
import edu.uoc.tdp.pac4.util.NumericUtils;
import edu.uoc.tdp.pac4.client.PnlMain;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Implementa el formulario para introducir peticiones.
 * @author eSupport Netbeans
 */
public class FrmResourcesRequest extends javax.swing.JDialog 
{
   private GestorEstoc rmiManager;
   private LanguageUtils language;

   /**
    * Creates new form FrmResourcesRequest
    */
   public FrmResourcesRequest(PnlMain parent, boolean modal, GestorEstoc manager, LanguageUtils language) 
   {
      super(parent, modal);
      // Obtiene los parámetros que provienen del formulario principal
      this.rmiManager = manager;
      this.language = language;
      
      // Inicializa los componentes del formulario
      initComponents();
      
      // Inicializa literales de idioma
      this.setTitle(language.getProperty("form.resourceRequest.title"));
      lblClass.setText(language.getProperty("form.resourceRequest.lblClass"));
      lblResource.setText(language.getProperty("form.resourceRequest.lblResource"));
      lblNumItems.setText(language.getProperty("form.resourceRequest.lblNumItems"));
      cmdAccept.setText(language.getProperty("form.common.accept"));
      cmdCancel.setText(language.getProperty("form.common.cancel"));
      
      // Rellena los campos con sus valores por defecto
      txtNumItems.setText("1");
      
      // Rellena las listas de recursos y aulas
      try 
      {
         List<Recurso> recursos = manager.getRecursos();
         cboResourceType.removeAll();
         cboResourceType.setModel(new DefaultComboBoxModel(recursos.toArray()));
         
         List<Aula> aulas = manager.getAulas();
         cboClass.removeAll();
         cboClass.setModel(new DefaultComboBoxModel(aulas.toArray()));
      }
      catch (RemoteException ex)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.rmi") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Bloquea el botón de Aceptar pues no sabemos a priori como ha quedado el formulario
         cmdAccept.setEnabled(false);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (SQLException ex)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Bloquea el botón de Aceptar pues no sabemos a priori como ha quedado el formulario
         cmdAccept.setEnabled(false);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (Exception ex) 
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.generic") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Bloquea el botón de Aceptar pues no sabemos a priori como ha quedado el formulario
         cmdAccept.setEnabled(false);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
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

        cboClass = new javax.swing.JComboBox();
        lblClass = new javax.swing.JLabel();
        cboResourceType = new javax.swing.JComboBox();
        lblResource = new javax.swing.JLabel();
        txtNumItems = new javax.swing.JTextField();
        lblNumItems = new javax.swing.JLabel();
        cmdAccept = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva petición de recursos");
        setModal(true);
        setResizable(false);

        cboClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblClass.setText("Aula");

        cboResourceType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblResource.setText("Recurso");

        txtNumItems.setText("1");

        lblNumItems.setText("Cantidad solicitada");

        cmdAccept.setText("Aceptar");
        cmdAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAcceptActionPerformed(evt);
            }
        });

        cmdCancel.setText("Cancelar");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblResource)
                    .addComponent(lblClass)
                    .addComponent(lblNumItems))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumItems, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboResourceType, 0, 200, Short.MAX_VALUE)
                    .addComponent(cboClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdAccept)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCancel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboResourceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResource))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumItems))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdAccept))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
      
      // Cierra el formulario
      this.dispose();
      
   }//GEN-LAST:event_cmdCancelActionPerformed

   private void cmdAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAcceptActionPerformed
      
      // Verifica los parámetros introducidos por el usuario en el formulario
      if (!NumericUtils.isInteger(txtNumItems.getText()))
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("form.resourceRequest.incorrectNumItems"), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.WARNING_MESSAGE);
         return;
      }
      
      if (cboResourceType.getSelectedIndex() < 0)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("form.resourceRequest.noSelectedResource") ,
                                       language.getProperty("app.title"), 
                                       JOptionPane.WARNING_MESSAGE);
         return;
      }
      
      if (cboClass.getSelectedIndex() < 0)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("form.resourceRequest.noSelectedClass") ,
                                       language.getProperty("app.title"), 
                                       JOptionPane.WARNING_MESSAGE);
         return;
      }

      // Obtiene los parámetros del formulario
      java.util.Date utilDate = new java.util.Date();
      java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
      
      try 
      {
         // Añade el stock
         rmiManager.setPeticionRecursos(((Aula)cboClass.getSelectedItem()).getId(),
                                        ((Recurso)cboResourceType.getSelectedItem()).getIdRecurso(),
                                        Integer.parseInt(txtNumItems.getText()),
                                        sqlDate);
         
         // Cierra el formulario
         this.dispose();
      } 
      catch (SQLException ex)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (DuplicatedRequestException ex)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.duplicatedRequest"), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.WARNING_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (TooManyRequestsException ex)
      {
         // Obtiene el aula
         String aula = ((Aula)cboClass.getSelectedItem()).getNombre();

         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.tooManyRequests"), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.WARNING_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (RemoteException ex) 
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.rmi") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      } 
      catch (Exception ex) 
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.generic") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }//GEN-LAST:event_cmdAcceptActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboClass;
    private javax.swing.JComboBox cboResourceType;
    private javax.swing.JButton cmdAccept;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblNumItems;
    private javax.swing.JLabel lblResource;
    private javax.swing.JTextField txtNumItems;
    // End of variables declaration//GEN-END:variables
}
