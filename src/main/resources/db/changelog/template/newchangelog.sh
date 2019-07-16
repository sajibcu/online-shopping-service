#!/usr/bin/env bash

if [ -z "$1" ]; then
    echo "Usage: newchangelog.sh <sprint number>"
    exit 0
fi

TIMESTAMP=$(date +%s)
SCRIPT_DIR="`pwd`/`dirname $0`"
FILE_DIR=$SCRIPT_DIR/../sprint$1
FILE_NAME="db.changelog-$TIMESTAMP.yaml"

cp $SCRIPT_DIR/basic.yaml $SCRIPT_DIR/$FILE_NAME

case "$(uname -s)" in
Darwin)
    sed -i '' "s/#timestamp/$TIMESTAMP/" $SCRIPT_DIR/$FILE_NAME
    sed -i '' "s/#author/$USER/" $SCRIPT_DIR/$FILE_NAME
    ;;
Linux)
    sed -i "s/#timestamp/$TIMESTAMP/" $SCRIPT_DIR/$FILE_NAME
    sed -i "s/#author/$USER/" $SCRIPT_DIR/$FILE_NAME
     ;;
esac

mkdir -p $FILE_DIR
mv $SCRIPT_DIR/$FILE_NAME $FILE_DIR