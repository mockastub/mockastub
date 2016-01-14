package hello;

public class Planet {

    private final String name;
    private boolean hostile;

    public Planet(String name, boolean hostile) {
        this.name = name;
        this.hostile = hostile;
    }

    public String getName() {
        return name;
    }

    public boolean isHostile() {
        return hostile;
    }    
}