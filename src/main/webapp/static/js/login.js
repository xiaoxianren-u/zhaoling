layui.use('layer', function () {
    $("#btn").click(function () {


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
            success: function (sre) {
                console.log(sre);

                if (sre.bool) {
                    window.sessionStorage.setItem('token', sre.token);
                    document.cookie = sre.token;
                    // window.location = "/";
                    window.location = "/sys/index.action";
                    layer.msg("n-----------------");
                } else {
                    layer.msg("noNONONONOONNONONO");
                }

            }

        })
    })
})