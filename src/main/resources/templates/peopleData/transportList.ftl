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
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>货运单号</th>
                            <th>姓名</th>
                            <th>车牌号</th>
                            <th>载重(吨)</th>
                            <th>货运地址</th>
                            <th>时间</th>
                            <th colspan="2" align="center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list empDataPage.content as empTD>
                            <tr>
                                <td>${empTD.billId}</td>
                                <td>${empTD.username}</td>
                                <td>${empTD.signs}</td>
                                <td>${empTD.weights}</td>
                                <td>${empTD.address}</td>
                                <td>${(empTD.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                <td><a href="/hyxinfo/people/indexList?billId=${empTD.billId}">修改</a></td>
                                <td><a onclick='return confirmAct();' href="/hyxinfo/people/deleteList?billId=${empTD.billId}">删除</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页内容-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if empPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/hyxinfo/people/transportList?page=${empPage - 1}&size=10">上一页</a></li>
                        </#if>
                        <#list 1..empDataPage.getTotalPages() as index>
                            <#if empPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li><a href="/hyxinfo/people/transportList?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if empPage gte empDataPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/hyxinfo/people/transportList?page=${empPage + 1}&size=10">下一页</a></li>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>

</div>

</body>
</html>