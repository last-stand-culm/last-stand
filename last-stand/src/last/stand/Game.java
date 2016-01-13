package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;


public class Game extends BasicGameState {
    
    public static int score=0,round=0,kills=0,money=10000,ammo;
    private int x=-2,y=-14,x2=17,y2=27,x3=42,y3=41,bCount=0,weaponselc=0,nextX,nextY,health=3;
    
    private Animation zombieWalkingUp,zombieWalkingLeft,zombieWalkingRight,zombieWalkingDown;
    private Animation playerWalkingUp,playerWalkingLeft,playerWalkingRight,playerWalkingDown;
    private Animation playerWalkingUpShotgun,playerWalkingLeftShotgun,playerWalkingRightShotgun,playerWalkingDownShotgun;
    private Animation playerWalkingUpM4a1,playerWalkingLeftM4a1,playerWalkingRightM4a1,playerWalkingDownM4a1;
    private Animation playerShootingPistolUp,playerShootingPistolDown,playerShootingPistolLeft,playerShootingPistolRight;
    
    public static String weapon="Pistol ";
    
    private TiledMap map;
    
    private Image heart;
    private Image chestClosed;
    private Image doorUp,doorDown,doorRight,doorLeft;
    private Image box_Open2;
    private Image bulletup,bulletdown,bulletleft,bulletright;
    
    private boolean door1Open=false,door2Open=false,door3Open=false,door4Open=false,door5Open=false,door6Open=false;
    private boolean buyDoor=false,buyChest=false;
    private boolean start=false;
    private boolean boxOpen2=false;
    private boolean m4a1=false,minigun=false,nova=false,p250=true;
    private boolean boxStart=false;
    private boolean shooting=false;
    private boolean broken = false;
    
    
    private ArrayList<Integer> zombieX = new ArrayList();
    private ArrayList<Integer> zombieY = new ArrayList();
    private ArrayList<Integer> moveC = new ArrayList();
    private ArrayList<Integer> pos = new ArrayList();
    private ArrayList<Integer> zombieHP = new ArrayList();
    private ArrayList<Integer> pistolX = new ArrayList();
    private ArrayList<Integer> pistolY = new ArrayList();
    private ArrayList<Integer> pistolPos = new ArrayList();
    
    private ArrayList<Boolean> toMove = new ArrayList();
    
    private Shape zombieBox = new Rectangle(32,32,32,32);
    private Shape doorBox = new Rectangle(32,32,32,32);
    
    
    private int[][] zombieSpawn=new int[3][2];
    private int[] position=new int[3];
    
    private boolean[][] door = new boolean[100][100];
    
    
    private int time=0,bulletTime=0,test=0,cap=10,playerMovement=1;
    
    Random randomGenerator = new Random();
            
    public Game(int state){
        
    }
    //base code for all animation
    public Animation getAnimation ( Image i , int spritesX, int spritesY , int spriteWidth , int spriteHeight, int frames, int duration )
	{
		Animation a = new Animation(false);
		
		int c = 0;
		for( int y = 0 ; y < spritesY; y++)
		{
			for( int x = 0 ; x < spritesX; x++)
			{
				if( c < frames ) a.addFrame( i.getSubImage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight), duration);
				c++;
			}
		}
		
