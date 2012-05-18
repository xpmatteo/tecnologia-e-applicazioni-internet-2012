#!/bin/bash

set -e
cd "$(dirname $0)/.."

CP=bin:lib/freemarker-2.3.16.jar:lib/simple-4.1.21.jar:mysql-connector-java-5.1.13.jar
java -cp $CP it.uninsubria.invoice.main.Main
