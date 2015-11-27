package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Instruction extends BasicGameState {
    
    private Image instructionPic;
    private Image playButtonInstruction;
    private Image playButtonSelectInstruction;
    private Image WKey;
    private Image AKey;
    private Image SKey;
    private Image DKey;
    private Image EKey;
    private Image ESCKey;
    private Image mouse;
    private Image zombie;
    private float x,y;
    
    public Instruction(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
     instructionPic=new Image("res/instructions.Jpg");
     playButtonInstruction=new Image ("res/play_button.png");
     playButtonSelectInstruction=new Image("res/play_button_selected.png");
     WKey=new Image("res/W_Key.fw.png");
     //AKey=new Image("res/");
    // SKey=new Image("res/");
    // DKey=new Image("res/"); 
    // EKey=new Image("res/");
     //ESCKey=new Image("res/");
    // mouse=new Image("res/");
   //  zombie=new Image("res/");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
     instructionPic.draw();   
     playButtonInstruction.draw(1,635);
     if(x>=25 && y>=682 && x<=386 && y<=792){playButtonSelectInstruction.draw(1,635);}
     WKey.draw(3,100);
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
          Input input=gc.getInput();
        x=input.getMouseX();
        y=input.getMouseY();
        
        // if mouse is over the play button and button is clicked
        if(x>=25 && y>=682 && x<=386 && y<=792){
            if(input.isMouseButtonDown(0)){
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
