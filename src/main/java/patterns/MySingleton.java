package patterns;


/**
 * Singleton is a design pattern that ensures a class has only one instance.
 * Most often the implementation defines a private constructor and a static variable.
 * Here we use lazy initialization to create the instance
 */
public class MySingleton {
    private static MySingleton INSTANCE;

    private MySingleton(){}

    public static MySingleton getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MySingleton();
        return INSTANCE;
    }

}
