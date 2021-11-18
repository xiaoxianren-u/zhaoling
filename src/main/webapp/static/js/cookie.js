let lastTime = new Date().getTime();
let currentTime = new Date().getTime();
const timeOut = 25 * 60 * 1000; //设置超时时间： 15分
// const timeOut = 20 * 60 * 1000; //设置超时时间： 20分

$(function () {
    /* 鼠标移动事件 */
    $(document).mouseover(function () {
        lastTime = new Date().getTime(); //更新操作时间

    });
});

function toLoginPage() {
    currentTime = new Date().getTime(); //更新当前时间
    if (currentTime - lastTime > timeOut) { //判断是否超时
        $.cookie("to", '0');
        $.cookie("data", '0');
        // $.cookie("to1", '12','','/');
        document.cookie = 'to1=;path=/';
        sessionStorage.setItem("bool", 0);
        sessionStorage.setItem('cook', '1');
        console.log("===================================");
        console.log("===================================");
        console.log("===================================");
    }
}

/* 定时器
* 间隔1秒检测是否长时间未操作页面
*/
window.setInterval(toLoginPage, 1000);


