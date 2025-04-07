<%@ page import="hello.phonemanager.domain.Shop" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.phonemanager.domain.Owner" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Shop> shopList = (List<Shop>) request.getAttribute("shopList");
    Owner loginOwner = (Owner) session.getAttribute("loginOwner");
%>
<html>
<head>
    <title>Shop 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 1.4rem;
        }
        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            margin-bottom: 20px;
        }
        .btn-custom {
            padding: 8px 16px;
            font-weight: 600;
            letter-spacing: 0.5px;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        .btn-custom:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .page-header {
            background: linear-gradient(135deg, #13547a 0%, #80d0c7 100%);
            color: white;
            padding: 30px;
            border-radius: 12px;
            margin-bottom: 30px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        .welcome-badge {
            background-color: rgba(255,255,255,0.2);
            border-radius: 20px;
            padding: 8px 16px;
            margin-right: 10px;
            backdrop-filter: blur(5px);
        }
        .table {
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0,0,0,0.05);
            border-collapse: separate;
            border-spacing: 0;
        }
        .table thead {
            background-color: #343a40;
            color: white;
        }
        .table thead th {
            padding: 15px;
            border: none;
        }
        .table tbody tr:hover {
            background-color: rgba(0,0,0,0.02);
        }
        .table a {
            color: #13547a;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.2s ease;
        }
        .table a:hover {
            color: #80d0c7;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container">
        <a class="navbar-brand" href="/">
            <i class="fas fa-mobile-alt me-2"></i>PhoneManager
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="/shop/list"><i class="fas fa-store me-1"></i> 샵 목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/sales/history"><i class="fas fa-chart-line me-1"></i> 판매 내역</a>
                </li>
            </ul>
            <div class="d-flex align-items-center">
                <% if (loginOwner != null) { %>
                <span class="welcome-badge text-white">
                    <i class="fas fa-user-circle me-1"></i> <%= loginOwner.getName() %> 님
                </span>
                <a class="btn btn-outline-light btn-sm" href="/owner/logout">
                    <i class="fas fa-sign-out-alt me-1"></i> 로그아웃
                </a>
                <% } else { %>
                <a class="btn btn-outline-light me-2" href="/owner/login">
                    <i class="fas fa-sign-in-alt me-1"></i> 로그인
                </a>
                <a class="btn btn-primary" href="/owner/register">
                    <i class="fas fa-user-plus me-1"></i> 회원가입
                </a>
                <% } %>
            </div>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="page-header">
        <h2><i class="fas fa-store me-2"></i>내 가맹점 목록</h2>
        <p class="mb-0">현재 등록된 모든 가맹점을 관리할 수 있습니다.</p>
    </div>
    
    <div class="d-flex justify-content-end mb-4">
        <a href="/shop/new" class="btn btn-primary btn-custom">
            <i class="fas fa-plus-circle me-2"></i>가맹점 등록
        </a>
    </div>
    
    <div class="card">
        <div class="card-body p-0">
            <table class="table table-hover mb-0">
                <thead>
                <tr>
                    <th><i class="fas fa-store me-1"></i> 이름</th>
                    <th><i class="fas fa-map-marker-alt me-1"></i> 주소</th>
                    <th><i class="fas fa-cogs me-1"></i> 관리</th>
                </tr>
                </thead>
                <tbody>
                <% for (Shop shop : shopList) { %>
                <tr>
                    <td>
                        <a href="/shop/phones?shopId=<%= shop.getId() %>">
                            <i class="fas fa-store me-1"></i> <%= shop.getName() %>
                        </a>
                    </td>
                    <td><%= shop.getAddress() %></td>
                    <td>
                        <a href="/shop/edit?id=<%= shop.getId() %>" class="btn btn-sm btn-warning me-2">
                            <i class="fas fa-edit me-1"></i> 수정
                        </a>
                        <a href="/shop/delete?id=<%= shop.getId() %>" class="btn btn-sm btn-danger"
                           onclick="return confirm('삭제하시겠습니까?')">
                            <i class="fas fa-trash me-1"></i> 삭제
                        </a>
                    </td>
                </tr>
                <% } %>
                <% if (shopList == null || shopList.isEmpty()) { %>
                <tr>
                    <td colspan="3" class="text-center py-4">등록된 가맹점이 없습니다.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="text-center mt-4">
        <a href="/" class="btn btn-secondary btn-custom">
            <i class="fas fa-home me-2"></i>홈으로 돌아가기
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
