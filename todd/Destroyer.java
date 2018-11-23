public class Destroyer extends Ships{

    private int size = 2;
    private int totalHealth = 2;
    private  int currentHealth = 2;
    private int id = 1;

    public Destroyer(){
        super();
        this.setSize(size);
        this.setTotalHealth(totalHealth);
        this.setcurrentHealth(currentHealth);
        this.setId(id);
        this.setShipName("Destroyer");
    }
}

