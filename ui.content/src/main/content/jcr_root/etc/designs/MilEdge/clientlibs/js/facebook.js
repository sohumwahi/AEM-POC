    $(document).ready(function() {
                var url = window.location.href;
        		//checkLoginState();

                if (url == "http://34.207.125.192:4510/content/MilEdge/miledge/en/home.html") {
					  if (sessionStorage["PopupShown"] != 'yes') {
                         _satellite.track("chatBotData");
                         FB.getLoginStatus(function(response) {
    						console.log('data pushed');
    						if (response.status === 'connected') {
                            sessionStorage.setItem("PopupShown","yes");
     						}
  						});
                        $("#myModal123").modal('show');
                    }
				 else if (sessionStorage["PopupShown"] === 'yes') {
							$("#myModal123").modal('hide');
                     }
                    $("#fbregister").click(function() {
                        $("#myModal123").addClass('login-fb-hide-clas');
                        sessionStorage["PopupShown"] = 'yes'; //Save in the sessionStorage if the modal has been shown
						login();
                    });
                }
            });

  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
      $("#myModal123").modal('hide');
      testAPI();
    } else {
				  document.getElementById('status').innerHTML = 'Please log ' +
			'into this app.';
    }
  }
var login=function(){
FB.login(function(response){
    if (response.status === 'connected') {
        $("#myModal123").modal('hide');
        testAPI();
        setTimeout(function(){_satellite.track("facebookData");},2000);
    	}
});};


function checkLoginState() {
	console.log("checkLoginState");
    FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
 }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '342803562884970',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.11' // use graph api version 2.8
  });

	checkLoginState()
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
      FB.api('/me?fields=id,name,birthday,hometown,email,gender', function(response) {
     $.ajax({
         type: 'GET',    
         url:'/bin/storeFacebookData',
         data:'data='+(JSON.stringify(response, null, 4)),
         success: function(msg){

         }
     });
      console.log('Successful login for: ' + response.name);
      document.getElementById('status').innerHTML =
          'Hi, ' + response.name + '!';
      		dataLayer.facebookData.name=response.name;
          	dataLayer.facebookData.birthday=response.birthday;
          	dataLayer.facebookData.hometown=response.hometown;
          	dataLayer.facebookData.email=response.email;
          	dataLayer.facebookData.gender=response.gender;
    });
  }
