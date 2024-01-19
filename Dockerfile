FROM bellsoft/liberica-openjdk-alpine:17.0.6  

WORKDIR /micro-services/api-teste-devops

ARG PROFILE
ARG ADDITIONAL_OPTS  

COPY /target/API-TESTE-DEVOPS.jar API-TESTE-DEVOPS.jar

CMD java ${ADDITIONAL_OPTS} -jar API-TESTE-DEVOPS.jar --spring.profiles.active=${PROFILE}
