package edu.uoc.tdp.pac4.client;

import edu.uoc.tdp.pac4.beans.Usuario;
import edu.uoc.tdp.pac4.client.conexion.*;
import edu.uoc.tdp.pac4.client.estadisticas.PnlFiltroActividades;
import edu.uoc.tdp.pac4.client.estadisticas.PnlFiltroPersonalAcademico;
import edu.uoc.tdp.pac4.client.estadisticas.PnlFiltroProfesor;
import edu.uoc.tdp.pac4.client.gestion.PnlGroupGestor;
import edu.uoc.tdp.pac4.client.gestion.PnlMatriculaGestor;
import edu.uoc.tdp.pac4.client.mantenimiento.PnlMantenimientoAulas;
import edu.uoc.tdp.pac4.client.mantenimiento.PnlMantenimientoRecursos;
import edu.uoc.tdp.pac4.client.mantenimiento.PnlMantenimientoActividades;
import edu.uoc.tdp.pac4.client.mantenimiento.PnlMantenimientoUsuarios;
import edu.uoc.tdp.pac4.client.mantenimiento.PnlMantenimientoCentros;
import edu.uoc.tdp.pac4.remote.Conexion;
import edu.uoc.tdp.pac4.remote.Estadisticas;
import edu.uoc.tdp.pac4.remote.GestAcademica;
//import edu.uoc.tdp.pac4.remote.GestorEstoc;
import edu.uoc.tdp.pac4.remote.Mantenimiento;

import edu.uoc.tdp.pac4.util.LanguageUtils;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import edu.uoc.tdp.pac4.eAcademiaEU;

/**
 * Implementa el formulario principal de la aplicación.
 * 
 * @author Javabeginers
 */
public class PnlMain extends javax.swing.JFrame 
{
   
   // private final String urlRMI = "rmi://localhost/eAssistencia";
   private LanguageUtils language = new LanguageUtils();
   
   //private GestorEstoc rmiStockManager;
   private Mantenimiento rmiMantenimiento;
   private GestAcademica rmiGestAcademica;
   private Estadisticas rmiEstadisticas;
   private Conexion rmiConexion;
   private Usuario usuario;

   /**
    * Creates new form PnlMain
    */
   public PnlMain() 
   {  
      initComponents(); 
      // Inicializa literales de idioma
      setLanguage(LanguageUtils.LANG_SPANISH);
      // Centra el formulario inicial
      this.setLocationRelativeTo(null);      
      //Inicializa los componentes del formulario
      initPnlMain();
      //Nombre de la aplicacion      
      this.setTitle(language.getProperty("app.title") + " - " + language.getProperty("client.title"));
                 
   }
   
