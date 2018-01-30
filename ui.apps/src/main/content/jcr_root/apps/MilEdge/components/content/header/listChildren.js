var PageFilter = Packages.com.day.cq.wcm.api.PageFilter;
use(function () {
    var resourceResolver = resource.getResourceResolver();
   // var filter = new PageFilter(request);
	var parentPage= this.parent;
   // var children = this.parent.listChildren(filter)
	var pageVariable=resourceResolver.getResource(parentPage).adaptTo(Packages.com.day.cq.wcm.api.Page);
    var children= pageVariable.listChildren();
    return children;
});