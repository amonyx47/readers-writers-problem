class SleepUtil {

    private static final int NAP_TIME = 5; //5 sekund

    public static void nap() {
        nap(NAP_TIME);
    }

    private static void nap(int duration) {
        int sleeptime = (int) (NAP_TIME * Math.random());
        try {
            Thread.sleep(sleeptime * 1000);
        } catch (InterruptedException e) {
            //nieco sa stalo (deadlock!)
        }
    }


}