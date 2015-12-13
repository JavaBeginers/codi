package edu.uoc.tdp.pac4.client.gestion;

import edu.uoc.tdp.pac4.beans.Asistencia;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.exceptions.GroupAlreadyCountedException;
import edu.uoc.tdp.pac4.remote.GestAcademica;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import edu.uoc.tdp.pac4.eAcademiaEU;

/**
 * Formulario de recuento de asistencia.
 * 
 * @author JavaBeginers
 */
public class PnlAsistencia extends javax.swing.JDialog 
{
   private GestAcademica manager;
   private LanguageUtils language;
   private Usuario usuario;
   private Asistencia asistencia;

   /**
    * Creates new form PnlAsistencia
    */
   public PnlAsistencia(java.awt.Frame parent, boolean modal, GestAcademica manager, LanguageUtils language, Usuario usuario) 
   {
      super(parent, modal);
      initComponents();
      
      this.manager = manager;
      this.language = language;
      this.usuario = usuario;    // profesor
      
      setLanguage();
      fillForm();
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      cboGrupo = new javax.swing.JComboBox();
      lblGrupo = new javax.swing.JLabel();
      jPanel1 = new javax.swing.JPanel();
      lblLabelCurso = new javax.swing.JLabel();
      lblCurso = new javax.swing.JLabel();
      lblLabelProfe = new javax.swing.JLabel();
      lblProfesor = new javax.swing.JLabel();
      lblLabelTurno = new javax.swing.JLabel();
      lblTurno = new javax.swing.JLabel();
      lblFecha = new javax.swing.JLabel();
      lblDate = new javax.swing.JLabel();
      cmdCancel = new javax.swing.JButton();
      lblRecuento = new javax.swing.JLabel();
      jPanel2 = new javax.swing.JPanel();
      cmdRecuento = new javax.swing.JButton();
      lblLabelHoraInicio = new javax.swing.JLabel();
      lblLabelHoraFin = new javax.swing.JLabel();
      lblRecuentoFin = new javax.swing.JLabel();
      lblRecuentoIni = new javax.swing.JLabel();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("Recuento de asistencia");

      cboGrupo.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboGrupoActionPerformed(evt);
         }
      });

      lblGrupo.setText("Grupo");

      jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

      lblLabelCurso.setText("Curso:");

      lblCurso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblCurso.setText("- seleccione un grupo -");

      lblLabelProfe.setText("Profesor:");

      lblProfesor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblProfesor.setText("- seleccione un grupo -");

      lblLabelTurno.setText("Turno:");

      lblTurno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblTurno.setText("- seleccione un grupo -");

      lblFecha.setText("Fecha actual:");

      lblDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblDate.setText("dd/MM/yyyy");

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lblFecha)
               .addComponent(lblLabelProfe)
               .addComponent(lblLabelTurno)
               .addComponent(lblLabelCurso))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel1Layout.createSequentialGroup()
                  .addGap(1, 1, 1)
                  .addComponent(lblProfesor))
               .addComponent(lblTurno)
               .addComponent(lblCurso)
               .addComponent(lblDate))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelCurso)
               .addComponent(lblCurso))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelProfe)
               .addComponent(lblProfesor))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelTurno)
               .addComponent(lblTurno))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblFecha)
               .addComponent(lblDate))
            .addContainerGap(20, Short.MAX_VALUE))
      );

      cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/arrow-curve-180.png"))); // NOI18N
      cmdCancel.setText("Cerrar");
      cmdCancel.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmdCancelActionPerformed(evt);
         }
      });

      lblRecuento.setText("Recuento");

      jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

      cmdRecuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clock-frame.png"))); // NOI18N
      cmdRecuento.setText("Iniciar recuento de alumnos");
      cmdRecuento.setEnabled(false);
      cmdRecuento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmdRecuentoActionPerformed(evt);
         }
      });

      lblLabelHoraInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clock-small.png"))); // NOI18N
      lblLabelHoraInicio.setText("Hora de inicio:");
      lblLabelHoraInicio.setToolTipText("");

      lblLabelHoraFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clock-small.png"))); // NOI18N
      lblLabelHoraFin.setText("Hora de finalización:");
      lblLabelHoraFin.setToolTipText("");

      lblRecuentoFin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblRecuentoFin.setText("- inicie recuento -");

      lblRecuentoIni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblRecuentoIni.setText("- inicie recuento -");

      javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
      jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(cmdRecuento, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(jPanel2Layout.createSequentialGroup()
                  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(lblLabelHoraFin)
                     .addComponent(lblLabelHoraInicio))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(lblRecuentoIni)
                     .addComponent(lblRecuentoFin))))
            .addContainerGap(136, Short.MAX_VALUE))
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(cmdRecuento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelHoraInicio)
               .addComponent(lblRecuentoIni))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelHoraFin)
               .addComponent(lblRecuentoFin))
            .addContainerGap(14, Short.MAX_VALUE))
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(0, 0, Short.MAX_VALUE)
                  .addComponent(cmdCancel))
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(lblRecuento)
                     .addComponent(lblGrupo))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(cboGrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(cboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(lblGrupo))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lblRecuento)
               .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmdCancel)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
      
      // Cierra el formulario
      this.dispose();

   }//GEN-LAST:event_cmdCancelActionPerformed

   private void cboGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGrupoActionPerformed
      
      if (cboGrupo.getSelectedIndex() < 0)
      {
         lblCurso.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_SELECTGRUPO));
         lblProfesor.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_SELECTGRUPO));
         lblTurno.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_SELECTGRUPO));
         lblRecuentoIni.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_INITRECUENTO));
         lblRecuentoFin.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_INITRECUENTO));
         
         cmdRecuento.setEnabled(false);
         
         return;
      }

      try 
      {
         Grupo grupo = manager.getGrupo(((Grupo) cboGrupo.getSelectedItem()).getId());
         
         lblCurso.setText(grupo.getNombreActividad());
         lblProfesor.setText(grupo.getNombreProfesor());
         lblTurno.setText(Grupo.getTurnoName(grupo.getTurno(), language));
         
         cmdRecuento.setEnabled(true);
      } 
      catch (Exception ex) 
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.ERROR_GENERIC) + "\n" + language.getProperty(eAcademiaEU.ERROR_DETAILS) + ":\n\n" + ex.getMessage(), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }//GEN-LAST:event_cboGrupoActionPerformed

   private void cmdRecuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRecuentoActionPerformed
      
      final SimpleDateFormat sdf = new SimpleDateFormat(language.getProperty(eAcademiaEU.FORMAT_TIME));
      
      // Configura la visualización del cronómetro
      ActionListener timerListener = new ActionListener()  
      {  
         @Override
         public void actionPerformed(ActionEvent e)  
         {  
            if (cmdRecuento.isEnabled())
            {
               lblRecuentoFin.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_CONTANDO) + " " + sdf.format(new Date()));
            }
         }  
      };  
      Timer timer = new Timer(1000, timerListener);
      
      if (asistencia == null)
      {  
         Date time = new Date();
         
         try 
         {
            // Obtiene el grupo
            Grupo grupo = (Grupo) cboGrupo.getSelectedItem();
            
            // Genera el registro de asistencia al grupo
            asistencia = new Asistencia();
            asistencia.setDate(new Date());
            asistencia.setIdGrupo(grupo.getId());
            asistencia.setHoraInicio(time);
            asistencia.setHoraFin(null);

            asistencia.setId(manager.startRecuento(asistencia));
            
            // Configura los controles de pantalla
            cboGrupo.setEnabled(false);
            cmdRecuento.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_RECUENTOCLOSE));
            cmdCancel.setEnabled(false);
            lblRecuentoIni.setText(sdf.format(asistencia.getHoraInicio()));
            lblRecuentoFin.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_CONTANDO));
            
            // Inicia el crono
            timer.setInitialDelay(0);  
            timer.start();
         } 
         catch (GroupAlreadyCountedException ex) 
         {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty(eAcademiaEU.ERROR_GROUPALREADYCOUNTED), 
                                          language.getProperty(eAcademiaEU.APP_TITLE), 
                                          JOptionPane.ERROR_MESSAGE);
         } 
         catch (Exception ex) 
         {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty(eAcademiaEU.ERROR_GENERIC) + "\n" + language.getProperty(eAcademiaEU.ERROR_DETAILS) + ":\n\n" + ex.getMessage(), 
                                          language.getProperty(eAcademiaEU.APP_TITLE), 
                                          JOptionPane.ERROR_MESSAGE);
            
            // Registra el error en un archivo de LOG
            // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      else
      {
         try 
         {
            // Detiene el crono
            timer.stop();
            
            // Actualiza el registro de asistencia
            asistencia.setHoraFin(new Date());
            manager.stopRecuento(asistencia);

            // Configura los controles de pantalla
            cboGrupo.setEnabled(false);
            cmdRecuento.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_RECUENTOFIN));
            cmdRecuento.setEnabled(false);
            lblRecuentoFin.setText(sdf.format(asistencia.getHoraFin()));
            cmdCancel.setEnabled(true);
         } 
         catch (Exception ex) 
         {
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty(eAcademiaEU.ERROR_GENERIC) + "\n" + language.getProperty(eAcademiaEU.ERROR_DETAILS) + ":\n\n" + ex.getMessage(), 
                                          language.getProperty(eAcademiaEU.APP_TITLE), 
                                          JOptionPane.ERROR_MESSAGE);
            
            // Registra el error en un archivo de LOG
            // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }//GEN-LAST:event_cmdRecuentoActionPerformed

   /**
    * Internacionaliza los textos del formulario.
    */
   private void setLanguage()
   {
      this.setTitle(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_TITLE));
      lblGrupo.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_GRUPO));
      lblRecuento.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_RECUENTO));
      lblLabelCurso.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_ACTIVIDAD) + ":");
      lblCurso.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_SELECTGRUPO));
      lblLabelProfe.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_PROFESOR) + ":");
      lblProfesor.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_SELECTGRUPO));
      lblLabelTurno.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_TURNO) + ":");
      lblTurno.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_SELECTGRUPO));
      lblFecha.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_FECHA) + ":");
      lblLabelHoraInicio.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_HORAINI) + ":");
      lblRecuentoIni.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_INITRECUENTO));
      lblLabelHoraFin.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_HORAFIN) + ":");
      lblRecuentoFin.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_INITRECUENTO));
      cmdRecuento.setText(language.getProperty(eAcademiaEU.FORM_PNLASISTENCIA_BTNRECUENTO));
      cmdCancel.setText(language.getProperty(eAcademiaEU.FORM_COMMON_CLOSE));
   }
   
   /**
    * Rellena los campos del formulario.
    */
   private void fillForm()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(language.getProperty(eAcademiaEU.FORMAT_SHORTDATE));
      
      try 
      {
         lblDate.setText(sdf.format(new Date()));
         
         cboGrupo.removeAll();
         cboGrupo.setModel(new DefaultComboBoxModel(manager.getGruposByProfesor(this.usuario.getId()).toArray()));
         cboGrupo.setSelectedIndex(-1);
      } 
      catch (Exception ex) 
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.ERROR_GENERIC) + "\n" + language.getProperty(eAcademiaEU.ERROR_DETAILS) + ":\n\n" + ex.getMessage(), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JComboBox cboGrupo;
   private javax.swing.JButton cmdCancel;
   private javax.swing.JButton cmdRecuento;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JLabel lblCurso;
   private javax.swing.JLabel lblDate;
   private javax.swing.JLabel lblFecha;
   private javax.swing.JLabel lblGrupo;
   private javax.swing.JLabel lblLabelCurso;
   private javax.swing.JLabel lblLabelHoraFin;
   private javax.swing.JLabel lblLabelHoraInicio;
   private javax.swing.JLabel lblLabelProfe;
   private javax.swing.JLabel lblLabelTurno;
   private javax.swing.JLabel lblProfesor;
   private javax.swing.JLabel lblRecuento;
   private javax.swing.JLabel lblRecuentoFin;
   private javax.swing.JLabel lblRecuentoIni;
   private javax.swing.JLabel lblTurno;
   // End of variables declaration//GEN-END:variables
}
