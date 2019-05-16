$(document).ready(function(){
  $("button[name='PostButton']").click(function(){
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/test/SaveName',
      data: { 
        'name': 'Chandra', 
        'surName': 'Krintz' 
      },
      error: function() {
        alert("SaveName Error");
      },
      success: function(){ //server should have posted/printed 'name:Chandra surname:Krintz'
        alert('Success!'); //popup 
      }
    });
  });
  /////////////////////////////////////////////////
  $("button[name='PostArrayButton']").click(function(){
    var data_array = [{
      "key": "myKey",
      "keyName": "myKeyName",
      "value": "value",
      "valueName": "valueName"
    },
    {
      "key": "mySecondKey",
      "keyName": "mySecondKeyName",
      "value": "secondValue",
      "valueName": "secondValueName"
    }
    ];
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/test/SaveArray',
      data: JSON.stringify(data_array),
      contentType: "application/json; charset=utf-8", //send type
      dataType: "json", //return type
      error: function(jqXHR, exception) {
        var msg = '';
        if (jqXHR.status === 0) {
            msg = 'Not connect.\n Verify Network.';
        } else if (jqXHR.status == 404) {
            msg = 'Requested page not found. [404]';
        } else if (jqXHR.status == 500) {
            msg = 'Internal Server Error [500].';
        } else if (exception === 'parsererror') {
            msg = 'Requested JSON parse failed.';
        } else if (exception === 'timeout') {
            msg = 'Timeout error.';
        } else if (exception === 'abort') {
            msg = 'Ajax request aborted.';
        } else {
            msg = 'Uncaught Error.\n' + jqXHR.responseText;
        }
        alert("SaveArray Error: "+jqXHR.status + " "  +msg);
      },
      success: function(){ //server should have posted/printed the array
        alert('Success!'); //popup 
      }
    });
  });
  /////////////////////////////////////////////////
  var ourInfo = "flour:egg:salt";
  $("button[name='GetButton']").click(function(){
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/test/GetName',
      data: {  //send data here or remove this entire block
        'ID': ourInfo, 
      },
      dataType: 'json', //return type
      error: function() {
        alert("GetName Error");
      },
      success: function(data){ 
        var items = [];
        $.each( data, function( key, val ) {
        items.push( "<li>" + key + ":" + val + "</li>" );
        });
        $( "<ul/>", {
          "class": "data_list",
          html: items.join( "" )
        }).appendTo( "body" );
      }
    });
  });
});

