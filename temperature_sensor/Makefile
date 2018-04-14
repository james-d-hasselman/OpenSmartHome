all: TemperatureSensor.class temperature_sensor.manifest
	jar cvfm temperature_sensor.jar classes/temperature_sensor.manifest classes/*/*/*/*/*.class

TemperatureSensor.class:
	mkdir -p classes
	javac -cp lib/sensorlib.jar:lib/dcpssaj5.jar -d classes src/*/*/*/*/*.java

temperature_sensor.manifest:
	echo Class-Path: lib > classes/temperature_sensor.manifest
	echo Main-Class: src/*/*/*/*/TemperatureSensor.java >> classes/temperature_sensor.manifest

