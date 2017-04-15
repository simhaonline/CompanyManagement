<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>

a {
		-moz-transition: border-color 0.2s ease-in-out;
		-webkit-transition: border-color 0.2s ease-in-out;
		-ms-transition: border-color 0.2s ease-in-out;
		transition: border-color 0.2s ease-in-out;
		border-bottom: dotted 1px;
		color: inherit;
		outline: 0;
		text-decoration: none;
	}

		a:hover {
			border-color: transparent;
		}

/* Icon */

	.icon {
		text-decoration: none;
		position: relative;
	}

		.icon:before {
			-moz-osx-font-smoothing: grayscale;
			-webkit-font-smoothing: antialiased;
			font-family: FontAwesome;
			font-style: normal;
			font-weight: normal;
			text-transform: none !important;
		}

		.icon > .label {
			display: none;
		}

body{
	margin: 0;
	padding: 0;
	background-color: #3399FF;
}
.wrapp{
	position: absolute;
	top: 50%;
	left: 50%;
	width: 600px;
	height: 300px;
	margin: -150px 0 0 -300px;
	-webkit-perspective: 30px;
	-webkit-transform: translateX(0px);
	-webkit-animation: wrapp 400ms 800ms ease-in forwards;

	-moz-perspective: 30px;
	-moz-transform: translateX(0px);
	-moz-animation: wrapp 400ms 800ms ease-in forwards;

	-ms-perspective: 30px;
	-ms-transform: translateX(0px);
	-ms-animation: wrapp 400ms 800ms ease-in forwards;

	perspective: 30px;
	transform: translateX(0px);
	animation: wrapp 400ms 800ms ease-in forwards;
}
.text{
	position: absolute;
	top: 50%;
	left: 50%;
	width: 0px;
	height: 60px;
	margin: -30px 0 0 -160px;
	overflow: hidden;
	-webkit-transform: translateX(0px);
	-webkit-animation: text 400ms 800ms linear forwards;

	-moz-transform: translateX(0px);
	-moz-animation: text 400ms 800ms linear forwards;

	-ms-transform: translateX(0px);
	-ms-animation: text 400ms 800ms linear forwards;

	transform: translateX(0px);
	animation: text 400ms 800ms linear forwards;
}
h1{
	float: right;
	font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
	font-weight: normal;
	color: #fff;
	padding: 0;
	margin: 0;
	width: 320px;
	height: 60px;
	font-size: 60px;
	line-height: 60px;
}
.logo{
	position: absolute;
	top: 50%;
	left: 50%;
	width: 90px;
	height: 90px;
	margin: -45px 0 0 -45px;
	background-color: white;
	-webkit-transform: translateX(0px) rotateY(10deg);
	-webkit-animation: logo 500ms 300ms ease-out forwards;

	-moz-transform: translateX(0px) rotateY(10deg);
	-moz-animation: logo 500ms 300ms ease-out forwards;

	-ms-transform: translateX(0px) rotateY(10deg);
	-ms-animation: logo 500ms 300ms ease-out forwards;

	transform: translateX(0px) rotateY(10deg);
	animation: logo 500ms 300ms ease-out forwards;
}


@-webkit-keyframes logo {
    from {
    	-webkit-transform:  translateX(0px) rotateY(10deg);
    }
    to {
    	-webkit-transform:  translateX(0px) rotateY(-10deg);
    }
}
@-webkit-keyframes text {
    from {
    	width: 0px;
    	-webkit-transform: translateX(0px);
    }
    60%{
    	width: 0px;
    }
    to {
    	width: 320px;
    	-webkit-transform: translateX(240px);
    }
}
@-webkit-keyframes wrapp {
    from {
    	-webkit-transform: translateX(0px);
    }
    to {
    	-webkit-transform: translateX(-200px);
    }
}
@-moz-keyframes logo {
    from {
    	-moz-transform:  translateX(0px) rotateY(10deg);
    }
    to {
    	-moz-transform:  translateX(0px) rotateY(-10deg);
    }
}
@-moz-keyframes text {
    from {
    	width: 0px;
    	-moz-transform: translateX(0px);
    }
    60%{
    	width: 0px;
    }
    to {
    	width: 320px;
    	-moz-transform: translateX(240px);
    }
}
@-moz-keyframes wrapp {
    from {
    	-moz-transform: translateX(0px);
    }
    to {
    	-moz-transform: translateX(-200px);
    }
}

@-ms-keyframes logo {
    from {
    	-ms-transform:  translateX(0px) rotateY(10deg);
    }
    to {
    	-ms-transform:  translateX(0px) rotateY(-10deg);
    }
}
@-ms-keyframes text {
    from {
    	width: 0px;
    	-ms-transform: translateX(0px);
    }
    60%{
    	width: 0px;
    }
    to {
    	width: 320px;
    	-ms-transform: translateX(240px);
    }
}
@-ms-keyframes wrapp {
    from {
    	-ms-transform: translateX(0px);
    }
    to {
    	-ms-transform: translateX(-200px);
    }
}
@keyframes logo {
    from {
    	transform:  translateX(0px) rotateY(10deg);
    }
    to {
    	transform:  translateX(0px) rotateY(-10deg);
    }
}
@keyframes text {
    from {
    	width: 0px;
    	transform: translateX(0px);
    }
    60%{
    	width: 0px;
    }
    to {
    	width: 320px;
    	transform: translateX(240px);
    }
}
@keyframes wrapp {
    from {
    	transform: translateX(0px);
    }
    to {
    	transform: translateX(-200px);
    }
}

.ghost_btn {
    text-decoration: none;
    font-size: 150%;
    font-weight:600;
    color: #FFF ;
    border: 2px solid #FFF;
    padding: 0.65em 1.7em;
    display: inline-block;
    overflow: hidden;
    border-radius: 2px;
    transition: all 0.6s ease 0s;
    cursor: pointer;
 
}
a.ghost_btn:hover{
  background:#fff;color: #000;
 
}
.semi-transparent:hover {
  background: rgba(255, 255, 255, 0.5)!important;
}

</style>
<title>Welcome</title>
</head>

<body>
<div class="wrapp">
  <div class="text">
    <h1>SPEC Store</h1>
    
  </div>


  <div class="logo">
    <span class="top-left"></span><br>
    <img src="images/spec.jpeg" width=90px/>
    <span class="bottom-right"></span>
  </div>
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div align="center">
<br><br><br>
<a class="ghost_btn semi-transparent" href="login.jsp" >Login </a>
</div>
</body>
</html>