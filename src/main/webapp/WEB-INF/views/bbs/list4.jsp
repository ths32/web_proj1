<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp4 = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><!-- lang="en" -->
  <head>
    <title>Autoroad - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/project/resources/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/project/resources/css/animate.css">
    
    <link rel="stylesheet" href="/project/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/project/resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/project/resources/css/magnific-popup.css">

    <link rel="stylesheet" href="/project/resources/css/aos.css">

    <link rel="stylesheet" href="/project/resources/css/ionicons.min.css">

    <link rel="stylesheet" href="/project/resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/project/resources/css/jquery.timepicker.css">

    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" href="/project/resources/css/flaticon.css">
    <link rel="stylesheet" href="/project/resources/css/icomoon.css">
    <link rel="stylesheet" href="/project/resources/css/style.css">
  
  
  
  <link rel="stylesheet" href="/project/resources/css/style.css" type="text/css"/>
  <link rel="stylesheet" href="/project/resources/resources/css/article2.css" type="text/css"/>
  
  <link rel="stylesheet" href="/project/resources/resources/css/style.css" type="text/css"/><!--  -->
  <link rel="stylesheet" href="/project/resources/resources/css/list2.css" type="text/css"/><!--  -->
  
  <script type="text/javascript">

	function sendIt4(){
		
		var f4 = document.searchForm4;
		
		f4.action = "<%=cp4%>/list4.action";
		f4.submit();
		
	}

  </script>
  <style type="text/css">
   					 img:hover {
       					cursor: pointer;
       					width:16px;
       					height:16px;
   					} 
   					input[type=submit] {
       					display: none;
       					width:6px;
       					height:6px;
   					}
   					input[type=button] {
       					cursor: pointer;
   					}
  </style>
  
  
  </head>
  <!-- -->
  <body>
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="index.html">Auto<span>road</span></a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="index.html" class="nav-link">Home</a></li>
	          <li class="nav-item active"><a href="about.html" class="nav-link">About</a></li>
	          <li class="nav-item"><a href="pricing.html" class="nav-link">Pricing</a></li>
	          <li class="nav-item"><a href="car.html" class="nav-link">Our Car</a></li>
	          <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/project/resources/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>About us <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">About Us</h1>
          </div>
        </div>
      </div>
    </section>
<!--  -->
    
    
    
    <div id="bbsList" align="center">
	<div id="bbsList_title" style="font-size:35px; font-weight:bold; width:100%" align="center">
	Notice
	</div>
	<!--  -->
	<div id="bbsList_header">
			<input type="button" value="TH Telecom" class="btn2" onclick="javascript:location.href='<%=cp4%>/list.action';" style="color:#000000; background-color:#ffffff;"/>		
			<input type="button" value="Direct Shop" class="btn2" onclick="javascript:location.href='<%=cp4%>/list2.action';" style="color:#000000; background-color:#ffffff;"/>	
			<input type="button" value="Member Service" class="btn2" onclick="javascript:location.href='<%=cp4%>/list3.action';" style="color:#000000; background-color:#ffffff;"/>	
			<input type="button" value="Roaming" class="btn2" onclick="javascript:location.href='<%=cp4%>/list4.action';" style="color:#ffffff; background-color:#f91c16;"/>	
	 </div>
	<!--  -->
	<div id="bbsList_header">
		<div id="leftHeader">
		
			<form action="" name="searchForm4" method="post">
				<select name="searchKey4" class="selectField">
					<option value="name4">Class</option>
					<option value="subject4">Subject</option>
					<option value="content4">Content</option>				
				</select>
		<input type="text" name="searchValue4" class="textField">
				<label for="img">
   					<input type="submit" name="submit" id="img" value="" onclick="sendIt4();" style="color:#ffffff;">
   					<img src="/project/resources/images/searchboximage.png" id="img" height="16px" width="16px" style="color:#ffffff;">
				</label>
		</form>							
		</div>
			
		<!-- Manager Mode ON/OFF -->
		<div id="rightHeader">
			<input type="button" value=" Write" class="btn2" 
			onclick="javascript:location.href='<%=cp4%>/created4.action';"/>		
		</div>	
		<!-- Manager Mode Ended -->
	</div>
	
	<div id="bbsList_list">
		<div id="title" style="width:100%"><!--  -->
			<dl>
				<dt class="num4" style="width:4%">No</dt>
				<dt class="name4" style="width:5%">Class</dt>
				<dt class="subject4" style="width:71%">Subject</dt>
				<dt class="created4" style="width:10%">Written</dt>
				<dt class="hitCount4" style="width:8%">Views</dt>
			</dl>
		</div>
		<div id="lists">
		<c:forEach var="dto4" items="${lists4}">
			<dl>
				<dd class="num4" style="width:4%">${dto4.num4 }</dd>
				<dd class="name4" style="width:5%">${dto4.name4 }</dd>
				<dd class="subject4" style="width:71%">
				<a href="${articleUrl4}&num4=${dto4.num4}">
				${dto4.subject4 }</a></dd>
				<dd class="created4" style="width:10%">${dto4.created4 }</dd>
				<dd class="hitCount4" style="width:8%">${dto4.hitCount4 }</dd>
			</dl>
		</c:forEach>
		</div>
		
		<div id="footer">
			<p>
				<c:if test="${dataCount4!=0 }">
					${pageIndexList4 }
				</c:if>
				<c:if test="${dataCount4==0 }">
					No articles found.
				</c:if>
			</p>
		</div>	
	</div>
</div>
    
    
    
    
    
    

<!--  -->
    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">About Autoroad</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">Information</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">About</a></li>
                <li><a href="#" class="py-2 d-block">Services</a></li>
                <li><a href="#" class="py-2 d-block">Term and Conditions</a></li>
                <li><a href="#" class="py-2 d-block">Best Price Guarantee</a></li>
                <li><a href="#" class="py-2 d-block">Privacy &amp; Cookies Policy</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Customer Support</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">FAQ</a></li>
                <li><a href="#" class="py-2 d-block">Payment Option</a></li>
                <li><a href="#" class="py-2 d-block">Booking Tips</a></li>
                <li><a href="#" class="py-2 d-block">How it works</a></li>
                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Have Questions?</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="/project/resources/js/jquery.min.js"></script>
  <script src="/project/resources/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="/project/resources/js/popper.min.js"></script>
  <script src="/project/resources/js/bootstrap.min.js"></script>
  <script src="/project/resources/js/jquery.easing.1.3.js"></script>
  <script src="/project/resources/js/jquery.waypoints.min.js"></script>
  <script src="/project/resources/js/jquery.stellar.min.js"></script>
  <script src="/project/resources/js/owl.carousel.min.js"></script>
  <script src="/project/resources/js/jquery.magnific-popup.min.js"></script>
  <script src="/project/resources/js/aos.js"></script>
  <script src="/project/resources/js/jquery.animateNumber.min.js"></script>
  <script src="/project/resources/js/bootstrap-datepicker.js"></script>
  <script src="/project/resources/js/jquery.timepicker.min.js"></script>
  <script src="/project/resources/js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="/project/resources/js/google-map.js"></script>
  <script src="/project/resources/js/main.js"></script>
    
  </body>
</html>