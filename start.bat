@echo off
cd /d "%~dp0src"
javac -encoding UTF-8 *.java
java DragonTreasure
pause
