/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.minesweepersalxixilla;

import static com.mycompany.minesweepersalxixilla.MineButton.BUTTON_SIZE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

/**
 *
 * @author alu10654799
 */
public class Board extends javax.swing.JPanel {

    private int[][] matrix;
    private MineButton[][] buttonMatrix;
    private static Icon iconButton = null;
    private static MouseAdapter mouseAdapter = null;
    private int currentRow;
    private int currentCol;

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        setLayout(new GridLayout(numRows, numCols));
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MineButton b = (MineButton) e.getComponent();
                if (e.getButton() == MineButton.LEFT_BUTTON) {
                    if (b.getState() == ButtonState.CLOSE) {
                        eraseZeros(b.getRow(), b.getCol());
                    }
                    if (b.getState() == ButtonState.OPEN
                            && matrix[b.getRow()][b.getCol()] == -1) {
                        exploteBombs();
                    }
                }
            }
        };
    }

    public void initBoard() {
        currentRow = 0;
        currentCol = 0;
        Dimension panelDimension = new Dimension(MineButton.BUTTON_SIZE, MineButton.BUTTON_SIZE);
        Image image = new ImageIcon(getClass().getResource("/images/bomb.png")).getImage();
        Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH);
        iconButton = new ImageIcon(newimg);
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        buttonMatrix = new MineButton[numRows][numCols];
        initMatrix(numRows, numCols);
        addBombs(numRows, numCols);
        addOne(numRows, numCols);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(panelDimension);
                panel.setLayout(new OverlayLayout(panel));
                MineButton button = new MineButton(row, col);
                buttonMatrix[row][col] = button;
                panel.add(button);
                button.addMouseListener(mouseAdapter);
                JLabel label = putImages(row, col);
                label.setPreferredSize(panelDimension);
                label.setMinimumSize(panelDimension);
                label.setMaximumSize(panelDimension);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(new Font("Oswald", Font.BOLD, 20));
                panel.add(label);
                if(matrix[row][col] == -1){
                    panel.setBackground(Color.red);
                }
                add(panel);
            }
        }
        printMatrix(numRows, numCols);
    }

    public void initMatrix(int rows, int cols) {
        matrix = new int[rows][cols];
        buttonMatrix = new MineButton[rows][cols];
        for (int row = 0; row < ConfigData.getInstance().getNumRows(); row++) {
            for (int col = 0; col < ConfigData.getInstance().getNumCols(); col++) {
                matrix[row][col] = 0;
            }
        }
    }

    public void addBombs(int numRows, int numCols) {
        int bombCounter = 0;
        while (bombCounter < ConfigData.getInstance().getNumBombs()) {
            int row = (int) (Math.random() * ConfigData.getInstance().getNumRows());
            int col = (int) (Math.random() * ConfigData.getInstance().getNumCols());
            if (matrix[row][col] != -1) {
                matrix[row][col] = -1;
                bombCounter++;
            }
        }
    }

    public void printMatrix(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println("");
        }
    }

    public JLabel putImages(int row, int col) {
        int number = matrix[row][col];
        Image image = new ImageIcon(getClass().getResource("/images/bomb.png")).getImage();
        Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH);
        iconButton = new ImageIcon(newimg);
        JLabel label = new JLabel("");
        switch (number) {
            case -1:
                label = new JLabel(iconButton);
                break;
            case 1:
                label = new JLabel("" + number);
                label.setForeground(Color.blue);
                break;
            case 2:
                label = new JLabel("" + number);
                label.setForeground(Color.orange);
                break;
            case 3:
                label = new JLabel("" + number);
                label.setForeground(Color.green);
                break;
            case 4:
                label = new JLabel("" + number);
                label.setForeground(Color.red);
                break;
            case 5:
                label = new JLabel("" + number);
                label.setForeground(Color.magenta);
                break;
            case 6:
                label = new JLabel("" + number);
                label.setForeground(Color.CYAN);
                break;
            case 7:
                label = new JLabel("" + number);
                label.setForeground(Color.yellow);
                break;
            case 8:
                label = new JLabel("" + number);
                label.setForeground(Color.pink);
                break;
            default:
                break;
        }
        return label;
    }

    public void addOne(int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == -1) {
                    addOneAround(r, c);
                }
            }
        }
    }

    public void addOneAround(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    int numRows = row + i;
                    int numCols = col + j;
                    if (isValid(numRows, numCols)) {
                        if (matrix[row + i][col + j] != -1) {
                            matrix[row + i][col + j] += 1;
                        }
                    }
                }
            }
        }
    }

    public void eraseZeros(int row, int col) {
        if (!isValid(row, col)) {
            return;
        }
        MineButton button = buttonMatrix[row][col];
        if (button.getState() != ButtonState.CLOSE) {
            return;
        }
        button.open();
        if (matrix[row][col] == 0) {
            for (int r = -1; r <= 1; r++) {
                for (int c = -1; c <= 1; c++) {
                    int newRow = row + r;
                    int newCol = col + c;
                    if (isValid(newRow, newCol)) {
                        MineButton newButton = buttonMatrix[newRow][newCol];
                        if (!(newRow == row && newCol == col)
                                && (newButton.getState() != ButtonState.OPEN)) {
                            eraseZeros(newRow, newCol);
                        }
                    }
                }
            }
        }
    }

    public void exploteBombs() {
        deleteMouseAdapter();
        ConfigData.getInstance().setGameOver(true);
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();

        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentRow < numRows) {
                    if (matrix[currentRow][currentCol] == -1) {
                        buttonMatrix[currentRow][currentCol].open();
                    }

                    currentCol++;

                    if (currentCol >= numCols) {
                        currentCol = 0;
                        currentRow++;
                    }
                } else {
                    ConfigData.getInstance().setGameOver(false);
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "GAME OVER!!!", "Error", JOptionPane.ERROR_MESSAGE);// Detener el temporizador cuando todas las casillas estén abiertas
                }
            }
        });

        timer.start();
    }

    public void deleteMouseAdapter() {
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                buttonMatrix[row][col].removeMouseListener(mouseAdapter);
            }
        }
    }

    

    public boolean isValid(int rows, int cols) {
        return rows >= 0 && rows < ConfigData.getInstance().getNumRows()
                && cols >= 0 && cols < ConfigData.getInstance().getNumCols();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
