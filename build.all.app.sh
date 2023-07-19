./clean.all.sh
echo
echo Building monitor.annotation module ...
javac -p mods -d classes/monitor.annotation monitor.annotation/src/main/java/monitor/annotation/*.java monitor.annotation/src/main/java/module-info.java
jar -v --create --file "mods/monitor.annotation.jar" --module-version 1.0.0 -C classes/monitor.annotation .
echo
echo Building monitor.service.loader module ...
javac -p mods -d classes/monitor.service.loader monitor.service.loader/src/main/java/monitor/service/loader/*.java monitor.service.loader/src/main/java/module-info.java
jar -v --create --file "mods/monitor.service.loader.jar" --module-version 1.0.0 -C classes/monitor.service.loader .
echo
echo Building monitor.observer module
javac -p "mods;lib" -d classes/monitor.observer monitor.observer/src/main/java/monitor/observer/*.java monitor.observer/src/main/java/module-info.java
jar -v --create --file "mods/monitor.observer.jar" --module-version 1.0.0 -C classes/monitor.observer .
echo
echo Building monitor.core module
javac -p mods -d classes/monitor.core monitor.core/src/main/java/module-info.java
jar -v --create --file "mods/monitor.core.jar" --module-version 1.0.0 -C classes/monitor.core .
each
echo Building monitor module
javac -p mods -d classes/monitor monitor/src/main/java/monitor/*.java monitor/src/main/java/module-info.java
jar -v --create --file mods/monitor.jar --module-version 1.0.0 --main-class monitor.Main -C classes/monitor .
echo
echo ------- Done! -------
echo
ls -la mods
