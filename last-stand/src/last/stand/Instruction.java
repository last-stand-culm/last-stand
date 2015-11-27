package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Instruction extends BasicGameState {
    
    private Image instructionPic;
    private float x,y;
    
    public Instruction(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
     instructionPic=new Image("res/instructions.Jpg");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
     instructionPic.draw();    
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
          Input input=gc.getInput();
        x=input.getMouseX();
        y=input.getMouseY();
        
        if(x>=44 && y>=198 && x<=406 && y<=306 ){
            if(input.isMouseButtonDown(0)){
                sbg.enterState(1);
            }
        }
        if(x>=45 && y>=398 && x<=406 && y<=508){
          if(input.isMouseButtonDown(0)){
              sbg.enterState(2);
          }  
        }
        
        System.out.println(x);
        System.out.println(y);
     }
     
      public int getID(){
        return 1;
    }
}
