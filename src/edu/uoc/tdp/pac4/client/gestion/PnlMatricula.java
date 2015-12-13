package edu.uoc.tdp.pac4.client.gestion;

import edu.uoc.tdp.pac4.beans.Alumno;
import edu.uoc.tdp.pac4.beans.Actividad;
import edu.uoc.tdp.pac4.beans.Grupo;
import edu.uoc.tdp.pac4.beans.IdentifiableObject;
import edu.uoc.tdp.pac4.beans.Matricula;
import edu.uoc.tdp.pac4.exceptions.NoGroupFoundException;
import edu.uoc.tdp.pac4.exceptions.StudentNotExistsException;
import edu.uoc.tdp.pac4.remote.GestAcademica;
import edu.uoc.tdp.pac4.util.ComboItem;
import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import edu.uoc.tdp.pac4.eAcademiaEU;

/**
 * Implementa el formulario de edición de matriculas.
 * 
 * @author JavaBeginers
 */
public class PnlMatricula extends javax.swing.JDialog 
{
   private GestAcademica manager;
   private LanguageUtils language;
   private Matricula matricula;
   private int matriculaId;
   private int numPlazasMañana, numPlazasTarde;
   private List<Alumno> alumnos;
   private List<Grupo> grupos;
   private List<ComboItem> estados;
   private DialogMode mode;
   private Grupo grupo = null;
   private Alumno alumno = null;

   public enum DialogMode
   {
      AceptarMatricula,
      EditarMatricula
   }
   
