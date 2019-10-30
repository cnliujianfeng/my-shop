<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>

    <title>我的商城|用户管理</title>
    <jsp:include page="../includes/head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible" ${baseResult==null?"style='display: none;'" : ""}>
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${baseResult.message}
                    </div>

                    <div class="box box-info box-info-search" style="display: none">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form action="/user/search" cssClass="form-horizontal" method="post"
                                   modelAttribute="tbUser">
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="username" class="col-sm-4 control-label">姓名</label>
                                            <div class="col-sm-8">
                                                <form:input path="username" cssClass="form-control"
                                                            placeholder="姓名"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="email" class="col-sm-4 control-label">邮箱</label>
                                            <div class="col-sm-8">
                                                <form:input path="email" cssClass="form-control" placeholder="邮箱"/>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-4 control-label">手机</label>
                                            <div class="col-sm-8">
                                                <form:input path="phone" cssClass="form-control" placeholder="手机"/>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">

                                <button type="submit" class="btn btn-info pull-right">搜索</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>

                            <div class="row" style="padding-top: 10px">
                                <div class="col-xs-12">
                                    <a href="/user/form" type="button" class="btn  btn-default btn-sm"><i
                                            class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn  btn-default btn-sm" onclick="deleteMutil()"><i
                                            class="fa fa-trash-o"></i>
                                        删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn  btn-default btn-sm"><i
                                            class="fa fa-download"></i>
                                        导入</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn  btn-default btn-sm"><i
                                            class="fa fa-upload"></i>
                                        导出</a>&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn  btn-primary btn-sm"
                                            onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')">
                                        <i
                                                class="fa fa-search"></i>
                                        搜索
                                    </button>

                                </div>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbUsers}" var="tbUsers">
                                    <tr>
                                        <td><input id="${tbUsers.id}" type="checkbox" class="minimal"></td>
                                        <td>${tbUsers.id}</td>
                                        <td>${tbUsers.username}</td>
                                        <td>${tbUsers.phone}</td>
                                        <td>${tbUsers.email}</td>
                                        <td><fmt:formatDate value="${tbUsers.updated}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <a href="#" type="button" class="btn  btn-default btn-sm"><i
                                                    class="fa fa-search"></i> 查看</a>
                                            <a href="#" type="button" class="btn  btn-primary btn-sm"><i
                                                    class="fa fa-edit"></i> 编辑</a>
                                            <a href="#" type="button" class="btn  btn-danger btn-sm"><i
                                                    class="fa fa-trash-o"></i> 删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>


    </div>
    <jsp:include page="../includes/copyright.jsp"/>

</div>
<jsp:include page="../includes/footer.jsp"/>

<sys:modal message="第一个模态框" opts="confirm" url="/user/delete"/>


<script>



    function deleteMutil() {

        //定义一个存放id的数组
        var idArray = new Array();
        //将选择元素的id放入数组中
        var _checkbox = App.getChechbox();
        _checkbox.each(function () {
            var _id = $(this).attr('id');

            if (_id != null && _id != 'undefine' && $(this).is(':checked')) {
                idArray.push(_id);
            }
        });

        if(idArray.length===0){
            $('#modal-default').modal('show');
        }
    }
</script>

</body>
</html>