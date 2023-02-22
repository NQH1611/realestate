"use strict";
function kiemTraRole(){
  var role = window.sessionStorage.getItem("role");
  if(role == "ROLE_HOMESELLER"){
    $(".nav-cus").remove();
    $(".nav-employee").remove();
  }
}
var image = "";
kiemTraRole()
let gProjectId = 0;
let Project = {
  newProject: {
    id: 0,
    name: "",
    provinceId: "",
    districtId: "",
    wardId: "",
    streetId: "",
    address: "",
    slogan: "",
    description: "",
    constructArea: "",
    acreage: "",
    numBlock: "",
    numFloors: "",
    numApartment: "",
    apartmenttArea: "",
    investor: "",
    constructContractor: "",
    designUnit: "",
    utilities: "",
    regionLink: "",
    photo: "",
    lat: "",
    lng: "",
    price: "",
    status: 0,
    customer: window.sessionStorage.getItem("idcus"),
  },
  onUpdateProjectClick() {
    let vSelectedRow = $(this).parents("tr");
    let vSelectedData = ProjectTable.row(vSelectedRow).data();
    gProjectId = vSelectedData.id;
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/projectadmin/${gProjectId}`,
      async: false,
      dataType: "json",
      headers: headers,
      success: function (response) {
        image = response.photo;
        $("#modal-update-project").modal("show");
        $("#modal-update-project #inp-update-name").val(response.name);
        $("#modal-update-project #inp-update-address").val(response.address);
        $("#modal-update-project #inp-update-slogan").val(response.slogan);
        $("#modal-update-project #inp-update-description").val(
          response.description
        );
        $("#modal-update-project #inp-update-acreage").val(response.acreage);
        $("#modal-update-project #inp-update-numblock").val(response.numBlock);
        $("#modal-update-project #inp-update-numfloors").val(
          response.numFloors
        );
        $("#modal-update-project #inp-update-numapartment").val(
          response.numApartment
        );
        $("#modal-update-project #inp-update-price").val(
          response.price
        );
        $("#modal-update-project #inp-update-photo").html(response.photo)
        $("#modal-update-project #select-update-status").val(
          response.status
        );
      },
    });
  },
  onSaveProjectClick() {
    this.newProject = {
      id: 0,
      name: $("#modal-update-project #inp-update-name").val(),
      provinceId: "",
      districtId: "",
      wardId: "",
      streetId: "",
      address: $("#modal-update-project #inp-update-address").val(),
      slogan: $("#modal-update-project #inp-update-slogan").val(),
      description: $("#modal-update-project #inp-update-description").val(),
      constructArea: "",
      acreage: $("#modal-update-project #inp-update-acreage").val(),
      numBlock: $("#modal-update-project #inp-update-numblock").val(),
      numFloors: $("#modal-update-project #inp-update-numfloors").val(),
      numApartment: $("#modal-update-project #inp-update-numapartment").val(),
      apartmenttArea: "",
      investor: "",
      constructContractor: "",
      designUnit: "",
      utilities: "",
      regionLink: "",
      photo:  image,
      lat: "",
      lng: "",
      price: $("#modal-update-project #inp-update-price").val(),
      status: $("#modal-update-project #select-update-status").val(),
    };
    if (validateProject(this.newProject)) {
      $.ajax({
        url: `http://localhost:8080/project/${gProjectId}`,
        method: "PUT",
        headers: headers,
        data: JSON.stringify(this.newProject),
        dataType: "json",
        contentType: "application/json",
        success: (data) => {
          $("#modal-update-project").modal("hide");
          alert("Project updated successfully");
          getProjectFromDb();
          gProjectId = 0;
          resetProjectInput();
        },
        error: (err) => alert(err.responseText),
      });
    }
  },
  onDeleteProjectByIdClick() {
    $("#modal-delete-location").modal("show");
    let vSelectedRow = $(this).parents("tr");
    let vSelectedData = ProjectTable.row(vSelectedRow).data();
    gProjectId = vSelectedData.id;
  },
  onDeleteConfirmClick() {
    if (gProjectId == 0) {
      $.ajax({
        url: "http://localhost:8080/project",
        method: "DELETE",
        headers: headers,
        success: () => {
          alert("All project were successfully deleted");
          getProjectFromDb();
          $("#modal-delete-location").modal("hide");
        },
        error: (err) => alert(err.responseText),
      });
    } else {
      $.ajax({
        url: `http://localhost:8080/project/${gProjectId}`,
        method: "DELETE",
        headers: headers,
        success: () => {
          alert(`project with id: ${gProjectId} was successfully deleted`);
          getProjectFromDb();
          $("#modal-delete-location").modal("hide");
        },
        error: (err) => {
          if(err.status == 403){
            alert("Bạn không có quyền xóa!");
          }
        },
      });
    }
  },
};

