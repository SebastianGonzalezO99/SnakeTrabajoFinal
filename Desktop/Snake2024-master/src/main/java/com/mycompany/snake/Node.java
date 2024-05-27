/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

/**
 * Esta es la clase de nodos que con lo que vamos a construir tanto la snake 
 * como los dos tipos foods
 * @author alu13114532
 */
public class Node {
/**
 * Es el contructor de la clase nodo, donde le damos la fila y columna
 * @param row
 * @param col 
 */
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
/**
 * el getter del row de un nodo
 * @return 
 */
    public int getRow() {
        return row;
    }
/**
 * el setter de un row para un nodo
 * @param row 
 */
    public void setRow(int row) {
        this.row = row;
    }
/**
 * getter del col de un nodo
 * @return 
 */
    public int getCol() {
        return col;
    }
/** 
 * setter del col de un nodo
 * @param col 
 */
    public void setCol(int col) {
        this.col = col;
    }
    /**
     * las variables de la clase nodo
     */
    private int row;
    private int col;

}
