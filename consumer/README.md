# Attachments Microservice

## Build project
Setup environment
```shell
export NEXUS_REGISTRY_USERNAME=wefox-server
export NEXUS_REGISTRY_PASSWORD=<<password>>
```

### Project specifics
- Maven wrapper is used by Dockerfile

### Maven build
```shell
./mvnw --settings settings.xml clean package
```
Produces a spring boot application located at `application/target/application.jar`

### Docker Image build

Compile and then build the image
```shell
# compile
./mvnw --settings settings.xml clean package
# build image
docker build . --build-arg JAR_FILE=application/target/application.jar --build-arg NEXUS_USERNAME=${NEXUS_USERNAME} --build-arg NEXUS_PASSWORD=${NEXUS_PASSWORD} -t comms-server-ms-crm-attachment
```

Compile during the docker build
```shell
docker build . -f compile.Dockerfile --build-arg NEXUS_USERNAME=${NEXUS_USERNAME} --build-arg NEXUS_PASSWORD=${NEXUS_PASSWORD} -t comms-server-ms-crm-attachment
```
Test the image
```shell
docker run -d --name comms-server-ms-crm-attachment \
  -p 8100:8080 \
  -e APP_POSTGRES_HOST=localhost \
  -e APP_POSTGRES_PORT=5432 \
  -e APP_POSTGRES_SCHEMA=salesforce \
  -e APP_POSTGRES_DB=attachments \
  -e APP_POSTGRES_USER=user \
  -e APP_POSTGRES_PASSWORD=password \
  -e APP_SERVER_PORT=8080 \
  -e APP_KAFKA_BROKERS=kafka:9092
  comms-server-ms-crm-attachment

curl --request GET -vsL \
     --url 'http://localhost:8100/actuator/health'
```
## :running_man: Run locally


### Environment
List of environment variables that may be available during runtime

| Variable | default | Description |
| --- | --- | --- |
| APP_PROFILE | dev, stg, pro | profile |
| APP_GATEWAY_URL | http://.... | gateway url |
| APP_SERVER_PORT | 8080 | server port |
| APP_POSTGRES_HOST | localhost | wglink host of postgres |
| APP_POSTGRES_PORT | 5432 | wglink postgres port |
| APP_POSTGRES_DB | attachments | wglink postgres database |
| APP_POSTGRES_SCHEMA | public | - |
| APP_POSTGRES_USER | user | - |
| APP_POSTGRES_PASSWORD | password | - |
| APP_KAFKA_BROKERS | localhost:29092 | - |
| APP_KAFKA_SECURITY_PROTOCOL | - | For secure communication use: SSL |
| APP_KAFKA_TRUSTSTORE | - | For secure communication use: /application/kafka.client.truststore.jks |
| APP_KAFKA_REPLICATION_FACTOR | 1 | should be increased on production |
| APP_TOPIC_EXECUTE_CREATE_CRM_DOCUMENT_COMMAND | command.comms.execute-create-crm-documents | kafka topic command |
| APP_TOPIC_EXECUTE_CREATE_CRM_DOCUMENT_GROUP | group.comms.documents| kafka topic group |
| APP_TOPIC_EXECUTE_CREATE_CRM_DOCUMENT_ERROR | command.comms.execute-create-crm-documents.dlq | kafka topic dlq |
| LOGSTASH_HOST | localhost:5000 | Not used unless "logstash" is spring profile is active |
| APP_OAUTH_SERVER | https://id-dev.wefox.com/auth/realms/wefox | - |
| APP_OAUTH_SERVER_CERTS | https://id-dev.wefox.com/auth/realms/wefox/protocol/openid-connect/certs | - |
| APP_OAUTH_SCOPE | crm-attachments | - |

### Docker compose
1. `./mvnw clean package`
1. `docker-compose -f docker-compose.yml build`
1. `docker-compose up -d`
1. Navigate to OpenApi definition http://localhost:8100/v3/api-docs/
1. OpenAPI YAML file http://localhost:8100/v3/api-docs.yaml
1. Swagger specification http://localhost:8100/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
1. Stop local environment `docker-compose down`
1. Delete all containers `docker rm -f $(docker ps -a -q)`
1. Delete all volums (Optional) `docker volume rm $(docker volume ls -q)`

### Maven
1. `docker-compose up -d postgres` -> Start the database locally
1. `docker-compose up -d zookeeper kafka` -> Start kafka locally
1. `./mvnw clean install`
1. `./mvnw -pl main spring-boot:run`

### Kafkacat
Install</br>
```
brew install kafkacat
```

Send something to a topic</br>
```
kafkacat -b localhost:29092 -t command.comms.execute-create-crm-documents -P
```

View a topic</br>
```
kafkacat -L -b localhost:29092 -t command.comms.execute-create-crm-documents
```

View number of messsages into topic</br>
```
kafkacat -b localhost:29092 -t command.comms.execute-create-crm-documents -C -e -q| wc -l
```

## :jigsaw: Naming and modules

### Architecture
architecture:

![modules](assets/architecture.png "Modules")

### Modules
The project is divided in the following modules:

![modules](assets/modules.png "Modules")

### Domain Class Diagram
These are the main entities in the ms-domain:

![modules](assets/domain.png "Modules")

### Sequence Class Diagram
These are the main entities in the ms-domain:

![modules](assets/sequence_diagram.png "Modules")

### Database
* Optimistic locking [link](https://docs.spring.io/spring-data/r2dbc/docs/current/reference/html/#r2dbc.optimistic-locking)
