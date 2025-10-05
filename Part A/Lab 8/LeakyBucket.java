import java.util.Scanner;

public class LeakyBucket{
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        
        int bucketCapacity,outputPacketSize, n;

        System.out.println("Enter bucket size");
        bucketCapacity=in.nextInt();

        System.out.println("Enter the output packet size");
        outputPacketSize=in.nextInt();
        
        System.out.println("Enter the number of requests");
        n=in.nextInt();


        int[] requests= new int[n];

        System.out.println("Enter the number of packets sent per request: ");
        for(int i=0; i<n; i++){
            System.out.printf("Request %d: ", i+1);
            requests[i]=in.nextInt();
        }

        int packetsInBucket=0;

        System.out.printf("%s\t%s\t%s\t%s\t%s\n", "Time","Packets/Req", "Packets Accepted","Bucket size", "Bucket Capacity");
        for(int i=0; i<n; i++){
            int packetsAccepted=requests[i];

            if(requests[i]+packetsInBucket<=bucketCapacity) packetsInBucket+=requests[i];
            else {

                System.out.println("Bucket overflow in the below request!");
                System.out.println("Dropping "+(requests[i]+packetsInBucket-bucketCapacity)+" packets");
                
                packetsAccepted=bucketCapacity-packetsInBucket;
                packetsInBucket=bucketCapacity;
            }

            if(packetsInBucket>outputPacketSize) packetsInBucket-=outputPacketSize;

            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\n", i+1,requests[i],packetsAccepted ,packetsInBucket,bucketCapacity);
            
        }

    }
}


//OUTPUT:

// Enter bucket size
// 6
// Enter the output packet size
// 2
// Enter the number of requests
// 5
// Enter the number of packets sent per request:
// Request 1: 3
// Request 2: 2
// Request 3: 3
// Request 4: 4
// Request 5: 8
// Time    Packets/Req     Packets Accepted        Bucket size     Bucket Capacity
// 1               3               3               1               6
// 2               2               2               1               6
// 3               3               3               2               6
// 4               4               4               4               6
// Bucket overflow in the below request!
// Dropping 6 packets
// 5               8               2               4               6