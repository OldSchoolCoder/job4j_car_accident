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
    <title>Edit</title>
</head>

<body>
<div class="modal modal-signin position-static d-block bg-white py-5" tabindex="-1" role="dialog" id="modalSignin">
    <div class="modal-dialog" role="document">
        <div class="modal-content rounded-5 shadow text-center">
            <div class=" p-5 pb-4 border-bottom-0">
                <h2 class="fw-bold mb-0">Edit accident</h2>
            </div>
            <div class="modal-body p-5 pt-0">
                <form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
                    <div class="form-floating mb-3">
                        <input type="hidden" name="type.id" value="${accident.type.id}">
                        <input type="text" class="form-control rounded-4" id="floatingInput"
                               placeholder="name@example.com" name="name"
                               value="${accident.name}">
                        <label for="floatingInput">Name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control rounded-4" id="floatingPass"
                               placeholder="Password" name="text"
                               value="${accident.text}">
                        <label for="floatingPass">Text</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control rounded-4" id="floatingPassword"
                               placeholder="Password" name="address"
                               value="${accident.address}">
                        <label for="floatingPassword">Address</label>
                    </div>
                    <button class="w-100 mb-4 btn btn-lg rounded-4 text-white"
                            style="background-color: rgb(16, 194, 0);" type="submit">Update accident
                    </button>
                    <a href="/accident"
                       class="w-100 py-2 mb-2 btn btn-outline-success rounded-4">Go to main page</a>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>

</html>
