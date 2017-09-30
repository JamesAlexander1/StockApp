<!DOCTYPE html>
<!-- Used to access jstl language and functionality -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
		Bootstrap CSS used with permission from Bootstrap.com and Bootswatch.com as per their policies.
 -->


<html>
<head>

	<meta charset="UTF-8">
	
	<title>Hello</title>
	<!-- HTML for web page -->
	<!--  If we want to include javascript, we would store it in a .js file under WEB-INF and would reference it with a <script> tag
	and call the js functions/code as needed in this html file  -->
	 <link href="${pageContext.request.contextPath}/bootstrap/css/darkly_bootstrap.min.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/bootstrap/css/custom.css" rel="stylesheet" type="text/css" />
	
	<!-- ${pageContext.request.contextPath} is a jstl variable, jstl is a scripting language for access servlet variables for use 
	in jsp files. in this case, we are retrieving the root of the directory for all pages (WebContent/) -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	
	
	
</head>
<body>
	
	<nav class="navbar navbar-default navbar-fixed-top" style=" margin-bottom: 40px;">
      <div class="container-fluid" >
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">StockApp</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-left" method="post" action="search">
            <input type="text" class="form-control" placeholder="Search..." name="search_value">
            <button type="submit" class="btn btn-default">Submit</button>
          </form>
          
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" style=" margin-top: 20px;">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#" class="text-info">Reports</a></li>
            <li><a href="#" class="text-info">Analytics</a></li>
            <li><a href="#" class="text-info">Export</a></li>
          </ul>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>            
                  <th>Sectors</th>
                </tr>
                
              </thead>
              <tbody>	
             <!-- Loop through each sector object, display name and yesterday's performance as a percentage increase or decrease on closing price --> 
			<c:forEach items="${sector_list}" var="element">    
				<tr>
				
				<c:choose>
					<c:when test="${element.value <= '0'}">
						<td class="text-danger"><c:out value="${element.name}"/> : <c:out value="${element.value}"/></td>
					</c:when>
					<c:otherwise>
						<td class="text-success"><c:out value="${element.name}"/> : <c:out value="${element.value}"/></td>
					</c:otherwise>
				</c:choose>

   				</tr>
			</c:forEach>
			
              </tbody>
            </table>
            </div>
        </div>
        <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-1 main"  style=" margin-top: 40px;">
        
          <div class="jumbotron" style=" margin-top: 40px;">
			  <h2>Welcome to StockApp</h2>
			  <p>The best S&amp;P 500 Stock Application ever designed. Search your favourite stocks by market code and customise search results for a variety of indices and performance indicators.</p>
		  
          
			 ${pageContext.request.contextPath}/bootstrap/css/custom.css
		  </div> 
		  <div id=linechart_material>
		  </div>

          
        </div>
      </div>
    </div>
    
</body>
 <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</html>