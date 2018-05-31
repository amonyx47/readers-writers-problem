class Writer implements Runnable
{
    private LockForReadWrite resource;
    private int writerNum;

    Writer(int numOfWriters, LockForReadWrite resource) {
        this.writerNum = numOfWriters;
        this.resource = resource;
    }

    public void run() {
        while (true){
            SleepUtil.nap();

            System.out.println("Writer " + writerNum + " chce zapisovat.");
            resource.acquireWriteLock(writerNum);

            // mame pristup k zapisovaniu, tak zapisujeme
            SleepUtil.nap();

            resource.releaseWriteLock(writerNum);
        }
    }


}