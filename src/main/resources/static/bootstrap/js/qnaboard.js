function validateField(value, maxLength, errorMessage) {
    if (!value || value.length > maxLength) {
        Swal.fire({
            title: errorMessage.title,
            text: errorMessage.text,
            icon: "error",
            confirmButtonText: "확인",
            confirmButtonColor: "#dc3545"
        })
        return false;
    }
    return true;
}

function showWrite(event) {
    event.preventDefault();

    //유효성 검사
    const qna_title = document.getElementById("qna_title").value.trim();
    const qna_content = document.getElementById("qna_content").value.trim();
    const qna_writer = document.getElementById("qna_writer").value.trim();
    const qna_pw = document.getElementById("qna_pw").value.trim();

    if(!validateField(qna_title, 100, { title: "제목을 입력하세요.", text: "제목은 100자를 초과할 수 없습니다."})) return;
    if(!validateField(qna_content, 1000, { title: "내용을 입력하세요.", text: "내용은 1000자를 초과할 수 없습니다."})) return;
    if(!validateField(qna_writer, 5, { title: "작성자를 올바르게 입력하세요.", text: "작성자는 5글자를 초과할 수 없습니다."})) return;
    if(!validateField(qna_pw, 4, { title: "비밀번호(숫자)를 올바르게 입력하세요.", text: "비밀번호는 4글자를 초과할 수 없습니다."})) return;

    Swal.fire({
      icon: "success",
      title: "작성이 완료 되었습니다!",
      confirmButtonText: "확인",
      confirmButtonColor: "#0d6efd",
      allowOutsideClick: false
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById("write").submit();
        }
    });
}

function showDelete() {
    const qna_no = document.getElementById('qna_no').value.trim();

    Swal.fire({
        title: "삭제하시겠습니까?",
        text: "삭제 후 복구는 어렵습니다.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#dc3545",
        cancelButtonColor: "#0d6efd",
        confirmButtonText: "삭제하기",
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/qnaboard/delete/${qna_no}`, {
                method: 'POST'
            }).then(response => {
                if (response.ok) {
                    Swal.fire({
                        title: "삭제되었습니다.",
                        icon: "success",
                        confirmButtonText: "확인"
                    }).then(() => {
                        location.href = "/qnaboard";
                    });
                } else {
                    Swal.fire({
                        title: "삭제 실패",
                        icon: "error",
                        confirmButtonText: "확인"
                    });
                }
            }).catch(error => {
                Swal.fire({
                    title: "오류 발생",
                    text: "삭제 중 문제가 발생했습니다.",
                    icon: "error",
                    confirmButtonText: "확인"
                });
            });
        }
    });
}

function showEdit(event) {
    event.preventDefault();

    //유효성 검사
    const qna_title = document.getElementById("qna_title").value.trim();
    const qna_content = document.getElementById("qna_content").value.trim();
    const qna_writer = document.getElementById("qna_writer").value.trim();
    const qna_pw = document.getElementById("qna_pw").value.trim();

    if(!validateField(qna_title, 100, { title: "제목을 입력하세요.", text: "제목은 100자를 초과할 수 없습니다."})) return;
    if(!validateField(qna_content, 1000, { title: "내용을 입력하세요.", text: "내용은 1000자를 초과할 수 없습니다."})) return;
    if(!validateField(qna_writer, 5, { title: "작성자를 올바르게 입력하세요.", text: "작성자는 5글자를 초과할 수 없습니다."})) return;
    if(!validateField(qna_pw, 4, { title: "비밀번호(숫자)를 올바르게 입력하세요.", text: "비밀번호는 4글자를 초과할 수 없습니다."})) return;

    Swal.fire({
      icon: "success",
      title: "수정되었습니다!",
      confirmButtonText: "확인",
      confirmButtonColor: "#0d6efd",
      allowOutsideClick: false
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById("edit").submit();
        }
    });
}

function showDetail(event) {
    const qna_no = document.getElementById("qna_no").value.trim();
    const qna_pw = document.getElementById("qna_pw").value.trim();

    // 비밀번호 유효성 검사
    if(!validateField(qna_pw, 4, { title: "비밀번호를 바르게 입력하세요.", text: "비밀번호는 4자를 초과할 수 없습니다."})) return;

    // 서버에 요청
    fetch(`/qnaboard/password-check/${qna_no}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            qna_pw: qna_pw
        })
    })
    .then(response => response.text()) // 서버에서 숫자가 문자열로 올 가능성 있음
    .then(data => {
        console.log(data);
        if (data === "1") {
            location.href = `/qnaboard/detail/${qna_no}`;
        } else {
            Swal.fire({
                title: "비밀번호가 일치하지 않습니다.",
                text: "비밀번호를 재확인 해주세요.",
                icon: "error",
                confirmButtonText: "확인"
            });
        }
    })
    .catch(error => {
        Swal.fire({
            title: "오류 발생",
            text: "비밀번호 인증 중 문제가 발생했습니다.",
            icon: "error",
            confirmButtonText: "확인"
        });
    });
}