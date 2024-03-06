FROM bellsoft/liberica-openjdk-alpine:17.0.6 

WORKDIR /micro-services/api-devops

ARG PROFILE
ARG ADDITIONAL_OPTS

RUN apk --no-cache update && apk add curl git
RUN mkdir -p /tmp/bucket && export CI_JOB_TOKEN="prod@t16033"
RUN git clone http://junior:$CI_JOB_TOKEN@gitlab.sifat.com.br:8888/waybe-api-microsservicos/api-bucket.git /tmp/bucket /tmp/bucket

COPY /target/API-DEVOPS.jar API-DEVOPS.jar

RUN ls -la && ls -la /tmp/bucket

CMD java ${ADDITIONAL_OPTS} -jar API-DEVOPS.jar --spring.profiles.active=${PROFILE}
