echo Cleaning ...
cd /c/dev/projects/jigsaw/service.monitor
rm -rf classes
rm -rf mods
rm -rf services
mkdir classes
mkdir mods
mkdir services
echo
echo --- Building monitor.service.loader module ---
echo
javac -p mods -d classes/monitor.service.loader monitor.service.loader/src/main/java/monitor/service/loader/*.java monitor.service.loader/src/main/java/module-info.java
jar -cvf mods/monitor.service.loader.jar -C classes/monitor.service.loader .
echo
echo --- Building monitor.observer module ---
echo
javac -p mods -d classes/monitor.observer monitor.observer/src/main/java/monitor/observer/*.java monitor.observer/src/main/java/module-info.java
jar -cvf mods/monitor.observer.jar -C classes/monitor.observer .
echo
echo --- Building monitor module ---
echo
javac -p mods -d classes/monitor monitor/src/main/java/monitor/*.java monitor/src/main/java/module-info.java
jar -v --create --file mods/monitor.jar --main-class monitor.Main -C classes/monitor .
echo
echo --- Building Services ---
echo
javac -p mods -d classes/monitor.observer.alpha monitor.observer.alpha/src/main/java/monitor/observer/alpha/*.java monitor.observer.alpha/src/main/java/module-info.java
javac -p mods -d classes/monitor.observer.beta monitor.observer.beta/src/main/java/monitor/observer/beta/*.java monitor.observer.beta/src/main/java/module-info.java
javac --class-path 'mods/*' -d classes/monitor.observer.zero monitor.observer.zero/src/main/java/monitor/observer/zero/*.java
cp -rf monitor.observer.alpha/src/main/resources/META-INF classes/monitor.observer.alpha
cp -rf monitor.observer.beta/src/main/resources/META-INF classes/monitor.observer.beta
cp -rf monitor.observer.zero/src/main/resources/META-INF classes/monitor.observer.zero
jar -cvf services/monitor.observer.alpha.jar -C classes/monitor.observer.alpha .
jar -cvf services/monitor.observer.beta.jar -C classes/monitor.observer.beta .
jar -cvf services/monitor.observer.zero.jar -C classes/monitor.observer.zero .
echo
echo ------- All Done -------
echo
ls -al mods
ls -al services
echo
echo --- Running monitor as a modular jar ---
java -p mods -m monitor
echo
echo --- Running monitor as a plain jar ---
java -cp 'mods/*' monitor.Main
echo
echo --- Running monitor as a modular jar with alpha services ---
java -p mods -m monitor alpha
echo
echo --- Running monitor as a modular jar with beta services ---
java -p mods -m monitor beta
