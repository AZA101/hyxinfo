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
                    <form role="form" method="post" action="/hyxinfo/emp/data/save" >
                        <div class="form-group">
                            <label >载重(吨)</label>
                            <input type="number" step="0.01" class="form-control" name="weights" value="${(empData.weights)!''}" />
                        </div>
                        <div class="form-group">
                            <label >货运地址</label>
                            <input type="text" class="form-control" name="address" value="${(empData.address)!''}" />
                        </div>
                        <input hidden type="text" name="billId" value="${(empData.billId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>



</body>

</html>