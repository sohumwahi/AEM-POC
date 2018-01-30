
/*** If you want to run in the server */


(function(){
var flickerAPI = "https://jsonblob.com/api/jsonBlob/81ddebf2-c054-11e7-a6bd-53afb2900beb";
  $.getJSON( flickerAPI, {
    format: "json"
  })
    .done(function( data ) {
      //$("#dressCount").html(data.length);
      //$.template('product', '<div class="col-xs-6 col-md-3"><img src="img/${displayPicture}"><div class="product-details"><p class="product-title">${productTitle}</p><p class= "product-title product-price">${amount}</p></div></div>');
      //$.tmpl('product', data).appendTo("#images");
    });
    
    function slideEffect(){
      $( "#sizeMenu").toggle( "blind", 500);

    };
    $("#sizeSelector").on("click", function(){
      slideEffect();

    });

    function slideEffectDress(){
      $( "#dressMenu").toggle( "blind", 500);
    };
    $("#typeSelector").on("click", function(){
      slideEffectDress();
    });

    function slideEffectColor(){
       
      $( "#colorMenu").toggle( "blind", 500);
    };
    $("#colorSelector").on("click", function(){
      slideEffectColor();
    });    

    function chatToggle(){
          $("#chatBot").toggleClass("chat-bot-hide");
          $("#close-button").toggleClass("chat-bot-hide");
    };

       $("#chat-bot-icon").on("click",function()
       {
         chatToggle();
       });


       function chatFrameToggle(){
          $("#chatBot").addClass("chat-bot-hide");
          $("#close-button").addClass("chat-bot-hide");
       };

       $("#close-button").on("click", function(){
         chatFrameToggle();
       });



})();