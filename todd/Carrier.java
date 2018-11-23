public class Carrier extends Ships {

    private int size = 5;
    private int totalHealth = 5;
    private  int currentHealth = 5;
    private int id = 5;

    public Carrier(){
        super();
        this.setSize(size);
        this.setTotalHealth(totalHealth);
        this.setcurrentHealth(currentHealth);
        this.setId(id);
        this.setShipName("Carrier");

    }
}
