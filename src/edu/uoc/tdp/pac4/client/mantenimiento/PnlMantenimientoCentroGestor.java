package edu.uoc.tdp.pac4.client.mantenimiento;

import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.beans.AuxiliarCombo;
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

public class PnlMantenimientoCentroGestor extends javax.swing.JDialog 
{
    private Mantenimiento manager;
    private LanguageUtils language;
    private String ActionType;
    private Date now  = new Date();
    private Centro centro = null;
    private int centroID;
    private int LONG_NOMBRE = 30;
    private int LONG_CODIGO= 10;
    private int LONG_UBICACION = 50;
   
    /*************************Formulario Gestor Centroo**********************
    *********************Consulta, alta y mofificación de centro*s***********
    *********************************************************************/
    
    public PnlMantenimientoCentroGestor(java.awt.Frame parent, boolean modal, Mantenimiento manager, LanguageUtils language, String ActionType, int idcentro) 
    {
        super(parent, modal);
        initComponents();
      
        setLocationRelativeTo(null);

        this.manager    = manager;
        this.language   = language;
        this.ActionType = ActionType;
        this.centroID     = idcentro;
      
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
        cmdAccept = new javax.swing.JButton();
        lblFechaAlta = new javax.swing.JLabel();
        lblUni = new javax.swing.JLabel();
        fldFechaAlta = new javax.swing.JTextField();
        comboUni = new javax.swing.JComboBox();
        lblFechaBaja = new javax.swing.JLabel();
        fldFechaBaja = new javax.swing.JTextField();
        comboPais = new javax.swing.JComboBox();
        lblPais = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        fldDireccion = new javax.swing.JTextField();
        lblPoblacion = new javax.swing.JLabel();
        fldPoblacion = new javax.swing.JTextField();
        fldCP = new javax.swing.JTextField();
        fldTfn = new javax.swing.JTextField();
        fldURL = new javax.swing.JTextField();
        fldMail = new javax.swing.JTextField();
        lblCP = new javax.swing.JLabel();
        lblTfn = new javax.swing.JLabel();
        lblUrl = new javax.swing.JLabel();
        lblMail = new javax.swing.JLabel();

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

        cmdAccept.setText("Acceptar");
        cmdAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAcceptActionPerformed(evt);
            }
        });

        lblFechaAlta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaAlta.setText("Fecha Alta");

        lblUni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUni.setText("Universidad");

        lblFechaBaja.setText("Fecha Baja");

        lblPais.setText("País");

        lblDireccion.setText("Direccion");

        lblPoblacion.setText("Población");

        lblCP.setText("CP");

        lblTfn.setText("Teléfono");

        lblUrl.setText("URL");

        lblMail.setText("Mail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmdAccept)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblFechaAlta)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fldFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblFechaBaja)
                                    .addGap(18, 18, 18)
                                    .addComponent(fldFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClose))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblUni)
                            .addComponent(lblPais)
                            .addComponent(lblDireccion)
                            .addComponent(lblPoblacion)
                            .addComponent(lblCP)
                            .addComponent(lblTfn)
                            .addComponent(lblUrl)
                            .addComponent(lblMail))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fldMail)
                            .addComponent(fldURL)
                            .addComponent(comboUni, 0, 278, Short.MAX_VALUE)
                            .addComponent(fldNombre)
                            .addComponent(comboPais, javax.swing.GroupLayout.Alignment.TRAILING, 0, 278, Short.MAX_VALUE)
                            .addComponent(fldDireccion)
                            .addComponent(fldPoblacion)
                            .addComponent(fldCP, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fldTfn))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(fldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUni)
                    .addComponent(comboUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPais))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPoblacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldTfn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTfn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUrl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaAlta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fldFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaBaja))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose)
                    .addComponent(cmdAccept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
//Define el texto de las etiquetas en función del idioma seleccionado
   private void prepararEtiquetas() {

        lblNombre.setText     (language.getProperty("mantenimiento.centros.Nombre"));
        lblUni.setText(language.getProperty("mantenimiento.centros.Universidad"));
        lblPais.setText (language.getProperty("mantenimiento.centros.Pais"));
        lblDireccion.setText (language.getProperty("mantenimiento.centros.Direccion")); 
        lblPoblacion.setText (language.getProperty("mantenimiento.centros.Poblacion"));
        lblCP.setText (language.getProperty("mantenimiento.centros.CP"));
        lblTfn.setText (language.getProperty("mantenimiento.centros.Tlf"));
        lblUrl.setText (language.getProperty("mantenimiento.centros.url"));
        lblMail.setText (language.getProperty("mantenimiento.centros.mail"));
        lblFechaAlta.setText (language.getProperty("mantenimiento.recursos.FechaAlta"));        
        cmdClose.setText(language.getProperty("mantenimiento.Cerrar"));
        lblFechaBaja.setText(language.getProperty("mantenimiento.recursos.FechaBaja")); 
        
        setCombos();
        
        //Establece longitud de los campos
        this.fldFechaAlta.setEditable(false);
        this.fldFechaBaja.setEditable(false);
        this.fldNombre.setDocument(new FieldLimit(LONG_NOMBRE));
        //this.fldDescripcion.setDocument()
    }
   
//Prepara el formulario para Alta de Recurso
   private void adaptarAlta(){
       this.setTitle(language.getProperty("mantenimiento.Nuevo") + " - " + 
                      language.getProperty("mantenimiento.Centro"));
       
       this.fldFechaAlta.setText(this.now.toString());
       this.fldFechaBaja.setVisible(false);
       this.lblFechaBaja.setVisible(false);
 
       this.cmdAccept.setText(language.getProperty("mantenimiento.usermain.newUser"));
    }

//Prepara el formulario para Edición de Recurso 
   private void adaptarEdicion(){
        
        this.setTitle(language.getProperty("mantenimiento.Editar") + " - " + 
                      language.getProperty("mantenimiento.Centro"));
            
        try {
            this.centro= manager.getCentro(this.centroID);
            this.fldNombre.setText     (centro.getNom());
            this.fldDireccion.setText  (centro.getAdreca());
            this.fldCP.setText(centro.getCP());
            this.fldMail.setText(centro.getEmail());
            this.fldPoblacion.setText(centro.getPoblacio());
            this.fldTfn.setText(centro.getTelf());
            this.fldURL.setText(centro.getURL());
            
            this.fldFechaAlta.setText  (centro.getDataAlta().toString());
            //this.comboUni.getItemAt (centro.getAulaRecurso()-1);
            this.fldFechaBaja.setVisible(true);
            this.lblFechaBaja.setVisible(true);
            if(centro.getDataBaixa()==null){
            this.fldFechaBaja.setText ("");  
            }else{
            this.fldFechaBaja.setText  (""+centro.getDataBaixa());
            }
           /* for (int i=0;i<comboUni.getItemCount();i++){
              if (recurso.getAulaRecurso()==((ComboItem)comboUni.getItemAt(i)).getId())  {
                comboUni.setSelectedIndex(i);
              }
            } */
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
                      language.getProperty("mantenimiento.Centro"));
        
        this.fldCP.setEditable(false);
        this.fldFechaAlta.setEditable(false);
        this.fldNombre.setEditable(false);
        this.fldPoblacion.setEditable(false);
        this.fldMail.setEditable(false);
        this.fldTfn.setEditable(false);
        this.fldDireccion.setEditable(false);
        this.fldURL.setEditable(false);
        this.fldFechaBaja.setEditable(false);
    
        
        this.cmdAccept.setVisible(false);
        this.comboUni.setEnabled(false);
        this.comboPais.setEnabled(false);
    }
  
//Cerrar Formulario
   private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
    this.dispose();
   }//GEN-LAST:event_cmdCloseActionPerformed

