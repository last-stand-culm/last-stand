package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
    
    private Image menuPic;
    
    public Menu (int state) {  
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{  
        menuPic=new Image("res/main-menu.Jpg");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException { 
        menuPic.draw();
    }
    
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{  
    }
    public int getID(){
        return 0;
    }
   
    
    
    
            
}
