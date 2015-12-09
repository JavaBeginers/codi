package edu.uoc.tdp.pac4.client.mantenimiento;

import edu.uoc.tdp.pac4.beans.Aula;
import edu.uoc.tdp.pac4.remote.Mantenimiento;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.util.FieldLimit;

import java.rmi.RemoteException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author eSupport Netbeans
 */
public class PnlMantenimientoAulaGestor extends javax.swing.JDialog 
{
    private Mantenimiento manager;
    private LanguageUtils language;
    private String ActionType;
    private Date now  = new Date();
    private Aula aula = null;
    private ArrayList<Aula> aulasinactivas = null;
    private int aulaID;
    private int NAME_LENGTH        = 30;
    private int DESCRIPTION_LENGTH = 50;
    private int PLACE_LENGTH       = 50;
    
    private java.util.HashMap RolesDesc;
    private java.util.HashMap doubleDescription = new java.util.HashMap();
   
    /**
      * Creates new form PnlGroupGestor
      */
    public PnlMantenimientoAulaGestor(java.awt.Frame parent, boolean modal, Mantenimiento manager, LanguageUtils language, String ActionType, int aulaID) 
    {
        super(parent, modal);
        initComponents();
      
        setLocationRelativeTo(null);

        this.manager    = manager;
        this.language   = language;
        this.ActionType = ActionType;
        this.aulaID     = aulaID;
      
        
        addaptToPreferences();

   }


   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fldName1 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        fldName = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblCapacity = new javax.swing.JLabel();
        lblPlace = new javax.swing.JLabel();
        fldCapacity = new javax.swing.JTextField();
        fldPlace = new javax.swing.JTextField();
        cmdAccept = new javax.swing.JButton();
        lblCode = new javax.swing.JLabel();
        fldCode = new javax.swing.JTextField();
        lblFechaAlta = new javax.swing.JLabel();
        lblCenter = new javax.swing.JLabel();
        fldFechaAlta = new javax.swing.JTextField();
        comboCenter = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/arrow-curve-180.png"))); // NOI18N
        cmdClose.setText("Cerrar");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        lblName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblName.setText("Nombre");

        lblCapacity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCapacity.setText("Capacidad");

        lblPlace.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPlace.setText("Ubicación");

