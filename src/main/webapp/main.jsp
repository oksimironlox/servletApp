<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.util.Date" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<h1>${name}</h1>
    <body>
    <form method="POST" action="/my-app/servlet">
    <style>
        a.split {
            float: right;
        }
    </style>
    <h4>
        <a class="split">
            <form method="POST" action="/servlet">
                <button name="btnExit" type="submit">Exit</button>
            </form>
        </a>
    </h4>
    </form>
        <table>
            <tr>
                <td>File</td>
                <td>Size</td>
                <td>Date</td>
            </tr>
            <tr>
                <td>
                    <form method="POST" action="/my-app/servlet">
                        <button name="btnBack" type="submit" >
                            ...
                        </button>
                    </form>
                </td>
            </tr>
            <c:forEach var="file" items="${files}">
                <tr>
                    <td>
                        <form method="POST" action="/my-app/servlet">
                            <button name="btn" type="submit" value="${file.getPath()}">
                                ${file.getName()}
                            </button>
                        </form>
                    </td>
                    <td>
                        ${file.length()}
                    </td>
                    <td>
                       ${Date(file.lastModified())}
                    </td>
                    <td>
                        <form method="POST" action="/my-app/download">
                            <button name="btnDownload" type="submit" value="${file.getPath()}">
                                Download
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>