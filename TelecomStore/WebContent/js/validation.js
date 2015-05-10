

/*------LogIn Form------*/


function login_validateForm()
 {
	var a=document.f1.uid.value;
  if(a=="")
   {
        alert("Please enter the UserId");
        document.f1.uid.focus();
        return false;
    }
   
   if(a.indexOf(".")!=-1)
	{
	   alert("Please enter valid UserId");
	document.f1.uid.select();
  	return false;
	}
   if(isNaN(a))
   {
	   alert("Please enter only numeric value for UserId.Eg:30001");
	   document.f1.uid.select();
       return false;
   }
   if(a.charAt(0)==' ')
   {
	   alert("Please don't enter space in UserId field. Enter numerics only");
	   document.f1.uid.select();
	   return false;
   }
   if(a<=0)
   {
   	alert("Please enter valid UserId.Eg:30001");
    document.f1.uid.select();
   	return false;
   }
    if(document.f1.pwd.value=="")
    {
         alert("Please enter the Password");
         document.f1.pwd.focus();
         return false;
     } 
  
}


/*------OrderId Validation------*/


function orderId_validateForm()
{
	var a=document.forms["f1"]["orderid"].value;
	if(a=="")
	{
		alert("Please enter the order ID ");
		document.f1.orderid.focus();
		return false;
	}
	if(isNaN(a))
	{
		alert("Please enter only numerics for Order ID.Eg:100");
		document.f1.orderid.select();
		return false;
		
	}
	if(a.charAt(0)==' ')
	{
		alert("Please don't enter spaces in Order ID field. Enter numerics only");
		document.f1.orderid.select();
		return false;
	}
		
	if(a<=0)
	{
	   	alert("Please enter valid OrderId.Eg:100");
	   	document.f1.orderid.select();
	   	return false;
	}
	if(a.indexOf(".")!=-1)
	{alert("Please enter valid OrderId.Eg:100");
   	document.f1.orderid.select();
   	return false;
		
}
}
/*------Cancel order Validation------*/


function cancel_orderId_validateForm()
{
	var a=document.forms["f1"]["orderid"].value;
	if(a=="")
	{
		alert("Please enter the order ID ");
		document.f1.orderid.focus();
		return false;
	}
	if(isNaN(a))
	{
		alert("Please enter only numerics for Order ID.Eg:100");
		document.f1.orderid.focus();
		return false;
		
	}
	if(a.charAt(0)==' ')
	{
		alert("Please don't enter spaces in Order ID field. Enter numerics only");
		document.f1.orderid.select();
		return false;
	}
		
	if(a<=0)
	{
	   	alert("Please enter valid OrderId.Eg:100");
	   	document.f1.orderid.select();
	   	return false;
	}
	if(a.indexOf(".")!=-1)
	{alert("Please enter valid OrderId.Eg:100");
   	document.f1.orderid.select();
   	return false;
		
}
	
var r=confirm("Are you sure you want to cancel the order? Press OK to cancel.");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }
	}


/*------Customer Details Validation------*/