let ProjectTable = $("#project-table").DataTable({
  columns: [
    { data: "id" },
    { data: "name" },
    { data: "address" },
    { data: "slogan" },
    { data: "description" },
    { data: "acreage" },
    { data: "numBlock" },
    { data: "numFloors" },
    { data: "numApartment" },
    { data: "photo" },
    { data: "status" },
    { data: "action" },
  ],
  columnDefs: [
    {
      targets: -1,
      defaultContent: `<i class="fas fa-edit text-primary"></i>  ||  <i class="fa fa-trash text-primary""></i>`,
    },
    {
        targets: 9,
        render: function(url){
            return `<img src = "../image/${url}" width="100%" alt=""></img>`
        }
      },
      {
        targets: 10,
        render: function(val){
            if(val == 0){
              return "Chưa duyệt!"
            }else{
              return "Đã duyệt!"
            }
        }
      },
  ],
  responsive: true,
  lengthChange: false,
  autoWidth: false,
  //buttons: ["copy", "csv", "excel", "pdf", "print", "colvis"],
});
ProjectTable.buttons()
  .container()
  .appendTo("#project-table_wrapper .col-md-6:eq(0)");
  function getImgURL(url, callback){
    var xhr = new XMLHttpRequest();
    xhr.onload = function() {
        callback(xhr.response);
    };
    xhr.open('GET', url);
    xhr.responseType = 'blob';
    xhr.send();
  }
  function loadURLToInputFiled(url){
    getImgURL(url, (imgBlob)=>{
      // Load img blob to input
      // WIP: UTF8 character error
      let fileName = 'hasFilename.jpg'
      let file = new File([imgBlob], fileName,{type:"image/jpeg", lastModified:new Date().getTime()}, 'utf-8');
      let container = new DataTransfer(); 
      container.items.add(file);
      document.querySelector('#file_input').files = container.files;
      
    })
  }
function loadProjectOnTable(paramProjects) {
  "use strict";
  ProjectTable.clear();
  ProjectTable.rows.add(paramProjects);
  ProjectTable.draw();
}
var fileList = ""
$("#inp-update-photo").on("change", function(e){
    fileList = e.target.files;
  })
  const token = getCookie("token1")
  //console.log(window.sessionStorage.getItem("token"))
  var headers = {
    Authorization: "Token " + token
  };
function getProjectFromDb() {
  "use strict";
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/projects",
    dataType: "json",
    headers: headers,
    success: function (response) {
      loadProjectOnTable(response)
    }
  });
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
getProjectFromDb();
// const fileSelector = document.getElementById('inp-update-photo');
//   fileSelector.onchange((event) => {
//     const fileList = event.target.files;
//     console.log(fileList);
//   });

$(".btn-create-location").click(Project.displayModalCreateAddress);
$("#create-location").click(Project.onCreateNewProjectClick);
$("#project-table").on("click", ".fa-edit", Project.onUpdateProjectClick);
$("#update-location").click(Project.onSaveProjectClick);
$("#delete-all-location").click(Project.onDeleteAllProjectClick);
$("#delete-location").click(Project.onDeleteConfirmClick);
$("#project-table").on("click", ".fa-trash", Project.onDeleteProjectByIdClick);

function loadUpdateProjectToInput(paramProjects) {
  $("#modal-update-location #input-update-latitude").val(
    paramProjects.latitude
  );
  $("#modal-update-location #input-update-longitude").val(
    paramProjects.longtitude
  );
}
function loadProjectToInput(paramProjects) {
  $("#modal-update-location #input-latitude").val(paramProjects.latitude);
  $("#modal-update-location #input-longitude").val(paramProjects.longtitude);
}

function resetProjectInput() {
  $("#inp-update-latitude").val("");
  $("#inp-update-longitude").val("");
}
function resetCreateProjectInput() {
  $("#inp-create-latitude").val("");
  $("#inp-create-longitude").val("");
}

function validateProject(paramProjects) {
  "use strict";
  let vResult = true;
  try {
  } catch (e) {
    alert(e);
  }
  return vResult;
}
