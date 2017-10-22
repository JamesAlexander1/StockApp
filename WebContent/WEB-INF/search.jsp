<!DOCTYPE html>
<!-- Used to access jstl language and functionality -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 
		Bootstrap CSS used with permission from Bootstrap.com and Bootswatch.com as per their policies.
 -->


<html>
<head>

	<meta charset="UTF-8">
	
	<title>SandP 500 Price Charts</title>
	<!-- HTML for web page -->
	<!--  If we want to include javascript, we would store it in a .js file under WEB-INF and would reference it with a <script> tag
	and call the js functions/code as needed in this html file  -->
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- ${pageContext.request.contextPath} is a jstl variable, jstl is a scripting language for access servlet variables for use 
	in jsp files. in this case, we are retrieving the root of the directory for all pages (WebContent/) -->
	 <link href="${pageContext.request.contextPath}/bootstrap/css/darkly_bootstrap.min.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/bootstrap/css/custom.css" rel="stylesheet" type="text/css" />
	  <link href="${pageContext.request.contextPath}/bootstrap/css/tabbed-carousel.css" rel="stylesheet" type="text/css" />
	
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
	

	
</head>

<!-- Javascript -->

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

	
	
	//
	
    google.charts.load('current', {'packages':['line']});
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    google.charts.setOnLoadCallback(drawVisualization);

    
    /*
     * SearchController.java loads the relevant time period, search.jsp only has to load whatever it is given.
     */
    
    function drawChart() {
	    var dataYearly = new google.visualization.DataTable();
	    
	   
	   	dataYearly.addColumn('date', 'Date');
		dataYearly.addColumn('number', 'Price');
	   	<c:forEach items="${yearly_list}" varStatus="element">
		dataYearly.addRow([new Date(<c:out value="${yearly_list[element.index].year}"/> ,<c:out value="${yearly_list[element.index].month}"/>, <c:out value="${yearly_list[element.index].day}"/> ), <c:out value="${yearly_list[element.index].price}"/>]);
		</c:forEach>
	 
	    
	    
	    var optionsYearly = {
	            title: 'Stock Price Performance',
	            //curveType: 'function',
	            legend: { position: 'bottom' },
	            backgroundColor: '#303030',
	            legendTextStyle: { color: '#FFF' },
	            titleTextStyle: { color: '#FFF' },
	            hAxis: {
	                title: "Date",
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
     //sma chart
     function drawSma(){
	     var smaChart = new google.visualization.DataTable();
	      
	     
	     
	    		smaChart.addColumn('date', 'Date');
	     	
	       	smaChart.addColumn('number', 'SMA');
	       	
	     	<c:forEach items="${sma_chart}" varStatus="element">
	   			smaChart.addRow([new Date(<c:out value="${sma_chart[element.index].year}"/> ,<c:out value="${sma_chart[element.index].month}"/>, 
	   			<c:out value="${sma_chart[element.index].day}"/> ), <c:out value="${sma_chart[element.index].price}"/>]);
	   			
	   	 	</c:forEach>
	    
	      
	      
	      var smaOptions = {
	              title: 'Stock Price Performance',
	              //curveType: 'function',
	              legend: { position: 'bottom' },
	              backgroundColor: '#303030',
	              legendTextStyle: { color: '#FFF' },
	              titleTextStyle: { color: '#FFF' },
	              hAxis: {
	                  title: "Date",
	                  titleTextStyle:{color: '#FFF'},
	                  textStyle:{color: '#FFF'}
	              },
	              vAxis: {
	                  title: "SMA value in $USD",
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
	
	     
	     
	     var smaChartDraw = new google.charts.Line(document.getElementById('sma_chart_graph'));
	     smaChartDraw.draw(smaChart, google.charts.Line.convertOptions(smaOptions));
     
     
    }
     
    // 
    function drawRsi() {
     //rsi chart
	     var rsiChart = new google.visualization.DataTable();
			rsiChart.addColumn('date', 'Date');
			rsiChart.addColumn('number', 'RSI');
		<c:forEach items="${rsi_chart}" varStatus="element">
			rsiChart.addRow([new Date(<c:out value="${rsi_chart[element.index].year}"/> ,<c:out value="${rsi_chart[element.index].month}"/>, <c:out value="${rsi_chart[element.index].day}"/> ), <c:out value="${rsi_chart[element.index].price}"/>]);
		</c:forEach>
	
		var rsiOptions = {
		        title: 'RSI Chart',
		        //curveType: 'function',
		        legend: { position: 'bottom' },
		        backgroundColor: '#303030',
		        legendTextStyle: { color: '#FFF' },
		        titleTextStyle: { color: '#FFF' },
		        hAxis: {
		            title: "Date",
		            titleTextStyle:{color: '#FFF'},
		            textStyle:{color: '#FFF'}
		        },
		        vAxis: {
		            title: "RSI value in $USD",
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
	
		var rsiChartDraw = new google.charts.Line(document.getElementById('rsi_chart_graph'));
		rsiChartDraw.draw(rsiChart, google.charts.Line.convertOptions(rsiOptions));
    
    }
    
    function drawVisualization() {
    	//macd chart
        var macdChart = new google.visualization.DataTable();
   		macdChart.addColumn('date', 'Date');
   		macdChart.addColumn('number', 'MACD');
   		macdChart.addColumn('number', 'MACD HIST');
   		macdChart.addColumn('number', 'MACD SIGNAL');

   		<c:forEach items="${macd_chart}" varStatus="element">
   			macdChart.addRow([new Date(<c:out value="${macd_chart[element.index].year}"/> ,<c:out value="${macd_chart[element.index].month}"/>, <c:out value="${macd_chart[element.index].day}"/> ), <c:out value="${macd_chart[element.index].macdPrice}"/>, <c:out value="${macd_chart[element.index].macdHistPrice}"/>, <c:out value="${macd_chart[element.index].macdSignalPrice}"/>]);
   		</c:forEach>

   		var macdOptions = {
   	        title: 'MACD Chart',
   	        //curveType: 'function',
   	        legend: { position: 'bottom' },
   	        backgroundColor: '#303030',
   	        legendTextStyle: { color: '#FFF' },
   	        titleTextStyle: { color: '#FFF' },
   	        hAxis: {
   	            title: "Date",
   	            titleTextStyle:{color: '#FFF'},
   	            textStyle:{color: '#FFF'}
   	        },
   	        vAxis: {
   	            title: "MACD indicators in $USD",
   	            titleTextStyle:{color: '#FFF'},
   	            textStyle:{color: '#FFF'},
   	            format: 'decimal'
   	           
   	            
   	        },
   	        opacity: 0.8,
   	        seriesType: 'bars',
   	        series: {0: {type: 'line'}, 2:{type:'line'}, 1:{type:'area'}}
   	    };
   	
   	
   	
   		var macdChartDraw = new google.visualization.ComboChart(document.getElementById('macd_chart_graph'));
   		macdChartDraw.draw(macdChart, google.charts.Line.convertOptions(macdOptions));
    }
    
   	   //Carousel js.
   	 	$(document).ready( function() {
   	 	    $('#myCarousel').carousel({
   	 	   		pause: true,
   	     		interval: false
   	 		});
   	 		
   	 		var clickEvent = false;
   	 		$('#myCarousel').on('click', '.nav a', function() {
   	 				clickEvent = true;
   	 				$('.nav li').removeClass('active');
   	 				$(this).parent().addClass('active');		
   	 				
   	 		}).on('slid.bs.carousel', function(e) {
   	 			
   	 			var current = $('.nav li.active');
   	 			var id = parseInt(current.data('slide-to'));
   	 			if(!clickEvent) {
   	 				var count = $('.nav').children().length -1;
   	 				
   	 				current.removeClass('active').next().addClass('active');
   	 				//var id = parseInt(current.data('slide-to'));
   	 				if(count == id) {
   	 					$('.nav li').first().addClass('active');	
   	 				}
   	 				
   	 				
   	 			}
   	 			clickEvent = false;
   	 			if(id == 0){
					drawChart();
				}else if(id == 1){
					drawSma();
				}else if(id == 2){
					drawRsi();
				}else{
					drawVisualization();
				}
   	 			//var current = $('.nav li.active');
   	 			//drawChart();
   	 			//drawVisualization();
   	 			//drawRsi()
   	 			//drawSma();
   	 			
   	 		});
   	 	});
</script>
<!--  -->



<body>
	
	<nav class="navbar navbar-default navbar-fixed-top">
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
        <div class="col-sm-3 col-md-2 sidebar" style=" margin-top: 40px;">
          

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
            
            <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/search" method="post" style=" margin-top: 5px;">           
	                    
		        		<button type="submit" class="btn btn-default" name="time_period" value="YEARLY">Yearly</button>
		        		<button type="submit" class="btn btn-default" name="time_period" value="HALF_YEARLY" style=" margin-top: 5px;">Half Yearly</button>
		       		<button type="submit" class="btn btn-default" name="time_period" value="QUARTERLY" style=" margin-top: 5px;">Quarterly</button>
		        		<button type="submit" class="btn btn-default" name="time_period" value="MONTHLY" style=" margin-top: 5px;">Monthly</button>
		        		<button type="submit" class="btn btn-default" name="time_period" value="WEEKLY" style=" margin-top: 5px;">Weekly</button>
		         		
		        		<input type=hidden name="company" value="${company}">
	           		
			</form>
        </div>
        <div class="col-sm-6 col-sm-offset-3 col-md-8 col-md-offset-1 main" style=" margin-top: 30px;">
        		
        		
        		
			
			
	        	<!-- Jumbotron  -->
	        	
	        	<div class="jumbotron" style=" margin-top: 30px;">
	        	
	        		<!-- Comparison Form -->
        		
	        		<form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/compare" method="post">
	        		
		            <input type="text" name="companyCompare" class="form-control" placeholder="Compare...">
		            <input type=hidden name="company" value="${company}">
		            <input type=hidden name="companyCompare" value="${companyCompare}">
		            <button type="submit" class="btn btn-default">Compare</button>
		            
	        		</form>
			 	
			 	
			 	<!-- Different Time Period Intervals for Data -->
			 	<!-- 
			 	<div class="bs-component">
              <div class="list-group">
                <a href="#" class="list-group-item active">
                  Cras justo odio
                </a>
                <a href="#" class="list-group-item">Dapibus ac facilisis in
                </a>
                <a href="#" class="list-group-item">Morbi leo risus
                </a>
              </div>
            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div> -->
	        		<!--  -->
	        		
	        		
        			<!-- Charts  -->
	        		
	        		<div class="container">
	        		
	        		
				    <div id="myCarousel" class="carousel slide" data-ride="carousel" style=" margin-top: 20px;">
				    
				      <!-- Wrapper for slides -->
				      <div class="carousel-inner">
				      
				        <div class="item active">
				          	
			          			
			   					<div align="center" id="linechart_material" style="width: 700px; height: 450px; left: 0; right:0; margin: auto"></div>
								  
				
						 
						  
				           
				        </div><!-- End Item -->
				 
						<div class="item">
				        		
				            
				          		
				   				<div align="center" id="sma_chart_graph" style="width: 700px; height: 450px; left: 0; right:0; margin: auto"></div>
				                 
				  		
							
				        </div><!-- End Item -->
				        
						<div class="item">
						  	
	          						
	   				 				<div align="center" id="rsi_chart_graph" style="width: 700px; height: 450px; left: 0; right:0; margin: auto"></div>
					   		
						   
						   		
						</div><!-- End Item -->
				        
				        <div class="item">
				          <!-- place graph here -->
				           
				           
				           
				          		
				   				<div align="center" id="macd_chart_graph" style="width: 700px; height: 450px; left: 0; right:0; margin: auto"></div>
				                 
						
				           
				        </div><!-- End Item -->
				                
				      </div><!-- End Carousel Inner -->
				
					<ul class="nav nav-pills nav-justified">
				          <li data-target="#myCarousel" data-slide-to="0" class="active"><a href="#">Closing Price<small>Chart of Closing Price of Stock</small></a></li>
				          <li data-target="#myCarousel" data-slide-to="1" ><a href="#">SMA<small>SMA Chart</small></a></li>
				          <li data-target="#myCarousel" data-slide-to="2" ><a href="#">RSI<small>RSI Chart</small></a></li>
				          <li data-target="#myCarousel" data-slide-to="3" ><a href="#">MACD<small>MACD Chart</small></a></li>
			        </ul>
				    	
				
				
				    </div><!-- End Carousel -->
				    </div>
				    
				    
				    
				
	        	</div>
	        	<!-- End Jumbotron  -->
 
        </div>
      </div>
    </div>
</body>
 <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</html>
