RM = rm -rf

all: TempControl sensorlib.manifest
	javac -cp lib/dcpssaj5.jar -d classes src/*/*/*/*/*/*.java
	cd classes ; jar cvfm sensorlib.jar sensorlib.manifest org/*/*/*/*/*.class
	mv classes/sensorlib.jar sensorlib.jar

TempControl: ./idl/TempControl.idl
	mkdir -p src
	cd src ; idlpp -l java ../idl/TempControl.idl

sensorlib.manifest:
	mkdir -p classes
	echo Class-Path: lib/dcpssaj5.jar > classes/sensorlib.manifest

clean:
	$(RM) src classes sensorlib.jar
