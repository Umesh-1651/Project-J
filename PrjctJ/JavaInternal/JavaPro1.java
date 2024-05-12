//Program 1: Buuble Sort

import java.util.*;
public class JavaPro1 {
    public static void bubbleSort(int[] arr){
        int n = arr.length,temp;
        for(int i =0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 5 numbers:");
        int[] arr = new int[5];
        for(int i=0;i<5;i++){
            arr[i] = sc.nextInt();
        } 
        sc.close();
        System.out.println("Array Before Bubble Sort:");
        for(int i=0;i<5;i++){
            System.out.print(arr[i]+" ");
        }
        bubbleSort(arr);
        System.out.println("Array After Bubble Sort:");
        for(int i=0;i<5;i++){
            System.out.print(arr[i]+" ");
        }
    }
}

//Program 2: Method Overloading

// public class JavaPro1{
//     public static void methodOne(){
//         System.out.println("No Parameters");
//     }
//     public static void methodOne(int a){
//         System.out.println("One Integer Paramter.");
//         System.out.println(a);
//     }
//     public static void methodOne(int a,int b){
//         System.out.println("2 integer Parameters.");
//         System.out.println(a+" "+b);
//     }
//     public static void methodOne(double a){
//         System.out.println("One float Parameter.");
//         System.out.println(a);
//     }

//     public static void main(String[] args){
//         methodOne();
//         methodOne(5);
//         methodOne(5,3);
//         methodOne(1.1);
//     }
// }