echo Cleaning ...
cd /c/dev/projects/jigsaw/service.monitor
rm -rf classes/monitor
rm -rf mods/monitor.jar
echo
echo Compiling ...
javac -p mods -d classes/monitor monitor/src/main/java/monitor/*.java monitor/src/main/java/module-info.java
echo
echo Building ...
echo
jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
echo
echo Running ...
echo
java -p mods -m monitor all
