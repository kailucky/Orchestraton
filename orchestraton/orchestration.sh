#!/bin/sh

orchestration_home=`pwd`;
orchestration_jar="${orchestration_home}/target/orchestration-1.2.jar";

java -jar ${orchestration_jar};
