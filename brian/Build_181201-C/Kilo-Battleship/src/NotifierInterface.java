public interface NotifierInterface {
  void sendObject();
  default void setNewBoard(Board _object) { }
  default void setNewShot(Shot _object) { }
}
