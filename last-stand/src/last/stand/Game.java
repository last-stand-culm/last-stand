package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;


public class Game extends BasicGameState {
    
    public static int score=0,round=0,kills=0,money=500,ammo;
    public static String weapon=" ";
    private TiledMap map;
    
    private Music music;
            
    public Game(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    music= new  Music("res/Zombie__Horror_Music_Mix.ogg");
    music.setVolume(0.5f);
    map=new TiledMap("res/map_game.tmx");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
    g.drawString("Score: "+score,25,25);
    g.drawString("Round: "+round,225,25);
    g.drawString("Kills: "+kills,425,25);
    g.drawString("Money: "+money,625,25);
    g.drawString("Weapon: "+weapon, 825, 25);
    g.drawString("Ammo: "+ammo, 1000, 25);
    map.render(0, 0);
    
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
     music.play();  
     int objectLayers = map.getLayerIndex("Tile Layer 1");
     }
     
      public int getID(){
        return 3;
    }
}
