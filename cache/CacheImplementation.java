package cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CacheImplementation {
    private static int queueSize;                                           // 4
    private static List<Integer> userInputNumbers = new ArrayList<>();      // 10, 66, 9, 5, 100, 9, 100, 20, 15, 100, 30

    private CircularQueue queue = new CircularQueue(queueSize);
    private int cacheHit = 0;
    private int cacheMiss = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("Size of circular queue?");
        Scanner scanner = new Scanner(System.in);
        queueSize = scanner.nextInt();

        System.out.println("Input items?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        for (String input : line.split(",")) {
            userInputNumbers.add(Integer.parseInt(input.trim()));
        }
        scanner.close();
        br.close();

        CacheImplementation cqi = new CacheImplementation();
        cqi.execute();
    }

    public void execute() {
        // enqueue user inputs
        for (Integer i : userInputNumbers) {
            System.out.println("Circular Queue : " + queue);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}

            // if the item already exists then don't enqueue but set refBit to 1
            if (queue.isPresent(i)) {
                System.out.println("Cache hit on item " + i);
                cacheHit++;
                continue;

            } else {
                System.out.println("Cache miss. User input to be added : " + i);
                cacheMiss++;
                // check if the queue is full before enqueuing
                // if queue is full then dequeue first item and then enqueue
                queue.dequeueAndEnqueue(new Box(i));
            }

            // Edge case: if queue is full and all refBits are 1 then set all refBits to zero and then dequeue and enqueue
        }

        System.out.println("Final cache state = " + queue);
        System.out.println("Number of replaced items : " + queue.getReplacedBoxes().size());
        System.out.println("Replaced items : " + queue.getReplacedBoxes());
        System.out.println("Total cache hit count = " + cacheHit);
        System.out.println("Total cache miss count = " + cacheMiss);
    }


}