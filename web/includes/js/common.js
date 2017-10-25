/****************************************************************
 COMMON VALIDATION  INITIALIZATION SCRIPT
 
 In order for mainvalidation to know which validation rules to apply, 
 you must first initialize the validation process. 
 This is done by setting properties on the form fields you wish to validate
 note:
 Supported browsers IE5, Mozilla,Firefox
 
 Author: MrutyumajayaRao.Chenn<mchennu@miraclesoft.com>
 *****************************************************************/

/***********************************************
 * Disable "Enter" key in Form script- By MrutyumjayaRao.Chennu
 * Mail: mchennu@miraclesoft.com
 ***********************************************/

function handleEnter(field, event) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
        var i;
        for (i = 0; i < field.form.elements.length; i++)
            if (field == field.form.elements[i])
                break;
        i = (i + 1) % field.form.elements.length;
        field.form.elements[i].focus();
        return false;
    } 
    else
        return true;
}

/*This function isNumberKey() is only allow to enter numbers in a textbox*/
/*function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57) || (charCode == 13))
        return false;
    return true;
}*/


  function isNumberKey(evt)
       {  // alert("hiiiiii");
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57)){
            alert("Please enter Numeric vales!");
            return false;
            }
            

          return true;
       }


function isAlpha(evt){   
	var charCode = (evt.which) ? evt.which : evt.keyCode
	if((charCode > 47 && charCode<58) || (charCode == 13) || charCode==32 || (charCode == 38))
		  {
			return false;
		  }
      return true;
}

function isNameAlpha(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode
	if((charCode > 47 && charCode<58) || (charCode == 13) || (charCode == 38))
		  {
			return false;
		  }
      return true;
}
    
function alphaNumeric(evt){
  var charCode = (evt.which) ? evt.which : event.keyCode
  
	if(!((charCode > 47 && charCode<58) || (charCode > 64 && charCode<91) || (charCode > 96 && charCode<123) || charCode==8) || (charCode == 13) || (charCode == 38))
		  {
			return false;
		  }		
      return true;
}  
        
/*This function is to reset the RadioButtons*/
function clearRadioButtons(radioObj){
    var radioLength = radioObj.length;
    for(var i = 0; i < radioLength; i++) {
        radioObj[i].checked = false;
    }
}

/*This Function is to Make the alphabets to UpperCase*/
function makeUpperCase(thisObj){
  var upperValue=thisObj.value.toUpperCase();
  document.getElementById(thisObj.id).value=upperValue;  
  }
  
 function getCurrentDate(objId){
           var dateObj=new Date();
           var year=1900+dateObj.getYear();           
           var month=dateObj.getMonth()+1;         
           if(month.toString().length==1){
            month="0"+month;
           }
           var date=dateObj.getDate();
           if(date.toString().length==1){
             date="0"+date;
           }
     var curDate =year.toString()+ month.toString()+ date.toString();     
     document.getElementById(objId).value= curDate;
}
 
function copyValuTo(sourceId,targetId){	
	document.getElementById(targetId).value = document.getElementById(sourceId).value;	
}

/*This function will copy the Timestamp(MM-dd-yyyy HH:mm:ss.SSSS) 
from the source object to the target object with the 5 minutes
of time difference*/

//Note : This function not yet completed. Based on the requirement
//Have to do some manipulations on source TS and then have to copy to target
function copyTSValuTo(sourceId,targetId){
	var srcTS =document.getElementById(sourceId).value;				
	var index = srcTS.indexOf(':');							
	var srcMin = srcTS.substring(index+1,index+3);				
	var targetTS = srcTS.substring(0,index)+":"+srcMin+srcTS.substring(index+3,srcTS.length);
	document.getElementById(targetId).value=targetTS;
}		

function ediAllFormsFieldLenthvalidation(id,field,len){
	var message="Maximum Length For "+field+"  is : "+len;
		if(id.value.length > len)
		{
		alert(message);		
		id.value=id.value.substring(0,len);
		}
	}


