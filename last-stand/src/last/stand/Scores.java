package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.io.*;
public class Scores extends BasicGameState{
    private Image background;
    private int time;
    
    public Scores(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
        background=new Image("res/score_screen.png");
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
        g.drawString("Tester                                        Tester",100,100);
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
        
    }
    public int getID(){
        return 5;
    }
}