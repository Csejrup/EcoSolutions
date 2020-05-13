package ecosolutions;
/**
 * This Class
 *
 */
public final class Singleton {
    private static volatile Singleton instance;

    //CONSTRUCTOR
    private Singleton() {}

    public static Singleton getInstance(String value) {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}