package edu.uoc.tdp.pac4.client.mantenimiento;

import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.Recurso;
import edu.uoc.tdp.pac4.beans.Centro;
import edu.uoc.tdp.pac4.eAcademiaEU;
import edu.uoc.tdp.pac4.remote.Mantenimiento;
import edu.uoc.tdp.pac4.util.ComboItem;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.util.FieldLimit;

import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**********************************************************************
 ******************** @author JavaBeginers - Cristian******************
 *********************************************************************/

public class PnlMantenimientoRecursoGestor extends javax.swing.JDialog 
{
    private Mantenimiento manager;
    private LanguageUtils language;
    private String ActionType;
    private Date now  = new Date();
    private Recurso recurso = null;
    private int recursoID;
    private int LONG_NOMBRE = 30;
    private int LONG_CODIGO= 10;
    private int LONG_UBICACION = 50;
   
    /*************************Formulario Gestor Recurso**********************
    *********************Consulta, alta y mofificación de recursos***********
    *********************************************************************/
    
    public PnlMantenimientoRecursoGestor(java.awt.Frame parent, boolean modal, Mantenimiento manager, LanguageUtils language, String ActionType, int recursoID) 
    {
        super(parent, modal);
        initComponents();
      
        setLocationRelativeTo(null);

        this.manager    = manager;
        this.language   = language;
        this.ActionType = ActionType;
        this.recursoID     = recursoID;
      
        prepararEtiquetas();
        
        //Adaptar el formulario a la opción de Alta, edicion o consulta
        if (this.ActionType.equalsIgnoreCase("Add")) {
            this.adaptarAlta();
        } else if (this.ActionType.equalsIgnoreCase("Edit")) {
            this.adaptarEdicion();
        } else if (this.ActionType.equalsIgnoreCase("Explore")) {
            this.adaptarConsulta();
        }
   }

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fldName1 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        fldNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        cmdAccept = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        fldCodigo = new javax.swing.JTextField();
        lblFechaAlta = new javax.swing.JLabel();
        lblAula = new javax.swing.JLabel();
        fldFechaAlta = new javax.swing.JTextField();
        comboAula = new javax.swing.JComboBox();
        lblFechaBaja = new javax.swing.JLabel();
        fldFechaBaja = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        fldDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        cmdClose.setText("Cerrar");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombre.setText("Nombre");

        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescripcion.setText("Descripcion");

        cmdAccept.setText("Acceptar");
        cmdAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAcceptActionPerformed(evt);
            }
        });

        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCodigo.setText("Codigo");

        lblFechaAlta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaAlta.setText("Fecha Alta");

        lblAula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAula.setText("Aula");

        lblFechaBaja.setText("Fecha Baja");

        fldDescripcion.setColumns(20);
        fldDescripcion.setRows(5);
        jScrollPane1.setViewportView(fldDescripcion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClose))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblDescripcion)
                            .addComponent(lblCodigo)
                            .addComponent(lblAula)
                            .addComponent(lblFechaAlta)
                            .addComponent(lblFechaBaja))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAula, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fldFechaBaja, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fldFechaAlta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(fldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAula))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaAlta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaBaja)
                    .addComponent(fldFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose)
                    .addComponent(cmdAccept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
//Define el texto de las etiquetas en función del idioma seleccionado
   private void prepararEtiquetas() {

        lblNombre.setText     (language.getProperty("mantenimiento.recursos.Nombre"));
        lblDescripcion.setText (language.getProperty("mantenimiento.recursos.Descripcion"));
        lblCodigo.setText (language.getProperty("mantenimiento.recursos.Codigo"));      
        lblFechaAlta.setText (language.getProperty("mantenimiento.recursos.FechaAlta"));    
        lblAula.setText (language.getProperty("mantenimiento.Aula"));    
        cmdClose.setText(language.getProperty("mantenimiento.Cerrar"));
        lblFechaBaja.setText(language.getProperty("mantenimiento.recursos.FechaBaja")); 
        
        setAulas();
        
        //Establece longitud de los campos
        this.fldFechaAlta.setEditable(false);
        this.fldFechaBaja.setEditable(false);
        this.fldNombre.setDocument(new FieldLimit(LONG_NOMBRE));
        this.fldCodigo.setDocument(new FieldLimit(LONG_CODIGO));
        //this.fldDescripcion.setDocument()
    }
   
//Prepara el formulario para Alta de Recurso
   private void adaptarAlta(){
       this.setTitle(language.getProperty("mantenimiento.Nuevo") + " - " + 
                      language.getProperty("mantenimiento.Recurso"));
       
       fldNombre.setText     ("");
       fldDescripcion.setText  ("");
       this.fldCodigo.setText("");
       this.fldFechaAlta.setText(this.now.toString());
       this.fldFechaBaja.setVisible(false);
       this.lblFechaBaja.setVisible(false);
 
       this.cmdAccept.setText(language.getProperty("mantenimiento.usermain.newUser"));
    }

//Prepara el formulario para Edición de Recurso 
   private void adaptarEdicion(){
        
        this.setTitle(language.getProperty("mantenimiento.Editar") + " - " + 
                      language.getProperty("mantenimiento.Recurso"));
            
        try {
            this.recurso= manager.getRecurso(this.recursoID);
            this.fldCodigo.setText     (recurso.getCodigoRecurso());
            this.fldNombre.setText     (recurso.getNombreRecurso());
            this.fldDescripcion.setText  (recurso.getDescripcionRecurso());
            this.fldFechaAlta.setText  (recurso.getFechaAlta().toString());
            this.comboAula.getItemAt (recurso.getAulaRecurso()-1);
            this.fldFechaBaja.setVisible(true);
            this.lblFechaBaja.setVisible(true);
            if(recurso.getFechaBaja()==null){
            this.fldFechaBaja.setText ("");  
            }else{
            this.fldFechaBaja.setText  (""+recurso.getFechaBaja());
            }
            for (int i=0;i<comboAula.getItemCount();i++){
              if (recurso.getAulaRecurso()==((ComboItem)comboAula.getItemAt(i)).getId())  {
                comboAula.setSelectedIndex(i);
              }
            } 
        }
        catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                                       language.getProperty("err.rmi") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), 
                                       JOptionPane.ERROR_MESSAGE);
        }
         cmdAccept.setText(language.getProperty("mantenimiento.Editar"));
    }
    
