const btn=document.getElementById("update");
const change=document.getElementById("change");
function editForm(account){

    console.log(account);

    // .0document.getElementById("fullName").innerHTML=account["fullName"];
    // document.getElementById("currency").innerHTML=account["currency"];
    // document.getElementById("country").innerHTML=account["country"];
    // document.getElementById("externlid").innerHTML=account["externlid"];

    change.value="Update";
    change.onclick=function(account){
        account["fullname"]=document.getElementById("fullName").value;
        account["currency"]=document.getElementById("currency").value;
        account["country"]=document.getElementById("country").value;
        account["externlid"]=document.getElementById("externlid").value;

        console.log(account);
    }
}
function updateForm(account){
    
}