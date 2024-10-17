#Step 0: Choose base
FROM  maven:3.8.7-openjdk-18-slim 

ARG OPSGENIE_API_TOKEN

#Step 1 : Get Env Variables for suite and environment
ENV suite "smokeTest.xml"
ENV environment "Production"
ENV API_TOKEN_GLOBAL_OPSGENIE=$OPSGENIE_API_TOKEN


#Step 3: copy code to WORKDIR
RUN   mkdir /cretests
WORKDIR /cretests
COPY . .


#Step 4: Prepare Docker image with maven dependencies
RUN mvn dependency:resolve
RUN mvn clean compile

#Step 5: below will run for docker run
CMD mvn test -DsuiteXmlFile=${suite} -Denvironment=${environment}
