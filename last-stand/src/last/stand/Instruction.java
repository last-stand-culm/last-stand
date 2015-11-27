package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Instruction extends BasicGameState {
    
    private Image instructionPic; 
    
    public Instruction(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
     instructionPic=new Image("res/instructions.Jpg");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
     instructionPic.draw();    
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
         
     }
     
      public int getID(){
        return 1;
    }
}
