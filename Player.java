import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    public float posX = 0;
    public float posY = 64 * 20;
    
    public float velX = 0;
    public float velY = 0;
    
    private MyWorld world;
    public Player(MyWorld world) {
        this.world = world;
    }
    
    public void act()
    {
       moveTrig();
       placeTrig();
       breakTrig();
    }
    
    private void moveTrig() {
        boolean[] collisions = blockDirections();
        // user input to movement handle
        if (Greenfoot.isKeyDown("a")) {
            velX = (float) Math.max(velX - 0.5, -3.0);
            if (collisions[0]) {
                velX = 0;
            }
        }
        if (Greenfoot.isKeyDown("d")) {
            velX = (float) Math.min(velX + 0.5, 3.0);
            if (collisions[1]) {
                velX = 0;
            }
        }
        if (Greenfoot.isKeyDown("w") && collisions[2]) {
            velY = -6;
        }
        // to slowly stop when no key is held
        if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("d")) {
            if (velX > 0) { velX -= 0.5; }
            else if (velX < 0) { velX += 0.5; }
        }
        velY = (float) Math.min(velY + 0.3, (collisions[2]) ? 0 : 5);
        posX = (collisions[2]) ? (posX / 20) * 20 : posX;
        posX += velX;
        posY += velY;
    }
    
    // stores what directions blocks are
    // used for collisions
    private boolean[] blockDirections() {
        int roundX = (int) Math.floor(posX / 20);
        int roundY = (int) posY / 20;
        boolean[] directions = {false, false, false, false};
        for (Block block : getObjectsInRange(80, Block.class)) {
            int blockX = (int) Math.round(block.blockPosX / 20);
            int blockY = (int) block.blockPosY / 20;
            // left
            if (blockX == roundX && blockY == roundY) { directions[0] = true; }
            // right
            if (blockX == roundX + 1 && blockY == roundY) { directions[1] = true; }
            
            // bottoms
            if ((blockX == roundX + 1 || blockX == roundX) && blockY == roundY + 1) { directions[2] = true; }
            // tops
            if ((blockX == roundX + 1 || blockX == roundX) && blockY == roundY) { directions[3] = true; }
        }
        return directions;
    }
    
    // triggers for placing and breaking blocks
    private void placeTrig() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) { return; }
        // detects right click
        if (mouse.getButton() != 3) { return; }
        // -400 because coordinates start top left
        int mouseX = mouse.getX() - 400;
        int mouseY = mouse.getY() - 400;
        
        int worldX = Math.round((mouseX + posX) / 20);
        int worldY = Math.round((mouseY + posY) / 20);
        
        // cancels when too far away
        if (Math.abs(worldX - posX / 20) > 5 || Math.abs(worldY - posY / 20) > 5) { return; }
        world.showText("new block at " + worldX + " " + worldY, 120, 60);
        
        world.addObject(new Block(1, worldX, worldY, world), 0, 0);
    }
    private void breakTrig() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) { return; }
        // detects left click
        if (mouse.getButton() != 1) { return; }
        // -400 because coordinates start top left
        int mouseX = mouse.getX() - 400;
        int mouseY = mouse.getY() - 400;
        
        int worldX = Math.round((mouseX + posX) / 20);
        int worldY = Math.round((mouseY + posY) / 20);
        world.showText("broke block at " + worldX + " " + worldY, 120, 90);
        
        for (Block block : getObjectsInRange(1000, Block.class)) {
            if (block.blockPosX / 20 == worldX && block.blockPosY / 20 == worldY) {
                world.removeObject(block);
                break;
            }
        }
    }
}
