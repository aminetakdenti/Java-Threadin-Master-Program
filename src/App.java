import java.util.concurrent.Semaphore;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

class App {
  static Semaphore semInit = new Semaphore(1);
  static Semaphore semMove = new Semaphore(1);
  static int threadNumber;
  static int line;
  static int column;

  public static void main(String[] args) throws InterruptedException {
    // Init the array
    System.out.println("Enter the size of the matrix size: ");
    // Line and column
    System.out.println("Enter line number: ");
    line = Integer.parseInt(System.console().readLine());
    System.out.println("Enter column number: ");
    column = Integer.parseInt(System.console().readLine());
    Arr arr = new Arr(line, column);

    // Init the runners
    while (true) {
      System.out.println("Enter the number of threads: ");
      threadNumber = Integer.parseInt(System.console().readLine());
      if (threadNumber <= (line * column)) {
        break;
      } else {
        System.out.println("The number of threads is too big!!!");
      }
    }

    // Create the array of runners
    Runner[] runners = new Runner[threadNumber];
    for (int i = 0; i < threadNumber; i++) {
      System.out.println("Thread " + (i + 1) + " is created");
      runners[i] = new Runner();
    }

    System.out.println("#################");

    // Set the runners in the array
    for (int i = 0; i < threadNumber; i++) {
      System.out.println("Thread " + (i + 1) + " is setted");
      checkThread(runners[i], arr, "t" + (i + 1));
    }

    System.out.println("#################");
    // Print the array
    arr.printArr();
    // Move the runners
    for (int i = 0; i < 5; i++) {
      arr.clearArr();
      for (int j = 0; j < threadNumber; j++) {
        moveThread(runners[j], arr, "t" + (j + 1));
      }
      arr.printArr();
      Thread.sleep(1000);
    }
  }

  // Function for checking if the thread is in the same position and set it to array
  public static void checkThread(Runner thread, Arr arr, String i)
    throws InterruptedException {
    semInit.acquire();
    boolean condition = false;
    while (!condition) {
      if (arr.setArr(thread.position[0], thread.position[1], i) == 1) {
        condition = true;
      } else {
        thread.randomPosition();
      }
    }
    semInit.release();
  }

  // function for moving the thread
  public static void moveThread(Runner thread, Arr arr, String i)
    throws InterruptedException {
    semMove.acquire();
    // boolean move = false;
    while (true) {
      thread.runnerMove();
      if (arr.setArr(thread.position[0], thread.position[1], i) == 1) {
        break;
      }
    }
    semMove.release();
  }
}
