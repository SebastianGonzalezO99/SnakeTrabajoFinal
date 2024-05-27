/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snake;
//Todas las librerias que impoprtamos para la clase Board
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *El Board se extiende de un JPanel para que herede tambien los metodos del
 * mismo, adicional instanciamos todos los elementos y contantes que estaran
 * interactuadno con el Board
 * @author alu13114532
 */
public class Board extends javax.swing.JPanel {

    public static final int NUM_ROWS = 30;
    public static final int NUM_COL = 30;
    private Timer timer;
    private MyKeyAdapter keyAdapter;
    private SnakeBody snake;
    private Food food;
    private SpecialFood specialFood;
    private boolean turning;
    private int specialFood_timer = 0;
    private ScoreBoard scoreBoard;

    /**
     * este es el contructor de la clase Board, instanciamos la snake
     * las dos clases de food las instanciamos por fuera del board para que 
     * no se vean, por ultimo le damos las dimensiones a la ventana
     */
    public Board() {
        initComponents();
        snake = new SnakeBody();
        food = new Food(-1,-1);
        food.generateFood(snake);
        specialFood = new SpecialFood(-1,-1);
        specialFood.generateFood(snake);
        setFocusable(true);
        setPreferredSize(new Dimension(NUM_COL * 15, NUM_ROWS * 15));
    }
/**
 * Instanciamos el scoreboard para que sea visible en el board y se vaya
 * actualizando respecto a las food que vas comiendo
 * @param scoreBoard 
 */
    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }
    /**
     * Aqui le damos las ordenes que realizaran las 4 teclas de las flechas que
     * son las que le daran movimiento a la snake, adicional usamos la variable
     * turning para evitar un error que si hundimos dos teclas rapidamente para
     * mover la snake, se buggeaba
     */
    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT && !turning) {
                        snake.setDirection(Direction.LEFT);
                        turning = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT && !turning) {
                        snake.setDirection(Direction.RIGHT);
                        turning = true;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN && !turning) {
                        snake.setDirection(Direction.UP);
                        turning = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP && !turning) {
                        snake.setDirection(Direction.DOWN);
                        turning = true;
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }
/**
 * Aqui es donde inicializamos la sanke, el timer de la specialfood, los key
 * ademas de inicializar el timer del juego, en caso tal de que sea una partida
 * nueva, revisa que un timer viejo lo detenga e inicie uno nuevo desde 0
 */
    public void initGame() {
        specialFood_timer = 0;
        snake = new SnakeBody();
        keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        int deltaTime = ConfigData.getInstance().getDeltaTime();
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });
        timer.start();
    }
    /**
     * sirve para deter del timer del juego
     */
    public void stop() {
        timer.stop();
    }
/**
 * Es la velocidad con la que se movera el snake dentro del juego, aqui se 
 * emplean los metodos del move y checkFoodEaten, dentro de este revisa si una
 * food esta comida para generar otra y para aumentar el puntaje del score
 * el checkSpecialFoodEaten funcina de una manera similar, solo que cuando es 
 * comido, reseteamos el timer de la especialfood. en caso tal de que la snake
 * no pueda moverse, entrara el metodo de gameover()
 */
    public void tick() {
        if (snake.move()) {
            if (snake.checkFoodEaten(food)) {
                scoreBoard.incrementFood();
                food.generateFood(snake);
            }
            if (snake.checkSpecialFoodEaten(specialFood)) {
                    scoreBoard.incrementSpecialFood();
                    specialFood.generateFood(snake);
                    specialFood_timer = 0;
            }
            repaint();
            turning = false;
        } else {
            gameOver();
        }
        specialFood_timer++;
    }
/**
 * sirve para dibujar todo el board en el que jugaremos
 * @param g 
 */
    public void paintMatrix(Graphics g) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COL; col++) {
                if ((row + col) % 2 == 0) {
                    Util.drawSquare(g, row, col, ConfigData.BG1, NUM_ROWS / 2, NUM_COL / 2);
                } else {
                    Util.drawSquare(g, row, col, ConfigData.BG2, NUM_ROWS / 2, NUM_COL / 2);
                }
            }
        }

    }
/**
 * aqui es donde dibujamos todos los compotentes del snake, la food 
 * y la especial food, el metodo revisa que si la snake y las foods son null,
 * las dibuja, en el caso de la special food, como tiene que aparecer y
 * desaparecer del board, usamos un timer adicional para la especial food
 * para dibujar la special food, ademas de ser null, tien que llegar a 30 
 * en su contador para poder dibujarla, luego cuando ese mismo contador
 * llegue a 70 y no ha sido comida, la eliminamos del board, y la volvemos a 
 * instancear para que pueda volver a crear, ademas de resetear el timer
 * de la especial food
 * @param g 
 */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBorder(g);
        paintMatrix(g);
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (food != null) {
            food.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (specialFood != null && specialFood_timer >= 30) {
            specialFood.paint(g, getSquareWidth(), getSquareHeight());
            if(specialFood_timer == 70) {
              specialFood = null;
              specialFood = new SpecialFood(-1,-1);
              specialFood.generateFood(snake);
              specialFood_timer = 0;
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }
/**
 * getter del ancho del un cuadrado
 * @return 
 */
    private int getSquareWidth() {
        return getWidth() / NUM_COL;
    }
/**
 * getter de la altura de un cuadrado
 * @return 
 */
    private int getSquareHeight() {
        return getHeight() / NUM_ROWS;
    }
/**
 * el metodo detiene el timer del juego ademas de mostrar un panel donde te dice
 * que has perdido
 */
    public void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "GAME OVER!!!", "Error", JOptionPane.ERROR_MESSAGE);
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
