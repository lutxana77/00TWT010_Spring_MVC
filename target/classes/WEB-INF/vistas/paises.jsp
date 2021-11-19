
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet"
              href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <title>Products</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1> <spring:message code="app.paises" /> </h1>
                    <p>Todos los paises </p>
                    
                </div>
            </div>
        </section>

        <section class="container">
            <div class="row">
                <c:forEach items="${paises}" var="pais">
                    <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                        <div  class="thumbnail" style="background-color: beige">
                            <div class="caption">
                                <h2>${pais.countryName}</h2>
                                <p>${pais.countryId}</p>
                                <p>
                               
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

    </body>
</html>
