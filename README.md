# Project Description
<p>
  This demo project is a completely scaleable selenium-docker project with complete flexibility to run the project locally or in AWS or any cloud platform. This selenium-java project code is fully reusable to test completely any web-based application.<br>
  Download and import the Maven project in Eclipse or any other Maven-supported IDE and explore.<br>
  maven terminal run command: <br>
  to run local machine: mvn clean test -Dbrowser=chrome -Dselenium.grid.enabled=false <br>
  Result: target > test-output > emailable-report.html (open with browser) <br>
  to run in docker (in linux platform): <br>
  docker-compose up <br>
  mvn clean test -Dbrowser=chrome -Dselenium.grid.enabled=true <br>

  Result: /output/ <br>
</p>

<img src="https://github.com/sgnsabir/portfolio/blob/main/images/selenium-docker.png">
