<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<head>
    <meta charset="ISO-8859-1">

    <title>View Person List</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        a{
            color: white;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="p-3">People Administrator</h1>

        <a href="/personCreate" class="btn btn-primary btn-lg" role="buton">Add Person</a>
        <hr/>
        <form:form>

            <table class="table table-bordered">
            	<tr>
            		<th>ID</th>
            		<th>Name</th>
            		<th>Age</th>
            		<th>Address</th>
            		<th>Edit</th>
            		<th>Delete</th>
            	</tr>

            	<c:forEach var="person" items="${people}">
                    <tr>
                		<td>${person.id}</td>
                		<td>${person.name}</td>
                		<td>${person.age}</td>
                		<td>${person.address}</td>
                		<td><button type="button" class="btn btn-primary">
                		    <a href="/personEdit/${person.id}">Edit</a>
                		</button></td>
                		<td><button type="button" class="btn btn-danger">
                			<a href="/personDelete/${person.id}">Delete</a>
                		</button></td>
                	</tr>
            	</c:forEach>
            </table>
        </form:form>
    </div>

    <script th:inline="javascript">
        window.onload = function() {

            var msg = "${message}";

            if (msg == "Save Success") {
                Command: toastr["success"]("Person added successfully!!")
            } else if (msg == "Delete Success") {
                Command: toastr["success"]("Person deleted successfully!!")
            } else if (msg == "Delete Failure") {
                Command: toastr["error"]("Some error occurred, couldn't delete person")
            } else if (msg == "Edit Success") {
                Command: toastr["success"]("Person updated successfully!!")
            }

            toastr.options = {
              "closeButton": true,
              "debug": false,
              "newestOnTop": false,
              "progressBar": true,
              "positionClass": "toast-top-right",
              "preventDuplicates": false,
              "showDuration": "300",
              "hideDuration": "1000",
              "timeOut": "5000",
              "extendedTimeOut": "1000",
              "showEasing": "swing",
              "hideEasing": "linear",
              "showMethod": "fadeIn",
              "hideMethod": "fadeOut"
            }
        }
    </script>
</body>
</html>