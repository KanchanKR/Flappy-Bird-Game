package flappyBirdPack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePanel extends JPanel {

    public static boolean starting = false;
    public static boolean gameOver = false;
    public static int proceed = -1;
    public static int score = 0;
    public static final int WIDTH = 600 ;
    public static final int HEIGHT = 800;
    private static final long serialVersionVID = 1L;
    private int XCor = 0;
    private BufferedImage image = null;

    BirdImage birdImage = new BirdImage();
    WallImage wallImage = new WallImage(GamePanel.WIDTH);
    WallImage wallImage2 = new WallImage(GamePanel.WIDTH+(GamePanel.WIDTH)/2);
    public GamePanel(){
        LoadImage();
        // Mouse pressed event
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                birdImage.goUpwards();
            }
        });
    }

    private void LoadImage(){
        try {
            image = ImageIO.read(new File("C:/Users/K/IdeaProjects/Flappy Bird/Images/BackGround.png"));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image,XCor,0,null);
        g.drawImage(image,XCor+2400,0,null);
        birdImage.drawBird(g);
        wallImage.drawWall(g);
        wallImage2.drawWall(g);
        g.setFont(new Font("Serif",Font.BOLD,100));
        g.setColor(new Color(255,255,255));
        g.drawString(""+score,GamePanel.WIDTH/2,100);

        if(starting == true){
            g.setFont(new Font("Times New Roman",Font.BOLD,150));
            g.drawString(Integer.toString(proceed),WIDTH/2,250);
        }

    }

    public void MoveMethod(){
        birdImage.birdMovement();
        wallImage.wallMovement();
        wallImage2.wallMovement();

        if(gameOver==true){
            wallImage.X = GamePanel.WIDTH;
            wallImage2.X = GamePanel.WIDTH+(GamePanel.WIDTH)/2;
            gameOver = false;
        }
        XCor += WallImage.speed;
        if(XCor==-2400){
            XCor = 0;
        }
        if(wallImage.X==BirdImage.x || wallImage2.X==BirdImage.x){
            score+=1;
        }

    }

    public static boolean popUpMessage(){
        int result = JOptionPane.showConfirmDialog(null,"Game Over, Your Score is: "+score+"\n Do you Want to restart ?","Game Over",JOptionPane.YES_NO_OPTION);
        if (result==JOptionPane.YES_NO_OPTION){
            return true;
        }else
            return false;
    }


}
