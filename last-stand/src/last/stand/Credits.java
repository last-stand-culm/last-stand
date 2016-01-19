package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState {
    
    private Image enterNameImage,credits,continue_Button,continue_Button_select;
    private Image exit_button, exit_button_select,continueImage;
    
    private boolean nameSelected=false,choose=false;
    
    
    private String name="";
    public Credits(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
        enterNameImage=new Image("res/enter_name.png");
       credits=new Image("res/credits.png");
       continue_Button=new Image("res/continue_button.png");
        continue_Button_select=new Image("res/continue_button_select.png");
        exit_button=new Image("res/exit_button.png");
        exit_button_select=new Image("res/exit_button_select.png");
        continueImage=new Image("res/continue_Image.png");
        
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
        if(nameSelected==false){
            enterNameImage.draw();
            
                    
            
        }
        
        if(nameSelected==true){
           credits.draw();
           choose=true;
        }
        
        if(choose==true){
           continueImage.draw();
           continue_Button.draw(60,400);
           exit_button.draw(600,400);
        }
    }
    
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
         
     }
    
    public int getID(){
        return 4;
    }
}
