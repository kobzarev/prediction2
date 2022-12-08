$(document).ready(function() {
    $("#data > tbody").empty();
    $("#data > thead").empty();

    $('#data').append(
        $('<tr id="head">')
            .append($('<th>').append("Дата"))
            .append($('<th>').append("Матч"))
            .append($('<th>').append("Результат"))
    );

    $.getJSON("/table/data", function (data) {
        var i, j, k;

        for (i = 0; i < data.users.length; i++) {
            $('#head').append(
                $('<td>').append(data.users[i].nickname)
            );
        }

        for (i = 0; i < data.stages.length; i++) {
            $('#data > tbody:last-child').append(
                $('<tr>').append($('<th colspan="5">').append(data.stages[i].stage))
            );

            for(j = 0; j < data.stages[i].matches.length; j++) {
                $('#data > tbody:last-child').append(
                    $('<tr>')
                        .attr("id", "match" + data.stages[i].matches[j].id)
                        .append($('<td>').append(data.stages[i].matches[j].startedAt))
                        .append($('<td>').append(data.stages[i].matches[j].match))
                        .append($('<td>').append(data.stages[i].matches[j].result))
                );
                for (k = 0; k < data.stages[i].matches[j].predictions.length; k++) {
                    var bgcolor;
                    if (data.stages[i].matches[j].predictions[k].points === 3) {
                        bgcolor = '#0596C8';
                    } else if (data.stages[i].matches[j].predictions[k].points === 2) {
                        bgcolor = '#69A028';
                    }  else if (data.stages[i].matches[j].predictions[k].points === 1) {
                        bgcolor = '#BEC864';
                    }
                    $('#match' + data.stages[i].matches[j].id).append(
                        $('<td>')
                            .css("background-color", bgcolor)
                            .append(data.stages[i].matches[j].predictions[k].result)
                    )
                }
            }
        }
    });
});