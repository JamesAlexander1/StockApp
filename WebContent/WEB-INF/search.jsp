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
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- ${pageContext.request.contextPath} is a jstl variable, jstl is a scripting language for access servlet variables for use 
	in jsp files. in this case, we are retrieving the root of the directory for all pages (WebContent/) -->
	 <link href="${pageContext.request.contextPath}/bootstrap/css/darkly_bootstrap.min.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/bootstrap/css/custom.css" rel="stylesheet" type="text/css" />
	
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	

	
</head>

<!-- Javascript -->

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['line']});
    google.charts.setOnLoadCallback(drawChart);
    
    
    /*
     * For now, just uncomment whichever graph you want to display. We need to add conditional statements
     * depending on which time period the user wants.
     */
    
    function drawChart() {
        /*var data = new google.visualization.DataTable();
        data.addColumn('date', 'Date');
        data.addColumn('number', 'Price');
        <c:forEach items="${week_list}" var="element">
        	data.addRow([new Date(<c:out value="${element.year}"/> ,<c:out value="${element.month}"/>, <c:out value="${element.day}"/> ), <c:out value="${element.price}"/>]);
        </c:forEach>
        
        var options = {
                title: 'Stock Price Performance',
                //curveType: 'function',
                legend: { position: 'bottom' },
                backgroundColor: '#303030',
                legendTextStyle: { color: '#FFF' },
                titleTextStyle: { color: '#FFF' },
                hAxis: {
                    title: "Date This Week",
                    titleTextStyle:{color: '#FFF'},
                    textStyle:{color: '#FFF'}
                },
                vAxis: {
                    title: "Stock Price in $USD",
                    titleTextStyle:{color: '#FFF'},
                    textStyle:{color: '#FFF'},
                    format: 'decimal'
                   
                    
                },
                opacity: 0.8,
                series: {
                    
                    0: { color: '#3BB9FF' },
                    1: { color: '#6CC417' }
                }
            };
        
        var chart = new google.charts.Line(document.getElementById('linechart_material'));
        chart.draw(data, google.charts.Line.convertOptions(options));*/
        
        /*var dataMonth = new google.visualization.DataTable();
        dataMonth.addColumn('date', 'Date');
        dataMonth.addColumn('number', 'Price');
        
        <c:forEach items="${month_list}" var="element">
    		dataMonth.addRow([new Date(<c:out value="${element.year}"/> ,<c:out value="${element.month}"/>, <c:out value="${element.day}"/> ), <c:out value="${element.price}"/>]);
        </c:forEach>
        
        
        
        var optionsMonth = {
                title: 'Stock Price Performance',
                //curveType: 'function',
                legend: { position: 'bottom' },
                backgroundColor: '#303030',
                legendTextStyle: { color: '#FFF' },
                titleTextStyle: { color: '#FFF' },
                hAxis: {
                    title: "Date This Month",
                    titleTextStyle:{color: '#FFF'},
                    textStyle:{color: '#FFF'}
                },
                vAxis: {
                    title: "Stock Price in $USD",
                    titleTextStyle:{color: '#FFF'},
                    textStyle:{color: '#FFF'},
                    format: 'decimal'
                   
                    
                },
                opacity: 0.8,
                series: {
                    
                    0: { color: '#3BB9FF' },
                    1: { color: '#6CC417' }
                }
            };

       
       
       var chartMonth = new google.charts.Line(document.getElementById('linechart_material'));
       chartMonth.draw(dataMonth, google.charts.Line.convertOptions(optionsMonth));*/
       
       
    	/*var dataQuarterly = new google.visualization.DataTable();
        dataQuarterly.addColumn('date', 'Date');
        dataQuarterly.addColumn('number', 'Price');
        
        <c:forEach items="${quarterly_list}" var="element">
    		dataQuarterly.addRow([new Date(<c:out value="${element.year}"/> ,<c:out value="${element.month}"/>, <c:out value="${element.day}"/> ), <c:out value="${element.price}"/>]);
        </c:forEach>
        
        
        
        var optionsQuarterly = {
                title: 'Stock Price Performance',
                //curveType: 'function',
                legend: { position: 'bottom' },
                backgroundColor: '#303030',
                legendTextStyle: { color: '#FFF' },
                titleTextStyle: { color: '#FFF' },
                hAxis: {
                    title: "Date This Quarter",
                    titleTextStyle:{color: '#FFF'},
                    textStyle:{color: '#FFF'}
                },
                vAxis: {
                    title: "Stock Price in $USD",
                    titleTextStyle:{color: '#FFF'},
                    textStyle:{color: '#FFF'},
                    format: 'decimal'
                   
                    
                },
                opacity: 0.8,
                series: {
                    
                    0: { color: '#3BB9FF' },
                    1: { color: '#6CC417' }
                }
            };

       
       
       var chartQuarterly = new google.charts.Line(document.getElementById('linechart_material'));
       chartQuarterly.draw(dataQuarterly, google.charts.Line.convertOptions(optionsQuarterly));*/
       
       /*var dataHalfYearly = new google.visualization.DataTable();
       dataHalfYearly.addColumn('date', 'Date');
       dataHalfYearly.addColumn('number', 'Price');
       
       <c:forEach items="${halfYearly_list}" var="element">
   			dataHalfYearly.addRow([new Date(<c:out value="${element.year}"/> ,<c:out value="${element.month}"/>, <c:out value="${element.day}"/> ), <c:out value="${element.price}"/>]);
   	   </c:forEach>
       
       
       
       var optionsHalfYearly = {
               title: 'Stock Price Performance',
               //curveType: 'function',
               legend: { position: 'bottom' },
               backgroundColor: '#303030',
               legendTextStyle: { color: '#FFF' },
               titleTextStyle: { color: '#FFF' },
               hAxis: {
                   title: "Date This Half Year",
                   titleTextStyle:{color: '#FFF'},
                   textStyle:{color: '#FFF'}
               },
               vAxis: {
                   title: "Stock Price in $USD",
                   titleTextStyle:{color: '#FFF'},
                   textStyle:{color: '#FFF'},
                   format: 'decimal'
                  
                   
               },
               opacity: 0.8,
               series: {
                   
                   0: { color: '#3BB9FF' },
                   1: { color: '#6CC417' }
               }
           };

      
      
      var chartHalfYearly = new google.charts.Line(document.getElementById('linechart_material'));
      chartHalfYearly.draw(dataHalfYearly, google.charts.Line.convertOptions(optionsHalfYearly));*/
      
      var dataYearly = new google.visualization.DataTable();
      dataYearly.addColumn('date', 'Date');
      dataYearly.addColumn('number', 'Price');
      
      <c:forEach items="${yearly_list}" var="element">
  			dataYearly.addRow([new Date(<c:out value="${element.year}"/> ,<c:out value="${element.month}"/>, <c:out value="${element.day}"/> ), <c:out value="${element.price}"/>]);
  	   </c:forEach>
      
      
      
      var optionsYearly = {
              title: 'Stock Price Performance',
              //curveType: 'function',
              legend: { position: 'bottom' },
              backgroundColor: '#303030',
              legendTextStyle: { color: '#FFF' },
              titleTextStyle: { color: '#FFF' },
              hAxis: {
                  title: "Date This Year",
                  titleTextStyle:{color: '#FFF'},
                  textStyle:{color: '#FFF'}
              },
              vAxis: {
                  title: "Stock Price in $USD",
                  titleTextStyle:{color: '#FFF'},
                  textStyle:{color: '#FFF'},
                  format: 'decimal'
                 
                  
              },
              opacity: 0.8,
              series: {
                  
                  0: { color: '#3BB9FF' },
                  1: { color: '#6CC417' }
              }
          };

     
     
     var chartYearly = new google.charts.Line(document.getElementById('linechart_material'));
     chartYearly.draw(dataYearly, google.charts.Line.convertOptions(optionsYearly));
    }
</script>
<!--  -->



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

          <a class="navbar-brand" href="${pageContext.request.contextPath}/home">StockApp</a>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            
            <li><a href="#">Help</a></li>
          </ul>

          <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/search" method="post">
            <input type="text" name="search_code" class="form-control" placeholder="Search...">

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
              <tbody>	<!--  Our sector query data will be displayed here	-->
              
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
			  <!-- <h2>Welcome to StockApp</h2>
			  <p>The best S&amp;P 500 Stock Application ever designed. Search your favourite stocks by market code and customise search results for a variety of indices and performance indicators.</p>
		   width: 900px; height: 500px;
		   -->
          		<h2 style = "text-align: center;"><c:out value="${company}" /></h2>
   				 <div align="center" id="linechart_material" style="width: 550px; height: 400px; left: 0; right:0; margin: auto"></div>
        	
			 
		  </div>

          
        </div>
      </div>
    </div>
</body>
 <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</html>
