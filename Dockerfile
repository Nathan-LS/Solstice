FROM openjdk:11-stretch
LABEL maintainer="nathan@nathan-s.com"
LABEL url="https://github.com/Nathan-LS/Solstice"
RUN apt-get update && apt-get install -y \
 git \
 && rm -rf /var/lib/apt/lists/*
WORKDIR /SolsticeDocker
RUN git clone https://github.com/Nathan-LS/Solstice.git
WORKDIR /SolsticeDocker/Solstice
RUN chmod +x gradlew
RUN ./gradlew assemble
WORKDIR /app
RUN cp /SolsticeDocker/Solstice/build/libs/* . -R
ENTRYPOINT ["java", "-jar", "solstice-0.0.1-SNAPSHOT.jar"]