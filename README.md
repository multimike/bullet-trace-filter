# bullet-trace-filter

This is a "TurboFilter" for Logback to be used with SLF4J. It can be used to set a specific log level for an individual thread. Typically used to pinpoint production problems in multi-threaded server environments, where each client is serviced by an individual thread (or sub-thread).

Note that, when setting a thread local log level, the log level of the logger itself is ignored.

### Usage:

#### Code
```scala
// We're probably somwhere in the server request handler here

// get log level from request headers, IP-address, username or anything you wish...
extractBulletTraceLevel() match {
  case Some(level) => BulletTraceFilter.setLevel(level)
  case _ => BulletTraceFilter.disable()
}

// code and logging statements goes here ...
logger.trace(s"This will only be seen when we use bullet tracing level 'TRACE'.")

BulletTraceFilter.disable() // optional, if always set beforehand
```

#### Logback.xml

```xml
<configuration>
  <turboFilter class="blueflow.logging.BulletTraceFilter" />
  ...
</configuration>
```
