package flappyBirdPack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuPanel extends JPanel {
    private static final long serialVersionVID = 1L;
    private BufferedImage image = null;
    public boolean startingPt = false;

    public MenuPanel(){
        LoadImage();
        //Handle MouseClick event
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                startingPt = true;
            }
        });
    }

    private void LoadImage(){
        try {
            image = ImageIO.read(new File("C:/Users/K/IdeaProjects/Flappy Bird/Images/MenuPanel.png"));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image,0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
    }
}
