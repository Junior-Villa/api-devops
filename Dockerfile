FROM bellsoft/liberica-openjdk-alpine:17.0.6   

WORKDIR /micro-services/api-devops  

ARG PROFILE
ARG ADDITIONAL_OPTS  

COPY /target/API-DEVOPS.jar API-DEVOPS.jar

CMD java ${ADDITIONAL_OPTS} -jar API-DEVOPS.jar --spring.profiles.active=${PROFILE}
