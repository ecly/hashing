import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Adds Unique strings to an Array of LinkedLists using Hashing
 * and Separate-Chaining. Duplicates are not allow by default.
 */

public class Hashing {

    private LinkedList<String>[] list;
    private int M; //size of array
    private int N; //numbers of object in array
    private final boolean duplicatesAllowed = false;


    /**
     * Create an Array of default size 16
     * containing LinkedList<String>.
     */
    public Hashing() {
        M = 16; //default array length
        list = new LinkedList[M];

        //fill the array
        for (int i = 0; i < M; i++){
            list[i] = new LinkedList<>();
        }
    }

    /**
     * Insert a String into the LinkedList
     * at location of the hash if it doesn't exist already.
     * Resizes array if needed.
     * @param s    String to be added
     */
    public void insert(String s) {
        checkSize(); //check if resizing is needed
        if (!hashContains(s) || duplicatesAllowed) {
            N++;
            int hash = Math.abs(s.hashCode() % M);
            list[hash].add(s); //add the string to list at index of hash % N
        }
    }

    private boolean hashContains(String s){
        int hash = Math.abs(s.hashCode() % M);
        return list[hash].contains(s);
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

    private void makeSmaller() {
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

    /**
     * Deletes string from the list if it exists
     * otherwise prints that it doesn't exist in console.
     * @param s
     */
    public void delete(String s) {
        int hash = Math.abs(s.hashCode() % M);
        if (list[hash].contains(s))
            list[hash].remove(s);
        else
            System.out.println("The string attempted to delete does not exist");
    }


    /**
     * Tests the Hash.
     * Takes an input file of Strings. In my case "res/tale.txt".
     * Prints out first 10% of LinkedList's contents when done adding.
     * @param args
     */
    public static void main(String[] args) {
        String filename = args[0];
        File file = new File(filename);
        Hashing hash = new Hashing();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String s = scanner.next();
                hash.insert(s);
            }

            //Prints out content of first 10% of LinkedLists
            for (int i = 1; i < hash.M/10; i++) {
                for(String s : hash.list[i])
                    System.out.println(s + " " + i);
            }

            System.out.println("N = " + hash.N);
            System.out.println("M = " + hash.M);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}