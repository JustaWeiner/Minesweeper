public class Square {

    private boolean hidden;
    private boolean isFlagged;
    private int spaceType;

    public Square(){
        hidden=true;
        isFlagged=false;
        spaceType=0;
    }
    public void setHidden(boolean change){
        hidden=change;
    }
    public boolean isHidden(){
        return hidden;
    }
    public void setSpaceType(int newSpace){
        spaceType=newSpace;
    }
    public int getSpaceType(){
        return spaceType;
    }
    public void setFlagged(){
        isFlagged=!isFlagged;
    }
    public boolean hasFlag(){
        return isFlagged;
    }
    public void aroundBomb() {
        if (spaceType != 9) {
            spaceType++;
        }
    }
}
