/*
in order to send to github you must go through these steps
*(if you added a class) then go to team and click add
*click commit then push
*click pull to get latest updated version of program 
 */
package last.stand;
import org.newdawn.slick.AppGameContainer;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.BasicGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.*;

public class LastStand extends StateBasedGame {
    public static final int Menu=0;

    public LastStand(){
        super("LastStand");
        this.addState(new Menu(Menu));
    }
    public void initStatesList(GameContainer gc)throws SlickException{
        this.getState(Menu).init(gc,this);
        this.enterState(Menu);
    }
    
   
    public static void main(String[] args) {
       try{
           AppGameContainer app=new AppGameContainer(new LastStand());
           app.setDisplayMode(800,600,false);
           app.start();
       }
       catch(SlickException e){
           e.printStackTrace();
       }
    }
    
}
