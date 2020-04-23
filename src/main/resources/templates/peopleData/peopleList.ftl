<html>
<#include "../common/header.ftl">

<script type="text/javascript" language="javascript" src="/hyxinfo/js/peopleJs.js"></script>
<body>
<div id="wrapper" class="toggled">
    <#--添加侧边栏-->
    <#include "../common/adminnav.ftl">
    <#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>电话</th>
                            <th>车牌号</th>
                            <th>密码</th>
                            <th colspan="2" align="center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list peopleInfoPage.content as peopleinfo>
                            <tr>
                                <td>${(peopleinfo.username)!''}</td>
                                <td>${(peopleinfo.phoneNumber)!''}</td>
                                <td>${(peopleinfo.signs)!''}</td>
                                <td>${(peopleinfo.passwords)!''}</td>
                                <td><a href="/hyxinfo/people/index?personId=${(peopleinfo.personId)!''}">修改</a></td>
                                <td><a onclick='return confirmAct();' href="/hyxinfo/people/delete?personId=${(peopleinfo.personId)!''}">删除</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#--分页的内容-->

            </div>
        </div>
    </div>

</div>

</body>

</html>