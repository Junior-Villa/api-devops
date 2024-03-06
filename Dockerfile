FROM bellsoft/liberica-openjdk-alpine:17.0.6 

WORKDIR /micro-services/api-devops

ARG PROFILE
ARG ADDITIONAL_OPTS

RUN apk --no-cache update && apk add curl git

RUN git config --global credential.helper store

RUN mkdir -p /tmp/bucket

RUN echo "junior:prod@t16033" > /tmp/bucket/token.txt

RUN git clone --config credential.username=gitlab-ci-token --config credential.helper='store --file=/tmp/bucket/token.txt' http://gitlab.sifat.com.br:8888/waybe-api-microsservicos/api-bucket.git /tmp/bucket

COPY /target/API-DEVOPS.jar API-DEVOPS.jar

RUN ls -la && ls -la /tmp/bucket

CMD java ${ADDITIONAL_OPTS} -jar API-DEVOPS.jar --spring.profiles.active=${PROFILE}
