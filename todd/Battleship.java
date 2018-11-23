public class Battleship extends Ships {

    private  int size = 4;
    private final int totalHealth = 4;
    private  int currentHealth = 4;
    private int id = 4;


    public Battleship(){
        super();
        this.setSize(size);
        this.setTotalHealth(totalHealth);
        this.setcurrentHealth(currentHealth);
        this.setId(id);
        this.setShipName("Battleship");
    }
}


