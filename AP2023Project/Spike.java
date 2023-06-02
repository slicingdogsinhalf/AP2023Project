import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spike extends Actor
{
    /**
     * Act - do whatever the Spike wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Spike(){
        GreenfootImage spik= new GreenfootImage("images/spike.png");
        spik.scale(spik.getWidth()/12,spik.getHeight()/12);
        setImage(spik);
    }
    public void spikemove(double dif){
        
        Greenfoot.delay((int)Math.random()*20+20);
        
        boolean value=true;
        this.getImage().setTransparency(255);
        boolean offScreen=true;
        int i=0;

        if(offScreen){  
            
            offScreen=false;
            while(this.getX()>0){

                i++;
                this.setLocation(this.getX() - (int)(dif*10),this.getY());
                Greenfoot.delay(2);
                if(this.getX()<=0){
                    this.getImage().setTransparency(0);
                }
                else{this.getImage().setTransparency(255); offScreen=true;}

            }
        }
        this.getImage().setTransparency(0);
        this.setLocation(600,(290));
    }   
}
