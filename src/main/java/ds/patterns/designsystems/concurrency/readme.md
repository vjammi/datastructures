## Concurrency in Java

### Thread Life Cycle



### Creating a new thread

1. Extending the Thread class and overriding the run method
```
    public class Task{
        public static void main(String[] args) {
            Thread subTask1 = new SubTask1();
            subTask1.run();
    
            Runnable subTask2 = new SubTask2();
            subTask2.run();
        }
    }
    
    class SubTask1 extends Thread {
        public void run() {
            for (int i=0; i<10; i++) {
                System.out.println("Executing Task1 [extends Thread ]...");
            }
        }
    }
    
    class SubTask2 implements Runnable {
        public void run() {
            for (int i=0; i<10; i++) {
                System.out.println("Executing Task2 [implements Runnable]...");
            }
        }
    }
```

2. Implementing the Runnable interface

```
class SubTaskWithRunnable implements Runnable {
  public void run() {
    System.out.println("SubTaskWithRunnable started...");
  }
}
```




Reference:
https://howtodoinjava.com/java/multi-threading/