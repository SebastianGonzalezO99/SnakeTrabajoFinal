/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;
/**
 * esta es la biblioteca que usa la clase superfood
 */
import java.awt.Graphics;

/**
 * Esta clase es una extension de la clase food, solo debemos agregar los 
 * metodos adicionales
 * @author alu13114532
 */
public class SpecialFood extends Food{
    /**
     * esta es la unica variable que usa la clase superfood, nos sirve para saber
     * si la special food esta activa o no
     */
     private boolean active;
    /**
     * este es el constructor de la clase super food
     * @param row
     * @param col 
     */
    public SpecialFood(int row, int col) {
        super(row, col);
    }
    /**
     * este metodo nos ayuda para saber si la special food esta activa o no 
     * @return 
     */
    public boolean isActive() {
        return active;
    }
/**
 * es el stter de la special food para poner en activo
 * @param active 
 */
    public void setActive(boolean active) {
        this.active = active;
    }
/**
 * este es el metodo que dibuja y pinta la special food
 * @param g
 * @param squareWidth
 * @param squareHeight 
 */
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        g.setColor(ConfigData.BLUE);
        g.fillRect(getCol() * squareWidth, getRow() * squareHeight, squareWidth, squareHeight);
    }
    
}
