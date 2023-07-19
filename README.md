# Service in Java Module

## Commands

### Clean

```
$ cd /c/dev/projects/jigsaw/service.monitor
$ rm -rf classes/monitor*
$ rm -rf mods/**/*
```

### Compile

```
$ javac
       --module-path mods
       --module-source-path "./*/src/main/java"
       -d classes
       --module monitor
$ javac -p mods --module-source-path "./*/src/main/java" -d classes -m monitor
```

```
$ cd monitor.observer.alpha
$ javac --module-path ../mods -d ../classes/monitor.observer.alpha src/main/java/monitor/observer/alpha/*.java src/main/java/module-info.java
```

```
$ cd monitor.observer.beta
$ javac --module-path ../mods -d ../classes/monitor.observer.beta src/main/java/monitor/observer/beta/*.java src/main/java/module-info.java
```

### Package

```
$ cd /c/dev/projects/jigsaw/service.monitor
$ jar -cvf mods/monitor.observer.jar -C classes/monitor.observer .
$ jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
$ jar -cvf mods/monitor.observer.alpha.jar -C classes/monitor.observer.alpha .
```

### Run
```
$ java --module-path mods --module monitor
```

### Excluding Services with ``` --limit-modules ```

```
$ java --module-path mods --limit-modules monitor --module monitor
$ java --module-path mods --limit-modules monitor --add-modules monitor.observer.alpha --module monitor
$ java --module-path mods --limit-modules monitor --add-modules monitor.observer.beta --module monitor
```

### Module Versions

#### Record version info
```
$ jar -v --create --file mods/monitor.jar --module-version 1.0 --main-class monitor.Main -C classes/monitor .
```

#### Check module's version

```
$ jar --describe-module --file mods/monitor.jar
```
