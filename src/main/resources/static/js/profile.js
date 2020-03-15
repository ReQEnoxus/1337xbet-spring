$(document).ready(function () {
    checkCollapsed();
    $('#accountEditForm').submit(function (event) {
        if (document.getElementById('accountEditForm').checkValidity() === false) {
            event.preventDefault();
        }
        else {
            $('#accountModal').modal('hide');
        }
        document.getElementById('accountEditForm').classList.add('was-validated');
    });
    $(window).on('hashchange', function (e) {
        checkCollapsed();
    });
});

function checkCollapsed() {
    var currentUrl = window.location.href;
    if (currentUrl.includes('#trips')) {
        $('#trips').collapse("show");
    }
    else {
        $('#trips').collapse("hide");
    }
}
