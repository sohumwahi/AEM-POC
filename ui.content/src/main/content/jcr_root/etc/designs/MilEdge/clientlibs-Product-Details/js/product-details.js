$(function(){
    $("#description").click(function(){
        $(".product-details-shipping").addClass("product-details-hidden ");
        $(".product-details-specification").addClass("product-details-hidden ");
        $(".product-details-description").removeClass("product-details-hidden");
        $("#description").addClass("focus-class");
        $("#shipping").removeClass("focus-class");
        $("#specification").removeClass("focus-class");
    });
    $("#shipping").click(function(){
        $(".product-details-description").addClass("product-details-hidden ");
        $(".product-details-specification").addClass("product-details-hidden ");
        $(".product-details-shipping").removeClass("product-details-hidden");
        $("#shipping").addClass("focus-class");
        $("#description").removeClass("focus-class");
        $("#specification").removeClass("focus-class");
    });
    $("#specification").click(function(){
        $(".product-details-description").addClass("product-details-hidden ");
        $(".product-details-shipping").addClass("product-details-hidden ");
        $(".product-details-specification").removeClass("product-details-hidden");
        $("#specification").addClass("focus-class");
        $("#description").removeClass("focus-class");
        $("#shipping").removeClass("focus-class");
    });

    $("#imageOne").click(function(){
       var big_src = $('.image-one').attr('src');
       $('.product-display-image').attr('src', big_src);
    });

    $("#imageTwo").click(function(){
       var big_src = $('.image-two').attr('src');
       $('.product-display-image').attr('src', big_src);
    });

    $("#imageThree").click(function(){
       var big_src = $('.image-three').attr('src');
       $('.product-display-image').attr('src', big_src);
    });

    $("#imageFour").click(function(){
       var big_src = $('.image-four').attr('src');
       $('.product-display-image').attr('src', big_src);
    });

    $("#imageFive").click(function(){
       var big_src = $('.image-five').attr('src');
       $('.product-display-image').attr('src', big_src);
    });

    $(".reduce-quantity").click(function(){
      var productQuantity= $(".text-quantity").val();
      var reducedProductQuantity= productQuantity-1;
      if(reducedProductQuantity>=0)
        $(".text-quantity").val(reducedProductQuantity);
    })
    
    $(".add-quantity").click(function(){
      var productQuantity= $(".text-quantity").val();
      var addProductQuantity= parseInt(productQuantity)+1;
      $(".text-quantity").val(addProductQuantity);
    })
    //carausel starts
    
    $('#myCarousel').carousel({
  interval: 4000
})

$('.carousel .item').each(function(){
  var next = $(this).next();
  if (!next.length) {
    next = $(this).siblings(':first');
  }
  next.children(':first-child').clone().appendTo($(this));
  
  for (var i=0;i<2;i++) {
    next=next.next();
    if (!next.length) {
    	next = $(this).siblings(':first');
  	}
    
    next.children(':first-child').clone().appendTo($(this));
  }
});

//carausel ends
})