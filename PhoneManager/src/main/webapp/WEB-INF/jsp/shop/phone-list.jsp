<%@ page import="hello.phonemanager.domain.dto.ShopPhoneDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.phonemanager.domain.Owner" %>
<%@ page import="hello.phonemanager.domain.Phone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ShopPhoneDetail> phones = (List<ShopPhoneDetail>) request.getAttribute("phones");
    Owner loginOwner = (Owner) session.getAttribute("loginOwner");
    Long shopId = Long.parseLong(request.getParameter("shopId"));
    List<Phone> allPhones = (List<Phone>) request.getAttribute("allPhones");
%>
<html>
<head>
    <title>가맹점 휴대폰 목록</title>
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
        .sell-form {
            display: flex;
            align-items: center;
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
                    <a class="nav-link" href="/shop/list"><i class="fas fa-store me-1"></i> 샵 목록</a>
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
        <h2><i class="fas fa-mobile-alt me-2"></i>가맹점의 판매 중인 휴대폰</h2>
        <p class="mb-0">현재 가맹점에 등록된 휴대폰 목록과 재고 현황입니다.</p>
    </div>
    
    <div class="card">
        <div class="card-body p-0">
            <table class="table table-hover mb-0">
                <thead>
                <tr>
                    <th><i class="fas fa-mobile-alt me-1"></i> 모델명</th>
                    <th><i class="fas fa-trademark me-1"></i> 브랜드</th>
                    <th><i class="fas fa-tag me-1"></i> 가격</th>
                    <th><i class="fas fa-calendar-alt me-1"></i> 출시일</th>
                    <th><i class="fas fa-boxes me-1"></i> 재고</th>
                    <th><i class="fas fa-shopping-cart me-1"></i> 판매</th>
                    <th><i class="fas fa-trash me-1"></i> 삭제</th>
                </tr>
                </thead>
                <tbody>
                <% for (ShopPhoneDetail phone : phones) { %>
                <tr>
                    <td><%= phone.getName() %></td>
                    <td><%= phone.getBrand() %></td>
                    <td><%= phone.getPrice() %></td>
                    <td><%= phone.getReleaseDate() %></td>
                    <td>
                        <span class="badge bg-<%= phone.getStock() > 5 ? "success" : (phone.getStock() > 0 ? "warning" : "danger") %>">
                            <%= phone.getStock() %>
                        </span>
                    </td>

                    <!-- 판매 폼 -->
                    <td>
                        <form action="/shop/phones/sell" method="post" class="sell-form">
                            <input type="hidden" name="shopId" value="<%= shopId %>">
                            <input type="hidden" name="phoneId" value="<%= phone.getPhoneId() %>">
                            <input type="number" name="quantity" min="1" max="<%= phone.getStock() %>" class="form-control form-control-sm me-2" style="width: 60px;" placeholder="수량" required <%= phone.getStock() == 0 ? "disabled" : "" %>>
                            <button type="submit" class="btn btn-sm btn-success" <%= phone.getStock() == 0 ? "disabled" : "" %>>
                                <i class="fas fa-shopping-cart me-1"></i>판매
                            </button>
                        </form>
                    </td>

                    <!-- 삭제 버튼 -->
                    <td>
                        <form action="/shop/phones/delete" method="post">
                            <input type="hidden" name="shopId" value="<%= shopId %>">
                            <input type="hidden" name="phoneId" value="<%= phone.getPhoneId() %>">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('정말 삭제하시겠습니까?')">
                                <i class="fas fa-trash me-1"></i>삭제
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <div class="card mt-4">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0"><i class="fas fa-plus-circle me-2"></i>휴대폰 추가</h4>
        </div>
        <div class="card-body">
            <form action="/shop/phones/add" method="post" class="row g-3">
                <input type="hidden" name="shopId" value="<%= shopId %>">
                <div class="col-md-6">
                    <label class="form-label"><i class="fas fa-mobile-alt me-1"></i> 휴대폰 모델</label>
                    <select name="phoneId" class="form-select" required>
                        <option value="" disabled selected>휴대폰 선택</option>
                        <% for (Phone phone : allPhones) { %>
                        <option value="<%= phone.getId() %>"><%= phone.getName() %> (<%= phone.getBrand() %>)</option>
                        <% } %>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label"><i class="fas fa-boxes me-1"></i> 재고</label>
                    <input type="number" name="stock" class="form-control" placeholder="재고 수량" required>
                </div>
                <div class="col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary btn-custom w-100">
                        <i class="fas fa-plus me-1"></i> 등록
                    </button>
                </div>
            </form>
        </div>
    </div>
    
    <div class="text-center mt-4">
        <a href="/shop/list" class="btn btn-secondary btn-custom">
            <i class="fas fa-arrow-left me-2"></i>샵 목록으로 돌아가기
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
