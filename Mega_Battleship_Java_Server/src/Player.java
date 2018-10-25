import java.util.List;
import java.util.ArrayList;


public class Player {

    private String _name;
    private int _score;
    private List<Ship> _ships = new ArrayList<Ship>();

    public void Player(){

    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public int get_score() {
        return _score;
    }

}


