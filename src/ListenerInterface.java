public interface ListenerInterface {
  void updateObject();

  default Board getNewBoard() {
    System.out.println("Abstract method call from ListenerInterface. " +
        "Please override the getBoard() method. Null returned.");
    return null;
  }

  default Shot getNewShot() {
    System.out.println("Abstract method call from ListenerInterface. " +
        "Please override the getShot() method. Null returned.");
    return null;
  }
}
