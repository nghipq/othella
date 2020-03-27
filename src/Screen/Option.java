/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author phamq
 */
public class Option extends javax.swing.JFrame implements Runnable {

    private Color bgcolor = new Color(0, 102, 51);
    private Color active = new Color(204, 204, 0);
    private Color unactive = new Color(0, 0, 0);
    private int mode = 0;
    private int rows = 8;
    private int cols = 8;
    private JLabel[] cells;
    public PnBoard game = new PnBoard(8, 8, bgcolor, mode);
    private JPanel help = new HelpPanel();
    private JPanel about = new AboutPanel();
    private JPanel rank = new JPanel();
    public int n = 0;

    /**
     * Creates new form Option
     */
    public Option() {
        super("Reversi - ひかりGroup");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.png"));
        this.setIconImage(icon);

        initComponents();

        cells = new JLabel[rows * cols];

        for (int i = 0; i < rows * cols; i++) {
            cells[i] = new JLabel();
            cells[i].setBackground(bgcolor);
            cells[i].setOpaque(true);
            cells[i].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
            Pnboard.add(cells[i]);
        }
    }

    @Override
    public void run() {
        if (game.isClick()) {
            game.loadBoard(n);
            n++;
            if(n == 4) {
                game.setClick(false);
                game.BoardGame.add(new Reversi(game.board));
                n = 0;
            }
        }
    }

    public void loadBoard() {
        for (int i = 0; i < rows * cols; i++) {
            cells[i].setBackground(bgcolor);
        }
    }

    public void game() {
        game = new PnBoard(this.rows, this.cols, bgcolor, mode);
        this.setLayout(new BorderLayout());
        this.add(game, BorderLayout.CENTER);
        game.play();
        pack();
    }