   /**
    * Método que habilita / deshabilita las opciones de menu según el rol
    * @param login 
    * @param idRol 
    * @param descRol
    */
   public void setRolMenuOpciones(String login, int idRol, String descRol)
   {
       // Habilita las opciones que requieren conexión
       //TO-DO: Habilitar opciones en función del rol
        switch(idRol) {
      
            // Es administrador
            case 1:
                mnuFileConnect.setEnabled(false);
                mnuGestion.setEnabled(true);
                mnuGestionGrupos.setEnabled(true);
                mnuGestionMatriculas.setEnabled(true);
                mnuMantenimiento.setEnabled(true);     
                mnuEstadisticas.setEnabled(true);
                mnuEstadisticas.setEnabled(true);
                mnuEstadisticasAlumno.setEnabled(true);
                mnuEstadisticasProfesor.setEnabled(true);
                mnuEstadisticasPersonal.setEnabled(true);
                mnuMatriculas.setEnabled(true);
                //mnuStock.setEnabled(true);
                mnuRequest.setEnabled(true); 
                break;
                
            // Es personal academico
            case 2:
                mnuFileConnect.setEnabled(false);
                mnuGestion.setEnabled(true);
                mnuGestionGrupos.setEnabled(true);
                mnuGestionMatriculas.setEnabled(true);
                mnuMantenimiento.setEnabled(true);     
                mnuEstadisticas.setEnabled(true);
                mnuEstadisticasAlumno.setEnabled(false);
                mnuEstadisticasProfesor.setEnabled(false);
                mnuEstadisticasPersonal.setEnabled(true);
                mnuMatriculas.setEnabled(false);
                //mnuStock.setEnabled(true);
                mnuRequest.setEnabled(true); 
                break;
                
            // Es Profesor
            case 3:
                mnuFileConnect.setEnabled(false);
                mnuGestion.setEnabled(true);
                mnuGestionGrupos.setEnabled(false);
                mnuGestionMatriculas.setEnabled(false);
                mnuMantenimiento.setEnabled(false);     
                mnuEstadisticas.setEnabled(true);
                mnuEstadisticasProfesor.setEnabled(true);
                mnuEstadisticasAlumno.setEnabled(false);
                mnuEstadisticasPersonal.setEnabled(false);
                mnuMatriculas.setEnabled(false);
                //mnuStock.setEnabled(false);
                mnuRequest.setEnabled(false); 
                break;
                
            //Es Usuari
            case 4:
                mnuFileConnect.setEnabled(false);
                mnuGestion.setEnabled(false);
                mnuGestionGrupos.setEnabled(false);
                mnuGestionMatriculas.setEnabled(false);
                mnuMantenimiento.setEnabled(false);     
                mnuEstadisticas.setEnabled(true);
                mnuEstadisticasAlumno.setEnabled(true);
                mnuEstadisticasProfesor.setEnabled(false);
                mnuEstadisticasPersonal.setEnabled(false);
                mnuMatriculas.setEnabled(true);
                //mnuStock.setEnabled(false);
                mnuRequest.setEnabled(false); 
                break;
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

        pnlMain = new javax.swing.JPanel();
        lblConnectionStatus = new javax.swing.JLabel();
        lblUserSession = new javax.swing.JLabel();
        lbleSupport = new javax.swing.JLabel();
        lblPortada = new javax.swing.JLabel();
        lblUOC = new javax.swing.JLabel();
        mnuBarMenuPrincipal = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuFileConnect = new javax.swing.JMenuItem();
        mnuDesconectar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuIdioma = new javax.swing.JMenu();
        mnuLanguageEsp = new javax.swing.JMenuItem();
        mnuLanguageCat = new javax.swing.JMenuItem();
        mnuLanguageEng = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuFileExit = new javax.swing.JMenuItem();
        mnuGestion = new javax.swing.JMenu();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnuGestionGrupos = new javax.swing.JMenuItem();
        mnuGestionMatriculas = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        mnuMantenimientoUsuarios = new javax.swing.JMenuItem();
        mnuMantenimientoAulas = new javax.swing.JMenuItem();
        mnuMantenimientoRecursos = new javax.swing.JMenuItem();
        mnuMantenimientoCentros = new javax.swing.JMenuItem();
        mnuMantenimientoActividades = new javax.swing.JMenuItem();
        mnuEstadisticas = new javax.swing.JMenu();
        mnuEstadisticasAlumno = new javax.swing.JMenuItem();
        mnuEstadisticasProfesor = new javax.swing.JMenuItem();
        mnuEstadisticasPersonal = new javax.swing.JMenuItem();
        mnuMatriculas = new javax.swing.JMenu();
        mnuAltaMatricula = new javax.swing.JMenuItem();
        mnuConsultaSolicitudes = new javax.swing.JMenuItem();
        mnuRequest = new javax.swing.JMenu();
        mnuRequestAdd = new javax.swing.JMenuItem();
        mnuRequestServe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("eAssistencia - Aplicació client");
        setIconImage(getIconImage());
        setResizable(false);

        pnlMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConnectionStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lightning.png"))); // NOI18N
        lblConnectionStatus.setText("Estado: Desconectado");

        lblUserSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user_004_16.png"))); // NOI18N
        lblUserSession.setText("Usuario: Sin sesión iniciada");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(lblConnectionStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUserSession)
                .addGap(0, 384, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblConnectionStatus)
                .addComponent(lblUserSession))
        );

        lbleSupport.setText("Javabeginer NetBeans");

        lblUOC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/uoc.png"))); // NOI18N

        mnuFile.setText("Archivo");

