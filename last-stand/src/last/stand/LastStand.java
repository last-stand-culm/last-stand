/*
in order to send to github you must go through these steps
*(if you added a class) then go to team and click add
*click commit then push
*click pull to get latest updated version of program 
 */
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

    public LastStand(){
        super("LastStand");
        this.addState(new Menu(Menu));
        this.addState(new Instruction(instructions));
        this.addState(new Options(option));
        this.addState(new Game(game));
        
    }
    public void initStatesList(GameContainer gc)throws SlickException{
        this.getState(Menu).init(gc,this);
        this.getState(instructions).init(gc,this);
        this.getState(option).init(gc, this);
        this.getState(game).init(gc, this);
        this.enterState(Menu);
    }
    
   
    public static void main(String[] args) {
       try{
           AppGameContainer app=new AppGameContainer(new LastStand());
           app.setDisplayMode(1100,800,false);
           app.start();
       }
       catch(SlickException e){
           e.printStackTrace();
       }
    }
    
}
//Campbell's Comment