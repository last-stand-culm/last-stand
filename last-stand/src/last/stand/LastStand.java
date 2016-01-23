/*
in order to send to github you must go through these steps
*(if you added a class) then go to team and click add
*click commit then push
*click pull to get latest updated version of program 
 */
// we need to make collison for the mystery box using tile editor 
//sound wont work at home computer
//collison with walls glitches player gets stuck for a sec

package last.stand;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.*;

public class LastStand extends StateBasedGame {
    public static final int Menu=0;
    public static final int instructions=1;
    public static final int option=2;
    public static final int game=3;
    public static final int credits=4;
    public static final int scores=5;

    public LastStand(){
        super("LastStand");
        this.addState(new Menu(Menu));
        this.addState(new Instruction(instructions));
        this.addState(new Options(option));
        this.addState(new Game(game));
        this.addState(new Credits(credits));
        
    }
    public void initStatesList(GameContainer gc)throws SlickException{
        this.getState(Menu).init(gc,this);
        this.getState(instructions).init(gc,this);
        this.getState(option).init(gc, this);
        this.getState(game).init(gc, this);
        this.getState(credits).init(gc, this);
        this.enterState(Menu);
    }
    
   
    public static void main(String[] args) {
       try{
           AppGameContainer app=new AppGameContainer(new LastStand());
           app.setDisplayMode(1100,800,false);
           app.setTargetFrameRate(32);
           app.start();
       }
       catch(SlickException e){
           e.printStackTrace();
       }
    }
    
}
