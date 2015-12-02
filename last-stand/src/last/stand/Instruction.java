package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Instruction extends BasicGameState {
    
    private Image instructionPic;
    private Image playButtonInstruction;
    private Image playButtonSelectInstruction;
    
    private float x,y;
    
    private Sound playSound;
    
    public Instruction(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
     instructionPic=new Image("res/instructions_new.fw.png");
     playButtonInstruction=new Image ("res/play_button.png");
     playButtonSelectInstruction=new Image("res/play_button_selected.png");
     
     playSound=new Sound("res/menu_button_select_sound1.ogg");
     
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
     instructionPic.draw();   
     playButtonInstruction.draw(1,635);
     if(x>=25 && y>=682 && x<=386 && y<=792){playButtonSelectInstruction.draw(1,635);}
     g.drawString("W = move up \nA = move left\nS = move down\nD = move right\nE = select/pick up gun\nESC = bring up menu bar\nLeft mouse button = shoot",100,200);
     g.drawString("OBJECTIVE:\nyour objective is to survive as long as you can there is\nno win to the game you just do a little better everytime ", 100, 500);
     g.drawString("Zombie-\n\n\n\nYou-", 100, 350);
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
          Input input=gc.getInput();
        x=input.getMouseX();
        y=input.getMouseY();
        
        // if mouse is over the play button and button is clicked
        if(x>=25 && y>=682 && x<=386 && y<=792){
            if(input.isMouseButtonDown(0)){
                playSound.playAt(0,0,0);
                sbg.enterState(3);
            }
        }
        
        
        System.out.println(x);
        System.out.println(y);
     }
     
      public int getID(){
        return 1;
    }
}
