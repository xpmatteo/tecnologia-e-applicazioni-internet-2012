#!/bin/bash

set -e
cd "$(dirname $0)/.."

classpath="bin"
for lib in lib/*.jar; do
	classpath=$classpath:$lib
done

java -cp $classpath it.uninsubria.invoice.main.Main