function customer_validateForm()
{
var a = document.myform.customername.value;
var b = document.myform.shipmentaddress.value;
var c = document.myform.contactno.value;
var d = document.myform.email.value;
var alphanum=/^([a-zA-Z])+([a-zA-Z0-9- : #  \ - . , ])+$/;

if(a=="")
	{
		alert("Please enter Customer Name");
		document.myform.customername.focus();
		return false;
	}

if(!(a.match(/^[a-zA-Z" "]+$/)))
{ 
alert("Please enter alphabet for Customer Name. One space is allowed in between.");
document.myform.customername.select();
return false;
}


if(a.charAt(0)==' ')
{
	alert("Please don't enter space at the begining of Customer Name");
	document.myform.customername.select();
	return false;
}
if(!(isNaN(a)))
{
	alert("Please enter alphabet for Customer Name");
	document.myform.customername.select();
	return false;
}

if(b=="")
	{
		alert("Please enter Shipment Address");
		document.myform.shipmentaddress.focus();
		return false;
	}



if(!(isNaN(b)))
{
	alert("Please enter valid address");
	document.myform.shipmentaddress.select();
	return false;
}

if(!(b.match(alphanum)))
{
	alert("Please re-enter the Shipment Address. Only (- , . : # \) are allowed.");
	document.myform.shipmentaddress.select();
	return false;
}

if (b.length <= 20)
	{
	  alert("Please enter Shipment Address with minimum 20 characters.");
	  document.myform.shipmentaddress.select();
	  return false;
	}

if(c=="")
	{
		alert("Please enter Contact Number");
		document.myform.contactno.focus();
		return false;
	}
if(c.match(/^[-s0-6]+$/))
{
	alert("Please enter valid Contact Number.Eg:9800234879");
	document.myform.contactno.select();
	return false;
}
if(isNaN(c)||c.indexOf(" ")!=-1)
{
	alert("Please enter valid Contact Number.Eg:9800234879");
	document.myform.contactno.select();
 	return false;
}
if (c.length<10)
	{
	  	alert("Please enter a Contact Number with 10 digits.");
	  	document.myform.contactno.select();
	  	return false;
	}
if(c<=0)
{
	alert("Please enter valid Contact Number.Eg:9800234879");
	document.myform.contactno.select();
	return false;
}

if(c.indexOf(".")!=-1)
{
	alert("Please don't enter '.' in Contact Number");
	document.myform.contactno.select();
	return false;

}
if(d=="")
{
	alert("Please enter Email ID");
	document.myform.email.focus();
	return false;
}

/*if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(d))
{
		return (true);
	}
		alert("Please enter valid E-mail Address");
		document.myform.email.select();
		return (false);
	
*/


/*var atpos=d.indexOf("@");

var atlast=d.lastIndexOf("@");
var dotpos=d.lastIndexOf(".");
var dotfirst=d.indexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=d.length)
  {
  alert("Please enter valid E-mail Address");
  return false;
  }

if(atlast!=atpos)
{
	alert("Please enter valid E-mail Address");
	  return false;
}
if(dotfirst==0)
{
	alert("Please enter valid E-mail Address");
	  return false;
}*/
var matchEmail =/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
if(!d.match(matchEmail) )
	 {
		 alert("Please Enter emailID in (xyz@gmail.com) format");
		 return false;
	 } 


var r=confirm("Do you want to submit? Press OK to submit.");
if (r==true)
  {
  return true;
  }
else
  {
  return false;
  }
}

function maxLength(field,maxChars)
{
      if(field.value.length >= maxChars) {
         event.returnValue=false;
         return false;
      }
}  




/*------Confirm box Validation------*/



function show_confirm()
{
var r=confirm("Do you want to submit?Press OK to submit.");
if (r==true)
  {
  return true;
  }
else
  {
  return false;
  }
}




/*------Quantity Validation------*/


function quantity_validateForm()
{
var b=document.f2.s1.selectedIndex;
var a=document.f2.q1.value;
document.f2.q1.focus();
	 
if(b==0)
{
	alert("Please select the product");
    document.f2.s1.focus();
    return false;	
	
}
  if(a=="")
  {
       alert("Please enter the quantity");
       document.f2.q1.focus();
       return false;
   } 
   if(isNaN(a))
   {
        alert("Please enter numeric value for quantity");
        document.f2.q1.select();
        return false;
    } 
   if(a.indexOf(".")!=-1)
   {
   	alert("Please enter valid Quantity");
   	document.f2.q1.select();
   	return false;

   }
   if(a.charAt(0)==' ')
   {
	   alert("Please don't enter space in Quantity field. Enter numerics only");
	   document.f2.q1.select();
	   return false;
   }
 
   if(a<=0)
   {
        alert("Please enter valid quantity");
        document.f2.q1.select();
        return false;
    }
   var r=confirm("Do you want to submit? Press OK to submit.");
	if (r==true)
	  {
	  return true;
	  }
	else
	  {
	  return false;
	  }
   f2.submit();  

   
 }


/*------Date Validation------*/


function date_validateForm(){
    
	var p=document.forms["viewall"]["D1"].value;

	var q=document.forms["viewall"]["M1"].value;
	var r=document.forms["viewall"]["Y1"].value;

	var p1=document.forms["viewall"]["D2"].value;
	var q1=document.forms["viewall"]["M2"].value;
	var r1=document.forms["viewall"]["Y2"].value;

	if((p=="")||(q=="")||(r==""))
	{
	alert("Please enter the date");
	document.viewall.D1.focus();
	return false;
	  }
	 if((p<1) || (q<1) || (r<1))
	{alert("Please enter a valid date");
	document.viewall.D1.select();
	return false;
	}
	 if((q < 1)||(q >12))
	{alert("Please enter a valid date.Eg:04/03/2000");
	document.viewall.M1.select();
	return false;

	}
	 if(p<10 && p.length!=2)
	{
	alert("Please enter Date in  DD-MM-YYYY format.Eg:04/03/2000");
	document.viewall.D1.select();
	return false;
	}
	 if(q.length!=2)
	{
		 alert("Please enter Date in  DD-MM-YYYY format.Eg:04/03/2000");
	document.viewall.M1.select();
	return false;

	}
	 if(r.length!=4)
	{
		 alert("Please enter Date in  DD-MM-YYYY format.Eg:04/03/2000");
	document.viewall.Y1.select();
	return false;

	}
	if(isNaN(p)||p.indexOf(" ")!=-1)
    {
       alert("Please enter a valid date.Eg:04/03/2000)");
       document.viewall.D1.select();
       return false; 
    }
	if(isNaN(q)||q.indexOf(" ")!=-1)
    {
       alert("Please enter a valid date.Eg:04/03/2000)");
       document.viewall.M1.select();
       return false; 
    }
	if(isNaN(r)||r.indexOf(" ")!=-1)
    {
       alert("Please enter a valid date.Eg:04/03/2000)");
       document.viewall.Y1.select();
       return false; 
    }
	if((q==1 && p>31) || (q==3 && p>31) || (q==4 && p>30) || (q==5 && p>31) || (q==6 && p>30) || (q==7 && p>31) || (q==8 && p>31) || (q==9 && p>30) || (q==10 && p>31) || (q==11 && p>30) || (q==12 && p>31)) 
     {
     alert("Please enter a valid date.Eg:04/03/2000");
     document.viewall.M1.select();
     return false;
     }

	if((r % 4 == 0) && (r % 100 != 0) || (r % 400 == 0))
     {
     pm=1;
       if(p>29 && q==2)
        {alert("Please enter valid date. February contains 29 days in Leap year.");
          document.viewall.D1.focus();
          return false;
         }
     }
	if(p>28 && q==2 && pm==0) {
     alert("Please enter valid date. February contains 28 days.");
     document.viewall.D1.focus();
     return false;
     }
	if(r.indexOf(".")!=-1)
	{
		alert("Please enter valid date");
		 document.viewall.Y1.select();
		return false;

	}
	
if((p1=="")||(q1=="")||(r1==""))
{
alert("Please enter date");
document.viewall.D2.focus();
return false;
  }
 if((p1<1) || (q1<1) || (r1<1))
{alert("Please enter a valid date");
document.viewall.D2.select();
return false;
}
if((q1 < 1)||(q1 >12))
{alert("Please enter a valid date.Eg:04/03/2000");
document.viewall.M2.select();
false;

}
if(p1<10 && p1.length!=2)
{
alert("Please enter Date in DD-MM-YYYY format.Eg:04/03/2000");
document.viewall.D2.select();
return false;
}
if(q1.length!=2)
{
	alert("Please enter Date in DD-MM-YYYY format.Eg:04/03/2000");
document.viewall.M2.select();
return false;

}
if(r1.length!=4)
{
	alert("Please enter Date in DD-MM-YYYY format.Eg:04/03/2000");
document.viewall.Y2.select();
return false;

}
if(isNaN(p1)||p1.indexOf(" ")!=-1)
{
   alert("Please enter a valid date.Eg:04/03/2000");
   document.viewall.D2.select();
   return false; 
}
if(isNaN(q1)||q1.indexOf(" ")!=-1)
{
   alert("Please enter a valid date.Eg:04/03/2000");
   document.viewall.M2.select();
   return false; 
}
if(isNaN(r1)||r1.indexOf(" ")!=-1)
{
   alert("Please enter a valid date.Eg:04/03/2000");
   document.viewall.Y2.select();
   return false; 
}
 if((q1==1 && p1>31) || (q1==3 && p1>31) || (q1==4 && p1>30) || (q1==5 && p1>31) || (q1==6 && p1>30) || (q1==7 && p1>31) || (q1==8 && p1>31) || (q1==9 && p1>30) || (q1==10 && p1>31) || (q1==11 && p1>30) || (q1==12 && p1>31)) 
 {
 alert("Please enter a valid date.Eg:04/03/2000");
 document.viewall.M2.select();
 return false;
 }

if((r1 % 4 == 0) && (r1 % 100 != 0) || (r1 % 400 == 0))
 {
 pm=1;
 if(p1>29 && q1==2)
 {alert("Please enter valid date. February contains 29 days in Leap year.");
 document.viewall.D2.select();
 return false;
 }
 }
 if(p1>28 && q1==2 && pm==0) {
 alert("Please enter valid date. February contains 28 days.");
 document.viewall.D2.select();
 return false;
 }
 if(r1.indexOf(".")!=-1)
	{
		alert("Please enter valid date.Eg:04/03/2000");
		 document.viewall.Y2.select();
		return false;

	}
 
 if(r>r1 || (q>q1 && r>=r1) || (p>p1 && q>=q1 && r>=r1))
 {
	 alert("Please re-enter the date. Starting date should not be greater than Ending date");
	  return false;
 }
  /*
 if(p>5 && q>=3 && r>=2012)
 {
	 alert("Please don't enter future date");
	 document.viewall.D1.select();
	 return false;
 }
 if(q>3 && r>=2012)
 {
	 alert("Please don't enter future date");
	 document.viewall.M1.select();
	 return false;
 }
 if(r>2012)
 {
	 alert("Please don't enter future date");
	 document.viewall.Y1.select();
	 return false;
 }
 
 if(p1>5 && q1>=3 && r1>=2012)
 {
	 alert("Please don't enter future date");
	 document.viewall.D2.select();
	 return false;
 }
 if(q1>3 && r1>=2012)
 {
	 alert("Please don't enter future date");
	 document.viewall.M2.select();
	 return false;
 }
 if(r1>2012)
 {
	 alert("Please don't enter future date");
	 document.viewall.Y2.select();
	 return false;
 }
  */
}


/*------FirstFocus Validation------*/


function Login_firstFocus()
{
	document.f1.uid.focus();
}


function OrderId_firstFocus()
{
	document.f1.orderid.focus();
}


function Customer_firstFocus()
{
	document.myform.customername.focus();
}


function Date_firstFocus()
{
	document.viewall.D1.focus();
}



/*------Check Box Validation------*/


 
function checkbox_validate()
{
	var a=document.defec.code;
	var count=0;
	
	for(var i=1;i<=a.length;i++)
	{
		if(a[i].checked==true)
		{
			count++;
		}
	}
	if(count==0)
	{
		alert("Please select atleast one product code");
		return false;
	}
	
}
