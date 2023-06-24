package monitor;

import java.net.URL;
import java.net.URLClassLoader;

public class ServiceClassLoader extends URLClassLoader {

    public ServiceClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

}
