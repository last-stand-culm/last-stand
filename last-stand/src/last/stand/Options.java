package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Options extends BasicGameState {
       
    private float x,y;
    
    public Options(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
         
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
        return 2;
    }
}
