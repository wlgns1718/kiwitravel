let root = "";

// 회원 목록.
fetch(`${root}/admin/user`)
  .then((response) => response.json())
  .then((data) => makeList(data));

// 회원 등록.
document.querySelector("#btn-join").addEventListener("click", function () {
  let registerinfo = {
    userName: document.querySelector("#username").value,
    userId: document.querySelector("#userid").value,
    userPwd: document.querySelector("#userpwd").value,
    emailId: document.querySelector("#emailid").value,
    emailDomain: document.querySelector("#emaildomain").value,
  };
  let config = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(registerinfo),
  };
  fetch(`${root}/admin/user`, config)
    .then((response) => response.json())
    .then((data) => makeList(data));
});

//회원 정보 보기.
function userView(el) {
  var modal = new bootstrap.Modal(document.getElementById("userViewModal"), {
    backdrop: true,
    keyboard: false,
  });
  let id = el.getAttribute("data-id");
  fetch(`${root}/admin/user/${id}`)
    .then((response) => response.json())
    .then((data) => {
      document.querySelector("#vid").innerText = data.userId;
      document.querySelector("#vname").innerText = data.userName;
      document.querySelector("#vemail").innerText =
        data.emailId + "@" + data.emailDomain;
      document.querySelector("#vjoindate").innerText = data.joinDate;
      modal.show();
    });
}

// 회원 정보 수정 보기.
function viewModify(el) {
  let id = el.parentNode.parentNode.getAttribute("data-id");
  console.log(id);
  document.querySelector(`#view_${id}`).setAttribute("style", "display: none;");
  document.querySelector(`#mview_${id}`).setAttribute("style", "display: ;");
}

// 회원 정보 수정 닫기.
function closeModify(el) {
  let id = el.parentNode.parentNode.getAttribute("data-id");
  document.querySelector(`#view_${id}`).setAttribute("style", "display: ;");
  document
    .querySelector(`#mview_${id}`)
    .setAttribute("style", "display: none;");
}

// 회원 정보 수정.
function userModify(el) {
  let id = el.parentNode.parentNode.getAttribute("data-id");
  let pwd = document.querySelector(`#userpwd${id}`).value;
  let email = document.querySelector(`#email${id}`).value.split("@");
  let modifyinfo = {
    userId: id,
    userPwd: pwd,
    emailId: email[0],
    emailDomain: email[1],
  };
  let config = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(modifyinfo),
  };
  fetch(`${root}/admin/user`, config)
    .then((response) => response.json())
    .then((data) => makeList(data));
}

// 회원 삭제.
function userDelete(el) {
  if (confirm("정말 삭제?")) {
    let id = el.parentNode.parentNode.getAttribute("data-id");
    let config = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    };
    fetch(`${root}/admin/user/${id}`, config)
      .then((response) => response.json())
      .then((data) => makeList(data));
  }
}

/* let modifyViewBtns = document.querySelectorAll(".modifyViewBtn");
			modifyViewBtns.forEach(function(btn) {
				btn.addEventListener("click", function() {
					console.log(btn.parent.parent);
				});
			}); */
/* $(document).on("click", ".modiBtn", function() {
				let mid = $(this).parents("tr").attr("data-id");
				$("#view_" + mid).css("display", "none");
				$("#mview_" + mid).css("display", "");
			}); */

// 회원 목록 tbody
function makeList(users) {
  let tbody = ``;
  users.forEach(function (user) {
    console.log(user.name);
    tbody += `
						<tr id="view_${user.id}" class="view text-center" data-id="${user.id}" ondblclick="userView(this);">
							<td>${user.id}</td>
							<td>${user.name}</td>
							<td>${user.email}</td>
							<td>${user.joindate}</td>
							<td>
								<button type="button" class="modifyViewBtn btn btn-outline-primary btn-sm" onclick="viewModify(this);">수정</button>
								<button type="button" class="deleteBtn btn btn-outline-danger btn-sm" onclick="userDelete(this);">삭제</button>
							</td>
						</tr>
						<tr id="mview_${user.id}" data-id="${user.id}" style="display: none;" class="text-center">
							<td>${user.id}</td>
							<td><input type="text" name="name" id="name${user.id}" value="${user.name}"></td>
							<td><input type="text" name="email" id="email${user.id}" value="${user.email}"></td>
							<td>${user.joindate}</td>
							<td>
								<button type="button" class="modifyBtn btn btn-primary btn-sm" onclick="userModify(this);">수정</button>
								<button type="button" class="cancelBtn btn btn-danger btn-sm" onclick="closeModify(this);">취소</button>
							</td>
						</tr>
					`;
  });
  document.querySelector("#userlist").innerHTML = tbody;
}

/*
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          document.querySelector("#articleno").value = this.getAttribute("data-no");
          document.querySelector("#form-no-param").submit();
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
    	  let form = document.querySelector("#form-param");
        form.setAttribute("action", "${root}/board/write");
        form.submit();
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
        form.setAttribute("action", "${root}/board/list");
        form.submit();
      });
      
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
        	let form = document.querySelector("#form-param");
        	document.querySelector("#pgno").value = page.parentNode.getAttribute("data-pg");
          form.setAttribute("action", "${root}/board/list");
          form.submit();
        });
      });
      */
