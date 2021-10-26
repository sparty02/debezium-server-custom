# Issue with Customo Sink in Debezium Server

This is a minimal reproduction of an issue that looks to be related to Quarkus Bean Discovery with Debezium Server, resulting in the following error:

```java
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2021-10-26 09:49:43,972 ERROR [io.qua.run.Application] (main) Failed to start application (with profile prod): java.util.NoSuchElementException: SRCFG00014: The config property debezium.sink.type is required but it could not be found in any config source
        at io.smallrye.config.SmallRyeConfig.convertValue(SmallRyeConfig.java:257)
        at io.smallrye.config.SmallRyeConfig.getValue(SmallRyeConfig.java:214)
        at io.smallrye.config.SmallRyeConfig.getValue(SmallRyeConfig.java:191)
        at io.debezium.server.DebeziumServer.start(DebeziumServer.java:99)
        at io.debezium.server.DebeziumServer_Bean.create(DebeziumServer_Bean.zig:256)
        at io.debezium.server.DebeziumServer_Bean.create(DebeziumServer_Bean.zig:272)
        at io.quarkus.arc.impl.AbstractSharedContext.createInstanceHandle(AbstractSharedContext.java:96)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:29)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:26)
        at io.quarkus.arc.impl.LazyValue.get(LazyValue.java:26)
        at io.quarkus.arc.impl.ComputingCache.computeIfAbsent(ComputingCache.java:69)
        at io.quarkus.arc.impl.AbstractSharedContext.get(AbstractSharedContext.java:26)
        at io.quarkus.arc.impl.ClientProxies.getApplicationScopedDelegate(ClientProxies.java:17)
        at io.debezium.server.DebeziumServer_ClientProxy.arc$delegate(DebeziumServer_ClientProxy.zig:67)
        at io.debezium.server.DebeziumServer_ClientProxy.arc_contextualInstance(DebeziumServer_ClientProxy.zig:82)
        at io.debezium.server.DebeziumServer_Observer_Synthetic_d70cd75bf32ab6598217b9a64a8473d65e248c05.notify(DebeziumServer_Observer_Synthetic_d70cd75bf32ab6598217b9a64a8473d65e248c05.zig:94)
        at io.quarkus.arc.impl.EventImpl$Notifier.notifyObservers(EventImpl.java:300)
        at io.quarkus.arc.impl.EventImpl$Notifier.notify(EventImpl.java:282)
        at io.quarkus.arc.impl.EventImpl.fire(EventImpl.java:70)
        at io.quarkus.arc.runtime.ArcRecorder.fireLifecycleEvent(ArcRecorder.java:128)
        at io.quarkus.arc.runtime.ArcRecorder.handleLifecycleEvents(ArcRecorder.java:97)
        at io.quarkus.deployment.steps.LifecycleEventsBuildStep$startupEvent1144526294.deploy_0(LifecycleEventsBuildStep$startupEvent1144526294.zig:87)
        at io.quarkus.deployment.steps.LifecycleEventsBuildStep$startupEvent1144526294.deploy(LifecycleEventsBuildStep$startupEvent1144526294.zig:40)
        at io.quarkus.runner.ApplicationImpl.doStart(ApplicationImpl.zig:634)
        at io.quarkus.runtime.Application.start(Application.java:101)
        at io.quarkus.runtime.ApplicationLifecycleManager.run(ApplicationLifecycleManager.java:101)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:66)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:42)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:119)
        at io.debezium.server.Main.main(Main.java:15)
```

To recreate, run `repro.sh` in the root of this repo..