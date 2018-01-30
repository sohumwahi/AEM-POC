
$(function(){
    function chatToggle(){
          $("#chatBot").toggleClass("chat-bot-hide");
          $("#close-button").toggleClass("chat-bot-hide");
          $('#alternative-header').removeClass("chat-bot-hide");
          $('#chat-bot-icon').addClass('chat-bot-hide'); 
    };

       $("#chat-bot-icon").on("click",function()
       {
         chatToggle();
       });


       function chatFrameToggle(){
          $("#chatBot").addClass("chat-bot-hide");
          $("#close-button").addClass("chat-bot-hide");
           $('#alternative-header').addClass("chat-bot-hide");
           $('#chat-bot-icon').removeClass('chat-bot-hide'); 
       };

       $("#close-button").on("click", function(){
         chatFrameToggle();
       });

})