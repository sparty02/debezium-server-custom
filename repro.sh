#!/usr/bin/env bash

debezium_version='1.7.0.Final'
dist_dir='debezium-server-dist'

# download debezium server if it hasn't already been for this repro
if [ -z "$(ls -A ${dist_dir})" ]; then
  mkdir -p $dist_dir
  curl "https://repo1.maven.org/maven2/io/debezium/debezium-server-dist/${debezium_version}/debezium-server-dist-${debezium_version}.tar.gz" | tar -xz -C ${dist_dir} --strip-components=1
fi

# build custom sink
custom_sink_name='debezium-sink-logging'

build_dir=$(mvn -f $custom_sink_name help:evaluate -Dexpression=project.build.directory -q -DforceStdout)
artifact_name=$(mvn -f $custom_sink_name help:evaluate -Dexpression=project.build.finalName -q -DforceStdout)
custom_sink_jar="$build_dir/$artifact_name.jar"

mvn -f $custom_sink_name clean package
cp "$custom_sink_jar" "${dist_dir}/lib"

# create repro props
cat << EOF > "$dist_dir/conf/application.properties"
debezium.sink.type=logging
EOF

# run debezium server
cd $dist_dir || exit
./run.sh
cd - || exit