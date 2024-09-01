package flappyBirdPack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBird {

    //Window
    //1st Panel
    //2nd Panel

    private static JFrame window;
    public static Timer timer,timer2;
    private int proceed = 4;
    private MainBird(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(GamePanel.WIDTH,GamePanel.HEIGHT);
        window.setLocationRelativeTo(null);
        window.setTitle("Flappy Bird");
        window.setResizable(false);
        //window.setVisible(true);
    }

    public void rendering(){

        MenuPanel menuPanel = new MenuPanel();
        GamePanel gamePanel = new GamePanel();
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.repaint();
                gamePanel.MoveMethod();
            }
        });

        window.add(menuPanel);
        window.setVisible(true);

        while (menuPanel.startingPt == false){
            try {
                Thread.sleep(10);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

       window.remove(menuPanel);
        window.add(gamePanel);
        gamePanel.setVisible(true);
        window.revalidate();
        //timer.start();      //start the timer
        timer2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proceed--;
                GamePanel.proceed =proceed;
                GamePanel.starting = true;
                gamePanel.repaint();
                if(proceed ==0){
                    timer2.stop();
                    timer.start();
                    GamePanel.starting = false;
                }
            }
        });

        timer2.start();
    }

    public static JFrame getWindow(){
        return window;
    }

    public static void main(String[] args){
        MainBird mainBird = new MainBird();
        mainBird.rendering();
    }
}
