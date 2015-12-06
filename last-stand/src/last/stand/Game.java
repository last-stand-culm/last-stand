package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;


public class Game extends BasicGameState {
    
    public static int score=0,round=0,kills=0,money=500,ammo;
    private int x=-2,y=-14,x2=17,y2=27;
    
    public static String weapon=" ";
    
    private TiledMap map;
    
    private Image heart;
    
    private boolean heart1=true,heart2=true,heart3=true;
            
    public Game(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    map=new TiledMap("res/map_game.tmx");
    heart=new Image("res/heart_pixel.png");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
    // rendering all images/strings to screen
    map.render(x*32, y*32); 
    g.drawString("Score: "+score,25,25);
    g.drawString("Round: "+round,125,25);
    g.drawString("Kills: "+kills,225,25);
    g.drawString("Money: "+money,355,25);
    g.drawString("Weapon: "+weapon, 625, 25);
    g.drawString("Ammo: "+ammo, 525, 25);
    if(heart1==true){heart.draw(850, 20);}
    if(heart2==true){heart.draw(900, 20);}
    if(heart3==true){heart.draw(950, 20);}
    g.fillRect(480, 416, 32, 32);
    
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
     int objectLayer = map.getLayerIndex("Tile Layer 1");
    
   
     
     
     //to move right
     if(gc.getInput().isKeyPressed(Input.KEY_D)){
         if(map.getTileId(x2+1,y2,objectLayer)==0){
            x--;
            x2++;
         }
     }
     //to move up
     if(gc.getInput().isKeyPressed(Input.KEY_W)){
         if(map.getTileId(x2,y2-1,objectLayer)==0){
            y++;
            y2--;
         }
     }
     //to move right
     if(gc.getInput().isKeyPressed(Input.KEY_A)){
         if(map.getTileId(x2-1,y2,objectLayer)==0){
            x++;
            x2--;
         }
     }
     //to move down
     if(gc.getInput().isKeyPressed(Input.KEY_S)){
         if(map.getTileId(x2,y2+1,objectLayer)==0){
            y--;
            y2++;
         }
     }
     
     
     }
     
      public int getID(){
        return 3;
    }
}
