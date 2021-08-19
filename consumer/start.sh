./docker/wait-for-it.sh --timeout=0 localhost:5432
./docker/wait-for-it.sh --timeout=0 localhost:2181
./docker/wait-for-it.sh --timeout=0 localhost:9092
./docker/wait-for-it.sh --timeout=0 localhost:29092
./docker/wait-for-it.sh --timeout=0 localhost:9200
./docker/wait-for-it.sh --timeout=0 localhost:5002
./docker/wait-for-it.sh --timeout=0 localhost:8880
./docker/wait-for-it.sh --timeout=0 localhost:5601
./gradlew :application:bootRun
