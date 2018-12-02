public class Submarine extends Ships{

    private int size = 3;
    private int totalHealth = 3;
    private  int currentHealth = 3;
    private int id = 2;

    public Submarine(){
        super();
        this.setSize(size);
        this.setTotalHealth(totalHealth);
        this.setCurrentHealth(currentHealth);
        this.setId(id);
        this.setShipName("Submarine");
    }
}


