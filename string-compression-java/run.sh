#!/bin/bash

root_dir=$(realpath $(dirname $0))
lib_dir=$root_dir/lib

javac $(find $root_dir -name "*.java") -d $lib_dir
java -cp $lib_dir tglanz.Program
