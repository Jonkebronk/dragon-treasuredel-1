#!/bin/bash
cd "$(dirname "$0")/src"
javac -encoding UTF-8 *.java
java DragonTreasure
