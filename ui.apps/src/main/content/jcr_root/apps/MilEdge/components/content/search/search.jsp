<%-- Search --%> 
<%@include file="/libs/foundation/global.jsp"%> 
<%@page session="false" %>
<%@ page import="java.util.*"%> 
 <cq:includeClientLib categories="renderClientLib"/>
<head>
    <meta charset="UTF-8">
    <title>Product</title>
    <link rel= "stylesheet" type= "text/css" href= "/etc/designs/MilEdge/clientlibs/css/bootstrap-min.css">
    <link rel= "stylesheet" type= "text/css" href= "/etc/designs/MilEdge/clientlibs/css/imageSearch.css" >
 
    <script src="/etc/designs/MilEdge/clientlibs/js/jquery-3.2.1.min.js" ></script>
    <script src= "http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
</head>
<!-- ...............................Header Section Ends .......................... -->

<!-- ...............................Main Body Starts .............................. -->

<body>

    <div id="search-menu-container" class=" container main-container">
        <h2 class="search-image-text">Search by Image</h2>
        <ul class="search-bar-container ">
            <li><a id ="Image-Url" class="active" href="#">Paste Image URL</a></li>
            <li><a id="Image-Upload" href="#">Upload an Image</a></li>
        </ul>
        <div class="search-bar">
            <!-- ......................................Search By URL ....................................-->
            <div class="abc">
                    <input type="text" name="search" id="text-field-url" placeholder="Paste the URL here" class="input-type-text-field">
                    <button class="search-button" >Search</button>
            </div>
            <!--......................................... Search By URL Ends ....................... -->

            <!-- ....................Search by Browsing Image........................... -->
    <div class="input-group xyz hide-class">
                    <input type="text" class="form-control" id="text-field-path" placeholder="Upload an Image here " readonly>
                    <label class="input-group-btn">
                        <span class="btn search-button1">
                             <input type="file" style="display: none;" id="searchimage" class="file-input" multiple>
                             Browse File

                        </span>
                    </label>
        <button  class="search-button search-button2">Search</button>
                </div>

            <!-- ...........................Search By Browsing Image Ends.......................... -->
        </div>
    </div>
    <div id="no-results-text" class="hide-class">
        <h3>No Results found</h3>
    </div>
    <div id="search-results-text" class=" container hide-class">
	<h3 class="search-results-text">Results found for <span class="input-reading"></span> </h3>

	<% 	Resource resBlack = resourceResolver.getResource("/content/MilEdge/miledge/en/product/Lace_maxi_dress/jcr:content/Parsys1/productdetails");
    	ValueMap resPropertiesBlack = resBlack.adaptTo(ValueMap.class);
    	String titleBlack = resPropertiesBlack.get("productTitle")!= null? resPropertiesBlack.get("productTitle").toString():"Dress";
    	String priceBlack = resPropertiesBlack.get("productPrice")!= null? resPropertiesBlack.get("productPrice").toString():"20";
    	String imageBlack = resPropertiesBlack.get("imageTile")!= null? resPropertiesBlack.get("imageTile").toString():"/content/dam/MilEdgeContent/No_tile_image.png";

    %>

	      <div id ="black-dress-display" class="col-xs-6 col-md-3 product-box">
                <a href="/content/MilEdge/miledge/en/product/Lace_maxi_dress.html"><img src="<%= imageBlack%>" ></a>
                <div class="product-details">
                    <div class="product-title">
                        <%= titleBlack%>
                    </div>
                    <p class= "product-title product-price">
                        <span class= "product-title-price">
                            $ 
                        </span>
                        <%= priceBlack%>
                    </p>
                </div>
            </div>
        	<% 
 	   	Resource resRed = resourceResolver.getResource("/content/MilEdge/miledge/en/product/women_empire_maxi_dress/jcr:content/Parsys1/productdetails");
    	ValueMap resPropertiesRed = resRed.adaptTo(ValueMap.class);
    	String titleRed = resPropertiesRed.get("productTitle")!= null? resPropertiesRed.get("productTitle").toString():"Dress";
    	String priceRed = resPropertiesRed.get("productPrice")!= null? resPropertiesRed.get("productPrice").toString():"20";
    	String imageRed = resPropertiesRed.get("imageTile")!= null? resPropertiesRed.get("imageTile").toString():"/content/dam/MilEdgeContent/No_tile_image.png";

    %>

	      <div id="red-dress-display"class="col-xs-6 col-md-3 product-box">
                <a href="/content/MilEdge/miledge/en/product/Lace_maxi_dress.html"><img src="<%= imageRed%>" ></a>
                <div class="product-details">
                    <div class="product-title">
                        <%= titleRed%>
                    </div>
                    <p class= "product-title product-price">
                        <span class= "product-title-price">
                            $ 
                        </span>
                        <%= priceRed%>
                    </p>
                </div>
            </div>
    </div>

    <script src= "/etc/designs/MilEdge/clientlibs/js/searchImage.js"></script>
</body>
<!-- ....................................Main Body Ends ................................... -->
</br>
</br>
</br>
</br>
</br>
</br>