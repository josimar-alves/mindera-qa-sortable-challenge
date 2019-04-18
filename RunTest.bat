docker pull selenium/standalone-chrome
docker run -d -p 4444:4444 selenium/standalone-chrome
docker build -t node/carbon .
docker run -d -p 49160:3000 node/carbon
set projectLocation=%cd%
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause