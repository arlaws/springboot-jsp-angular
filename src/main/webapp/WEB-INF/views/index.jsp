<%-- 
    Document   : index
    Created on : Apr 19, 2023, 12:38:18 PM
    Author     : a.lawson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="${contextPath}/resources/assets/img/favicon.png" rel="shortcut icon"/>
        <title>Springboot-Jsp-Angular</title>

        <script type="text/javascript" src="${contextPath}/resources/assets/js/jquery.min.js"></script>
        <script type="text/javascript" src="${contextPath}/resources/assets/js/angular.js"></script>
        <link href="${contextPath}/resources/assets/css/toastr.css" rel="stylesheet" type="text/css"/>
        <script src="${contextPath}/resources/assets/js/script.js"></script>
        <script src="${contextPath}/resources/assets/js/toastr.js"></script>

    </head>
    <body ng-app="HomeManagement" ng-controller="HomeApiController" style="padding: 7%">
        <div id="defaul_page">
            <div style="width: 100%">
                <a href="" ng-click="hideAndShow('secondPage', '/MaSecondePage')" >Consulez que la liste</a>
            </div>
            <br/><br/>
            <div style="width: 70%; float: right">
                <table border="1" style="width: 100%" >
                    <tr>
                        <th>Id</th>
                        <th>Nom complet</th>
                        <th>Contact</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    <tr ng-repeat="user in users">
                        <td>{{$index + 1}}</td>
                        <td>{{user.nomPrenoms}}</td>
                        <td>{{user.contact}}</td>
                        <td>{{user.email}}</td>
                        <td>
                            <a href="" ng-click="loadUser(user)">Edit</button> 
                        </td>
                    </tr>
                </table>
            </div>
            <div style="width: 30%">
                <form ng-submit="addNewUser()" >
                    <input type="text" required="true" id="nom" ng-model="usersForm.nom"
                           placeholder="Nom" /> <br/><br/>

                    <input type="text" required="true" id="prenoms" ng-model="usersForm.prenoms"
                           placeholder="Prénoms" /> <br/><br/>

                    <input type="email" required="true" id="email" ng-model="usersForm.email"
                           placeholder="Email" /> <br/><br/>

                    <input type="text" required="true" id="contact" ng-model="usersForm.contact"
                           placeholder="Contact" /> <br/><br/>

                    <input
                        ng-model="usersForm.validated" type="checkbox" id="validated"/>
                    <label class="form-check-label" for="validated">
                        Validated
                    </label> <br/>

                    <input
                        ng-model="usersForm.activated" type="checkbox" id="activated"/>
                    <label class="form-check-label" for="activated">
                        Activated
                    </label> <br/><br/>
                    
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </form>
            </div>

            
        </div>
        <%@include file="includes/secondPage.jsp"%>
    </body>
</html>
