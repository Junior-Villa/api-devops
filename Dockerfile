FROM bellsoft/liberica-openjdk-alpine:17.0.6 

WORKDIR /micro-services/api-devops

# Instalação de dependências
RUN apk --no-cache update && \
    apk add curl git && \
    rm -rf /var/cache/apk/*

# Configuração do git para armazenar as credenciais localmente
#RUN git config --global credential.helper store

# Definição de variáveis de ambiente
ENV PROFILE=default
ENV ADDITIONAL_OPTS=""

# Clonar o repositório git
RUN mkdir -p /tmp/bucket && \
    export CI_JOB_TOKEN="prod@t16033" && \
    git config --system --unset credential.helper && \
    git clone http://junior:'$CI_JOB_TOKEN'@gitlab.sifat.com.br:8888/waybe-api-microsservicos/api-bucket.git /tmp/bucket

# Copiar o arquivo JAR para o diretório de trabalho
COPY target/API-DEVOPS.jar API-DEVOPS.jar

# Exibir conteúdo dos diretórios para fins de depuração
RUN ls -la && ls -la /tmp/bucket

# Comando padrão para iniciar a aplicação
CMD java ${ADDITIONAL_OPTS} -jar API-DEVOPS.jar --spring.profiles.active=${PROFILE}
