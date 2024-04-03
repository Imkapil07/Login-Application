<body>
<form name="f1" action="/LoginApplication/LoginServlet" method="post" onSubmit="return validate()">
  Username : <input type="text" name="username"/><br>
  Password : <input type="password" name="password"/><br>
  <input type="submit" value="Login"/><br>   
 </form> 
   New user <a href="Register.jsp">Register</a> 
   <Script>
     function validate(){
    	 var x1 = document.f1.username.value;
    	 if(x1 == null || x1 == ""){
    		 alert("Username should not be empty");
    		 return false;
    	 }
    	 var x2 = document.f1.password .value;
    	 if(x2 == null || x2 == ""){
    		 alert("Password should not be empty");
    		 return false;
    	 } 
    	 return true;
     }
   </Script>
</body>