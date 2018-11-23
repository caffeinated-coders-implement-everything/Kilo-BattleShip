public class Cruiser extends Ships{

    private int size = 3;
    private int totalHealth = 3;
    private  int currentHealth = 3;
    private int id = 3;

    public Cruiser(){
        super();
        this.setSize(size);
        this.setTotalHealth(totalHealth);
        this.setcurrentHealth(currentHealth);
        this.setId(id);
        this.setShipName("Cruiser");
    }
}


