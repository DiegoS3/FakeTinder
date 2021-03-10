/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import codigos.CodeResponse;
import comunicacion.Comunicacion;
import constantes.ConstantesPreferencias;
import datos.Preferencia;
import datos.Usuario;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import seguridad.Seguridad;
import utilities.Utilities;

/**
 *
 * @author Diego
 */
public class PreferencesView extends javax.swing.JFrame {

    private Socket servidor;
    private PrivateKey clavePrivPropia;
    private PublicKey clavePubAjena;
    private Usuario userLogeado;
    private Preferencia prefsUser;

    private ButtonGroup btnGRelacion = new ButtonGroup();
    private ButtonGroup btnGTenerHijos = new ButtonGroup();
    private ButtonGroup btnGQuererHijos = new ButtonGroup();

    /**
     * Creates new form PreferencesView
     */
    public PreferencesView(Socket servidor, PrivateKey clavePrivPropia, PublicKey clavePubAjena, Usuario u, Preferencia p) {
        initComponents();
        this.servidor = servidor;
        this.clavePrivPropia = clavePrivPropia;
        this.clavePubAjena = clavePubAjena;
        this.userLogeado = u;
        this.prefsUser = p;
        init();
    }

    private void init() {
        crearButtonGroups();
        mostrarPrefs();

    }

    private void crearButtonGroups() {

        btnGRelacion.add(ckbRelationSeria);
        btnGRelacion.add(ckbRelationEsporadica);

        btnGTenerHijos.add(ckbNoTieneHijos);
        btnGTenerHijos.add(ckbTieneHijos);

        btnGQuererHijos.add(ckbQuiereHijos);
        btnGQuererHijos.add(ckbNoQuiereHijos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCreatePrefs = new newscomponents.RSButtonIcon_new();
        jLabel4 = new javax.swing.JLabel();
        ckbRelationEsporadica = new RSMaterialComponent.RSCheckBoxMaterial();
        ckbRelationSeria = new RSMaterialComponent.RSCheckBoxMaterial();
        jLabel5 = new javax.swing.JLabel();
        ckbQuiereHijos = new RSMaterialComponent.RSCheckBoxMaterial();
        ckbNoQuiereHijos = new RSMaterialComponent.RSCheckBoxMaterial();
        jLabel6 = new javax.swing.JLabel();
        ckbNoTieneHijos = new RSMaterialComponent.RSCheckBoxMaterial();
        ckbTieneHijos = new RSMaterialComponent.RSCheckBoxMaterial();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sldDeporte = new javax.swing.JSlider();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelTextIcon2 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelTextIcon3 = new RSMaterialComponent.RSLabelTextIcon();
        sldArte = new javax.swing.JSlider();
        rSLabelTextIcon4 = new RSMaterialComponent.RSLabelTextIcon();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sldPolitica = new javax.swing.JSlider();
        rSLabelTextIcon5 = new RSMaterialComponent.RSLabelTextIcon();
        rSLabelTextIcon6 = new RSMaterialComponent.RSLabelTextIcon();
        jLabel11 = new javax.swing.JLabel();
        cmbInteres = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¿Tienes algun hijo?");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TINDER FAKE");

        btnCreatePrefs.setBackground(new java.awt.Color(65, 123, 67));
        btnCreatePrefs.setText("Confirmar");
        btnCreatePrefs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCreatePrefs.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_FORWARD);
        btnCreatePrefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePrefsActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PREFERENCIAS");

        ckbRelationEsporadica.setForeground(new java.awt.Color(255, 116, 113));
        ckbRelationEsporadica.setText("Esporádica");
        ckbRelationEsporadica.setColorCheck(new java.awt.Color(255, 116, 113));
        ckbRelationEsporadica.setColorUnCheck(new java.awt.Color(255, 116, 113));
        ckbRelationEsporadica.setRippleColor(new java.awt.Color(255, 106, 84));

        ckbRelationSeria.setForeground(new java.awt.Color(255, 116, 113));
        ckbRelationSeria.setText("Seria");
        ckbRelationSeria.setColorCheck(new java.awt.Color(255, 116, 113));
        ckbRelationSeria.setColorUnCheck(new java.awt.Color(255, 116, 113));
        ckbRelationSeria.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ckbRelationSeria.setRippleColor(new java.awt.Color(255, 106, 84));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("GUSTOS");

        ckbQuiereHijos.setForeground(new java.awt.Color(255, 116, 113));
        ckbQuiereHijos.setText("Si");
        ckbQuiereHijos.setColorCheck(new java.awt.Color(255, 116, 113));
        ckbQuiereHijos.setColorUnCheck(new java.awt.Color(255, 116, 113));
        ckbQuiereHijos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ckbQuiereHijos.setRippleColor(new java.awt.Color(255, 106, 84));

