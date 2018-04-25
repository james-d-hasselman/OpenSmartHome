var websocket = null;
var floorplan = null;
OK = 0;
MAINTENANCE = 1;

function init() {
    if ("WebSocket" in window) {
        // prepare the floorplan
        var floorplanObject = document.getElementById("floorplan");
        floorplan = floorplanObject.contentDocument;

        // create the web socket
        websocket = new WebSocket('ws://localhost:8080/monitoring/socket/1');

        // add handlers to the socket
        websocket.onmessage = function(data) {
            var sensor = JSON.parse(data.data);
            console.log(sensor);
            var sensorDisplay = floorplan.getElementById(sensor.id);
            if(sensor.isOpen && sensor.status == OK) {
                sensorDisplay.setAttribute("fill", "red");
            } else if(sensor.isOpen && sensor.status == MAINTENANCE){
                sensorDisplay.setAttribute("fill", "url(./test.svg#open-maintenance)");
            } else if(!sensor.isOpen && sensor.status == OK) {
                sensorDisplay.setAttribute("fill", "green");
            } else {
                // sensor is closed and needs maintenance
                sensorDisplay.setAttribute("fill", "url(./test.svg#closed-maintenance)");
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
    websocket = null;
    floorplan = null;
}

