/*
in order to send to github you must go through these steps
*(if you added a class) then go to team and click add
*click commit then push
*click pull to get latest updated version of program 
 */
package last.stand;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class LastStand {

   
    public static void main(String[] args) {
       try{
           AppGameContainer app=new AppGameContainer(new Menu());
           app.setDisplayMode(800,600,false);
           app.start();
       }
       catch(SlickException e){
           e.printStackTrace();
       }
    }
    
}
