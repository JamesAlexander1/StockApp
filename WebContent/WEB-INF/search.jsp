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
	
	
	<!-- ${pageContext.request.contextPath} is a jstl variable, jstl is a scripting language for access servlet variables for use 
	in jsp files. in this case, we are retrieving the root of the directory for all pages (WebContent/) -->
	 <link href="${pageContext.request.contextPath}/bootstrap/css/darkly_bootstrap.min.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/bootstrap/css/custom.css" rel="stylesheet" type="text/css" />
	
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	

 

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
          <form class="navbar-form navbar-left" method="post" action="/search">
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
         </div>
          	 
    
        <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-1 main"  style=" margin-top: 40px;">
          <div style=" margin-top: 40px;" id=linechart_material>
 				
		  </div>   
        </div>
      </div>
    </div>
   
    <script>
    google.charts.load('current', {'packages':['line']});
    google.charts.setOnLoadCallback(drawChart);
  	function drawChart() {
  		
  	
	    var data = new google.visualization.DataTable();
	    data.addColumn('number', 'Date');
	    data.addColumn('number', 'Price');
	    
	    
	    	
	    	data.addRow(1, 80.1);
	    	data.addRow(2, 76.5);
	    	data.addRow(3, 84.1);
	   
					
	    var options = {
	      title: 'Test Price Chart',
	      titleTextStyle: {color: '#FFF'},
	      legend : {position:'none'},
	      backgroundColor: '#fcfcfc',
	      hAxis: {textStyle:{color: '#FFF'}},
	      vAxis: {
	    	  textStyle:{color: '#FFF'},
	    	  baselineColor: '#fff',
	    	  gridlineColor: '#fff'
	      },
		  backgroundColor:'#272626',
	      width: 300px,
	      height: 300px
	    };
	    var chart = new google.visualization.LineChart(document.getElementById('linechart_material'));
	    chart.draw(data, google.charts.Line.convertOptions(options));
  	}
  	
</script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
 
 
</html>
