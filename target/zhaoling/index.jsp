<%--
  Created by IntelliJ IDEA.
  User: yangzhan
  Date: 2021/9/15
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <!-- 头部区域 -->
        
        <ul class="layui-nav" lay-filter="layadmin-layout-right" style="background-color: #393D49">
            
            
            <div class="layui-row" style="background-color: #393D49">
                <div class="layui-col-xs1">
                    <div class="grid-demo grid-demo-bg1">
                        s
                    </div>
                </div>
                <div class="layui-col-xs10">
                    <div class="grid-demo">
                        <li class="layui-nav-item layadmin-flexible kit-side-fold" lay-unselect>
                            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                                <i class="layui-icon layui-icon-shrink-right" style="color: white" >&nbsp;&nbsp;ssssss</i>
                            </a>
                        </li>
                    </div>
                </div>
                <div class="layui-col-xs1">
                    <div class="grid-demo">
                        <li class="layui-nav-item" lay-unselect>
                            <a href="javascript:;">
                                <img th:src="@{/static/image/OIP5689B8RQ.jpg}" height="20" width="20"/>
                                <cite  style="color: white">贤心</cite>
                            </a>
                            <dl class="layui-nav-child"  style="color: white">
                                <dd><a >基本资料</a></dd>
                                <dd><a >修改密码</a></dd>
                                <hr>
                                <dd><a>退出</a></dd>
                            </dl>
                        </li>
                    </div>
                </div>
            </div>
        
        </ul>
    </div>
    
    
    <!--         侧边菜单-->
    <div class="layui-side layui-side-menu layui-bg-cyan sh">
        <div class="layui-side-scroll" lay-filter="test">
            <div class="layui-logo" style="background-color: #393D49">
                <i><span>layuiAdmin</span></i>
            </div>
            
            <ul class="layui-nav layui-nav-tree" >
                
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;" lay-tips="主页" lay-direction="2"><i class="layui-icon layui-icon-home"></i><span><cite>主页</cite></span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="https://www.nowcoder.com/index"><i class="fa fa-list fa-lg"><span>sdfd</span></i></a></dd>
                        <dd><a href="https://gitee.com/"><i class="fa fa-clipboard fa-lg"><span>sdfd</span></i> </a> </dd>
                        <dd><a href="/login/login/denglu"><i class="fa fa-file-text fa-lg"><span>sdfd</span></i></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    
    <div class="layui-body">
        <div class="layadmin-tabsbody-item layui-show">
            <iframe src="" frameborder="0" align="top" style="margin: 0;padding: 0;" class="layadmin-iframe" id="iframe" sandbox="allow-same-origin allow-scripts allow-forms"></iframe>
        </div>
    </div>
</div>

</body>
</html>