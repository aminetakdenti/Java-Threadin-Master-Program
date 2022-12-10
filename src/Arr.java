import java.util.concurrent.Semaphore;

public class Arr {
  int line, column;
  private String[][] arr;
  Semaphore sem = new Semaphore(1);

  public Arr(int n, int m) {
    line = n;
    column = m;
    arr = new String[line][column];
    initArr();
  }

  // Function to inisialize the array
  public void initArr() {
    for (int i = 0; i < line; i++) {
      for (int j = 0; j < column; j++) {
        arr[i][j] = "0";
      }
    }
  }

  // Function to print the array
  public void printArr() {
    System.out.println("-------------------------");
    for (int i = 0; i < line; i++) {
      for (int j = 0; j < column; j++) {
        System.out.print(arr[i][j] + "\t");
      }
      System.out.println();
    }
    System.out.println("-------------------------");
  }

  // Function to clear the array
  public void clearArr() {
    for (int i = 0; i < line; i++) {
      for (int j = 0; j < column; j++) {
        arr[i][j] = "0";
      }
    }
  }

  // Function to set value in array
  public int setArr(int i, int j, String value) throws InterruptedException {
    sem.acquire();
    if ("0".equals(arr[i][j])) {
      arr[i][j] = value;
      sem.release();
      return 1;
    } else {
      sem.release();
      return 0;
    }
  }
}
