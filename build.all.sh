echo Cleaning ...
cd /c/dev/projects/jigsaw/service.monitor
rm -rf classes/monitor*
rm -rf mods/**/*
echo
echo Compiling monitor module ...
javac -p mods --module-source-path "./*/src/main/java" -d classes -m monitor
echo
echo Building monitor module ...
jar -cvf mods/monitor.observer.jar -C classes/monitor.observer .
jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
echo
echo Compiling Services ...
javac -p mods -d classes/monitor.observer.alpha monitor.observer.alpha/src/main/java/monitor/observer/alpha/*.java monitor.observer.alpha/src/main/java/module-info.java
javac -p mods -d classes/monitor.observer.beta monitor.observer.beta/src/main/java/monitor/observer/beta/*.java monitor.observer.beta/src/main/java/module-info.java
javac --class-path 'mods/*' -d classes/monitor.observer.zero monitor.observer.zero/src/main/java/monitor/observer/zero/*.java
cp -rf monitor.observer.zero/src/main/resources/META-INF classes/monitor.observer.zero
echo
echo Building Services ...
jar -cvf mods/monitor.observer.alpha.jar -C classes/monitor.observer.alpha .
jar -cvf mods/monitor.observer.beta.jar -C classes/monitor.observer.beta .
jar -cvf mods/monitor.observer.zero.jar -C classes/monitor.observer.zero .
echo
echo Done
ls -al mods
echo
echo Running monitor module ...
java -p mods -m monitor 
