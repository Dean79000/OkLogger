![jcenter](https://img.shields.io/badge/_jcenter_-_1.0-SNAPSHOT-6688ff.png?style=flat) &#x2003; ![jcenter](https://img.shields.io/badge/_Tests_-_12/12-green.png?style=flat)

# OkLogger #

**This is work in progress, bugs can still presents**

A logging library for the JVM and Android, built on top of okio.

It's lightweight library, has only one dependency and contains only few number of class. Unlike majors like log4j (600+ classes, 14+ dependencies), ...
With interfaces you can easily extend it, to add more outputs, ...

## Download ##

Not now :/

## Usage ##

```java
final Configuration conf = new Configuration(Level.DEBUG); //Minimum log level to output (ignore under levels)
conf.addOutput(new ConsoleOutput()); //Use System.out.println();
conf.addOutput(new FileOutput(new File("/my/path/to/file.log")); //Output in files : file.log, file.log.1 (if maxFiles = 2)

final Logger logger = new BasicLogger(conf);
logger.info("Hello world !"); //Log simple message
logger.warn(new Exception()); //Log exception
```

FileOutput offer by default rolling between 2 log files, you can disable it by settings maximum files to 1
```java
final FileOutput fo = new FileOutput(new File("/my/path/to/file.log");
fo.setMaxFiles((byte)1);
```

You can also change maximum file size. Default to 10Mb (expressed in bytes)
```java
final FileOutput fo = new FileOutput(new File("/my/path/to/file.log");
fo.setMaxFileSize(15 * 1024 * 1024); //15Mb
```

BasicLogger/FileOutput is Thread-Safe, you ~~can~~ should use same instance across multiple threads.


## FAQ ##

In case of number of files, why type byte is used instead of int ?<br/>
*If you need more than 127 differents files, consider raise maximum file size or explain me in what purpose. Furthermore it consume less memory.*
