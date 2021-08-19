#FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-slim as compiler
FROM gradle:jdk11-openj9
WORKDIR app

ARG USERNAME
ARG PASSWORD

COPY wait-for-it.sh .
COPY wait-for-it.sh /
COPY start.sh .
COPY start.sh /

RUN chmod +x wait-for-it.sh
RUN chmod +x /wait-for-it.sh
RUN chmod +x start.sh
RUN chmod +x /start.sh

