# ez-quarkus-vertx-kotlin
make it easy to use kotlin coroutines in quarkus project

## usage
- create a project with quarkus starter(https://code.quarkus.io/).  

  recommanded extension(s): 
  - RESTEasy JSON-B(or RESTEasy Jackson, but you need to config jdk8 LocalDate/LocalTime/LocalDateTime serializer manually)
  - SmallRye OpenAPI
  - SmallRye Health
  - SmallRye Metrics  
  
  optional extension(s):
  - quarkus-eureka(https://github.com/fmcejudo/quarkus-eureka)
- add jitpack to your repos
  ```
  <repository>
    <id>2-jitpack</id>
    <url>https://jitpack.io</url>
  </repository>
  ```
- add dep
  ```
  <dependency>
    <groupId>com.github.rainmanhhh</groupId>
    <artifactId>ez-quarkus-vertx-kotlin</artifactId>
    <version>1.0</version>
  </dependency>
  ```
- make your resource extends VertxScope and write async web endpoints with kotlin coroutines
  ```Kotlin
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/")
  class ExampleResource : VertxScope() {
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun helloAsync() = async {
      delay(1000)
      "hello"
    }
    @GET
    @Path("now")
    fun now() = LocalDate.now()
  }
  ```
- config the app by application.yml(or application.properties)
  ```Yaml
  quarkus:
    index-dependency:
      kotlinx-coroutines:
        group-id: org.jetbrains.kotlinx
        artifact-id: kotlinx-coroutines-core
    eureka: # if you don't use eureka, skip this part
      region: default
      health-check-url: ${quarkus.smallrye-health.root-path:/health}
      status-page-url: ${quarkus.swagger-ui.path:/swagger-ui}
      service-url:
        defaultZone: http://127.0.0.1:8761/eureka/
    swagger-ui:
      always-include: true
    log:
      category:
        "io.quarkus.eureka.operation.heartbeat.HeartBeatOperation": # if you don't use eureka, skip this part
          level: WARNING
  "%dev":
    quarkus:
      vertx: # use small pools and disable thread-blocking check for profile dev
        event-loops-pool-size: 1
        worker-pool-size: 1
        warning-exception-time: 1h
        max-event-loop-execute-time: 1h
        max-worker-execute-time: 1h
  ```
