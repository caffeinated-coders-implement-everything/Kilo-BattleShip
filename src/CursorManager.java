import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class CursorManager implements Runnable {
  ExecutorService threadManager;
  ClientNotifier notifier;

  private int x;
  private int y;
  private char keyPress;
  private Scanner scanner = new Scanner(System.in);
  private Shot newShot = null;

  CursorManager(ExecutorService _threadManager, ClientNotifier _notifier) {
    threadManager = _threadManager;
    notifier = _notifier;
  }

  public synchronized int getX() {
    return x;
  }

  public synchronized int getY() {
    return y;
  }

  @Override
  public void run() {
    threadManager.execute(notifier);

    // w, a, s, d, & f input implementation
    Scanner keyListener = new Scanner(System.in);

    while(keyListener.hasNext()) {
      try {
        keyPress = keyListener.nextLine().charAt(0);
        keyListener.reset();

        if ((keyPress == 'w' || keyPress == 'W') && x != 0) {
          --x;
        }
        else if ((keyPress == 's' || keyPress == 'S') && x != (Board.BOARD_LENGTH - 1)) {
          ++x;
        }
        else if ((keyPress == 'a' || keyPress == 'A') && y != 0) {
          --y;
        }
        else if ((keyPress == 'd' || keyPress == 'D') && y != (Board.BOARD_WIDTH - 1)) {
          ++y;
        }
        else if (keyPress == 'f' || keyPress == 'F') {

          newShot = new Shot(x, y);
          notifier.setNewShot(newShot);
        }
      }
      catch (Exception e) {
        keyListener.nextLine();
      }
    }


    // 1, 2, 3, 4 & 9 input implementation
    /*
    while(true) {

      int playerMove = scanner.nextInt();

      switch (playerMove) {

        case 1:
          if (y != 0) {
            y -= 1;
          }
          break;

        case 2:
          if (y != 20) {
            y += 1;
          }
          break;

        case 3:
          if (x != 0) {
            x -= 1;
          }
          break;

        case 4:
          if (x != 20) {
            x += 1;
          }
          break;

        case 9:
          newShot = new Shot(x, y);
          notifier.setNewShot(newShot);
          break;
      }
    }
    */
  }
}