        ckbNoQuiereHijos.setForeground(new java.awt.Color(255, 116, 113));
        ckbNoQuiereHijos.setText("No");
        ckbNoQuiereHijos.setColorCheck(new java.awt.Color(255, 116, 113));
        ckbNoQuiereHijos.setColorUnCheck(new java.awt.Color(255, 116, 113));
        ckbNoQuiereHijos.setRippleColor(new java.awt.Color(255, 106, 84));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("¿Cual es tu interes?");

        ckbNoTieneHijos.setForeground(new java.awt.Color(255, 116, 113));
        ckbNoTieneHijos.setText("No");
        ckbNoTieneHijos.setColorCheck(new java.awt.Color(255, 116, 113));
        ckbNoTieneHijos.setColorUnCheck(new java.awt.Color(255, 116, 113));
        ckbNoTieneHijos.setRippleColor(new java.awt.Color(255, 106, 84));

        ckbTieneHijos.setForeground(new java.awt.Color(255, 116, 113));
        ckbTieneHijos.setText("Si");
        ckbTieneHijos.setColorCheck(new java.awt.Color(255, 116, 113));
        ckbTieneHijos.setColorUnCheck(new java.awt.Color(255, 116, 113));
        ckbTieneHijos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ckbTieneHijos.setRippleColor(new java.awt.Color(255, 106, 84));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("¿Que relación buscas en Tinder Fake?");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("¿Pasión por el deporte?");

