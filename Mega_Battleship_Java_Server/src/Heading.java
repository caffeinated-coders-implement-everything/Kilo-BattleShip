public class Heading {

    private double _location;
    private double _direction;
    private double _speed;

    public void Heading(){

    }

    public void set_location(double location) {
        _location = location;
    }

    public double get_location(){
        return this._location;
    }

    public void set_direction(double direction){
        _direction = direction;
    }

    public void set_speed(double speed) {
        _speed = speed;
    }

    public double get_speed() {
        return _speed;
    }
}
