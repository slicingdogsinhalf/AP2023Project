import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startbutton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startbutton extends Actor
{
    /**
     * Act - do whatever the startbutton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public startbutton()
    {
        GreenfootImage start= new GreenfootImage("images/starter.png");
        start.scale(start.getWidth()/4,start.getHeight()/(4));
        setImage(start);
    }
}
