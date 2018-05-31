//podla semaforoveho pseudokodu na: https://en.wikipedia.org/wiki/Readers%E2%80%93writers_problem

import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Program pre riesenie readers-writers problemu pomocou semaforov.");
        System.out.println("Ovladanie:");
        System.out.println("Zadavame postupne 2 cisla: pocet readerov a pocet writerov.");
        System.out.println("Defaultny cas read/write operacii je nastaveny na 5 sekund.");
        System.out.println("Nachadzame sa v nekonecnom cykle poziadaviek na read/write... program treba ukoncit nasilne.");

        System.out.println("Zadaj pocet readerov: ");
        final int NUM_OF_READERS = scan.nextInt();
        System.out.println("Zadaj pocet writerov: ");
        final int NUM_OF_WRITERS = scan.nextInt();

        System.out.println("Vytvaram resource, ku ktoremu budeme pristupovat...");
        LockForReadWrite resource = new Resource();

        System.out.println("Vytvaram polia readerov a writerov...");
        Thread[] readerArray = new Thread[NUM_OF_READERS];
        Thread[] writerArray = new Thread[NUM_OF_WRITERS];

        System.out.println("Startujem thready readerov...");
        for (int i = 0; i < NUM_OF_READERS; i++) {
            readerArray[i] = new Thread(new Reader(i, resource));
            readerArray[i].start();
        }

        System.out.println("Startujem thready writerov...");
        for (int i = 0; i < NUM_OF_WRITERS; i++) {
            writerArray[i] = new Thread(new Writer(i, resource));
            writerArray[i].start();
        }
    }

}