   /**
    * Creates new form PnlMatricula
    */
   public PnlMatricula(java.awt.Frame parent, boolean modal, GestAcademica manager, LanguageUtils language, int matriculaId, DialogMode mode) throws Exception 
   {
      super(parent, modal);
      initComponents();
      
      this.manager = manager;
      this.language = language;
      this.matriculaId = 0;
      this.matricula = null;
      this.mode = mode;
      
      // Obtiene la matricula
      this.matricula = manager.getMatricula(matriculaId);
      
      setLanguage();
      fillLists();
      fillFormData(matriculaId);
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblLabelAlumno = new javax.swing.JLabel();
        lblGrupo = new javax.swing.JLabel();
        cboGrupo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        lblLabelFechas = new javax.swing.JLabel();
        lblFechas = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();
        lblLabelTurno = new javax.swing.JLabel();
        lblLabelActividad = new javax.swing.JLabel();
        lblActividad = new javax.swing.JLabel();
        lblPlazas = new javax.swing.JLabel();
        lblTurno1 = new javax.swing.JLabel();
        lblTurno2 = new javax.swing.JLabel();
        lblManana = new javax.swing.JLabel();
        lblTarde = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox();
        lblAlumno = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar matrícula");

        lblLabelAlumno.setText("Alumno");

        lblGrupo.setText("Grupo");

        cboGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGrupoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblLabelFechas.setText("Fechas:");

        lblFechas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFechas.setText("- seleccione un grupo -");

        lblTurno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTurno.setText("- seleccione un grupo -");

        lblLabelTurno.setText("Turno solicitado:");

        lblLabelActividad.setText("Actividad:");
        lblLabelActividad.setToolTipText("");

        lblActividad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblActividad.setText("- seleccione un grupo -");

        lblPlazas.setText("Plazas disponibles:");

        lblTurno1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTurno1.setText("- seleccione un grupo -");

        lblTurno2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTurno2.setText("- seleccione un grupo -");

        lblManana.setText("Mañana");

        lblTarde.setText("Tarde");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLabelTurno)
                    .addComponent(lblLabelFechas)
                    .addComponent(lblLabelActividad)
                    .addComponent(lblPlazas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechas)
                    .addComponent(lblTurno)
                    .addComponent(lblActividad)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblManana)
                            .addComponent(lblTarde))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTurno1)
                            .addComponent(lblTurno2))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLabelActividad)
                    .addComponent(lblActividad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLabelFechas)
                    .addComponent(lblFechas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLabelTurno)
                    .addComponent(lblTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlazas)
                    .addComponent(lblTurno1)
                    .addComponent(lblManana))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTurno2)
                    .addComponent(lblTarde))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAccept.setText("Aceptar");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        lblEstado.setText("Estado");

        cboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoActionPerformed(evt);
            }
        });

        lblAlumno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAlumno.setText("Student name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAccept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLabelAlumno)
                            .addComponent(lblGrupo)
                            .addComponent(lblEstado))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboGrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAlumno))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLabelAlumno)
                    .addComponent(lblAlumno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrupo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnAccept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

   private void cboGrupoActionPerformed(java.awt.event.ActionEvent evt) {                                         
      
      Actividad actividad = null;
      
      if (cboGrupo.getSelectedIndex() < 0)
      {
         return;
      }
      else
      {
         lblFechas.setText("");
         lblTurno.setText("");
         lblTurno1.setText("");
         lblTurno2.setText("");
      }
      
      try 
      {
         grupo = (Grupo) cboGrupo.getSelectedItem();
         lblFechas.setText(grupo.getFechaInicioActividad().toString() + " - " + grupo.getFechaFinActividad().toString());
         lblTurno.setText(grupo.getTurno() == Grupo.TURNO_TARDE ? language.getProperty(eAcademiaEU.GRUPO_TURNO_TARDE) : language.getProperty(eAcademiaEU.GRUPO_TURNO_MANANA));
         
         actividad = manager.getActividad(grupo.getIdActividad());
         lblActividad.setText(actividad.getTitol());
      } 
      catch (Exception ex) 
      {
         // Nothing to do here
      }

      try 
      {
         numPlazasMañana = manager.getPlazasDisponibles(actividad.getId(), Grupo.TURNO_MANANA);
         lblTurno1.setText(numPlazasMañana + " " + language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_NPLAZAS));
      } 
      catch (NoGroupFoundException ex) 
      {
         lblTurno1.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_NOGROUP));
      } 
      catch (Exception ex) 
      {
         // Nothing to do here
      }
      
      try 
      {
         numPlazasTarde = manager.getPlazasDisponibles(actividad.getId(), Grupo.TURNO_TARDE);
         lblTurno2.setText(numPlazasTarde + " " + language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_NPLAZAS));
      } 
      catch (NoGroupFoundException ex) 
      {
         lblTurno2.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_NOGROUP));
      } 
      catch (Exception ex) 
      {
         // Nothing to do here
      }
   }                                        

   private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
      
      // Cierra el formulario
      this.dispose();
      
   }                                         

   private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {                                          
      
      // Comprueba los datos especificados en el formulario
      if (cboGrupo.getSelectedIndex() < 0)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_WARN_GRUPO), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.WARNING_MESSAGE);
         return;
      }
      else if (grupos.get(cboGrupo.getSelectedIndex()).getTurno() == Grupo.TURNO_MANANA && numPlazasMañana <= 0)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_WARN_NOPLAZAS), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.WARNING_MESSAGE);
         return;
      }
      else if (grupos.get(cboGrupo.getSelectedIndex()).getTurno() == Grupo.TURNO_TARDE && numPlazasTarde <= 0)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_WARN_NOPLAZAS), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.WARNING_MESSAGE);
         return;
      }      
      
      // Recopila los datos del formulario.
      // Dado que ya se carga la matrícula al inicio, sólo actualiza los campos necesarios
      //matricula.setGrupoId(grupos.get(cboGrupo.getSelectedIndex()).getId());
      matricula.setEstado(Matricula.MATRICULA_ESTADO_ACEPTADA);

      try 
      {
         // Actualiza la matrícula
         if (matricula.getEstado() == Matricula.MATRICULA_ESTADO_ACEPTADA)
         {
            manager.aceptarMatricula(matricula);
         }
         else
         {
            manager.updateMatricula(matricula);
         }
         
         // Cierra el formulario
         this.dispose();
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

   private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {                                          
      // TODO add your handling code here:
   }                                         

   /**
    * Internacionaliza los textos del formulario.
    */
   private void setLanguage()
   {
      this.setTitle(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_TITLE));
      lblLabelAlumno.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_ALUMNO));
      lblGrupo.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_GRUPO));
      lblEstado.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_ESTADO));
      lblLabelActividad.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_ACTIVIDAD));
      lblLabelFechas.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_FECHAS));
      lblLabelTurno.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_TURNO));
      lblPlazas.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_PLAZAS));
      lblManana.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_PLAZASMANANA));
      lblTarde.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_PLAZASTARDE));
      lblActividad.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_SELECTGRUPO));
      lblFechas.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_SELECTGRUPO));
      lblTurno.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_SELECTGRUPO));
      lblTurno1.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_SELECTGRUPO));
      lblTurno2.setText(language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_SELECTGRUPO));
      btnAccept.setText(language.getProperty(eAcademiaEU.FORM_COMMON_ACCEPT));
      btnCancel.setText(language.getProperty(eAcademiaEU.FORM_COMMON_CANCEL));
   }
   
   /**
    * Rellena el formulario con la información de un determinado grupo.
    */
   private void fillFormData(int id)
   {
      try 
      {
         // Obtiene la matricula
         this.matricula = manager.getMatricula(id);

         // Si la matricula no está pendiente, no se puede modificar el estado
         if (matricula.getEstado() != Matricula.MATRICULA_ESTADO_PENDIENTE)
         {
            cboEstado.setEnabled(false);
         }
         
         // Si la matrícula es de sólo lectura, informa al usuario y bloquea controles
         if (matricula.getEstado() == Matricula.MATRICULA_ESTADO_ANULADA || matricula.getEstado() == Matricula.MATRICULA_ESTADO_BAJA)
         {
            cboGrupo.setEnabled(false);
            btnAccept.setVisible(false);
            btnCancel.setText(language.getProperty(eAcademiaEU.FORM_COMMON_CLOSE));
            
            JOptionPane.showMessageDialog(null, 
                                          language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_WARN_READONLY), 
                                          language.getProperty(eAcademiaEU.APP_TITLE), 
                                          JOptionPane.WARNING_MESSAGE);
         }
         
         // Obtiene el alumno
         alumno = manager.getAlumno(this.matricula.getUsuarioId());
         lblAlumno.setText(alumno.toString());
         
         //cboGrupo.setSelectedItem(IdentifiableObject.getObjectFromList(grupos.toArray(), matricula.getGrupoId()));
      } 
      catch (StudentNotExistsException ex)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.FORM_PNLMATRICULA_ERR_NOTSTUDENT), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Inhabilita el botón de aceptar
         this.btnAccept.setVisible(false);
         this.btnCancel.setText(language.getProperty(eAcademiaEU.FORM_COMMON_CLOSE));
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (Exception ex)
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.ERROR_GENERIC) + "\n\n" + language.getProperty(eAcademiaEU.ERROR_DETAILS) + ":\n" + ex.getMessage(), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.ERROR_MESSAGE);
         
         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   /**
    * Rellena las listas del formulario.
    */
   private void fillLists()
   {
      ComboItem cboi;
      
      try 
      {
         // Informa del actividad al que pertenece la matrícula
         int idActividad = this.matricula.getActividadId();
         grupos = manager.getGrupos(idActividad);
         cboGrupo.removeAll();
         cboGrupo.setModel(new DefaultComboBoxModel(grupos.toArray()));
         cboGrupo.setSelectedIndex(-1);

         // Rellena los estados en función del estado actual de la matrícula
         estados = new ArrayList<ComboItem>();
         if (matricula.getEstado() == Matricula.MATRICULA_ESTADO_ANULADA)
         {
            cboi = new ComboItem(Matricula.getStatusName(Matricula.MATRICULA_ESTADO_ANULADA, language), Matricula.MATRICULA_ESTADO_ANULADA);
            estados.add(cboi);
         }
         else if (matricula.getEstado() == Matricula.MATRICULA_ESTADO_BAJA)
         {
            cboi = new ComboItem(Matricula.getStatusName(Matricula.MATRICULA_ESTADO_BAJA, language), Matricula.MATRICULA_ESTADO_BAJA);
            estados.add(cboi);
         }
         else if (matricula.getEstado() == Matricula.MATRICULA_ESTADO_ACEPTADA)
         {
            cboi = new ComboItem(Matricula.getStatusName(Matricula.MATRICULA_ESTADO_ACEPTADA, language), Matricula.MATRICULA_ESTADO_ACEPTADA);
            estados.add(cboi);
         }
         else
         {
            cboi = new ComboItem(Matricula.getStatusName(Matricula.MATRICULA_ESTADO_PENDIENTE, language), Matricula.MATRICULA_ESTADO_PENDIENTE);
            estados.add(cboi);
            cboi = new ComboItem(Matricula.getStatusName(Matricula.MATRICULA_ESTADO_ACEPTADA, language), Matricula.MATRICULA_ESTADO_ACEPTADA);
            estados.add(cboi);
         }
         cboEstado.removeAll();
         cboEstado.setModel(new DefaultComboBoxModel(estados.toArray()));
         cboEstado.setSelectedItem(IdentifiableObject.getObjectFromList(estados.toArray(), matricula.getEstado()));
      }
      catch (Exception ex) 
      {
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty(eAcademiaEU.ERROR_GENERIC) + "\n" + language.getProperty(eAcademiaEU.ERROR_DETAILS) + ":\n\n" + ex.getMessage(), 
                                       language.getProperty(eAcademiaEU.APP_TITLE), 
                                       JOptionPane.ERROR_MESSAGE);

         // Registra el error en un archivo de LOG
         // Logger.getLogger(FrmResourcesRequest.class.getName()).log(Level.SEVERE, null, ex);
         
         // Bloquea el botón de Aceptar pues no sabemos a priori como ha quedado el formulario
         btnAccept.setEnabled(false);
      }
   }
   
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cboEstado;
    private javax.swing.JComboBox cboGrupo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblAlumno;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechas;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblLabelActividad;
    private javax.swing.JLabel lblLabelAlumno;
    private javax.swing.JLabel lblLabelFechas;
    private javax.swing.JLabel lblLabelTurno;
    private javax.swing.JLabel lblManana;
    private javax.swing.JLabel lblPlazas;
    private javax.swing.JLabel lblTarde;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JLabel lblTurno1;
    private javax.swing.JLabel lblTurno2;
    // End of variables declaration                   
}
