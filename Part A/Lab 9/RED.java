import java.util.Random;
import java.util.Scanner;

public class RED {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum number of packets:");
        int maxPackets = scanner.nextInt();

        System.out.println("Enter the queue size:");
        int queueSize = scanner.nextInt();

        System.out.println("Enter the maximum drop probability:");
        double maxProbability = scanner.nextDouble();

        System.out.println("Enter the minimum drop probability:");
        double minProbability = scanner.nextDouble();

        System.out.println("Enter the congestion threshold:");
        int threshold = scanner.nextInt();

        simulateCongestion(maxPackets, queueSize, maxProbability, minProbability, threshold);
    }

    private static void simulateCongestion(int maxPackets, int queueSize, double maxProbability, double minProbability, int threshold) {
        Random rand = new Random(System.currentTimeMillis());
        int queueLength = 0;

        // Simulate sending packets
        for (int i = 0; i < maxPackets; i++) {

            // Calculate drop probability based on current queue length
            double dropProbability = calculateDropProbability(queueLength, queueSize, maxProbability, minProbability, threshold);

            // Determine if the packet is dropped or accepted
            if (queueLength >= threshold && rand.nextDouble() < dropProbability) {
                System.out.println("Packet dropped (CONGESTION AVOIDANCE)");
                // Decrement queue length when a packet is dropped
                queueLength--;
            } else {
                System.out.println("Packet accepted " + (i + 1));
                queueLength++;
            }
        }
    }

    private static double calculateDropProbability(int currentQueueLength, int queueSize, double maxProbability, double minProbability, int threshold) {
        // Calculate drop probability using linear interpolation
        double slope = (maxProbability - minProbability) / (queueSize - threshold);
        return minProbability + slope * (currentQueueLength - threshold);
    }
}


// Enter the maximum number of packets:
// 100
// Enter the queue size:
// 20
// Enter the maximum drop probability:
// 0.8
// Enter the minimum drop probability:
// 0.2
// Enter the congestion threshold:
// 10
// Packet accepted 1
// Packet accepted 2
// Packet accepted 3
// Packet accepted 4
// Packet accepted 5
// Packet accepted 6
// Packet accepted 7
// Packet accepted 8
// Packet accepted 9
// Packet accepted 10
// Packet accepted 11
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 13
// Packet accepted 14
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 17
// Packet accepted 18
// Packet accepted 19
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 21
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 23
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 25
// Packet accepted 26
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 31
// Packet accepted 32
// Packet accepted 33
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 35
// Packet accepted 36
// Packet accepted 37
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 39
// Packet accepted 40
// Packet accepted 41
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 44
// Packet accepted 45
// Packet accepted 46
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 48
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 51
// Packet accepted 52
// Packet accepted 53
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 59
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 62
// Packet accepted 63
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 65
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 67
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 69
// Packet accepted 70
// Packet accepted 71
// Packet accepted 72
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 77
// Packet accepted 78
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 82
// Packet accepted 83
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 85
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 87
// Packet accepted 88
// Packet accepted 89
// Packet accepted 90
// Packet accepted 91
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 97
// Packet dropped (CONGESTION AVOIDANCE)
// Packet accepted 99
// Packet dropped (CONGESTION AVOIDANCE)
