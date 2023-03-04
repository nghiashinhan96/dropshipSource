FROM openjdk:11
RUN mkdir /runSeleniumGrid
WORKDIR /runSeleniumGrid
COPY . .
EXPOSE 4445:4445
EXPOSE 5557:5557
CMD ["java", "-jar", "selenium-server-standalone-3.141.59.jar -role hub -port 4445 -sessionTimeout 300 -maxSession 20", "java", "-Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone-3.141.59.jar -port 5557 -role node -hub http://localhost:4445/grid/register -browser "browserName=chrome, version=ANY, maxInstances=20, platform=WINDOWS, maxSession=10""]