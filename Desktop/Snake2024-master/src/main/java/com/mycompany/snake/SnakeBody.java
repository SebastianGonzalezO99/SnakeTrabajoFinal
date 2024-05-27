/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;
/**
 * Las bibliotecas que usa la clase snakeBody
 */
import static com.mycompany.snake.Direction.UP;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;


/**
 * Es la clase que genera todo el cuerpo de la snake dentro del board
 * @author alu13114532
 */
public class SnakeBody {
/**
 * estas son las 3 variables que tiene la clase, el cuerpo de la snake esta
 * conformado por una lista de nodos, la direccion que sera el sentido
 * en que se movera la snake y por ultimo un nodesToGrow que seran los nodos
 * que se añaden a la lista cuando se come una food o special food
 */
    private List<Node> body;
    private Direction direction;
    private int nodesToGrow;
/**
 * Es el constructor del cuerpo de la snake, donde añadimos nodos al cuerpo, 
 * adicionalmente ponemos que la snake empiece su movimiento hacia la derecha
 * y empezamos el contador de nodos a crecer a 0
 */
    public SnakeBody() {
        body = new ArrayList<Node>();
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2 - 1));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2 - 2));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2 - 3));
        direction = Direction.RIGHT;
        nodesToGrow = 0;
    }
/**
 * Aqui pintamos y dibujamos el cuerpo del snake, si detectamos en el if
 * que el nodo que se genera es el primero, lo pintamos de morado, y el resto
 * los pintamos de verde
 * @param g
 * @param squareWidth
 * @param squareHeight 
 */
    public void paint(Graphics g, int squareWidth, int squareHeight) {
        boolean firstNode = true;
        Color color;
        for (Node node : body) {
            if (firstNode) {
                color = ConfigData.PURPLE;
                firstNode = false;
            } else {
                color = ConfigData.GREEN;
            }
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeight);
        }
    }
/**
 * el getter de la direccion 
 * @return 
 */
    public Direction getDirection() {
        return direction;
    }
/**
 * setter de la direccion
 * @param direction 
 */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
/**
 * el getter del head de la snake
 * @return 
 */
    public Node getHead() {
        return body.get(0);
    }
/**
 * es un metodo en donde la serpiente se mueve, para esto necesitamos siempre 
 * saber donde esta la cabeza de la snake y en caso tal de que se pueda mover, 
 * la serpiente se mueve, el principio de como se mueve la sanake es que siempre 
 * añadimos un nodo adelante y quitamos un nodo al final, para hacer la ilusion 
 * optica de que se mueve
 * @return 
 */
    public boolean move() {
        Node head = getHead();
        int row = head.getRow();
        int col = head.getCol();
        switch (direction) {
            case UP:
                if (canMove(row - 1, col)) {
                    body.add(0, new Node(row - 1, col));
                } else {
                    return false;
                }
                break;
            case DOWN:
                if (canMove(row + 1, col)) {
                    body.add(0, new Node(row + 1, col));
                } else {
                    return false;
                }
                break;
            case LEFT:
                if (canMove(row, col - 1)) {
                    body.add(0, new Node(row, col - 1));
                } else {
                    return false;
                }
                break;
            case RIGHT:
                if (canMove(row, col + 1)) {
                    body.add(0, new Node(row, col + 1));
                } else {
                    return false;
                }
                break;
        }
        if (nodesToGrow == 0) {
            body.remove(body.size() - 1);
        } else {
            nodesToGrow--;
        }
        return true;
    }
/**
 * Es el metodo donde revisa que antes de hacer cualquier movimiento, no haya 
 * ningun obstaculo que se interponga en su movimiento ya sea la pared o el
 * propio cuerpo de la snake
 * @param row
 * @param col
 * @return 
 */
    private boolean canMove(int row, int col) {
        if (row < 0 || row >= Board.NUM_ROWS || col < 0 || col >= Board.NUM_COL) {
            return false;
        }

        if (snakeHitsMatrix(row, col)) {
            return false;
        }

        return true;
    }
/**
 * es el metodo donde revisamos si la cabeza de la snake golpea la trix, asi 
 * evitamos que se salga del tablero
 * @param row
 * @param col
 * @return 
 */
    public boolean snakeHitsMatrix(int row, int col) {
        for (Node nodes : body) {
            if (row == nodes.getRow() && col == nodes.getCol()) {
                return true;
            }
        }
        return false;
    }
/**
 * El metodo chequea cuando una comida ha sido comida, ya sea secial food o no
 * en cada una esta la instruccion de que el cuerpo de la snake crezca cuando
 * ocurra esto
 * @param food
 * @return 
 */
    public boolean checkFoodEaten(Food food) {
        Node head = getHead();
        if (head.getRow() == food.getRow() && head.getCol() == food.getCol()) {
            nodesToGrow++;
            return true;
        }
        return false;
    }

    public boolean checkSpecialFoodEaten(SpecialFood food) {
        Node head = getHead();
        if (head.getRow() == food.getRow() && head.getCol() == food.getCol()) {
            nodesToGrow = nodesToGrow + 3;
            return true;
        }
        return false;
    }
    
    public boolean containsNode(int row, int col) {
        for (Node node : body) {
            if (node.getRow() == row && node.getCol() == col) {
                return true;
            }
        }
        return false;
    }

}
