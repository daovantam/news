<%--
  Created by IntelliJ IDEA.
  User: Dao Van Tam
  Date: 5/4/2019
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="ApiUrl" value="/api/admin/user"></c:url>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
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
                <li class="active">Chỉnh sửa người dùng</li>
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
                            <label class="col-sm-3 control-label no-padding-right">Quyền</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="code" name="roleId">
                                    <c:forEach items="${roles}" var="role">
                                        <c:if test="${role.id == userModel.roleId}">
                                            <option value="${role.id}"
                                                    selected='selected'
                                            >
                                                    ${role.roleName}
                                            </option>
                                        </c:if>
                                        <c:if test="${userModel.roleId != role.id}">
                                            <option value="${role.id}">${role.roleName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên người dùng</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="userName" name="userName"
                                       value="${userModel.userName}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Mật khẩu</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="password" name="password"
                                       value="${userModel.password}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Họ đệm</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="firstName" name="firstName"
                                       value="${userModel.firstName}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Tên</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="" name="lastName"
                                       value="${userModel.lastName}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Ngày</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="createdDate" name="createdDate"
                                       value="${userModel.createdDate}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Email</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${userModel.email}"/>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Giới tính</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="sex" name="sex"
                                       value="${userModel.sex}"/>
                            </div>
                        </div>
                        <div class="form-group">

                            <input type="hidden" class="form-control" id="id" name="id" value="${userModel.id}"/>

                        </div>
                        <br>
                        <br>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <c:if test="${not empty userModel.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Cập nhật người dùng"
                                           id="btnAddOrUpdate"/>
                                </c:if>
                                <c:if test="${empty userModel.id}">
                                    <input type="button" class="btn btn-white btn-warning btn-bold"
                                           value="Thêm mới người dùng"
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
            addUser(data);
        } else {
            updateUser(data);
        }
    });

    function addUser(data) {
        $.ajax({
            url: ('${ApiUrl}'),
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data), // bien du lieu mang ve JSON
            dataType: 'html',
            success: function () {
                window.location.href = 'http://localhost:8080/admin/user';
            },
            error: function () {
                // window.location.href = 'http://localhost:8080/404';

            }
        });
    }

    function updateUser(data) {
        $.ajax({
            url: ('${ApiUrl}'),
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'html',
            success: function () {
                window.location.href = 'http://localhost:8080/admin/user';
            },
            error: function () {
                // window.location.href = 'http://localhost:8080/404';

            }

        })
    }

</script>
</body>
</html>
