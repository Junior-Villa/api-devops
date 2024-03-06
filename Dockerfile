FROM bellsoft/liberica-openjdk-alpine:17.0.6 

WORKDIR /micro-services/api-devops

ARG PROFILE
ARG ADDITIONAL_OPTS

RUN apk --no-cache update && apk add curl git
RUN mkdir -p /tmp/bucket && export CI_JOB_TOKEN="-gmUkLxr3UV4xSXxUdDE"
RUN git clone https://github.com/provectus/kafka-ui.git /tmp/bucket

COPY /target/API-DEVOPS.jar API-DEVOPS.jar

RUN ls -la && ls -la /tmp/bucket

CMD java ${ADDITIONAL_OPTS} -jar API-DEVOPS.jar --spring.profiles.active=${PROFILE}
