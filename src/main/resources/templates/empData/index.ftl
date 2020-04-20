<html>
<#include "../common/header.ftl">

<#--设置日期控件-->
<script type="text/javascript">
    $(function(){
        $("[name='createTime']").datetimepicker({
            forceParse: 0,//设置为0，时间不会跳转1899，会显示当前时间。
            language: 'zh-CN',//显示中文
            format: 'yyyy-mm-dd hh:ii:ss',//显示格式
            minView: "month",//设置只显示到月份
            initialDate: new Date(),//初始化当前日期
            autoclose: true,//选中自动关闭
            todayBtn: true//显示今日按钮
        });
    });
    $("[name='createTime']").datetimepicker("setDate", new Date() );  //设置显示默认当天的时间     $("[name='datetimepicker']")
</script>

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
                        <input type="text" style="display:none;" name="billId" value="${(empData.billId)!''}">
                        <div class="form-group">
                            <label >载重(吨)</label>
                            <input type="number" step="0.01" class="form-control" name="weights" value="${(empData.weights)!''}" />
                        </div>
                        <div class="form-group">
                            <label >货运地址</label>
                            <input type="text" class="form-control" name="address" value="${(empData.address)!''}" />
                        </div>
                        <#if (empData.billId)??>
                            <input type="text" style="display:none;" name="createTime" value="${(empData.createTime?string("yyyy-MM-dd HH:mm:ss"))!''}">
                          <#else >
                            <div class="col-sm-2 col-lg-offset-1">
                                <label >货运日期</label>
                                <input type="text" class="form-control" name="createTime" value=" "  />
                            </div>
                        </#if>
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