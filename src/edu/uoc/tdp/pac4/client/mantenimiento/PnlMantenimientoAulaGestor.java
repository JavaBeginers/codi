package edu.uoc.tdp.pac4.client.mantenimiento;

import edu.uoc.tdp.pac4.beans.Aula;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**********************************************************************
 ******************** @author JavaBeginers - Cristian******************
 *********************************************************************/

public class PnlMantenimientoAulaGestor extends javax.swing.JDialog 
{
    private Mantenimiento manager;
    private LanguageUtils language;
    private String ActionType;
    private Date now  = new Date();
    private Aula aula = null;
    private ArrayList<Aula> aulasinactivas = null;
    private int aulaID;
    private int LONG_NOMBRE = 30;
    private int LONG_UBICACION = 50;
   
    /*************************Formulario Gestor Aula**********************
    *********************Consulta, alta i mofificación de aulas***********
    *********************************************************************/
    
    public PnlMantenimientoAulaGestor(java.awt.Frame parent, boolean modal, Mantenimiento manager, LanguageUtils language, String ActionType, int aulaID) 
    {
        super(parent, modal);
        initComponents();
      
        setLocationRelativeTo(null);

        this.manager    = manager;
        this.language   = language;
        this.ActionType = ActionType;
        this.aulaID     = aulaID;
      
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
        lblCapacidad = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        fldCapacidad = new javax.swing.JTextField();
        fldUbicacion = new javax.swing.JTextField();
        cmdAccept = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        fldCodigo = new javax.swing.JTextField();
        lblFechaAlta = new javax.swing.JLabel();
        lblCentro = new javax.swing.JLabel();
        fldFechaAlta = new javax.swing.JTextField();
        comboCentro = new javax.swing.JComboBox();

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

        lblCapacidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCapacidad.setText("Capacidad");

        lblUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUbicacion.setText("Ubicación");

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

        lblCentro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCentro.setText("Centro");

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
                            .addComponent(lblUbicacion)
                            .addComponent(lblCapacidad)
                            .addComponent(lblCodigo)
                            .addComponent(lblCentro)
                            .addComponent(lblFechaAlta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(fldUbicacion)
                            .addComponent(fldCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fldFechaAlta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCentro, 0, 278, Short.MAX_VALUE)
                            .addComponent(fldCapacidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCapacidad)
                    .addComponent(fldCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUbicacion)
                    .addComponent(fldUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaAlta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCentro)
                    .addComponent(comboCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose)
                    .addComponent(cmdAccept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
//Define el texto de las etiquetas en función del idioma seleccionado
   private void prepararEtiquetas() {

        lblNombre.setText     (language.getProperty("mantenimiento.aulas.Nombre"));
        lblCapacidad.setText (language.getProperty("mantenimiento.aulas.Capacidad"));
        lblUbicacion.setText    (language.getProperty("mantenimiento.aulas.Ubicacion"));
        lblCodigo.setText (language.getProperty("mantenimiento.aulas.Codigo"));      
        lblFechaAlta.setText (language.getProperty("mantenimiento.aulas.FechaAlta"));    
        lblCentro.setText (language.getProperty("mantenimiento.aulas.Centro"));    
        cmdClose.setText(language.getProperty("mantenimiento.Cerrar"));
        
        setCentros();
        
        //Establece longitud de los campos
        this.fldNombre.setDocument(new FieldLimit(LONG_NOMBRE));
        this.fldUbicacion.setDocument(new FieldLimit(LONG_UBICACION));
    }
   
//Prepara el formulario para Alta de Aula
   private void adaptarAlta(){
        
        this.setTitle(language.getProperty("mantenimiento.Nueva") + " - " + 
                      language.getProperty("mantenimiento.main.aula"));
       
       try {
            this.aulasinactivas = manager.getAulasInactivas();
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
       
       fldNombre.setText     ("");
       fldCapacidad.setText ("");
       fldUbicacion.setText    ("");
       this.fldCodigo.setText("");
        this.fldFechaAlta.setText(this.now.toString());
       this.fldFechaAlta.setEditable(false);
 
                  
       this.cmdAccept.setText(language.getProperty("mantenimiento.usermain.newUser"));
    }

//Prepara el formulario para Edición de Aula    
   private void adaptarEdicion(){
        
        this.setTitle(language.getProperty("mantenimiento.Editar") + " - " + 
                      language.getProperty("mantenimiento.main.aula"));
            
        try {
            this.aula = manager.getAula(this.aulaID);
            this.fldCodigo.setText     (""+aula.getCodigo());
            this.fldNombre.setText     (aula.getNombre());
            this.fldCapacidad.setText  ("" + aula.getCapacidad());
            this.fldUbicacion.setText  (aula.getUbicacion());
            this.fldFechaAlta.setText  (""+aula.getFechaAlta());
            this.comboCentro.getItemAt (aula.getCentro()-1);
            
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
    
//Prepara el formulario para Consulta de Aula    
   private void adaptarConsulta() {
        this.adaptarEdicion();
        this.fldCodigo.setEditable(false);
        this.fldFechaAlta.setEditable(false);
        this.fldNombre.setEditable(false);
        this.fldCapacidad.setEditable(false);
        this.fldUbicacion.setEditable(false);
        this.cmdAccept.setVisible(false);
        this.comboCentro.setEditable(false);
    }
  
//Cerrar Formulario
   private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
    this.dispose();
   }//GEN-LAST:event_cmdCloseActionPerformed

   private void cmdAddAulaAction() {

        try {
            this.aula = new Aula();
            aula.setCodigo(new Integer(this.fldCodigo.getText()));
            aula.setNombre(this.fldNombre.getText());
            aula.setCapacidad(new Integer(this.fldCapacidad.getText()));
            aula.setUbicacion(this.fldUbicacion.getText());
            aula.setFechaAlta(this.now);
            aula.setCentro(((ComboItem)comboCentro.getSelectedItem()).getId());
       
            if (manager.addAula(aula)) {
                JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.add.aula"),
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
    
   private void cmdModAulaAction() {
        try {
            Aula new_aula = new Aula();
            new_aula.setId(this.aulaID);
            new_aula.setCodigo(new Integer(this.fldCodigo.getText()));
            new_aula.setNombre(this.fldNombre.getText());
            new_aula.setCapacidad(new Integer(this.fldCapacidad.getText()));
            new_aula.setUbicacion(this.fldUbicacion.getText());
            new_aula.setCentro(this.comboCentro.getSelectedIndex()-1);         
            new_aula.setFechaAlta(this.aula.getFechaAlta());

            
            if (this.aula.getFechaBaja() != null) {
                new_aula.setFechaBaja (this.aula.getFechaBaja());
            }
            else {
                new_aula.setFechaBaja (this.aula.getFechaAlta());
            }
            
            
            Object[] options = {language.getProperty("opt.si"), language.getProperty("opt.no")};
            int reply = JOptionPane.showOptionDialog(this, language.getProperty("mantenimiento.msg.confirm"), language.getProperty("app.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, now);
            if (reply == 0) {
                if (manager.updateAula(new_aula)) {
                    JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.modif"),
                                                  "Información", JOptionPane.INFORMATION_MESSAGE);
                }
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
    
    private void cmdAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAcceptActionPerformed
        /*
         * Las acciones sólo se llevaran a cabo si tenemos TODOS los campos llenos
         */
        if (!this.CompruebaCampos()) {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty("mantenimiento.err.fields"), 
                                          language.getProperty("app.title"), 
                                          JOptionPane.ERROR_MESSAGE);
        } else {
            if (this.ActionType.equalsIgnoreCase("Add")) {
                this.cmdAddAulaAction();
            }
            else if (this.ActionType.equalsIgnoreCase("Edit")) {
                this.cmdModAulaAction();
            }
        }
    }//GEN-LAST:event_cmdAcceptActionPerformed
   
   private void setCentros() {
        comboCentro.removeAll();
        List<Centro> centros = new ArrayList<Centro>();
        try {
            centros = manager.getCentros();
        } catch (Exception ex) {
            Logger.getLogger(PnlMantenimientoActividadGestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboCentro.addItem(new ComboItem(language.getProperty(eAcademiaEU.FORM_PNLACTIVIDAD_CENTRO_SELECCIONA), -1));
        for(Centro centro: centros) {
        comboCentro.addItem(new ComboItem(centro.getNom(),centro.getId()));
        }  
        
    }
   
   private boolean CompruebaCampos() {
        //Comprova que tots els camps obligatoris estiguinc omplerts. 
        if (fldNombre.getText().isEmpty()     || fldNombre.getText().equals(""))     {return false;}
        if (fldCapacidad.getText().isEmpty() || fldCapacidad.getText().equals("")) {return false;}
        if (fldUbicacion.getText().isEmpty()    || fldUbicacion.getText().equals(""))    {return false;}

        return true;
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAccept;
    private javax.swing.JButton cmdClose;
    private javax.swing.JComboBox comboCentro;
    private javax.swing.JTextField fldCapacidad;
    private javax.swing.JTextField fldCodigo;
    private javax.swing.JTextField fldFechaAlta;
    private javax.swing.JTextField fldName1;
    private javax.swing.JTextField fldNombre;
    private javax.swing.JTextField fldUbicacion;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCapacidad;
    private javax.swing.JLabel lblCentro;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblUbicacion;
    // End of variables declaration//GEN-END:variables
}
