public class State {
   private String path;
    private Boolean isChanged;
    public void setPath(String myPath){
        path = myPath;
    }
    public String getPath(){
        return path;
    }
    public void setIsChanged(Boolean changes){
        isChanged = changes;
    }
    public Boolean getIsChanged(){
        return isChanged;
    }


    public State(String p, Boolean change){
        this.path = p;
        this.isChanged = change;
    }
}
