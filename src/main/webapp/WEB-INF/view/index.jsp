<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Collection" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Main page</title>
</head>

<body class="text-center" style="padding-top: 4.5rem; min-height: 75rem;">
<nav class="navbar navbar-light bg-light rounded navbar-expand-md fixed-top">
    <div class="container-fluid text-white justify-content-md-center">
        <a href="http://localhost:8080/accident/create"
           class="w-50 py-2 mb-2 btn btn-outline-success rounded-4">Add accident</a>
    </div>
</nav>
<div class="container pt-3">
    <div class="card mb-3" style="border-color: rgb(2, 209, 37);">
        <div class="card-header fw-light text-white" style="background-color: rgb(16, 194, 0);">
            All accidents
        </div>
        <div class="card-body fw-light">
            <table class="table border-light">
                <thead class="table-light">
                <tr>
                    <th>№</th>
                    <th>Name</th>
                    <th>Text</th>
                    <th>Adress</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${accidents}" var="accident">
                    <tr>
                        <td>
                            <c:out value="${accident.getId()}"/>
                        </td>
                        <td>
                            <c:out value="${accident.getName()}"/>
                        </td>
                        <td>
                            <c:out value="${accident.getText()}"/>
                        </td>
                        <td>
                            <c:out value="${accident.getAddress()}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>

</html>