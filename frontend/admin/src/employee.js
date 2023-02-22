"use strict"
function kiemTraRole(){
  var role = window.sessionStorage.getItem("role")
  if(role == "ROLE_HOMESELLER"){
    window.location.href = "./project.html"
    $(".nav-cus").remove()
    $(".nav-employee").remove()
  }
}
kiemTraRole()
let gEmployeeId = 0
let Employee = {
  newEmployee: {
    id: "",
    lastName: "",
    firstName: "",
    title: "",
    titleOfCourtesy: "",
    birthDay: "",
    HireDay: "",
    address: "",
    city: "",
    region: "",
    postalCode: "",
    Country: "",
    homePhone: "",
    Extention: "",
    photo: "",
    notes: "",
    reportsTo: "",
    userName: "",
    password: "",
    email: "",
    activated: "",
    profile: "",
    userLevel: "",
    roles: "",
  },
  onCreateNewEmployeeClick() {
    Employee.newEmployee = {
      id: "",
      lastName: $("#inp-lastname").val().trim(),
      firstName: $("#inp-firstname").val().trim(),
      title: $("#inp-title").val().trim(),
      titleOfCourtesy: $("#inp-titleofcourtesy").val().trim(),
      birthDay: $("#inp-birthDay").val().trim(),
      HireDay: $("#inp-hireDate").val().trim(),
      address: $("#inp-address").val().trim(),
      city: $("#inp-city").val().trim(),
      region: $("#inp-region").val().trim(),
      postalCode: $("#inp-postalcode").val().trim(),
      Country: $("#inp-country").val().trim(),
      homePhone: $("#inp-homephone").val().trim(),
      Extention: $("#inp-extention").val().trim(),
      photo: $("#inp-photo").val().trim(),
      notes: $("#inp-notes").val().trim(),
      reportsTo: $("#inp-reportsto").val().trim(),
      userName: $("#inp-username").val().trim(),
      password: $("#inp-password").val().trim(),
      email: $("#inp-email").val().trim(),
      activated: $("#inp-activated").val().trim(),
      profile: $("#inp-profile").val().trim(),
      userLevel: "2",
      }
    if(validateEmployee(this.newEmployee)) {
      $.ajax({
        url: "http://localhost:8080/employee",
        method: "POST",
        headers: headers,
        data: JSON.stringify(Employee.newEmployee),
        contentType: "application/json",
        success: (data) => {
          alert("Employee created successfully")
        },
        error: (err) => alert(err.responseText),
      })
    }
  },
  onUpdateEmployeeClick() {
    $("#modal-update-employee").modal("show")
    let vSelectedRow = $(this).parents("tr")
    let vSelectedData = EmployeeTable.row(vSelectedRow).data()
    gEmployeeId = vSelectedData.id
    console.log(gEmployeeId)
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/employee/${gEmployeeId}`,
      async: false,
      headers: headers,
      dataType: "json",
      success: function (response) {
        $("#modal-update-employee #txtName").val(`${response.firstName}`)
        $("#modal-update-employee #txtRole").val(response.userLevel)
        Employee.newEmployee = {
          id: "",
          lastName: response.lastName,
          firstName: response.firstName,
          title: response.title,
          titleOfCourtesy: response.titleOfCourtesy,
          birthDay: response.birthDay,
          hireDay: response.hireDay,
          address: response.address,
          city: response.city,
          region: response.region,
          postalCode: response.postalCode,
          country: response.country,
          homePhone: response.homePhone,
          extention: response.extention,
          photo: response.photo,
          notes: response.notes,
          reportsTo: response.reportsTo,
          userName: response.userName,
          password: response.password,
          email: response.email,
          activated: response.activated,
          profile: response.profile,
          userLevel: response.userLevel,
          }
      },
    })
  },
  onSaveEmployeeClick() {
    Employee.newEmployee.userLevel = $("#txtRole").val();
      $.ajax({
        url: `http://localhost:8080/employee/${gEmployeeId}`,
        method: "PUT",
        headers: headers,
        data: JSON.stringify(Employee.newEmployee),
        contentType: "application/json",
        success: (data) => {
          $("#modal-update-employee").modal("hide")
          alert("Employee updated successfully")
          getEmployeeFromDb()
        },
        error: (err) => alert(err.responseText),
      })
    },
  onDeleteEmployeeByIdClick() {
    $("#modal-delete-Employee").modal("show")
    let vSelectedRow = $(this).parents("tr")
    let vSelectedData = EmployeeTable.row(vSelectedRow).data()
    gEmployeeId = vSelectedData.id
  },
  onDeleteConfirmClick() {
    if (gEmployeeId == 0) {
      $.ajax({
        url: "http://localhost:8080/employee",
        method: "DELETE",
        headers: headers,
        success: () => {
          alert("All Employee were successfully deleted")
          getEmployeeFromDb()
          $("#modal-delete-employee").modal("hide")
        },
        error: (err) => alert(err.responseText),
      })
    } else {
      $.ajax({
        url: `http://localhost:8080/employee/${gEmployeeId}`,
        method: "DELETE",
        headers: headers,
        success: () => {
          alert(
            `Employee with id: ${gEmployeeId} was successfully deleted`
          )
          getEmployeeFromDb()
          $("#modal-delete-employee").modal("hide")
        },
        error: (err) => alert(err.responseText),
      })
    }
  },
}

