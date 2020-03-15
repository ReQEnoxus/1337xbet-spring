$(document).ready(function () {
    $('#passwordAgain').on('input', function () {
            if (document.getElementById('password').value != document.getElementById('passwordAgain').value) {
                document.getElementById('passwordAgain').setCustomValidity('Пароли не совпадают');
                return false;
            }
            else {
                document.getElementById('passwordAgain').setCustomValidity('');
                return true;
            }
        }
    );
    $('#registerForm').submit(function (event) {
        if (document.getElementById('registerForm').checkValidity() === false || passwordAgainValid() === false) {
            event.preventDefault();
        }
        document.getElementById('registerForm').classList.add('was-validated');
    });
});

