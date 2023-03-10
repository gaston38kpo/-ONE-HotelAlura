package ar.com.hotel.view;

import ar.com.hotel.App;
import ar.com.hotel.model.Guest;
import ar.com.hotel.model.Reservation;
import ar.com.hotel.utils.CBoxUI;
import ar.com.hotel.utils.UtilsUI;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class GuestView extends javax.swing.JFrame {

    int xMouse, yMouse;
    JFrame reservationFrame;
    Reservation newReservation;

    public GuestView(JFrame reservationFrame, Reservation newReservation) {
        this.reservationFrame = reservationFrame;
        this.newReservation = newReservation;

        initComponents();
        this.myInitComponents();
    }

    private void myInitComponents() {
        this.setCountriesCombobox(nationalityInput);
        nationalityInput.setSelectedIndex(3);
        UtilsUI.setTextFieldPadding(nameInput);
        UtilsUI.setTextFieldPadding(lastnameInput);
        UtilsUI.setTextFieldPadding(phoneInput);
        UtilsUI.setTextFieldPadding(reservationNumberInput);
        UtilsUI.setColorsJCalendar(birthdateInput, Color.WHITE, Color.BLACK);
        nationalityInput.setUI(CBoxUI.createUI(background));
    }

    public static void setCountriesCombobox(JComboBox<String> nationalityInput) {
        nationalityInput.setModel(new DefaultComboBoxModel(new String[]{"afgano", "alemán", "árabe", "argentino", "australiano", "belga", "boliviano", "brasileño", "camboyano", "canadiense", "chileno", "chino", "colombiano", "coreano", "costarricense", "cubano", "danés", "ecuatoriano", "egipcio", "salvadoreño", "escocés", "español", "estadounidense", "estonio", "etiope", "filipino", "finlandés", "francés", "galés", "griego", "guatemalteco", "haitiano", "holandés", "hondureño", "indonés", "inglés", "iraquí", "iraní", "irlandés", "israelí", "italiano", "japonés", "jordano", "laosiano", "letón", "letonés", "malayo", "marroquí", "mexicano", "nicaragüense", "noruego", "neozelandés", "panameño", "paraguayo", "peruano", "polaco", "portugués", "puertorriqueño", "dominicano", "rumano", "ruso", "sueco", "suizo", "tailandés", "taiwanes", "turco", "ucraniano", "uruguayo", "venezolano", "vietnamita"}));
    }

    private void generateNewGuest() {
        String name = nameInput.getText();
        String lastname = lastnameInput.getText();
        String phone = phoneInput.getText();
        Date birthdate = birthdateInput.getDate();
        String nationality = (String) nationalityInput.getSelectedItem();

        if (name.isBlank() || lastname.isBlank() || phone.isBlank() || birthdate == null) {
            App.openQuestion(this, "Por Favor Complete Todos los Campos para Continuar.");
        } else if (this.getDaysBetweenDates(birthdate, new Date()) <= 6570) {
            App.openQuestion(this, "Debe ser Mayor de 18 Años para Reservar!");
        } else {
            Guest newGuest = new Guest(
                    name,
                    lastname,
                    new java.sql.Date(((Date) birthdate).getTime()),
                    nationality,
                    phone);

            Reservation createdReservation = App.reservationController.create(newReservation);

            if (createdReservation != null) {
                Guest createdGuest = App.guestController.create(newGuest, createdReservation.getId());

                App.openQuestion(this, "La Reserva de "
                        + createdGuest.getLastname() + " "
                        + createdGuest.getName() + " "
                        + createdGuest.getNationality() + " con Teléfono: "
                        + createdGuest.getPhone() + ", fue creada con éxito, su numero de reserva es: "
                        + createdReservation.getId());

                this.setFullDataUI(createdReservation.getId());
            }
        }
    }

    private long getDaysBetweenDates(Date fromDate, Date toDate) {
        LocalDateTime from = LocalDateTime.ofInstant(fromDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime to = LocalDateTime.ofInstant(toDate.toInstant(), ZoneId.systemDefault());

        Duration duration = Duration.between(from, to);

        return duration.toDays();
    }

    private void setFullDataUI(Integer reservationId) {
        nameInput.setEnabled(false);
        lastnameInput.setEnabled(false);
        phoneInput.setEnabled(false);
        birthdateInput.setEnabled(false);
        nationalityInput.setEnabled(false);
        reservationNumberInput.setText(String.valueOf(reservationId));
        saveBtn.setEnabled(false);

        returnBtn.setText("FINALIZAR");
        for (ActionListener al : returnBtn.getActionListeners()) {
            returnBtn.removeActionListener(al);
        }
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnNewActionPerformed();
            }
        });
    }

    private void returnBtnNewActionPerformed() {
        this.dispose();
        App.openHotelNavigation();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        mainTitle = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        lastnameLabel = new javax.swing.JLabel();
        lastnameInput = new javax.swing.JTextField();
        birthdateLabel = new javax.swing.JLabel();
        nationalityLabel = new javax.swing.JLabel();
        nationalityInput = new javax.swing.JComboBox<>();
        phoneLabel = new javax.swing.JLabel();
        phoneInput = new javax.swing.JTextField();
        reservarionNumberLabel = new javax.swing.JLabel();
        reservationNumberInput = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        birthdateInput = new com.toedter.calendar.JDateChooser();
        exitBtn = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();
        backgroundImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        topBar.setOpaque(false);
        topBar.setPreferredSize(new java.awt.Dimension(0, 30));
        topBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topBarMouseDragged(evt);
            }
        });
        topBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topBarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        background.add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 854, -1));

        mainTitle.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        mainTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainTitle.setText("Registro del Húesped");
        background.add(mainTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 854, -1));

        nameLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(160, 160, 160));
        nameLabel.setText("Nombre");
        background.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 94, -1, -1));

        nameInput.setBackground(new java.awt.Color(0, 0, 0));
        nameInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        nameInput.setForeground(new java.awt.Color(224, 224, 224));
        nameInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        nameInput.setPreferredSize(new java.awt.Dimension(196, 40));
        background.add(nameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 118, -1, -1));

        lastnameLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        lastnameLabel.setForeground(new java.awt.Color(160, 160, 160));
        lastnameLabel.setText("Apellido");
        background.add(lastnameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 170, -1, -1));

        lastnameInput.setBackground(new java.awt.Color(0, 0, 0));
        lastnameInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        lastnameInput.setForeground(new java.awt.Color(224, 224, 224));
        lastnameInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lastnameInput.setPreferredSize(new java.awt.Dimension(196, 40));
        background.add(lastnameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 194, -1, -1));

        birthdateLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        birthdateLabel.setForeground(new java.awt.Color(160, 160, 160));
        birthdateLabel.setText("Fecha de nacimiento");
        background.add(birthdateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 94, -1, -1));

        nationalityLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        nationalityLabel.setForeground(new java.awt.Color(160, 160, 160));
        nationalityLabel.setText("Nacionalidad");
        background.add(nationalityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 170, -1, -1));

        nationalityInput.setBackground(new java.awt.Color(0, 0, 0));
        nationalityInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        nationalityInput.setForeground(new java.awt.Color(224, 224, 224));
        nationalityInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        nationalityInput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nationalityInput.setPreferredSize(new java.awt.Dimension(196, 40));
        background.add(nationalityInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 194, -1, -1));

        phoneLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        phoneLabel.setForeground(new java.awt.Color(160, 160, 160));
        phoneLabel.setText("Teléfono");
        background.add(phoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 246, -1, -1));

        phoneInput.setBackground(new java.awt.Color(0, 0, 0));
        phoneInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        phoneInput.setForeground(new java.awt.Color(224, 224, 224));
        phoneInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        phoneInput.setPreferredSize(new java.awt.Dimension(196, 40));
        background.add(phoneInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 270, -1, -1));

        reservarionNumberLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        reservarionNumberLabel.setForeground(new java.awt.Color(160, 160, 160));
        reservarionNumberLabel.setText("Número de reserva");
        background.add(reservarionNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 246, -1, -1));

        reservationNumberInput.setBackground(new java.awt.Color(0, 0, 0));
        reservationNumberInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        reservationNumberInput.setForeground(new java.awt.Color(224, 224, 224));
        reservationNumberInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        reservationNumberInput.setText("---");
        reservationNumberInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 2));
        reservationNumberInput.setEnabled(false);
        reservationNumberInput.setPreferredSize(new java.awt.Dimension(196, 40));
        background.add(reservationNumberInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 270, -1, -1));

        saveBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(224, 224, 224));
        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-large.png"))); // NOI18N
        saveBtn.setText("GUARDAR");
        saveBtn.setBorder(null);
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        background.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 322, -1, -1));

        birthdateInput.setBackground(new java.awt.Color(0, 0, 0));
        birthdateInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        birthdateInput.setForeground(new java.awt.Color(224, 224, 224));
        birthdateInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        birthdateInput.setPreferredSize(new java.awt.Dimension(196, 40));
        background.add(birthdateInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 118, -1, -1));

        exitBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(224, 224, 224));
        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        exitBtn.setText("SALIR");
        exitBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exitBtn.setPreferredSize(new java.awt.Dimension(196, 40));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        background.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 384, -1, -1));

        returnBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        returnBtn.setForeground(new java.awt.Color(224, 224, 224));
        returnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        returnBtn.setText("VOLVER");
        returnBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        returnBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returnBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        returnBtn.setPreferredSize(new java.awt.Dimension(196, 40));
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });
        background.add(returnBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 384, -1, -1));

        backgroundImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/dirt-background.png"))); // NOI18N
        backgroundImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        background.add(backgroundImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - this.xMouse, y - this.yMouse);
    }//GEN-LAST:event_topBarMouseDragged

    private void topBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMousePressed
        this.xMouse = evt.getX();
        this.yMouse = evt.getY();
    }//GEN-LAST:event_topBarMousePressed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        this.dispose();
        reservationFrame.setVisible(true);
    }//GEN-LAST:event_returnBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        this.generateNewGuest();
    }//GEN-LAST:event_saveBtnActionPerformed

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
            java.util.logging.Logger.getLogger(GuestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>-

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestView(new ReservationView(), new Reservation(new java.sql.Date(((Date) new Date()).getTime()), new java.sql.Date(((Date) new Date()).getTime()), new BigDecimal("10000.00"), "EFECTIVO")).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel backgroundImg;
    private com.toedter.calendar.JDateChooser birthdateInput;
    private javax.swing.JLabel birthdateLabel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JTextField lastnameInput;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JTextField nameInput;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> nationalityInput;
    private javax.swing.JLabel nationalityLabel;
    private javax.swing.JTextField phoneInput;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel reservarionNumberLabel;
    private javax.swing.JTextField reservationNumberInput;
    private javax.swing.JButton returnBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
