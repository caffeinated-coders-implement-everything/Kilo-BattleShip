import java.io.Serializable;

public class Shot implements Serializable {
  private int x;
  private int y;

  Shot() {
    x = -1;
    y = -1;
  }

  Shot(int _x, int _y) {
    x = _x;
    y = _y;
  }

  Shot(Shot _shot) {
    x = _shot.getX();
    y = _shot.getY();
  }

  public synchronized void setX(int x) {
      this.x = x;
  }

  public synchronized int getX() {
      return x;
  }

  public synchronized void setY(int y) {
      this.y = y;
  }

  public synchronized int getY() { return y; }

  public synchronized String getShotKey() {
    return Integer.toString(x) + Integer.toString(y);
  }

}
