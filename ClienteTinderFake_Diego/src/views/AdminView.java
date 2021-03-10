/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import codigos.CodeResponse;
import comunicacion.Comunicacion;
import datos.Preferencia;
import datos.Usuario;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import seguridad.Seguridad;
import utilities.Utilities;

/**
 *
 * @author Diego
 */
public class AdminView extends javax.swing.JFrame {

    private Socket servidor;
    private PrivateKey clavePrivPropia;
    private PublicKey clavePubAjena;
    private Usuario userLogeado;
    private ArrayList<Usuario> listaUsuarios;

    /**
     * Creates new form AdminView
     */
    public AdminView(Socket servidor, PrivateKey clavePrivPropia, PublicKey clavePubAjena, Usuario u) {
        initComponents();
        this.servidor = servidor;
        this.clavePrivPropia = clavePrivPropia;
        this.clavePubAjena = clavePubAjena;
        this.userLogeado = u;
        cargarTabla();
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
        btnAscRol = new newscomponents.RSButtonIcon_new();
        btnAddUser = new newscomponents.RSButtonIcon_new();
        btnActiveUser = new newscomponents.RSButtonIcon_new();
        btnDelUser = new newscomponents.RSButtonIcon_new();
        btnDescRol = new newscomponents.RSButtonIcon_new();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlTable = new javax.swing.JScrollPane();
        tbUsers = new RSMaterialComponent.RSTableMetroCustom();
        btnVolveAd = new newscomponents.RSButtonIcon_new();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAscRol.setBackground(new java.awt.Color(255, 153, 51));
        btnAscRol.setText("ASCENDER");
        btnAscRol.setBackgroundHover(new java.awt.Color(204, 102, 0));
        btnAscRol.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnAscRol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAscRol.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_DROP_UP);
        btnAscRol.setRound(10);
        btnAscRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscRolActionPerformed(evt);
            }
        });

        btnAddUser.setBackground(new java.awt.Color(0, 153, 0));
        btnAddUser.setText("Añadir");
        btnAddUser.setBackgroundHover(new java.awt.Color(0, 102, 51));
        btnAddUser.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnAddUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddUser.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_CIRCLE);
        btnAddUser.setRound(10);
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnActiveUser.setBackground(new java.awt.Color(51, 102, 255));
        btnActiveUser.setText("Activar");
        btnActiveUser.setBackgroundHover(new java.awt.Color(0, 51, 153));
        btnActiveUser.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnActiveUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActiveUser.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK_CIRCLE);
        btnActiveUser.setRound(10);
        btnActiveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveUserActionPerformed(evt);
            }
        });

        btnDelUser.setBackground(new java.awt.Color(255, 0, 0));
        btnDelUser.setText("Eliminar");
        btnDelUser.setBackgroundHover(new java.awt.Color(204, 0, 51));
        btnDelUser.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnDelUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelUser.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnDelUser.setRound(10);
        btnDelUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelUserActionPerformed(evt);
            }
        });

        btnDescRol.setBackground(new java.awt.Color(153, 0, 255));
        btnDescRol.setText("DESCENDER");
        btnDescRol.setBackgroundHover(new java.awt.Color(153, 0, 153));
        btnDescRol.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnDescRol.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_DROP_DOWN);
        btnDescRol.setRound(10);
        btnDescRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescRolActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("USUARIOS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("ROLES");

        tbUsers.setBackground(new java.awt.Color(204, 204, 204));
        tbUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Email", "Activado", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsers.setBackgoundHead(new java.awt.Color(204, 102, 255));
        tbUsers.setBackgoundHover(new java.awt.Color(153, 102, 255));
        tbUsers.setColorBorderHead(new java.awt.Color(255, 255, 255));
        pnlTable.setViewportView(tbUsers);

        btnVolveAd.setBackground(new java.awt.Color(255, 0, 0));
        btnVolveAd.setText("Volver");
        btnVolveAd.setBackgroundHover(new java.awt.Color(204, 0, 51));
        btnVolveAd.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        btnVolveAd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolveAd.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_BACK);
        btnVolveAd.setRound(10);
        btnVolveAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolveAdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActiveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(btnAscRol, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDescRol, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel1)
                                .addGap(265, 265, 265)
                                .addComponent(jLabel2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(btnVolveAd, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAscRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActiveUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolveAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAscRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscRolActionPerformed
        actuar(CodeResponse.ASC_CODE, "Exito al ascender al Usuario", "Error al ascender al Usuario", "ASCENDER USUARIO");
    }//GEN-LAST:event_btnAscRolActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        //Utilities.enviarOrden(CodeResponse.ADD_CODE, clavePubAjena, servidor);
        System.out.println("ENVIO MODO SIGN UP");
        Utilities.enviarOrden(CodeResponse.SIGNUP_CODE, clavePubAjena, servidor);
        mostraSignUpDialog();
        cargarTabla();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnActiveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveUserActionPerformed
        actuar(CodeResponse.ACTIVAR_CODE, "Exito al activar al Usuario", "Error al activar al Usuario", "ACTIVAR USUARIO");
    }//GEN-LAST:event_btnActiveUserActionPerformed

    private void btnDelUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelUserActionPerformed
        actuar(CodeResponse.DELETE_CODE, "Exito al eliminar al Usuario", "Error al eliminar al Usuario", "ELIMINAR USUARIO");
    }//GEN-LAST:event_btnDelUserActionPerformed

    private void btnDescRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescRolActionPerformed
        actuar(CodeResponse.DESC_CODE, "Exito al descender al Usuario", "Error al descender al Usuario", "DESCENDER USUARIO");
    }//GEN-LAST:event_btnDescRolActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Utilities.enviarOrden(CodeResponse.ADMIN_FIN_CODE, clavePubAjena, servidor);
    }//GEN-LAST:event_formWindowClosing

    private void btnVolveAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolveAdActionPerformed
        Utilities.enviarOrden(CodeResponse.ADMIN_FIN_CODE, clavePubAjena, servidor);
        this.dispose();
    }//GEN-LAST:event_btnVolveAdActionPerformed
   
    private ArrayList<Usuario> rellenarLista() {
        ArrayList<Usuario> listaUsersNoCifrados = new ArrayList<>();

        try {
            ArrayList<SealedObject> users = (ArrayList<SealedObject>) Comunicacion.recibirObjeto(servidor);
            System.out.println("RECIBIDA LISTA USERS CIFRADOS");

            for (int i = 0; i < users.size(); i++) {
                Usuario u = (Usuario) Seguridad.descifrar(clavePrivPropia, users.get(i));
                listaUsersNoCifrados.add(u);
            }

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }

        return listaUsersNoCifrados;
    }

    private void cargarTabla() {
        this.listaUsuarios = rellenarLista();
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tbUsers.getModel();
        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }
        Object[] o = new Object[4];
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario u = listaUsuarios.get(i);
            o[0] = u.getNombre();
            o[1] = u.getEmail();
            o[2] = activado(u.isActivado());
            o[3] = u.getRol();

            modeloTabla.addRow(o);
        }
        this.tbUsers.setModel(modeloTabla);
        this.tbUsers.setDefaultEditor(Object.class, null);
    }

    private String activado(boolean acti) {
        String estado;
        if (acti) {
            estado = "Activado";
        } else {
            estado = "Desactivado";
        }
        return estado;
    }

    private Usuario getUserTable() {
        Usuario user = null;

        if (this.tbUsers.getSelectedRowCount() == 0) {
            Utilities.showMessage("Debes seleccionar al menos a un Usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int row = this.tbUsers.getSelectedRow();
            user = this.listaUsuarios.get(row);
            if (user.getId().equals(userLogeado.getId())) {
                Utilities.showMessage("No puedes modificarte a ti mismo", "ERROR", JOptionPane.ERROR_MESSAGE);
                user = null;
            }
        }
        return user;
    }

    private void actuar(short code, String exito, String error, String titulo) {
        Usuario uSelected = getUserTable();

        if (uSelected != null) {
            try {
                if (code == CodeResponse.ACTIVAR_CODE && uSelected.isActivado()) {
                    Utilities.showMessage("Este usuario ya esta activado", titulo, JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("ENVIO ORDEN DESDE ADMIN VIEW");
                    Utilities.enviarOrden(code, clavePubAjena, servidor);
                    
                    //Envio id usuario seleccionado para modificar
                    SealedObject so = Seguridad.cifrar(clavePubAjena, uSelected.getId());
                    Comunicacion.enviarObjeto(servidor, so);
                    //recibo respuesta en base a mi peticion
                    so = (SealedObject) Comunicacion.recibirObjeto(servidor);
                    short respuesta = (short) Seguridad.descifrar(clavePrivPropia, so);

                    if (respuesta == CodeResponse.ADMIN_EXITO_CODE) {
                        Utilities.showMessage(exito, titulo, JOptionPane.PLAIN_MESSAGE);
                        cargarTabla();
                    } else {
                        Utilities.showMessage(error, titulo, JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException
                    | IllegalBlockSizeException | ClassNotFoundException | BadPaddingException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void mostraSignUpDialog() {        
        final JDialog frame = new JDialog(this, "", true);
        SignUpView signUp = new SignUpView(servidor, clavePrivPropia, clavePubAjena, frame);
        frame.getContentPane().add(signUp.getContentPane());
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private newscomponents.RSButtonIcon_new btnActiveUser;
    private newscomponents.RSButtonIcon_new btnAddUser;
    private newscomponents.RSButtonIcon_new btnAscRol;
    private newscomponents.RSButtonIcon_new btnDelUser;
    private newscomponents.RSButtonIcon_new btnDescRol;
    private newscomponents.RSButtonIcon_new btnVolveAd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane pnlTable;
    private RSMaterialComponent.RSTableMetroCustom tbUsers;
    // End of variables declaration//GEN-END:variables
}
