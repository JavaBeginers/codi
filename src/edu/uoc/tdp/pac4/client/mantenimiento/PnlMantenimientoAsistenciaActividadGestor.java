package edu.uoc.tdp.pac4.client.mantenimiento;

import edu.uoc.tdp.pac4.util.DateTimeUtils;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Centro;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.remote.Mantenimiento;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import edu.uoc.tdp.pac4.util.FieldLimit;

import edu.uoc.tdp.pac4.eAcademiaEU;
import edu.uoc.tdp.pac4.util.ComboItem;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author eSupport Netbeans
 */
public class PnlMantenimientoAsistenciaActividadGestor extends javax.swing.JDialog {

    private final Mantenimiento manager;
    private final LanguageUtils language;
    private List<ComboItem> tiposActividad;
    private List<ComboItem> universidades;

    private final String actionType;

    private final Date now = new Date();
    private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private Actividad actividad = null;
    private final int actividadID;
    private final Usuario usuario;

    /**
     * Creates new form PnlGroupGestor
     *
     * @param parent
     * @param modal
     * @param manager
     */
    public PnlMantenimientoAsistenciaActividadGestor(PnlMantenimientoActividades parent, boolean modal, Mantenimiento manager, LanguageUtils language, String actionType, int actividadID, Usuario usuario) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.manager = manager;
        this.language = language;
        this.actionType = actionType;
        this.actividadID = actividadID;
        this.usuario = usuario;

