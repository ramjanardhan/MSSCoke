function clientValidate()
{
    
    var category = document.getElementById("category").value;
    var priority = document.getElementById("priority").value;
    var assignment = document.getElementById("assignment").value;
    var group = document.getElementById("group").value;
    var summary = document.getElementById("summary").value;
   // var status = document.getElementById("status").value;
    var time = document.getElementById("time").value;
    var desc = document.getElementById("desc").value;
    if(category == "-1")
    {
        alert("Please Select Category!");
        return false;
    }else if(priority=="-1")
        {
            alert("Please select priority!");
             return false;
        }
     else if(assignment==null || assignment == "")
     {
         alert("Please select assignment!");
          return false;
     }
     else if(group == null || group=="")
         {
             alert("Please enter Group");
              return false;
         }
     else if(summary==null || summary=="")
     {
         alert("Please enter summary!");
          return false;
     }
     else if(time==null || time=="")
     {
         alert("Please enter Devlopment Estimation Time!");
          return false;
     }
     else if(desc==null || desc=="")
     {
         alert("Please enter description!");
          return false;
     }
     
     
 //   alert(category+" as" + priority+"as "+assignment+"as "+group+"as "+summary+"as "+status+"as "+time+"as "+desc)
    return true;
    
}
function checkLength()
{
    var summary = document.getElementById("summary").value;
     if(summary.length >500)
     {
       //  var description = document.
         if(summary.replace(/^\s+|\s+$/g,"").length>255){
            
            str = new String(document.createIssueForm.summary.value);
            document.createIssueForm.summary.value=str.substring(0,499);  
            
            alert("The Summary must be less than 500 characters");
            
            
            
        }
        document.createIssueForm.summary.focus();
        //return false;
        
     }
}
function checkDescLength()
{
     var desc = document.getElementById("desc").value;
     if(desc.length>2500)
     {
        if(desc.replace(/^\s+|\s+$/g,"").length>200){
            
            str = new String(document.createIssueForm.desc.value);
            document.createIssueForm.desc.value=str.substring(0,2499);  
            
            alert("The Description must be less than 2500 characters");
            
            
            
        }
        document.createIssueForm.desc.focus();
       // return (false);
     }
    
}