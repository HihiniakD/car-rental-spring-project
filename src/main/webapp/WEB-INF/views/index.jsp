<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="maintitle"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>

<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-2">
                    <img src="https://cdn.worldvectorlogo.com/logos/rent-a-car-systeme.svg" alt="logo" width="100">
                </div>
                <div class="card shadow-lg">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4"><fmt:message key="greeting"/></h1>
                        <c:choose>
                            <c:when test="${requestScope.error != null}">
                                <div style="text-align: center;"><p class="text-danger fw-bold"><fmt:message key="${requestScope.error}"/></p></div>
                            </c:when>
                        </c:choose>
                        <form method="GET" action="${Path.SEARCH_CARS_PATH}">
                            <div class="mb-3">
                                <label class="mb-2 text-muted" > <fmt:message key="pickupcity"/></label>
                                <select name="city" class="form-select">
                                    <c:forEach var="city" items="${cities}">
                                        <option value="${city.id}"><fmt:message key="${city.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="mb-2 text-muted" > <fmt:message key="brand"/></label>
                                <select name="brand" class="form-select">
                                    <c:forEach var="brand" items="${brands}">
                                        <option value="${brand.id}">${brand.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label class="mb-2 text-muted" > <fmt:message key="category"/></label>
                                <select name="category" class="form-select">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.id}"><fmt:message key="${category.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted"><fmt:message key="pickupdate"/></label>
                                </div>
                                <input class="form-control" name="pickupDate" type="text" placeholder="<fmt:message key="dateformat"/> <showDate:dateTomorrowTag/>" required>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted"><fmt:message key="dropoffdate"/></label>
                                </div>
                                <input class="form-control" type="text" name="dropoffDate" aria-label="Type" placeholder="<fmt:message key="dateformat"/> <showDate:dateInFutureTag/>" required>
                            </div>

                            <div class="d-flex align-items-center">
                                <button type="submit" class="btn btn-success ms-auto">
                                    <fmt:message key="search"/>
                                </button>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="text-center mt-5 text-muted">
                    <fmt:message key="copyright"/>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