    public void exit() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure to close this program?", "", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            this.dispose();
        }
    }

    public void option() {
        remove(game);
        remove(help);
        remove(about);
        remove(rank);
        add(HomePN, BorderLayout.CENTER);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HomePN = new javax.swing.JPanel();
        PnBorderBoard = new javax.swing.JPanel();
        Pnboard = new javax.swing.JPanel();
        PnHomeEdit = new javax.swing.JPanel();
        LBOption = new javax.swing.JLabel();
        LBMode = new javax.swing.JLabel();
        CBMode = new javax.swing.JComboBox<>();
        LBBackground = new javax.swing.JLabel();
        BtnGreen = new javax.swing.JButton();
        BtnBrown = new javax.swing.JButton();
        BtnStart = new javax.swing.JButton();
        BtnExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnNew = new javax.swing.JMenuItem();
        mnOption = new javax.swing.JMenuItem();
        mnRank = new javax.swing.JMenuItem();
        mnHelp = new javax.swing.JMenuItem();
        mnAboutUs = new javax.swing.JMenuItem();
        mnExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        HomePN.setBackground(new java.awt.Color(0, 102, 102));
        HomePN.setPreferredSize(new java.awt.Dimension(500, 300));

        PnBorderBoard.setBackground(new java.awt.Color(102, 0, 0));
        PnBorderBoard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Pnboard.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Pnboard.setPreferredSize(new java.awt.Dimension(262, 262));
        Pnboard.setLayout(new java.awt.GridLayout(8, 8));

        javax.swing.GroupLayout PnBorderBoardLayout = new javax.swing.GroupLayout(PnBorderBoard);
        PnBorderBoard.setLayout(PnBorderBoardLayout);
        PnBorderBoardLayout.setHorizontalGroup(
            PnBorderBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnBorderBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pnboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PnBorderBoardLayout.setVerticalGroup(
            PnBorderBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnBorderBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pnboard, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        PnHomeEdit.setBackground(new java.awt.Color(255, 255, 255));
        PnHomeEdit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LBOption.setBackground(new java.awt.Color(0, 102, 51));
        LBOption.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        LBOption.setForeground(new java.awt.Color(0, 102, 51));
        LBOption.setText("Option");

        LBMode.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBMode.setForeground(new java.awt.Color(0, 102, 51));
        LBMode.setText("Game Mode");

        CBMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Player", "2 Players" }));
        CBMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBModeActionPerformed(evt);
            }
        });

        LBBackground.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBBackground.setForeground(new java.awt.Color(0, 102, 51));
        LBBackground.setText("Board");

        BtnGreen.setBackground(new java.awt.Color(0, 102, 51));
        BtnGreen.setForeground(new java.awt.Color(255, 255, 255));
        BtnGreen.setText("Green");
        BtnGreen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 0), 3));
        BtnGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGreenActionPerformed(evt);
            }
        });

        BtnBrown.setBackground(new java.awt.Color(255, 213, 13));
        BtnBrown.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        BtnBrown.setText("Yellow");
        BtnBrown.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        BtnBrown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBrownActionPerformed(evt);
            }
        });

        BtnStart.setBackground(new java.awt.Color(0, 102, 51));
        BtnStart.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        BtnStart.setForeground(new java.awt.Color(255, 255, 255));
        BtnStart.setText("START");
        BtnStart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnStartActionPerformed(evt);
            }
        });

        BtnExit.setBackground(new java.awt.Color(0, 102, 51));
        BtnExit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        BtnExit.setForeground(new java.awt.Color(255, 255, 255));
        BtnExit.setText("QUIT");
        BtnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnHomeEditLayout = new javax.swing.GroupLayout(PnHomeEdit);
        PnHomeEdit.setLayout(PnHomeEditLayout);
        PnHomeEditLayout.setHorizontalGroup(
            PnHomeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnHomeEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnHomeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnHomeEditLayout.createSequentialGroup()
                        .addGroup(PnHomeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CBMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LBOption, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LBBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PnHomeEditLayout.createSequentialGroup()
                        .addComponent(BtnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(BtnBrown, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PnHomeEditLayout.setVerticalGroup(
            PnHomeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnHomeEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LBOption)
                .addGap(18, 18, 18)
                .addComponent(LBMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBBackground)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnHomeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGreen)
                    .addComponent(BtnBrown))
                .addGap(25, 25, 25)
                .addComponent(BtnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout HomePNLayout = new javax.swing.GroupLayout(HomePN);
        HomePN.setLayout(HomePNLayout);
        HomePNLayout.setHorizontalGroup(
            HomePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePNLayout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(PnHomeEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(HomePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(HomePNLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnBorderBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        HomePNLayout.setVerticalGroup(
            HomePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnHomeEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(HomePNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(HomePNLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnBorderBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jMenu1.setText("Menu");

        mnNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnNew.setMnemonic('N');
        mnNew.setText("New");
        mnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNewActionPerformed(evt);
            }
        });
        jMenu1.add(mnNew);

        mnOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnOption.setMnemonic('O');
        mnOption.setText("Option");
        mnOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOptionActionPerformed(evt);
            }
        });
        jMenu1.add(mnOption);

        mnRank.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mnRank.setText("Rank");
        mnRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRankActionPerformed(evt);
            }
        });
        jMenu1.add(mnRank);

        mnHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mnHelp.setMnemonic('H');
        mnHelp.setText("Help");
        mnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHelpActionPerformed(evt);
            }
        });
        jMenu1.add(mnHelp);

        mnAboutUs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnAboutUs.setMnemonic('A');
        mnAboutUs.setText("About Us");
        mnAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAboutUsActionPerformed(evt);
            }
        });
        jMenu1.add(mnAboutUs);

        mnExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mnExit.setMnemonic('E');
        mnExit.setText("Exit");
        mnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(HomePN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(HomePN, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnStartActionPerformed
        // TODO add your handling code here:
        this.remove(HomePN);
//        game();
        game();
    }//GEN-LAST:event_BtnStartActionPerformed

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_BtnExitActionPerformed

    private void CBModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBModeActionPerformed
        // TODO add your handling code here:
        this.mode = CBMode.getSelectedIndex();
    }//GEN-LAST:event_CBModeActionPerformed

    private void BtnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGreenActionPerformed
        // TODO add your handling code here:
        this.bgcolor = BtnGreen.getBackground();
        BtnGreen.setBorder(BorderFactory.createLineBorder(active, 2));
        BtnBrown.setBorder(BorderFactory.createLineBorder(unactive, 1));
        loadBoard();
    }//GEN-LAST:event_BtnGreenActionPerformed

    private void BtnBrownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBrownActionPerformed
        // TODO add your handling code here:
        this.bgcolor = BtnBrown.getBackground();
        BtnBrown.setBorder(BorderFactory.createLineBorder(active, 2));
        BtnGreen.setBorder(BorderFactory.createLineBorder(unactive, 1));
        loadBoard();
    }//GEN-LAST:event_BtnBrownActionPerformed

    private void mnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHelpActionPerformed
        // TODO add your handling code here:
        this.remove(HomePN);
        this.remove(game);
        this.remove(about);
        this.remove(rank);
        this.setLayout(new BorderLayout());
        this.add(help, BorderLayout.CENTER);
        pack();
    }//GEN-LAST:event_mnHelpActionPerformed

    private void mnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAboutUsActionPerformed
        // TODO add your handling code here:
        this.remove(HomePN);
        this.remove(game);
        this.remove(help);
        this.remove(rank);
        this.setLayout(new BorderLayout());
        this.add(about, BorderLayout.CENTER);
        pack();
    }//GEN-LAST:event_mnAboutUsActionPerformed

    private void mnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExitActionPerformed
        // TODO add your handling code here:
        exit();
    }//GEN-LAST:event_mnExitActionPerformed

    private void mnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNewActionPerformed
        // TODO add your handling code here:
        this.remove(game);
        this.remove(help);
        this.remove(HomePN);
        this.remove(rank);
        game = new PnBoard(this.rows, this.cols, bgcolor, mode);
        this.setLayout(new BorderLayout());
        this.add(game, BorderLayout.CENTER);
        pack();
        game.play();
    }//GEN-LAST:event_mnNewActionPerformed

    private void mnOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOptionActionPerformed
        // TODO add your handling code here:
        option();
    }//GEN-LAST:event_mnOptionActionPerformed

    private void mnRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRankActionPerformed
        // TODO add your handling code here:
        this.remove(HomePN);
        this.remove(game);
        this.remove(help);
        this.remove(about);
        this.setLayout(new BorderLayout());
        try {
            rank = new Ranking();
            this.add(rank, BorderLayout.CENTER);
        } catch (SQLException ex) {
            Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
        }
        pack();
    }//GEN-LAST:event_mnRankActionPerformed

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
            java.util.logging.Logger.getLogger(Option.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Option.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Option.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Option.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Option().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBrown;
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton BtnGreen;
    private javax.swing.JButton BtnStart;
    private javax.swing.JComboBox<String> CBMode;
    private javax.swing.JPanel HomePN;
    private javax.swing.JLabel LBBackground;
    private javax.swing.JLabel LBMode;
    private javax.swing.JLabel LBOption;
    private javax.swing.JPanel PnBorderBoard;
    private javax.swing.JPanel PnHomeEdit;
    private javax.swing.JPanel Pnboard;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnAboutUs;
    private javax.swing.JMenuItem mnExit;
    private javax.swing.JMenuItem mnHelp;
    private javax.swing.JMenuItem mnNew;
    private javax.swing.JMenuItem mnOption;
    private javax.swing.JMenuItem mnRank;
    // End of variables declaration//GEN-END:variables
}
