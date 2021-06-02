# hsql-demo

Create default database:
> cd hsqldb

> java -classpath lib/hsqldb.jar org.hsqldb.server.Server

Start database:
> java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb

Build project:
> mvn clean compile assembly:single

> mvn install

Run tests: 
> mvn test

Run program:
> java -cp target/luxoft-assignment-1.0-SNAPSHOT-jar-with-dependencies.jar Main


When typing pathname include logfile.txt, e.g.: C:\Users\Mateusz\Desktop\logfile.txt
