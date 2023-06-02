import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class losss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class losss extends Actor
{
    public GreenfootImage lose= new GreenfootImage("youlose.PNG");
    /**
     * Act - do whatever the losss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public losss(){
        lose.scale(lose.getWidth()*2,lose.getHeight()*2);
        setImage(lose);
    }
}
