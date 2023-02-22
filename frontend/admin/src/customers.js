"use strict";
let gCustomerId = 0;
let Customer = {
  newCustomer: {
    id: 0,
    contactName: "",
    contactTitle: "",
    address: "",
    mobile: "",
    email: "",
    note: "",
    createBy: "",
    updateBy: "",
    createDate: "",
    updateDate: "",
  },
  displayModalCreateAddress() {
    $("#modal-create-customer").modal("show");
  },
  onCreateNewCustomerClick() {
    this.newCustomer = {
      id: 0,
      contactName: $("#inp-create-contactname").val().trim(),
      contactTitle: $("#inp-create-contacttitle").val().trim(),
      address: $("#inp-create-address").val().trim(),
      mobile: $("#inp-create-mobile").val().trim(),
      email: $("#inp-create-email").val().trim(),
      note: $("#inp-create-note").val().trim(),
    };
    if (validateCustomer(this.newCustomer)) {
      $.ajax({
        url: "http://localhost:8080/customers",
        method: "POST",
        headers: headers,
        data: JSON.stringify(this.newCustomer),
        contentType: "application/json",
        success: (data) => {
          $("#modal-create-customer").modal("hide");
          alert("Customer created successfully");
          getCustomerFromDb();
          resetCreateCCInput();
        },
        error: (err) => alert(err.responseText),
      });
    }
  },
  onUpdateCustomerClick() {
    let vSelectedRow = $(this).parents("tr");
    let vSelectedData = CustomerTable.row(vSelectedRow).data();
    gCustomerId = vSelectedData.id;
    console.log(gCustomerId);
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/customers/${gCustomerId}`,
      async: false,
      headers: headers,
      dataType: "json",
      success: function (response) {
        $("#modal-update-customer").modal("show");
        $("#inp-update-contactname").val(response.contactName);
        $("#inp-update-contacttitle").val(response.contactTitle);
        $("#inp-update-address").val(response.address);
        $("#inp-update-mobile").val(response.mobile);
        $("#inp-update-email").val(response.email);
        $("#inp-update-note").val(response.note);
        $("#inp-update-createby").val(response.createBy);
        $("#inp-update-updateby").val(response.updateBy);
        $("#inp-update-createdate").val(response.createDate);
        $("#inp-update-updatedate").val(response.updateDate);
      },
    });
  },
  onSaveCustomerClick() {
    this.newCustomer = {
      id: 0,
      contactName: $("#inp-update-contactname").val(),
      contactTitle: $("#inp-update-contacttitle").val(),
      address: $("#inp-update-address").val(),
      mobile: $("#inp-update-mobile").val(),
      email: $("#inp-update-email").val(),
      note: $("#inp-update-note").val(),
      updateBy: $("#inp-update-updateby").val(),
      updateDate: $("#inp-update-updatedate").val(),
    };
    if (validateCustomer(this.newCustomer)) {
      $.ajax({
        url: `http://localhost:8080/customers/${gCustomerId}`,
        method: "PUT",
        headers: headers,
        data: JSON.stringify(this.newCustomer),
        contentType: "application/json",
        success: (data) => {
          $("#modal-update-customer").modal("hide");
          alert("Customer updated successfully");
          getCustomerFromDb();
          gCustomerId = 0;
          resetCustomerInput();
        },
        error: (err) => alert(err.responseText),
      });
    }
  },
  onDeleteCustomerByIdClick() {
    $("#modal-delete-customer").modal("show");
    let vSelectedRow = $(this).parents("tr");
    let vSelectedData = CustomerTable.row(vSelectedRow).data();
    gCustomerId = vSelectedData.id;
  },
  onDeleteConfirmClick() {
    if (gCustomerId == 0) {
      $.ajax({
        url: "http://localhost:8080/customers",
        method: "DELETE",
        headers: headers,
        success: () => {
          alert("All Customer were successfully deleted");
          getCustomerFromDb();
          $("#modal-delete-cc").modal("hide");
        },
        error: (err) => alert(err.responseText),
      });
    } else {
      $.ajax({
        url: `http://localhost:8080/customers/${gCustomerId}`,
        method: "DELETE",
        headers: headers,
        success: () => {
          alert(
            `Customer with id: ${gCustomerId} was successfully deleted`
          );
          getCustomerFromDb();
          $("#modal-delete-customer").modal("hide");
        },
        error: (err) => alert(err.responseText),
      });
    }
  },
};

let CustomerTable = $("#customer-table").DataTable({
  columns: [
    { data: "id" },
    { data: "contactName" },
    { data: "action" },
  ],
  columnDefs: [
    {
      targets: -1,
      defaultContent: `<i class="fas fa-edit text-primary"></i>  ||  <i class="fa fa-trash text-primary""></i>`,
    },
  ],
  responsive: true,
  lengthChange: false,
  autoWidth: false,
  buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
});
CustomerTable.buttons()
  .container()
  .appendTo("#customer-table_wrapper .col-md-6:eq(0)");

function loadCustomerOnTable(paramCustomers) {
  "use strict";
  CustomerTable.clear();
  CustomerTable.rows.add(paramCustomers);
  CustomerTable.draw();
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
function getCustomerFromDb() {
  "use strict";
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/customers",
    dataType: "json",
    headers: headers,
    success: function (response) {
      loadCustomerOnTable(response)
    }
  });
}
getCustomerFromDb();

$(".btn-create-customer").click(Customer.displayModalCreateAddress);
$("#create-customer").click(
  Customer.onCreateNewCustomerClick
);
$("#customer-table").on(
  "click",
  ".fa-edit",
  Customer.onUpdateCustomerClick
);
$("#update-customer").click(Customer.onSaveCustomerClick);
$("#delete-all-customer").click(
  Customer.onDeleteAllCustomerClick
);
$("#delete-customer").click(Customer.onDeleteConfirmClick);
$("#customer-table").on(
  "click",
  ".fa-trash",
  Customer.onDeleteCustomerByIdClick
);
function loadCustomerToInput(paramCustomers) {
  $("#modal-update-Customer #input-address").val(
    paramCustomers.address
  );
  $("#modal-update-Customer #input-lat").val(
    paramCustomers.lat
  );
  $("#modal-update-Customer #input-lng").val(
    paramCustomers.lng
  );
}

function resetCustomerInput() {
  $("#inp-update-address").val("");
  $("#inp-update-lat").val("");
  $("#inp-update-lng").val("");
}
function resetCreateCCInput() {
  $("#inp-create-address").val("");
  $("#inp-create-lat").val("");
  $("#inp-create-lng").val("");
}

function validateCustomer(paramCC) {
  "use strict";
  let vResult = true;
  try {
    
  } catch (e) {
    alert(e);
  }
  return vResult;
}
