"use strict";
function kiemTraRole() {
  var role = window.sessionStorage.getItem("role");
  if (role == "ROLE_HOMESELLER") {
    $(".nav-cus").remove();
    $(".nav-employee").remove();
  }
}
kiemTraRole()
let gContactId = 0;
let Contact = {
  newContact: {
    id: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    email: "",
    message: "",
    project: "",
    status: 0,
    saler: 0,
  },
  onUpdateEmployeeClick() {
    $("#modal-update-contact").modal("show")
    let vSelectedRow = $(this).parents("tr");
    let vSelectedData = EmployeeTable.row(vSelectedRow).data();
    gContactId = vSelectedData.id;
    console.log(gContactId);
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/contact/${gContactId}`,
      async: false,
      headers: headers,
      dataType: "json",
      success: function (response) {
        Contact.newContact.id = gContactId;
        Contact.newContact.firstName = response.firstName;
        Contact.newContact.lastName = response.lastName;
        Contact.newContact.phoneNumber = response.phoneNumber;
        Contact.newContact.email = response.email;
        Contact.newContact.message = response.message;
        Contact.newContact.project = response.project;
        Contact.newContact.status = response.status;
        $(".selectStatus").val(response.status)
      },
    });
  },
  onSaveEmployeeClick() {
    Contact.newContact.status = $(".selectStatus").val();
    console.log(Contact.newContact)
    $.ajax({
      url: `http://localhost:8080/contact/${gContactId}`,
      method: "PUT",
      headers: headers,
      data: JSON.stringify(Contact.newContact),
      contentType: "application/json",
      success: (data) => {
        $("#modal-update-contact").modal("hide");
        alert("Contact updated successfully");
        getEmployeeFromDb();
        gEmployeeId = 0;
        resetEmployeeInput();
      },
      error: (err) => alert(err.responseText),
    });
  },
}
let EmployeeTable = $("#contact-table").DataTable({
  columns: [
    { data: "id" },
    { data: "lastName" },
    { data: "firstName" },
    { data: "project" },
    { data: "phoneNumber" },
    { data: "status" },
    { data: "action" },
  ],
  columnDefs: [
    {
      targets: -1,
      defaultContent: `<i class="fas fa-edit text-primary"></i>`,
    },
    {
      targets: 3,
      render: function (value) {
        var name = ""
        $.ajax({
          type: "get",
          url: `http://localhost:8080/projects/${value}`,
          async: false,
          dataType: "json",
          success: function (response) {
            name = response.name
          }
        });
        return name
      }
    },
    {
      targets: 5,
      render: function (value) {
        if (value == 0) {
          return 'Chưa liên hệ';
        } else {
          return 'Đã liên hệ';
        }
      }
    }
  ],
  responsive: true,
  lengthChange: false,
  autoWidth: false,
  buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
});

EmployeeTable.buttons()
  .container()
  .appendTo("#contact-table_wrapper .col-md-6:eq(0)");
function displayProject(param) {
  var name = ""
  $.ajax({
    type: "get",
    url: `http://localhost:8080/projects/${param}`,
    async: false,
    dataType: "json",
    success: function (response) {
      name = response.name
      console.log("trong api" +name)
    }
  });
  console.log("sau api"+ name)
  return name
}
function loadEmployeeOnTable(paramEmployees) {
  "use strict";
  EmployeeTable.clear();
  EmployeeTable.rows.add(paramEmployees);
  EmployeeTable.draw();
}
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
const token = getCookie("token1")
var headers = {
  Authorization: "Token " + token
};
function getContactFromDb() {
  "use strict";
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/contact",
    dataType: "json",
    async: false,
    headers: headers,
    success: function (response) {
      var Obj = []
      for (var bIndex in response) {
        if (response[bIndex].status == 0) {
          Obj.push(response[bIndex])
        }
      }
      loadEmployeeOnTable(Obj)
    }
  });
  // $.get("http://localhost:8080/employee", (Employee) =>
  //   loadEmployeeOnTable(Employee)
  // );
}

getContactFromDb();
$(".logout").on("click", function () {
  redirectToLogin();
});

function redirectToLogin() {
  // Trước khi logout cần xóa token đã lưu trong cookie
  setCookie("token", "", 1)
  window.location.href = "./login.html"
}
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
$("#contact-table").on(
  "click",
  ".fa-edit",
  Contact.onUpdateEmployeeClick
);
$("#update-contact").click(Contact.onSaveEmployeeClick);
function loadEmployeeToInput(paramEmployees) {
  $("#modal-update-Employee #input-address").val(
    paramEmployees.address
  );
  $("#modal-update-Employee #input-lat").val(
    paramEmployees.lat
  );
  $("#modal-update-Employee #input-lng").val(
    paramEmployees.lng
  );
}
function getRole() {
  var username = window.sessionStorage.getItem("username")
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/username/${username}`,
    success: function (response) {
      if (response.roles[0].roleKey == "ROLE_CUSTOMER") {
        window.location.href = "../index.html"
      }
    }
  });
}
getRole()
function validateEmployee(paramCC) {
  "use strict";
  let vResult = true;
  try {

  } catch (e) {
    alert(e);
  }
  return vResult;
}
