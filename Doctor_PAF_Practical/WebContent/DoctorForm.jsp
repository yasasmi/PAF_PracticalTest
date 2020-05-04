<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="DocPackage.Doctor"%>
<!DOCTYPE html>
<html>
<head>
	<title>Register Form</title>
	<link rel="stylesheet" type="text/css" href="Register.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/jquery-3.5.0.min.js"></script>
	<script src="Components/Doctor.js"></script>
    
</head>
<body>
	<img class="wave" src="img/wave.png">
    	
	<section id="contact">
            <div class="container">
                <h1>Doctor Register </h1>
                <div class="row">
                    <div class="col-md-6" style="margin-top: 50px;">
                            <div class="img">
                                <img src="img/undraw_medicine_b1ol.svg">
                            </div>
                    </div>
                    
                    <div class="col-md-6" style="margin-top: 50px;">
                        <form id="formItem" name="formItem">
                                                                                    
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                     <label for="From">Enter NIC No :</label>
                                     <input id="docNic" name="docNic" type="text" class="form-control" placeholder="NIC No" required>
                                </div> 
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                     <label for="From"> Enter Name :</label>
                                     <input id="docName" name="docName"  type="text" class="form-control" placeholder="Username" required>
                                </div>    
                                 
                                 <div class="form-group" style="width: 510px; margin-left: 15px;">
                                      <label for="From">Enter Email :</label>
                                     <input id="docEmail" name="docEmail" type="Email" class="form-control" placeholder="Email" required>
                                </div> 
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                      <label for="From">Enter Contact No :</label>
                                     <input id="docContact" name="docContact" type="text" class="form-control" placeholder="Contact No" required>
                                </div>
                            
                                 <div class="form-group" style="width: 510px; margin-left: 15px;">
                                     <label for="From">Enter Gender :</label><br>
                                     <select id = "docGender" name = "docGender" class="btn btn-success btn-lg dropdown-toggle dropdown-toggle-split" style="width: 100%;" required>
                                           <option value="0">--Select Gender--</option>
                                           <option >Male</option>
                                           <option >Female</option>
                                     </select>
                                </div>  
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                      <label for="From">Enter Appointment Fee :</label>
                                     <input id="docFee" name="docFee" type="text" class="form-control" placeholder="Appointment Fee" required>
                                </div>
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                     <label for="From">Select Specification :</label><br>
                                      <select id = "docSpec" name = "docSpec" class="btn btn-success btn-lg dropdown-toggle dropdown-toggle-split" style="width: 100%;" required>
                                                <option value="0">--Select Specification--</option>
                                                <option > Addiction medicine</option>
                                                <option > Pediatrics and child health</option>
                                                <option > Anesthesia, Dermatology</option>
                                                <option > Obstetrics and gynecology</option>
                                     </select>
                                </div>  
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                     <label for="From">Hospital :</label><br>
                                      <select id = "docHospital" name = "docHospital" class="btn btn-success btn-lg dropdown-toggle dropdown-toggle-split" style="width: 100%;" required>
                                                <option value="0">--Select Hospital--</option>
                                                <option >Asiri Hospitals</option>
                                                <option >Golden key Hospital</option>
                                                <option >Nine-wells Hospital</option>
                                                <option >Hemas Hospitals</option>
                                                <option >Royal Hospital</option>
                                     </select>
                                </div> 
                                <div class="form-group" style="width: 510px; margin-left: 15px;">
                                        <label for="From" style="margin-top: 8px;">Number of appointments:</label><br>
                                        <select id = "docNumAppointments" name = "docNumAppointments" class="btn btn-success btn-lg dropdown-toggle dropdown-toggle-split" style="width: 100%;" required>
                                                <option value="0">--Select Appointments--</option>
                                                <option >20</option>
                                                <option >30</option>
                                                <option >40</option>
                                                <option >15</option>
                                        </select>
                                </div>
                                 <div class="form-group" style="width: 510px; margin-left: 15px;">
                                      <label for="From">Enter Password :</label>
                                     <input id = "docPassword" name = "docPassword" type="password" class="form-control" placeholder="Password" required>
                                </div>           
                            
                                 <div class="form-group" style="width: 510px; margin-left: 15px;">
                                      <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-outline-success">
                                      <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
                                </div>                     
                          
                     </form>    
                <div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
                </div>
            </div>
            <br>    
            <div id="divItemsGrid" style="overflow-x:auto;">
					<%
						Doctor itemObj = new Doctor();
						out.print(itemObj.readDoctors());
					%>
            </div> 
        </div>        
        </section>	
    
</body>
</html>
