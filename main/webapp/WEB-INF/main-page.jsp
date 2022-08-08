<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<head>
    <link rel="stylesheet" href="css/mainPageStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>

<html>
 <main class="site-wrapper">
   <div class="pt-table desktop-768">
     <div class="pt-tablecell page-home relative" style="background-image: url(https://images.unsplash.com/photo-1486870591958-9b9d0d1dda99?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1500&q=80);
     background-position: center;
     background-size: cover;">
                     <div class="overlay"></div>

                     <div class="container">
                         <div class="row">
                             <div class="col-xs-12 col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
                                 <div class="page-title  home text-center">
                                   <span class="heading-page"><font color="white"> Welcome to Garand App: ${logged_user}!</font></span>
                                 </div>

                                 <div class="hexagon-menu clear">
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content" href="/customers">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-universal-access"></i>
                                                 </span>
                                                 <span class="title"><font color="white">Customers</font></span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content" href="/products">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-bullseye"></i>
                                                 </span>
                                                 <span class="title"><font color="white">Products</font></span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-braille"></i>
                                                 </span>
                                                 <span class="title">Services</span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-id-badge"></i>
                                                 </span>
                                                 <span class="title">Resume</span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-life-ring"></i>
                                                 </span>
                                                 <span class="title">Works</span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-clipboard"></i>
                                                 </span>
                                                 <span class="title">Testimonials</span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                     <div class="hexagon-item">
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <div class="hex-item">
                                             <div></div>
                                             <div></div>
                                             <div></div>
                                         </div>
                                         <a  class="hex-content" href="/logout">
                                             <span class="hex-content-inner">
                                                 <span class="icon">
                                                     <i class="fa fa-map-signs"></i>
                                                 </span>
                                                 <span class="title"><font color="white">Logout</font></span>
                                             </span>
                                             <svg viewBox="0 0 173.20508075688772 200" height="200" width="174" version="1.1" xmlns="http://www.w3.org/2000/svg"><path d="M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z" fill="#1e2530"></path></svg>
                                         </a>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
   </main>
</html>