cd /c/dev/projects/jigsaw/service.monitor
echo Cleaning ...
rm -rf classes
rm -rf mods
mkdir classes
mkdir mods
echo
echo Building monitor.service.loader module ...
javac -p mods -d classes/monitor.service.loader monitor.service.loader/src/main/java/monitor/service/loader/*.java monitor.service.loader/src/main/java/module-info.java
jar -cvf mods/monitor.service.loader.jar -C classes/monitor.service.loader .
echo
echo Building monitor.observer module
javac -p mods -d classes/monitor.observer monitor.observer/src/main/java/monitor/observer/*.java monitor.observer/src/main/java/module-info.java
jar -cvf mods/monitor.observer.jar -C classes/monitor.observer .
echo
echo Building monitor module
javac -p mods -d classes/monitor monitor/src/main/java/monitor/*.java monitor/src/main/java/module-info.java
jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
echo
echo ------- Done! -------
echo
ls -la mods