        mnuFileConnect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuFileConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lightning.png"))); // NOI18N
        mnuFileConnect.setText("Iniciar conexión");
        mnuFileConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileConnectActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileConnect);

        mnuDesconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lightning-disable.png"))); // NOI18N
        mnuDesconectar.setText("Desconectar");
        mnuDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDesconectarActionPerformed(evt);
            }
        });
        mnuFile.add(mnuDesconectar);
        mnuFile.add(jSeparator1);

        mnuIdioma.setText("Idioma");

        mnuLanguageEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ok_st_obj.gif"))); // NOI18N
        mnuLanguageEsp.setText("Castellano");
        mnuLanguageEsp.setEnabled(false);
        mnuLanguageEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLanguageEspActionPerformed(evt);
            }
        });
        mnuIdioma.add(mnuLanguageEsp);

        mnuLanguageCat.setText("Català");
        mnuLanguageCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLanguageCatActionPerformed(evt);
            }
        });
        mnuIdioma.add(mnuLanguageCat);

        mnuLanguageEng.setText("English");
        mnuLanguageEng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLanguageEngActionPerformed(evt);
            }
        });
        mnuIdioma.add(mnuLanguageEng);

        mnuFile.add(mnuIdioma);
        mnuFile.add(jSeparator2);

        mnuFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuFileExit.setText("Salir");
        mnuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileExit);

        mnuBarMenuPrincipal.add(mnuFile);

        mnuGestion.setText("Gestión");
        mnuGestion.add(jSeparator3);

        mnuGestionGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/users.png"))); // NOI18N
        mnuGestionGrupos.setText("Grupos...");
        mnuGestionGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGestionGruposActionPerformed(evt);
            }
        });
        mnuGestion.add(mnuGestionGrupos);

        mnuGestionMatriculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/book.png"))); // NOI18N
        mnuGestionMatriculas.setText("Matriculas...");
        mnuGestionMatriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGestionMatriculasActionPerformed(evt);
            }
        });
        mnuGestion.add(mnuGestionMatriculas);

        mnuBarMenuPrincipal.add(mnuGestion);

        mnuMantenimiento.setText("Mantenimiento");

        mnuMantenimientoUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/users--pencil.png"))); // NOI18N
        mnuMantenimientoUsuarios.setText("Usuarios...");
        mnuMantenimientoUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMantenimientoUsuariosActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuMantenimientoUsuarios);
        mnuMantenimientoUsuarios.getAccessibleContext().setAccessibleName("mnuMantenimientoUsuarios");

        mnuMantenimientoAulas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sofa--pencil.png"))); // NOI18N
        mnuMantenimientoAulas.setText("Aulas...");
        mnuMantenimientoAulas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMantenimientoAulasActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuMantenimientoAulas);

        mnuMantenimientoRecursos.setText("Recursos...");
        mnuMantenimientoRecursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMantenimientoRecursosActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuMantenimientoRecursos);

        mnuMantenimientoCentros.setText("Centros...");
        mnuMantenimientoCentros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMantenimientoCentrosActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuMantenimientoCentros);

        mnuMantenimientoActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/projection-screen--pencil.png"))); // NOI18N
        mnuMantenimientoActividades.setText("Activitats...");
        mnuMantenimientoActividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMantenimientoActividadesActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mnuMantenimientoActividades);

        mnuBarMenuPrincipal.add(mnuMantenimiento);

        mnuEstadisticas.setText("LListats");

        mnuEstadisticasAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/report.png"))); // NOI18N
        mnuEstadisticasAlumno.setText("Asistencia alumno");
        mnuEstadisticasAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEstadisticasAlumnoActionPerformed(evt);
            }
        });
        mnuEstadisticas.add(mnuEstadisticasAlumno);

        mnuEstadisticasProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/report.png"))); // NOI18N
        mnuEstadisticasProfesor.setText("Profesor");
        mnuEstadisticasProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEstadisticasProfesorActionPerformed(evt);
            }
        });
        mnuEstadisticas.add(mnuEstadisticasProfesor);

        mnuEstadisticasPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/report.png"))); // NOI18N
        mnuEstadisticasPersonal.setText("Personal Académico");
        mnuEstadisticasPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEstadisticasPersonalActionPerformed(evt);
            }
        });
        mnuEstadisticas.add(mnuEstadisticasPersonal);

        mnuBarMenuPrincipal.add(mnuEstadisticas);

        mnuMatriculas.setText("Matrícula");

        mnuAltaMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/book--plus.png"))); // NOI18N
        mnuAltaMatricula.setText("Solicitud de Matrícula");
        mnuAltaMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAltaMatriculaActionPerformed(evt);
            }
        });
        mnuMatriculas.add(mnuAltaMatricula);

        mnuConsultaSolicitudes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/book-open-text.png"))); // NOI18N
        mnuConsultaSolicitudes.setText("Consulta de Solicitudes");
        mnuConsultaSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConsultaSolicitudesActionPerformed(evt);
            }
        });
        mnuMatriculas.add(mnuConsultaSolicitudes);

        mnuBarMenuPrincipal.add(mnuMatriculas);

        mnuRequest.setText("Peticiones");

        mnuRequestAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/luggage--plus.png"))); // NOI18N
        mnuRequestAdd.setText("Nueva petición...");
        mnuRequestAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRequestAddActionPerformed(evt);
            }
        });
        mnuRequest.add(mnuRequestAdd);

        mnuRequestServe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/luggage--pencil.png"))); // NOI18N
        mnuRequestServe.setText("Atender peticiones...");
        mnuRequestServe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRequestServeActionPerformed(evt);
            }
        });
        mnuRequest.add(mnuRequestServe);

        mnuBarMenuPrincipal.add(mnuRequest);

        setJMenuBar(mnuBarMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbleSupport))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPortada, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUOC)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUOC)
                        .addGap(0, 319, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblPortada, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(lbleSupport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void mnuFileConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileConnectActionPerformed
      
      try 
      {
         // Inicializa la connexión RMI
         Registry registry = LocateRegistry.getRegistry("localhost", 1099);
         
         // Instancia los gestores remotos (RMI)
         //rmiStockManager = (GestorEstoc)Naming.lookup(eAcademiaEU.RMI_URI_STOCK);
         rmiMantenimiento = (Mantenimiento)Naming.lookup(eAcademiaEU.RMI_URI_MANTENIMIENTO);
         rmiGestAcademica = (GestAcademica)Naming.lookup(eAcademiaEU.RMI_URI_GESTIONACAD);
         rmiEstadisticas = (Estadisticas)Naming.lookup(eAcademiaEU.RMI_URI_ESTADISTICAS);
         rmiConexion = (Conexion)Naming.lookup(eAcademiaEU.RMI_URI_CONEXION);

         setStatusMessage(language.getProperty("rmi.connected") + " " + eAcademiaEU.RMI_URI_BASE);
         
         // Solicitamos login de usuario, una vez conectado con los subsistemas
         PnlLogin login = new PnlLogin(this, true, rmiConexion, language);
         login.setLocationRelativeTo(null);         
         login.setVisible(true);
         
         // Actualiza los datos de sesión
         this.usuario = login.getCurrentUser(); // al ser JDialog, se actualizará cuando termine
         lblUserSession.setText(language.getProperty("cnxmatricula.pnlmain.usuario") + (this.usuario == null ? language.getProperty("cnxmatricula.pnlmain.noiniciada") : usuario.getApellidos() + ", " + usuario.getNombre()));
         
         //Habilita el menú de desconexión
         mnuDesconectar.setEnabled(true);
         
      }
      catch (RemoteException ex)
      {
               
         JOptionPane.showMessageDialog(null, 
                                       language.getProperty("rmi.errConnection") + "\n\n" + ex.getMessage(), 
                                       language.getProperty("app.title"), JOptionPane.ERROR_MESSAGE);
         
         setStatusMessage(language.getProperty("rmi.errConnection"));
      }
      catch (Exception ex) 
      {                
         JOptionPane.showMessageDialog(null, 
                                       "ERROR: " + ex.getMessage(), 
                                       language.getProperty("app.title"), JOptionPane.ERROR_MESSAGE);
         
         setStatusMessage(language.getProperty("rmi.errConnection"));
      }
   }//GEN-LAST:event_mnuFileConnectActionPerformed

   private void mnuFileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileExitActionPerformed
      
      // Si está la conexión abierta, la cierra
      // TODO: Close connection
      
      // Termina la ejecución del programa
      lblConnectionStatus.setText(language.getProperty("form.main.closing"));
      System.exit(0);
      
   }//GEN-LAST:event_mnuFileExitActionPerformed

   private void mnuRequestAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRequestAddActionPerformed
      
      //FrmResourcesRequest form = new FrmResourcesRequest(this, true, rmiStockManager, language);
      //form.setLocationRelativeTo(null);
      //form.setVisible(true);
      
   }//GEN-LAST:event_mnuRequestAddActionPerformed

   private void mnuRequestServeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRequestServeActionPerformed
      
     // FrmResourcesRequestsServe form = new FrmResourcesRequestsServe(this, true, rmiStockManager, language);
      //form.setLocationRelativeTo(null);
      //form.setVisible(true);
      
   }//GEN-LAST:event_mnuRequestServeActionPerformed

   private void mnuLanguageCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLanguageCatActionPerformed
      
      mnuLanguageCat.setEnabled(false);
      mnuLanguageCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ok_st_obj.gif")));
      mnuLanguageEsp.setEnabled(true);
      mnuLanguageEsp.setIcon(null);
      mnuLanguageEng.setEnabled(true);
      mnuLanguageEng.setIcon(null);
      
      setLanguage(LanguageUtils.LANG_CATALAN);
      
   }//GEN-LAST:event_mnuLanguageCatActionPerformed

   private void mnuLanguageEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLanguageEspActionPerformed
      
      mnuLanguageEsp.setEnabled(false);
      mnuLanguageEsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ok_st_obj.gif")));
      mnuLanguageCat.setEnabled(true);
      mnuLanguageCat.setIcon(null);
      mnuLanguageEng.setEnabled(true);
      mnuLanguageEng.setIcon(null);
      
      setLanguage(LanguageUtils.LANG_SPANISH);
      
   }//GEN-LAST:event_mnuLanguageEspActionPerformed

   private void mnuGestionGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGestionGruposActionPerformed
      
      PnlGroupGestor form = new PnlGroupGestor(this, true, rmiGestAcademica, language);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
      
   }//GEN-LAST:event_mnuGestionGruposActionPerformed

   private void mnuGestionMatriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGestionMatriculasActionPerformed
      
      PnlMatriculaGestor form = new PnlMatriculaGestor(this, true, rmiGestAcademica, language);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
      
   }//GEN-LAST:event_mnuGestionMatriculasActionPerformed

    private void mnuAltaMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAltaMatriculaActionPerformed
        
      PnlSolicitaMatricula form = new PnlSolicitaMatricula(this, true, rmiConexion, language, usuario);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
      
    }//GEN-LAST:event_mnuAltaMatriculaActionPerformed

    private void mnuEstadisticasAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEstadisticasAlumnoActionPerformed
    
      PnlFiltroActividades form = new PnlFiltroActividades(this, true, rmiEstadisticas, language, usuario);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
        
    }//GEN-LAST:event_mnuEstadisticasAlumnoActionPerformed

    private void mnuEstadisticasProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEstadisticasProfesorActionPerformed
        
      PnlFiltroProfesor form = new PnlFiltroProfesor(this, true, rmiEstadisticas, language, usuario);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
        
    }//GEN-LAST:event_mnuEstadisticasProfesorActionPerformed

    private void mnuMantenimientoUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMantenimientoUsuariosActionPerformed

       PnlMantenimientoUsuarios form = new PnlMantenimientoUsuarios(this, true, rmiMantenimiento, language, usuario);
       form.setLocationRelativeTo(null);
       form.setVisible(true);

    }//GEN-LAST:event_mnuMantenimientoUsuariosActionPerformed

    private void mnuEstadisticasPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEstadisticasPersonalActionPerformed

      PnlFiltroPersonalAcademico form = new PnlFiltroPersonalAcademico(this, true, rmiEstadisticas, language);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
        
    }//GEN-LAST:event_mnuEstadisticasPersonalActionPerformed

    private void mnuConsultaSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConsultaSolicitudesActionPerformed
      
      PnlConsultaMatricula form = new PnlConsultaMatricula(this, true, rmiConexion, language, usuario);
      form.setLocationRelativeTo(null);
      form.setVisible(true);
    }//GEN-LAST:event_mnuConsultaSolicitudesActionPerformed

    private void mnuMantenimientoAulasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMantenimientoAulasActionPerformed
        // TODO add your handling code here:
        PnlMantenimientoAulas form = new PnlMantenimientoAulas(this, true, rmiMantenimiento, language);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_mnuMantenimientoAulasActionPerformed
 
    
    private void mnuMantenimientoActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMantenimientoActividadesActionPerformed
        // TODO add your handling code here:
        PnlMantenimientoActividades form = new PnlMantenimientoActividades(this, true, rmiMantenimiento, language, usuario);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_mnuMantenimientoActividadesActionPerformed

    /**
     * Metodo para desconetarse del servidor y permitir realizar login con otro usuario distinto.
     * @param evt 
     */
    private void mnuDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDesconectarActionPerformed
    
    try 
      {
                           
         rmiConexion = null;
         rmiEstadisticas = null;
         rmiMantenimiento = null;
         rmiGestAcademica = null;
         //rmiStockManager = null;
                                             
         //Informa al usuario de la desconexión   
         setStatusMessage(language.getProperty("rmi.disconnected"));
                           
         //Reinicializa el formulario principal
         initPnlMain();
         
         // Actualiza los datos de sesión         
         lblUserSession.setText(language.getProperty("cnxmatricula.pnlmain.usuario") + (this.usuario == null ? language.getProperty("cnxmatricula.pnlmain.noiniciada") : usuario.getApellidos() + ", " + usuario.getNombre()));
      }
      catch (Exception ex) 
      {
         //mnuStockAdd.setEnabled(false);
         mnuRequestAdd.setEnabled(false);
         mnuRequestServe.setEnabled(false);
         
         JOptionPane.showMessageDialog(null, 
                                       "ERROR: " + ex.getMessage(), 
                                       language.getProperty("app.title"), JOptionPane.ERROR_MESSAGE);
         
         setStatusMessage(language.getProperty("rmi.errConnection"));
      }        
        
    }//GEN-LAST:event_mnuDesconectarActionPerformed

    private void mnuLanguageEngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLanguageEngActionPerformed
        
      mnuLanguageEng.setEnabled(false);
      //mnuLanguageEng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ok_st_obj.gif")));
      mnuLanguageCat.setEnabled(true);
      mnuLanguageCat.setIcon(null);
      mnuLanguageEsp.setEnabled(true);
      mnuLanguageEsp.setIcon(null);
      
      setLanguage(LanguageUtils.LANG_ENGLISH);
    }//GEN-LAST:event_mnuLanguageEngActionPerformed

    private void mnuMantenimientoRecursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMantenimientoRecursosActionPerformed
        // TODO add your handling code here:
         PnlMantenimientoRecursos form = new PnlMantenimientoRecursos(this, true, rmiMantenimiento, language);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_mnuMantenimientoRecursosActionPerformed

    private void mnuMantenimientoCentrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMantenimientoCentrosActionPerformed
        // TODO add your handling code here:
        PnlMantenimientoCentros form = new PnlMantenimientoCentros(this, true, rmiMantenimiento, language);
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }//GEN-LAST:event_mnuMantenimientoCentrosActionPerformed

   
   /**
    * Internacionaliza el contenido del formulario.
    */
   private void setLanguage(String languageCode)
   {
      language = new LanguageUtils(languageCode);
      
      this.setTitle(language.getProperty("app.title") + " - " + language.getProperty("client.title"));
      
      lblConnectionStatus.setText(language.getProperty("cnxmatricula.pnlmain.estado") + 
                                  language.getProperty("cnxmatricula.pnlmain.desconectado"));
      
      lblUserSession.setText(language.getProperty("cnxmatricula.pnlmain.usuario") + 
                             language.getProperty("cnxmatricula.pnlmain.noiniciada"));
      
      mnuIdioma.setText(language.getProperty("menu.language"));
      
      mnuFile.setText(language.getProperty("menu.file"));
      mnuFileConnect.setText(language.getProperty("menu.file.connect"));
      mnuDesconectar.setText(language.getProperty("menu.file.disconnect"));
      mnuFileExit.setText(language.getProperty("menu.file.exit"));
      
      mnuMantenimiento.setText(language.getProperty("mantenimiento.main.mantenimiento"));
      mnuMantenimientoUsuarios.setText(language.getProperty("mantenimiento.main.usuarios"));
      mnuMantenimientoAulas.setText(language.getProperty("mantenimiento.main.aulas"));
      mnuMantenimientoActividades.setText(language.getProperty("mantenimiento.main.actividades"));
      
      mnuMatriculas.setText(language.getProperty("cnxmatricula.pnlmain.matriculas"));
      mnuAltaMatricula.setText(language.getProperty("cnxmatricula.pnlmain.solicitud"));
      mnuConsultaSolicitudes.setText(language.getProperty("cnxmatricula.pnlmain.consultasolicitud"));
      
      mnuGestion.setText(language.getProperty(eAcademiaEU.MENU_GESTION));
      mnuGestionGrupos.setText(language.getProperty(eAcademiaEU.MENU_GESTION_GRUPOS));
      mnuGestionMatriculas.setText(language.getProperty(eAcademiaEU.MENU_GESTION_MATRICULAS));
      
      //mnuStock.setText(language.getProperty("menu.stock"));
      //mnuStockAdd.setText(language.getProperty("menu.stock.add") + "...");
      
      mnuRequest.setText(language.getProperty("menu.request"));
      mnuRequestAdd.setText(language.getProperty("menu.request.add") + "...");
      mnuRequestServe.setText(language.getProperty("menu.request.serve") + "...");
      
      mnuEstadisticasAlumno.setText(language.getProperty("estadisticas.main.alumno"));
      mnuEstadisticasProfesor.setText(language.getProperty("estadisticas.main.profesor"));
      mnuEstadisticasPersonal.setText(language.getProperty("estadisticas.main.personalAcadmico"));
      mnuEstadisticas.setText(language.getProperty("estadisticas.main.title"));
      /**
      if (rmiStockManager == null)
      {
         setStatusMessage(language.getProperty("rmi.disconnected"));
      }
      else
      {
         setStatusMessage(language.getProperty("rmi.connected") + " " + eAcademiaEU.RMI_URI_BASE);
      }
      * */
   }
   
   /**
    * Establece el texto de la barra de estado.
    * 
    * @param text cadena que aparecerá en la barra de estado.
    */
   private void setStatusMessage(String text)
   {
      lblConnectionStatus.setText(language.getProperty("form.main.status") + ": " + text);
   }
   
   /**
    * Inicializa el acceso a menús del formulario e idioma por defecto
    */
   private void initPnlMain()
   {             
      // Inicializa el estado de los componentes del formulario      
      mnuFileConnect.setEnabled(true);
      mnuGestion.setEnabled(false);
      mnuMantenimiento.setEnabled(false);     
      mnuEstadisticas.setEnabled(false);
      mnuMatriculas.setEnabled(false);
      //mnuStock.setEnabled(false);
      mnuRequest.setEnabled(false);
      mnuDesconectar.setEnabled(false);
     
      // Otras inicializaciones
      this.usuario = null;
   }
      
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
         java.util.logging.Logger.getLogger(PnlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(PnlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(PnlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(PnlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() 
      {
         @Override
         public void run() 
         {
            new PnlMain().setVisible(true);
         }
      });
   }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblConnectionStatus;
    private javax.swing.JLabel lblPortada;
    private javax.swing.JLabel lblUOC;
    private javax.swing.JLabel lblUserSession;
    private javax.swing.JLabel lbleSupport;
    private javax.swing.JMenuItem mnuAltaMatricula;
    private javax.swing.JMenuBar mnuBarMenuPrincipal;
    private javax.swing.JMenuItem mnuConsultaSolicitudes;
    private javax.swing.JMenuItem mnuDesconectar;
    private javax.swing.JMenu mnuEstadisticas;
    private javax.swing.JMenuItem mnuEstadisticasAlumno;
    private javax.swing.JMenuItem mnuEstadisticasPersonal;
    private javax.swing.JMenuItem mnuEstadisticasProfesor;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuFileConnect;
    private javax.swing.JMenuItem mnuFileExit;
    private javax.swing.JMenu mnuGestion;
    private javax.swing.JMenuItem mnuGestionGrupos;
    private javax.swing.JMenuItem mnuGestionMatriculas;
    private javax.swing.JMenu mnuIdioma;
    private javax.swing.JMenuItem mnuLanguageCat;
    private javax.swing.JMenuItem mnuLanguageEng;
    private javax.swing.JMenuItem mnuLanguageEsp;
    private javax.swing.JMenu mnuMantenimiento;
    private javax.swing.JMenuItem mnuMantenimientoActividades;
    private javax.swing.JMenuItem mnuMantenimientoAulas;
    private javax.swing.JMenuItem mnuMantenimientoCentros;
    private javax.swing.JMenuItem mnuMantenimientoRecursos;
    private javax.swing.JMenuItem mnuMantenimientoUsuarios;
    private javax.swing.JMenu mnuMatriculas;
    private javax.swing.JMenu mnuRequest;
    private javax.swing.JMenuItem mnuRequestAdd;
    private javax.swing.JMenuItem mnuRequestServe;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
