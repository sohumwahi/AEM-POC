"use strict";  
  
use(["/libs/sightly/js/3rd-party/q.js"], function (R) {  
    var childProperties1 = R.defer();  
    granite.resource.resolve(granite.resource.path + "/" + this.multifieldName).then(function (currentResource) {  
        currentResource.getChildren().then(function(child) {  
        childProperties1.resolve(child);  
    });  
});  
    return childProperties1.promise;  
});  