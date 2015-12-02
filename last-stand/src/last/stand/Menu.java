package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
    
    private float x,y;
    
    private Image menuPic;
    private Image playButton;
    private Image optionButton;
    private Image playButtonSelect;
    private Image optionButtonSelect;
    
    private Sound sound;
    
    public Menu (int state) {  
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{  
        menuPic=new Image("res/main-menu.Jpg");
        playButton=new Image("res/play_button.png");  
        optionButton=new Image("res/option_button1.png");  
        playButtonSelect=new Image("res/play_button_selected.png");  
        optionButtonSelect=new Image("res/option_button_selected1.png"); 
        
        sound = new Sound("res/menu_button_select_sound1.ogg");
        
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException { 
        menuPic.draw();
        playButton.draw(20,150);
        optionButton.draw(20,350);
        if(x>=44 && y>=198 && x<=406 && y<=306 ){
        playButtonSelect.draw(20,150);
        }
        if(x>=45 && y>=398 && x<=406 && y<=508){
        optionButtonSelect.draw(20,350);
        }
        
    }
    
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{  
        Input input=gc.getInput();
        x=input.getMouseX();
        y=input.getMouseY();
        
        if(x>=44 && y>=198 && x<=406 && y<=306 ){
            if(input.isMouseButtonDown(0)){
                sound.playAt(-1, 0, 0);
                sbg.enterState(1);
            }
        }
        if(x>=45 && y>=398 && x<=406 && y<=508){
          if(input.isMouseButtonDown(0)){
              sound.playAt(-3,0,0);
              sbg.enterState(2);
          }  
        }
        
        System.out.println(x);
        System.out.println(y);
    }
    public int getID(){
        return 0;
    }
   
    
    
    
            
}
