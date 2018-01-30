<%-- Tiles --%> 
<%@include file="/libs/foundation/global.jsp"%> 
<%@page session="false" %>
<%@ page import="java.util.*"%> 

<%
	Node node = resourceResolver.getResource("/content/MilEdge/chatbotData").adaptTo(Node.class);
	Property prop = node.getProperty("data");
	String data = prop.getString();
%>
<div class="cq-placeholder section" data-emptytext="footer"></div>
    <footer class="product-footer">
        <div class="footer-footer">
            <div class="footer-link">
                <h3>
                    ONLINE SHOPPING
                </h3>
                <ul class="footer-link-list">
                    <li><a href= "#">
                        Runway
                        </a>
                    </li>
                    <li><a href= "#">
                        Women
                        </a>
                    </li>
                    <li><a href= "#">
                        Men
                        </a>
                    </li>
                    <li><a href= "#">
                        Kids
                        </a>
                    </li>
                    <li><a href= "#">
                        Accessories
                        </a>
                    </li>
                </ul>
            </div>
            <div class="footer-link">
                <h3>
                    USEFUL LINK
                </h3>
                <ul class="footer-link-list">
                    <li><a href= "#">Contact Us
                        </a>
                    </li>
                    <li><a href= "#">FAQ
                        </a>
                    </li>
                    <li><a href= "#">T&C
                        </a>
                    </li>
                    <li><a href= "#">Track Of Use
                        </a>
                    </li>
                    <li><a href= "#">Track Orders
                        </a>
                    </li>
                    <li><a href= "#">Shipping
                        </a>
                    </li>
                    <li><a href= "#">Cancellation
                        </a>
                    </li>
                </ul>
            </div>

            <div class="footer-link1 footer-app-link ">   
                <img src="/content/dam/MilEdgeContent/footer img2.png" alt="App links" />
            </div>
            <div class="footer-details">   
                <img src="/content/dam/MilEdgeContent/footer image.png" alt="App links" />
            </div>
        </div>
        <div id="chatbotData" style="visibility: hidden"><%= data %> </div>
    </footer>