//Prepara el formulario para Consulta de Recurso    
   private void adaptarConsulta() {
        this.adaptarEdicion();
        this.setTitle(language.getProperty("mantenimiento.Ver") + " - " + 
                      language.getProperty("mantenimiento.Recurso"));
        this.fldCodigo.setEditable(false);
        this.fldFechaAlta.setEditable(false);
        this.fldNombre.setEditable(false);
        this.fldDescripcion.setEditable(false);
        this.cmdAccept.setVisible(false);
        this.comboAula.setEnabled(false);
    }
  
//Cerrar Formulario
   private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
    this.dispose();
   }//GEN-LAST:event_cmdCloseActionPerformed

//Guardar nuevo Recurso
   private void guardarNuevoRecurso() {

        try {
            this.recurso = new Recurso();
            recurso.setCodigoRecurso(this.fldCodigo.getText());
            recurso.setNombreRecurso(this.fldNombre.getText());
            recurso.setDescripcionRecurso(this.fldDescripcion.getText());
            recurso.setFechaAltaRecurso(this.now);
            recurso.setAulaRecurso(((ComboItem)comboAula.getSelectedItem()).getId());
       
            if (manager.altaRecurso(recurso)) {
                JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.add.Recurso"),
                                              "Información", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        }
        catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, 
                                               language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                               language.getProperty("app.title"), 
                                               JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, 
                                               language.getProperty("err.generic") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                               language.getProperty("app.title"), 
                                               JOptionPane.ERROR_MESSAGE);
        }
    }
   
//Guardar los cambios del Recurso
   private void guardarEdicionRecurso() {
        try {
            Recurso new_recurso = new Recurso();
            new_recurso.setIdRecurso(this.recursoID);
            new_recurso.setCodigoRecurso(this.fldCodigo.getText());
            new_recurso.setNombreRecurso(this.fldNombre.getText());
            new_recurso.setDescripcionRecurso(this.fldDescripcion.getText());
            new_recurso.setAulaRecurso(((ComboItem)comboAula.getSelectedItem()).getId());       
            new_recurso.setFechaAltaRecurso(this.recurso.getFechaAlta());

            
            if (this.recurso.getFechaBaja() != null) {
                new_recurso.setFechaBajaRecurso (this.recurso.getFechaBaja());
            }
            else {
                new_recurso.setFechaBajaRecurso (this.recurso.getFechaAlta());
            }
            if (manager.actualizarRecurso(new_recurso)) {
                JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.modif"),
                                                  "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            this.dispose();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                          language.getProperty("app.title"), 
                                          JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty("err.rmi") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                          language.getProperty("app.title"), 
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
    
//Escoger opción del boton guardar (Alta o edición). Comprueba los datos
    private void cmdAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAcceptActionPerformed
       //Comprueba que los campos se han introducido correctamente
        if (!this.CompruebaCampos()) {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty("mantenimiento.err.Campos"), 
                                          language.getProperty("app.title"), 
                                          JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.ActionType.equalsIgnoreCase("Add")) {
                this.guardarNuevoRecurso();
            }
            else if (this.ActionType.equalsIgnoreCase("Edit")) {
                guardarEdicionRecurso();
            }
        }
    }//GEN-LAST:event_cmdAcceptActionPerformed
   
//Carga el Combo de aulas
  private void setAulas() {
        comboAula.removeAll();
        List<Aula> aulas = new ArrayList<Aula>();
        try {
            aulas = manager.getAulas();
        } catch (Exception ex) {
            Logger.getLogger(PnlMantenimientoActividadGestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.ActionType.equalsIgnoreCase("Add")) {
             comboAula.addItem(new ComboItem( language.getProperty("mantenimiento.msg.SeleccionAula"), 0));
        }
        for(Aula aula: aulas) {
        comboAula.addItem(new ComboItem(aula.getNombre(),aula.getId()));
        }  
        
    }
   
//Comprueba que los campos se han escrito correctamente
  private boolean CompruebaCampos() {
        //Comprueba que todos los campos esten correctos.
        if (fldNombre.getText().isEmpty()     || fldNombre.getText().equals(""))     {return false;}
        if (fldDescripcion.getText().isEmpty() || fldDescripcion.getText().equals("")) {return false;}
        if (fldCodigo.getText().isEmpty()    || fldCodigo.getText().equals(""))    {return false;}
        if (this.ActionType.equalsIgnoreCase("Add")&& comboAula.getSelectedIndex()==0)    {return false;}
        
       

        return true;
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAccept;
    private javax.swing.JButton cmdClose;
    private javax.swing.JComboBox comboAula;
    private javax.swing.JTextField fldCodigo;
    private javax.swing.JTextArea fldDescripcion;
    private javax.swing.JTextField fldFechaAlta;
    private javax.swing.JTextField fldFechaBaja;
    private javax.swing.JTextField fldName1;
    private javax.swing.JTextField fldNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblAula;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblFechaBaja;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}
