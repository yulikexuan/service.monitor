./build.all.app.sh
echo
echo --- Building Services ---
echo
javac -p mods -d classes/monitor.observer.alpha monitor.observer.alpha/src/main/java/monitor/observer/alpha/*.java monitor.observer.alpha/src/main/java/module-info.java
javac -p mods -d classes/monitor.observer.beta monitor.observer.beta/src/main/java/monitor/observer/beta/*.java monitor.observer.beta/src/main/java/module-info.java
javac --class-path 'mods/*' -d classes/monitor.observer.zero monitor.observer.zero/src/main/java/monitor/observer/zero/*.java
cp -rf monitor.observer.alpha/src/main/resources/META-INF classes/monitor.observer.alpha
cp -rf monitor.observer.beta/src/main/resources/META-INF classes/monitor.observer.beta
cp -rf monitor.observer.zero/src/main/resources/META-INF classes/monitor.observer.zero
jar -v --create --file services/monitor.observer.alpha.jar --module-version 1.0.0 -C classes/monitor.observer.alpha .
jar -v --create --file services/monitor.observer.beta.jar --module-version 1.0.0 -C classes/monitor.observer.beta .
jar -v --create --file services/monitor.observer.zero.jar --module-version 1.0.0 -C classes/monitor.observer.zero .
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
echo
echo --- Running monitor as a modular jar with all services ---
java -p mods -m monitor all
echo
