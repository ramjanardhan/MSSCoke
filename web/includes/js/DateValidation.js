/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

   // new script for data validation
   
function compareDates(datefrom,dateto){  
 //alert("start--->"+start+"--------end---------"+end);   
 
     if(((datefrom == "") && (dateto != ""))|| (datefrom != "") && (dateto == "")){
                 alert("Please enter Date From and Date To");
                return false;
      }else if(datefrom != "" && dateto != ""){
         var startDate = datefrom;             
         var endDate =dateto;
         var sDate=new Date(startDate);            
         var eDate=new Date(endDate);
        if(sDate>eDate){
             alert("DateFrom must less than DateTo!");
             return false;
        }else
             return true;
     
      }
};

/**
 * Ristrict the manual entry in date From And date to fields
 * 
 * 
 */
 
function enterDateResponse()
{
    
   document.getElementById('datepickerfrom').value = "";
   document.getElementById('datepickerTo').value = "";
   alert("Please select from the Calender !");
    return false;
}
function enterDateRepository()
{
   // alert(document.documentForm.docdatepickerfrom.value);
  // document.documentForm.docdatepickerfrom.value="";
   document.getElementById('docdatepickerfrom').value = "";
   document.getElementById('docdatepicker').value = "";
    alert("Please select from the Calender !");
    return false;
}
function enterScheduleRepository()
{
   // alert(document.documentForm.docdatepickerfrom.value);
  // document.documentForm.docdatepickerfrom.value="";
   document.getElementById('schdatepicker').value = "";
   document.getElementById('reportfromdate').value = "";
   document.getElementById('reporttodate').value = "";
    alert("Please select from the Calender !");
    return false;
}

function enterSchedulerDates()
{
   // alert(document.documentForm.docdatepickerfrom.value);
  // document.documentForm.docdatepickerfrom.value="";
   document.getElementById('schStartdate').value = "";
   document.getElementById('schEnddate').value = "";
  // document.getElementById('reporttodate').value = "";
    alert("Please select from the Calender !");
    return false;
}

function enterDateTendoring()
{
    
   document.getElementById('docdatepickerfrom').value = "";
   document.getElementById('docdatepicker').value = "";
   alert("Please select from the Calender !");
    return false;
}
function enterDateInvoice()
{
    
   document.getElementById('datepickerfrom').value = "";
   document.getElementById('datepickerTo').value = "";
   alert("Please select from the Calender !");
    return false;
}
function enterManufacturingDateInvoice()
{
    
   document.getElementById('invdatepickerfrom').value = "";
   document.getElementById('invdatepicker').value = "";
   alert("Please select from the Calender !");
    return false;
}
function enterManufacturingDatePayment()
{
    
   document.getElementById('padatepickerfrom').value = "";
   document.getElementById('padatepicker').value = "";
   alert("Please select from the Calender !");
    return false;
}
function enterManufacturingDatePurchase()
{
    
   document.getElementById('podatepicker').value = "";
   document.getElementById('podatepickerfrom').value = "";
   alert("Please select from the Calender !");
    return false;
}
function enterManufacturingDateShipment()
{
    
   document.getElementById('shpdatepicker').value = "";
   document.getElementById('shpdatepickerfrom').value = "";
   alert("Please select from the Calender !");
    return false;
}
 // populates date into date field when page loads


		function initDateTime(from,to,sampleValue) {
                     // alert("in init");
                     
                     
                      if(sampleValue == null || sampleValue=="null")
                         {
                      
                        var currentdate = new Date();
                        var yyyy = currentdate.getFullYear();
                        var dd = currentdate.getDate();
                        if(dd<10)
                            dd = "0"+dd;
                        var MM = currentdate.getMonth()+1;
                        if(MM<10)
                            MM = "0"+MM;
                        var hh = currentdate.getHours();
                        if(hh<10)
                            hh = "0"+hh;
                        var mm = currentdate.getMinutes();
                        if(mm<10)
                            mm = "0"+mm;
                        var toDate = MM+"/"+dd+"/"+yyyy+" "+hh+":"+mm;
                        
                           var mydate= new Date()
                            mydate.setDate(mydate.getDate()-2);
                             var mth = (mydate.getMonth()+1);
                             if(mth<10)
                                 mth = "0"+mth;
                             
                             var dt = mydate.getDate();
                             
                             if(dt<10)
                                 dt = "0"+dt;
                             
                           var frmDate = mth+"/"+dt+"/"+mydate.getFullYear()+" "+hh+":"+mm;
                       
                         document.getElementById(from).value = frmDate;
                         document.getElementById(to).value = toDate;

                         }
		}
                
                function initDate(from,to,sampleValue) {
                     if(sampleValue == null || sampleValue=="null")
                         {
                   
                        var currentdate = new Date();
                        var yyyy = currentdate.getFullYear();
                        var dd = currentdate.getDate();
                        if(dd<10)
                            dd = "0"+dd;
                        var MM = currentdate.getMonth()+1;
                        if(MM<10)
                            MM = "0"+MM;
                        var hh = currentdate.getHours();
                       /* if(hh<10)
                            hh = "0"+hh;
                        var mm = currentdate.getMinutes();
                        if(mm<10)
                            mm = "0"+mm;  */
                        var toDate = MM+"/"+dd+"/"+yyyy;
                        
                        
                          //  out put :: 31/3/2013   04/02/2013
                            var mydate= new Date()
                            mydate.setDate(mydate.getDate()-2);
                            //alert("Yesterday Date :" +(mydate.getMonth()+1)+"/"+mydate.getDate()+"/"+mydate.getFullYear());
                             var mth = (mydate.getMonth()+1);
                             if(mth<10)
                                 mth = "0"+mth;
                             
                             var dt = mydate.getDate();
                             
                             if(dt<10)
                                 dt = "0"+dt;
                             
                           var frmDate = mth+"/"+dt+"/"+mydate.getFullYear();
                       
                       
                           document.getElementById(from).value = frmDate;
                           document.getElementById(to).value = toDate;

                         } 
                      
		}