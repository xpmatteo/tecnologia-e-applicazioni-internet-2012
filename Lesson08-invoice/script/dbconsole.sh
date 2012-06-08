
hsqldb=lib/hsqldb.jar
config=config/sqltool.rc

cd "$(dirname $0)/.."

if [ "$1" == "--environment" -o "$1" == "-e" ]; then
  urlid=$2
  shift 2
else
  urlid="development"
fi

java -jar $hsqldb --rcfile $config $urlid $*

