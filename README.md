# bullet-trace-filter

This is a "TurboFilter" for Logback to be used with SLF4J. It can be used to set a specific log level for an individual thread. Typically used to pinpoint production problems in multi-threaded server environments, where each client is serviced by an individual thread (or sub-thread).
