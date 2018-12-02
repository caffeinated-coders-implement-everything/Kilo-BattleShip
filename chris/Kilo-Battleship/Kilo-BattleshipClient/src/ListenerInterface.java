public interface ListenerInterface {
  void updateObject();

  default Board getBoard() {
    System.out.println("Abstract method call from ListenerInterface. " +
        "Please override the getBoard() method. Null returned.");
    return null;
  }

  default Shot getShot() {
    System.out.println("Abstract method call from ListenerInterface. " +
        "Please override the getShot() method. Null returned.");
    return null;
  }
}
