#!/bin/sh

set -e

./gradlew jar
cp build/libs/Pokemua.jar .
