$(document).ready(function () {
  //----Phần này làm sau khi đã làm trang info.js
  //Kiểm tra token nếu có token tức người dùng đã đăng nhập
  const token = sessionStorage.getItem("token");
  if (token != "") {
    token = sessionStorage.removeItem("token")
    //window.location.href ="./index.html"
  }
  //----Phần này làm sau khi đã làm trang info.js

  //Sự kiện bấm nút login
  $(".btn-login").on("click", function () {
    var username = $(".username").val().trim();
    var password = $(".password").val().trim();
    console.log(username, password);

    if (validateForm(username, password)) {
      signinForm(username, password);
    }
  });
  function signinForm(username, password) {
    var loginUrl = "http://localhost:8080/loginemploy";

    //API theo đặc tả sẽ call theo data-form chứ không phải kiểu json nên cần khai báo formdata
    var formData = new FormData();
    formData.append("username", username);
    formData.append("password", password);
    var obj = {
      username: $(".username").val().trim(),
      password: $(".password").val().trim(),
    };
    $.ajax({
      type: "POST",
      url: loginUrl,
      data: JSON.stringify(obj),
      contentType: "application/json",
      success: function (response) {
        responseHandler(response, username);
        xuLyChuyenTrang();
        //window.location.href = "./employee.html"
      },
    });
  }
  function xuLyChuyenTrang() {
    var username = window.sessionStorage.getItem("username");
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/username/${username}`,
      success: function (response) {
        console.log(response)
        if (response.roles[0].roleKey == "ROLE_CUSTOMER") {
          window.location.href = "./index.html";
        } else {
          window.location.href = "./admin/employee.html";
        }
      },
    });
  }
  //Xử lý object trả về khi login thành công
  function responseHandler(data, username) {
    //Lưu token vào cookie trong 1 ngày
    setCookie("token", data, 1);
    window.sessionStorage.setItem("token", data);
    window.sessionStorage.setItem("username", username);
  }

  //Hàm setCookie đã giới thiệu ở bài trước
  function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  }

  //Hiển thị lỗi lên form
  function showError(message) {
    var errorElement = $("#error");

    errorElement.html(message);
    errorElement.addClass("d-block");
    errorElement.addClass("d-none");
  }

  //Validate dữ liệu từ form
  function validateForm(username, password) {
    // if(!validateEmail(email)) {
    //     alert("Email không phù hợp");
    //     return false;
    // };
    if (username === "") {
      alert("Username không phù hợp");
      return false;
    }
    if (password === "") {
      alert("Password không phù hợp");
      return false;
    }

    return true;
  }

  // Hàm validate email bằng regex
  function validateEmail(email) {
    const regex =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(String(email).toLowerCase());
  }

  //Hàm get Cookie đã giới thiệu ở bài trước
  function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(";");
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == " ") {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
});
