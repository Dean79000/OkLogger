![jcenter](https://img.shields.io/badge/_jcenter_-_1.0-SNAPSHOT-6688ff.png?style=flat) &#x2003; ![jcenter](https://img.shields.io/badge/_Tests_-_7/7-green.png?style=flat)

# OkLogger #

**/!\ This is work in progress, use only for development**

A logging library for the JVM and Android, built on top of okio.

## Download ##

Not now :/

## Usage ##

```java
final Configuration conf = new Configuration(Level.DEBUG);
conf.addOutput(new ConsoleOutput());
conf.addOutput(new FileOutput(new File("/my/path/to/file.log"));

final Logger logger = new BasicLogger(conf);
logger.info("Hello world !");
```