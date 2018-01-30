<%-- Tiles --%> 
<%@include file="/libs/foundation/global.jsp"%> 
<%@page session="false" %>
<%@ page import="java.util.*"%> 
 <cq:includeClientLib categories="renderClientLib"/>
<% 
    Page rootPage = pageManager.getPage("/content/MilEdge/miledge/en/product"); 
	Iterator<Page> pageCount = rootPage.listChildren();
	int count=0;
	while(pageCount.hasNext())
    {
        count++;
        pageCount.next();
    }
	Iterator<Page> children = rootPage.listChildren(); %>
<h3 class="heading-count">
          Dresses (<%= count%>)
</h3>
 <div class="row body-product-list">
<% while (children.hasNext()) 
	{ 
    	Page child = children.next().adaptTo(Page.class); 
   		String path=child.getPath();
    	Resource res = resourceResolver.getResource(path+"/jcr:content/Parsys1/productdetails");
    	ValueMap resProperties = res.adaptTo(ValueMap.class);
    	String title = resProperties.get("productTitle")!= null? resProperties.get("productTitle").toString():"Dress";
    	String price = resProperties.get("productPrice")!= null? resProperties.get("productPrice").toString():"20";
    	String image = resProperties.get("imageTile")!= null? resProperties.get("imageTile").toString():"/content/dam/MilEdgeContent/No_tile_image.png";
    %>

	      <div class="col-xs-6 col-md-3 product-box">
                <a href="<%= path%>.html"><img src="<%= image%>" ></a>
                <div class="product-details">
                    <div class="product-title">
                        <%= title%>
                    </div>
                    <p class= "product-title product-price">
                        <span class= "product-title-price">
                            $ 
                        </span>
                        <%= price%>
                    </p>
                </div>
            </div>

<%

	} %>
</div>

