<script>
    function showAlert(qna_no) {
        Swal.fire({
            title: "삭제하시겠습니까?",
            text: "삭제 후 복구는 어렵습니다.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#0d6efd",
            cancelButtonColor: "#dc3545",
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
                            location.href = "/qnaboard"; // 삭제 후 페이지 이동
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
</script>
