FROM clojure:temurin-21-lein-2.11.2-alpine

WORKDIR /pg2
COPY src src
COPY project.clj project.clj
RUN lein uberjar

EXPOSE 8080

CMD ["java", "-server", "-Xms2G", "-Xmx2G", "-XX:+UseNUMA", "-XX:+UseParallelGC", "-Dvertx.disableMetrics=true", "-Dvertx.threadChecks=false", "-Dvertx.disableContextTimings=true", "-Dvertx.disableTCCL=true", "-XX:+UseStringDeduplication", "-Djava.net.preferIPv4Stack=true", "-jar", "target/uberjar/server.jar"]