let EmployeeTable = $("#employee-table").DataTable({
  columns: [
    { data: "action" },
    { data: "id" },
    { data: "lastName" },
    { data: "firstName" },
    { data: "title" },
    { data: "birthDay" },
    { data: "hireDate" },
    { data: "userLevel" },
  ],
  columnDefs: [
    {
      targets: 0,
      defaultContent: `<i class="fas fa-edit text-primary"></i><i class="fa fa-trash text-primary""></i>`,
    },
    {
      targets: -1,
      render: (value)=>{
        if(value == 1){
          return "Admin"
        }else if(value == 2){
          return "Customer"
        }else if(value == 3){
          return "Home Seller"
        }else{
          return "Không xác định"
        }
      },
    },
  ],
  responsive: true,
  lengthChange: false,
  autoWidth: false,
  buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
})
EmployeeTable.buttons()
  .container()
  .appendTo("#employee-table_wrapper .col-md-6:eq(0)")

function loadEmployeeOnTable(paramEmployees) {
  "use strict"
  EmployeeTable.clear()
  EmployeeTable.rows.add(paramEmployees)
  EmployeeTable.draw()
}
function getCookie(cname) {
  var name = cname + "="
  var decodedCookie = decodeURIComponent(document.cookie)
  var ca = decodedCookie.split('')
  for (var i = 0; i < ca.length; i++) {
      var c = ca[i]
      while (c.charAt(0) == ' ') {
          c = c.substring(1)
      }
      if (c.indexOf(name) == 0) {
          return c.substring(name.length, c.length)
      }
  }
  return ""
}
const token = window.sessionStorage.getItem("token")
console.log(window.sessionStorage.getItem("token"))
var headers = {
  Authorization: "Token " + token
}
console.log(headers)
function getEmployeeFromDb() {
  "use strict"
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/employee",
    dataType: "json",
    headers: headers,
    success: function (response) {
      loadEmployeeOnTable(response)
    }, 
    error: function(response){
      if(response.status == 403){
        alert("Bạn không có quyền truy cập vào!")
      }
    }
  })
}

getEmployeeFromDb()

$(".btn-create-employee").on("click", function(){
  window.location.href = "./pagecrud/ru_employee.html"
})
$("#tao-tai-khoan").on("click", function(){
  Employee.onCreateNewEmployeeClick()
}
)
$(".logout").on("click", function() {
  redirectToLogin()
})

function redirectToLogin() {
  // Trước khi logout cần xóa token đã lưu trong cookie
  setCookie("token", "", 1)
  window.location.href = "./login.html"
}
function setCookie(cname, cvalue, exdays) {
  var d = new Date()
  d.setTime(d.getTime() + (exdays*24*60*60*1000))
  var expires = "expires="+ d.toUTCString()
  document.cookie = cname + "=" + cvalue + "" + expires + "path=/"
}
$("#employee-table").on(
  "click",
  ".fa-edit",
  Employee.onUpdateEmployeeClick
)
$("#update-employee").click(Employee.onSaveEmployeeClick)
$("#delete-all-employee").click(
  Employee.onDeleteAllEmployeeClick
)
$("#delete-employee").click(Employee.onDeleteConfirmClick)
$("#employee-table").on(
  "click",
  ".fa-trash",
  Employee.onDeleteEmployeeByIdClick
)
function loadEmployeeToInput(paramEmployees) {
  $("#modal-update-Employee #input-address").val(
    paramEmployees.address
  )
  $("#modal-update-Employee #input-lat").val(
    paramEmployees.lat
  )
  $("#modal-update-Employee #input-lng").val(
    paramEmployees.lng
  )
}
function getRole(){
  var username = window.sessionStorage.getItem("username")
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/username/${username}`,
    success: function (response) {
      if(response.roles[0].roleKey == "ROLE_CUSTOMER"){
        window.location.href = "../index.html"
      }
    }
  })
}
getRole()
function resetEmployeeInput() {
  $("#inp-update-address").val("")
  $("#inp-update-lat").val("")
  $("#inp-update-lng").val("")
}
function resetCreateCCInput() {
  $("#inp-create-address").val("")
  $("#inp-create-lat").val("")
  $("#inp-create-lng").val("")
}

function validateEmployee(paramCC) {
  "use strict"
  let vResult = true
  try {
    
  } catch (e) {
    alert(e)
  }
  return vResult
}
