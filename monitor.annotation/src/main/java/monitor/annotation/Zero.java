package monitor.annotation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface Zero {
}
