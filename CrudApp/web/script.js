var selectedRow=null;
var id=0;

function getNextId(){
    id++;
    return id;
}

function onFormSubmit(){
    event.preventDefault();
    var formData=readFormData();
    if(selectedRow==null){
        insertData(formData);
    }
    else{
        updateData(formData);
    }
    resetData();
}

//retrieve the data
function readFormData(){

    var formData={};
    formData["accountId"]=getNextId();
    formData["fullName"]=document.getElementById("fullName").value;
    formData["country"]=document.getElementById("country").value;
    formData["currency"]=document.getElementById("currency").value;
    formData["externalId"]=document.getElementById("externalId").value;
    return formData;

}

//insert the data
function insertData(formData){

    var table=document.getElementById("accountList").getElementsByTagName("tbody")[0];
    var newRow=table.insertRow(table.length);
    cell0=newRow.insertCell(0);
        cell0.innerHTML=formData.accountId;
    cell1=newRow.insertCell(1);
        cell1.innerHTML=formData.fullName;
    cell2=newRow.insertCell(2);
        cell2.innerHTML=formData.country;
    cell3=newRow.insertCell(3);
        cell3.innerHTML=formData.currency;
    cell4=newRow.insertCell(4);
        cell4.innerHTML=formData.externalId;
    cell5=newRow.insertCell(5);
        cell5.innerHTML=`<button onclick='editData(this)'>Edit</button> <button onclick='deleteData(this)'>Delete</button>`;

}

//Edit the data
function editData(row){
    selectedRow=row.parentElement.parentElement;
    document.getElementById("fullName").value=selectedRow.cells[1].innerHTML;
    document.getElementById("country").value=selectedRow.cells[2].innerHTML;
    document.getElementById("currency").value=selectedRow.cells[3].innerHTML;
    document.getElementById("externalId").value=selectedRow.cells[4].innerHTML;
}

//update the data
function updateData(formData){
    selectedRow.cells[1].innerHTML=formData.fullName;
    selectedRow.cells[2].innerHTML=formData.country;
    selectedRow.cells[3].innerHTML=formData.currency;
    selectedRow.cells[4].innerHTML=formData.externalId;
}


//Delete the data
function deleteData(row){
    var i=row.parentElement.parentElement;
    document.getElementById("accountList").deleteRow(i.rowIndex);
    resetData();
}

// Reset the data
function resetData(){
    document.getElementById("fullName").value="";
    document.getElementById("country").value="";
    document.getElementById("currency").value="";
    document.getElementById("externalId").value="";
    selectedRow = null;
}

