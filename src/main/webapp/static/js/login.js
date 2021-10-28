function login() {
    let username = $("#username").val();
    let password = $("#password").val();
    console.log(username);
    console.log(password);
    const yan = '!a@n%p&';
    password = md5(yan + password + yan);
    console.log(password);
    $.ajax({
        type: "POST",
        url: "/login",
        async: true,
        data: JSON.stringify({user_name: username, pass_word: password, status: "1"}),
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success: function (src) {
            console.log(src);

            if (src.bool) {
                document.cookie = src.token;
                window.location = "/sys/index.action";
            } else {
                layer.msg(src.msg);
            }

        }

    })
}