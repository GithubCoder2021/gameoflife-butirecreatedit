public class Cell {
    private boolean alive;
    
    public Cell(){
        alive = false;
    }
    public boolean getAlive(){
        return alive;
    }
    public void kill(){
        alive =  false;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public void birth(){
        alive = true;
    }
    public String toString(){
        if (alive){
            return "*";
        }else{
            return ".";
        }
    }
}
