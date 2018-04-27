var websocket = null;
var floorplan = null;
var sensors = [];
OK = 0;
MAINTENANCE = 1;
CLOSED_COLOR = "green";
OPEN_COLOR = "red";
OFFLINE_COLOR = "silver";
HOVER_COLOR = "yellow";
DEFAULT_STROKE_COLOR = "black";

function init() {
    if ("WebSocket" in window) {
        // prepare the floorplan
        var floorplanObject = document.getElementById("floorplan");
        floorplan = floorplanObject.contentDocument;
        var windows = floorplan.getElementsByTagName("rect");
        for(var i = 0; i < windows.length; i++) {
            var aWindow = windows[i];
            aWindow.onclick = function() {
                updateSensorInfo(this);
            };

            aWindow.onmouseover = function() {
                this.setAttribute("stroke", HOVER_COLOR);
            };

            aWindow.onmouseout = function() {
                this.setAttribute("stroke", DEFAULT_STROKE_COLOR);
            };
        }

        var doors = floorplan.getElementsByTagName("path");
        for(var i = 0; i < doors.length; i++) {
            var aDoor = doors[i];
            aDoor.onclick = function() {
                updateSensorInfo(this);
            };

            aDoor.onmouseover = function() {
                this.setAttribute("stroke", HOVER_COLOR);
            };

            aDoor.onmouseout = function() {
                this.setAttribute("stroke", DEFAULT_STROKE_COLOR);
            };
        }

        // create the web socket
        websocket = new WebSocket('ws://localhost:8080/monitoring/socket/');

        // add handlers to the socket
        websocket.onmessage = function(data) {
            var sensor = JSON.parse(data.data);
            sensors[sensor.id] = sensor;
            var clickedSensor = document.getElementById("sensorId").innerHTML;
            if(clickedSensor == sensor.id) {
                updateSensorInfo(sensor);
            }

            var sensorDisplay = floorplan.getElementById(sensor.id);
            if(sensor.batteryPercentage >= 0) {
                if(sensor.isOpen && sensor.status == OK) {
                    sensorDisplay.setAttribute("fill", OPEN_COLOR);
                } else if(sensor.isOpen && sensor.status == MAINTENANCE){
                    sensorDisplay.setAttribute("fill", "url(./patterns.svg#open-maintenance)");
                } else if(!sensor.isOpen && sensor.status == OK) {
                    sensorDisplay.setAttribute("fill", CLOSED_COLOR);
                } else {
                    // sensor is closed and needs maintenance
                    sensorDisplay.setAttribute("fill", "url(./patterns.svg#closed-maintenance)");
                }
            } else {
                sensorDisplay.setAttribute("fill", OFFLINE_COLOR);
            }
        };

        websocket.onerror = function(e) {
            alert('An error occured, closing application');
            cleanUp();
        };

        websocket.onclose = function(data) {
            cleanUp();
            var reason = (data.reason && data.reason !== null) ? data.reason : 'Goodbye';
            alert(reason);
        };
    } else {
        alert("Websockets not supported");
    }
}

function cleanUp() {
    for(var id in sensors) {
        sensorDisplay = floorplan.getElementById(id);
        sensorDisplay.setAttribute("fill", OFFLINE_COLOR);
        sensorDisplay.onclick = "";
        sensorDisplay.onmouseover = "";
        sensorDisplay.onmouseout = "";
    }

    var idField = document.getElementById("sensorId");
    idField.innerHTML = "";

    var typeField = document.getElementById("sensorType");
    typeField.innerHTML = "";

    var isOpenField = document.getElementById("sensorOpen");
    isOpenField.innerHTML = "";
   
    var statusField = document.getElementById("sensorStatus");
    statusField.innerHTML = "";

    var batteryPercentageField = document.getElementById("sensorBatteryPercentage");
    batteryPercentageField.innerHTML = "";

    sensors = null;
    websocket = null;
    floorplan = null;
}

function updateSensorInfo(sensor) {
    var idField = document.getElementById("sensorId");
    idField.innerHTML = sensor.id
    var typeField = document.getElementById("sensorType");
    if(sensor.type == 1) {
        typeField.innerHTML = "Window";
    } else {
        typeField.innerHTML = "Door";
    }
    var isOpenField = document.getElementById("sensorOpen");
    isOpenField.innerHTML = sensor.isOpen;
    var statusField = document.getElementById("sensorStatus");
    if(sensor.status == 0) {
        statusField.innerHTML = "OK";
    } else {
        statusField.innerHTML = "Needs Maintenance";
    } 
    
    if (sensor.batteryPercentage < 0) {
        statusField.innerHTML = "Offline";
    }

    if(sensor.batteryPercentage >= 0) {
        var batteryPercentageField = document.getElementById("sensorBatteryPercentage");
        batteryPercentageField.innerHTML = sensor.batteryPercentage + "%";
    }
}

