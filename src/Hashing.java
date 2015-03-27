package src;

import java.util.LinkedList;

public class Hashing {

    private LinkedList<Integer>[] list;
    private int N; //size of array
    private int M; //numbers of object in array

    public Hashing(int _N) {
        N = _N;
        list = new LinkedList[N];

        for (int i = 0; i < N; i++){
            list[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        System.out.println("Success");
    }
}