console.log("수띵파크 티켓이 오신 것을 환영 환영 화녕");

let concertService = (function (){
    function name(cno, callback, error) {
        $.ajax({
            url : "/info/name/" + cno,
            type : "post",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
    }

    function detail(info, callback, error) {
        $.ajax({
            url : "/info/detail",
            type : "get",
            dataType : "json",
            success : function (result) {
                if(callback) {
                    callback(result);
                }
            },
            error : function (xhr, status, er) {
                if(error) {
                    error(xhr, status, er); // console에서 확인을 해보면 된다.
                }
            }
        })
    }

    function modify(cno, info, callback, error) {
        $.ajax({
            url : "/info/modify/" + cno ,
            type : "post",
            data: JSON.stringify(info),
            contentType: "application/json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })

    }

    return {
        name : name,
        detail : detail,
        modify : modify
    };

})()