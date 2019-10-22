<%--
  Created by IntelliJ IDEA.
  User: Dao Van Tam
  Date: 5/4/2019
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/api/admin/category"></c:url>
<html>
<head>
    <title>Chỉnh sửa thể loại</title>
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
                            <label class="col-sm-3 control-label no-padding-right">Tên Thể Loại</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="title" name="name"
                                       value="${categoryModel.name}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="id" name="id" value="${categogyModel.id}"/>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty categoryModel.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Cập nhật thể loại"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                                <c:if test="${empty categoryModel.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Thêm mới thể loại"
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
            addCategory(data);
        } else {
            updateCategory(data);
        }
    });

    function addCategory(data) {
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

    function updateCategory(data) {
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
