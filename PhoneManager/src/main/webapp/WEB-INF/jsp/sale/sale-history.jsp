<%@ page import="java.util.List" contentType="text/html; charset=UTF-8"%>
<%@ page import="hello.phonemanager.domain.dto.GroupedSaleDetail" %>
<%@ page import="hello.phonemanager.domain.dto.SaleDetail" %>
<%@ page import="hello.phonemanager.domain.Owner" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!doctype html>
<%
    List<GroupedSaleDetail> groupedSales = (List<GroupedSaleDetail>) request.getAttribute("groupedSales");
    int totalRevenue = (int) request.getAttribute("totalRevenue");
    Owner loginOwner = (Owner)session.getAttribute("loginOwner");

    // 통화 형식으로 매출 포맷팅
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.KOREA);
    String formattedTotalRevenue = currencyFormatter.format(totalRevenue);
%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>판매 내역 - 휴대폰 판매관리 시스템</title>
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
        .hero-section {
            background: linear-gradient(135deg, #13547a 0%, #80d0c7 100%);
            color: white;
            padding: 60px 0;
            border-radius: 0 0 20px 20px;
            margin-bottom: 30px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        .feature-card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            height: 100%;
        }
        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.12);
        }
        .btn-custom {
            padding: 12px 24px;
            font-weight: 600;
            letter-spacing: 0.5px;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        .btn-custom:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .footer {
            background-color: #343a40;
            color: #adb5bd;
            padding: 20px 0;
            margin-top: 80px;
            border-radius: 20px 20px 0 0;
        }
        .welcome-badge {
            background-color: rgba(255,255,255,0.2);
            border-radius: 20px;
            padding: 8px 16px;
            margin-right: 10px;
            backdrop-filter: blur(5px);
        }
        .sales-card {
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            margin-bottom: 25px;
            border: none;
        }
        .sales-card .card-header {
            padding: 15px 20px;
            font-weight: 600;
        }
        .table {
            margin-bottom: 0;
        }
        .total-revenue {
            background-color: rgba(255,255,255,0.2);
            border-radius: 10px;
            padding: 10px 20px;
            display: inline-block;
            backdrop-filter: blur(5px);
            font-weight: 600;
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
                    <a class="nav-link active" href="/sales/history"><i class="fas fa-chart-line me-1"></i> 판매 내역</a>
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

<section class="hero-section">
    <div class="container text-center">
        <h1 class="display-4 fw-bold mb-3"><i class="fas fa-chart-bar me-2"></i>판매 내역</h1>
        <p class="lead mb-4">점포별 판매 실적을 확인하세요</p>
        <div class="total-revenue">
            <i class="fas fa-money-bill-wave me-2"></i> 전체 매출: <%= formattedTotalRevenue %>
        </div>
    </div>
</section>

<div class="container">
    <% for (GroupedSaleDetail group : groupedSales) {
        // 매장별 매출 포맷팅
        String formattedShopRevenue = currencyFormatter.format(group.getTotalRevenue());
    %>
    <div class="card sales-card">
        <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
            <span><i class="fas fa-store me-2"></i><%= group.getShopName() %></span>
            <span><i class="fas fa-coins me-1"></i> 매출 합계: <%= formattedShopRevenue %></span>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead class="table-light">
                    <tr>
                        <th><i class="fas fa-mobile-alt me-1"></i> 휴대폰 모델</th>
                        <th><i class="fas fa-cubes me-1"></i> 수량</th>
                        <th><i class="fas fa-tag me-1"></i> 판매가</th>
                        <th><i class="fas fa-calendar-alt me-1"></i> 판매일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (SaleDetail sale : group.getSales()) {
                        // 판매가 포맷팅
                        String formattedSalePrice = currencyFormatter.format(sale.getSalePrice());
                    %>
                    <tr>
                        <td><%= sale.getPhoneName() %></td>
                        <td><%= sale.getQuantity() %></td>
                        <td><%= formattedSalePrice %></td>
                        <td><%= sale.getSaleDate() %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <% } %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>