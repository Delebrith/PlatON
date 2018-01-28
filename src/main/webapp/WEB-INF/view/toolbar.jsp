<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="toolbar">
    <nav class="navbar navbar-toggleable-md navbar-inverse bg-primary">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="#"><h2 style="padding-left: 25%; letter-spacing: 2px; font-weight: bold;">PlatON</h2></a>
        <div class="collapse navbar-collapse  justify-content-end" id="navbarNavDropdown" style='float: right'>
            <ul class="navbar-nav" >
                <li class="nav-item active" >
                    <a class="nav-link dropdown btn btn-primary" href="/home">
                        <div class='fa fa-home'></div>
                        Strona domowa<span class="sr-only">(current)</span>
                    </a>
                </li>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="AdminDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Administrator
                    </a>
                    <div class="dropdown-menu" aria-labelledby="AdminDropdownMenuLink">
                        <a class="dropdown-item" href="/admin/addUser" method="GET">Dodaj użytkownika</a>
                        <a class="dropdown-item" href="/admin/accountManagement">Zarządzaj kontami</a>
                        <a class="dropdown-item" href="#">Utwórz powiadomienie</a>
                        <a class="dropdown-item" href="/admin/reports">Raporty</a>
                    </div>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_AUTHORITY')">
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="AuthorityDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Władze
                    </a>
                    <div class="dropdown-menu" aria-labelledby="AuthorityDropdownMenuLink">
                        <a class="dropdown-item" href="#">Zapytania oczekujące</a>
                    </div>
                    </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('ROLE_OFFICE', 'ROLE_AUTHORITY')">
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="AdministrationDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Administracja
                    </a>
                    <div class="dropdown-menu" aria-labelledby="AdministrationDropdownMenuLink">
                        <a class="dropdown-item" href="/office/changeEnrollments">Zapisy na przedmioty</a>
                        <a class="dropdown-item" href="#">Harmonogramy</a>
                        <a class="dropdown-item" href="#">Opłaty i stypendia</a>
                        <a class="dropdown-item" href="#">Przedmioty</a>
                        <a class="dropdown-item" href="#">Rygory studiów</a>
                        <a class="dropdown-item" href="#">Kierunki</a>
                        <a class="dropdown-item" href="#">Wyniki studentów</a>
                    </div>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_TEACHER')">
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="TeacherDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Wykładowca
                    </a>
                    <div class="dropdown-menu" aria-labelledby="TeacherDropdownMenuLink">
                        <a class="dropdown-item" href="#">Moje przedmioty</a>
                    </div>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_STUDENT')">
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="StudentDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Student
                    </a>
                    <div class="dropdown-menu" aria-labelledby="StudentDropdownMenuLink">
                        <a class="dropdown-item" href="#">Moje przedmioty</a>
                        <a class="dropdown-item" href="#">Moje finanse</a>
                        <a class="dropdown-item" href="#">Rejestracja na przedmioty</a>
                        <a class="dropdown-item" href="#">Moje postępy</a>
                    </div>
                    </li>
                </security:authorize>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="GuestDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Informacje
                    </a>
                    <div class="dropdown-menu" aria-labelledby="GuestDropdownMenuLink">
                        <a class="dropdown-item" href="#">Informacje o przedmiotach</a>
                        <a class="dropdown-item" href="#">Plany modelowe</a>
                        <a class="dropdown-item" href="#">Przydatne linki</a>
                    </div>
                </li>
                <security:authorize access="!hasAnyRole('ROLE_ADMIN', 'ROLE_AUTHORITY', 'ROLE_OFFICE', 'ROLE_TEACHER', 'ROLE_STUDENT')">
                    <li class="nav-item">
                    <a class="btn btn-primary" href="/login">
                        <div class='fa fa-user'></div>
                        Zaloguj się
                    </a>
                    </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_AUTHORITY', 'ROLE_OFFICE', 'ROLE_TEACHER', 'ROLE_STUDENT')">
                    <li class="nav-item">
                    <a class="btn btn-primary" href="/logout">
                        <div class='fa fa-sign-out'></div>
                        Wyloguj się
                    </a>
                </li>
                </security:authorize>
            </ul>
        </div>
    </nav>

</div>