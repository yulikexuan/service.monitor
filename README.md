# Service in Java Module

## Commands

### Compile gamma module  
```
javac -p "../mods;../services" -d ../classes/monitor.observer.gamma src/main/java/monitor/observer/gamma/*.java src/main/java/module-info.java
```

### Compile alpha module
```
javac -p ../mods -d ../classes/monitor.observer.alpha src/main/java/monitor/observer/alpha/*.java src/main/java/module-info.java
jar -cvf ../mods/monitor.observer.alpha.jar -C ../classes/monitor.observer.alpha .
```
