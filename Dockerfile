FROM bellsoft/liberica-openjdk-alpine:21

# Install curl jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

# Fix for windows
RUN dos2unix runner.sh

# Start the runner.sh
ENTRYPOINT sh runner.sh

# mvn clean package -DskipTests
# docker build -t=sgnsabir/selenium .
# volume mapping: sudo docker run -it -v ${PWD}/result:/home/selenium-docker/test-output sgnsabir/selenium
# java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=158.39.125.203 -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml

# sudo docker run -e BROWSER=firefox -e HUB_HOST=158.39.125.203 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=2 sgnsabir/selenium
# sudo docker run -v ${PWD}/result:/home/selenium-docker/test-output -e BROWSER=firefox -e HUB_HOST=158.39.125.203 -e TEST_SUITE=vendor-portal.xml -e THREAD_COUNT=2 sgnsabir/selenium




