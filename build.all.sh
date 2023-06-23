echo Cleaning ...
cd /c/dev/projects/jigsaw/service.monitor
rm -rf classes/monitor*
rm -rf mods/**/*
echo
echo Compiling ...
javac -p mods --module-source-path "./*/src/main/java" -d classes -m monitor
javac -p mods -d classes/monitor.observer.alpha monitor.observer.alpha/src/main/java/monitor/observer/alpha/*.java monitor.observer.alpha/src/main/java/module-info.java
echo
echo Packaging
jar -cvf mods/monitor.observer.jar -C classes/monitor.observer .
jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
jar -cvf mods/monitor.observer.alpha.jar -C classes/monitor.observer.alpha .
echo
echo Done
ls -al mods
