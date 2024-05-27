/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;
/**
 * Las bibliotecas que usa la clase Food
 */
import java.awt.Graphics;
import java.util.Random;

/**
 * La clase Food es una extension de la clase nodo, ya que tendran la misma
 * estructura, solo añadiremos los metodos necesarios
 * @author alu13114532
 */
public class Food extends Node {
/**
 * declaramos un Random que nos servira para que la food se genere en un lugar
 * random del tablero
 */
    private Random random;
/**
 * en el constructir del Food solo agregamos el random ademas del super que 
 * proviende de la clase Node
 * @param row
 * @param col 
 */
    public Food(int row, int col) {
        super(row, col);
        random = new Random();
    }
/**
 * El metodo generateFood nos sirve para poner una food en uuna casilla random
 * del board
 * @param snakeBody 
 */
    public void generateFood(SnakeBody snakeBody) {
        int numRows = Board.NUM_ROWS;
        int numCols = Board.NUM_COL;
        do {
            setRow(random.nextInt(numRows));
            setCol(random.nextInt(numCols));
        } while (snakeBody.containsNode(getRow(), getCol())); 
    }
/**
 * Es el metodo que dibuja la food dentro del board
 * @param g
 * @param squareWidth
 * @param squareHeight 
 */
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        g.setColor(ConfigData.RED);
        g.fillRect(getCol() * squareWidth, getRow() * squareHeight, squareWidth, squareHeight);
    }

}
