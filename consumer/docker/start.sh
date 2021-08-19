./docker/wait-for-it.sh --timeout=0 db:5432
./docker/wait-for-it.sh --timeout=0 zookeeper:2181
./docker/wait-for-it.sh --timeout=0 kafka:9092
./docker/wait-for-it.sh --timeout=0 kafka:29092
./docker/wait-for-it.sh --timeout=0 elasticsearch:9200
./docker/wait-for-it.sh --timeout=0 logstash:5002
./docker/wait-for-it.sh --timeout=0 keycloak:8080
./docker/wait-for-it.sh --timeout=0 kibana:5601
gradle :application:bootRun
