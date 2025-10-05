import java.util.*;
import java.io.*;
import java.net.*;

public class P1 {
    public static void main(String[] args) {
        ArrayList<int[]> frames= new ArrayList<>();
        Random random=new Random();
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of frames");
        int num=sc.nextInt();

        System.out.print("\n--------\n");

        for(int i=0; i<num; i++){
            System.out.printf("Enter data for frame %d: ",i+1);
            int data=sc.nextInt();
            int seqNum=random.nextInt(500);
            int[] frame={seqNum,data};
            frames.add(frame);
        }
        System.out.print("\n--------\n");

        System.out.println("The frames before sorting are:");
        for(int[] frame:frames){
            System.out.printf("%d : %d\n",frame[0], frame[1]);
        }
        System.out.print("\n--------\n");

        Collections.sort(frames, (a,b)-> Integer.compare(a[0],b[0]));

        System.out.println("The frames after sorting are:");
        for(int[] frame:frames){
            System.out.printf("%d : %d\n",frame[0], frame[1]);
        }
    }    
}

// // Output
// Enter the number of frames
// 3

// --------
// Enter data for frame 1: 2 
// Enter data for frame 2: 232
// Enter data for frame 3: 433

// --------
// The frames before sorting are:
// 235 : 2
// 141 : 232
// 375 : 433

// --------
// The frames after sorting are:
// 141 : 232
// 235 : 2
// 375 : 433