        addaptToPreferences();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        cmdClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        cmdClearFilter = new javax.swing.JButton();
        cmdFilter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/arrow-curve-180.png"))); // NOI18N
        cmdClose.setText("Cerrar");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblData);

        cmdClearFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/action_001.png"))); // NOI18N
        cmdClearFilter.setText("Ha asistido");
        cmdClearFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearFilterActionPerformed(evt);
            }
        });

        cmdFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/object_delete.gif"))); // NOI18N
        cmdFilter.setText("No ha asistido");
        cmdFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdClearFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdClose)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdFilter)
                    .addComponent(cmdClearFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(cmdClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setLabelsLanguage() {
        /*
         * Definimos el texto de las labels del panel en función del idioma seleccionado
         */
//        lblTipoActividad.setText(language.getProperty("mantenimiento.actividad.tipo.actividad"));
//        lblUniversidad.setText(language.getProperty("mantenimiento.actividad.universidad"));
//        lblCentro.setText(language.getProperty("mantenimiento.actividad.centro"));
//        lblSitio.setText(language.getProperty("mantenimiento.actividad.sitio"));
//        lblAreaConocimiento.setText(language.getProperty("mantenimiento.actividad.area.conocimiento"));
//        lblEspecialización.setText(language.getProperty("mantenimiento.actividad.area.especializacion"));
//        lblTitulo.setText(language.getProperty("mantenimiento.actividad.titulo"));
//        lblDecanatura.setText(language.getProperty("mantenimiento.actividad.decanatura"));
//        lblInvestigador.setText(language.getProperty("mantenimiento.actividad.investigador"));
//        lblCambios.setText(language.getProperty("mantenimiento.actividad.cambios"));
//        lblPrecio.setText(language.getProperty("mantenimiento.actividad.precio"));
//        lblDateIni.setText(language.getProperty("mantenimiento.actividad.fechaini"));
//        lblDateFin.setText(language.getProperty("mantenimiento.actividad.fechaend"));
//        lblDateMaximaInscripcion.setText(language.getProperty("mantenimiento.actividad.fechamaximainscripcion"));
//        cbCancelada.setText(language.getProperty("mantenimiento.actividad.cancelada"));

        this.cmdClose.setText(language.getProperty("mantenimiento.usermain.back"));
    }

    private void addaptToPreferences() {
        /*
         * El mismo panel se usa para añadir/modificar usuarios, hay que adaptar ciertas características
         * en función de qué se solicite
         */
        this.setLabelsLanguage();

//        this.fldTitulo.setDocument(new FieldLimit(NAME_LENGTH));

        // Cambios Vinculados al tipo de llamada
        if (this.actionType.equalsIgnoreCase("Add")) {
            this.addaptToAddActividad();
        } else if (this.actionType.equalsIgnoreCase("Edit")) {
            this.addaptToEditActividad();
        }
    }

    private void addaptToAddActividad() {

        //Titulo de la cabecera
        this.setTitle(language.getProperty("mantenimiento.main.title") + ". "
                + language.getProperty("mantenimiento.main.aula") + ". "
                + language.getProperty("mantenimiento.usermain.newUser"));

        //Inicializamos el combo de tipo de actividad

    }

    private void addaptToEditActividad() {

        this.setTitle(language.getProperty("mantenimiento.main.title") + ". "
                + language.getProperty("mantenimiento.main.user") + ". "
                + language.getProperty("mantenimiento.usermain.modUser"));

        /*
        * Cuando vamos a modificar un usuario, inicializamos los formularios con los valores
        * que tiene el usuario
         */
        try {
            this.actividad = manager.getActividad(this.actividadID);

            //Inicializamos el combo de tipo de actividad
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(),
                    language.getProperty("app.title"),
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    language.getProperty("err.generic") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(),
                    language.getProperty("app.title"),
                    JOptionPane.ERROR_MESSAGE);

        }

    }

   private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed

       /*
     * Cerramos el formulario
        */
       this.dispose();

   }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdAddActividadAction() {
        /*
         * Gestión de Añadir Nuevo Usuario
         * Creamos una instancia Usuario con toda la info recibida y luego la pasamos al manager
         * para que la incluya en la BD
         * 
         * Si añadimos correctamente se cierra el panel
         */

//        try {
//            this.actividad = new Actividad();
//
//            actividad.setTipus(((ComboItem) cboTipoActividad.getSelectedItem()).getId());
//            actividad.setUniversitatId(((ComboItem) cboUniversidad.getSelectedItem()).getId());
//            actividad.setCentreId(((ComboItem) cboCentro.getSelectedItem()).getId());
//            actividad.setAulaId(((ComboItem) cboSitio.getSelectedItem()).getId());
//            actividad.setArea(fldAreaConocimiento.getText());
//            actividad.setEspecialitat(fldEspecializacion.getText());
//            actividad.setTitol(fldTitulo.getText());
//            actividad.setDecanatura(fldDecanatura.getText());
//            actividad.setInvestigator(fldInvestigador.getText());
//            try {
//                actividad.setMinimPercentatge(new Double(this.fldCambios.getText()));
//            } catch (NumberFormatException ex) {
//            }
//            try {
//                actividad.setPreu(new Double(fldPrecio.getText()));
//            } catch (NumberFormatException ex) {
//            }
//            actividad.setDataInici(iniActividad);
//            actividad.setDataFi(endActividad);
//            actividad.setDataMaxInscripcio(DateTimeUtils.strToDate(this.fldDateMaximaInscripcion.getText()));
//            actividad.setCancelada(cbCancelada.isSelected());
//
//            ArrayList<Actividad> actividades = manager.getActividadesInactivas();
//
//            for (Actividad oldActividad : actividades) {
//                if (actividad.getTitol().equalsIgnoreCase(oldActividad.getTitol())) {
//                    if (manager.undeleteActividad(oldActividad.getId())) {
//                        JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.add.oldcurs"),
//                                "Información", JOptionPane.INFORMATION_MESSAGE);
//                        this.dispose();
//                        return;
//                    }
//                }
//            }
//
//            if (manager.addActividad(actividad)) {
//                JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.add.curs"),
//                        "Información", JOptionPane.INFORMATION_MESSAGE);
//                this.dispose();
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,
//                    language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(),
//                    language.getProperty("app.title"),
//                    JOptionPane.ERROR_MESSAGE);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,
//                    language.getProperty("err.generic") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(),
//                    language.getProperty("app.title"),
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }

    private void cmdModActividadAction() {
        /*
         * Gestión de modificar Usuario
         * Creamos una instancia Usuario con toda la info recibida y luego la pasamos al manager
         * para que la actualize la BD (no se accede al UserID)
         * 
         * Para la modificacion solicitaremos confirmación
         * 
         * Si modificamos correctamente se cierra el panel
         */
//        try {
//
//            Date iniActividad = DateTimeUtils.strToDate(this.fldDateIni.getText());
//            Date endActividad = DateTimeUtils.strToDate(this.fldDateFin.getText());
//
//            if (!DateTimeUtils.isDate(this.fldDateIni.getText())
//                    || !DateTimeUtils.isDate(this.fldDateMaximaInscripcion.getText())) {
//                JOptionPane.showMessageDialog(null,
//                        language.getProperty("mantenimiento.err.date.mal"),
//                        language.getProperty("app.title"),
//                        JOptionPane.ERROR_MESSAGE);
//                return;
//            } else if (endActividad.before(iniActividad)) {
//                JOptionPane.showMessageDialog(null,
//                        "fchas mal orden",
//                        language.getProperty("mantenimiento.err.date.reverse"),
//                        JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            Actividad new_actividad = new Actividad();
//
//            new_actividad.setId(this.actividadID);
//
//            new_actividad.setTipus(((ComboItem) cboTipoActividad.getSelectedItem()).getId());
//            new_actividad.setUniversitatId(((ComboItem) cboUniversidad.getSelectedItem()).getId());
//            new_actividad.setCentreId(((ComboItem) cboCentro.getSelectedItem()).getId());
//            new_actividad.setAulaId(((ComboItem) cboSitio.getSelectedItem()).getId());
//            new_actividad.setArea(fldAreaConocimiento.getText());
//            new_actividad.setEspecialitat(fldEspecializacion.getText());
//            new_actividad.setTitol(fldTitulo.getText());
//            new_actividad.setDecanatura(fldDecanatura.getText());
//            new_actividad.setInvestigator(fldInvestigador.getText());
//            try {
//                new_actividad.setMinimPercentatge(new Double(this.fldCambios.getText()));
//            } catch (NumberFormatException ex) {
//            }
//            try {
//                new_actividad.setPreu(new Double(fldPrecio.getText()));
//            } catch (NumberFormatException ex) {
//            }
//            new_actividad.setDataInici(iniActividad);
//            new_actividad.setDataFi(endActividad);
//            new_actividad.setDataMaxInscripcio(DateTimeUtils.strToDate(this.fldDateMaximaInscripcion.getText()));
//            new_actividad.setCancelada(cbCancelada.isSelected());
//
//            Object[] options = {language.getProperty("opt.si"), language.getProperty("opt.no")};
//            int reply = JOptionPane.showOptionDialog(this, language.getProperty("mantenimiento.msg.confirm"), language.getProperty("app.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, now);
//            if (reply == 0) {
//                if (manager.updateActividad(new_actividad)) {
//                    JOptionPane.showMessageDialog(null, language.getProperty("mantenimiento.msg.modif"),
//                            "Información", JOptionPane.INFORMATION_MESSAGE);
//                }
//            }
//            this.dispose();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,
//                    language.getProperty("err.sql") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(),
//                    language.getProperty("app.title"),
//                    JOptionPane.ERROR_MESSAGE);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,
//                    language.getProperty("err.generic") + "\n" + language.getProperty("err.detail") + ":\n\n" + ex.getMessage(),
//                    language.getProperty("app.title"),
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }

    private void cmdClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdClearFilterActionPerformed

    private void cmdFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFilterActionPerformed

    }//GEN-LAST:event_cmdFilterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClearFilter;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
