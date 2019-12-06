call mvn clean javafx:compile javafx:jlink install
echo on
rmdir /S /Q dist
mkdir dist
copy target\scribe.exe dist\scribe-word-processor\scribe.exe
robocopy target/image dist/scribe-word-processor/jre /E