$(document).ready(function(){
    $('#search-form').submit(function(event){
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
    });
});

function fire_ajax_submit(){
    var search = {};
    search["name"] = $('#name').val();
    $("#btn-search").prop("disabled", true);
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "/users/apishow",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data){
            // var json = "<h4>Ajax Response</h4><pre>"
            //     + JSON.stringify(data, null, 4) + "</pre>";
            // $('#feedback').html(json);
            // console.log("SUCCESS : ", data);
            // $("#btn-search").prop("disabled", false);
            var i,x = "<table border=\"1\" style=\"width: 300px;\">"+
                "<tr>" +
                "<th>ID</th><th>Age</th><th>Name</th>" +
                "</tr>";
            // var json = JSON.parse(data);
            for (i in data.result) {
                x += "<tr>" + "<th>"+data.result[i].id+"</th>"+"<th>"+data.result[i].age+"</th>"+"<th>"+data.result[i].name+"</th>"+ "</tr>";
            }
            x+="</table>";
            document.getElementById("feedback").innerHTML = x;
        },
        error: function(e){
            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    })
}