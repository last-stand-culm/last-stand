package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Instruction extends BasicGameState {
    
    private Image instructionPic;
    private Image playButtonInstruction;
    private Image playButtonSelectInstruction;
    private Image zombie_intsruction;
    private Image player_instruction;
    
    private float x,y;
    
    private Sound playSound;
    
    public Instruction(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
//linking image variables to their Image        
     instructionPic=new Image("res/instructions_new.fw.png");
     playButtonInstruction=new Image ("res/play_button.png");
     playButtonSelectInstruction=new Image("res/play_button_selected.png");
     zombie_intsruction=new Image("res/zombie_instruction1.png");
     player_instruction=new Image("res/player_instruction1.fw.png");
//------------------------------------------------------------------------------
//linking sound variable to its audio
     playSound=new Sound("res/menu_button_select_sound1.ogg");
//------------------------------------------------------------------------------
     
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
// rendering all images and strings to the screen
     instructionPic.draw();   
     playButtonInstruction.draw(1,635);
     if(x>=25 && y>=682 && x<=386 && y<=792){playButtonSelectInstruction.draw(1,635);}
     g.drawString("W = move up \nA = move left\nS = move down\nD = move right\nE = select/pick up gun\nESC = bring up menu bar\nLeft mouse button = shoot",100,100);
     g.drawString("OBJECTIVE:\nyour objective is to survive as long as you can there is\nno win to the game you just do a little better everytime. Your score \nwill be entered into a scoreboard which you can see in the option menu ", 100, 500);
     g.drawString("Zombie-\n\n\n\n\nYou-", 100, 300);
     zombie_intsruction.draw(170,290);
     player_instruction.draw(170,400);
//------------------------------------------------------------------------------     
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
//------------------------------------------------------------------------------        
        
        
     }
     
      public int getID(){
        return 1;
    }
}
