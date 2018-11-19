public abstract class Ship {

    private double _attack;
    private double _health;
    private double _resiliency;
    private double _speed;
    private double _accuracy;
    private double _turning;

    public void Ship(){

    }

    public void attack(){

    }

    public void moveTo(int x, int y){

    }

    public void set_attack(double _attack) {
        this._attack = _attack;
    }

    public double get_attack() {
        return _attack;
    }

    public void set_health(double health){
        this._health = health;
    }

    public double get_health(){
        return this._health;
    }

    public void set_resiliency(double _resiliency) {
        this._resiliency = _resiliency;
    }

    public double get_resiliency(){
        return this._resiliency;
    }

    public void set_speed(double _speed) {
        this._speed = _speed;
    }

    public double get_speed() {
        return _speed;
    }

    public void set_accuracy(double _accuracy) {
        this._accuracy = _accuracy;
    }

    public double get_accuracy() {
        return _accuracy;
    }

    public void set_turning(double _turning) {
        this._turning = _turning;
    }

    public double get_turning() {
        return _turning;
    }
}