        rSLabelTextIcon1.setForeground(new java.awt.Color(255, 116, 113));
        rSLabelTextIcon1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSLabelTextIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SENTIMENT_SATISFIED);

        rSLabelTextIcon2.setForeground(new java.awt.Color(255, 116, 113));
        rSLabelTextIcon2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rSLabelTextIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SENTIMENT_DISSATISFIED);

        rSLabelTextIcon3.setForeground(new java.awt.Color(255, 116, 113));
        rSLabelTextIcon3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rSLabelTextIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SENTIMENT_DISSATISFIED);

        rSLabelTextIcon4.setForeground(new java.awt.Color(255, 116, 113));
        rSLabelTextIcon4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSLabelTextIcon4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SENTIMENT_SATISFIED);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("¿Pasión por el arte?");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("¿Pasión por la politica?");

        rSLabelTextIcon5.setForeground(new java.awt.Color(255, 116, 113));
        rSLabelTextIcon5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rSLabelTextIcon5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SENTIMENT_DISSATISFIED);

        rSLabelTextIcon6.setForeground(new java.awt.Color(255, 116, 113));
        rSLabelTextIcon6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSLabelTextIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SENTIMENT_SATISFIED);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("¿Quieres tener hijos?");

        cmbInteres.setEditable(true);
        cmbInteres.setForeground(new java.awt.Color(255, 116, 113));
        cmbInteres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona uno...", "Hombres", "Mujeres", "Ambos" }));
        cmbInteres.setColorMaterial(new java.awt.Color(255, 116, 113));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("PERSONALIDAD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(ckbRelationSeria, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(ckbRelationEsporadica, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(185, 185, 185))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(rSLabelTextIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(sldPolitica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rSLabelTextIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(rSLabelTextIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sldDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rSLabelTextIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(ckbQuiereHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(ckbNoQuiereHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(96, 96, 96)
                                                .addComponent(ckbTieneHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(ckbNoTieneHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(145, 145, 145)
                                        .addComponent(rSLabelTextIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(sldArte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(btnCreatePrefs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbRelationSeria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbRelationEsporadica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ckbQuiereHijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckbNoQuiereHijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckbTieneHijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ckbNoTieneHijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 32, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sldPolitica, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelTextIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelTextIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelTextIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sldDeporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelTextIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sldArte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelTextIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnCreatePrefs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreatePrefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePrefsActionPerformed
        try {
            if (comprobarForm()) {
                Utilities.enviarOrden(CodeResponse.CONTINUAR_CODE, clavePubAjena, servidor);
                Preferencia nP = crearPrefs();
                //Ciframos y enviamos preferencias
                SealedObject so = Seguridad.cifrar(clavePubAjena, nP);
                Comunicacion.enviarObjeto(servidor, so);

                if (this.prefsUser != null) {
                    //enviamos codigo actu pref
                    Utilities.enviarOrden(CodeResponse.PREFS_ACTUALIZAR_CODE, clavePubAjena, servidor);
                    correcto(nP);
                } else {
                    //enviamos codigo crear pref
                    Utilities.enviarOrden(CodeResponse.PREFS_CREAR_CODE, clavePubAjena, servidor);
                    correcto(nP);
                }
                this.prefsUser = nP;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException ex) {
            Logger.getLogger(PreferencesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreatePrefsActionPerformed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        MainView main = new MainView(servidor, clavePrivPropia, clavePubAjena, userLogeado, this.prefsUser);
        main.setVisible(true);
        //this.dispose();
        Utilities.enviarOrden(CodeResponse.SALIR_CODE, clavePubAjena, servidor);
        //dialog.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        //Utilities.enviarOrden(CodeResponse.SALIR_CODE, clavePubAjena, servidor);
    }//GEN-LAST:event_formWindowClosed

    private void correcto(Preferencia nP) {
        short res = Utilities.recibirOrden(servidor, clavePrivPropia);
        if (res == CodeResponse.PREFS_CORRECTO_CODE) {
            Utilities.showMessage("Se han creado con exito", "EXITO PREFERENCIAS", JOptionPane.PLAIN_MESSAGE);
            MainView main = new MainView(servidor, clavePrivPropia, clavePubAjena, userLogeado, nP);
            main.setVisible(true);
            this.dispose();
        } else {
            Utilities.showMessage("Error al crear preferencias", "ERROR PREFERENCIAS", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarPrefs() {
        if (this.prefsUser != null) {
            //las muestro en pantalla
            String relacion = this.prefsUser.getTiporelacion();
            String interes = this.prefsUser.getInteres();
            boolean quiereHijos = this.prefsUser.isQhijos();
            boolean tieneHijos = this.prefsUser.isThijos();
            int deporte = this.prefsUser.getDeporte();
            int arte = this.prefsUser.getArte();
            int politica = this.prefsUser.getPolitica();

            cmbInteres.setSelectedItem(interes);
            seleccionarRelacion(relacion);
            seleccionarQHijos(quiereHijos);
            seleccionarTHijos(tieneHijos);
            sldArte.setValue(arte);
            sldDeporte.setValue(deporte);
            sldPolitica.setValue(politica);
        }
    }

    private void seleccionarTHijos(boolean tieneHijos) {
        if (tieneHijos) {
            ckbTieneHijos.setSelected(true);
        } else {
            ckbNoTieneHijos.setSelected(true);
        }
    }

    private void seleccionarQHijos(boolean quiereHijos) {
        if (quiereHijos) {
            ckbQuiereHijos.setSelected(true);
        } else {
            ckbNoQuiereHijos.setSelected(true);
        }
    }

    private void seleccionarRelacion(String relacion) {
        if (relacion.equals(ConstantesPreferencias.RELACION_ESPO)) {
            ckbRelationEsporadica.setSelected(true);
        } else {
            ckbRelationSeria.setSelected(true);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private newscomponents.RSButtonIcon_new btnCreatePrefs;
    private RSMaterialComponent.RSCheckBoxMaterial ckbNoQuiereHijos;
    private RSMaterialComponent.RSCheckBoxMaterial ckbNoTieneHijos;
    private RSMaterialComponent.RSCheckBoxMaterial ckbQuiereHijos;
    private RSMaterialComponent.RSCheckBoxMaterial ckbRelationEsporadica;
    private RSMaterialComponent.RSCheckBoxMaterial ckbRelationSeria;
    private RSMaterialComponent.RSCheckBoxMaterial ckbTieneHijos;
    private RSMaterialComponent.RSComboBoxMaterial cmbInteres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon2;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon3;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon4;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon5;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon6;
    private javax.swing.JSlider sldArte;
    private javax.swing.JSlider sldDeporte;
    private javax.swing.JSlider sldPolitica;
    // End of variables declaration//GEN-END:variables

    private Preferencia crearPrefs() {
        String relacion = obtenerRelacion();
        String interes = String.valueOf(cmbInteres.getSelectedItem());
        boolean quiereHijos = obtenerQHijos();
        boolean tieneHijos = obtenerTHijos();
        int deporte = sldDeporte.getValue();
        int arte = sldArte.getValue();
        int politica = sldPolitica.getValue();

        return new Preferencia(userLogeado.getId(), relacion, interes, arte, deporte, politica, tieneHijos, quiereHijos);
    }

    private boolean comprobarForm() {
        boolean correcto = false;

        if (btnGRelacion.getSelection() != null && btnGQuererHijos.getSelection() != null
                && btnGTenerHijos.getSelection() != null && cmbInteres.getSelectedIndex() != 0) {

            correcto = true;

        } else {
            Utilities.showMessage("Rellenar todos los campo", "ERROR PREFERENCIAS", JOptionPane.ERROR_MESSAGE);
        }

        return correcto;
    }

    private boolean obtenerQHijos() {
        boolean quiereHijos = false;

        if (ckbQuiereHijos.isSelected()) {
            quiereHijos = true;
        }

        return quiereHijos;
    }

    private boolean obtenerTHijos() {
        boolean tieneHijos = false;

        if (ckbTieneHijos.isSelected()) {
            tieneHijos = true;
        }

        return tieneHijos;
    }

    private String obtenerRelacion() {
        String relacion;
        if (ckbRelationSeria.isSelected()) {
            relacion = ConstantesPreferencias.RELACION_SERIA;
        } else {
            relacion = ConstantesPreferencias.RELACION_ESPO;
        }

        return relacion;
    }
}
