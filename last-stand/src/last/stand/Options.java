package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Options extends BasicGameState {
    
    public boolean musicOn =true;
    public boolean easy=true;
    
    private float x,y;
    
    private Image optionPic;
    private Image exitButton;
    private Image exitButtonSelected;
    private Image musicButtonOnGreen;
    private Image musicButtonOnRed;
    private Image musicButtonOffGreen;
    private Image musicButtonOffRed;
    private Image easyRed;
    private Image easyGreen;
    private Image hardRed;
    private Image hardGreen;
    private Image scoreButton;
    private Image scoreButtonSelect;
    
    public Options(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
        //finding the location of all the images and placing them with their variables
        optionPic=new Image("res/option.Jpg");
        exitButton=new Image("res/exit_button.png");
        exitButtonSelected=new Image("res/exit_button_select.png");
        musicButtonOnGreen=new Image("res/music_button_on_green.png");
        musicButtonOnRed=new Image("res/music_button_on_red.png");
        musicButtonOffGreen=new Image("res/music_off_green.png");
        musicButtonOffRed=new Image("res/music_off_red.png");
        easyRed=new Image("res/difficullty_easy_red.png");
        easyGreen=new Image("res/difficullty_easy_green.png");
        hardRed=new Image("res/difficullty_hard_red.png");
        hardGreen=new Image("res/difficullty_hard_green.png");
        scoreButton=new Image("res/score_button.png");
        scoreButtonSelect=new Image("res/score_button_selected.png");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
        //rendering all the images and strings
        optionPic.draw();
        exitButton.draw(5,550);
        if(musicOn==true){musicButtonOnGreen.draw(250,55);}
        if(musicOn==true){musicButtonOffRed.draw(700,55);}
        if(musicOn==false){musicButtonOnRed.draw(250,55);}
        if(musicOn==false){musicButtonOffGreen.draw(700,55);}
        if(easy==true){easyGreen.draw(330,200);}
        if(easy==true){hardRed.draw(700,200);}
        if(easy==false){easyRed.draw(330,200);}
        if(easy==false){hardGreen.draw(700,200);}
        scoreButton.draw(500,400);
        if(x>=33 && y>=601 && x<=390 && y<=705){exitButtonSelected.draw(5,550);}
        if(x>=527 && y>=449 && x<=883 && y<=555){scoreButtonSelect.draw(500,400);}
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
          Input input=gc.getInput();
        x=input.getMouseX();
        y=input.getMouseY();
       
        //when mouse is over button off and button is clicked
       if(x>=726 && y>=104 && x<=1083 && y<=209){
            if(input.isMouseButtonDown(0) && musicOn==true){
                musicOn=false;
            }
       } 
       //when mouse is over button on and buton is clicked
       if(x>=275 && y>=103 && x<=633 && y<=210){
          if(input.isMouseButtonDown(0) && musicOn==false){
              musicOn=true;
          } 
       }
       //when mouse is over button easy and button is clicked
       if(x>=356 && y>=249 && x<=714 && y<=354){
         if(input.isMouseButtonDown(0) && easy==false){
             easy=true;
         }  
       } 
       //when mouse is over button hard and button is clicked
       if(x>=726 && y>=248 && x<=1083 && y<=354){
           if(input.isMouseButtonDown(0) && easy==true){
               easy=false;
           }
       }
       //when mouse is over exit button which brings you back to menu
       if(x>=33 && y>=601 && x<=390 && y<=705){
          if(input.isMouseButtonDown(0)){
               sbg.enterState(0);
           }
      }
        
        
     }
     
      public int getID(){
        return 2;
    }
}
