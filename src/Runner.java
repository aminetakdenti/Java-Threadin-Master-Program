import java.util.Arrays;

public class Runner extends Thread {
  int[] position;

  // Constructor
  Runner(int n, int m) {
    randomPosition(n, m);
  }

  // Get random position
  public void randomPosition(int n, int m) {
    position = random(n, m);
  }

  @Override
  public void run() {}

  // Function to generate random position
  public int[] random(int rndI, int rndJ) {
    int[] arr = new int[2];
    int i = (int) (Math.random() * (rndI));
    int j = (int) (Math.random() * (rndJ));
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
    int i = 0;
    while (true) {
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

    // Print the runner's name, direction and position
    System.out.println(
      this.getName() +
      " Runner moved " +
      direction(i) +
      " to " +
      Arrays.toString(position)
    );
    return i;
  }
}
