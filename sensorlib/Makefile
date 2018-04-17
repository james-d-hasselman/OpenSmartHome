RM = rm -rf

sensors: ./idl/sensors.idl
	mkdir -p src/main/java
	cd src/main/java ; idlpp -l java ../../../idl/sensors.idl
	mvn package

clean:
	mvn clean
	$(RM) src classes sensorlib.jar
