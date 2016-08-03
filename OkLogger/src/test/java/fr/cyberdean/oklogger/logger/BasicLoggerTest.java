package fr.cyberdean.oklogger.logger;

import fr.cyberdean.oklogger.Configuration;
import fr.cyberdean.oklogger.Level;
import fr.cyberdean.oklogger.output.Output;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicLoggerTest {
    private Output output;
    private String lastLog;

    @Before
    public void setUp() {
        output = new Output() {
            @Override
            public void append(String str) {
                lastLog = str;
            }
        };
        lastLog = null;
    }

    @After
    public void tearDown() {
        lastLog = null;
        output = null;
    }

    @Test
    public void debug(){
        final Configuration conf = new Configuration(Level.DEBUG, "{level} {className} {message}");
        conf.addOutput(output);
        final Logger log = new BasicLogger(conf);

        log.debug("hello");
        Assert.assertEquals("DEBUG fr.cyberdean.oklogger.logger.BasicLoggerTest hello", lastLog);
        log.info("Hi !");
        Assert.assertEquals("INFO fr.cyberdean.oklogger.logger.BasicLoggerTest Hi !", lastLog);
        log.warn("Wait");
        Assert.assertEquals("WARN fr.cyberdean.oklogger.logger.BasicLoggerTest Wait", lastLog);
        log.error("Fail");
        Assert.assertEquals("ERROR fr.cyberdean.oklogger.logger.BasicLoggerTest Fail", lastLog);
        log.fatal("System failure");
        Assert.assertEquals("FATAL fr.cyberdean.oklogger.logger.BasicLoggerTest System failure", lastLog);
    }

    @Test
    public void info(){
        final Configuration conf = new Configuration(Level.INFO, "{level} {className} {message}");
        conf.addOutput(output);
        final Logger log = new BasicLogger(conf);
        log.debug("hello");
        Assert.assertNull(lastLog);
        log.info("Hi !");
        Assert.assertEquals("INFO fr.cyberdean.oklogger.logger.BasicLoggerTest Hi !", lastLog);
        log.warn("Wait");
        Assert.assertEquals("WARN fr.cyberdean.oklogger.logger.BasicLoggerTest Wait", lastLog);
        log.error("Fail");
        Assert.assertEquals("ERROR fr.cyberdean.oklogger.logger.BasicLoggerTest Fail", lastLog);
        log.fatal("System failure");
        Assert.assertEquals("FATAL fr.cyberdean.oklogger.logger.BasicLoggerTest System failure", lastLog);
    }

    @Test
    public void warn(){
        final Configuration conf = new Configuration(Level.WARN, "{level} {className} {message}");
        conf.addOutput(output);
        final Logger log = new BasicLogger(conf);
        log.debug("hello");
        Assert.assertNull(lastLog);
        log.info("Hi !");
        Assert.assertNull(lastLog);
        log.warn("Wait");
        Assert.assertEquals("WARN fr.cyberdean.oklogger.logger.BasicLoggerTest Wait", lastLog);
        log.error("Fail");
        Assert.assertEquals("ERROR fr.cyberdean.oklogger.logger.BasicLoggerTest Fail", lastLog);
        log.fatal("System failure");
        Assert.assertEquals("FATAL fr.cyberdean.oklogger.logger.BasicLoggerTest System failure", lastLog);
    }

    @Test
    public void error(){
        final Configuration conf = new Configuration(Level.ERROR, "{level} {className} {message}");
        conf.addOutput(output);
        final Logger log = new BasicLogger(conf);
        log.debug("hello");
        Assert.assertNull(lastLog);
        log.info("Hi !");
        Assert.assertNull(lastLog);
        log.warn("Wait");
        Assert.assertNull(lastLog);
        log.error("Fail");
        Assert.assertEquals("ERROR fr.cyberdean.oklogger.logger.BasicLoggerTest Fail", lastLog);
        log.fatal("System failure");
        Assert.assertEquals("FATAL fr.cyberdean.oklogger.logger.BasicLoggerTest System failure", lastLog);
    }

    @Test
    public void fatal(){
        final Configuration conf = new Configuration(Level.FATAL, "{level} {className} {message}");
        conf.addOutput(output);
        final Logger log = new BasicLogger(conf);
        log.debug("hello");
        Assert.assertNull(lastLog);
        log.info("Hi !");
        Assert.assertNull(lastLog);
        log.warn("Wait");
        Assert.assertNull(lastLog);
        log.error("Fail");
        Assert.assertNull(lastLog);
        log.fatal("System failure");
        Assert.assertEquals("FATAL fr.cyberdean.oklogger.logger.BasicLoggerTest System failure", lastLog);
    }
}
