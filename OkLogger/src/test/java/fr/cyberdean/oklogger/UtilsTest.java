package fr.cyberdean.oklogger;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

  @Test
  public void stacktraceToStringTest() {
    final Exception e = new Exception();
    final String str = Utils.stacktraceToString(e);
    final String expected = "java.lang.Exception\n" +
        "\tat fr.cyberdean.oklogger.UtilsTest.stacktraceToStringTest(UtilsTest.java:11)\n" +
        "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
        "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
        "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
        "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
        "\tat org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)\n" +
        "\tat org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)\n" +
        "\tat org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)\n" +
        "\tat org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)\n" +
        "\tat org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)\n" +
        "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)\n" +
        "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)\n" +
        "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)\n" +
        "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)\n" +
        "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)\n" +
        "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)\n" +
        "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)\n" +
        "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:309)\n" +
        "\tat org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecuter.runTestClass(JUnitTestClassExecuter.java:112)\n" +
        "\tat org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecuter.execute(JUnitTestClassExecuter.java:56)\n" +
        "\tat org.gradle.api.internal.tasks.testing.junit.JUnitTestClassProcessor.processTestClass(JUnitTestClassProcessor.java:66)\n" +
        "\tat org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:51)\n" +
        "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
        "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
        "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
        "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
        "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:35)\n" +
        "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)\n" +
        "\tat org.gradle.messaging.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:32)\n" +
        "\tat org.gradle.messaging.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:93)\n" +
        "\tat com.sun.proxy.$Proxy2.processTestClass(Unknown Source)\n" +
        "\tat org.gradle.api.internal.tasks.testing.worker.TestWorker.processTestClass(TestWorker.java:109)\n" +
        "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
        "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
        "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
        "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
        "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:35)\n" +
        "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)\n" +
        "\tat org.gradle.messaging.remote.internal.hub.MessageHub$Handler.run(MessageHub.java:360)\n" +
        "\tat org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:54)\n" +
        "\tat org.gradle.internal.concurrent.StoppableExecutorImpl$1.run(StoppableExecutorImpl.java:40)\n" +
        "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n" +
        "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\n" +
        "\tat java.lang.Thread.run(Thread.java:745)\n";
    assertEquals(expected, str);
  }



}