//Guardar nuevo Recurso
   private void guardarNuevoCentro() {

        try {
            this.centro = new Centro();
            centro.setAdreca(fldDireccion.getText());
            centro.setCP(fldCP.getText());
            centro.setEmail(fldMail.getText());
            centro.setNom(fldNombre.getText());
            centro.setPais(((ComboItem)comboPais.getSelectedItem()).getId());
            centro.setPoblacio(fldPoblacion.getText());
            centro.setTelf(fldTfn.getText());
            centro.setURL(fldURL.getText());
            centro.setUniversitat(((ComboItem)comboUni.getSelectedItem()).getId());
            centro.setDataAlta(this.now);
       
            if (manager.addCentro(centro)) {
                JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.add.Centro"),
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
   private void guardarEdicionCentro() {
        try {
            Centro new_centro = new Centro();
            new_centro.setId(this.centroID);
            new_centro.setAdreca(fldDireccion.getText());
            new_centro.setCP(fldCP.getText());
            new_centro.setEmail(fldMail.getText());
            new_centro.setNom(fldNombre.getText());
            new_centro.setPais(((ComboItem)comboPais.getSelectedItem()).getId());
            new_centro.setPoblacio(fldPoblacion.getText());
            new_centro.setTelf(fldTfn.getText());
            new_centro.setURL(fldURL.getText());
            new_centro.setUniversitat(((ComboItem)comboUni.getSelectedItem()).getId());
            new_centro.setDataAlta(this.now);

            
            if (this.centro.getDataBaixa() != null) {
                new_centro.setDataBaixa (this.centro.getDataBaixa());
            }

            if (manager.updateCentro(new_centro)) {
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
                this.guardarNuevoCentro();
            }
            else if (this.ActionType.equalsIgnoreCase("Edit")) {
                guardarEdicionCentro();
            }
        }
    }//GEN-LAST:event_cmdAcceptActionPerformed
   
//Carga el Combo de aulas
  private void setCombos() {
        comboUni.removeAll();
            comboUni.removeAll();
        List<AuxiliarCombo> universidades = new ArrayList<AuxiliarCombo>();
        try {
           universidades= manager.getUniversidades();
        } catch (Exception ex) {
            Logger.getLogger(PnlMantenimientoCentroGestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(AuxiliarCombo universidad: universidades) {
        comboUni.addItem(new ComboItem(universidad.getNombre(),universidad.getId()));
        } 

        comboPais.removeAll();
        List<AuxiliarCombo> paises = new ArrayList<AuxiliarCombo>();
        try {
           paises= manager.getPaises();
        } catch (Exception ex) {
            Logger.getLogger(PnlMantenimientoCentroGestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(AuxiliarCombo pais:paises) {
        comboPais.addItem(new ComboItem(pais.getNombre(),pais.getId()));
        } 
        
        
    }
   
//Comprueba que los campos se han escrito correctamente
  private boolean CompruebaCampos() {
        //Comprueba que todos los campos esten correctos.
        /*if (fldNombre.getText().isEmpty()     || fldNombre.getText().equals(""))     {return false;}
        if (fldDescripcion.getText().isEmpty() || fldDescripcion.getText().equals("")) {return false;}
        if (fldCodigo.getText().isEmpty()    || fldCodigo.getText().equals(""))    {return false;}
        if (this.ActionType.equalsIgnoreCase("Add")&& comboUni.getSelectedIndex()==0)    {return false;}
        */
       

        return true;
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAccept;
    private javax.swing.JButton cmdClose;
    private javax.swing.JComboBox comboPais;
    private javax.swing.JComboBox comboUni;
    private javax.swing.JTextField fldCP;
    private javax.swing.JTextField fldDireccion;
    private javax.swing.JTextField fldFechaAlta;
    private javax.swing.JTextField fldFechaBaja;
    private javax.swing.JTextField fldMail;
    private javax.swing.JTextField fldName1;
    private javax.swing.JTextField fldNombre;
    private javax.swing.JTextField fldPoblacion;
    private javax.swing.JTextField fldTfn;
    private javax.swing.JTextField fldURL;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCP;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblFechaBaja;
    private javax.swing.JLabel lblMail;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPoblacion;
    private javax.swing.JLabel lblTfn;
    private javax.swing.JLabel lblUni;
    private javax.swing.JLabel lblUrl;
    // End of variables declaration//GEN-END:variables
}
