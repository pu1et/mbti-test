function refreshPage(){
    location.reload();
}

function onClick(toMbti, isFirst){
    location.href="/main?toMbti="+ toMbti + "&isFirst=" + isFirst;
}

function swalCheck(newFromMbti, newToMbti, newStars){
    $.post("main",
    {
        "newFromMbti":newFromMbti,
        "newToMbti":newToMbti,
        "newStars":newStars
    });
}
/*
function onClick(toMbti, isFirst){
    alert(toMbti+ "을 선택하셨습니다.");
    //$.get("main", {"toMbti":selectMbti});

    $.ajax({
        type: "GET",
        url: "main",
        dataType:"html",
        async: "false",
        data:{
            "toMbti":toMbti,
            "isFirst":isFirst
        },
        success: function(data){
            alert("전달 완료!");
        },
        error:function(request, error){
            alert("오류가 발생하였습니다: "+error);
        }
        });
        refreshPage();
    

}
*/