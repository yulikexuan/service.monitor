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

### Package

```
$ cd /c/dev/projects/jigsaw/service.monitor
$ jar -cvf mods/monitor.observer.jar -C classes/monitor.observer .
$ jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
$ jar -cvf mods/monitor.observer.alpha.jar -C classes/monitor.observer.alpha .
```