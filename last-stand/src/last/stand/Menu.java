package last.stand;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
    
   
    
    private float x5,y5;
    
    private Image menuPic;
    private Image playButton;
    private Image optionButton;
    private Image playButtonSelect;
    private Image optionButtonSelect;
    
    private Sound sound;
    
    public Menu (int state) {  
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{  
//linking all image variables to the images        
        menuPic=new Image("res/main-menu.Jpg");
        playButton=new Image("res/play_button.png");  
        optionButton=new Image("res/option_button1.png");  
        playButtonSelect=new Image("res/play_button_selected.png");  
        optionButtonSelect=new Image("res/option_button_selected1.png"); 
//------------------------------------------------------------------------------
//sound variable linking to the audio
        sound = new Sound("res/menu_button_select_sound1.ogg");
//------------------------------------------------------------------------------       
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException { 
        //rendering all images and strings to the screen
        menuPic.draw();
        playButton.draw(20,150);
        optionButton.draw(20,350);
//if mouse hovers over play or option button it highlights it        
        if(x5>=44 && y5>=198 && x5<=406 && y5<=306 ){
        playButtonSelect.draw(20,150);
        }
        if(x5>=45 && y5>=398 && x5<=406 && y5<=508){
        optionButtonSelect.draw(20,350);
        }
//------------------------------------------------------------------------------        
    }
    
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{ 
//resets all game variables so they dont repeat       
        if(Credits.reset==true){
          Game.score=0;
          Game.round=1;
          Game.kills=0;
          Game.money=750;
          Game.zedHP=3;
          Game.x=-2;
          Game.y=-14;
          Game.x2=17;
          Game.y2=27;
          Game.bCount=0;
          Game.weaponselc=0;
          Game.nextX=0;
          Game.nextY=0;
          Game.health=3;
          Game.oHeat=0;
          Game.shotgunX=0;
          Game.shotgunY=0;
          Game.shotgunPos=0;
          Game.weapon="Pistol ";
          Game.door1Open=false;
          Game.door2Open=false;
          Game.door3Open=false;
          Game.door4Open=false;
          Game.door5Open=false;
          Game.door6Open=false;
          Game.buyDoor=false;
          Game.buyChest=false;
          Game.start=false;
          Game.boxOpen2=false;
          Game.m4a1=false;
          Game.minigun=false;
          Game.nova=false;
          Game.p250=true;
          Game.boxStart=false;
          Game.shooting=false;
          Game.broken = false;
          Game.sBroken = false;
          Game.oHT =  false;
          Game.shotgunShot=false;
    
          Game.zombieX.clear();
          Game.zombieY.clear();
          Game.moveC.clear();
          Game.pos.clear();
          Game.zombieHP.clear();
          Game.pistolX.clear();
          Game.pistolY.clear();
          Game. pistolPos.clear();
        
          Game.toMove.clear();
      
          Credits.reset=false;
        }
 //-----------------------------------------------------------------------------     
 //if mouse is over play and left mouse button is clicked
        Input input=gc.getInput();
        x5=input.getMouseX();
        y5=input.getMouseY();
        
        if(x5>=44 && y5>=198 && x5<=406 && y5<=306 ){
            if(input.isMouseButtonDown(0)){
                sound.playAt(-1, 0, 0);
                sbg.enterState(1);
            }
        }
//------------------------------------------------------------------------------        
//if mouse is over options and left mouse button is clicked then enter option state
        if(x5>=45 && y5>=398 && x5<=406 && y5<=508){
          if(input.isMouseButtonDown(0)){
              sound.playAt(-3,0,0);
              sbg.enterState(2);
          }  
        }
//------------------------------------------------------------------------------        
        
    }
    public int getID(){
        return 0;
    }
   
    
    
    
            
}
