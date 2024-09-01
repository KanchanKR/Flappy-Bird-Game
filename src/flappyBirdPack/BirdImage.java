package flappyBirdPack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BirdImage {
    private BufferedImage image = null;
    private static int birdDiameter = 60;
    public static int x = (GamePanel.WIDTH/2)-birdDiameter/2;   //300-2-=280
    public static int y = (GamePanel.HEIGHT/2);
    private static int speed = 2;
    private int acceleration = 1;

    public BirdImage(){
        LoadImage();
    }

    public void LoadImage(){
        try {
            image = ImageIO.read(new File("C:/Users/K/IdeaProjects/Flappy Bird/Images/Bird.png"));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void drawBird(Graphics g){
        g.drawImage(image,x,y,null);
    }

    public void birdMovement(){
        if(y>=0 && y<=GamePanel.HEIGHT){
            speed+=acceleration;//3,4,5
            y+=speed;  //400+3,400+3+4,400+3+4+5
        }else {
            boolean option = GamePanel.popUpMessage();
            if (option==true){
                try {
                    Thread.sleep(500);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                reset();
            }else {
                JFrame frame = MainBird.getWindow();
                frame.dispose();
                MainBird.timer.stop();
            }

        }
    }

    public void goUpwards(){
        speed = -17;
    }

    public static void reset(){
        speed = 2;
        y = GamePanel.HEIGHT/2;
        GamePanel.gameOver = true;
        GamePanel.score = 0;
    }

    public static Rectangle getBirdRect(){
        Rectangle birdRect = new Rectangle(x,y,birdDiameter,44);
        return birdRect;
    }
}
