<html>
<#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">
    <#--添加侧边栏-->
    <#include "../common/nav.ftl">
    <#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form"  method="post" action="/hyxinfo/emp/data/update">
                        <input type="number" style="display:none;" name="personId" value="${(peopleInfo.personId)!''}">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="username"  value="${(peopleInfo.username)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>电话</label>
                            <input type="text" class="form-control" name="phoneNumber" value="${(peopleInfo.phoneNumber)!''}" />
                        </div>
                        <div class="form-group">
                            <label>车牌号</label>
                            <input type="text" class="form-control" name="signs" value="${(peopleInfo.signs)!''}" />
                        </div>
                        <div class="form-group">
                            <label>密码</label>
                            <input type="text" class="form-control" name="passwords"  value="${(peopleInfo.passwords)!''}"/>
                        </div>
                        <div class="col-md-12 ">
                            <button type="submit" class="btn btn-default">提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>



</body>

</html>