//to convert given phone format to specified format((111)-111-1111) 
function formatPhone(element) {     
    str = new String(element.value);       
    if(str == "undefined" ) { 
        alert('Please give atleast  10 charcters in PhoneNumber');
        return false;        
    }else{        
        //element.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
        var pattern =/^\(\d{3}\)\-\d{3}\-\d{4}$/;
        element.value=str.replace(pattern, "");
        num=element.value;
        var _return;
        if(num.length == 10) { 
            _return="(";
            var ini = num.substring(0,3);
            _return+=ini+")";
            var st = num.substring(3,6);
            _return+="-"+st+"-";
            var end = num.substring(6,10);
            _return+=end;
            
            element.value ="";
            element.value =_return;
            
        }
        else if(num.length > 10)
    {
   _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end+"x";
	var ext = num.substring(10,num.length);
	_return+=ext;
          
          element.value ="";
          element.value =_return;
            
 }
        return _return;
    }
};  

function validateEmail(thisObj)
{
var x = thisObj.value;	
var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {  
  thisObj.value = "";
  
  alert("Not a valid e-mail address");
  //return false;
  }
}

/*
 * Method for setting style class dynamically.
 */
function setStyle(styleMainId,styleSubId) {
    
        document.getElementById(styleMainId).className = "current";
        if(styleSubId!='') {
        var obj = document.getElementById(styleSubId);
        if(obj.getAttribute('className') != null) { // IE bullshit
        obj.setAttribute('className','current')
        } else { // real browsers
        obj.setAttribute('class','current')
        }
}
}

/*
 * 
 * Date 05/01/2013
 * for user creation
 */

