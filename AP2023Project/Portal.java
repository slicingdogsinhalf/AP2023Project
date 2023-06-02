import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Portal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portal extends Actor
{
    /**
     * Act - do whatever the Portal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //Desert d = new Desert();
    boolean offScreen=true;
    int i=0;
    public Portal(){
        GreenfootImage portal = new GreenfootImage("portal.png");
        portal.scale(portal.getWidth()/2,portal.getHeight());
        setImage(portal);
    }

    public void por()
    {
        
            setLocation(600,275);
            turnTowards(0,275);
            getImage().setTransparency(255);

            if(offScreen){  

                offScreen=false;
                while(this.getX()>0/* || ( isTouching(Dinosaur.class)*/){

                    i++;
                    this.setLocation(this.getX() - 5,this.getY());
                    Greenfoot.delay(1);
                    if(this.getX()<=0){
                        this.getImage().setTransparency(0);
                    }
                    else{this.getImage().setTransparency(255); offScreen=true;}
                    /*
                    if(!isTouching(Dinosaur.class)){
                    move(20);
                    if(getX() <= 0){
                    setLocation(600,275);
                    }
                    }
                    if(isTouching(Dinosaur.class)){
                    Greenfoot.setWorld(h);
                    }*/
                }

            }
            this.getImage().setTransparency(0);
            this.setLocation(600,(300));
        
    }
}
