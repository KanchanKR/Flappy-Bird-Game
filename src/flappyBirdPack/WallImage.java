package flappyBirdPack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class WallImage {
    private BufferedImage image = null;
    private Random random = new Random();
    public int X ;
    public int Y = random.nextInt(GamePanel.HEIGHT-400)+200;    //MAX 600 , MIN 200
    private int widthOfWall = 112;
    private int height = GamePanel.HEIGHT-Y;
    private int gap = 200;
    public static int speed = -6;

    public WallImage(int X){
        this.X = X;
        LoadImage();
    }

    private void LoadImage(){
        try {
            image = ImageIO.read(new File("C:/Users/K/IdeaProjects/Flappy Bird/Images/pipe.png"));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void drawWall(Graphics g){
        g.drawImage(image,X,Y,null);    //bottom wall
        g.drawImage(image,X,(-GamePanel.HEIGHT)+(Y-gap),null);  //upper wall
    }


    public void wallMovement(){
        X += speed;
        if(X<=-widthOfWall){
            X = GamePanel.WIDTH;
            Y = random.nextInt(GamePanel.HEIGHT-400)+200;
            height = GamePanel.HEIGHT-Y;
        }
        Rectangle lowRect = new Rectangle(X,Y,widthOfWall,height);
        Rectangle upperRect = new Rectangle(X,0,widthOfWall,GamePanel.HEIGHT-(height+gap));
        if(lowRect.intersects(BirdImage.getBirdRect()) || upperRect.intersects(BirdImage.getBirdRect())){
            boolean option = GamePanel.popUpMessage();
            if (option==true){
                try {
                    Thread.sleep(500);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                BirdImage.reset();
                wallReset();
            }else {
                JFrame frame = MainBird.getWindow();
                frame.dispose();
                MainBird.timer.stop();
            }
        }
    }

    public void wallReset(){
        Y = random.nextInt(GamePanel.HEIGHT-400)+200;
        height = GamePanel.HEIGHT-Y;
        GamePanel.gameOver = true;
    }
}
