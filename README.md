Scala Concurrency Examples
--------------------------

A repository providing examples of how to implement various concurrency methods.

### Futures

There are two main examples using the `scala.concurrent.Future` API.

* Single Future
* Multiple Futures

Use the following command to run each example

    // single future
    sbt "run-main com.github.kevbradwick.scalaconcurrency.FutureExample"
    
    // multiple futures
    sbt "run-main com.github.kevbradwick.scalaconcurrency.MultipleFuturesExample"