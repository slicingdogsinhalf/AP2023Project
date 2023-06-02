import java.util.stream.Collectors;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Desert extends World
{
  
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public  int score=0;
    public static int highscore=0;
    public boolean died=false;
    public boolean started=false;
    public double difficulty=1;
    public boolean portalbool=false;
    public static boolean jumpbool = false;
    public static boolean d3=false;
    public static boolean d4=false;
    public static boolean quackersbool=true;
    public static boolean startbool=true;
    public static boolean alive=true;
    public static boolean spikebool=false;
    public static Spike spike=new Spike();
    public static duck temp = new duck();
    public static boolean currentduck=true;
    public static Dinosaur pika = new Dinosaur();
    public static startbutton start=new startbutton();
   
    public static Portal port = new Portal();
    public static over over=new over();
    public losss loser=new losss();
    private int timer=0;
    public static int level;
    GreenfootImage hg = getBackground();
    public Desert()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        start.getImage().setTransparency(255);
        pika.setImage("pikcahu1.png");
        pika.getImage().scale((int)pika.w,(int)pika.h);
        prepare();
        addObject(temp,600, 175);
        addObject(pika,100, 275);
        addObject(start,425,150); 
        addObject(spike,600,290);
        addObject(port, 600, 0);
        port.getImage().setTransparency(255);
        over.getImage().setTransparency(255);
        temp.getImage().setTransparency(0);
        spike.getImage().setTransparency(0);
        port.getImage().setTransparency(0);
        start.getImage().setTransparency(255);
        alive=true;
        started=false; 
    }

    public void act(){

        if(Greenfoot.mouseClicked(start)){
            start.getImage().setTransparency(0);
            started=true;
        }
        if(started){

            if(alive){
                died=false;
                if((Greenfoot.isKeyDown("Space")||Greenfoot.isKeyDown("w")) && !jumpbool){
                    jumpbool = true;
                    new Thread(() -> jump(14)).start();

                }
                if(Greenfoot.isKeyDown("s") && !d4){
                    pika.getImage().scale((int)pika.w,(int)pika.h/2);
                    pika.setLocation(pika.getX(),pika.getY()+pika.getImage().getHeight()/2);
                    d4 = true;
                    d3 = true;
                }
                else if (!Greenfoot.isKeyDown("s") && d3){

                    pika.getImage().scale((int)pika.w,(int)pika.h);
                    pika.setLocation(pika.getX(),pika.getY()-pika.getImage().getHeight()/4);
                    d4=false;
                    d3 = false;
                }
                if(startbool){
                    startbool=false;

                    currentduck=true;

                    temp.getImage().setTransparency(255);
                    new Thread(() -> move()).start();
                    temp.getImage().setTransparency(0);
                }
                if(spikebool){
                    spikebool=false;
                    currentduck=false;
                    spike.getImage().setTransparency(255);
                    new Thread(() -> spikers()).start();
                    spike.getImage().setTransparency(0);
                }

                score+=1;
                int timer=score/20;
                showText(""+timer,30,30);
                alive=isAlive();

                if(score%1000==0){
                    port.getImage().setTransparency(255);
                    new Thread(() -> port.por()).start();
                }
                if(pika.pikatouch()){
                    if(!portalbool){
                        difficulty+=0.2;
                        level = (int)((difficulty-1)*5+1);
                        if(level % 5 == 1){
                            hillsPrepare();
                            setBackground(hg);
                        } 
                        else if(level % 5 == 2){
                            cityBg();
                        }
                        else if(level % 5 == 3){
                            snowBg();
                        }
                        else if (level % 5 == 4){
                            lavaBg();
                        }
                        else{
                            prepare();
                        }
                    }
                    portalbool=true;
                }
                else{

                    portalbool=false;
                }
            }
            else{
                if(!died){
                    Greenfoot.playSound("deathSound.mp3");
                }
                died=true;
                addObject(loser,440,150);
                addObject(over,440,215);
                highscore=Math.max(score/20,highscore);
                showText("High Score: "+(highscore),440,110);
                showText("Your score was: "+(score/20),440,180);
                loser.getImage().setTransparency(255);
                over.getImage().setTransparency(255);
                spike.getImage().setTransparency(0); 
                temp.getImage().setTransparency(0);
                port.getImage().setTransparency(0);
                pika.setLocation(100,275); 
                pika.setImage("pikadead.png");
                Greenfoot.delay(50);
                if(Greenfoot.mouseClicked(over)){
                    revive(); 
                }
            }
        }
    }

    public void revive(){

        alive=true;
        showText(null,440,180);
        pika.setImage("pikcahu1.png");
        pika.setLocation(100,275);
        pika.getImage().scale((int)pika.w,(int)pika.h);
        loser.getImage().setTransparency(0);
        over.getImage().setTransparency(0);
        showText(null,440,110);
        score=0;
        difficulty=1;
        level=0;
        pika.setLocation(100,275); 
        prepare();
        setBackground(hg);
    }

    public boolean isAlive(){
        if(currentduck){
            int heighter=pika.getImage().getHeight();
            if(Greenfoot.isKeyDown("s")){
                heighter/=3;
            }
            int dist=temp.getImage().getWidth()+temp.getImage().getHeight()+pika.getImage().getWidth()+heighter;
            dist/=6;
            if(Math.sqrt(Math.pow(temp.getX()-pika.getX(),2)+Math.pow(temp.getY()-pika.getY(),2))<=dist){
                return false;
            }
            return true;
        }
        else{
            int heighter=pika.getImage().getHeight();
            int dist=spike.getImage().getWidth()+spike.getImage().getHeight()+pika.getImage().getWidth()+heighter;
            dist/=4;
            if(Math.sqrt(Math.pow(spike.getX()-pika.getX(),2)+Math.pow(spike.getY()-pika.getY(),2))<=dist){
                spike.getImage().setTransparency(0);
                return false;

            }
            return true;
        }
    }

    /**
    /**    
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    public void spikers(){
        spike.spikemove(difficulty);
        startbool=true;
    }

    public  void move(){
        temp.duckmove(difficulty);
        spikebool=true;
    }

    public static void jump(int j){
        pika.pikjump(j);
        jumpbool=false;
    }

    private void prepare()
    {
        GreenfootImage bg = getBackground();
        bg.clear();
        Color lBlue = new Color(125, 231, 255);
        bg.setColor(lBlue);
        bg.fill();
        Color sand = new Color(255,216,125);
        bg.setColor(sand);
        bg.fillRect(0,300,600,100);
        sandSpecs();
        bg.setColor(Color.YELLOW);
        bg.fillOval(-50,-50,150,150);

    }

    private void sandSpecs(){
        GreenfootImage spec = getBackground();
        Color sBrown = new Color(222,175,75);
        spec.setColor(sBrown);
        for(int i = 0; i < 20; i++){
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = 400-Greenfoot.getRandomNumber(100);
            spec.fillRect(x,y,3,3);
        }
    }

    public void clear(){
        GreenfootImage cl = getBackground();
        cl.clear();
    }
    //hills background
    public void hillsPrepare(){
        
        hg.clear();
        Color lBlue = new Color(162, 230, 250);
        hg.setColor(lBlue);
        hg.fill();
        //hills and grass
        Color hillG = new Color(41, 128, 32);
        hg.setColor(hillG);
        hg.fillOval(320, 220, 250, 200);
        hg.fillOval(250, 250, 150, 120);
        Color grass = new Color(77, 179, 66);
        hg.setColor(grass);
        hg.fillRect(0,300,600,100);
        //sun
        hg.setColor(Color.YELLOW);
        hg.fillOval(220,25,100,100);
        //clouds
        makeClouds();
        
    }
    public void makeClouds(){
        GreenfootImage c = getBackground();
        c.setColor(Color.WHITE);
        c.setTransparency(100);
        for(int i = 0; i < 3; i++){
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = 150-Greenfoot.getRandomNumber(100);
            c.fillRect(x,y, 100-Greenfoot.getRandomNumber(50),75-Greenfoot.getRandomNumber(50));
        }
    }
    public void cityBg(){
        GreenfootImage ci = getBackground();
        ci.clear();
        Color sky = new Color(67, 189, 250);
        ci.setColor(sky);
        ci.fill();
        Color sw = new Color(163, 176, 181);
        ci.setColor(sw);
        ci.fillRect(0, 235, 600, 165);
        Color road = new Color(50, 58, 61);
        ci.setColor(road);
        ci.fillRect(0, 250, 600, 150);
        ci.setColor(sw);
        ci.fillRect(0, 300, 600, 100);
        Color rline = new Color(237, 189, 78);
        ci.setColor(rline);
        ci.setColor(Color.YELLOW);
        ci.fillOval(Greenfoot.getRandomNumber(600),25,100,100);
        for(int i = 0; i < 4; i++){
            ci.fillRect(i*150, 267, 100, 10);
        }
        cities();
    }
    public void cities(){
        GreenfootImage cy = getBackground();
        int randX = (int)(Math.random()*100)+75;
        int randY = (int)(Math.random()*100)+100;
        Color b1 = new Color(150, 148, 143);
        cy.setColor(b1);
        cy.fillRect(200-randX, 235-randY, randX, randY);
        Color b2 = new Color(219, 217, 211);
        randX = (int)(Math.random()*100)+75;
        randY = (int)(Math.random()*100)+100;
        cy.setColor(b2);
        cy.fillRect(400-randX, 235-randY, randX, randY);
        Color b3 = new Color(67, 67, 99);
        randX = (int)(Math.random()*100)+50;
        randY = (int)(Math.random()*100)+100;
        cy.setColor(b3);
        cy.fillRect(600-randX, 235-randY, randX, randY);
        
    }
    public void snowBg(){
        int randY = (int)(Math.random()*150)+1;
        int randX = (int)(Math.random()*150)+1;
        int[] x1 = {0+randX, 37+randX,75+randX, 112+randX, 150+randX};
        int[] y = {300,(500-randY)/2,200-randY,(500-randY)/2,300};
        GreenfootImage sn = getBackground();
        sn.clear();
        Color sky = new Color(223, 242, 240);
        sn.setColor(sky);
        sn.fill();
        Color mtn = new Color(78, 97, 94);
        sn.setColor(mtn);
        sn.fillPolygon(x1, y, 5);
        Color snow = new Color(237, 245, 244);
        sn.setColor(snow);
        sn.fillRect(0,300,600,100);
        snowClouds();
        snowmen();
    }
    public void snowClouds(){
        GreenfootImage c = getBackground();
        c.setColor(Color.WHITE);
        c.setTransparency(100);
        int rand = 450-(int)(Math.random()*450);
        int height = Greenfoot.getRandomNumber(100);
        for(int i = 0; i < 3; i++){
            int x = rand+i*25;
            int y = height-Greenfoot.getRandomNumber(50);
            c.fillOval(x,y, 400-Greenfoot.getRandomNumber(100),100-Greenfoot.getRandomNumber(50));
        }
    }
    public void snowmen(){
        GreenfootImage s = getBackground();
        s.setColor(Color.WHITE);
        int rand = (int)(Math.random()*300)+300;
        for(int i = 0; i < 3; i++){
            s.fillOval(rand, 300-i*40, 50, 50);
        }
    }
    public void lavaBg(){
        int randY = (int)(Math.random()*150)+1;
        int randX = (int)(Math.random()*150)+1;
        int[] x1 = {250+randX, 287+randX, 325+randX, 362+randX, 400+randX};
        randX = (int)(Math.random()*150)+1;
        int[] x2 = {350+randX, 375+randX, 400+randX, 425+randX, 450+randX};
        int[] y = {300,(500-randY)/2,200-randY,(500-randY)/2,300};
        GreenfootImage lv = getBackground();
        lv.clear();
        Color crim = new Color(117, 40, 9);
        lv.setColor(crim);
        lv.fill();
        Color volc1 = new Color(43, 27, 24);
        lv.setColor(volc1);
        lv.fillPolygon(x1,y,5);
        Color volc2 = new Color(48, 31, 28);
        lv.setColor(volc2);
        lv.fillPolygon(x2,y,5);
        Color nether = new Color(74, 11, 2);
        lv.setColor(nether);
        lv.fillRect(0, 300, 600, 100);
        Color lava = new Color(207, 107, 0);
        lv.setColor(lava);
        lv.fillRect((int)(Math.random()*100), 0, 50, 300);
        smokeClouds();
    }
    public void smokeClouds(){
        GreenfootImage c = getBackground();
        Color smoke = new Color(168, 164, 160);
        c.setColor(smoke);
        c.setTransparency(100);
        for(int i = 0; i < 3; i++){
            int x = 600-Greenfoot.getRandomNumber(250);
            int y = 150-Greenfoot.getRandomNumber(100);
            c.fillRect(x,y, 100-Greenfoot.getRandomNumber(50),75-Greenfoot.getRandomNumber(50));
        }
    }
}
