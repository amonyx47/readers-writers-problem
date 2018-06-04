import java.util.concurrent.Semaphore;

class Resource implements LockForReadWrite{
    private int readerCount;  // pocet aktivnych readerov
    private Semaphore readersLock;  // semafor pre readerov
    private Semaphore resourceLock;     // semafor na kontrolu pristupu k resourcu

    Resource() {
        readerCount = 0;
        readersLock = new Semaphore(1);
        resourceLock = new Semaphore(1);
    }

    public void acquireReadLock(int readerNum) {
        try{
            //ziskam readersky semafor
            readersLock.acquire();
        }
        catch (InterruptedException e) {
            //mozny deadlock!
        }

        //zvysim pocet readerov
        ++readerCount;

        // ak som jeden reader poviem vsetkym, ze prave citam ja
        if (readerCount == 1){
            try{
                resourceLock.acquire();
            }
            catch (InterruptedException e) {
                //mozny deadlock!
            }
        }

        System.out.println("Reader " + readerNum + " prave cita. Pocet readerov = " + readerCount);

        //nemam readerov, releasnem readersky lock
        readersLock.release();
    }

    public void releaseReadLock(int readerNum) {
        try{
            //mutual exclusion for readerCount
            readersLock.acquire();
        }
        catch (InterruptedException e) {
            //mozny deadlock!
        }

        //znizim pocet readerov
        --readerCount;

        // ak je 0 readerov poviem vsetkym, ze uz sa necita
        if (readerCount == 0){
            resourceLock.release();
        }

        System.out.println("Reader " + readerNum + " prestal citat. Pocet readerov = " + readerCount);

        //releasnem readersky lock
        readersLock.release();
    }

    public void acquireWriteLock(int writerNum) {
        try{
            //skusim ziskat lock k resourcu
            resourceLock.acquire();
        }
        catch (InterruptedException e) {
            //mozny deadlock!
        }
        System.out.println("Writer " + writerNum + " prave zapisuje.");
    }

    public void releaseWriteLock(int writerNum) {
        System.out.println("Writer " + writerNum + " prestal prave zapisovat.");
        //prestanem citat, releasnem lock pre resource
        resourceLock.release();
    }


}