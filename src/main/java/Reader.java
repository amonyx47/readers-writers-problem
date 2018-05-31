class Reader implements Runnable {

    private LockForReadWrite resource;
    private int readerNum;

    Reader(int readerNum, LockForReadWrite resource) {
        this.readerNum = readerNum;
        this.resource = resource;
    }

    public void run() {
        while (true) {
            SleepUtil.nap();

            System.out.println("Reader " + readerNum + " chce citat.");
            resource.acquireReadLock(readerNum);

            // mame pristup k citaniu
            SleepUtil.nap();

            resource.releaseReadLock(readerNum);
        }
    }

    ;
}