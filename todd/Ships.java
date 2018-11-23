public abstract class Ships {

    private int _size;
    private int _totalHealth;
    private int _currentHealth;
    private int _id;
    private int healthDecrement = 1;

    private String _shipName;

    public Ships(){

    }


    public void setSize(int size){
        this._size = size;
    }

    public void setTotalHealth(int health) {
        this._totalHealth = health;
    }

    public void setcurrentHealth(int health) {
        this._currentHealth = health;
    }

    public void setId(int id){
        this._id = id;
    }

    public void setShipName(String shipName){
        this._shipName = shipName;
    }



    public int getSize(){
        return this._size;
    }

    public int getHealth(){
        return this._totalHealth;
    }

    public int getCurrentHealth(){
        return this._currentHealth;
    }

    public int getId(){
        return this._id;
    }

    public String getShipName(){return _shipName;}

    public void decrementHealth(){
        _currentHealth -= healthDecrement;
    }

    public void printShipSunk(){
        System.out.println(this.getShipName() + " sunk!");
    }
}
