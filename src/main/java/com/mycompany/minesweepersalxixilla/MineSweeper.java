/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.minesweepersalxixilla;

import static com.mycompany.minesweepersalxixilla.MineButton.BUTTON_SIZE;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author alu10654799
 */
public class MineSweeper extends javax.swing.JFrame {

    /**
     * Creates new form MineSweeper
     */
    public MineSweeper() {
        initComponents();
        setLocationRelativeTo(null);
        board.initBoard();
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        int width = numCols * MineButton.BUTTON_SIZE;
        int height = numRows * MineButton.BUTTON_SIZE;
        setPlayIcon();
        board.setPreferredSize(new Dimension(width, height));
        pack();
    }

    private void setPlayIcon() {
        Image image = new ImageIcon(getClass().getResource("/images/smiley.png")).getImage();
        Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        smileButton.setIcon(icon);
    }
    
    public void reset(){
        board.removeAll();
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        int width = numCols * MineButton.BUTTON_SIZE;
        int height = numRows * MineButton.BUTTON_SIZE;
        board.setLayout(new GridLayout(numRows,numCols));
        board.initBoard();
        board.setPreferredSize(new Dimension(width, height));
        board.validate();
        pack();
        setLocationRelativeTo(null);
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
        jComboBox1 = new javax.swing.JComboBox<>();
        smileButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel1 = new javax.swing.JPanel();
        board = new com.mycompany.minesweepersalxixilla.Board();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Begineer", "Intermediate", "Dificult" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        smileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        smileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        smileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smileButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(smileButton);
        jToolBar1.add(filler1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(51, 0, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(board, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setBounds(0, 0, 410, 330);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        switch (jComboBox1.getSelectedIndex()) {
            case 0:
                ConfigData.getInstance().setLevel(LevelType.BEGINNER);
                break;
            case 1:
                ConfigData.getInstance().setLevel(LevelType.INTERMEDIATE);
                break;    
            case 2:
                ConfigData.getInstance().setLevel(LevelType.DIFFICULT);
                break;    
            default:
                throw new AssertionError();
        }
        reset();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void smileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smileButtonActionPerformed
        if (ConfigData.getInstance().getGameOver() != true) {
            reset();
        }
    }//GEN-LAST:event_smileButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MineSweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MineSweeper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.minesweepersalxixilla.Board board;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton smileButton;
    // End of variables declaration//GEN-END:variables
}
