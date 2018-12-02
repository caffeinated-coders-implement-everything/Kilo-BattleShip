public interface NotifierInterface {
  boolean isRepeatObject();
  void sendObject();
  default void setObject(Board _object) { }
  default void setObject(Shot _object) { }
}
