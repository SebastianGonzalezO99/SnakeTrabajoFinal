/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;
/**
 * estas son las bibliotecas que usa la clase snake
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * Esta clase es la que usamos para poder dibujar y colorear mas facil los
 * cuadrados que seran los nodos de la snake y los dos tipos de food, ademas
 * de hacerlos del tamaño que necesitamos para que todo se vea bien
 * @author alu13114532
 */
public class Util {
    
    public static void drawSquare(Graphics g, int row, int col,
            Color color, int squareWidth, int squareHeight) {
        int x = col * squareWidth;
        int y = row * squareHeight;
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth - 2,
                squareHeight - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight - 1, x, y);
        g.drawLine(x, y, x + squareWidth - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight - 1,
                x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth - 1,
                y + squareHeight - 1,
                x + squareWidth - 1, y + 1);
    }
    
}
