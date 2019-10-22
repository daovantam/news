<%--
  Created by IntelliJ IDEA.
  User: Dao Van Tam
  Date: 5/4/2019
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/api/admin/role"></c:url>
<html>
<head>
    <title>Chỉnh sửa quyền</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa bài viết</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                                ${message}
                        </div>
                    </c:if>
                    <form id="formUpdateOrCreate">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên quyền</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="name"
                                       value="${roleModel.roleName}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="id" name="id" value="${roleModel.roleId}"/>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty roleModel.roleId}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Cập nhật quyền"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                                <c:if test="${empty roleModel.roleId}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Thêm mới quyền"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#btnAddOrUpdate').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formUpdateOrCreate').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });

        var id = $('#id').val();
        if (id == '') {
            addRole(data);
        } else {
            updateRole(data);
        }
    });

    function addRole(data) {
        $.ajax({
            url: ('${ApiUrl}'),
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // bien du lieu mang ve JSON
            dataType: 'html',
            success: function () {
                window.location.href = 'http://localhost:8080/admin/category';
            },
            error: function () {

            }
        });
    }

    function updateRole(data) {
        $.ajax({
            url: ('${ApiUrl}'),
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'html',
            success: function () {
                window.location.href = 'http://localhost:8080/admin/category';
            },
            error: function () {

            }

        })
    }

</script>
</body>
</html>