		return a;
	}
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    map=new TiledMap("res/map_game.tmx");
    heart=new Image("res/heart_pixel.png");
    chestClosed=new Image("res/mystery_box_closed.png");
    doorUp=new Image("res/door_for_game_up (2).png");
    doorDown=new Image("res/door_for_game_down (1).png");
    doorRight=new Image("res/door_for_game_right (1).png");
    doorLeft=new Image("res/door_for_game_left (1).png");
    box_Open2=new Image("res/box_open.png");
    Image zombieWalking = new Image("res/zombie_animation.png");
    Image zombieWalkingL=new Image("res/zombie_animation_left.png");
    Image zombieWalkingR = new Image("res/zombie_animation_right.png");
    Image zombieWalkingD=new Image("res/zombie_animation_down.png");
    Image playerWalkingU = new Image("res/player_walking_up.png");
    Image playerWalkingL=new Image("res/player_walking_left.png");
    Image playerWalkingR = new Image("res/player_walking_right.png");
    Image playerWalkingD=new Image("res/player_walking_down.png");
    Image playerWalkingUshotgun = new Image("res/player_walking_shotgun.png");
    Image playerWalkingLshotgun=new Image("res/player_walking_left_shotgun.png");
    Image playerWalkingRshotgun = new Image("res/player_walking_right_shotgun.png");
    Image playerWalkingDshotgun=new Image("res/player_walking_down_shotgun.png");
    Image playerWalkingUm4a1 = new Image("res/player_walking_m4a1.png");
    Image playerWalkingLm4a1=new Image("res/player_walking_left_m4a1.png");
    Image playerWalkingRm4a1 = new Image("res/player_walking_right_m4a1.png");
    Image playerWalkingDm4a1=new Image("res/player_walking_down_m4a1.png");
    bulletup=new Image("res/bullet_up.png");
    bulletdown=new Image("res/bullet_down.png");
    bulletleft=new Image("res/bullet.png");
    bulletright=new Image("res/bullet_right.png");
    Image playerPistolShootingUp = new Image ("res/player_standing_shooting_pistol.png");
    Image playerPistolShootingDown = new Image ("res/player_standing_down_shooting_pistol.png");
    Image playerPistolShootingLeft = new Image ("res/player_standing_left_shooting_pistol.png");
    Image playerPistolShootingRight = new Image ("res/player_standing_rigtht_shooting_pistol.png");
    
    zombieWalkingUp = getAnimation ( zombieWalking, 8 , 1 , 45, 52, 54, 100 );
    zombieWalkingLeft= getAnimation( zombieWalkingL, 1 , 8 , 52 , 45 , 54 , 100);
    zombieWalkingRight= getAnimation( zombieWalkingR, 1 , 8 , 52 , 45 , 54 , 100);
    zombieWalkingDown= getAnimation( zombieWalkingD, 8 , 1 , 45 , 52 , 54 , 100);
    playerWalkingUp=getAnimation(playerWalkingU , 8 , 1 , 36 , 59 , 54 , 100);
    playerWalkingLeft=getAnimation(playerWalkingL , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingRight=getAnimation(playerWalkingR , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingDown=getAnimation(playerWalkingD , 8 , 1 , 36 , 59 , 54 , 100);
    
    //animation for m4a1 and shotgun are out of place
    playerWalkingUpShotgun=getAnimation(playerWalkingUshotgun , 8 , 1 , 36 , 59 , 54 , 100);
    playerWalkingLeftShotgun=getAnimation(playerWalkingLshotgun , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingRightShotgun=getAnimation(playerWalkingRshotgun, 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingDownShotgun=getAnimation(playerWalkingDshotgun , 8 , 1 , 36 , 59 , 54 , 100);
    playerWalkingUpM4a1=getAnimation(playerWalkingUm4a1 , 8 , 1 , 36 , 59 , 54 , 100);
    playerWalkingLeftM4a1=getAnimation(playerWalkingLm4a1 , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingRightM4a1=getAnimation(playerWalkingRm4a1 , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingDownM4a1=getAnimation(playerWalkingDm4a1 , 8 , 1 , 36 , 59 , 54 , 100);
    
    //player with pistol shooting animation
    playerShootingPistolUp=getAnimation(playerPistolShootingUp, 4 , 1 , 36 , 59 , 54 , 100);
    playerShootingPistolDown=getAnimation(playerPistolShootingDown, 4 , 1 , 36 , 59 , 54 , 100);
    playerShootingPistolLeft=getAnimation(playerPistolShootingLeft, 1 , 4 , 59 , 36 , 54 , 100);
    playerShootingPistolRight=getAnimation(playerPistolShootingRight, 1 , 4 , 59 , 36 , 54 , 100);
    
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
    // rendering all images/strings to screen
        
        
    
    
    map.render(x*32, y*32); 
    
    
    
    
    g.drawString("Score: "+score,25,25);
    g.drawString("Round: "+round,125,25);
    g.drawString("Kills: "+kills,225,25);
    g.drawString("Money: $"+money,355,25);
    g.drawString("Weapon: "+weapon, 625, 25);
    g.drawString("Ammo: "+ammo, 525, 25);
    if(health>=1){heart.draw(850, 20);}
    if(health>=2){heart.draw(900, 20);}
    if(health==3){heart.draw(950, 20);}
    chestClosed.draw(x*32+1408,y*32+1760);
  
   if(buyChest==true && gc.getInput().isKeyPressed(Input.KEY_E ) && money>=950 ){
       money-=950;
       boxOpen2=true;
       boxStart=true;
   }
    //when buying the chest
    if(boxOpen2==true){
        
        box_Open2.draw(x*32+1408,y*32+1760);
        buyChest=false;
        if(!boxStart&&bCount<40){
            bCount++;
        }
        if(!boxStart&&bCount>=40){
            boxOpen2=false;
            if(weaponselc==0){
                 weapon="m4a1";
                 m4a1=true;
                 minigun=false;
                 nova=false;
                 p250=false;
            }
            if(weaponselc==1){
                 weapon="minigun";
                 m4a1=false;
                 minigun=true;
                 nova=false;
                 p250=false;
            }
            if(weaponselc==2){
                 weapon="nova";
                 m4a1=false;
                 minigun=false;
                 nova=true;
                 p250=false;
            }
            if(weaponselc==3){
                 weapon="p250";
                 m4a1=false;
                 minigun=false;
                 nova=false;
                 p250=true;
            }
        }
        if(boxStart){
            bCount=0;
            boxStart=false;
        }
       Random boxGen =new Random();
       
        weaponselc=boxGen.nextInt(3);
       
            if(weaponselc==0){
                 g.drawString("m4a1", 464, 398);
            }
            if(weaponselc==1){
                 g.drawString("minigun", 464, 398);
            }
            if(weaponselc==2){
                 g.drawString("nova", 464, 398);
            }
            if(weaponselc==3){
                 g.drawString("pistol",464,398);
            }
            
       
        if((x2==44 && y2==56) || (x2==45 && y2==56)&& buyChest==false)
        g.drawString("pick up(E)", 464, 398);
        
    }
      //up=1 down=2 left=3 right=4
   if(playerMovement==1){
      if(p250==true){playerWalkingUp.draw(480, 416, 32, 32); }
      if(m4a1==true){playerWalkingUpM4a1.draw(480, 416, 32, 32);}
      if(minigun==true){playerWalkingUp.draw(480, 416, 32, 32);}//change to minigun when artis done
      if(nova==true){playerWalkingUpShotgun.draw(480, 416, 32, 32);}
   }
   if(playerMovement==2){
      if(p250==true){playerWalkingDown.draw(480, 416, 32, 32); }
      if(m4a1==true){playerWalkingDownM4a1.draw(480, 416, 32, 32);}
      if(minigun==true){playerWalkingDown.draw(480, 416, 32, 32);}//change to minigun when artis done
      if(nova==true){playerWalkingDownShotgun.draw(480, 416, 32, 32);}
   }
   if(playerMovement==3){
      if(p250==true){playerWalkingLeft.draw(480, 416, 32, 32); } 
      if(m4a1==true){playerWalkingLeftM4a1.draw(480, 416, 32, 32);}
      if(minigun==true){playerWalkingLeft.draw(480, 416, 32, 32);}//change to minigun when artis done
      if(nova==true){playerWalkingLeftShotgun.draw(480, 416, 32, 32);}
   }
   if(playerMovement==4){
      if(p250==true){playerWalkingRight.draw(480, 416, 32, 32); }
      if(m4a1==true){playerWalkingRightM4a1.draw(480, 416, 32, 32);}
      if(minigun==true){playerWalkingRight.draw(480, 416, 32, 32);}//change to minigun when artis done
      if(nova==true){playerWalkingRightShotgun.draw(480, 416, 32, 32);}
   }
    
    if(door1Open==false){ //rendering botton door in main room
        doorUp.draw(x*32+480,y*32+1088);
        doorUp.draw(x*32+512,y*32+1088);
        doorUp.draw(x*32+544,y*32+1088);
        doorUp.draw(x*32+576,y*32+1088);
    }
    if(door2Open==false){ //rendering top right door in main room
        doorLeft.draw(x*32+864,y*32+736);
        doorLeft.draw(x*32+864,y*32+768);
        doorLeft.draw(x*32+864,y*32+800);
        doorLeft.draw(x*32+864,y*32+832);
    }
    if(door3Open==false){ //rendering bottom left room top left door
        doorRight.draw(x*32+352,y*32+2112);
        doorRight.draw(x*32+352,y*32+2144);
        doorRight.draw(x*32+352,y*32+2176);
        doorRight.draw(x*32+352,y*32+2208);
    }
    if(door4Open==false){ //rendering middel room botton door 
        doorUp.draw(x*32+1280,y*32+2208);
        doorUp.draw(x*32+1312,y*32+2208);
        doorUp.draw(x*32+1344,y*32+2208);
        doorUp.draw(x*32+1376,y*32+2208);
    }
    if(door5Open==false){ //rendering bottom right room botton door 
        doorUp.draw(x*32+2304,y*32+2240);
        doorUp.draw(x*32+2336,y*32+2240);
        doorUp.draw(x*32+2368,y*32+2240);
        doorUp.draw(x*32+2400,y*32+2240);
    }
    if(door6Open==false){ //rendering bottom right room top left door 
        doorRight.draw(x*32+2016,y*32+1632);
        doorRight.draw(x*32+2016,y*32+1664);
        doorRight.draw(x*32+2016,y*32+1696);
        doorRight.draw(x*32+2016,y*32+1728);
        doorRight.draw(x*32+2016,y*32+1760);
    }
    if(buyDoor==true){g.drawString("$750 (E)", 464, 400);}
    if(buyChest==true ){
     g.drawString("$950 (E)", 464, 400);
    }
    
    g.setColor(Color.red);
    
    for(int i=0;i<pos.size();i++){
        if(pos.get(i)==1){
            zombieWalkingUp.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
        if(pos.get(i)==2){
            zombieWalkingDown.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
        if(pos.get(i)==3){
            zombieWalkingLeft.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
        if(pos.get(i)==4){
            zombieWalkingRight.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
    }
    //up=1 down =2 left =3 right=4
    for(int i=0;i<pistolX.size();i++){
       if(pistolPos.get(i)==1){bulletup.draw(x*32+pistolX.get(i)+4,y*32+pistolY.get(i),8,8);}
       if(pistolPos.get(i)==2){bulletdown.draw(x*32+pistolX.get(i)-4,y*32+pistolY.get(i),8,8);}
       if(pistolPos.get(i)==3){bulletleft.draw(x*32+pistolX.get(i),y*32+pistolY.get(i)-4,8,8);}
       if(pistolPos.get(i)==4){bulletright.draw(x*32+pistolX.get(i),y*32+pistolY.get(i)+4,8,8);}
    }
    if(shooting==true && p250==true){
        if(playerMovement==1){playerShootingPistolUp.draw(480, 416, 32, 32);}
        if(playerMovement==2){playerShootingPistolDown.draw(480, 416, 32, 32);}
        if(playerMovement==3){playerShootingPistolLeft.draw(480, 416, 32, 32);}
        if(playerMovement==4){playerShootingPistolRight.draw(480, 416, 32, 32);}
        shooting=false;
    }

     }
     
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
     int objectLayer = map.getLayerIndex("Tile Layer 1");
     boolean moveable;
     //update for animations
      zombieWalkingUp.update(delta);
      zombieWalkingLeft.update(delta);
      zombieWalkingDown.update(delta);
      zombieWalkingRight.update(delta);
      playerWalkingUp.update(delta);
      playerWalkingLeft.update(delta);
      playerWalkingRight.update(delta);
      playerWalkingDown.update(delta);
      playerWalkingUpShotgun.update(delta);
      playerWalkingLeftShotgun.update(delta);
      playerWalkingRightShotgun.update(delta);
      playerWalkingDownShotgun.update(delta);
      playerShootingPistolUp.update(delta);
      playerShootingPistolDown.update(delta);
      playerShootingPistolLeft.update(delta);
      playerShootingPistolRight.update(delta);
     
     //getting the zombie to come out of its spawn and zombie spawning
    if(!start){
        //up=1,down=2,left=3,right=4;
        position[0]=2;
        position[1]=2;
        position[2]=3;
        zombieX.add(12);
        zombieY.add(20);
        toMove.add(false);
        moveC.add(2);
        pos.add(2);
        zombieHP.add(3);
        start=true;
        zombieSpawn[0][0]=12;
        zombieSpawn[0][1]=18;
        zombieSpawn[1][0]=23;
        zombieSpawn[1][1]=18;
        zombieSpawn[2][0]=28;
        zombieSpawn[2][1]=31;
        
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                door[j][i]=false;
            }
        }
        
        door[15][34]=true;
        door[16][34]=true;
        door[17][34]=true;
        door[18][34]=true;
        
        door[11][66]=true;
        door[11][67]=true;
        door[11][68]=true;
        door[11][69]=true;
        
        door[40][69]=true;
        door[41][69]=true;
        door[42][69]=true;
        door[43][69]=true;
        
        door[72][70]=true;
        door[73][70]=true;
        door[74][70]=true;
        door[75][70]=true;
        
        door[63][51]=true;
        door[63][52]=true;
        door[63][53]=true;
        door[63][54]=true;
        door[63][55]=true;
        
        door[27][23]=true;
        door[27][24]=true;
        door[27][25]=true;
        door[27][26]=true;
    }
    time+=delta;
    bulletTime+=delta;
    if(time>=500){
        moveOut();
        int randomInt = randomGenerator.nextInt(3);
        for(int i=0;i<pos.size();i++){
        }
           
        //if zombie spawn is Up
        if(position[randomInt]==1){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieX.get(i)==zombieSpawn[randomInt][0] && (zombieY.get(i)==zombieSpawn[randomInt][1] || zombieY.get(i)==zombieSpawn[randomInt][1]-1)){
                    test=1;
                    break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(1);
                zombieHP.add(3);
            }
        }
        
        //if zombie spawn is Down
        if(position[randomInt]==2){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieX.get(i)==zombieSpawn[randomInt][0] && (zombieY.get(i)==zombieSpawn[randomInt][1] || zombieY.get(i)==zombieSpawn[randomInt][1]+1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(2);
                zombieHP.add(3);
            }
        }
        
        //if zombie spawn is Left
        if(position[randomInt]==3){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieY.get(i)==zombieSpawn[randomInt][1] && (zombieX.get(i)==zombieSpawn[randomInt][0] || zombieX.get(i)==zombieSpawn[randomInt][0]-1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(3);
                zombieHP.add(3);
            }
        }
        
        //if zombie spawn is Right
        if(position[randomInt]==4){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieY.get(i)==zombieSpawn[randomInt][1] && (zombieX.get(i)==zombieSpawn[randomInt][0] || zombieX.get(i)==zombieSpawn[randomInt][0]+1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(4);
                zombieHP.add(3);
            }
        }
        
        for(int i=0;i<zombieX.size();i++){
            if(!toMove.get(i)){
                moveable = findNext(zombieX.get(i),zombieY.get(i),i);
                if(moveable){
                    if(nextY<zombieY.get(i)){
                        pos.set(i,1);
                    }
                    if(nextY>zombieY.get(i)){
                        pos.set(i,2);
                    }
                    if(nextX<zombieX.get(i)){
                        pos.set(i,3);
                    }
                    if(nextX>zombieX.get(i)){
                        pos.set(i,4);
                    }
                    zombieX.set(i,nextX);
                    zombieY.set(i,nextY);
                    
                }
                if(nextX==x2&&nextY==y2){
                    zombieX.remove(i);
                    zombieY.remove(i);
                    moveC.remove(i);
                    toMove.remove(i);
                    pos.remove(i);
                    zombieHP.remove(i);
                }
            }
        }
        
        
        time=0;
        
    }
    
    if(bulletTime>=1){
        
        for(int i=0;i<pistolX.size();i++){
            
            if(pistolPos.get(i)==1){
                pistolY.set(i,pistolY.get(i)-10);
            }
            if(pistolPos.get(i)==2){
                pistolY.set(i,pistolY.get(i)+10);
            }
            if(pistolPos.get(i)==3){
                pistolX.set(i,pistolX.get(i)-10);
            }
            if(pistolPos.get(i)==4){
                pistolX.set(i,pistolX.get(i)+10);
            }
            
            for(int j=0;j<zombieX.size();j++){
                zombieBox=new Rectangle((x+zombieX.get(j))*32,(y+zombieY.get(j))*32,32,32);
                if(zombieBox.contains(x*32+pistolX.get(i),y*32+pistolY.get(i))){
                    pistolX.remove(i);
                    pistolY.remove(i);
                    pistolPos.remove(i);
                    i--;
                    zombieHP.set(j,zombieHP.get(j)-1);
                    if(zombieHP.get(j)==0){
                        zombieX.remove(j);
                        zombieY.remove(j);
                        pos.remove(j);
                        toMove.remove(j);
                        moveC.remove(j);
                        zombieHP.remove(j);
                        j--;
                        kills++;
                        money+=50;
                    }
                    break;
                }
            }
        }
        for(int j=0;j<pistolX.size();j++){
            if(map.getTileId(pistolX.get(j)/32,pistolY.get(j)/32,objectLayer)!=0){
                pistolX.remove(j);
                pistolY.remove(j);
                pistolPos.remove(j);
                j--;
                break;
            }
            for(int k=0;k<100;k++){
                for(int l=0;l<100;l++){
                    if(door[l][k]){
                        doorBox=new Rectangle(l*32,k*32,32,32);
                        if(doorBox.contains(pistolX.get(j),pistolY.get(j))){
                            pistolX.remove(j);
                            pistolY.remove(j);
                            pistolPos.remove(j);
                            j--;
                            broken=true;
                            break;
                        }
                    }
                }
                if(broken){
                    break;
                }
            }
            if(broken){
                broken=false;
                break;
            }
        }
        
    }
    
    
    
     //to move right and checking for doors
     if(((x2==10&&y2==66)||(x2==10&&y2==67)||(x2==10&&y2==68)||(x2==10&&y2==69))&&!door3Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door3Open=true;
             money-=750;
             door[11][66]=false;
             door[11][67]=false;
             door[11][68]=false;
             door[11][69]=false;
         }
     }
     else if(((x2==26&&y2==23)||(x2==26&&y2==24)||(x2==26&&y2==25)||(x2==26&&y2==26))&&!door2Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door2Open=true;
             money-=750;
             door[27][23]=false;
             door[27][24]=false;
             door[27][25]=false;
             door[27][26]=false;
             
         }
     }
     else if(((x2==62&&y2==51)||(x2==62&&y2==52)||(x2==62&&y2==53)||(x2==62&&y2==54)||(x2==62&&y2==55))&&!door6Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door6Open=true;
             money-=750;
             door[63][51]=false;
             door[63][52]=false;
             door[63][53]=false;
             door[63][54]=false;
             door[63][55]=false;
         }
     }
     else if(gc.getInput().isKeyPressed(Input.KEY_D)){
         if(map.getTileId(x2+1,y2,objectLayer)==0){
            x--;
            x2++;
            playerMovement=4;
            buyDoor=false;
         }
     }
     //to move up and checking for doors/box
     if((x2==44 && y2==56) || (x2==45 && y2==56)){
         buyChest=true;
     }
     else {
         buyChest=false;
     }
     
     if(((x2==15&&y2==35)||(x2==16&&y2==35)||(x2==17&&y2==35)||(x2==18&&y2==35))&&!door1Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door1Open=true;
             money-=750;
             door[15][34]=false;
             door[16][34]=false;
             door[17][34]=false;
             door[18][34]=false;
         }
     }
     else if(((x2==40&&y2==70)||(x2==41&&y2==70)||(x2==42&&y2==70)||(x2==43&&y2==70))&&!door4Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door4Open=true;
             money-=750;
             door[40][69]=false;
             door[41][69]=false;
             door[42][69]=false;
             door[43][69]=false;
         }
     }
     else if(((x2==72&&y2==71)||(x2==73&&y2==71)||(x2==74&&y2==71)||(x2==75&&y2==71))&&!door5Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door5Open=true;
             money-=750;
             door[72][71]=false;
             door[73][71]=false;
             door[74][71]=false;
             door[75][71]=false;
         }
     }
     else if(gc.getInput().isKeyPressed(Input.KEY_W)){
         if(map.getTileId(x2,y2-1,objectLayer)==0 ){
            y++;
            y2--;
            playerMovement=1;
            buyDoor=false;
         }
     }
     //to move left and checking for doors
     if(((x2==12&&y2==66)||(x2==12&&y2==67)||(x2==12&&y2==68)||(x2==12&&y2==69))&&!door3Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door3Open=true;
             money-=750;
             door[11][66]=false;
             door[11][67]=false;
             door[11][68]=false;
             door[11][69]=false;
         }
     }
     else if(((x2==28&&y2==23)||(x2==28&&y2==24)||(x2==28&&y2==25)||(x2==28&&y2==26))&&!door2Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door2Open=true;
             money-=750;
             door[27][23]=false;
             door[27][24]=false;
             door[27][25]=false;
             door[27][26]=false;
         }
     }
     else if(((x2==64&&y2==51)||(x2==64&&y2==52)||(x2==64&&y2==53)||(x2==64&&y2==54)||(x2==64&&y2==55))&&!door6Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door6Open=true;
             money-=750;
             door[63][51]=false;
             door[63][52]=false;
             door[63][53]=false;
             door[63][54]=false;
             door[63][55]=false;
         }
     }
     else if(gc.getInput().isKeyPressed(Input.KEY_A)){
         if(map.getTileId(x2-1,y2,objectLayer)==0){
            x++;
            x2--;
            playerMovement=3;
            buyDoor=false;
         }
     }
     //to move down and checking for doors
    if(((x2==15&&y2==33)||(x2==16&&y2==33)||(x2==17&&y2==33)||(x2==18&&y2==33))&&!door1Open){
        buyDoor=true;
        if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door1Open=true;
             money-=750;
             door[15][34]=false;
             door[16][34]=false;
             door[17][34]=false;
             door[18][34]=false;
         }
    }
    else if(((x2==40&&y2==68)||(x2==41&&y2==68)||(x2==42&&y2==68)||(x2==43&&y2==68))&&!door4Open){
        buyDoor=true;
        if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door4Open=true;
             money-=750;
             door[40][69]=false;
             door[41][69]=false;
             door[42][69]=false;
             door[43][69]=false;
         }
    }
    else if(((x2==72&&y2==69)||(x2==73&&y2==69)||(x2==74&&y2==69)||(x2==75&&y2==69))&&!door5Open){
        buyDoor=true;
        if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door5Open=true;
             money-=750;
             door[72][70]=false;
             door[73][70]=false;
             door[74][70]=false;
             door[75][70]=false;
         }
    }
    else if(gc.getInput().isKeyPressed(Input.KEY_S)){
        if(map.getTileId(x2,y2+1,objectLayer)==0){
            y--;
            y2++;
            playerMovement=2;
            buyDoor=false;
        }
    }
    if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
        if(p250){
            if(playerMovement==1){
                pistolX.add(x2*32+8);
                pistolY.add(y2*32-24);
            }
            if(playerMovement==2){
                pistolX.add(x2*32+8);
                pistolY.add(y2*32+40);
            }
            if(playerMovement==3){
                pistolX.add(x2*32-24);
                pistolY.add(y2*32+8);
            }
            if(playerMovement==4){
                pistolX.add(x2*32+40);
                pistolY.add(y2*32+8);
            }
            pistolPos.add(playerMovement);
        }
        shooting=true;
        
    }
    
    System.out.println("X:"+x2);
    System.out.println("Y:"+y2);
     
    

    }
    
    //base code for zombie spawning and moving out
    public void moveOut(){
        if(time>=500){
            for(int i=0;i<toMove.size();i++){
                if(toMove.get(i)==true&&moveC.get(i)<2){
                    if(pos.get(i)==1){
                        moveC.set(i,moveC.get(i)+1);
                        zombieY.set(i,zombieY.get(i)-1);
                    }
                    if(pos.get(i)==2){
                        moveC.set(i,moveC.get(i)+1);
                        zombieY.set(i,zombieY.get(i)+1);
                    }
                    if(pos.get(i)==3){
                        moveC.set(i,moveC.get(i)+1);
                        zombieX.set(i,zombieX.get(i)-1);
                    }
                    if(pos.get(i)==4){
                        moveC.set(i,moveC.get(i)+1);
                        
                        zombieX.set(i,zombieX.get(i)+1);
                    }
                    if(moveC.get(i)==2){
                        toMove.set(i,false);
                    }
                }
            }
        }
    }
    
    public boolean findNext(int startX, int startY, int zNum){
        ArrayList<Integer> openX = new ArrayList();
        ArrayList<Integer> openY = new ArrayList();
        ArrayList<Integer> closedX = new ArrayList();
        ArrayList<Integer> closedY = new ArrayList();
        
        int[][] parentX = new int[100][100];
        int[][] parentY = new int[100][100];
        int[][] fCost = new int[100][100];
        int[][] hCost = new int[100][100];
        int[][] gCost = new int[100][100];
        int[][] b = new int[4][2];
        
        b[0][0]=0;
        b[0][1]=-1;
        b[1][0]=-1;
        b[1][1]=0;
        b[2][0]=1;
        b[2][1]=0;
        b[3][0]=0;
        b[3][1]=1;
        
        boolean[][] walkable = new boolean[100][100];
        
        int objectLayer = map.getLayerIndex("Tile Layer 1");
        int currentX = startX, currentY = startY;
        int fLow,hLow,opos=0,c,finalX=x2,finalY=y2;
        
        boolean nClosed=false,nOpen=false;
        
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                walkable[j][i]=true;
                for(int k=0;k<zombieX.size();k++){
                    if(zombieX.get(k)==j&&zombieY.get(k)==i&&k!=zNum)walkable[j][i]=false;
                }
                if(map.getTileId(j,i,objectLayer)!=0){
                    walkable[j][i]=false;
                }
                if(door[j][i])walkable[j][i]=false;
                hCost[j][i]=Math.abs(finalX-j)+Math.abs(finalY-i);
            }
        }
        fCost[startX][startY]=hCost[startX][startY];
        gCost[startX][startY]=0;
        parentX[startX][startY]=startX;
        parentY[startX][startY]=startY;
        for(int i=0;i<openX.size();){
            openX.remove(0);
            openY.remove(0);
        }
        for(int i=0;i<closedX.size();){
            closedX.remove(0);
            closedY.remove(0);
        }
        openX.add(startX);
        openY.add(startY);
        
        while(true){
            fLow=200;
            hLow=200;
            c=0;
            for(int i=0;i<openX.size();i++){
                if(fCost[openX.get(i)][openY.get(i)]<=fLow){
                    fLow=fCost[openX.get(i)][openY.get(i)];
                    c++;
                    opos=i;
                }
            }
            if(c>1){
                for(int i=0;i<openX.size();i++){
                    if(fCost[openX.get(i)][openY.get(i)]==fLow){
                        if(hCost[openX.get(i)][openY.get(i)]<hLow){
                            hLow=hCost[openX.get(i)][openY.get(i)];
                            opos=i;
                        }
                    }
                }
            }
            if(opos>=openX.size()){
                return false;
            }
            
            
            currentX=openX.get(opos);
            currentY=openY.get(opos);
            openX.remove(opos);
            openY.remove(opos);
            
            closedX.add(currentX);
            closedY.add(currentY);
            
            if(currentX==finalX&&currentY==finalY){
                retrace(startX,startY,finalX,finalY,parentX,parentY);
                return true;
            }
            
            
            for(int h=0;h<4;h++){
                nClosed=false;
                nOpen=false;
                for(int i=0;i<closedX.size();i++){
                    if(closedX.get(i)==currentX+b[h][0]&&closedY.get(i)==currentY+b[h][1]){
                        nClosed=true;
                    }
                }
                if(walkable[currentX+b[h][0]][currentY+b[h][1]]&&!nClosed){
                    for(int i=0;i<openX.size();i++){
                        if(openX.get(i)==currentX+b[h][0]&&openY.get(i)==currentY+b[h][1]){
                            nOpen=true;
                        }
                    }
                    if(!nOpen){
                        openX.add(currentX+b[h][0]);
                        openY.add(currentY+b[h][1]);
                        gCost[currentX+b[h][0]][currentY+b[h][1]]=gCost[currentX][currentY]+1;
                        fCost[currentX+b[h][0]][currentY+b[h][1]]=gCost[currentX+b[h][0]][currentY+b[h][1]]+hCost[currentX+b[h][0]][currentY+b[h][1]];
                        parentX[currentX+b[h][0]][currentY+b[h][1]]=currentX;
                        parentY[currentX+b[h][0]][currentY+b[h][1]]=currentY;
                    }
                    if(nOpen&&gCost[currentX+b[h][0]][currentY+b[h][1]]<gCost[currentX][currentY]+1){
                        gCost[currentX+b[h][0]][currentY+b[h][1]]=gCost[currentX][currentY]+1;
                        fCost[currentX+b[h][0]][currentY+b[h][1]]=gCost[currentX+b[h][0]][currentY+b[h][1]]+hCost[currentX+b[h][0]][currentY+b[h][1]];
                        parentX[currentX+b[h][0]][currentY+b[h][1]]=currentX;
                        parentY[currentX+b[h][0]][currentY+b[h][1]]=currentY;
                    }
                }
            }
        }
    }
    
    public void retrace(int sX, int sY, int cX, int cY, int[][] pX, int[][] pY){
        
        if(pX[cX][cY]==sX&&pY[cX][cY]==sY){
            nextX=cX;
            nextY=cY;
        }
        if(!(pX[cX][cY]==sX&&pY[cX][cY]==sY)){
            retrace(sX,sY,pX[cX][cY],pY[cX][cY],pX,pY);
        }
    }
     
    public int getID(){
        return 3;
    }
}
