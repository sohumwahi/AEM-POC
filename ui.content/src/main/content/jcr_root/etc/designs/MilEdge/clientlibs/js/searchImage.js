//  Functionality for the Image Upload 

$(function() {
    
      // We can attach the `fileselect` event to all file inputs on the page
      $(document).on('change', ':file', function() {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
      });
    
      // We can watch for our custom `fileselect` event like this
      $(document).ready( function() {
          $(':file').on('fileselect', function(event, numFiles, label) {
    
              var input = $(this).parents('.input-group').find(':text'),
                  log = numFiles > 1 ? numFiles + ' files selected' : label;
    
              if( input.length ) {
                  input.val(log);
              } else {
                  if( log ) alert(log);
              }
    
          });
        // Functionality for the Image Upload Ends

         // Paste Image URL Functionality Starts
         $("#Image-Url").click(function(){
            $("#Image-Url").addClass("active");
            $("#Image-Upload").removeClass("active");
            $(".xyz").addClass("hide-class");
            $(".abc").removeClass("hide-class");
        });
        // Paste Image URL Functionality Ends


          // Upload an Image Button Functionality starts
          $("#Image-Upload").click(function(){
                    $("#Image-Url").removeClass("active");
                    $("#Image-Upload").addClass("active");
                    $(".abc").addClass("hide-class");
                    $(".xyz").removeClass("hide-class");
                    $("#search-results-text").addClass("hide-class");
                    $("#search-menu-container").addClass("main-container");
                    $(".search-image-text").removeClass("hide-class");
                });
          //Upload an Image Button Functionality Ends 

          // Search by Image-URL button Functionality starts

            // Search by Image-Path button Functionality ends
                });
    	$(".search-button").click(function(){
                    var x = $("#text-field-url").val();
                    if (/black/i.test(x)){
                    	$(".input-reading").html(x);
                    	$(".search-image-text").addClass("hide-class");
                    	$("#search-menu-container").removeClass("main-container").addClass("image-result-main-container");
                    	$("#search-results-text").removeClass("hide-class");
                        $("#red-dress-display").addClass("hide-class");
                        $("#black-dress-display").removeClass("hide-class");
                         $("#no-results-text").addClass("hide-class");
                    }
            	else if (/red/i.test(x)){
                    	$(".input-reading").html(x);
                    	$(".search-image-text").addClass("hide-class");
                    	$("#search-menu-container").removeClass("main-container").addClass("image-result-main-container");
                    	$("#search-results-text").removeClass("hide-class");
                        $("#black-dress-display").addClass("hide-class");
                    	 $("#red-dress-display").removeClass("hide-class");
                     $("#no-results-text").addClass("hide-class");
                    }
            		else{
						$(".search-image-text").removeClass("hide-class");
                        $("#search-menu-container").removeClass("image-result-main-container").addClass("main-container");
                    	$("#search-results-text").addClass("hide-class");
                        $("#no-results-text").removeClass("hide-class");
            		}

                });
            // Search by Image-URL button Functionality ends
				// Search by Image-Path button Functionality starts
    	$(".search-button2").click(function(){
                    var x1 = $("#text-field-path").val();
                    if (/black/i.test(x1)){
                    	$(".input-reading").html(x1);
                    	$(".search-image-text").addClass("hide-class");
                    	$("#search-menu-container").removeClass("main-container").addClass("image-result-main-container");
                    	$("#search-results-text").removeClass("hide-class");
                        $("#red-dress-display").addClass("hide-class");
                        $("#black-dress-display").removeClass("hide-class");
                        $("#no-results-text").addClass("hide-class");
                    }
            	else if (/red/i.test(x1)){
                    	$(".input-reading").html(x1);
                    	$(".search-image-text").addClass("hide-class");
                    	$("#search-menu-container").removeClass("main-container").addClass("image-result-main-container");
                    	$("#search-results-text").removeClass("hide-class");
                        $("#black-dress-display").addClass("hide-class");
                    	 $("#red-dress-display").removeClass("hide-class");
                     	$("#no-results-text").addClass("hide-class");
                    }
            		else{
						$(".search-image-text").removeClass("hide-class");
                        $("#search-menu-container").removeClass("image-result-main-container").addClass("main-container");
                    	$("#search-results-text").addClass("hide-class");
                        $("#no-results-text").removeClass("hide-class");
            		}
                });
});
