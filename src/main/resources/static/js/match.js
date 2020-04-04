$(document).ready(function () {
    $('#Bet').submit(function (event) {
        if (document.getElementById('Bet').checkValidity() === false) {
            event.preventDefault();
            if (document.querySelector('input[name="prediction"]:checked') == null) {
                document.getElementById('predictionInvalid').style.display = "block";
            }
            else {
                document.getElementById('predictionInvalid').style.display = "none"
            }
        }
        else {
            var url = new URL(window.location.href);
            var params = [
                {
                    name: "matchId",
                    value: url.searchParams.get('id')
                },
                {
                    name: "prediction",
                    value: document.querySelector('input[name="prediction"]:checked').value
                }
            ];
            $.each(params, function (i, param) {
                $('<input />').attr('type', 'hidden')
                    .attr('name', param.name)
                    .attr('value', param.value)
                    .appendTo('#Bet');
            });
            return true;
        }
        document.getElementById('Bet').classList.add('was-validated');
    });

    $('#Comment').submit(function (event) {
        if (document.getElementById('Comment').checkValidity() === false) {
            event.preventDefault();
        }
        else {
            var url = new URL(window.location.href);
            var params = [
                {
                    name: "matchId",
                    value: url.searchParams.get('id')
                }
            ];
            $.each(params, function (i, param) {
                $('<input />').attr('type', 'hidden')
                    .attr('name', param.name)
                    .attr('value', param.value)
                    .appendTo('#Comment');
            });
            return true;
        }
        document.getElementById('Comment').classList.add('was-validated');
    });
    $('.reply-form').submit(function (event) {
        if (this.checkValidity() === false) {
            event.preventDefault();
        }
        else {
            var url = new URL(window.location.href);
            var params = [
                {
                    name: "matchId",
                    value: url.searchParams.get('id')
                },
                {
                    name: "parentId",
                    value: this.id.replace(/^\D+/g, '')
                }
            ];
            var currentId = this.id;
            $.each(params, function (i, param) {
                $('<input />').attr('type', 'hidden')
                    .attr('name', param.name)
                    .attr('value', param.value)
                    .appendTo('#' + currentId);
            });
            return true;
        }
        this.classList.add('was-validated');
    });
});
