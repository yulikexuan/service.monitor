## jlink

### The Runtime Image

```
 $ jlink --add-modules java.base --output jdk17-base
```

```
 $ jlink --module-path "/c/dev/Java/jdk-17.0.4/jmods" --add-modules java.base --output jdk17-base
```

```
$ jdk17-base/bin/java --list-modules
```

```
$ jlink --module-path "/c/dev/Java/jdk-17.0.4/jmods" --add-modules java.base,jdk.charsets --output jdk17-base
```

```
$ jlink --module-path "/c/dev/Java/jdk-17.0.4/jmods" --suggest-providers java.nio.charset.spi.CharsetProvider --output jdk17-base
```

```
jlink --add-modules java.base --bind-services --output jdk17-base
```

### Use jdeps to determine which platform modules we need to sustain a large app

```
jdeps -summary -recursive --class-path './lib/*' lib/app.jar
```

```
jdeps -summary -recursive --module-path './mods' mods/monitor.jar
```

### The Application Image

```
$ rm -rf jdk-monitor
$ jlink --module-path "/c/dev/Java/jdk-17.0.4/jmods;mods" --add-modules monitor --output jdk-monitor
$ jdk-monitor/bin/java --list-modules
$ jdk-monitor/bin/java --module monitor
```

```
$ rm -rf jdk-monitor
$ jlink --module-path "/c/dev/Java/jdk-17.0.4/jmods;mods;services" --add-modules monitor,monitor.observer.alpha,monitor.observer.beta --output jdk-monitor
$ jdk-monitor/bin/java --list-modules
$ jdk-monitor/bin/java --module monitor
```

### Create a Launcher

```
$ rm -rf jdk-monitor
$ jlink --module-path "/c/dev/Java/jdk-17.0.4/jmods;mods;services" --add-modules monitor,monitor.observer.alpha,monitor.observer.beta --output jdk-monitor --launcher run-monitor=monitor
$ cat jdk-monitor/bin/run-monitor
$ jdk-monitor/bin/run-monitor
$ jdk-monitor/bin/run-monitor all
```
