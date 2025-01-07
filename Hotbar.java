import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hotbar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hotbar extends Actor
{
    public int selected = 1;
    public Hotbar() {
        renderHotbar(selected);
    }
    public void act()
    {
        // gets current pressed key
        String key = Greenfoot.getKey();
        // just fail gracefully if it isnt a number
        try {
            int num = Integer.parseInt(key);
            // if number is within hotbar range, set it
            selected = (num > 0 &&  num <= 9) ? num : selected;
            // hotbar only needs to be re-rendered when changing
            renderHotbar(selected);
        } catch (Exception e) {}
    }
    
    private int thickness = 5;
    private void renderHotbar(int slot) {
        slot--;
        GreenfootImage hotbar = new GreenfootImage("images/hotbar.png");
        hotbar.setColor(Color.RED);
        // draws several rectangles for extra thickness and visual acuity
        for (int i = 0; i < thickness; i++) {
            hotbar.drawRect(19 + (int) (slot*50.5) + i, 85 + i, 50 - 2*i, 50 - 2*i);
        }
        setImage(hotbar);
    }
}
