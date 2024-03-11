/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweepersalxixilla;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author alu10654799
 */
public class MineButton extends JButton {

    public static final int BUTTON_SIZE = 30;

    public static final int LEFT_BUTTON = 1;
    public static final int RIGHT_BUTTON = 3;

    private int row;
    private int col;
    private ButtonState state;
    private Image flagImage = null;
    private Image questionImage = null;

    private static Icon iconButton = null;
    private static Icon iconButtonPressed = null;
    private static MouseAdapter mouseAdapter = null;


    public MineButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        state = ButtonState.CLOSE;
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setBorderPainted(false);
        if (mouseAdapter == null) {
            mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == LEFT_BUTTON) {
                        MineButton b = (MineButton) e.getComponent();
                        b.setIcon(iconButtonPressed);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.getButton() == LEFT_BUTTON) {
                        MineButton b = (MineButton) e.getComponent();
                        b.setIcon(iconButton);
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    MineButton b = (MineButton) e.getComponent();
                    if (e.getButton() == RIGHT_BUTTON) {
                        b.changeState();
                        System.out.println("RIGHT");
                    } 
                }
            };
        }
        setIcon();
        addMouseListener(mouseAdapter);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private void setIcon() {
        if (iconButton == null) {
            Image image = new ImageIcon(getClass().getResource("/images/boton.jpg")).getImage();
            Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH);
            iconButton = new ImageIcon(newimg);
        }
        if (iconButtonPressed == null) {
            Image image = new ImageIcon(getClass().getResource("/images/boton_pressed.jpg")).getImage();
            Image newimg = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH);
            iconButtonPressed = new ImageIcon(newimg);
        }
        setIcon(iconButton);
    }

    public void changeState() {
        if (state == ButtonState.CLOSE) {
            state = ButtonState.FLAG;
        } else if (state == ButtonState.FLAG) {
            state = ButtonState.QUESTION;
        } else if (state == ButtonState.QUESTION) {
            state = ButtonState.CLOSE;
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = getImageForState();
        g.drawImage(image, 0, 0, null);
    }

    public void setState(ButtonState state) {
        this.state = state;
        repaint();
    }
    
    public ButtonState getState() {
        return state;
    }
    
    public void open() {
        state = ButtonState.OPEN;
        setVisible(false);
    }

    private Image getImageForState() {
        if (state == ButtonState.FLAG) {
            if (flagImage == null) {
                Image image = new ImageIcon(getClass().getResource("/images/flag.png")).getImage();
                flagImage = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH);
            }
            return flagImage;
        } else if (state == ButtonState.QUESTION) {
            if (questionImage == null) {
                Image image = new ImageIcon(getClass().getResource("/images/question.png")).getImage();
                questionImage = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, java.awt.Image.SCALE_SMOOTH);
            }
            return questionImage;
        } else {
            return null;
        }
    }

}