        cmdAccept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ok_st_obj.gif"))); // NOI18N
        cmdAccept.setText("Acceptar");
        cmdAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAcceptActionPerformed(evt);
            }
        });

        lblCode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCode.setText("Codigo");

        lblFechaAlta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaAlta.setText("Fecha Alta");

        lblCenter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCenter.setText("Centro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(202, Short.MAX_VALUE)
                        .addComponent(cmdAccept)
                        .addGap(45, 45, 45)
                        .addComponent(cmdClose))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lblPlace)
                            .addComponent(lblCapacity)
                            .addComponent(lblCode)
                            .addComponent(lblCenter)
                            .addComponent(lblFechaAlta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fldName, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(fldCapacity)
                            .addComponent(fldPlace)
                            .addComponent(fldCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fldFechaAlta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCenter, 0, 278, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCode)
                    .addComponent(fldCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCapacity)
                    .addComponent(fldCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlace)
                    .addComponent(fldPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fldFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaAlta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCenter)
                    .addComponent(comboCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose)
                    .addComponent(cmdAccept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  /*
    private void cargarComboCentros() {
  
        try {
            this.RolesDesc      = manager.getRolesByDesc();
            java.util.Set roles = this.RolesDesc.entrySet(); //Creamos diccionario de roles

            String[] possibleRoles  = new String[roles.size()];
            java.util.Iterator iter = roles.iterator();
            
            while (iter.hasNext()) {
                java.util.Map.Entry role = (java.util.Map.Entry)iter.next();
                
                doubleDescription.put(language.getProperty(role.getKey().toString()),
                                        role.getKey().toString());
                
                Integer rolID            = new Integer(role.getValue().toString());
                possibleRoles[rolID -1] = language.getProperty(role.getKey().toString());
            }
            cbxType.setModel(new javax.swing.DefaultComboBoxModel(possibleRoles));
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                          language.getProperty("app.title"), 
                                          JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty("err.rmi") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(), 
                                          language.getProperty("app.title"), 
                                          JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    */private boolean CompruebaCampos() {
        //Comprova que tots els camps obligatoris estiguinc omplerts. 
        if (fldName.getText().isEmpty()     || fldName.getText().equals(""))     {return false;}
        if (fldCapacity.getText().isEmpty() || fldCapacity.getText().equals("")) {return false;}
        if (fldPlace.getText().isEmpty()    || fldPlace.getText().equals(""))    {return false;}

        return true;
    }
    private void setLabelsLanguage() {
        /*
         * Definimos el texto de las labels del panel en función del idioma seleccionado
         */
        lblName.setText     (language.getProperty("mantenimiento.aulas.Nombre"));
        lblCapacity.setText (language.getProperty("mantenimiento.aulas.Capacidad"));
        lblPlace.setText    (language.getProperty("mantenimiento.aulas.Ubicacion"));
        lblCode.setText (language.getProperty("mantenimiento.aulas.Codigo"));      
        lblFechaAlta.setText (language.getProperty("mantenimiento.aulas.FechaAlta"));    
        lblCenter.setText (language.getProperty("mantenimiento.aulas.Centro"));    
       
        if (this.ActionType.equalsIgnoreCase("Add")) {
            this.cmdAccept.setText(language.getProperty("mantenimiento.Nueva"));
        }
        else if (this.ActionType.equalsIgnoreCase("Edit")){
            this.cmdAccept.setText(language.getProperty("mantenimiento.Editar"));
        }
        this.cmdClose.setText(language.getProperty("mantenimiento.Cerrar"));
    }
   
    private void addaptToPreferences() {
        /*
         * El mismo panel se usa para añadir/modificar usuarios, hay que adaptar ciertas características
         * en función de qué se solicite
         */
        this.setLabelsLanguage();
        
        this.fldName.setDocument(new FieldLimit(NAME_LENGTH));
        this.fldPlace.setDocument(new FieldLimit(PLACE_LENGTH));

                
        // Cambios Vinculados al tipo de llamada
        if (this.ActionType.equalsIgnoreCase("Add")) {
            this.addaptToAddAula();
        } else if (this.ActionType.equalsIgnoreCase("Edit")) {
            this.addaptToEditAula();
        } else if (this.ActionType.equalsIgnoreCase("Explore")) {
            this.addaptToExploreAula();
        }
    }
   
    private void addaptToAddAula(){
        
        this.setTitle(language.getProperty("mantenimiento.main.title") + ". " + 
                      language.getProperty("mantenimiento.main.aula")  + ". " +
                      language.getProperty("mantenimiento.usermain.newUser"));
       
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
       
       /*
        * Cuando vamos a añadir un nuevo usuario los parametros (excepto Fecha de Registro)
        * se inicializan en blanco
        * 
        * El usuario de creación por defecto es Alumno, que debería ser el más abundante
        */
       fldName.setText     ("");
       fldCapacity.setText ("");
       fldPlace.setText    ("");

            
       this.cmdAccept.setText(language.getProperty("mantenimiento.usermain.newUser"));
    }
   
    private void addaptToEditAula(){
        
        this.setTitle(language.getProperty("mantenimiento.main.title") + ". " + 
                      language.getProperty("mantenimiento.main.user")   + ". " +
                      language.getProperty("mantenimiento.usermain.modUser"));
       
       /*
        * Cuando vamos a modificar un usuario, inicializamos los formularios con los valores
        * que tiene el usuario
        */
        try {
            this.aula = manager.getAula(this.aulaID);
            this.fldCode.setText     (""+aula.getCodigo());
            this.fldName.setText     (aula.getNombre());
            this.fldCapacity.setText ("" + aula.getCapacidad());
            this.fldPlace.setText    (aula.getUbicacion());
            this.fldFechaAlta.setText    (""+aula.getFechaAlta());
            
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
           
        this.cmdAccept.setText(language.getProperty("mantenimiento.usermain.modUser")); 
    }
    
    private void addaptToExploreAula() {
        this.addaptToEditAula();
        this.fldName.setEditable(false);
        this.fldCapacity.setEditable(false);
        this.fldPlace.setEditable(false);
        this.cmdAccept.setVisible(false);
    }
   
   private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed

    /*
     * Cerramos el formulario
     */
    this.dispose();

   }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdAddAulaAction() {
        /*
         * Gestión de Añadir Nuevo Usuario
         * Creamos una instancia Usuario con toda la info recibida y luego la pasamos al manager
         * para que la incluya en la BD
         * 
         * Si añadimos correctamente se cierra el panel
         */
        try {
            this.aula = new Aula();
            aula.setCodigo(new Integer(this.fldCode.getText()));
            aula.setNombre(this.fldName.getText());
            aula.setCapacidad(new Integer(this.fldCapacity.getText()));
            aula.setUbicacion(this.fldPlace.getText());
            aula.setFechaAlta(this.now);
           // aula.setActiva(false);
            //aula.setFechaInactividad(this.now);
            
            for (Aula oldaula : this.aulasinactivas) {
              //  if (aula.compare(oldaula)) {
              if (1==1) {
                    if (manager.undeleteAula(oldaula.getId())) {
                        JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.add.oldaula"),
                                              "Información", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        return;
                    }
                    break;
                }
            }
                    
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
        /*
         * Gestión de odificar Usuario
         * Creamos una instancia Usuario con toda la info recibida y luego la pasamos al manager
         * para que la actualize la BD (no se accede al UserID)
         * 
         * Para la modificacion solicitaremos confirmación
         * 
         * Si modificamos correctamente se cierra el panel
         */
        try {
            
            Aula new_aula = new Aula();
            
            new_aula.setId(this.aulaID);
            new_aula.setCodigo(new Integer(this.fldCode.getText()));
            new_aula.setNombre(this.fldName.getText());
            new_aula.setCapacidad(new Integer(this.fldCapacity.getText()));
            new_aula.setUbicacion(this.fldPlace.getText());
            new_aula.setCentro(1);         
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
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAccept;
    private javax.swing.JButton cmdClose;
    private javax.swing.JComboBox comboCenter;
    private javax.swing.JTextField fldCapacity;
    private javax.swing.JTextField fldCode;
    private javax.swing.JTextField fldFechaAlta;
    private javax.swing.JTextField fldName;
    private javax.swing.JTextField fldName1;
    private javax.swing.JTextField fldPlace;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCapacity;
    private javax.swing.JLabel lblCenter;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPlace;
    // End of variables declaration//GEN-END:variables
}
