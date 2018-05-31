import java.util.concurrent.Semaphore;

class Resource implements LockForReadWrite{
    private int readerCount;  // the number of active readers
    private Semaphore mutex;  // semafor pre readerov
    private Semaphore resource;     // semafor na kontrolu pristupu k resourcu

    public Resource() {
        readerCount = 0;
        mutex = new Semaphore(1);
        resource = new Semaphore(1);
    }

    public void acquireReadLock(int readerNum) {
        try{
            mutex.acquire();
        }
        catch (InterruptedException e) {}

        ++readerCount;

        // ak som prvy reader poviem vsetkym, ze prave citam ja
        if (readerCount == 1){
            try{
                resource.acquire();
            }
            catch (InterruptedException e) {
                //mozny deadlock!
            }
        }

        System.out.println("Reader " + readerNum + " prave cita. Pocet readerov = " + readerCount);

        mutex.release();
    }

    public void releaseReadLock(int readerNum) {
        try{
            //mutual exclusion for readerCount
            mutex.acquire();
        }
        catch (InterruptedException e) {
            //mozny deadlock!
        }

        --readerCount;

        // ak som posledny reader poviem vsetkym, ze uz sa necita
        if (readerCount == 0){
            resource.release();
        }

        System.out.println("Reader " + readerNum + " prestal citat. Pocet readerov = " + readerCount);

        mutex.release();
    }

    public void acquireWriteLock(int writerNum) {
        try{
            resource.acquire();
        }
        catch (InterruptedException e) {
            //mozny deadlock!
        }
        System.out.println("Writer " + writerNum + " prave zapisuje.");
    }

    public void releaseWriteLock(int writerNum) {
        System.out.println("Writer " + writerNum + " prestal prave zapisovat.");
        resource.release();
    }


}