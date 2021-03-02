## How to start:

* run zookeper: bin/zookeeper-server-start.sh config/zookeeper.properties`
* run kafka: `bin/kafka-server-start.sh config/server.properties`
* run application: `java -jar kafka-0.0.1-SNAPSHOT.jar`

## What is it?
Once started the application generates 100 random payments (0 <= payment < 2000) and publishes them to topic "Payments".
If the payment exceeds an amount of 1000 it will be further published to topic "LaunderyCheck" where it is declined.

## Sourcecode

[github repository](https://github.com/SchineD/kafka-testapp)