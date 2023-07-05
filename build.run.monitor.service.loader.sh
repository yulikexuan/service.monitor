echo Cleaning ...
cd /c/dev/projects/jigsaw/service.monitor
rm -rf classes/monitor.service.loader
rm -rf mods/monitor.service.loader.jar
echo
echo Compiling ...
echo
javac -p mods -d classes/monitor.service.loader monitor.service.loader/src/main/java/monitor/service/loader/*.java monitor.service.loader/src/main/java/module-info.java
echo
echo Building ...
echo
jar -cvf mods/monitor.service.loader.jar -C classes/monitor.service.loader .
echo
echo Running ...
echo
java -p mods -m monitor all
