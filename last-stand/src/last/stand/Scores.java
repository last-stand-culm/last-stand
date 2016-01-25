package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.io.*;
public class Scores extends BasicGameState{
    private Image background;
    private int c=0,sCap=0;
    private String line,disp="";
    private String[] top10 = new String[10];
    private boolean start=false;
    public Scores(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
        background=new Image("res/score_screen.png");
        
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException{
        background.draw();
//displaying your score on the score screen        
        if(start){
            for(int i=0;i<10;i++){
                if(!(top10[i].equals("0,0,0,0,0"))){
                    g.setColor(Color.red);
                    String[] split=top10[i].split(",");
                    disp="";
                    for(int j=0;j<5;j++){
                        disp+=split[j].toUpperCase();
                        for(int k=0;k<20-split[j].length();k++){
                            disp+=" ";
                        }
                    }
                    g.drawString(disp,150,50*i+250);
                }
            }
        }
//------------------------------------------------------------------------------         
        
    }
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException{
//reading the score from the file        
        if(!start){
            try{
                BufferedReader read=new BufferedReader(new FileReader("score.txt"));
                while((line=read.readLine())!=null){
                    top10[c]=line;
                    System.out.println(c);
                    c++;
                    if(c>9)break;
                }
            }catch(IOException e){System.out.println("Error: "+e);}
            start=true;
        }
//------------------------------------------------------------------------------         
    }
    public int getID(){
        return 5;
    }
}