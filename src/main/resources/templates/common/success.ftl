<html>
<head>
    <meta charset="utf-8">
    <title>提示界面</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功！
                </h4> <strong>${msg!""}</strong> Best check yo self, you're not looking too good.<a href="${url}" class="alert-link">3秒之后自动跳转</a>
            </div>

        </div>
    </div>
</div>

</body>
<script>
    setTimeout('location.href="${url}"',3000);
</script>
</html>