docker build -t node/carbon .
docker-compose up -d
set projectLocation=%cd%
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause
