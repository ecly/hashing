import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Hashing {

    private LinkedList<String>[] list;
    private int M; //size of array
    private int N; //numbers of object in array

    public Hashing() {
        M = 16; //default value
        list = new LinkedList[M];

        //fill the array
        for (int i = 0; i < M; i++){
            list[i] = new LinkedList<>();
        }
    }

    public void insert(String s) {
        checkSize();
        int hash = Math.abs(s.hashCode() % M);
        list[hash].add(s); //add the string to list at index of hash % N

    }

    private void checkSize() {
        if(N / M >= 8)
            makeBigger();
        else if (N / M <= 2 && M < 16)
            makeSmaller();
    }

    private void makeBigger() {
        LinkedList<String>[] aux = list;
        M = M*2; //double M
        list = new LinkedList[M]; //replace list with new bigger list

        //fill the array
        for (int i = 0; i < M; i++){
            list[i] = new LinkedList<>();
        }

        //fill everything from aux into our resized array
        for (LinkedList<String> currentList : aux) {
            for (String s : currentList)
                insert(s);
        }
    }

    public void makeSmaller() {
        LinkedList<String>[] aux = list;
        M = M/2; //half M
        list = new LinkedList[M]; //replace list with new bigger list

        //fill the array
        for (int i = 0; i < M; i++){
            list[i] = new LinkedList<>();
        }

        //fill everything from aux into our resized array
        for (LinkedList<String> currentList : aux) {
            for (String s : currentList)
                insert(s);
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        File file = new File("res/" + filename);
        Hashing hash = new Hashing();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String s = scanner.next();
                hash.insert(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}