//PHONE NUMBER FORMAT SCRIPT :END
function validatenumber(xxxxx) {
   
    	var maintainplus = '';
 	var numval = xxxxx.value
 	if ( numval.charAt(0)=='+' ){ var maintainplus = '+';}
 	curnumbervar = numval.replace(/[\\A-Za-z!"£$%^&*+_={};:'@#~,¦\/<>?|`¬\]\[]/g,'');
 	xxxxx.value = maintainplus + curnumbervar;
 	var maintainplus = '';
        // alert("enter integers only");
 	xxxxx.focus;
}


//PHONE NUMBER FORMAT SCRIPT :START   
function formatPhone(num)
{ 

str = new String(document.userForm.ophno.value);
//document.userForm.ophno.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
var pattern =/^\(\d{3}\)\-\d{3}\-\d{4}$/;
    document.userForm.ophno.value=str.replace(pattern, "");
    num=document.userForm.ophno.value;
    var _return;
  if(num.length == 10) 
  { 
  _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end;
  
 document.userForm.ophno.value ="";
   document.userForm.ophno.value =_return;
         
  }else if(num.length > 10)
  {
   _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end+"x";
	var ext = num.substring(10,num.length);
	_return+=ext;
          
        alert('Phone Number should be 10 characters');
        document.userForm.ophno.value =_return;
        document.userForm.ophno.value ="";
        document.userForm.ophno.focus();
            return false;
 }else if(num.length < 10)
 {
   alert('Please give atleast  10 charcters in PhoneNumber');
    document.userForm.ophno.value ="";
 }

return _return;
}

 //to convert given phone format to specified format((111)-111-1111) 
function generalFormatPhone(element) {     
    //alert("Hii");
    str = new String(element.value);    
    //var str = element.value;
    if(str == "undefined" ) { 
        alert('Please give atleast  10 charcters in PhoneNumber');
        return false;        
    }else{        
        //element.value=str.replace(/[A-Za-z\(\)\.\-\x\s,]/g, "");
         var pattern =/^\(\d{3}\)\-\d{3}\-\d{4}$/;
        element.value=str.replace(pattern, "");
        num=element.value;
        var _return;
        if(num.length == 10) { 
            _return="(";
            var ini = num.substring(0,3);
            _return+=ini+")";
            var st = num.substring(3,6);
            _return+="-"+st+"-";
            var end = num.substring(6,10);
            _return+=end;
            
            element.value ="";
            element.value =_return;
            
        }
        else if(num.length > 10)
    {
   _return="(";
	var ini = num.substring(0,3);
	_return+=ini+")";
	var st = num.substring(3,6);
	_return+="-"+st+"-";
	var end = num.substring(6,10);
	_return+=end+"x";
	var ext = num.substring(10,num.length);
	_return+=ext;
          
          element.value ="";
          element.value =_return;
            
 }
        return _return;
    }
};
function allSmalls( name ) {
    
    
    var lower = name.value.toLowerCase(); 
    name.value=lower;
    /*alert(name.value=lower);*/
    
    return lower;
    
}

function Onlycharectors( name ){
  
  //alert("hi===>"+name.value);
  inputtxt = name.value
  //alert("hi===>"+inputtxt);
  var letters = /^[A-Za-z]+$/;
      if(!inputtxt.match(letters))
      {
         name.value="";
       alert("please enter char only"); 
      return false;
      }
}
function alphanumeric(inputtxt)
{ 
var letters = /^[0-9a-zA-Z]+$/;
if(!inputtxt.value.match(letters))
{
alert('Please input alphanumeric characters only');
inputtxt.value="";
//document.tradingPartnerForm.text1.focus();
return false;
}
}

 function alpha(e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    
    //alert("k----->"+k);
   // return ((k > 64 && k < 91) || (k > 96 && k < 123) || (k >= 48 && k <= 57) || k == 8 || k == 32 );
       return ((k >64 && k < 91) || (k > 106 && k < 123) || k == 8 || k == 32 || k==65 || (k > 96 && k < 108) || k == 0);
 
}



function checkUrl(urlObj)
{
var theUrl=urlObj.value;
if(theUrl.match(/^(http|ftp)\:\/\/\w+([\.\-]\w+)*\.\w{2,4}(\:\d+)*([\/\.\-\?\&\%\#]\w+)*\/?$/i) ||
theUrl.match(/^mailto\:\w+([\.\-]\w+)*\@\w+([\.\-]\w+)*\.\w{2,4}$/i))
{
//alert("valid Url");
}
else
{
alert("Not valid Url");
urlObj.value="http://";
urlObj.focus();
return false;
}
}

/*function checkEmail(element) {
    
    if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.value)){
        return (true)
    }
    
    element.value="";
    alert("Invalid E-mail Address! Please re-enter.")
    
    return (false)
}*/

function SchEmailValidator(element){
 //alert("In Field Length validator RSCV");
var i=0;
if(element.value!=null&&(element.value!="")){if(element.id=="extranalmailids"){i=500;
}
if(element.value.replace(/^\s+|\s+$/g,"").length>i){str=new String(element.value);
element.value=str.substring(0,i);
alert("The "+element.id+" must be less than "+i+" characters");
element.value="";
element.focus();
return false;
}return true;
}}

function checkEmail(email) {
    //alert("hi-----"+email);

var regExp =/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
return regExp.test(email);
}

function checkEmails(){
    //alert("hi");
	var emails = document.getElementById("extranalmailids").value;
	var emailArray = emails.split(",");
	for(i = 0; i <= (emailArray.length - 1); i++){
		if(checkEmail(emailArray[i])){
			//Do what ever with the email.
		}else{
			alert("invalid email: " + emailArray[i]);
		}
	}
}

function errorOverlay(id){
    
    var id=id;
      document.getElementById('ErrorMsg').innerHTML="";
//    document.getElementById("cancelReason").value="";
//    document.getElementById("cancelFlag").checked=false;
    var overlay = document.getElementById('orginloadoverlay1');
    var specialBox = document.getElementById('orginloadpecialBox1');
    //document.getElementById('operationType').value=operationType;
         
    overlay.style.opacity = .8;
    if(overlay.style.display == "block"){
        
        overlay.style.display = "none";
        specialBox.style.display = "none";
    } else {
       
        getLogisticsDocErrorDetails(id);
        overlay.style.display = "block";
        specialBox.style.display = "block";
    }
    
    
   /* if(operationType=='save'){
        document.getElementById('headTitle').innerHTML="Load&nbsp;Modification";
        document.getElementById('checkBoxMsg').innerHTML="Do you want to save this Load?";
    }
    else if(operationType=='submit'){
        document.getElementById('headTitle').innerHTML="Load&nbsp;Submission";
        document.getElementById('checkBoxMsg').innerHTML="Do you want to submit this Load?";
    }*/
}				

function getLogisticsDocErrorDetails(id){
    
   
    var id=id;
  
   var req = getXMLHttpRequest();
   req.onreadystatechange = readyStateHandlerTextError(req,populateLogisticsDocErrorDetails); 
   var url="../ajax/getLogisticsDocErrorDetails.action?id="+id;
    req.open("GET",url,"true");
    req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    req.send(null);
}
function readyStateHandlerTextError(req,responseTextHandler) {
    return function() {
        if (req.readyState == 4) {
            if (req.status == 200) {
              
                responseTextHandler(req.responseText);
            } else {
                
                alert("HTTP error ---"+req.status+" : "+req.statusText);
            }
        }else {
         
        }
    }
}
function populateLogisticsDocErrorDetails(response)
{
   
     document.getElementById('ErrorMsg').innerHTML=response;
    
    
}