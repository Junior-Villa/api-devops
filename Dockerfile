FROM bellsoft/liberica-openjdk-alpine:17.0.6 

WORKDIR /micro-services/api-devops

ARG PROFILE
ARG ADDITIONAL_OPTS

RUN apk --no-cache update && apk add curl git openssh
RUN mkdir -p /tmp/bucket
RUN git clone git@gitlab.sifat.com.br:waybe-api-microsservicos/api-bucket.git /tmp/bucket

COPY /target/API-DEVOPS.jar API-DEVOPS.jar

RUN ls -la && ls -la /tmp/bucket

CMD java ${ADDITIONAL_OPTS} -jar API-DEVOPS.jar --spring.profiles.active=${PROFILE}
