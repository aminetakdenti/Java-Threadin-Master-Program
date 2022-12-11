import java.util.concurrent.Semaphore;

class App {
  static Semaphore semInit = new Semaphore(1);
  static Semaphore semMove = new Semaphore(1);
  static int threadNumber;
  static int line;
  static int column;
  static int loop;

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
      if (threadNumber < (line * column)) {
        break;
      } else {
        System.out.println(
          "\u001B[31m" + "The number of threads is too big!!!" + "\u001B[0m"
        );
      }
    }

    // how much time you want to loop
    while (true) {
      System.out.println("Enter the number of loops: ");
      loop = Integer.parseInt(System.console().readLine());
      if (loop > 0) {
        break;
      } else {
        System.out.println(
          "\u001B[31m" + "you must enter number > 0!!" + "\u001B[0m"
        );
      }
    }

    // Create the array of runners
    Runner[] runners = new Runner[threadNumber];
    for (int i = 0; i < threadNumber; i++) {
      runners[i] = new Runner(line, column);
      System.out.println("Thread " + (i + 1) + " created");
    }

    System.out.println("#################");

    // Set the runners in the array
    for (int i = 0; i < threadNumber; i++) {
      checkThread(runners[i], arr, "t" + (i + 1));
    }

    System.out.println("#################");

    // Print the array
    arr.printArr();

    // Move the runners
    for (int i = 0; i < loop; i++) {
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
    while (true) {
      if (arr.setArr(thread.position[0], thread.position[1], i) == 1) {
        break;
      } else {
        thread.randomPosition(line, column);
      }
    }
    semInit.release();
  }

  // function for moving the thread
  public static void moveThread(Runner thread, Arr arr, String i)
    throws InterruptedException {
    semMove.acquire();
    while (true) {
      thread.runnerMove();
      if (arr.setArr(thread.position[0], thread.position[1], i) == 1) {
        break;
      }
      System.out.println(
        "\u001B[31m" + "That position is already taken!!!" + "\u001B[0m"
      );
    }
    semMove.release();
  }
}
