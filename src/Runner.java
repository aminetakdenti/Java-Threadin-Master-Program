import java.util.Arrays;

public class Runner extends Thread {
  int[] position;

  // Constructor
  Runner() {
    randomPosition();
  }

  // Get random position
  public void randomPosition() {
    position = random();
  }

  @Override
  public void run() {}

  // Function to generate random position
  public int[] random() {
    int[] arr = new int[2];
    int i = (int) (Math.random() * 2);
    int j = (int) (Math.random() * 2);
    arr[0] = i;
    arr[1] = j;
    return arr;
  }

  // Function to return the direction
  public String direction(int i) {
    if (i == 1) {
      return "up";
    } else if (i == 2) {
      return "down";
    } else if (i == 3) {
      return "left";
    } else return "right";
  }

  // Function to move the runner
  public int runnerMove() throws InterruptedException {
    String direction = "";
    boolean test = false;
    int i = 0;
    while (!test) {
      i = (int) (Math.random() * 4 + 1);
      if (i == 1 && position[0] > 0) {
        position[0] -= 1;
        break;
      } else if (i == 2 && position[0] < 3) {
        position[0] += 1;
        break;
      } else if (i == 3 && position[1] > 0) {
        position[1] -= 1;
        break;
      } else if (i == 4 && position[1] < 3) {
        position[1] += 1;
        break;
      }
    }

    direction = direction(i);

    System.out.println(
      this.getName() +
      " Runner moved " +
      direction +
      " to " +
      Arrays.toString(position)
    );
    // Thread.sleep(1000);
    return i;
  }
}
