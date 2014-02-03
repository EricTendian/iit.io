// high-performance modern Scarlet Hawk athlete detection technology

function appendResults(text) {
    var elem = document.getElementById('wait');
    elem.parentNode.removeChild(elem);
    var elem = document.getElementById('ans');
    elem.innerHTML = text;
}

function makeRequest() {
    var d = new Date();
    var t1 = d.toISOString();
    d.setSeconds(d.getSeconds() + 1);
    var t2 = d.toISOString();
    var request = gapi.client.calendar.freebusy.query({
     "timeZone": "America/Chicago",
     "items": [
      {
       "id": "iit.edu_qo3v87oteadr7jr3dsk6gqhgmk@group.calendar.google.com"
      }
     ],
     "timeMin": t1,
     "timeMax": t2
    });
    request.execute(function(response) {
        if (response.calendars) appendResults('YES');
        else appendResults('NO');
        //appendResults('MTCC');
    });
}

function load() {
    gapi.client.setApiKey('AIzaSyDKf0etDDpk0jStsehtRX3TSQaK8pU98mY');
    gapi.client.load('calendar', 'v3', makeRequest);
}