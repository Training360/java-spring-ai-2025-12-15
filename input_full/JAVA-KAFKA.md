×

# Kafka Java programozóknak (JAVA-KAFKA) (2 nap)

Skálázható és megbízható üzenetkezelés Apache Kafka és Java segítségével

## Ismertető

Ez a Java és Apache Kafka képzés átfogó betekintést nyújt a modern, elosztott üzenetkezelés és adatstreaming világába. A résztvevők megtanulják, hogyan használhatják a Kafka-t nagy teljesítményű és hibatűrő rendszerek építésére, beleértve a producer és consumer API-kat, a partíciókezelést, a replikációt és a pontos egyszeri üzenetfeldolgozást. A képzés során gyakorlati példákon keresztül sajátíthatók el a Kafka alapjai, valamint a haladó témák, mint a Kafka Streams, Schema Registry, Spring for Apache Kafka, Spring Cloud Stream. Ideális fejlesztőknek és architekteknek, akik szeretnék hatékonyan kezelni a valós idejű adatok feldolgozását.

## Szükséges előképzettség

Elvárt a Java programozási nyelv stabil ismerete.

Előfeltétele a _Java SE alapok (JAVA-BSC)_ és a _Java SE haladó (JAVA-ADV)_ képzések elvégzése, vagy azon képzések anyagának gyakorlati ismerete.

Mivel a tananyagok egy része angol nyelvű, ezért alapfokú, dokumentumolvasás-szintű angol nyelvtudás szükséges. A képzés magyar nyelvű, magyar slide-ok alapján.

## Tematika

  * Bevezetés a Kafka világába 
    * Mi az Apache Kafka?
    * Kafka főbb komponensei (Broker, Topic, Partition, Producer, Consumer, Consumer Group)
    * Maximális adatbiztonság, Topic Durability, Topic Replication, Acknowledgements, Log Retention, Flush Policy
    * Kafka összehasonlítása egyéb üzenetkezelő rendszerekkel (RabbitMQ, ActiveMQ stb.)
    * Használati esetek (real-time streaming, event-driven architektúrák stb.)
  * Kafka telepítése és konfigurálása 
    * Kafka letöltése és futtatása lokálisan
    * Kafka futtatása Docker, Docker Compose segítségével
    * Zookeeper szerepe és beállítása
    * KRaft
    * Kafka konfigurációs paraméterei
    * Alapvető Kafka CLI parancsok (topic létrehozása, üzenetek küldése/fogadása)
  * Producer és consumer fejlesztése Java Client for Apache Kafka használatával 
    * Producer API használata
    * Serialization, deserialization, JSON, Avro, Schema Registry
    * ProducerRecord és Serializer-ek
    * Particionálás és key használata
    * Aszinkron és szinkron küldés
    * Consumer API használata
    * Polling vs. event-driven feldolgozás
    * Consumer group
    * Consumer Incremental Cooperative Rebalance & Static Group Membership
    * Offset kezelés és commit stratégiák
    * Graceful shutdown
  * Kafka Streams – valós idejű adatfeldolgozás 
    * Mi az a Kafka Streams?
    * Alapvető koncepciók (KStream, KTable, Stateless vs. Stateful feldolgozás)
    * Egyszerű transzformációs műveletek (map, filter, groupBy, reduce)
    * Windowing és aggregáció
    * Custom Serializer/Deserializer használata (Serde)
  * Spring for Apache Kafka 
    * `@KafkaListener` és `KafkaTemplate` használata
    * Idempotent Kafka Producer és Consumer
    * Serialization, deserialization, JSON, Avro, Schema Registry
    * Message headers
    * Listener container leállítása/indítása
    * Filtering
    * Offset kezelése
    * Interceptors
    * Tranzakciók kezelése Kafka-val
    * Retry és exception/error handling
    * Dead Letter Queue (DLQ) használata hibás üzenetek kezelésére
    * Non-Blocking Retries
    * Integrációs tesztelés
    * Monitoring
  * Spring Cloud Stream
  * Kafka Admin API és Monitoring 
    * Kafka topic-ok és konfigurációjuk programozott módosítása
    * Prometheus, Grafana monitoring
  * Kafka Security (TLS, ACL, SASL)